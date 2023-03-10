/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Entities.maintenace;
import Entities.rapport;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Services.maintenaceService;
import Services.rapportService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class TechnicienMaintenanceAffichageController implements Initializable {

    @FXML
    private Button home;
    @FXML
    private VBox cardContainer;
    @FXML
    private TextField search;
      ObservableList<maintenace> ListM = FXCollections.observableArrayList();
    maintenaceService mSer = new maintenaceService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
        List<maintenace> list = mSer.recuperer();
        
        ListM.addAll(list);

        for (maintenace com : ListM) {

            // Créer les éléments de la première colonne
           
            Label label2v = new Label("     '" + com.getType()+ "'");
            VBox vbox1 = new VBox();
            vbox1.getChildren().addAll( label2v);
            vbox1.setAlignment(Pos.CENTER_LEFT);
            vbox1.setPrefWidth(200);
            
            // Créer les éléments de la deuxième colonne
          Label label1x = new Label(String.valueOf(com.getDate()));

            HBox depart = new HBox();
            depart.getChildren().addAll(label1x);
            depart.setAlignment(Pos.CENTER_LEFT);
            depart.setPrefWidth(150);
               // Créer les éléments de la troissieme colonne
          Label labell5 = new Label(String.valueOf(com.getId()));

            HBox duree = new HBox();
            duree.getChildren().addAll(labell5);
            duree.setAlignment(Pos.CENTER_LEFT);
            duree.setPrefWidth(150);
            
        
            // Créer les éléments de la quatrième colonne
            Button button_mod = new Button("Modifier");
            
            button_mod.setOnAction((event) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMaintenance.fxml"));
                    Parent root = loader.load();
                    ModifierMaintenanceController modifiier = loader.getController();
                    modifiier.setmaintenace(com);
                    Scene scene = button_mod.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(TechnicienMaintenanceAffichageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            HBox vbox3 = new HBox();
            vbox3.getChildren().addAll(button_mod);
            vbox3.setAlignment(Pos.CENTER_LEFT);
            vbox3.setPrefWidth(200);
            
            // Créer la HBox avec toutes les colonnes
            HBox hbox = new HBox();
            hbox.getChildren().addAll(duree,vbox1,depart, vbox3);
            hbox.setStyle("  -fx-background-color: #FFFFFF;\n"
                    + "    -fx-border-radius: 5px;\n"
                    + "    -fx-border-color: #D3D3D3;\n"
                    + "    -fx-border-width: 1px;\n"
                    + "    -fx-padding: 20px;\n"
                    + "    -fx-margin: 10px;\n"
                    + "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
                    + " display: flex;\n"
                    + "  -fx-height: 2px;");
            hbox.setSpacing(20);
            // Ajouter la HBox à la VBox
            cardContainer.getChildren().add(hbox);
            cardContainer.setStyle(" overflow-y: scroll;");
        }
        
        /*search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
        Predicate<Commande> filtrePersonnes = commande
                -> filtre.get().isEmpty()
                || commande.getNom_produit().toLowerCase().contains(filtre.get().toLowerCase())
                || commande.getAdresse_départ().toLowerCase().contains(filtre.get().toLowerCase());
        cardContainer.getChildren().setAll(node.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
cardContainer.getChildren().setAll(node.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        });*/
       } catch (SQLException ex) {
            Logger.getLogger(TechnicienMaintenanceAffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        

    @FXML
    private void retour(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/front.fxml"));
            Parent root = loader.load();
            home.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
}
