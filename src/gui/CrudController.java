/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
public class CrudController implements Initializable {

    @FXML
    private TextField id_nom_delete;
 
    @FXML
    private TextField id_prenom_delete;
    @FXML
    private Button btn_modif;
    @FXML
    private Button id_supprimer;
    @FXML
    private Button btn_go_home;
 
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button fx_back_home_crud;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
        
        
        ImageView imageView = new ImageView(getClass().getResource("sup.png").toExternalForm());
        id_supprimer.setGraphic(imageView);
        
        id_supprimer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                supprimer();
            }
        });      
        
         ImageView imageView2 = new ImageView(getClass().getResource("modif2.png").toExternalForm());
           btn_modif.setGraphic(imageView2);
           
            btn_modif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
                 ImageView imageView3 = new ImageView(getClass().getResource("abc.png").toExternalForm());
           btn_go_home.setGraphic(imageView3);
           
           btn_go_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                   redirectToMyHome(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
           
            ImageView imageView8 = new ImageView(getClass().getResource("777.png").toExternalForm());
           fx_back_home_crud.setGraphic(imageView8);  
                 fx_back_home_crud.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                   redirectToMyHome(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }    
  public void redirectToMyHome(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("Listuser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

   public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("modifier.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
      public void creeC(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("Listuser.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void supprimer() {
             String nom;
        String prenom;
         if(id_nom_delete.getText().isEmpty() || id_prenom_delete.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
          else
        {
        nom=(String)id_nom_delete.getText();
        prenom=(String)id_prenom_delete.getText();   
        ServiceAdmin uc= new ServiceAdmin();
        uc.supprimerUser_home(nom,prenom);
        JOptionPane.showMessageDialog(null,"Succés de suppression ");
        }
    }

    
}
