/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Remorque;
import Entities.TYPE_VEHICULE;
import Entities.Utilisateur;
import Entities.Vehicule;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import Services.RemorqueServices;
import Services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeRemorqueFXMLController implements Initializable {

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
            //TYPE_VEHICULE typeV = type.getValue();
            String longueur = longueurR.getText();
            String largeur = largeurR.getText();
            String capacite = capaciteR.getText();
            String marque = marqueR.getText();
            String couleur = couleurR.getValue();
      

            Utilisateur u = new Utilisateur(2, "selim", "admin");

            RemorqueServices rs = new RemorqueServices();
          

        boolean a = true;

        if (longueurR.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un modele");
            alert.showAndWait();
            longueurR.setText("");
        }
 if (largeurR.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une marque");
            alert.showAndWait();
            largeurR.setText("");
        }
 
        if (capaciteR.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une marque");
            alert.showAndWait();
            capaciteR.setText("");
        }
         
        if (marqueR.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une marque");
            alert.showAndWait();
            marqueR.setText("");
        }

        if (couleurR.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un type");
            alert.showAndWait();

        }

        if (!capaciteR.getText().matches("\\d+")) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une capacite valable en chiffres");
            alert.showAndWait();

        }
        if (!largeurR.getText().matches("\\d+")) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une largeur valable en chiffres");
            alert.showAndWait();

        }
         if (!longueurR.getText().matches("\\d+")) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une longueur valable en chiffres");
            alert.showAndWait();

        }
        
        int lor = Integer.parseInt(longueur);
            int lar = Integer.parseInt(largeur);
            int cr = Integer.parseInt(capacite);
            
            Remorque r = new Remorque(lor, lar, cr,couleur, marque);
        
        if (a == true) {
            rs.ajouter(r);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRemorqueFXML.fxml"));
                Parent root = loader.load();
                afficher.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRemorqueFXML.fxml"));
            Parent root = loader.load();
            retourA.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

}

