/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Trajet;
import Services.TrajetService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class ListtrajetController implements Initializable {

    @FXML
    private TableColumn<Trajet, Integer> id;
    @FXML
    private TableColumn<Trajet, Date> date;
    @FXML
    private TableColumn<Trajet, String> point;
    @FXML
    private TableColumn<Trajet, String> vehicule;
    @FXML
    private TableColumn<Trajet, String> conducteur;
    @FXML
    private TableView<Trajet> trajettable;
  
    
    TrajetService s=new TrajetService();
    
    ObservableList<Trajet>  StudentList = FXCollections.observableArrayList();
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List l=s.afficher();
            StudentList.addAll(l);
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        point.setCellValueFactory(new PropertyValueFactory<>("Point_dep"));
        vehicule.setCellValueFactory(new PropertyValueFactory<>("Vehicule"));
        conducteur.setCellValueFactory(new PropertyValueFactory<>("Conducteur"));
        trajettable.setItems(StudentList);
            
        } catch (SQLException ex) {
            Logger.getLogger(ListtrajetController.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifier.setOnMouseClicked(e->{
			modifier();
		});
         trajettable.refresh();
    }    
    @FXML
    public void modifier()
    {
       Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        System.out.println(trajet);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateTrajet.fxml"));
        UpdateTrajetController modifiier = loader.getController();
        String nomConducteur = trajet.getPoint_dep();
        modifiier.setTrajet(nomConducteur);
                           Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show(); 
    }
   
    
  

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
        
        Trajet trajet=trajettable.getSelectionModel().getSelectedItem();
        s.Supprimer_trajet(trajet.getId());
        trajettable.refresh();
        
    }

        public TableCell<Trajet, Void> call(TableColumn<Trajet, Void> param) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }


