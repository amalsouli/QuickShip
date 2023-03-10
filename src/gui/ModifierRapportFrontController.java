/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



/**
 * FXML Controller class
 *
 * @author Louay
 */
import Entities.maintenace;
import Entities.rapport;
import Entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Services.rapportService;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class ModifierRapportFrontController implements Initializable {

    @FXML
    private Label description_r;
    @FXML
    private Label piece_r;
    @FXML
    private Label cout_r;
    @FXML
    private Label ob_r;
    @FXML
    private Label duree_r;
    @FXML
    private Button modifier;
    @FXML
    private TextField description;
    @FXML
    private TextField duree;
    @FXML
    private TextField piece;
    @FXML
    private TextField cout;
    @FXML
    private TextField observation;
    @FXML
    private TextField id;
    @FXML
    private Label des_r;
maintenace m;
    rapportService rs = new rapportService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setDisable(true);
    }

    public void setrapport(rapport r) {
        String idc = String.valueOf(r.getId());
        id.setText(idc);
        description.setText(r.getDescription());
        String duR = String.valueOf(r.getDuree());
        duree.setText(duR);
        String coutR = String.valueOf(r.getCout());
        cout.setText(coutR);
        observation.setText(r.getObservation());
        piece.setText(r.getPiece());

        m = r.getId_maintenance();

    }
    
        
     

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         try {
            Utilisateur u = new Utilisateur(1, "selim","Technicien");
            //java.sql.Date date = (java.sql.Date) new Date("12/12/2022");
// maintenace m = new maintenace(2,TYPE.corrective,u, date);
// int idm=m.getId();
            String des = description.getText();
            String du = duree.getText();
            int duR = Integer.parseInt(du);
            String pi = piece.getText();
            String ct = cout.getText();
            int ctR = Integer.parseInt(ct);
            String ob = observation.getText();
             String idr = id.getText();
            int idd = Integer.parseInt(idr);
            id.setEditable(false);
            id.setDisable(true);
            rapport r = new rapport(idd, des, duR, pi, ctR, ob, m);
            System.out.println(r);
            rs.modifier(r);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TechnicienAffichage.fxml"));
            Parent root = loader.load();

            Parent parent = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(parent));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ModifierRapportFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    }
