/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import java.io.File;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCommandeFXMLController implements Initializable {

    @FXML
    private TableView<Commande> tv;
    @FXML
    private TableColumn<Commande, Date> date;
    @FXML
    private TableColumn<Commande, String> produit;
    @FXML
    private TableColumn<Commande, String> depart;
    @FXML
    private TableColumn<Commande, String> checkPoint;
    @FXML
    private TableColumn<Commande, String> categorie;
    @FXML
    private TableColumn<Commande, Integer> capacite;
    CommandeService commSer = new CommandeService();
    ObservableList<Commande> ListCommande = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Commande, Button> Delete;
    @FXML
    private TableColumn<Commande, Button> Modifier;
    @FXML
    private Button ajouter;
    @FXML
    private Button acceuil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List list = commSer.afficher();
        ListCommande.addAll(list);
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        produit.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
        depart.setCellValueFactory(new PropertyValueFactory<>("adresse_départ"));
        checkPoint.setCellValueFactory(new PropertyValueFactory<>("CheckPoint"));
        categorie.setCellValueFactory(cellData -> {
            Categorie cat = cellData.getValue().getCategorie();
            return new SimpleStringProperty(cat.getNom());
        });
        checkPoint.setCellValueFactory(cellData -> {
            CheckPoint check = cellData.getValue().getCheckPoint();
            return new SimpleStringProperty(check.getDestionation());
        });
        capacite.setCellValueFactory(new PropertyValueFactory<>("capacite"));

        tv.setItems(ListCommande);
        this.delete();
        this.modifier();

    }

    private void delete() {
        Delete.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Supprimer");
                        /* File imageFile = new File("C:\\Users\\asus\\Desktop\\QuickShip\\src\\GUI\\delete.png"); // chemin absolu vers l'image
                        Image image = new Image(imageFile.toURI().toString());
                        ImageView icon = new ImageView(image);
                        icon.setFitWidth(16);
icon.setFitHeight(16);*/
                        b.setStyle("-fx-background-color: #FC4843");

                        b.setOnAction((event) -> {

                            Alert alert = new Alert(Alert.AlertType.ERROR, "Êtes-vous sûr de vouloir supprimer ?");
                            alert.setHeaderText(null);
                            alert.setTitle("suppression");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                if (commSer.supprimer(tv.getItems().get(getIndex()))) {
                                    tv.getItems().remove(getIndex());
                                    tv.refresh();
                                }
                            }

                        });

                        setGraphic(b);
                    }
                }
            };

        });
    }

    private void modifier() {
        Modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setStyle("-fx-background-color: #14D175");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommandeFXML.fxml"));
                                Parent root = loader.load();
                                GUI.ModifierCommandeFXMLController modifiier = loader.getController();
                                Commande c = tv.getItems().get(getIndex());
                                System.out.println(c);
                                modifiier.setCommande(c);
                              
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);
                    }
                }
            };
        });
    }
     @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommandeFXML.fxml"));
            Parent root = loader.load();
            Scene scene = ajouter.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    private void acceuil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilFXML.fxml"));
            Parent root = loader.load();
            Scene scene = acceuil.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategorieFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
