/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.TYPE_VEHICULE;
import entities.Vehicule;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherVehiculeFXMLController implements Initializable {

    @FXML
    private TableColumn<Vehicule, Integer> annee;
    @FXML
    private TableColumn<Vehicule, Integer> capacite;
    @FXML
    private TableColumn<Vehicule, String> marque;
    @FXML
    private TableColumn<Vehicule, String> modele;
    @FXML
    private TableColumn<Vehicule, TYPE_VEHICULE> type;
    @FXML
    private TableColumn<Vehicule, Integer> remorque;
    @FXML
    private TableView<Vehicule> table;

    VehiculeServices vehiculeServ = new VehiculeServices();
    ObservableList<Vehicule> listVehicule = FXCollections.observableArrayList();
    @FXML
    private Button retour;
    
    @FXML
    private TableColumn<Vehicule, Button> Delete;
    @FXML
    private TableColumn<Vehicule, Button> Modifier;
    @FXML
    private TableColumn<Vehicule, String> matricule;
    @FXML
    private TableColumn<Vehicule, Integer> id;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List list = vehiculeServ.afficher();
        listVehicule.addAll(list);
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        remorque.setCellValueFactory(new PropertyValueFactory<>("remorque"));
        this.delete();
        this.modifier();
        /* vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));*/
        table.setItems(listVehicule);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/HomeVehiculeFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    private void delete() {
        Delete.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Delete");
                        b.setOnAction((event) -> {
                       
                                if (vehiculeServ.supprimer(table.getItems().get(getIndex())))
                                         {
                                    table.getItems().remove(getIndex());
                                    table.refresh();
                                }
                            

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    private void modifier() {
        Modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierVehiculeFXMLfxml.fxml"));
                                Parent root = loader.load();
                                GUI.ModifierVehiculeFXMLfxmlController modifiier = loader.getController();
                                Vehicule v = table.getItems().get(getIndex());
                               
                                modifiier.SetVehicule(v);
                                /* Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();*/
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherVehiculeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);
                    }
                }
            };
        });

    }

    
}
