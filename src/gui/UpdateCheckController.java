/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CheckPoint;
import Entities.Conducteur;
import Entities.Trajet;
import Entities.Vehicule;
import Services.CheckService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class UpdateCheckController implements Initializable {

    @FXML
    private TextField destination;
    @FXML
    private TextField heure;
    @FXML
    private TextField minute;
    @FXML
    private ComboBox<Trajet> date;
    @FXML
    private Button modifier;
    @FXML
    private TextField id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
      public void setCheck(String destinationn,int  heures,int minutes,Trajet t,int idi)
    {
       destination.setText(destinationn);
       heure.setText(Integer.toString(heures));
       minute.setText(Integer.toString(minutes));
       date.setValue(t);
       id.setText(Integer.toString(idi));
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
          
        Trajet t = date.getValue();
        
        String des = destination.getText();
        int ide=Integer.parseInt(id.getText());
        id.setEditable(false);
        id.setDisable(true);
        int h=Integer.parseInt(heure.getText());
        int m=Integer.parseInt(minute.getText());
        CheckService s=new CheckService();
        CheckPoint p =new CheckPoint(ide,des,h,m,t);
        s.modifier_check(p);
        System.out.println(t.getConducteur().getId());
    }
    
}
