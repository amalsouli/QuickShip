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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import services.CategorieService;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierCommandeFXMLController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private TextField id;
    @FXML
    private TextField nomProduit;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<CheckPoint> check;
    @FXML
    private ComboBox<Categorie> categorie;
    @FXML
    private TextField capacite;
    @FXML
    private Button modifier;

    private Utilisateur u;
    CategorieService cs = new CategorieService();
    private List<CheckPoint> checkPoint = new ArrayList<>();
    private List<Categorie> categories = cs.afficher();
    private ObservableList<CheckPoint> observableCheckPoint = FXCollections.observableList(checkPoint);
    private ObservableList<Categorie> observableCategories = FXCollections.observableList(categories);
    @FXML
    private Button ajouterCheck;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        observableCheckPoint.add(new CheckPoint(1, "sousse"));
        check.setItems(observableCheckPoint);
        categorie.setItems(observableCategories);
        id.setDisable(true);
    }

    public void setCommande(Commande c) {
        String idc = String.valueOf(c.getId());
        id.setText(idc);
        nomProduit.setText(c.getNom_produit());
        adresse.setText(c.getAdresse_départ());
        u = c.getUtilisateur();
        LocalDate d = c.getDate().toLocalDate();
        date.setValue(d);
        categorie.setValue(c.getCategorie());
        String cap = String.valueOf(c.getCapacite());
        capacite.setText(cap);
        check.setValue(c.getCheckPoint());
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate selectedDate = date.getValue();

            CommandeService cs = new CommandeService();
            String np = nomProduit.getText();
            String ad = adresse.getText();
            Categorie cat = categorie.getValue();
            CheckPoint cp = check.getValue();
            String capS = capacite.getText();
            
            if (nomProduit.getText().isEmpty()||capacite.getText().isEmpty()||adresse.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }
          
            if (!capacite.getText().matches("\\d+")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "La capcité doit étre un nombre ");
                alert.showAndWait();
            }
            if (selectedDate.isBefore(currentDate)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "veuiller remplir une date valide");
                alert.showAndWait();
            }
          /*  if (categorie.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }
            if (check.getSelectionModel().getSelectedIndex() == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }*/
            int cap = Integer.parseInt(capS);
            int ide = Integer.parseInt(id.getText());
            id.setEditable(false);
            id.setDisable(true);

            java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
            Commande c = new Commande(ide, datee, ad, np, cat, u, STATUS_COMMANDE.en_attente, cp,cap);
            System.out.println(c);
            cs.modifier(c);
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
            Scene scene = modifier.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(ModifierCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterCheck(ActionEvent event) {
    }

}
