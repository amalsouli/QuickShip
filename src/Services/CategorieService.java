/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.MyDB;

/**
 *
 * @author ThinkPad
 */
public class CategorieService  implements IService<Categorie> {
    
    Connection cnx;

    @Override
    public void ajouter(Categorie t) {
        try {
            String sql = "INSERT INTO `catégorie`(`Nom_cat`,`description_cat`) VALUES ('" + t.getNom() + "','" + t.getDescription() + "')";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Categorie> afficher() {
        List<Categorie> categories = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `catégorie` where archiver ="+0+"";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                categories.add(c);
            }
            return categories;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }

    @Override
    public void modifier(Categorie t) {
        try {
            String sql = "UPDATE `catégorie` SET `Nom_cat` = '"+t.getNom()+"',`description_cat`='"+t.getDescription()+"'  WHERE `cat_id` = '"+t.getId()+"'";
            cnx = MyDB.getInstance().getCnx();
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprimer(Categorie t) {
        try {
            String sql = "UPDATE `catégorie` SET `archiver`=" + 1 + " WHERE cat_id = " + t.getId() + "";
            //String sql = "DELETE FROM `catégorie` WHERE `cat_id` = ?";
            cnx = MyDB.getInstance().getCnx();
            PreparedStatement statement = cnx.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return false;
    }
}
