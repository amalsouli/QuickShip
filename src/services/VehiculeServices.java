/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Remorque;
import entities.Utilisateur;
import entities.Vehicule;
import entities.TYPE_VEHICULE;
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
public class VehiculeServices implements IServices<Vehicule> {

    Connection cnx;

    public VehiculeServices() {
        this.cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vehicule t) {
        try {
            if (t.getType().equals(TYPE_VEHICULE.semi_remorque)) {
                String sql = "INSERT INTO `vehicule`( `marque`, `modele`, `annee`, `type`,`utilisateur_id`,`id_remorque`,`capacite`,`matricule`,`couleur`,`statut`) VALUES ('" + t.getMarque() + "','" + t.getModele() + "','" + t.getAnnee() + "','" + t.getType() + "','" + t.getUtilisateur().getUtilisateur_id() + "','" + t.getRemorque().getId_remorque() + "','" + t.getCapacite() + "','" + t.getMatricule()+ "','" + t.getCouleur() + "','"+t.getStatut()+"')";
                Statement st = cnx.createStatement();
                st.executeUpdate(sql);
            } else {
                String sql = "INSERT INTO `vehicule`( `marque`, `modele`, `annee`, `type`,`utilisateur_id`,`capacite`,`matricule`,`couleur`,`statut`) VALUES ('" + t.getMarque() + "','" + t.getModele() + "','" + t.getAnnee() + "','" + t.getType() + "','" + t.getUtilisateur().getUtilisateur_id() +  "','" + t.getCapacite() + "','" + t.getMatricule()+ "','" + t.getCouleur() + "','"+t.getStatut()+"')";
                Statement st = cnx.createStatement();
                st.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Vehicule t) {
        try {
            if (t.getType().equals(TYPE_VEHICULE.semi_remorque)) {

                String sql = "UPDATE `vehicule` SET `marque`='" + t.getMarque() + "',`modele`='" + t.getModele() + "',`annee`='" + t.getAnnee() + "',`type`='" + t.getType() + "',`utilisateur_id`='" + t.getUtilisateur().getUtilisateur_id() + "',`id_remorque`='" + t.getRemorque().getId_remorque() + "',`capacite`='" + t.getCapacite() + "',`matricule`='" + t.getMatricule()+ "',`couleur`='" + t.getCouleur() + "' ,`statut`='" + t.getStatut() + "'WHERE  id = '" + t.getId() + "'";
                //  String sql = "UPDATE vehicule SET marque = ?, modele = ?, annee = ?, type = ?, id_remorque = ?, capacite = ? WHERE id = ?";
                Statement st = cnx.createStatement();
                st.executeUpdate(sql);
                System.out.println("modifier");
            } else {
                String sql = "UPDATE `vehicule` SET `marque`='" + t.getMarque() + "',`modele`='" + t.getModele() + "',`annee`='" + t.getAnnee() + "',`type`='" + t.getType() + "',`utilisateur_id`='" + t.getUtilisateur().getUtilisateur_id() + "',`capacite`='" + t.getCapacite() + "',`matricule`='" + t.getMatricule() + "',`couleur`='" + t.getCouleur() + "',`statut`='" + t.getStatut() + "' WHERE  id = '" + t.getId() + "'";
                //  String sql = "UPDATE vehicule SET marque = ?, modele = ?, annee = ?, type = ?, id_remorque = ?, capacite = ? WHERE id = ?";
                Statement st = cnx.createStatement();
                st.executeUpdate(sql);
                System.out.println("modifier");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean supprimer(Vehicule t) {
        try {
            String sql = "UPDATE vehicule SET `etat`='" + 1 + "' WHERE id = " + t.getId() + "";
            // String sql = "DELETE FROM vehicule WHERE id = ?";
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<Vehicule> afficher() {
        List<Vehicule> v = new ArrayList<>();
        try {
            String sql = "SELECT vehicule.id,vehicule.marque,vehicule.modele,vehicule.annee,vehicule.type,vehicule.capacite,vehicule.matricule,vehicule.couleur,vehicule.statut,vehicule.id_remorque,vehicule.utilisateur_id,utilisateur.id,utilisateur.nom FROM vehicule JOIN utilisateur on utilisateur.id=vehicule.utilisateur_id WHERE  etat = '" + 0 + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Vehicule v1 = new Vehicule();
                v1.setId(rs.getInt("id"));
                v1.setMarque(rs.getString("marque"));
                v1.setModele(rs.getString("modele"));
                v1.setMatricule(rs.getString("matricule"));
                v1.setCouleur(rs.getString("couleur"));
                v1.setAnnee(rs.getInt("annee"));
                v1.setCapacite(rs.getInt("capacite"));
                String type = rs.getString("type");
                v1.setStatut(rs.getString("statut"));
                TYPE_VEHICULE tv = TYPE_VEHICULE.valueOf(type);
                v1.setType(tv);
                int id_user = rs.getInt("utilisateur_id");
                String nom = rs.getString("nom");
                Utilisateur u = new Utilisateur();
                u.setUtilisateur_id(id_user);
                u.setNom(nom);

                v1.setUtilisateur(u);
                int id_remorque = rs.getInt("id_remorque");

                if (id_remorque != 0) {
                    RemorqueServices rs1 = new RemorqueServices();
                    for (Remorque r : rs1.afficher()) {
                        if (r.getId_remorque() == id_remorque);
                        v1.setRemorque(r);
                    }
                }

                v.add(v1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return v;
    }
    
    public int calculNombreVehiculeParStatut(String statut){
        
        try
        {
    String sql = "SELECT COUNT(*) as nb FROM vehicule WHERE statut='"+statut+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("nb");
         
           }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return 0;
    }
    
   public int calculNombreVehiculeParMarque(String marque){
        
        try
        {
    String sql = "SELECT COUNT(*) as nb FROM vehicule WHERE marque='"+marque+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt("nb");
         
           }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return 0;
    }

    
    public List<String> listMarques() {
        List<String>  listVehicule = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT vehicule.marque FROM vehicule";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
              listVehicule.add(rs.getString("marque"));
            }
        }
           catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listVehicule;
    }
}
