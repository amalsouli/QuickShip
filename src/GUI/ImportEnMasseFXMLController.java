/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import org.apache.poi.ss.util.CellRangeAddressList;

import entities.Remorque;
import entities.TYPE_VEHICULE;
import entities.Utilisateur;
import entities.Vehicule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.ss.usermodel.DataValidation;
import static org.apache.poi.ss.usermodel.FormulaError.NULL;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ImportEnMasseFXMLController implements Initializable {

    @FXML
    private Button importButton;

    private Utilisateur user;
    @FXML
    private Button telechargerB;
    @FXML
    private Button retour;
    @FXML
    private Button afficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void importFile(ActionEvent event) {

        VehiculeServices vehiculeServ = new VehiculeServices();
        try {
            //    FileInputStream file = null;
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.xlsx)", "*.xlsx");
            fileChooser.getExtensionFilters().add(extFilter);
            Stage stage = new Stage();
            File file = fileChooser.showOpenDialog(stage);
            //System.out.println(file);

            // FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\USER\\Downloads\\testImport.xlsx"));
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);

            // Créer une liste de véhicules
            ArrayList<Vehicule> vehicules = new ArrayList<Vehicule>();
            // Parcourir chaque ligne du fichier Excel
            Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext()) {
                org.apache.poi.ss.usermodel.Row row = rowIterator.next();

                // Récupérer les données de chaque colonne
                String matricule = row.getCell(0).getStringCellValue();
                int annee = (int) row.getCell(1).getNumericCellValue();
                int capacite = (int) row.getCell(2).getNumericCellValue();
                String marque = row.getCell(3).getStringCellValue();
                String couleur = row.getCell(4).getStringCellValue();
                String modele = row.getCell(5).getStringCellValue();
                String type = row.getCell(6).getStringCellValue();
                String statut = row.getCell(7).getStringCellValue();
                if (!matricule.equals(NULL) || !matricule.equals("")) {
                    // Créer un objet Vehicule
                    Utilisateur u = new Utilisateur(2, "selim", "sahli", "selimsahli2@gmail.com", "123456789", "la marsa", "123456789", ""); // => a changer plus tard
                    Remorque r = new Remorque(3, 10, 5, 4000, "verte", "dddddddddd");

                    Vehicule vehicule = new Vehicule(annee, capacite, marque, modele, TYPE_VEHICULE.valueOf(type), u, matricule, couleur, statut);
                    vehiculeServ.ajouter(vehicule);
                }
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Le fichier a été importé avec succès");
            alert.showAndWait();
        } catch (Exception ex) {
            Logger.getLogger(ImportEnMasseFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR, "Le fichier n est pas conforme au format attendu");
            alert.showAndWait();
        }

    }

    @FXML
    private void downloadFile(ActionEvent event) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("AJOUT VEHICULES");
        Object[][] datatypes = {
            {"Matricule", "Annee", "Capacite", "Marque", "Couleur", "Modele", "Type", "Statut"}
        };

        int rowNum = 0;
        System.out.println("Creating excel");
        //boucle sur 
        for (Object[] datatype : datatypes) {
            org.apache.poi.ss.usermodel.Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            //boucle sur la liste des entetes
            for (Object field : datatype) {
                org.apache.poi.ss.usermodel.Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);

                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        //LISTE DEROULANTE STATUT
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint statutConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(new String[]{"DISPONIBLE", "NON DISPONIBLE", "EN PANNE"});
        CellRangeAddressList statutList = new CellRangeAddressList(1, 200, 7, 7);
        XSSFDataValidation validationStatut = (XSSFDataValidation) dvHelper.createValidation(
                statutConstraint, statutList);
        validationStatut.setShowErrorBox(true);
        sheet.addValidationData(validationStatut);

        //LISTE DEROULANTE MARQUE
        XSSFDataValidationConstraint marqueConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(new String[]{"KIA", "RENAULT", "CITROEN", "FORD"});
        CellRangeAddressList marquetList = new CellRangeAddressList(1, 200, 3, 3);
        XSSFDataValidation validationMarque = (XSSFDataValidation) dvHelper.createValidation(
                marqueConstraint, marquetList);
        validationMarque.setShowErrorBox(true);
        sheet.addValidationData(validationMarque);

        //LISTE DEROULANTE TYPE
        XSSFDataValidationConstraint typeConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(new String[]{"Voiture", "Camion"});
        CellRangeAddressList typeList = new CellRangeAddressList(1, 200, 6, 6);
        XSSFDataValidation validationType = (XSSFDataValidation) dvHelper.createValidation(
                typeConstraint, typeList);
        validationType.setShowErrorBox(true);
        sheet.addValidationData(validationType);

        try {
            // Generate file name with current date and time
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
            String fileName = "template_" + dateFormat.format(new Date()) + ".xlsx";

            // Save workbook to file
            FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.home") + "\\Downloads\\" + fileName);
            workbook.write(outputStream);
            outputStream.close();

            // Show success message
            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION, "Le fichier est enregistré sous le chemin " + System.getProperty("user.home") + "\\Downloads\\" + fileName);
            alertInfo.showAndWait();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilAjoutVehiculeFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherVehiculeFXML.fxml"));
            Parent root = loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

}
