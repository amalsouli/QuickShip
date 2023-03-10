/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.TYPE;
import Entities.maintenace;
import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Services.maintenaceService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class ModifierMaintenanceController implements Initializable {

  private ObservableList<TYPE> observableType = FXCollections.observableArrayList(TYPE.values());
    

    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<TYPE> type;
    @FXML
    private Button modifier;
  
    @FXML
    private Button afficher;
    @FXML
    private TextField id;
            maintenaceService m = new maintenaceService();


       


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        type.setItems(observableType);
        id.setDisable(true);
    }    
     

    @FXML
    private void afficher(ActionEvent event) {
         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMaintenance.fxml"));
            Parent root = loader.load();
            afficher.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        } 
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         maintenaceService ms = new maintenaceService();
       
        TYPE ty = type.getValue();
        int ide = Integer.parseInt(id.getText());
        id.setEditable(false);
        id.setDisable(true);
        Utilisateur u = new Utilisateur(1, "selim","technicien");

        java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
        maintenace m = new maintenace(ide,ty,u,datee);
        System.out.println(m);
        ms.modifier(m);
          
    }

    void setmaintenace(maintenace m) {
 String idc = String.valueOf(m.getId());
        id.setText(idc);
        LocalDate d = m.getDate().toLocalDate();
        date.setValue( d);   
    type.setValue(m.getType());}
    
}