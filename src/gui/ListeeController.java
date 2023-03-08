/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Conducteur;
import Entities.Trajet;
import Entities.Vehicule;
import Services.TrajetService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class ListeeController implements Initializable {

    
    @FXML
    private TableColumn<Trajet, Date> date;
    @FXML
    private TableColumn<Trajet, String> point;
    @FXML
    private TableColumn<Trajet, String> vehicule;
    @FXML
    private TableColumn<Trajet, String> conducteur;
    @FXML
    private TableView<Trajet> trajettable;

    /**
     * Initializes the controller class.
     */
    TrajetService s=new TrajetService();
    
    ObservableList<Trajet>  StudentList = FXCollections.observableArrayList();
    ObservableList<Trajet>  StudentList1 = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Trajet, Button> Delete;
    @FXML
    private TableColumn<Trajet, Button> modifier;
    @FXML
    private AnchorPane retourner;
    @FXML
    private Button retour;
    @FXML
    private Text text1;
    @FXML
    private Button btn3;
    @FXML
    private TextField rech;
    @FXML
    private Button rechercher;
    @FXML
    private DatePicker daterech;
    @FXML
    private TableColumn<Trajet, String> etat;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List l=s.afficher();
            StudentList.addAll(l);
        
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        point.setCellValueFactory(new PropertyValueFactory<>("Point_dep"));
        vehicule.setCellValueFactory(cellData -> {
                Vehicule check = cellData.getValue().getVehicule();
                return new SimpleStringProperty(check.getMarque());
            });
      //  vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etatTraj"));
        trajettable.setItems(StudentList);
        this.delete();
        this.modifier();
        } catch (SQLException ex) {
            Logger.getLogger(ListeeController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }    
    
    private void delete() {
        Delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Delete");
                        b.setStyle("-fx-background-color: #F5655C");
                        b.setOnAction((event) -> {
                       
                            try {
                                if (s.Supprimer_trajet(trajettable.getItems().get(getIndex()).getId()))
                                {
                                    trajettable.getItems().remove(getIndex());
                                    trajettable.refresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ListeeController.class.getName()).log(Level.SEVERE, null, ex);
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
                        b.setStyle("-fx-background-color: #61F368");
                        b.setOnAction((event) -> {
                       
        Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        System.out.println(trajet.getConducteur().getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTrajet.fxml"));
                            try {
                                Parent root=loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        UpdateTrajetController modifiier = loader.getController();
        String nomConducteur = trajet.getPoint_dep();
        LocalDate d=trajet.getDate().toLocalDate();
        Vehicule V =trajet.getVehicule();
        Conducteur C=trajet.getConducteur();
        int id=trajet.getId();
        String myString = Integer.toString(id);
        
        modifiier.setTrajet(nomConducteur,d,V,C,myString,trajet.getEtatTraj());
                           Parent parent = loader.getRoot();
                            
                          Scene scene = b.getScene();
                          scene.setRoot(parent);
                            

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("homeFXML.fxml"));
        
                        Parent root=loader.load();
                        Parent parent = loader.getRoot();
                          Scene scene = retour.getScene();
                          scene.setRoot(parent);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
        
                        Parent root=loader.load();
                        Parent parent = loader.getRoot();
                          Scene scene = btn3.getScene();
                          scene.setRoot(parent);
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLException {
        
        java.sql.Date datee = java.sql.Date.valueOf(daterech.getValue());
        
            List<Trajet> l=s.afficher();
            List<Trajet> l1=l.stream()
                     .filter(p->p.getDate().equals(datee))
                     .collect(Collectors.toList());
            if(l1.isEmpty())
            {
                
                Alert alert = new Alert(Alert.AlertType.ERROR, "Aucun trajet n'existe à cette date" );
                alert.showAndWait(); 
                
                
            }
            else
            {
                date.setCellValueFactory(new PropertyValueFactory<>("Date"));
                point.setCellValueFactory(new PropertyValueFactory<>("Point_dep"));
              vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));
        trajettable.setItems(StudentList1);
        this.delete();
        this.modifier(); 
        StudentList1.addAll(l1);
           
            }
           
        
    }
    
}
