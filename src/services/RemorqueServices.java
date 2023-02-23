/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Remorque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author USER
 */
public class RemorqueServices implements IServices<Remorque> {

    Connection cnx;

    public RemorqueServices() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouter(Remorque t) {
        try {
            String sql = "INSERT INTO `type_remorque`(`longueur`, `largeur`, `capacite`, `couleur`, `marque`) VALUES ('" + t.getLongueur() + "','" + t.getLargeur() + "','" + t.getCapacite() + "','" + t.getCouleur() + "','" + t.getMarque() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Remorque t) {
        try {
            String sql = "UPDATE type_remorque SET longueur = ?, largeur = ?, capacite = ?, couleur = ?, marque = ? WHERE id_remorque = ?";
            PreparedStatement preparedStatement;
            preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, t.getLongueur());
            preparedStatement.setInt(2, t.getLargeur());
            preparedStatement.setInt(3, t.getCapacite());
            preparedStatement.setString(4, t.getCouleur());
            preparedStatement.setString(5, t.getMarque());
            preparedStatement.setInt(6, t.getId_remorque());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public boolean supprimer(Remorque t) {
        try {
                        String sql = "UPDATE type_remorque SET `etat`='" + 1 + "' WHERE id_remorque = " + t.getId_remorque()+ "";

          //  String sql = "DELETE FROM type_remorque WHERE id_remorque = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Remorque> afficher() {
        List<Remorque> remorques = new ArrayList<>();
        try {

            String sql = "SELECT * FROM type_remorque WHERE  etat = '" +0+ "'";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Remorque r = new Remorque();
                r.setId_remorque(resultSet.getInt("id_remorque"));
                r.setLongueur(resultSet.getInt("longueur"));
                r.setLargeur(resultSet.getInt("largeur"));
                r.setCapacite(resultSet.getInt("capacite"));
                r.setCouleur(resultSet.getString("couleur"));
                r.setMarque(resultSet.getString("marque"));
                remorques.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return remorques;
    }
}
