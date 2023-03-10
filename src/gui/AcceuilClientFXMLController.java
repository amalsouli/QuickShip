/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AcceuilClientFXMLController implements Initializable {

 @FXML
    private Button commande;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void commande(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandeClient.fxml"));
            Parent root = loader.load();
            Scene scene = commande.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouter(ActionEvent event) {
          try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommandeFXML.fxml"));
            Parent root = loader.load();
            Scene scene = commande.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
