/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Remorque;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
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
import services.RemorqueServices;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherRemorqueFXMLController implements Initializable {

    @FXML
    private TableView<Remorque> table;
    
    @FXML
    private TableColumn<Remorque, Button> Delete;

    RemorqueServices remorqueServ = new RemorqueServices();
    ObservableList<Remorque> listRemorque = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Remorque, Integer> identifiantR;
    @FXML
    private TableColumn<Remorque, Integer>longueurR;
    @FXML
    private TableColumn<Remorque, Integer> largeurR;
    @FXML
    private TableColumn<Remorque, Integer> capaciteR;
    @FXML
    private TableColumn<Remorque, Integer> couleurR;
    @FXML
    private TableColumn<Remorque, Integer> marqueR;
    @FXML
    private TableColumn<Remorque, Button> modifier;
    @FXML
    private Button retourR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List list = remorqueServ.afficher();
        listRemorque.addAll(list);

        identifiantR.setCellValueFactory(new PropertyValueFactory<>("id_remorque"));
        largeurR.setCellValueFactory(new PropertyValueFactory<>("largeur"));
        longueurR.setCellValueFactory(new PropertyValueFactory<>("longueur"));
        capaciteR.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        marqueR.setCellValueFactory(new PropertyValueFactory<>("marque"));
                couleurR.setCellValueFactory(new PropertyValueFactory<>("couleur"));


        this.delete();
        this.modifier();
        table.setItems(listRemorque);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeRemorqueFXMLfxml.fxml"));
            Parent root = loader.load();
            retourR.getScene().setRoot(root);
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

                            if (remorqueServ.supprimer(table.getItems().get(getIndex()))) {
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
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRemorqueFXML.fxml"));
                                Parent root = loader.load();
                                GUI.ModifierRemorqueFXMLController modifiier = loader.getController();
                                Remorque r = table.getItems().get(getIndex());
                               
                                modifiier.SetRemorque(r);
                                
                                
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
