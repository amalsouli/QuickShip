/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Vehicule;
import java.io.File;
import java.io.IOException;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class FrontFXMLController implements Initializable {

    @FXML
    private VBox cardContainer;
    VehiculeServices vehiculeSer = new VehiculeServices();
    ObservableList<Vehicule> ListVehicule = FXCollections.observableArrayList();
    private final SimpleStringProperty filtre = new SimpleStringProperty("");
    @FXML
    private ScrollPane scrollPane;

    /*@FXML
    private TextField search;*/
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Vehicule> list = vehiculeSer.afficher();
        ListVehicule.addAll(list);
        
        for (Vehicule vehi : ListVehicule) {

            // Créer les éléments de la première colonne
            Label label1V = new Label(vehi.getMatricule().toString());
            label1V.setPadding(new Insets(10, 10, 10, 10));
            label1V.setAlignment(Pos.CENTER_LEFT); // aligner le contenu à gauche
            label1V.setGraphicTextGap(10);
            label1V.setPrefWidth(120);

            label1V.setStyle("-fx-font-size: 14px; -fx-font-weight:  bold; -fx-text-fill: blue;");

            //Label label2v = new Label("     '" + com.getNom_produit() + "'");
            VBox vbox1 = new VBox();
            ScrollPane scrollPane = new ScrollPane(vbox1);
           
            vbox1.getChildren().addAll(label1V);
            //css
            vbox1.setPadding(new Insets(10, 10, 10, 10));
            // Créer les éléments de la deuxième colonne

            Label label1x = new Label(vehi.getMarque());
            Label label2x = new Label(vehi.getModele());
            VBox vbox2 = new VBox();
            vbox2.getChildren().addAll(label1x, label2x);
            vbox2.setAlignment(Pos.CENTER_LEFT); // aligner le contenu à gauche
            vbox2.setPadding(new Insets(10, 10, 10, 10));

            label1x.setGraphicTextGap(10);
            label1x.setPrefWidth(80);// définir la distance entre le texte et le bord gauche
            label2x.setGraphicTextGap(10);  // définir la distance entre le texte et le bord gauche
            label2x.setPrefWidth(80);
        
            // Créer la troisième colonne
            Label label3 = new Label(vehi.getType().toString());
            label3.setPadding(new Insets(10, 10, 10, 10));
            label3.setAlignment(Pos.CENTER);
            label3.setGraphicTextGap(10);
            label3.setPrefWidth(140);
            

            Label label4x = new Label(Integer.toString(vehi.getCapacite()));
            label4x.setPadding(new Insets(10, 10, 10, 10));
            label4x.setAlignment(Pos.CENTER_LEFT);
            label4x.setGraphicTextGap(10);
            label4x.setPrefWidth(30);

            Label label5x = new Label(vehi.getCouleur());
            label5x.setPadding(new Insets(10, 10, 10, 10));
            label5x.setAlignment(Pos.CENTER_LEFT);
            label5x.setGraphicTextGap(10);
            label5x.setPrefWidth(80);

            //integration si user=technicien
            Label label6x = new Label(Integer.toString(vehi.getAnnee()));
            label6x.setPadding(new Insets(10, 10, 10, 10));
            label6x.setAlignment(Pos.CENTER_LEFT);
            label6x.setGraphicTextGap(10);
            label6x.setPrefWidth(80);

            Label label7x = new Label(vehi.getStatut());
            label7x.setPadding(new Insets(10, 10, 10, 10));
            label7x.setAlignment(Pos.CENTER);
            label7x.setGraphicTextGap(10);
            label7x.setPrefWidth(150);
            

            // Créer les éléments de la quatrième colonne
            Button button_panne = new Button("En panne");
            button_panne.setPadding(new Insets(10, 10, 10, 10));
            button_panne.setPrefWidth(100);

            button_panne.setOnAction((event) -> {
                vehi.setStatut("EN PANNE");
                vehiculeSer.modifier(vehi);
                
                //refresh ne marche pas
               List<Vehicule> NewList = vehiculeSer.afficher();
                ListVehicule.setAll(NewList);
              

            });
            if(vehi.getStatut().equals("EN PANNE"))
            {
            label7x.setStyle("-fx-text-fill: red; -fx-font-weight:  bold");
            }
            
            HBox vbox3 = new HBox();
            vbox3.getChildren().addAll(button_panne);
            /*vbox3.setPadding(new Insets(10, 10, 10, 10));
            vbox3.setAlignment(Pos.CENTER_LEFT);*/

            // Créer la HBox avec toutes les colonnes
            HBox hbox = new HBox();

            hbox.getChildren().addAll(vbox1, vbox2, label3, label4x, label5x, label6x, label7x, vbox3);
            hbox.setStyle("  -fx-background-color: #FFFFFF;\n"
                    + "    -fx-border-radius: 5px;\n"
                    + "    -fx-border-color: #0A2B58;\n"
                    + "    -fx-border-width: 1px;\n"
                    + "    -fx-padding: 20px;\n"
                    + "    -fx-margin: 10px;\n"
                    + "    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.3), 10, 0, 0, 5);"
                    + " display: flex;\n"
                    + "  -fx-height: 2px;");
            hbox.setSpacing(20);
            // Ajouter la HBox à la VBox
            cardContainer.getChildren().add(hbox);
            // Node node = cardContainer;
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
    }
}

