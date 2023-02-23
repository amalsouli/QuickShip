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
    
    

    @FXML
    private ComboBox<TYPE_VEHICULE> type;
    @FXML
    private TextField annee;
    @FXML
    private TextField modele;
    @FXML
    private TextField marque;
    @FXML
    private TextField capacite;
    @FXML
    private Button afficher;

    @FXML
    private Label lonueurL;
    @FXML
    private ComboBox<Remorque> longueurT;
    @FXML
    private TextField matricule;
    @FXML
    private Button retourA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        longueurT.setItems(observableCRemorque);
        type.setItems(observableType);
        lonueurL.setVisible(false);
        longueurT.setVisible(false);

    }

    @FXML
    private void ajouter(ActionEvent event) {
        TYPE_VEHICULE typeV = type.getValue();
        String anneeV = annee.getText();
        String matriculeV = matricule.getText();
        String capaciteV = capacite.getText();
        String modeleV = modele.getText();
        String marqueV = marque.getText();
       
        Utilisateur u = new Utilisateur(2, "selim", "sahli", "selimsahli2@gmail.com", "123456789", "la marsa", "123456789", "");
        Remorque r = longueurT.getValue();
                VehiculeServices vs = new VehiculeServices();
        boolean a = true;
        for(Vehicule v : vehicules){
            System.out.println(marqueV.equals(v.getMatricule()));
        if(matriculeV.equals(v.getMatricule()))
        {
            System.out.println("existe");
              a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, " Matricule existe déja ");
            alert.showAndWait();
            marque.setText("");
        }}
     if (modeleV.isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez remplir ce champ: ");
            alert.showAndWait();
            modele.setText("");
        }
      if (marque.getText().isEmpty()) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une marque");
            alert.showAndWait();
        }
        if (type.getSelectionModel().getSelectedIndex() == -1) {
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
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une capacite valable");
            alert.showAndWait();

        }
        if (!annee.getText().matches("\\d+")) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une annee valable ");
            alert.showAndWait();

        }
        if (longueurT.isVisible() && longueurT.getSelectionModel().getSelectedIndex() == -1) {
            a = false;
            Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez saisir une remorque");
            alert.showAndWait();
        }
        int av = Integer.parseInt(anneeV);
        int cv = Integer.parseInt(capaciteV);
        Vehicule v = new Vehicule(av, cv, marqueV, modeleV, typeV, r, u,matriculeV);
        if(a==true)
        {
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
}
