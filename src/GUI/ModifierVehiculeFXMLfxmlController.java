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
import javafx.scene.control.Alert;
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

    private List<String> marques = new ArrayList<>();
    private ObservableList<String> observableMarque = FXCollections.observableList(marques);

    private List<String> statuts = new ArrayList<>();
    private ObservableList<String> observableStatut = FXCollections.observableList(statuts);

    @FXML
    private TextField annee;
    @FXML
    private TextField modele;
    @FXML
    private ComboBox<String> marque;
    @FXML
    private TextField capacite;
    @FXML
    private ComboBox<TYPE_VEHICULE> type;
    @FXML
    private Label lonueurL;

    //comboboxRemorque
    @FXML
    private ComboBox<Remorque> longueurT;
    private int id;
    @FXML
    private Button modiier;

    private Utilisateur user;
    //private TextField matricule;
    @FXML
    private TextField couleur;
    @FXML
    private ComboBox<String> statut;
    @FXML
    private TextField premiersChiffres;
    @FXML
    private TextField derniersChiffres;
    @FXML
    private TextField tun;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setItems(observableType);
        longueurT.setItems(observableCRemorque);

        observableMarque.add("KIA");
        observableMarque.add("FORD");
        observableMarque.add("RENAULT");
        observableMarque.add("CITROEN");
        marque.setItems(observableMarque);

        observableStatut.add("DISPONIBLE");
        observableStatut.add("NON DISPONIBLE");
        observableStatut.add("EN PANNE");
        statut.setItems(observableStatut);

    }

    public void SetVehicule(Vehicule v) {
        id = v.getId();
        String anneeV = String.valueOf(v.getAnnee());
        annee.setText(anneeV);
        modele.setText(v.getModele());
        marque.setValue(v.getMarque());
        couleur.setText(v.getCouleur());
        String capaciteV = String.valueOf(v.getCapacite());
        capacite.setText(capaciteV);
        type.setValue(v.getType());
        String matricule = v.getMatricule();
        String premiersChiffre = matricule.substring(0, 3);
        String tunn = matricule.substring(3, 6);
        String derniersChiffre = matricule.substring(6);
        premiersChiffres.setText(premiersChiffre);
        tun.setText(tunn);
        derniersChiffres.setText(derniersChiffre);
        // premiersChiffres.setText(v);
        // tun.setText(tunV);
        // derniersChiffres.setText(derniersChiffresV);
        // matriculeV = premiersChiffresV + tunV + derniersChiffresV;
        statut.setValue(v.getStatut());

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

            TYPE_VEHICULE typeV = type.getValue();
            String anneeV = annee.getText();
            String capaciteV = capacite.getText();
            String modeleV = modele.getText();
            String marqueV = marque.getValue();
            String couleurV = couleur.getText();
            String matriculeV = premiersChiffres.getText() + tun.getText() + derniersChiffres.getText();
          /*  String premiersChiffresV = premiersChiffres.getText();
            String tunV = tun.getText();
            String derniersChiffresV = derniersChiffres.getText();
            //String matriculeV = premiersChiffresV + tunV + derniersChiffresV;*/
            String statutV = statut.getValue();
            Remorque remorqueV = longueurT.getValue();

            //CONTROLE DE SAISI
            boolean a = true;
            String regex1 = "\\d{3}";
            String regex2 = "TUN";
            String regex3 = "\\d{4}";
         for (Vehicule v : vehicules) {

            if (v.getMatricule().equals(matriculeV)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, " Matricule existe déja ");
                alert.showAndWait();
               
            }

        }
            if (!premiersChiffres.getText().matches(regex1)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Le 1er champ de la matricule doit contenir 3 chiffres ");
                alert.showAndWait();
            }
            if (!tun.getText().matches(regex2)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Ce champs doit contenir 'TUN' ");
                alert.showAndWait();
            }
            if (!derniersChiffres.getText().matches(regex3)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Le 3eme champ de la matricule doit contenir 4 chiffres  ");
                alert.showAndWait();
            }
            /*  if (marque.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une marque");
            alert.showAndWait();
        }
            
        if (type.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un type");
            alert.showAndWait();
        }
        
         if (statut.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un type");
            alert.showAndWait();
        }
            if (longueurT.isVisible() && longueurT.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une remorque");
            alert.showAndWait();
        }
       
             */

            if (!capacite.getText().matches("\\d+")) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une capacite valable en chiffres");
                alert.showAndWait();

            }
            if (!annee.getText().matches("\\d{4}")) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une annee valable en chiffres");
                alert.showAndWait();

            }

            if (a) {
                System.out.println(a);
                int av = Integer.parseInt(anneeV);

                int cv = Integer.parseInt(capaciteV);

                Vehicule v = new Vehicule(id, av, cv, marqueV, modeleV, typeV, remorqueV, user, matriculeV, couleurV, statutV);

                VehiculeServices vs = new VehiculeServices();

                System.out.println(v);
                vs.modifier(v);
                Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherVehiculeFXML.fxml"));
                Scene scene = modiier.getScene();
                scene.setRoot(nouvelleVue);

                if (typeV == TYPE_VEHICULE.semi_remorque) {
                    longueurT.setVisible(true);
                    lonueurL.setVisible(true);

                } else {
                    longueurT.setVisible(false);
                    lonueurL.setVisible(false);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ModifierVehiculeFXMLfxmlController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CONTROLE DE SAISI
        // boolean a = true;
        //String regex = "\\d{3}TUN\\d{4}";

        /*if (!matriculeV.matches(regex)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une matricule valide (***TUN****)");
                alert.showAndWait();
                matricule.setText("");
            }*/
 /* if (couleurV.isEmpty()) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une couleur");
                alert.showAndWait();
                couleur.setText("");
            }*/
 /* if (modeleV.isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un modele");
            alert.showAndWait();
            modele.setText("");
        }*/
 /* if (a == true) {
            vs.modifier(v);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficherVehiculeFXML.fxml"));
                Parent root = loader.load();
                
                modiier.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        }*/
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

    @FXML
    private void check_remorque(DragEvent event) {
    }

}
