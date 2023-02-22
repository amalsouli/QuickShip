/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.CategorieService;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCommandeFXMLController implements Initializable {

    private List<Categorie> categories = new CategorieService().afficher();
    private List<CheckPoint> checkPoint = new ArrayList<>();
    private ObservableList<Categorie> observableCategorie = FXCollections.observableList(categories);
    private ObservableList<CheckPoint> observableCheckPoint = FXCollections.observableList(checkPoint);

    @FXML
    private TextField nom_produit;
    @FXML
    private TextField adresse_depart;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<CheckPoint> check_point;
    @FXML
    private ComboBox<Categorie> categorie;
    @FXML
    private TextField capacite;
    @FXML
    private Button ajouter;
    @FXML
    private Button nouvelleCheck;
    Tooltip tooltip = new Tooltip("Ce champ ne doit pas être vide");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        observableCheckPoint.add(new CheckPoint(1, "sousse"));
        check_point.setItems(observableCheckPoint);
        categorie.setItems(observableCategorie);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate selectedDate = date.getValue();
            Utilisateur u = new Utilisateur(1, "selim");
            String np = nom_produit.getText();
            String capc = capacite.getText();
            String ad = adresse_depart.getText();
            Categorie cat = categorie.getValue();
            CheckPoint cp = check_point.getValue();
            CommandeService commSer = new CommandeService();
            if (nom_produit.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Titre de l'information");
                alert.setContentText("Le contenu de l'information.");
            }
            
            if (capacite.getText().isEmpty()) {
                //  err12.setText("Tous les champs sont obligatoires !");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Titre de l'information");
                alert.setContentText("Le contenu de l'information.");
            }
            if (!capacite.getText().matches("\\d+")) {
                tooltip.show(nom_produit.getScene().getWindow(), nom_produit.getScene().getWindow().getX() + nom_produit.getLayoutX() + nom_produit.getWidth(), nom_produit.getScene().getWindow().getY() + nom_produit.getLayoutY() + nom_produit.getHeight());
            }
            if (selectedDate.isBefore(currentDate)) {
                // err.setText("Veuillez choisir une date valide");
            }
            if (categorie.getSelectionModel().getSelectedIndex() == -1) {
                // err11.setText("la catégorie du produit doit étre selectionnée");
            }
            if (check_point.getSelectionModel().getSelectedIndex() == -1) {
                // err111.setText("une destination doit étre selectionnée");
            }
            java.sql.Date datee = java.sql.Date.valueOf(date.getValue());
            Commande c = new Commande(datee, ad, np, cat, u, STATUS_COMMANDE.en_attente, cp);
            commSer.ajouter(c);
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherCommandeFXML.fxml"));
            Scene scene = ajouter.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(AjouterCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterCheckPoint(ActionEvent event) {
    }

}
