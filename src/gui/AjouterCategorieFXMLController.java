/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
import Entities.Categorie;
import Entities.CheckPoint;
import Entities.Commande;
import Entities.STATUS_COMMANDE;
import Entities.Utilisateur;
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
import Services.CategorieService;
import Services.CommandeService;

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
    private void ajouter(ActionEvent event) {          
        try {
            String n = nom.getText();
            String des = description.getText();
            
            CategorieService catSer = new CategorieService();
         
           if (nom.getText().isEmpty()|| description.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }  else{       
            Categorie c = new Categorie(n,des);
            catSer.ajouter(c);
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherCategorieFXML.fxml"));
            Scene scene = ajouter.getScene();
            scene.setRoot(nouvelleVue);}
        } catch (IOException ex) {
            Logger.getLogger(AjouterCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCategorieFXML.fxml"));
            Parent root = loader.load();
            Scene scene = retour.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

