/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.ServiceAdmin;
import Utils.Mail;


/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class ConnecterController implements Initializable {
    
    private Parent fxml;
    private Scene scene;
    private Stage stage;
    @FXML
    private TextField id_nom_conn;
    @FXML
    private PasswordField id_mdp_conn;
    @FXML
    private Button id_creerCompte2;
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        // TODO
           id_creerCompte2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToCreateUser(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    } 
        public void redirectToCreateUser(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
        
        public void gog(ActionEvent event) throws IOException  {
        Parent page1 = FXMLLoader.load(getClass().getResource("AcceuilAdminFXML.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
   
        public void gogo(ActionEvent event) throws IOException  {
        Parent page1 = FXMLLoader.load(getClass().getResource("AcceuilClientFXML.fxml"));
        Scene scene = new Scene(page1);
      
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
          
        public void tech(ActionEvent event) throws IOException  {
        Parent page1 = FXMLLoader.load(getClass().getResource("AcceuilTechFXML.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
                public void condu(ActionEvent event) throws IOException  {
        Parent page1 = FXMLLoader.load(getClass().getResource("FrontFXML.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void se_connecte(ActionEvent event) throws Exception {

           connect(event);  
       }
    
    
        public void connect(ActionEvent event) throws IOException {
        String nom_u = id_nom_conn.getText();
        String pwd = id_mdp_conn.getText();
        

        // Vérifier si les champs sont remplis
        if (nom_u.isEmpty() || pwd.isEmpty()) {
            // Afficher un message d'erreur
            System.out.println("Veuillez remplir tous les champs!");
            return;
        }

        // Vérifier si le nom d'utilisateur et le mot de passe sont valides
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            
            // Se connecter à la base de données MySQL
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_quickk","root","");

            // Requête pour vérifier le nom d'utilisateur et le mot de passe
            String sql = "SELECT COUNT(*) AS count FROM admin WHERE adresse = ? AND pwd = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom_u);
            stmt.setString(2, pwd);
            rs = stmt.executeQuery();
            
             if(id_nom_conn.getText().equals("amal.souli@esprit.tn") && id_mdp_conn.getText().equals("1482001")){
                   System.out.println("Connexion réussie!");
                 String recipient = "amal.souli@esprit.tn";
                 try {
                 Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();
               }
                gog(event);
               }
             
           else if(id_nom_conn.getText().equals("louay.almi@esprit.tn") && id_mdp_conn.getText().equals("louay123")){
                   System.out.println("Connexion réussie!");
                 String recipient = "amal.souli@esprit.tn";
                 try {
                 Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();
               }
               tech(event);
               } 
           
            else if(id_nom_conn.getText().equals("selim.sahli@esprit.tn") && id_mdp_conn.getText().equals("selim123")){
                   System.out.println("Connexion réussie!");
                 String recipient = "amal.souli@esprit.tn";
                 try {
                     Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();
               }
               condu(event);
               }
            // Récupérer le résultat de la requête
             else if (rs.next() && rs.getInt("count") > 0) {
                 
                  System.out.println("Connexion réussie!");
                 String recipient = "amal.souli@esprit.tn";
                 try {
                 Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();
               }
                // Connecté avec succès
                gogo(event);
            }
             
            else {
                // Afficher un message d'erreur
                System.out.println("Nom d'utilisateur ou mot de passe incorrect!");
            }
        } catch (SQLException e) {
            // Gérer les erreurs SQL
            e.printStackTrace();
        } finally {
            // Fermer les ressources JDBC
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }  
        }
    }
}     
  

