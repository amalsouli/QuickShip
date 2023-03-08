/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Font;
import Services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AcceuilVehiculeFXMLController implements Initializable {

    @FXML
    private Button afficherV;
    @FXML
    private Button afficherR;
    
    
    @FXML
    private Button afficherPieChart;
    @FXML
    private Button afficherBarChart;
    @FXML
    private Label label;
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void afficherV(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherVehiculeFXML.fxml"));
            Parent root = loader.load();
            afficherV.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void afficherR(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRemorqueFXML.fxml"));
            Parent root = loader.load();
            afficherR.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }


    @FXML
    private void afficherPieChart(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PieChartFXML.fxml"));
            Parent root = loader.load();
            afficherV.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void afficherBarChart(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BarChartFXML.fxml"));
            Parent root = loader.load();
            afficherV.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

