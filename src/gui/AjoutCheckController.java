/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CheckPoint;
import Entities.Trajet;
import Services.CheckService;
import Services.TrajetService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class AjoutCheckController implements Initializable {

    @FXML
    private TextField destination;
    @FXML
    private TextField heure;
    @FXML
    private TextField minute;
    @FXML
    private ComboBox<Trajet> date;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<Trajet> dates = FXCollections.observableArrayList();
    CheckService s=new CheckService();
    TrajetService ts=new TrajetService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            for(Trajet t:ts.afficher())
            {
                 dates.add(t);
            }
                
                } catch (SQLException ex) {
            Logger.getLogger(AjoutCheckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        date.setItems(dates);
        
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        boolean a=true;
        int h=Integer.parseInt(heure.getText());
        int m=Integer.parseInt(minute.getText());
        if(h>24 || h<0)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez resaisir l'heure: " + heure.getText());
                alert.showAndWait();
                heure.setText("");
        }
         if(m>60 || m<0)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez resaisir l minute: " + minute.getText());
                alert.showAndWait();
                minute.setText("");
        }
         if(date.getSelectionModel().getSelectedIndex() == -1)
        {
              a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez saisir un trajet " );
                alert.showAndWait();
                
        }
        
        String des=destination.getText();
        Trajet t=date.getValue();
               
       CheckPoint c=new CheckPoint(des,h,m,t);
                    
                    if(a==true)
                    {
                    s.ajouter(c);
                    System.out.println("Ajout avec succés");
                    System.out.println(c);
                    }
                     
                 
                 
            
        
    }
    
}
