/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Remorque;
import Entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.RemorqueServices;
import Services.VehiculeServices;

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
    @FXML
    private Button ajouterR;
    @FXML
    private TextField recherchertxtRemorque;
    private final SimpleStringProperty filtre = new SimpleStringProperty("");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Remorque> list = remorqueServ.afficher();
        listRemorque.addAll(list);

       // identifiantR.setCellValueFactory(new PropertyValueFactory<>("id_remorque"));
        largeurR.setCellValueFactory(new PropertyValueFactory<>("largeur"));
        longueurR.setCellValueFactory(new PropertyValueFactory<>("longueur"));
        capaciteR.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        marqueR.setCellValueFactory(new PropertyValueFactory<>("marque"));
        couleurR.setCellValueFactory(new PropertyValueFactory<>("couleur"));


        this.delete();
        this.modifier();
        table.setItems(listRemorque);
        
        recherchertxtRemorque.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
        Predicate<Remorque> filtrePersonnes = remorque
                -> filtre.get().isEmpty()
                || (remorque.getLongueur() + "").contains(filtre.get().toLowerCase())
                || (remorque.getLargeur() + "").contains(filtre.get().toLowerCase())
                || (remorque.getCapacite() + "").contains(filtre.get().toLowerCase())       
                || remorque.getMarque().toLowerCase().contains(filtre.get().toLowerCase())              
                ||(remorque.getCouleur()).toLowerCase().contains(filtre.get().toLowerCase());
        listRemorque.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
            listRemorque.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        });
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilFXML.fxml"));
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
                                ModifierRemorqueFXMLController modifiier = loader.getController();
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

    @FXML
    private void ajouterR(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/HomeRemorqueFXMLfxml.fxml"));
                Parent root = loader.load();
                ajouterR.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
    }

}
