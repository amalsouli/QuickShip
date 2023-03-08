/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Services.rapportService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class TechnicienAffichageController implements Initializable {

    @FXML
    private VBox cardContainer;
    @FXML
    private TextField search;
    ObservableList<rapport> ListR = FXCollections.observableArrayList();
    rapportService rSer = new rapportService();
    @FXML
    private Button home;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<rapport> list = rSer.recuperer();

            ListR.addAll(list);

            for (rapport com : ListR) {

                // Créer les éléments de la première colonne
                Label label2v = new Label("     '" + com.getDescription() + "'");
                VBox vbox1 = new VBox();
                vbox1.getChildren().addAll(label2v);
                vbox1.setAlignment(Pos.CENTER_LEFT);
                vbox1.setPrefWidth(200);

                // Créer les éléments de la deuxième colonne
                Label coutt = new Label(String.valueOf(com.getCout()));

                HBox cout = new HBox();
                cout.getChildren().addAll(coutt);
                cout.setAlignment(Pos.CENTER_LEFT);
                cout.setPrefWidth(150);
                // Créer les éléments de la troissieme colonne
                Label dur = new Label(String.valueOf(com.getDuree()));

                HBox duree = new HBox();
                duree.getChildren().addAll(dur);
                duree.setAlignment(Pos.CENTER_LEFT);
                duree.setPrefWidth(150);

                // Créer la troisième colonne
                Label label3 = new Label(com.getPiece());
                HBox Piece = new HBox();
                Piece.getChildren().addAll(label3);
                Piece.setAlignment(Pos.CENTER_LEFT);
                Piece.setPrefWidth(150);
                // Créer la troisième colonne
                Label label8 = new Label(com.getObservation());
                HBox observation = new HBox();
                observation.getChildren().addAll(label8);
                observation.setAlignment(Pos.CENTER_LEFT);
                observation.setPrefWidth(200);

                // Créer les éléments de la quatrième colonne
                Button button_mod = new Button("Modifier");

                button_mod.setOnAction((event) -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRapportFront.fxml"));
                        Parent root = loader.load();
                        gui.ModifierRapportFrontController modifiier = loader.getController();
                        modifiier.setrapport(com);
                        Scene scene = button_mod.getScene();
                        scene.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(TechnicienAffichageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                // Créer les éléments de la quatrième colonne
                Button button_pdf = new Button("PDF");
                                button_pdf.setOnAction((event) -> {

                 try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRapport.fxml"));
                        Parent root = loader.load();
                        gui.AfficherRapportController modifiier = loader.getController();
                        Scene scene = button_pdf.getScene();
                        scene.setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(TechnicienAffichageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                
                });

                HBox vbox4 = new HBox();
                vbox4.getChildren().addAll(button_pdf);
                vbox4.setAlignment(Pos.CENTER_LEFT);
                vbox4.setPrefWidth(200);

                HBox vbox3 = new HBox();
                vbox3.getChildren().addAll(button_mod);
                vbox3.setAlignment(Pos.CENTER_LEFT);
                vbox3.setPrefWidth(200);

                // Créer la HBox avec toutes les colonnes
                HBox hbox = new HBox();
                hbox.getChildren().addAll(vbox1, observation, duree, coutt, Piece, vbox3, vbox4);
                hbox.setStyle("  -fx-background-color: #FFFFFF;\n"
                        + "    -fx-border-radius: 5px;\n"
                        + "    -fx-border-color: #D3D3D3;\n"
                        + "    -fx-border-width: 1px;\n"
                        + "    -fx-padding: 20px;\n"
                        + "    -fx-margin: 10px;\n"
                        + "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
                        + " display: flex;\n"
                        + "  -fx-height: 2px;");
                hbox.setSpacing(20);
                // Ajouter la HBox à la VBox
                cardContainer.getChildren().add(hbox);
                cardContainer.setStyle(" overflow-y: scroll;");
            }

            /*search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
        Predicate<Commande> filtrePersonnes = commande
                -> filtre.get().isEmpty()
                || commande.getNom_produit().toLowerCase().contains(filtre.get().toLowerCase())
                || commande.getAdresse_départ().toLowerCase().contains(filtre.get().toLowerCase());
        cardContainer.getChildren().setAll(node.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
cardContainer.getChildren().setAll(node.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        });*/
        } catch (SQLException ex) {
            Logger.getLogger(TechnicienAffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/front.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

