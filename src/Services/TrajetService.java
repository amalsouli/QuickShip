/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Trajet;
import Entities.Conducteur;
import Entities.Vehicule;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public class TrajetService {
     Connection cnx;
     public TrajetService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    public void ajouter(Trajet t) throws SQLException {
           String req = "INSERT INTO trajets(`date_depart`,`point_depart`,`vehicule_id`,`utilisateur_id`,`etat`) VALUES("
                + "'" + t.getDate() + "','" + t.getPoint_dep() + "'," + t.getVehicule().getId()+"," +t.getConducteur().getId()+","+1+ ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
    public List<Trajet> afficher() throws SQLException
    {
        
        List<Trajet> tra = new ArrayList<>();
       // String s = "select * from trajets";
        String s="SELECT trajets.id,trajets.date_depart,trajets.point_depart,trajets.utilisateur_id,trajets.vehicule_id,vehicule.modele,utilisateur.id,utilisateur.nom "
                + "FROM trajets join vehicule on trajets.vehicule_id=vehicule.id "
                + "JOIN utilisateur on utilisateur.id=trajets.utilisateur_id where trajets.etat=1; " ;    
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        
        while(rs.next()){
            Trajet t = new Trajet();
            t.setId(rs.getInt("id"));
            t.setDate(rs.getDate("date_depart"));
            t.setPoint_dep(rs.getString("point_depart"));
            int id=rs.getInt("vehicule_id");
            String marque=rs.getString("modele");
            int idc=rs.getInt("utilisateur_id");
            String nom=rs.getString("nom");
            Conducteur c =new Conducteur();
            Vehicule v =new Vehicule();
            v.setId(id);
            v.setMarque(marque);
            c.setId(idc);
            c.setNom(nom);
            t.setVehicule(v);
            t.setConducteur(c);  
            tra.add(t);
        }
        return tra;
    }
    public boolean Supprimer_trajet(int id) throws SQLException
    {
        String req="UPDATE trajets SET etat = 0 where id = "+id;
        Statement st = cnx.createStatement();
        int rowsDeleted = st.executeUpdate(req);
        System.out.println("rowsdeleted"+rowsDeleted);
        return true;
    }
     public void modifier(Trajet t) throws SQLException
       {
           
    
        String req = "UPDATE trajets SET date_depart = ?,point_depart = ?,vehicule_id=?,utilisateur_id=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setDate(1, t.getDate());
        ps.setString(2, t.getPoint_dep());
        ps.setInt(3, t.getVehicule().getId());
        ps.setInt(4, t.getConducteur().getId());
        ps.setInt(5, t.getId());
        ps.executeUpdate();
        
    }
}
