/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import Entities.maintenace;
import Entities.rapport;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.rapportService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AfficherRapportController implements Initializable {

    @FXML
    private TableColumn<rapport, Integer> id_r;
    @FXML
    private TableColumn<rapport, String> description;
    @FXML
    private TableColumn<rapport, Integer> duree;
    @FXML
    private TableColumn<rapport, String> piece;
    @FXML
    private TableColumn<rapport, Integer> cout;
    @FXML
    private TableColumn<rapport, String> observation;
    @FXML
    private TableColumn<rapport, Button> modifier;
    @FXML
    private TableColumn<rapport, Button> supprimer;
    private Button retour;
    @FXML
    private TableView<rapport> tv;
    rapportService rapSer = new rapportService();
    ObservableList<rapport> Listrapport = FXCollections.observableArrayList();
    ObservableList<rapport> Listrapport1 = FXCollections.observableArrayList();
    maintenace main = new maintenace();
    @FXML
    private Button btnrecherche;
    @FXML
    private TextField tfrecherche;
    @FXML
    private Button writepdf;
    @FXML
    private Button acceuil1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List list = null;
            list = rapSer.recuperer();
            Listrapport.addAll(list);
            id_r.setCellValueFactory(new PropertyValueFactory<>("id"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            piece.setCellValueFactory(new PropertyValueFactory<>("piece"));
            cout.setCellValueFactory(new PropertyValueFactory<>("Cout"));
            observation.setCellValueFactory(new PropertyValueFactory<>("observation"));
            
            tv.setItems(Listrapport);
            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherRapportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete() {

        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimer");
                        b.setOnAction((event) -> {
                            try {
                                if (rapSer.supprimer(tv.getItems().get(getIndex()))) {
                                    tv.getItems().remove(getIndex());
                                    tv.refresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherRapportController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    private void modifier() {
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierRapoort.fxml"));
                                Parent root = loader.load();
                                gui.ModifierRapoortController modifier = loader.getController();
                                rapport r = tv.getItems().get(getIndex());
                                System.out.println(r);
                               // modifier.setrapport(r);
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherRapportController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);
                    }
                }
            };

        });
    }


    @FXML
    private void Recherche(ActionEvent event) throws SQLException {
        String piecce = tfrecherche.getText();
        List<rapport> l = rapSer.recuperer();
        List<rapport> l1 = l.stream()
                .filter(p -> p.getPiece().equals(piecce))
                .collect(Collectors.toList());
        Listrapport1.addAll(l1);
        if (l1.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun Rapport n'existe avec cette piece");
            alert.showAndWait();

        } else {

            id_r.setCellValueFactory(new PropertyValueFactory<>("id"));

            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            duree.setCellValueFactory(new PropertyValueFactory<>("duree"));
            piece.setCellValueFactory(new PropertyValueFactory<>("piece"));
            cout.setCellValueFactory(new PropertyValueFactory<>("Cout"));
            observation.setCellValueFactory(new PropertyValueFactory<>("observation"));

            tv.setItems(Listrapport1);

        }

    }

    @FXML
    private void pdf(ActionEvent event) throws DocumentException, FileNotFoundException, BadElementException, IOException {

        rapport selected = tv.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a row");
            alert.showAndWait();
            return;
        }
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\USER\\Desktop\\rapport.pdf"));
            doc.open();
            // Create a Font for the title
            Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);

// Create a Font for the body text
            Font bodyFont = new Font(Font.HELVETICA, 12, Font.NORMAL);

// Create a Paragraph with the title text and apply the title font
            Paragraph title = new Paragraph("Rapport", titleFont);

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            title.setAlignment(Element.ALIGN_CENTER);
            doc.add(title);
            rapportService rs = new rapportService();
            List<rapport> metiers = null;
            metiers = rs.recuperer();
            Image logo = Image.getInstance("C:\\Users\\USER\\Downloads\\QuickShip\\src\\gui\\logo.png");
            logo.scaleAbsolute(200, 150);
            doc.add(logo);
            int idInt = selected.getId();
            String idString = Integer.toString(idInt);
            int dureeInt = selected.getDuree();
            String dureeString = Integer.toString(dureeInt);
            int coutInt = selected.getCout();
            String coutString = Integer.toString(coutInt);
            
            
            Paragraph description = new Paragraph();
            description.add(new Chunk("Description: "));
            description.add(new Chunk(selected.getDescription()));
            doc.add(description);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            Paragraph duree = new Paragraph();
            duree.add(new Chunk("Duree: "));
            duree.add(new Chunk(dureeString));
            doc.add(duree);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            Paragraph pie = new Paragraph();
            pie.add(new Chunk("Piece: "));
            pie.add(new Chunk(selected.getPiece()));
            doc.add(pie);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            
            Paragraph coutt = new Paragraph();
            coutt.add(new Chunk("Cout: "));
            coutt.add(new Chunk(coutString));
            doc.add(coutt);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));
            Paragraph ob = new Paragraph();
            ob.add(new Chunk("Observation: "));
            ob.add(new Chunk(selected.getObservation()));
            doc.add(ob);

            doc.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "PDF generated successfully");
            alert.showAndWait();
        } catch (DocumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to generate PDF: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void Accueilbu(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMaintenance.fxml"));
            Parent root = loader.load();
            acceuil1.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

 
}
