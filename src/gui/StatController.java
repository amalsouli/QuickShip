/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import Utils.MyDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 */
public class StatController implements Initializable {

    Connection cnx;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label label;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            String qry = "SELECT type, COUNT(*) as total FROM maintenace GROUP BY type;";

            cnx = MyDB.getInstance().getCnx();
            try (Statement stm = cnx.createStatement(); ResultSet rs = stm.executeQuery(qry)) {
                while (rs.next()) {
                    
                    
                    String type = rs.getObject("type").toString();
                    String count = rs.getString("total");
        double total = Double.parseDouble(count);
        pieChartData.add(new PieChart.Data(type, total));
                }
            }
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Affichage des informations de statistiques dans le PieChart
        pieChart.setData(pieChartData);
        pieChart.setTitle("Statistiques");
        pieChart.setLegendVisible(false);

        // Affichage des informations de statistiques dans le Label
        double total = 0;
        total = pieChartData.stream().map((data) -> data.getPieValue()).reduce(total, (accumulator, _item) -> accumulator + _item);
        String info = String.format("Nombre de maintenance : %d\nTotal des total : %.2f", pieChartData.size(),total);
        label.setText(info);
    }

    @FXML
    private void retourtoafM(ActionEvent event) {
                try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/TechnicienAffichage.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        } 
    }

}
