/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import Entities.user;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.ServiceAdmin;
import Utils.Pdf;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class DashboardController implements Initializable {

    @FXML
    private Button id_liste_user;
    @FXML
    private Button id_deconn_admin;
    @FXML
    private TableView<user> id_liste;
    @FXML
    private TableColumn<user, String> id_nom;
    @FXML
    private TableColumn<user, String> id_prenom;
    @FXML
    private TableColumn<user, Integer> id_age;
    @FXML
    private TableColumn<user, String> id_adresse;
    @FXML
    private TableColumn<user, String> id_tel;
    @FXML
    private TableColumn<user, String> id_role;
    @FXML
    private TableColumn<user, String> id_genre;
    @FXML
    private TableColumn<user, String> id_pwd;

    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button id_crud;
    @FXML
    private TextField rech;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      ImageView imageView = new ImageView(getClass().getResource("2222.png").toExternalForm());
        id_liste_user.setGraphic(imageView);

           id_liste_user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listeUser();
            }
        });
           
           
             id_deconn_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deconn_admin(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
             
           id_crud.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    crud(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });     
    }    

    @FXML
    private void listeUser() {
       
        ServiceAdmin uc =new ServiceAdmin();
        ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_nom.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
        id_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
        id_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        id_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        id_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        id_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        id_genre.setCellValueFactory(new PropertyValueFactory<>("genere"));
        id_pwd.setCellValueFactory(new PropertyValueFactory<>("pwd"));
        
        
    }

     private void crud(ActionEvent event) throws Exception{
               
        Parent page1 = FXMLLoader.load(getClass().getResource("Crud.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }
    @FXML
    private void deconn_admin(ActionEvent event) throws Exception{
               
        Parent page1 = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void deleteUser(ActionEvent event) {
           int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));

            ServiceAdmin rs = new  ServiceAdmin();
           rs.supprimerUser(id);

           id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {
            String searchTerm = rech.getText();
    ServiceAdmin su = new ServiceAdmin ();
    ObservableList<user> list = su.searchent2(searchTerm);
    List<user> filteredList = list.stream()
        .filter(entretient -> entretient.getNom_u().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

   id_liste.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void pdf(ActionEvent event) {
               Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des offres");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void clear(ActionEvent event) {
           ServiceAdmin e =new ServiceAdmin();
        e.supprimerClassUser();
        JOptionPane.showMessageDialog(null, "reservation suprimer !");
    }
   
}
