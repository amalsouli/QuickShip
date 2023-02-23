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
public class ModifierVehiculeFXMLfxmlController implements Initializable {

    private ObservableList<TYPE_VEHICULE> observableType = FXCollections.observableArrayList(TYPE_VEHICULE.values());
    private List<Vehicule> vehicules = new VehiculeServices().afficher();

    private List<Remorque> remorques = new RemorqueServices().afficher();

    private ObservableList<Remorque> observableCRemorque = FXCollections.observableList(remorques);

    @FXML
    private TextField annee;
    @FXML
    private TextField modele;
    @FXML
    private TextField marque;
    @FXML
    private TextField capacite;
    @FXML
    private ComboBox<TYPE_VEHICULE> type;
    @FXML
    private Label lonueurL;

    //comboboxRemorque
    @FXML
    private ComboBox<Remorque> longueurT;
    @FXML
    private TextField id;
    @FXML
    private Button modiier;

    private Utilisateur user;
    @FXML
    private TextField matricule;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setItems(observableType);
        longueurT.setItems(observableCRemorque);

        id.setDisable(true);

    }

    public void SetVehicule(Vehicule v) {
        String idv = String.valueOf(v.getId());
        id.setText(idv);
        String anneeV = String.valueOf(v.getAnnee());
        annee.setText(anneeV);
        modele.setText(v.getModele());
        marque.setText(v.getMarque());
        String capaciteV = String.valueOf(v.getCapacite());
        capacite.setText(capaciteV);
        type.setValue(v.getType());
        matricule.setText(v.getMatricule());
        if (v.getType() == TYPE_VEHICULE.semi_remorque) {
            longueurT.setVisible(true);
            lonueurL.setVisible(true);
            longueurT.setValue(v.getRemorque());
        } else {
            longueurT.setVisible(false);
            lonueurL.setVisible(false);
        }
        user = v.getUtilisateur();
    }

    @FXML
    private void modiier(ActionEvent event) {
        try {

            int ide = Integer.parseInt(id.getText());
            id.setEditable(false);
            id.setDisable(true);
            TYPE_VEHICULE typeV = type.getValue();
            String anneeV = annee.getText();
            String capaciteV = capacite.getText();
            String modeleV = modele.getText();
            String marqueV = marque.getText();
            String matriculeV = matricule.getText();
            if (typeV == TYPE_VEHICULE.semi_remorque) {
                longueurT.setVisible(true);
                lonueurL.setVisible(true);
             
            } else {
                longueurT.setVisible(false);
                lonueurL.setVisible(false);
            }
            Remorque remorqueV = longueurT.getValue();
            int av = Integer.parseInt(anneeV);

            int cv = Integer.parseInt(capaciteV);

            Vehicule v = new Vehicule(ide, av, cv, marqueV, modeleV, typeV, remorqueV, user, matriculeV);
            VehiculeServices vs = new VehiculeServices();

            System.out.println(v);
            vs.modifier(v);
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherVehiculeFXML.fxml"));
            Scene scene = modiier.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeFXMLfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void check_remorque(DragEvent event) {
    }

    @FXML
    private void check_remorque(ActionEvent event) {
        System.out.println("nbc vxcgbfv");
        TYPE_VEHICULE typeV = type.getValue();
        if (typeV == TYPE_VEHICULE.semi_remorque) {
            lonueurL.setVisible(true);
            longueurT.setVisible(true);
        } else {
            lonueurL.setVisible(false);
            longueurT.setVisible(false);

        }

    }

    @FXML
    private void check_remorque(MouseEvent event) {
    }

}
