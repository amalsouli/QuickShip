/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
                        b.setOnAction((event) -> {
                            if (commSer.supprimer(tv.getItems().get(getIndex()))) {
                                tv.getItems().remove(getIndex());
                                tv.refresh();
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
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommandeFXML.fxml"));
                                Parent root = loader.load();
                                GUI.ModifierCommandeFXMLController modifiier = loader.getController();
                                Commande c = tv.getItems().get(getIndex());
                                System.out.println(c);
                                modifiier.setCommande(c);
                                /* Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();*/
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
}
