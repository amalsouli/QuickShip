/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class AcceuilController implements Initializable {

    private Text text2;
    private Text text3;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        
    }    

    @FXML
    private void traj(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Listee.fxml"));
        Parent root=loader.load();
        Parent parent = loader.getRoot();
                            
                          Scene scene = btn1.getScene();
                          scene.setRoot(parent);
        
    }

    @FXML
    private void check(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkpoint_list.fxml"));
        Parent root=loader.load();
        Parent parent = loader.getRoot();
                            
                          Scene scene = btn2.getScene();
                          scene.setRoot(parent);
    }
    
}
