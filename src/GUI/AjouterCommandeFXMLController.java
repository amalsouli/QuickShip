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
import java.util.Date;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.CategorieService;
import services.CommandeService;
import services.SMSUtil;

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
    private ComboBox<String> check_point;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextField capacite;
    @FXML
    private Button ajouter;
    @FXML
    private Button nouvelleCheck;
    Tooltip tooltip = new Tooltip("Ce champ ne doit pas être vide");
    @FXML
    private Button retour;
    @FXML
    private CheckBox rapide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
 ObservableList<String> listeDestination = observableCheckPoint.stream()
                .map(CheckPoint -> CheckPoint.getDestionation())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        check_point.setItems(listeDestination);

        ObservableList<String> listeCategorie = observableCategorie.stream()
                .map(categorie -> categorie.getNom())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        categorie.setItems(listeCategorie);
    }

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            
            LocalDate selectedDate = date.getValue();
            Utilisateur u = new Utilisateur(1, "selim","admin");
            String np = nom_produit.getText();
            String capc = capacite.getText();
            String ad = adresse_depart.getText();
            String cat = categorie.getValue();
            Categorie catt = null;
            for (Categorie categorie : observableCategorie) {
                if (categorie.getNom().equals(cat)) {
                    catt = categorie;
                }
            }
           boolean rap = rapide.isSelected();
           byte commandeRapide;
           if(rap){
               commandeRapide=1;
           }else{
                commandeRapide=0;
           }
            String cp = check_point.getValue();
             CheckPoint checkP = null;
            for (CheckPoint check : observableCheckPoint) {
                if (check.getDestionation().equals(cp)) {
                    checkP = check;
                }
            }
            CommandeService commSer = new CommandeService();
            if (nom_produit.getText().isEmpty()||capacite.getText().isEmpty()||adresse_depart.getText().isEmpty()||categorie.getSelectionModel().getSelectedIndex() == -1||check_point.getSelectionModel().getSelectedIndex() == -1||selectedDate == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            }
            else{
            if (!capacite.getText().matches("\\d+")) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "La capcité doit étre un nombre ");
                alert.showAndWait();
                capacite.setText("");
            }}
                    
            java.sql.Date datee = java.sql.Date.valueOf(selectedDate);
            int capc1 = Integer.parseInt(capc);
            Commande c = new Commande(datee, ad, np, catt, u, STATUS_COMMANDE.en_attente, checkP,capc1,commandeRapide);
            commSer.ajouter(c);
           // SMSUtil.sendSMS("+21625166021","votre commande a été ajouter avec succées");
           this.notif();
            Parent nouvelleVue = FXMLLoader.load(getClass().getResource("FXML.fxml"));
            Scene scene = ajouter.getScene();
            scene.setRoot(nouvelleVue);
        } catch (IOException ex) {
            Logger.getLogger(AjouterCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AjouterCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
private void notif(){
   Notifications notifications=Notifications.create();
        notifications.graphic(new ImageView());
        notifications.text("La commande a été ajoutée avec succès le "+LocalDate.now());
        notifications.title("Success Message");
        notifications.hideAfter(Duration.seconds(4));
        /*notifications.darkStyle();*/
     notifications.position(Pos.BOTTOM_CENTER);
        notifications.show();
}
    @FXML
    private void ajouterCheckPoint(ActionEvent event) {
    }
@FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
            Parent root = loader.load();
            Scene scene = retour.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}