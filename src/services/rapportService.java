/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.maintenace;
import entities.rapport;
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
public class rapportService {

    Connection cnx;

    public rapportService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouter(rapport t) throws SQLException {
        String req = "INSERT INTO `rapport`(`description`, `duree`, `piece`, `Cout`, `observation`,`id_maintenance`) VALUES("
                + "'" + t.getDescription() + "','" + t.getDuree() + "','" + t.getPiece() + "','" + t.getCout() + "','" + t.getObservation() + "','" + t.getId_maintenance().getId() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    public void modifier(rapport t) throws SQLException {

        String req = "UPDATE rapport SET description = ?,duree = ?,piece = ?,cout = ?,observation = ? where id_rapport = ?";
        PreparedStatement rs = cnx.prepareStatement(req);
        rs.setString(1, t.getDescription());
        rs.setInt(2, t.getDuree());
        rs.setString(3, t.getPiece());
        rs.setInt(4, t.getCout());
        rs.setString(5, t.getObservation());
        rs.setInt(6, t.getId());
        rs.executeUpdate();
    }

    public void supprimer(rapport t) throws SQLException {
        String req = "DELETE FROM rapport where id_rapport = ?";
        PreparedStatement rs = cnx.prepareStatement(req);
        rs.setInt(1, t.getId());
        rs.executeUpdate();
    }

    public List<rapport> recuperer() throws SQLException {
        List<rapport> rapport = new ArrayList<>();
        String s = "select * from rapport";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            rapport R = new rapport();
            R.setDescription(rs.getString("Description"));
            R.setDuree(rs.getInt("duree"));
            R.setPiece(rs.getString("piece"));
            R.setCout(rs.getInt("cout"));
            R.setObservation(rs.getString("observation"));
            maintenaceService mt = new maintenaceService();
            for (maintenace m : mt.recuperer()) {
                if (m.getId() == rs.getInt("id_maintenance"));
                R.setId_maintenance(m);
            }

            rapport.add(R);
        }
        return rapport;
    }
}
