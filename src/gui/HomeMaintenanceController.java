/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.maintenaceService;
import Entities.maintenace;
import Entities.TYPE;
import Entities.Utilisateur;
import Entities.Vehicule;
import Services.VehiculeServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class HomeMaintenanceController implements Initializable {

    private ObservableList<TYPE> observableType = FXCollections.observableArrayList(TYPE.values());

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<TYPE> type;
    @FXML
    private Button ajouter;

    @FXML
    private Button afficher;
    @FXML
    private Button acceuil;
    @FXML
    private ComboBox<String> vehicule;
    private List<Vehicule> vehicules = new VehiculeServices().afficher();
    
    private ObservableList<Vehicule> observableVehicules = FXCollections.observableList(vehicules);
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        type.setItems(observableType);
        ObservableList<String> listeCategorie = observableVehicules.stream()
                .map(v->v.getMatricule())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        vehicule.setItems(listeCategorie);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {

            LocalDate currentDate = LocalDate.now();
            LocalDate selectedDate = date.getValue();
            TYPE typeM = (TYPE) type.getValue();
            Utilisateur u = new Utilisateur(1, "selim","admin");

            maintenaceService ms = new maintenaceService();
                 String cat = vehicule.getValue();
            Vehicule catt = null;
            for (Vehicule categorie : observableVehicules) {
                if (categorie.getMatricule().equals(cat)) {
                    catt = categorie;
                    
                }
            }

            if (type.getSelectionModel().getSelectedIndex()==-1)
                      {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir un type: ");
                alert.showAndWait();

            }

            if (selectedDate.isBefore(currentDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une date: ");
                alert.showAndWait();;

            }
                        java.sql.Date datee = java.sql.Date.valueOf(date.getValue());

                        maintenace M = new maintenace(typeM, u, datee,catt);

            ms.ajouter(M);
        } catch (SQLException ex) {
            Logger.getLogger(HomeMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


@FXML
        private void afficher(ActionEvent event) throws IOException {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMaintenance.fxml"));
            Parent root = loader.load();
            afficher.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
}

    @FXML
    private void acceuilbu(ActionEvent event) {
   try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Accueil.fxml"));
            Parent root = loader.load();
            acceuil.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
        }
}
