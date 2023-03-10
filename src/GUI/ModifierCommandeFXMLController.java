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
import java.util.stream.Collectors;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
    private int id;
    @FXML
    private TextField nomProduit;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> check;
    @FXML
    private ComboBox<String> categorie;
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
    @FXML
    private CheckBox rapide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        observableCheckPoint.add(new CheckPoint(1, "sousse"));
        observableCheckPoint.add(new CheckPoint(3, "sfax"));

        observableCheckPoint.add(new CheckPoint(5, "nabeul"));
        observableCheckPoint.add(new CheckPoint(5, "nabeul"));
        ObservableList<String> listeDestination = observableCheckPoint.stream()
                .map(CheckPoint -> CheckPoint.getDestionation())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        check.setItems(listeDestination);

        ObservableList<String> listeCategorie = observableCategories.stream()
                .map(categorie -> categorie.getNom())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        categorie.setItems(listeCategorie);

    }

    public void setCommande(Commande c) {
        id = c.getId();
        nomProduit.setText(c.getNom_produit());
        adresse.setText(c.getAdresse_départ());
        u = c.getUtilisateur();
        LocalDate d = c.getDate().toLocalDate();
        date.setValue(d);
        categorie.setValue(c.getCategorie().getNom());
        String cap = String.valueOf(c.getCapacite());
        capacite.setText(cap);
        check.setValue(c.getCheckPoint().getDestionation());
        if (c.getCommandeRapide() == 1) {
            System.out.println(c.getCommandeRapide());
            rapide.setSelected(true);
        } else {
            rapide.setSelected(false);
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            LocalDate selectedDate = date.getValue();

            CommandeService cs = new CommandeService();
            String np = nomProduit.getText();
            String ad = adresse.getText();
            String cat = categorie.getValue();

            Categorie catt = null;
            for (Categorie categorie : observableCategories) {
                if (categorie.getNom().equals(cat)) {
                    catt = categorie;
                }
            }
            String cp = check.getValue();
            CheckPoint checkP = null;
            for (CheckPoint check : observableCheckPoint) {
                if (check.getDestionation().equals(cp)) {
                    checkP = check;
                }
            }
            String capS = capacite.getText();
            boolean rap = rapide.isSelected();
            byte commandeRapide;
            if (rap) {
                commandeRapide = 1;
            } else {
                commandeRapide = 0;
            }
            if (nomProduit.getText().isEmpty() || capacite.getText().isEmpty() || adresse.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }
            if (!capacite.getText().matches("\\d+")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "La capcité doit étre un nombre ");
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

            java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
            
            Commande c = new Commande(id, datee, ad, np, catt, u, STATUS_COMMANDE.en_attente, checkP, cap, commandeRapide);
            System.out.println(c);
            cs.modifier(c);
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("FXML.fxml"));
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
