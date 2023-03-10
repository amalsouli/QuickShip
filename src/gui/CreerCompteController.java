/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.user;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.ServiceAdmin;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class CreerCompteController implements Initializable {

    @FXML
    private TextField id_nom;
    @FXML
    private TextField id_mdp;
    @FXML
    private TextField id_telephone;
    @FXML
    private TextField id_prenom;
    @FXML
    private ComboBox id_age;
    @FXML
    private TextField id_adresse;
    @FXML
    private ComboBox id_role;
    @FXML
    private Button btn_valider;
    @FXML
    private ComboBox id_genre;

    @FXML
    private Button btn_connecter_old;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<String> list = FXCollections.observableArrayList( "Client","Conduteur","Technicien" ) ;
        id_role.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Femme" , "Homme") ;
        id_genre.setItems(liste);
        
         ObservableList<Integer> liste1 = FXCollections.observableArrayList(1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003) ;
        id_age.setItems(liste1);

        
            btn_connecter_old.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToUseOld(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

    }    
    
          public Connection getConnection(){ 
          
          Connection conx ;
          try{
              conx = DriverManager.getConnection("jdbc:mysql://localhost:3307/quickship1") ;
              return conx ;
          }catch(SQLException ex){
              System.out.println("Error: "+ ex.getMessage());
              return null ;
          }
    }    


    @FXML
    private void valider(ActionEvent event) {
    String nom;
    String prenom;
    int age;
    String pwd;
    String adresse;
    String role;
    String genere;
    String tel;
 
     
     nom=id_nom.getText();
     genere=id_genre.getSelectionModel().getSelectedItem().toString(); 
     prenom=id_prenom.getText();
     pwd=id_mdp.getText();
     tel=id_telephone.getText();
     role=id_role.getSelectionModel().getSelectedItem().toString(); 
     adresse=id_adresse.getText();
     age=2023-Integer.valueOf(id_age.getSelectionModel().getSelectedItem().toString());
     
     if(id_nom.getText().isEmpty() || id_prenom.getText().isEmpty() ||id_mdp.getText().isEmpty() || id_telephone.getText().isEmpty() || id_adresse.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
     else
        {
            user U1 = new user (nom,prenom,age,adresse,tel,role,genere,pwd);
            ServiceAdmin uc= new ServiceAdmin() ;
            uc.ajouterUser(U1);
        
            JOptionPane.showMessageDialog(null,"Succés de création ");
        
        } 

    }
    
    
    
    public void  crud(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Crud.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void  redirectToUseOld(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("connecter.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void redirectToSeeDash(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Listuser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
