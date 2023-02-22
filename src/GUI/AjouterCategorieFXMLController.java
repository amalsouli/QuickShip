/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import entites.STATUS_COMMANDE;
import entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.CategorieService;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCategorieFXMLController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private TextField nom;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {          
            String n = nom.getText();
            String des = description.getText();
            
            CategorieService catSer = new CategorieService();
            if (nom.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Titre de l'information");
                alert.setContentText("Le contenu de l'information.");
            }
            
            if (description.getText().isEmpty()) {
                //  err12.setText("Tous les champs sont obligatoires !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Titre de l'information");
                alert.setContentText("Le contenu de l'information.");
            }           
            Categorie c = new Categorie(n,des);
            catSer.ajouter(c);
           /* Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
            Scene scene = ajouter.getScene();
            scene.setRoot(nouvelleVue);*/
        
    }
    
}
