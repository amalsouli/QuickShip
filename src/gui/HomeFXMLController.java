/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Conducteur;
import Entities.Trajet;
import Entities.Vehicule;
import Services.SMSUtil;
import Services.Send_Mail;
import Services.TrajetService;
import Services.VehiculeServices;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class HomeFXMLController implements Initializable {
     
    private List<Conducteur> conducteurs = new ArrayList<>();
    private List<Vehicule> vehicules = new VehiculeServices().afficher();
    private ObservableList<Conducteur> observableConducteurs = FXCollections.observableList(conducteurs);
    private ObservableList<Vehicule> observableVehicules = FXCollections.observableList(vehicules);

    @FXML
    private DatePicker date;
    @FXML
    private TextField point_dép;
    @FXML
    private ComboBox<String> vehicule;
    @FXML
    private ComboBox<Conducteur> conducteur;
    @FXML
    private Button ajout;
    @FXML
    private Label err1;
    @FXML
    private Label err11;
    @FXML
    private Label err111;
    @FXML
    private Text text;
    @FXML
    private Button btn2;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observableConducteurs.add(new Conducteur(1,"selim","54076532","selimsahli2000@gmail.com"));
        
        ObservableList<String> listeCategorie = observableVehicules.stream()
                .map(v->v.getMatricule())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        conducteur.setItems(observableConducteurs);
        vehicule.setItems(listeCategorie);
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException, Exception {
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = date.getValue();
        //java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
        String point = point_dép.getText();
        Conducteur conducteur1 = conducteur.getValue();
                    String cat = vehicule.getValue();
            Vehicule catt = null;
            for (Vehicule categorie : observableVehicules) {
                if (categorie.getMatricule().equals(cat)) {
                    catt = categorie;
                    
                }
            }
        
        TrajetService ts=new TrajetService();
        boolean a=true;
        
        if(date.getValue()!=null)
        {
        for(Trajet t:ts.afficher())
        {
            if(t.getDate().toString().equals(date.getValue().toString())&& t.getConducteur().equals(conducteur1))
            {
                a=false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le conducteur ne peut pas avoir deux trajet à la méme date " );
                alert.showAndWait();
                
            }
            if(t.getDate().toString().equals(date.getValue().toString())&& t.getVehicule().equals(catt))
            {
                a=false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Le conducteur ne peut pas avoir deux trajet à la méme date " );
                alert.showAndWait();
                date.setValue(currentDate);
            }
           
        }
        }
        if(point_dép.getText().isEmpty() ||conducteur.getSelectionModel().getSelectedIndex() == -1||vehicule.getSelectionModel().getSelectedIndex() == -1 ||date.getValue()==null)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez remplir ce champ: " );
                alert.showAndWait();
               
        }
        if(selectedDate.isBefore(currentDate))
        {
           a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez resaisir la date: " );
                alert.showAndWait();     
        }
       /*if(conducteur.getSelectionModel().getSelectedIndex() == -1)
        {
          a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir un conducteur: " );
                alert.showAndWait();      
        }
       if(vehicule.getSelectionModel().getSelectedIndex() == -1)
        {
          a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir un véhicule " );
                alert.showAndWait();
                
        }*/
       
        
        //Trajet t =new Trajet(datee,point,vehicule1,conducteur1);
        if(a==true)
        {
            java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
            Trajet t =new Trajet(datee,point,catt,conducteur1);
            ts.ajouter(t);
            //SMSUtil.sendSMS("+216"+conducteur1.getTel(),"Vous avez un trajet à faire de "+point +"le "+datee);
          //  Send_Mail.sendEmail(conducteur1.getEmail(), "Trajet", "Bonjour,Vous avez un trajet le "+datee+"de "+point);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("listee.fxml"));
          Parent root=loader.load();
          Parent parent = loader.getRoot();
                            
                          Scene scene = ajout.getScene();
                          scene.setRoot(parent);
        }
        
        
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listee.fxml"));
                      Parent root=loader.load();
                     Parent parent = loader.getRoot();
                            
                          Scene scene = btn2.getScene();
                          scene.setRoot(parent);
    }
    
}
