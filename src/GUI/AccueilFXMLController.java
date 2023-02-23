/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AccueilFXMLController implements Initializable {

    @FXML
    private Button ajouterV;
    @FXML
    private Button ajouterR;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterV(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeVehiculeFXML.fxml"));
            Parent root = loader.load();
            ajouterV.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void ajouterR(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeRemorqueFXMLfxml.fxml"));
            Parent root = loader.load();
            ajouterR.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
}
