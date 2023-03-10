/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickship;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
          // Parent root = FXMLLoader.load(getClass().getResource("../GUI/AcceuilFXML.fxml"));
             Parent root = FXMLLoader.load(getClass().getResource("../GUI/AcceuilFrontFXML.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("QuickShip");
            primaryStage.setWidth(725);
            primaryStage.setHeight(450);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println("Err" + ex.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
