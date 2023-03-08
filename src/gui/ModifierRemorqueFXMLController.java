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
public class ModifierRemorqueFXMLController implements Initializable {

    private List<String> couleurs = new ArrayList<>();
    private ObservableList<String> observableCouleurs = FXCollections.observableList(couleurs);

    @FXML
    private Button modiier;

    @FXML
    private TextField capacitee;
    @FXML
    private TextField largeurr;
    @FXML
    private TextField longueurr;
    @FXML
    private ComboBox<String> couleurr;
    @FXML
    private TextField marquee;
    private int idd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observableCouleurs.add("ROUGE");
        observableCouleurs.add("VERT");
        observableCouleurs.add("JAUNE");
        observableCouleurs.add("rouge");
       
        couleurr.setItems(observableCouleurs);
    }

    @FXML
    private void modiier(ActionEvent event) {
        try {
            //int ide = Integer.parseInt(idd.getText());

            String longueurR = longueurr.getText();
            String largeurR = largeurr.getText();
            String capaciteR = capacitee.getText();
            String marqueR = marquee.getText();
            String couleurR = couleurr.getValue();
            boolean a = true;

            if (longueurr.getText().isEmpty() || largeurr.getText().isEmpty() || capacitee.getText().isEmpty() || marquee.getText().isEmpty() || couleurr.getValue().isEmpty()) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un modele");
                alert.showAndWait();
            }
            if (!capacitee.getText().matches("\\d+")) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une capacite valable en chiffres");
                alert.showAndWait();
                capacitee.setText("");
            }
            if (!largeurr.getText().matches("\\d+")) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une largeur valable en chiffres");
                alert.showAndWait();
                largeurr.setText("");
            }
            if (!longueurr.getText().matches("\\d+")) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une longueurr valable en chiffres");
                alert.showAndWait();
                longueurr.setText("");

            }
            if (a) {
                int lor = Integer.parseInt(longueurR);
                int lar = Integer.parseInt(largeurR);
                int cr = Integer.parseInt(capaciteR);

                RemorqueServices rs = new RemorqueServices();
                Remorque r = new Remorque(idd, lor, lar, cr, couleurR, marqueR);

                System.out.println(r);
                rs.modifier(r);
                //affichage apres modifier
                Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherRemorqueFXML.fxml"));
                Scene scene = modiier.getScene();
                scene.setRoot(nouvelleVue);
            }
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void SetRemorque(Remorque r) {
        idd = r.getId_remorque();
        String longg = String.valueOf(r.getLongueur());
        longueurr.setText(longg);
        String largeurrrr = String.valueOf(r.getLargeur());
        largeurr.setText(largeurrrr);
        marquee.setText(r.getMarque());
        String capaciteR = String.valueOf(r.getCapacite());
        capacitee.setText(capaciteR);
        couleurr.setValue(r.getCouleur());

    }

    @FXML
    private void check_remorque(DragEvent event) {
    }

    @FXML
    private void check_remorque(MouseEvent event) {
    }

    @FXML
    private void check_remorque(ActionEvent event) {

    }

}
