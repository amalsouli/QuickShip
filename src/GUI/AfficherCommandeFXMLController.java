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
import entites.Utilisateur;
import java.io.File;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.paint.Color;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCommandeFXMLController implements Initializable {

    private List<CheckPoint> c = new ArrayList<CheckPoint>();

    private ObservableList<CheckPoint> observableCheckPoint = FXCollections.observableList(c);
    @FXML
    private TableView<Commande> tv;
    @FXML
    private TableColumn<Commande, Date> date;
    @FXML
    private TableColumn<Commande, String> depart;
    @FXML
    private TableColumn<Commande, String> checkPoint;
    @FXML
    private TableColumn<Commande, String> rapide;
    @FXML
    private TableColumn<Commande, STATUS_COMMANDE> etat;
    @FXML
    private TableColumn<Commande, Button> details;
    @FXML
    private TableColumn<Commande, Button> Delete;
    private TableColumn<Commande, Button> Modifier;
    @FXML
    private Button acceuil;
    CommandeService commSer = new CommandeService();
    ObservableList<Commande> ListCommande = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private DatePicker dateFiltre;
    @FXML
    private ComboBox<String> destinationFilter;
    @FXML
    private ComboBox<STATUS_COMMANDE> etatFilter;

    private final SimpleStringProperty filtre = new SimpleStringProperty("");

    private ObservableList<STATUS_COMMANDE> observableStatus = FXCollections.observableArrayList(STATUS_COMMANDE.values());

    @FXML
    private Button refresh;
    @FXML
    private TableColumn<Commande, String> client;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        etatFilter.setItems(observableStatus);
        observableCheckPoint.add(new CheckPoint(1, "sousse"));
        observableCheckPoint.add(new CheckPoint(3, "sfax"));
        observableCheckPoint.add(new CheckPoint(5, "nabeul"));
        ObservableList<String> listeDestination = observableCheckPoint.stream()
                .map(CheckPoint -> CheckPoint.getDestionation())
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        destinationFilter.setItems(listeDestination);

        List<Commande> list = commSer.afficher();
        ListCommande.addAll(list);
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        client.setCellValueFactory(cellData -> {
            Utilisateur user = cellData.getValue().getUtilisateur();
            //au lieu du role ->prenom
            return new SimpleStringProperty(user.getNom()+" "+user.getRole());
        });
        depart.setCellValueFactory(new PropertyValueFactory<>("adresse_départ"));
        checkPoint.setCellValueFactory(cellData -> {
            CheckPoint check = cellData.getValue().getCheckPoint();
            return new SimpleStringProperty(check.getDestionation());
        });
        etat.setCellValueFactory(new PropertyValueFactory<>("status_commande"));
       rapide.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCommandeRapide() == 0 ? "Normale" : "Rapide"));

        tv.setItems(ListCommande);
        this.delete();
        //this.modifier();
        this.details();

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filtre.set(newValue);
        });
        Predicate<Commande> filtrePersonnes = commande
                -> filtre.get().isEmpty()
                || commande.getNom_produit().toLowerCase().contains(filtre.get().toLowerCase())
                || commande.getAdresse_départ().toLowerCase().contains(filtre.get().toLowerCase());
        ListCommande.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        filtre.addListener((observable, oldValue, newValue) -> {
            ListCommande.setAll(list.stream().filter(filtrePersonnes).collect(Collectors.toList()));
        });
        System.out.println(ListCommande);
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
                            LocalDate dateLimite = LocalDate.now().minusMonths(1);
                            System.out.println(tv.getItems().get(getIndex()).getDate());
                            if (tv.getItems().get(getIndex()).getDate().toLocalDate().isBefore(dateLimite) && tv.getItems().get(getIndex()).getStatus_commande() == STATUS_COMMANDE.expédiée) {
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
                            } else {
                                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Vous Pouvez pas supprimer cette commande?");
                                alert.setHeaderText(null);
                                alert.setTitle("suppression");
                                alert.showAndWait();
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

    private void details() {
        details.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Détail");
                        b.setStyle("-fx-background-color: #f0f0f0");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsCommandeFXML.fxml"));
                                Parent root = loader.load();
                                GUI.DetailsCommandeFXMLController details = loader.getController();
                                Commande c = tv.getItems().get(getIndex());
                                System.out.println(c);
                                details.setCommande(c);

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

    @FXML
    private void Filter(ActionEvent event) {
        List<Commande> l = commSer.afficher();
        System.out.println(destinationFilter.getValue());
        List<Commande> l1 = l.stream()
                .filter(p -> (dateFiltre.getValue() == null || p.getDate().equals(java.sql.Date.valueOf(dateFiltre.getValue())))
                && (etatFilter.getValue() == null || p.getStatus_commande() == etatFilter.getValue())
                && (destinationFilter.getValue() == null || p.getCheckPoint().getDestionation().equals(destinationFilter.getValue())))
                .collect(Collectors.toList());
        if (l1.isEmpty()) {
            ListCommande.clear();
        } else {
            date.setCellValueFactory(new PropertyValueFactory<>("Date"));
client.setCellValueFactory(cellData -> {
            Utilisateur user = cellData.getValue().getUtilisateur();
            //au lieu du role ->prenom
            return new SimpleStringProperty(user.getNom()+" "+user.getRole());
        });            depart.setCellValueFactory(new PropertyValueFactory<>("adresse_départ"));
            checkPoint.setCellValueFactory(cellData -> {
                CheckPoint check = cellData.getValue().getCheckPoint();
                return new SimpleStringProperty(check.getDestionation());
            });
            etat.setCellValueFactory(new PropertyValueFactory<>("status_commande"));
            rapide.setCellValueFactory(new PropertyValueFactory<>("commandeRapide"));

            tv.setItems(ListCommande);
            this.delete();
            //this.modifier();
            this.details();
            ListCommande.clear();
            ListCommande.addAll(l1);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
        dateFiltre.setValue(null);
        etatFilter.setValue(null);
        destinationFilter.setValue(null);
        List<Commande> l = commSer.afficher();

        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
client.setCellValueFactory(cellData -> {
            Utilisateur user = cellData.getValue().getUtilisateur();
            //au lieu du role ->prenom
            return new SimpleStringProperty(user.getNom()+" "+user.getRole());
        });        depart.setCellValueFactory(new PropertyValueFactory<>("adresse_départ"));
        checkPoint.setCellValueFactory(cellData -> {
            CheckPoint check = cellData.getValue().getCheckPoint();
            return new SimpleStringProperty(check.getDestionation());
        });
        etat.setCellValueFactory(new PropertyValueFactory<>("status_commande"));
       rapide.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCommandeRapide() == 0 ? "Normale" : "Rapide"));

        tv.setItems(ListCommande);
        this.delete();
       // this.modifier();
        this.details();
        ListCommande.clear();
        ListCommande.addAll(l);
    }

  

}
