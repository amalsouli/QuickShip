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
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class HomeFXMLController implements Initializable {
     
    private List<Conducteur> conducteurs = new ArrayList<>();
    private List<Vehicule> vehicules = new ArrayList<>();
    private ObservableList<Conducteur> observableConducteurs = FXCollections.observableList(conducteurs);
    private ObservableList<Vehicule> observableVehicules = FXCollections.observableList(vehicules);

    @FXML
    private DatePicker date;
    @FXML
    private TextField point_dép;
    @FXML
    private ComboBox<Vehicule> vehicule;
    @FXML
    private ComboBox<Conducteur> conducteur;
    @FXML
    private Button ajout;
    @FXML
    private Label err;
    @FXML
    private Label err1;
    @FXML
    private Label err11;
    @FXML
    private Label err111;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observableConducteurs.add(new Conducteur(1,"selim"));
        observableVehicules.add(new Vehicule(1,"xx"));
        
        conducteur.setItems(observableConducteurs);
        vehicule.setItems(observableVehicules);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = date.getValue();
        
        String point = point_dép.getText();
        Conducteur conducteur1 = conducteur.getValue();
        Vehicule vehicule1 = vehicule.getValue();
        TrajetService ts=new TrajetService();
        if(point_dép.getText().isEmpty())
        {
            err1.setText("Tous les champs sont obligatoires !");
        }
        if(selectedDate.isBefore(currentDate))
        {
           err.setText("Veuillez choisir une date valide");
        }
       if(conducteur.getSelectionModel().getSelectedIndex() == -1)
        {
           err11.setText("Un conducteur doit étre selectionné");
        }
       if(vehicule.getSelectionModel().getSelectedIndex() == -1)
        {
           err111.setText("Un vehicule doit étre selectionné");
        }
        java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
        Trajet t =new Trajet(datee,point,vehicule1,conducteur1);
        ts.ajouter(t);
        
    }
    
}
