/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Remorque;
import entities.TYPE_VEHICULE;
import entities.Utilisateur;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import services.RemorqueServices;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeRemorqueFXMLfxmlController implements Initializable {

    private List<String> couleurs = new ArrayList<>();
    private ObservableList<String> observableCouleurs = FXCollections.observableList(couleurs);
   
    @FXML
    private Button afficher;
    @FXML
    private Button ajouter;
    @FXML
    private TextField capaciteR;
    @FXML
    private TextField marqueR;
    @FXML
    private TextField longueurR;
    @FXML
    private TextField largeurR;
    @FXML
    private ComboBox<String> couleurR;
    @FXML
    private Button retourA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observableCouleurs.add("ROUGE");
        observableCouleurs.add("VERT");
        observableCouleurs.add("JAUNE");
        couleurR.setItems(observableCouleurs);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            //TYPE_VEHICULE typeV = type.getValue();
            String longueur = longueurR.getText();
            String largeur = largeurR.getText();
            String capacite = capaciteR.getText();
            String marque = marqueR.getText();
            String couleur = couleurR.getValue();
            int lor = Integer.parseInt(longueur);
            int lar = Integer.parseInt(largeur);
            int cr = Integer.parseInt(capacite);
            
            Utilisateur u = new Utilisateur(2, "selim", "sahli", "selimsahli2@gmail.com", "123456789", "la marsa", "123456789", "");
            
            RemorqueServices rs = new RemorqueServices();
            Remorque r = new Remorque(lor, lar, cr,couleur, marque);
            rs.ajouter(r);
            
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherRemorqueFXML.fxml"));
            Scene scene = ajouter.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(HomeRemorqueFXMLfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRemorqueFXML.fxml"));
            Parent root = loader.load();
            afficher.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }
    
    @FXML
     private void retourA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilFXML.fxml"));
            Parent root = loader.load();
            retourA.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

}
