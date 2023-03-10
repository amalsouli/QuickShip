/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import entites.STATUS_COMMANDE;
import entites.Utilisateur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author asus
 */
public class CommandeService implements IService<Commande> {

    Connection cnx;

    @Override
    public void ajouter(Commande t) {
        try {
            String sql = "INSERT INTO `commande`( `utilisateur_id`, `date`, `id_cat`, `adresse_départ`, `nom_produit` ,`id_check`,`status_commande`,`capacite`,`commandeRapide`) VALUES"
                    + " ('" + t.getUtilisateur().getId() + "','" + t.getDate() + "','" + t.getCategorie().getId() + "','" + t.getAdresse_départ() + "','" + t.getNom_produit() + "','" + t.getCheckPoint().getId() + "','" + t.getStatus_commande() + "','" + t.getCapacite()+ "','" + t.getCommandeRapide()+"')";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Commande> afficher() {
        List<Commande> commandes = new ArrayList<>();

        try {
            String s = "SELECT * FROM commande join chekh_point on commande.id_check=chekh_point.id_check JOIN utilisateur on utilisateur.id=commande.utilisateur_id where archiver = 0";

            cnx = MyDB.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(s);
            while (rs.next()) {
                Commande c = new Commande();
                c.setId(rs.getInt("id"));
                c.setDate(rs.getDate("date"));
                c.setAdresse_départ(rs.getString("adresse_départ"));
                c.setNom_produit(rs.getString("nom_produit"));
                c.setCapacite(rs.getInt("capacite"));
                c.setCommandeRapide(rs.getByte("commandeRapide"));
                String Status = rs.getString("status_commande");
                STATUS_COMMANDE sc = STATUS_COMMANDE.valueOf(Status);
                c.setStatus_commande(sc);
               
                int id_user = rs.getInt("utilisateur_id");
                String nom_user = rs.getString("nom");
                   String role_user = rs.getString("role");
                Utilisateur user = new Utilisateur();
                user.setId(id_user);
                user.setNom(nom_user);
                user.setRole(role_user);
                
                 int id_check = rs.getInt("id_check");
                String destination = rs.getString("destination");
                CheckPoint check = new CheckPoint();
                check.setId(id_check);
                check.setDestionation(destination);
                
                CategorieService cs = new CategorieService();
                Categorie categorie = new Categorie();
                 int id_cat = rs.getInt("id_cat");
                 for(Categorie cat : cs.afficher()){
                     if(cat.getId()==id_cat){
                         categorie.setId(id_cat);
                         categorie.setNom(cat.getNom());
                         categorie.setDescription(cat.getDescription());
                     }
                 }
                c.setCategorie(categorie);
                c.setUtilisateur(user);
                c.setCheckPoint(check);
                commandes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return commandes;
    }

    @Override
    public void modifier(Commande t) {
        try {
            String sql = "UPDATE `commande` SET `utilisateur_id`='" + t.getUtilisateur().getId() + "',`date`='" + t.getDate() + "',`id_cat`='" + t.getCategorie().getId() + "',`adresse_départ`='" + t.getAdresse_départ() + "',`nom_produit`='" + t.getNom_produit() + "',`id_check`='" + t.getCheckPoint().getId() + "',`status_commande`='" + t.getStatus_commande() +"',`capacite`='" + t.getCapacite()+"',`commandeRapide`='" + t.getCommandeRapide()+ "' WHERE id = " + t.getId() + "";
            cnx = MyDB.getInstance().getCnx();
            Statement statement = cnx.createStatement();
            statement.executeUpdate(sql);
            System.out.println("modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean supprimer(Commande t) {
        try {
            String sql = "UPDATE `commande` SET `archiver`='" + 1 + "' WHERE id = " + t.getId() + "";
            //String sql = " DELETE FROM `commande` WHERE `id` = " + t.getId() + "";
            cnx = MyDB.getInstance().getCnx();
            Statement statement = cnx.createStatement();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
