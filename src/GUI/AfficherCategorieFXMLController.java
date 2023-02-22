/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherCategorieFXMLController implements Initializable {

    @FXML
    private TableColumn<Categorie, Integer> id;
    @FXML
    private TableColumn<Categorie, String> nom;
    @FXML
    private TableColumn<Categorie, String> description;
     @FXML
    private TableView<Categorie> tv;
    CategorieService catSer = new CategorieService();
    ObservableList<Categorie> ListCategories = FXCollections.observableArrayList();
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          List list = catSer.afficher();
          ListCategories.addAll(list);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

tv.setItems(ListCategories);      
    }    
    
}
