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
import Services.TrajetService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    private ComboBox<String> date;
    @FXML
    private Button modifier;
    @FXML
    private TextField id;
    @FXML
    private Text text1;
    @FXML
    private Button retour;
    private ObservableList<Trajet> dates = FXCollections.observableArrayList();
    TrajetService ts =new TrajetService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
         try {
            // TODO
            for(Trajet t:ts.afficher())
            {
                 dates.add(t);
            }
                
                } catch (SQLException ex) {
            Logger.getLogger(AjoutCheckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> li=dates.stream().map(c->c.getDate().toString()).collect(Collectors.toCollection(FXCollections::observableArrayList));
        date.setItems(li);
        
        // TODO
    }  
    
    
      public void setCheck(String destinationn,int  heures,int minutes,String t,int idi)
    {
       destination.setText(destinationn);
       heure.setText(Integer.toString(heures));
       minute.setText(Integer.toString(minutes));
      
       date.setValue(t);
       id.setText(Integer.toString(idi));
    }
      public void setCheck1(CheckPoint c)
      {
           destination.setText(c.getAdresse());
           id.setText(Integer.toString(c.getId()));
           heure.setText(Integer.toString(c.getHeure()));
       minute.setText(Integer.toString(c.getMin()));
      if(c.getTrajet().getDate()!= null){
       date.setValue(c.getTrajet().getDate().toString());
       
      }else{
    date.setValue("");
}
      }
    @FXML
    private void modifier(ActionEvent event) throws SQLException, IOException {
           Trajet x=null;
        for(Trajet tr : dates)
        {
            if(tr.getDate().toString().equals(date.getValue()))
            {
                x=tr;
            }
            
        }
        
      
        
        String des = destination.getText();
        int ide=Integer.parseInt(id.getText());
        id.setEditable(false);
        id.setDisable(true);
        int h=Integer.parseInt(heure.getText());
        int m=Integer.parseInt(minute.getText());
        CheckService s=new CheckService();
        CheckPoint p =new CheckPoint(ide,des,h,m,x);
        s.modifier_check(p);
        System.out.println(x.getConducteur().getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("checkpoint_list.fxml"));
          Parent root=loader.load();
          Parent parent = loader.getRoot();
         Scene scene = modifier.getScene();
          scene.setRoot(parent);
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkpoint_list.fxml"));
                      Parent root=loader.load();
                     Parent parent = loader.getRoot();
                            
                          Scene scene = retour.getScene();
                          scene.setRoot(parent);
    }
    
}
