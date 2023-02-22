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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class ListController implements Initializable {
 private List<Conducteur> conducteurs = new ArrayList<>();
    private List<Vehicule> vehicules = new ArrayList<>();
    private ObservableList<Conducteur> observableConducteurs = FXCollections.observableList(conducteurs);
    private ObservableList<Vehicule> observableVehicules = FXCollections.observableList(vehicules);
     @FXML
    private TableColumn<Trajet, Integer> id;
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
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    TrajetService s=new TrajetService();
    
    ObservableList<Trajet>  StudentList = FXCollections.observableArrayList();
    @FXML
    private TextField text1;
    @FXML
    private DatePicker text2;
    @FXML
    private ComboBox<Vehicule> text3;
    @FXML
    private ComboBox<Conducteur> text4;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observableConducteurs.add(new Conducteur(1,"selim"));
        observableVehicules.add(new Vehicule(1,"xxx"));
        observableVehicules.add(new Vehicule(3,"aaa"));
        text4.setItems(observableConducteurs);
        text3.setItems(observableVehicules);
        try {
            List l=s.afficher();
            StudentList.addAll(l);
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        point.setCellValueFactory(new PropertyValueFactory<>("Point_dep"));
        vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));
        trajettable.setItems(StudentList);
            
        } catch (SQLException ex) {
            Logger.getLogger(ListtrajetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        trajettable.setOnMouseClicked(e->{
			printReport();
		});
        supprimer.setOnMouseClicked(e->{
            try {
                supprimer();
            } catch (SQLException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        modifier.setOnMouseClicked(e->{
            try {
                modifier();
            } catch (SQLException ex) {
                Logger.getLogger(ListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    } 
    
    public void printReport()
    {
        Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        text1.setText(trajet.getPoint_dep());
        text2.setValue(trajet.getDate().toLocalDate());
        text3.setValue(trajet.getVehicule());
        text4.setValue(trajet.getConducteur());
    }
    public void supprimer() throws SQLException
    {
        Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        s.Supprimer_trajet(trajet.getId());
    }
    public void modifier() throws SQLException
    {
        Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        LocalDate currentDate = LocalDate.now();
        LocalDate selectedDate = text2.getValue();
        
        String point = text1.getText();
        Conducteur conducteur1 = text4.getValue();
        Vehicule vehicule1 = text3.getValue();
          java.sql.Date datee = java.sql.Date.valueOf(text2.getValue());
        Trajet ts=new Trajet(trajet.getId(),datee,point,vehicule1,conducteur1);
        
        s.modifier(ts);
        System.out.println(trajet);
    }
       
   
    
}
