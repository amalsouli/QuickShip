/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class BarChartFXMLController implements Initializable {

    @FXML
    private BarChart<String, Integer> vehiculeMarque;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button retourA;
    @FXML
    private VBox mainBox;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VehiculeServices vs = new VehiculeServices();
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        xAxis.setLabel("Marque");
        yAxis.setLabel("Nb véhicules");

        vehiculeMarque = new BarChart(xAxis, yAxis);

        XYChart.Series<String, Integer> dataSeries1 = new XYChart.Series<>();

        dataSeries1.setName("chart1");
        List<String> list = vs.listMarques();

        for (String marqueCourrante : list) {
            //XYChart.Data<String, Integer> data = new XYChart.Data<>(marqueCourrante, vs.calculNombreVehiculeParMarque(marqueCourrante));
            dataSeries1.getData().add(new XYChart.Data(marqueCourrante, vs.calculNombreVehiculeParMarque(marqueCourrante)));
        }

        vehiculeMarque.getData().add(dataSeries1);
        mainBox.getChildren().add(vehiculeMarque);
        

        /*VBox vbox = new VBox(vehiculeMarque);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML
    private void retourA(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherVehiculeFXML.fxml"));
            Parent root = loader.load();
            retourA.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}

