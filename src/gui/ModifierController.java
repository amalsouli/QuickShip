/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.user;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class ModifierController implements Initializable {
    
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private ComboBox id_role_modifo;
    @FXML
    private ComboBox id_genre_modifo;
    @FXML
    private TextField id_nom_modifo;
    @FXML
    private TextField id_prenom_modifo;
    @FXML
    private TextField id_age_modifo;
    @FXML
    private TextField id_adresse_modifo;
    @FXML
    private TextField id_telephone_modifo;
    @FXML
    private TextField id_pwd_modifo;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_modifo;
    private TextField id_prenom;
    @FXML
    private TextField id_donne_mois_old;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList( "Client","Technicien","Conducteur" ) ;
        id_role_modifo.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Femme" ,"Homme") ;
        id_genre_modifo.setItems(liste);
        
            btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToCrud(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            ImageView imageView7 = new ImageView(getClass().getResource("mod3.png").toExternalForm());
            btn_modifo.setGraphic(imageView7);  
            ImageView imageView8 = new ImageView(getClass().getResource("777.png").toExternalForm());
            btn_back.setGraphic(imageView8);  
            
            btn_modifo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               modifier_un_user();
            }
        });
    } 
    
    
    
             public void redirectToCrud(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("Crud.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifier_un_user() {
               
       ServiceAdmin as = new ServiceAdmin();
       user u = new user();
    String modiftel;
    String nom;
    String prenom;
    int age;
    String pwd;
    String adresse;
    String role;
    String genere;
    String tel;
    
    
     modiftel=id_donne_mois_old.getText();
    
    
    
     nom=id_nom_modifo.getText();
     genere=id_genre_modifo.getSelectionModel().getSelectedItem().toString(); 
     prenom=id_prenom_modifo.getText();
     pwd=id_pwd_modifo.getText();
     role=id_role_modifo.getSelectionModel().getSelectedItem().toString(); 
     adresse=id_adresse_modifo.getText();
     age=Integer.valueOf(id_age_modifo.getText());
     
    
            user U1 = new user (nom,prenom,age,adresse,role,genere,pwd);
            ServiceAdmin uc= new ServiceAdmin() ;
            uc.update(U1,modiftel);
        
            JOptionPane.showMessageDialog(null,"Succés de modification ");
        
    }
    
}
