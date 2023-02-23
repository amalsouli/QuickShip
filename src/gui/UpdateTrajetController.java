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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class UpdateTrajetController implements Initializable {

    @FXML
    private Button ajout;
    @FXML
    private DatePicker date;
    @FXML
    private TextField point_dép;
    @FXML
    private ComboBox<Conducteur> conducteur;
    @FXML
    private ComboBox<Vehicule> vehicule;
     private List<Conducteur> conducteurs = new ArrayList<>();
    private List<Vehicule> vehicules = new ArrayList<>();
    private ObservableList<Conducteur> observableConducteurs = FXCollections.observableList(conducteurs);
    private ObservableList<Vehicule> observableVehicules = FXCollections.observableList(vehicules);
    @FXML
    private TextField id;
    @FXML
    private Text text1;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       observableConducteurs.add(new Conducteur(1,"selim"));
        observableVehicules.add(new Vehicule(1,"xxx"));
        observableVehicules.add(new Vehicule(3,"aaa"));
        
        conducteur.setItems(observableConducteurs);
        vehicule.setItems(observableVehicules);
        id.setDisable(true);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
         LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = date.getValue();
        
        String point = point_dép.getText();
        int ide=Integer.parseInt(id.getText());
        id.setEditable(false);
        id.setDisable(true);
        Conducteur conducteur1 = conducteur.getValue();
        Vehicule vehicule1 = vehicule.getValue();
        TrajetService ts=new TrajetService();
        java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
        
        boolean a=true;
         
       /* for(Trajet t:ts.afficher())
        {
            if(t.getDate().equals(datee)&& t.getConducteur().equals(conducteur1))
            {
                a=false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le conducteur ne peut pas avoir deux trajet à la méme date " );
                alert.showAndWait();
                
            }
            if(t.getDate().equals(datee)&& t.getVehicule().equals(vehicule1))
            {
                a=false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le conducteur ne peut pas avoir deux trajet à la méme date " );
                alert.showAndWait();
                date.setValue(currentDate);
            }
           
        }*/
        if(point_dép.getText().isEmpty()||selectedDate.isBefore(currentDate)||conducteur.getSelectionModel().getSelectedIndex() == -1||vehicule.getSelectionModel().getSelectedIndex() == -1)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez remplir ce champ: " );
                alert.showAndWait();
               
        }
        Trajet t =new Trajet(ide,datee,point,vehicule1,conducteur1);
        if(a==true)
        {
        ts.modifier(t);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listee.fxml"));
        
                        Parent root=loader.load();
                        Parent parent = loader.getRoot();
                          Scene scene = ajout.getScene();
                          scene.setRoot(parent);
        System.out.println(t.getConducteur().getId());
        }
        
    }
    public void setTrajet(String point,LocalDate datee,Vehicule V,Conducteur C,String idi)
    {
       date.setValue(datee);
       point_dép.setText(point);
       conducteur.setValue(C);
       vehicule.setValue(V);
       id.setText(idi);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
                      Parent root=loader.load();
                     Parent parent = loader.getRoot();
                            
                          Scene scene = retour.getScene();
                          scene.setRoot(parent);
    }
    
}
