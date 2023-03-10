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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class AjoutCheckController implements Initializable {

    private TextField destination;
    private TextField heure;
    private TextField minute;
    private ComboBox<String> date;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<Trajet> dates = FXCollections.observableArrayList();
    CheckService s=new CheckService();
    TrajetService ts=new TrajetService();
    @FXML
    private Button btn2;
    @FXML
    private WebView web;
    private  WebEngine webEngine;
    @FXML
    private TextField adrr;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        webEngine = web.getEngine();
        webEngine.load("https://www.openstreetmap.org/");

        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                web.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    double latitude = getLatitudeFromEvent(event);
                    double longitude = getLongitudeFromEvent(event);
                    String locationInfo = getLocationInfo(latitude, longitude);
                    System.out.println(locationInfo);
                    adrr.setText(locationInfo);
                });
            }
        });
        
      /*  try {
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
        */
        
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {
      /*  boolean a=true;
      
       
        if(destination.getText().isEmpty()|| heure.getText().isEmpty()|| minute.getText().isEmpty()||date.getSelectionModel().getSelectedIndex() == -1)
        {
             a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoire " );
                alert.showAndWait();
               
        }
        if(heure.getText()!=null)
        {
              int h=Integer.parseInt(heure.getText());
            
               if(h>24 || h<0)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez resaisir l'heure: " + heure.getText());
                alert.showAndWait();
                heure.setText("");
        }
        }
        if(minute.getText()!=null)
        {
             int m=Integer.parseInt(minute.getText());
           if(m>60 || m<0)
        {
            a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez resaisir l minute: " + minute.getText());
                alert.showAndWait();
                minute.setText("");
        }  
        }
        
         if(date.getSelectionModel().getSelectedIndex() == -1)
        {
              a=false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez saisir un trajet " );
                alert.showAndWait();
                
        }
        
        String des=destination.getText();
        String t=date.getValue();
        Trajet x=null;
        for(Trajet tr : dates)
        {
            if(tr.getDate().toString().equals(t))
            {
                x=tr;
            }
            
        }
        
         int m=Integer.parseInt(minute.getText());
          int h=Integer.parseInt(heure.getText());
         */
       CheckPoint c=new CheckPoint(adrr.getText());
                    
                    
                    s.ajouter(c);
                      System.out.println("Ajout avec succés");
                      //System.out.println(c);
                     /* FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommandeFXML.fxml"));
                      Parent root=loader.load();
                      Parent parent = loader.getRoot();
                            
                      Scene scene = ajouter.getScene();
                      scene.setRoot(parent);*/
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ajout avec succés" );
                alert.showAndWait();
                     
                 
                 
          
        
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("acceuil.fxml"));
                      Parent root=loader.load();
                     Parent parent = loader.getRoot();
                            
                          Scene scene = btn2.getScene();
                          scene.setRoot(parent);
        
    }
      private  double getLatitudeFromEvent(MouseEvent event) {
        double y = event.getY();
        double latitude = (180 / Math.PI) * Math.atan(Math.sinh(Math.PI * (1 - 2 * y / web.getHeight())));
        return latitude;
    }

    private  double getLongitudeFromEvent(MouseEvent event) {
       
        double x = event.getX();
        double longitude = (x / web.getWidth() * 360.0) - 180.0;
        return longitude;
    }
    private  String getLocationInfo(double latitude, double longitude) {
    String locationInfo = "";
    try {
        String url = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat=" + latitude + "&lon=" + longitude+"&accept-language=fr-FR";
        URLConnection conn = new URL(url).openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JSONObject jsonObject = new JSONObject(sb.toString());
        String displayName = jsonObject.optString("display_name", "");
        JSONArray addressArray = jsonObject.optJSONArray("address");
        if (addressArray != null) {
            String country = addressArray.optString(addressArray.length() - 1, "");
            String city = addressArray.optString(addressArray.length() - 2, "");
            String state = addressArray.optString(addressArray.length() - 3, "");
            locationInfo = "Location: " + displayName + "\n" +
                    "Country: " + country + "\n" +
                    "State: " + state + "\n" +
                    "City: " + city;
        } else {
            locationInfo = "Location: " + displayName + "\n" +
                    "No address information available.";
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return locationInfo;
    }
    
}
