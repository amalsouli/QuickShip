/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AcceuilFXMLController implements Initializable {

    @FXML
    private Button categorie;
    @FXML
    private Button commande;
    @FXML
    private Button ajouter;
    @FXML
    private JFXHamburger humberger;
    @FXML
    private JFXButton button1;
    @FXML
    private JFXButton button2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void categorie(ActionEvent event) {
             try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorieFXML.fxml"));
            Parent root = loader.load();
            Scene scene = categorie.getScene();
            scene.setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void commande(ActionEvent event) {
          try {
              
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandeFXML.fxml"));
            Parent root = loader.load();
            Scene scene = commande.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
