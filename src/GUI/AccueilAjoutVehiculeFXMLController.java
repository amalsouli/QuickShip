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
public class AccueilAjoutVehiculeFXMLController implements Initializable {

    @FXML
    private Button AjouterV1;
    @FXML
    private Button AjouterVp;
    @FXML
    private Button retour;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterV1(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/HomeVehiculeFXML.fxml"));
                Parent root = loader.load();
                AjouterV1.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
    }

    @FXML
    private void AjouterVp(ActionEvent event) {
         try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ImportEnMasseFXML.fxml"));
                Parent root = loader.load();
                AjouterVp.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherVehiculeFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }



   
    
}
