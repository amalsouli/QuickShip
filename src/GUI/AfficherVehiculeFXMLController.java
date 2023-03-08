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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    /*    @FXML
    private TableColumn<Vehicule, Integer> id;*/
    @FXML
    private TableColumn<Vehicule, String> couleur;
    @FXML
    private TableColumn<Vehicule, Integer> idVehicule;
    @FXML
    private Button AjouterV;
    @FXML
    private TextField recherchertxt;
    @FXML
    private TableColumn<Vehicule, String> statut;

    private final SimpleStringProperty filtre = new SimpleStringProperty("");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Vehicule> list = vehiculeServ.afficher();
        listVehicule.addAll(list);
        idVehicule.setCellValueFactory(new PropertyValueFactory<>("id"));
        matricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        couleur.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        modele.setCellValueFactory(new PropertyValueFactory<>("modele"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        remorque.setCellValueFactory(new PropertyValueFactory<>("remorque"));
        statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        this.delete();
        this.modifier();
        /* vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));*/
        table.setItems(listVehicule);
        recherchertxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
        Predicate<Vehicule> filtrePersonnes = vehicule
                -> filtre.get().isEmpty()
                || vehicule.getModele().toLowerCase().contains(filtre.get().toLowerCase())
                || vehicule.getMarque().toLowerCase().contains(filtre.get().toLowerCase())
                || vehicule.getMatricule().toLowerCase().contains(filtre.get().toLowerCase())
                || vehicule.getCouleur().toLowerCase().contains(filtre.get().toLowerCase())
                || (vehicule.getAnnee() + "").contains(filtre.get().toLowerCase())
                || (vehicule.getCapacite() + "").contains(filtre.get().toLowerCase())
                || (vehicule.getType().toString()).contains(filtre.get().toLowerCase())
                || (vehicule.getStatut().toString()).contains(filtre.get().toLowerCase());

        listVehicule.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
        listVehicule.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        });
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

                            if (vehiculeServ.supprimer(table.getItems().get(getIndex()))) {
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

    @FXML
    private void AjouterV(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAjoutVehiculeFXML.fxml"));
            Parent root = loader.load();
            AjouterV.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

}
