/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.Vehicule;
import Entities.maintenace;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.maintenaceService;
import javafx.beans.property.SimpleStringProperty;
/**
 * FXML Controller class
 *
 * @author Louay
 */
public class AffichageMaintenanceController implements Initializable {

    @FXML
    private TableColumn<maintenace, Button> modifier;
    @FXML
    private Button retour;
    @FXML
    private TableColumn<maintenace,String> id;
    @FXML
    private TableColumn<maintenace,String> v_id;
    @FXML
    private TableColumn<maintenace,String> type;
    @FXML
    private TableColumn<maintenace,Date> date;
 maintenaceService mainSer = new maintenaceService();
    ObservableList<maintenace>  Listmaintenace = FXCollections.observableArrayList();
        ObservableList<maintenace>  Listmaintenace1 = FXCollections.observableArrayList();

    
    @FXML
    private TableColumn<maintenace, Button> supprimer;
    @FXML
    private TableView<maintenace> tv;
    @FXML
    private Button btnrecherche;
    @FXML
    private DatePicker tfrecherche;
    @FXML
    private TableColumn<?, ?> rapport;
    @FXML
    private Button add;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List list = null;
            
            list = mainSer.recuperer();
        
            Listmaintenace.addAll(list);
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            v_id.setCellValueFactory(cellData -> {
                Vehicule check = cellData.getValue().getVehicule();
                return new SimpleStringProperty(check.getMatricule());
            });
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            date.setCellValueFactory(new PropertyValueFactory<>("Date"));
            tv.setItems(Listmaintenace);
            this.addrapport();
            this.delete();
            this.modifier();
        } catch (SQLException ex) {
            Logger.getLogger(AffichageMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
     private void delete() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Supprimer");
                        b.setOnAction((event) -> {
                            try {
                                if (mainSer.supprimer(tv.getItems().get(getIndex()))) {
                                    tv.getItems().remove(getIndex());
                                    tv.refresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(AffichageMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
  private void modifier() {
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((ActionEvent event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierMaintenance.fxml"));
                                Parent root = loader.load();
                              ModifierMaintenanceController modifier = loader.getController();
                               maintenace m = tv.getItems().get(getIndex());
                               System.out.println(m);
                                modifier.setmaintenace(m);
                               /* Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();*/ Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);
                    }
                }
            };

        });
    }
 @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilTechFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        } 
    }

    @FXML
    private void Recherche(ActionEvent event) throws SQLException {
    

         java.sql.Date datee = java.sql.Date.valueOf(tfrecherche.getValue());
        List<maintenace> l = mainSer.recuperer();
        //List<maintenace> l1 = l.stream()
              //  .filter(p->p.getDate().equals(datee))
                   //  .collect(Collectors.toList());
        //Listmaintenace1.addAll(l1);
      /*  if (l1.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR, "Aucune Maintenance n'existe a cette date ");
            alert.showAndWait();

        } else 
        {
             
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            v_id.setCellValueFactory(new PropertyValueFactory<>("v_id"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tv.setItems(Listmaintenace);
     

        tv.setItems(Listmaintenace1);
          
       
        }
        */
    }

  

    private void addrapport() {
  rapport.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("ajouter rapport");
                        b.setOnAction((ActionEvent event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Homerapport.fxml"));
                                Parent root = loader.load();
                              HomeRapportController modifier = loader.getController();
                               maintenace m = tv.getItems().get(getIndex());
                               System.out.println(m);
                                modifier.setmaintenace(m);
                               /* Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();*/
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageMaintenanceController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(b);
                    }
                }
            };

        });    }

    @FXML
    private void add(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeMaintenance.fxml"));
            Parent root = loader.load();
            add.getScene().setRoot(root);
           } catch (IOException ex) {
               System.out.print(ex.getMessage());
        }
    }

    
    
}


