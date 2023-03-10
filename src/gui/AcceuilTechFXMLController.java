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
public class AcceuilTechFXMLController implements Initializable {

    @FXML
    private Button afficherV;
    @FXML
    private Button afficherM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherV(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontTechnicienFXML.fxml"));
            Parent root = loader.load();
            Scene scene = afficherV.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficherM(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMaintenance.fxml"));
            Parent root = loader.load();
            Scene scene = afficherM.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AcceuilClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
