/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import com.google.zxing.qrcode.QRCodeWriter;
import Entities.Commande;
import Entities.Utilisateur;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class DetailsCommandeFXMLController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private Button retour;
    @FXML
    private Label rapide;
    @FXML
    private Label nomProduit;
    @FXML
    private Label depart;
    @FXML
    private Label destination;
    @FXML
    private Label capacite;
    private Label etat;
    @FXML
    private Label categorie;
    private Commande com;
    @FXML
    private ImageView qrcode;
    Utilisateur user;
    @FXML
    private Button modifier;
    @FXML
    private Label client;
    @FXML
    private Label nomClient;
    @FXML
    private ImageView local1;
    @FXML
    private ImageView local2;
    @FXML
    private ImageView exp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void setCommande(Commande c) {
        com = c;
        user = c.getUtilisateur();
        System.out.println(c.getUtilisateur().getRole());
        //ici au lieu de c.getUtilisateur() on test par user autentifier
        if (c.getUtilisateur().getRole().equals("admin")) {
            client.setVisible(true);
            nomClient.setVisible(true);
            nomClient.setText(c.getUtilisateur().getNom());
            modifier.setVisible(false);
        } else {
            modifier.setVisible(true);
            client.setVisible(false);
            nomClient.setVisible(false);
        }

        nomProduit.setText(c.getNom_produit());
        depart.setText(c.getAdresse_départ());
         File imageFile = new File("local.jpg"); // chemin absolu vers l'image
            Image image = new Image(imageFile.toURI().toString());
            local1.setImage(image);
           
        
        LocalDate d = c.getDate().toLocalDate();

        date.setValue(d);
        date.setDisable(true);
        // etat.setText(c.getStatus_commande().name());
        categorie.setText(c.getCategorie().getNom());
        String cap = String.valueOf(c.getCapacite());
        capacite.setText(cap);
         
            local2.setImage(image);
            
        destination.setText(c.getCheckPoint().getAdresse());
         File imageFileEx = new File("express.png"); // chemin absolu vers l'image
            Image imageE = new Image(imageFileEx.toURI().toString());
            exp.setImage(imageE);
           
        if (c.getCommandeRapide() == 1) {
            System.out.println(c.getCommandeRapide());
            rapide.setVisible(true);
            exp.setVisible(true);
        } else {
            rapide.setVisible(false);
            exp.setVisible(false);
        }
        this.start(c);
    }

    @FXML
    private void retour(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandeFXML.fxml"));
            Parent root = loader.load();
            Scene scene = retour.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void start(Commande u) {

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "Date de votre commande : " + u.getDate() + "\n" + "Nom du produit " + u.getNom_produit() + "\n" + "Etat du Commande : " + u.getStatus_commande();
        int width = 300;
        int height = 300;
        String fileType = "png";

        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");

        } catch (WriterException ex) {
            Logger.getLogger(DetailsCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
        qrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        /* StackPane root = new StackPane();
        root.getChildren().add(qrView);

        Scene scene = new Scene(root, 350, 350);

        Stage stage = new Stage();
        stage.setTitle("QR Code");
        stage.setScene(scene);
        stage.show();*/
        System.out.println("Success...");
    }

    @FXML
    private void modifier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierCommandeFXML.fxml"));
            Parent root = loader.load();
            ModifierCommandeFXMLController details = loader.getController();
            details.setCommande(com);

            Scene scene = modifier.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(DetailsCommandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
