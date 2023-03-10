/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import Entities.TYPE;
import Entities.maintenace;
import Entities.rapport;
import Entities.Utilisateur;
import Services.rapportService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class HomeRapportController implements Initializable {

    @FXML
    private Button ajouter;
    @FXML
    private Button afficher;

    private final List<maintenace> Listmaintenace = new ArrayList();
    @FXML
    private Label description_r;
    @FXML
    private Label piece_r;
    @FXML
    private Label cout_r;
    @FXML
    private Label ob_r;
    @FXML
    private Label duree_r;
    @FXML
    private TextArea description;
    @FXML
    private TextField duree;
    @FXML
    private TextField piece;
    @FXML
    private TextField cout;
    @FXML
    private TextArea observation;
    @FXML
    private Label description_r1;
    private Button Accueil;
    @FXML
    private Button acceuil;
    maintenace maintenace;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRapport.fxml"));
            Parent root = loader.load();
            afficher.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {

        try {

            String des = description.getText();
            String du = duree.getText();
            String pi = piece.getText();
            String ct = cout.getText();
            String ob = observation.getText();

            rapportService rs = new rapportService();

            if (description.getText().isEmpty()) {
                System.out.println("aaaaaaaaaaaaaaaaaaa1");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir un description: ");
                alert.showAndWait();
            }

            if (duree.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une duree: ");
                alert.showAndWait();

            } 

            
            if (piece.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une piece: ");
                alert.showAndWait();
            }
            if (cout.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir le cout: ");
                alert.showAndWait();
            } 

            
            if (observation.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Entrée invalide Veuillez choisir une observation: ");
                alert.showAndWait();
            }
                int ctR = Integer.parseInt(ct);
                int duR = Integer.parseInt(du);

            Utilisateur u = new Utilisateur(1, "selim","technicien");

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2022);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 23);
            java.util.Date specifiedDate = calendar.getTime();
            Date sqlDate = new Date(specifiedDate.getTime());
            //maintenace m = new maintenace(2, TYPE.corrective, u, sqlDate);
            rapport r = new rapport(des, duR, pi, ctR, ob, maintenace);
            rs.ajouter(r);
        } catch (NumberFormatException ex) {
            Logger.getLogger(HomeRapportController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Accueilbu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageMaintenance.fxml"));
            Parent root = loader.load();
             
            acceuil.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    void setmaintenace(maintenace m) {
maintenace = m;    }

}
