/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.maintenace;
import entities.TYPE;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author Louay
 */
public class maintenaceService {

    Connection cnx;

    public maintenaceService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouter(maintenace t) throws SQLException {
        String req = "INSERT INTO `maintenace`(`type`, `date`,`utilisateur_id`) VALUES("
                + "'" + t.getTYPE() + "','" + t.getDate() + "','" + t.getUtilisateur().getId() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    public void modifier(maintenace t) throws SQLException {
        String req = "UPDATE `maintenace` SET `utilisateur_id`='" + t.getUtilisateur().getId() + "',`type`='" + t.getTYPE() + "',`date`='" + t.getDate() + "' WHERE id = '" + t.getId() + "'";
        PreparedStatement ms = cnx.prepareStatement(req);
        ms.executeUpdate();
    }

    public List<maintenace> recuperer() throws SQLException {
        List<maintenace> maintenace = new ArrayList<>();
        String s = "select * from maintenace";
        Statement st = cnx.createStatement();
        ResultSet ms = st.executeQuery(s);
        while (ms.next()) {
            maintenace M = new maintenace();
            M.setTYPE(TYPE.predictive);
            System.out.println(ms.getType());
            M.setDate(ms.getString("date"));
            maintenace.add(M);
        }
        return maintenace;
    }

    public void supprimer(maintenace t) throws SQLException {
        String req = "DELETE FROM `maintenace` WHERE id = '" + t.getId() + "'";
        PreparedStatement ms = cnx.prepareStatement(req);
        ms.executeUpdate();
       }
}
