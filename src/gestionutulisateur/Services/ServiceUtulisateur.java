/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionutulisateur.Services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import gestionutulisateur.entities.utulisateur;
import gestionutulisateur.Utils.MyDB;

/**
 *
 * @author chino
 */

public class ServiceUtulisateur implements IService<utulisateur> {
    Connection cnx;
    @Override
    
    public void add(utulisateur t) {
        try {
        String qry ="INSERT INTO `utulisateur` ( `nom`, `prenom`, `email`, `mot_de_passe`, `adresse`, `telephone`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getEmail()+"','"+t.getMot_de_passe()+"','"+t.getAdresse()+"','"+t.getTelephone()+"')";

        cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }


    @Override
    
    public List<utulisateur> afficher() {
        List<utulisateur> utulisateurs = new ArrayList<>();
      try {
            String qry ="SELECT * FROM utulisateur ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
              utulisateur p =new utulisateur();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("id"));
                p.setPrenom(rs.getString(3));
                utulisateurs.add(p);
            }
            return utulisateurs;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utulisateurs;
        
    }


   // @Override
    public void modifier(utulisateur t) {
 try {
          String qry ="UPDATE utulisateur SET `nom`= "+t.getNom()+",`prenom`="+t.getPrenom()+",`email`="+t.getEmail()+",`mot_de_passe`="+t.getMot_de_passe()+",`adresse`="+t.getAdresse()+",`telephone`="+t.getTelephone()+"WHERE id="+t.getId()+";";

            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        } 
        }

    
   // @Override
    

    public void supprimer(int id) {
try {
            String qry ="DELETE from utulisateur t where id="+id+";";
           
         cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    }
}
