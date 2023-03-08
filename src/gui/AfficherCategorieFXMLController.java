/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
import Entities.Categorie;
import Entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Services.CategorieService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCategorieFXMLController implements Initializable {

    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> description;
    @FXML
    private TableView<Categorie> tv;
    CategorieService catSer = new CategorieService();
    ObservableList<Categorie> ListCategories = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Categorie, Button> supprimer;
    @FXML
    private TableColumn<Categorie, Button> modifier;
    @FXML
    private Button ajouter;
    @FXML
    private Button acceuil;
    @FXML
    private TextField search;
    
    private final SimpleStringProperty filtre = new SimpleStringProperty("");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categorie> list = catSer.afficher();
        ListCategories.addAll(list);
       // id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        tv.setItems(ListCategories);
        this.delete();
        this.modifier();
         search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
         Predicate<Categorie> filtreCategories = categorie
                -> filtre.get().isEmpty()
                || categorie.getNom().toLowerCase().contains(filtre.get().toLowerCase());
        ListCategories.setAll(list.stream().filter(filtreCategories).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
            ListCategories.setAll(list.stream().filter(filtreCategories).collect(Collectors.toList()));
        });
    }

    private void delete() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Supprimer");
                        b.setStyle("-fx-background-color: #FC4843");
                        b.setOnAction((event) -> {

                            Alert alert = new Alert(Alert.AlertType.ERROR, "Êtes-vous sûr de vouloir supprimer ?");
                            alert.setHeaderText(null);
                            alert.setTitle("suppression");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                if (catSer.supprimer(tv.getItems().get(getIndex()))) {
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
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setStyle("-fx-background-color: #14D175");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCategorieFXML.fxml"));
                                Parent root = loader.load();
                                ModifierCategorieFXMLController modifiier = loader.getController();
                                Categorie c = tv.getItems().get(getIndex());
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

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCategorieFXML.fxml"));
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

