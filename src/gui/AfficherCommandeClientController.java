/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Commande;
import Entities.STATUS_COMMANDE;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import Services.CommandeService;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class AfficherCommandeClientController implements Initializable {

    @FXML
    private VBox cardContainer;
    CommandeService commSer = new CommandeService();
    ObservableList<Commande> ListCommande = FXCollections.observableArrayList();
    private final SimpleStringProperty filtre = new SimpleStringProperty("");
    @FXML
    private TextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Commande> list = commSer.afficher();
        ListCommande.addAll(list);
        for (Commande com : ListCommande) {

            // Créer les éléments de la première colonne
            Label label1V = new Label(com.getDate().toString());
            label1V.setStyle("-fx-font-size: 14px; -fx-font-weight:  bold;");

            Label label2v = new Label("     '" + com.getNom_produit() + "'");
            VBox vbox1 = new VBox();
            vbox1.getChildren().addAll(label1V, label2v);
            // Créer les éléments de la deuxième colonne
            File imageFile = new File("destination.png"); // chemin absolu vers l'image
            Image image = new Image(imageFile.toURI().toString());
            ImageView icon = new ImageView(image);
            icon.setFitWidth(30);
            icon.setFitHeight(28);
            Label label1x = new Label("    " + com.getAdresse_départ());
            Label label2x = new Label(com.getCheckPoint().getAdresse());
            HBox depart = new HBox();
            depart.getChildren().addAll(label1x, icon, label2x);
            // Créer la troisième colonne
            Label label3 = new Label(com.getStatus_commande().name());

            // Créer les éléments de la quatrième colonne
            Button button_mod = new Button("Modifier");
            Tooltip tooltip = new Tooltip("votre commande est " + com.getStatus_commande().name() + "vous ne pouvez pas la modifier");

            if (!com.getStatus_commande().equals(STATUS_COMMANDE.en_attente)) {

                button_mod.setOnMouseEntered((event) -> {
                    tooltip.show(button_mod, event.getScreenX(), event.getScreenY() + 20);
                    button_mod.setDisable(true);
                });
                button_mod.setOnMouseExited((event) -> {
                    tooltip.hide();
                    button_mod.setDisable(true);
                });
            }
            button_mod.setOnAction((event) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommandeFXML.fxml"));
                    Parent root = loader.load();
                    ModifierCommandeFXMLController modifiier = loader.getController();
                    modifiier.setCommande(com);
                    Scene scene = button_mod.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            Button button_det = new Button("Détails");
            button_det.setOnAction((event) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCommandeFXML.fxml"));
                    Parent root = loader.load();
                    DetailsCommandeFXMLController modifiier = loader.getController();
                    modifiier.setCommande(com);
                    Scene scene = button_det.getScene();
                    scene.setRoot(root);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            HBox vbox3 = new HBox();
            vbox3.getChildren().addAll(button_mod, button_det);
            Button button_anul = new Button("Annuler");
            button_anul.setOnAction((event) -> {
                com.setStatus_commande(STATUS_COMMANDE.annulée);
                commSer.modifier(com);
                List<Commande> NewList = commSer.afficher();
                ListCommande.setAll(NewList);
            });


            // Créer la HBox avec toutes les colonnes
            HBox hbox = new HBox();

            hbox.getChildren().addAll(vbox1, depart, label3, button_anul, vbox3);
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
