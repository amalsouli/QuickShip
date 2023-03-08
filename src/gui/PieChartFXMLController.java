/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class PieChartFXMLController implements Initializable {

    @FXML
    private PieChart vehiculeStatut;
    @FXML
    private Label label;
    @FXML
    private Button retourA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           VehiculeServices vs = new VehiculeServices();

        PieChart.Data slice1 = new PieChart.Data("DISPONIBLE", vs.calculNombreVehiculeParStatut("DISPONIBLE"));
        PieChart.Data slice2 = new PieChart.Data("NON DISPONIBLE", vs.calculNombreVehiculeParStatut("NON DISPONIBLE"));
        PieChart.Data slice3 = new PieChart.Data("EN PANNE", vs.calculNombreVehiculeParStatut("EN PANNE"));
        int totalVehicules = vs.calculNombreVehiculeParStatut("DISPONIBLE") + vs.calculNombreVehiculeParStatut("NON DISPONIBLE") + vs.calculNombreVehiculeParStatut("EN PANNE");

        vehiculeStatut.getData().add(slice1);
        vehiculeStatut.getData().add(slice2);
        vehiculeStatut.getData().add(slice3);

        vehiculeStatut.setLegendSide(Side.LEFT);
        vehiculeStatut.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> label.setText("Le nombre de véhicule " + data.getName() + " : " + (int) (data.getPieValue()) + " (" + Math.round(data.getPieValue() * 100 / totalVehicules) + "%)"));
        });
    }    

    @FXML
    private void afficherPercentage(MouseEvent event) {
    }

    @FXML
    private void retourA(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AccueilFXML.fxml"));
            Parent root = loader.load();
            retourA.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
    
}