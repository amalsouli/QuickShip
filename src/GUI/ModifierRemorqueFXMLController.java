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
    @FXML
    private TextField idd;
    private Label longueur;
    private Label largeur;
    private Label couleur;
    private Label marque;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        observableCouleurs.add("ROUGE");
        observableCouleurs.add("VERT");
        observableCouleurs.add("JAUNE");
        couleurr.setItems(observableCouleurs);
    }

    @FXML
    private void modiier(ActionEvent event) {
        try {
               int ide = Integer.parseInt(idd.getText());
            idd.setEditable(false);
            idd.setDisable(true);
            String longueurR = longueurr.getText();
            String largeurR = largeurr.getText();
            String capaciteR = capacitee.getText();
            String marqueR = marquee.getText();
            String couleurR = couleurr.getValue();

            int lor = Integer.parseInt(longueurR);
            int lar = Integer.parseInt(largeurR);
            int cr = Integer.parseInt(capaciteR);


            RemorqueServices rs = new RemorqueServices();
            Remorque r = new Remorque(ide,lor, lar, cr,couleurR, marqueR);

            System.out.println(r);
            rs.modifier(r);
            //affichage apres modifier
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherRemorqueFXML.fxml"));
            Scene scene = modiier.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeFXMLfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    void SetRemorque(Remorque r) {
        String idv = String.valueOf(r.getId_remorque());
        idd.setText(idv);
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
