/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.org.apache.xerces.internal.impl.dv.xs.TypeValidator;
import entities.Remorque;
import entities.TYPE_VEHICULE;
import static entities.TYPE_VEHICULE.semi_remorque;
import entities.Utilisateur;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.RemorqueServices;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class HomeVehiculeFXMLController implements Initializable {

    private ObservableList<TYPE_VEHICULE> observableType = FXCollections.observableArrayList(TYPE_VEHICULE.values());
    private List<Vehicule> vehicules = new VehiculeServices().afficher();

    private List<Remorque> remorques = new RemorqueServices().afficher();
    private ObservableList<Remorque> observableCRemorque = FXCollections.observableList(remorques);

    private List<String> marques = new ArrayList<>();
    private ObservableList<String> observableMarque = FXCollections.observableList(marques);

    private List<String> statuts = new ArrayList<>();
    private ObservableList<String> observableStatut = FXCollections.observableList(statuts);

    @FXML
    private ComboBox<TYPE_VEHICULE> type;
    @FXML
    private TextField annee;
    @FXML
    private TextField modele;
    @FXML
    private TextField capacite;
    @FXML
    private Button afficher;

    @FXML
    private Label lonueurL;
    @FXML
    private ComboBox<Remorque> longueurT;
    //private TextField matricule;
    @FXML
    private Button retourA;
    @FXML
    private ComboBox<String> marque;
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
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tun.setText("TUN");
        tun.setEditable(false);

        longueurT.setItems(observableCRemorque);
        type.setItems(observableType);

        lonueurL.setVisible(false);
        longueurT.setVisible(false);

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

    @FXML
    private void ajouter(ActionEvent event) {
        TYPE_VEHICULE typeV = type.getValue();
        String anneeV = annee.getText();
        //String matriculeV = matricule.getText();
        String premiersChiffresV = premiersChiffres.getText();
        String tunV = tun.getText();
        String derniersChiffresV = derniersChiffres.getText();
        String matriculeV = premiersChiffresV + tunV + derniersChiffresV;
        System.out.println(matriculeV);
        String capaciteV = capacite.getText();
        String modeleV = modele.getText();
        String marqueV = marque.getValue();
        String couleurV = couleur.getText();
        String statutV = statut.getValue();

        Utilisateur u = new Utilisateur(2, "selim", "sahli", "selimsahli2@gmail.com", "123456789", "la marsa", "123456789", "");
        Remorque r = longueurT.getValue();
        VehiculeServices vs = new VehiculeServices();
        boolean a = true;
        String regex1 = "\\d{3}";
        String regex2 = "TUN";
        String regex3 ="\\d{4}";
        for (Vehicule v : vehicules) {

            if (v.getMatricule().equals(matriculeV)) {
                a = false;
                Alert alert = new Alert(Alert.AlertType.ERROR, " Matricule existe déja ");
                alert.showAndWait();
            }
        }
        if (!premiersChiffresV.matches(regex1)) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Le 1er champ de la matricule doit contenir 3 chiffres ");
            alert.showAndWait();
        }
        if (!tunV.matches(regex2)) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Ce champs doit contenir 'TUN' ");
            alert.showAndWait();
        }
        if (!derniersChiffresV.matches(regex3)) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Le 3eme champ de la matricule doit contenir 4 chiffres  ");
            alert.showAndWait();
        }

        if (couleurV.isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une couleur");
            alert.showAndWait();
            couleur.setText("");
        }
        if (modeleV.isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir un modele");
            alert.showAndWait();
            modele.setText("");
        }
        if (marque.getSelectionModel().getSelectedIndex() == -1) {
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

        if (capacite.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une capacité");
            alert.showAndWait();

        }

        if (annee.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une annee");
            alert.showAndWait();

        }

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
        if (longueurT.isVisible() && longueurT.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une remorque");
            alert.showAndWait();
        }

        if (a == true) {
            int av = Integer.parseInt(anneeV);
            int cv = Integer.parseInt(capaciteV);
            Vehicule v = new Vehicule(av, cv, marqueV, modeleV, typeV, r, u, matriculeV, couleurV, statutV);

            vs.ajouter(v);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficherVehiculeFXML.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AfficherVehiculeFXML.fxml"));
            Parent root = loader.load();
            afficher.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
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
    private void retourA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AccueilFXML.fxml"));
            Parent root = loader.load();
            retourA.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAjoutVehiculeFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
