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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.CategorieService;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class ModifierCategorieFXMLController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setDisable(true);
    }

    void setCommande(Categorie c) {
        nom.setText(c.getNom());
        description.setText(c.getDescription());
        String idc = String.valueOf(c.getId());
        id.setText(idc);
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {

            CategorieService cs = new CategorieService();
            String np = nom.getText();
            String des = description.getText();

            int ide = Integer.parseInt(id.getText());
            id.setEditable(false);
            id.setDisable(true);
            if (nom.getText().isEmpty() || description.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Tous les champs sont obligatoires ! ");
                alert.showAndWait();
            } else {
                Categorie c = new Categorie(ide, np, des);
                System.out.println(c);
                cs.modifier(c);
                Parent nouvelleVue = FXMLLoader.load(getClass().getResource("AfficherCategorieFXML.fxml"));
                Scene scene = modifier.getScene();
                scene.setRoot(nouvelleVue);
            }
        } catch (IOException ex) {
            Logger.getLogger(ModifierCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
