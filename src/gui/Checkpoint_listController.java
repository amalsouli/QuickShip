/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entities.CheckPoint;
import Entities.Trajet;
import Services.CheckService;
import Services.TrajetService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ThinkPad
 */
public class Checkpoint_listController implements Initializable {

    @FXML
    private TableView<CheckPoint> table;
    @FXML
    private TableColumn<CheckPoint, Integer> id;
    @FXML
    private TableColumn<CheckPoint, String> destination;
    @FXML
    private TableColumn<CheckPoint, Integer> heure;
    @FXML
    private TableColumn<CheckPoint, Integer> minutes;
    @FXML
    private TableColumn<CheckPoint, Date> date;
    CheckService s=new CheckService();
    
    ObservableList<CheckPoint>  StudentList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<CheckPoint, Button> supprimer;
    @FXML
    private TableColumn<CheckPoint, Button> modifer;
    private Button retour;
    @FXML
    private Text text1;
    @FXML
    private Button btn3;
    @FXML
    private TextField rechercher;
            private final SimpleStringProperty filtre = new SimpleStringProperty("");

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
           List<CheckPoint>  l = s.afficher();
            StudentList.addAll(l);
       
            
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        destination.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        minutes.setCellValueFactory(new PropertyValueFactory<>("min"));
        this.delete();
        this.modifier();
     rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filtre.set(newValue);
            });
            Predicate<CheckPoint> filtrePersonnes = tra
                    -> filtre.get().isEmpty()
                            || tra.getAdresse().toLowerCase().contains(filtre.get().toLowerCase());
            StudentList.setAll(l.stream().filter(filtrePersonnes).collect(Collectors.toList()));
            filtre.addListener((observable, oldValue, newValue) -> {
                StudentList.setAll(l.stream().filter(filtrePersonnes).collect(Collectors.toList()));
            });

        table.setItems(StudentList);
         } catch (SQLException ex) {
            Logger.getLogger(Checkpoint_listController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     private void delete() {
        supprimer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Delete");
                         b.setStyle("-fx-background-color: red");
                        b.setOnAction((event) -> {
                       
                            try {
                                if (s.Supprimer_check(table.getItems().get(getIndex()).getId()))
                                {
                                    table.getItems().remove(getIndex());
                                    table.refresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(ListeeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                    });
                        setGraphic(b);

                    }
                }
            };

        });
    }
    private void modifier() {
        modifer.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier"); 
                        b.setStyle("-fx-background-color: green");
                        
                        b.setOnAction((event) -> {
                       
        CheckPoint C=table.getSelectionModel().getSelectedItem();
        System.out.println(C.getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCheck.fxml"));
                            try {
                                Parent root=loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(ListeeController.class.getName()).log(Level.SEVERE, null, ex);
                            }
        UpdateCheckController modifiier = loader.getController();
        String destination = C.getAdresse();
        int heure=C.getHeure();
        int minute =C.getMin();
        Trajet t=C.getTrajet();
        int id=C.getId();
        String myString = Integer.toString(id);
        
      //  modifiier.setCheck(destination,heure,minute,t.getDate().toString(),id);
         //modifiier.setCheck1(destination,id);
         modifiier.setCheck1(C);
                           Parent parent = loader.getRoot();
                            
                          Scene scene = b.getScene();
                          scene.setRoot(parent);
                            

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

  

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AcceuilAdminFXML.fxml"));
        
                        Parent root=loader.load();
                        Parent parent = loader.getRoot();
                          Scene scene = btn3.getScene();
                          scene.setRoot(parent);
    }

    }    
    

