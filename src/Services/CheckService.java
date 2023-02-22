/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.CheckPoint;
import Entities.Conducteur;
import Entities.Trajet;
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
public class CheckService {
    Connection cnx;
     public CheckService() {
        cnx = MyDB.getInstance().getCnx();
    }
     public void ajouter(CheckPoint C) throws SQLException {
           String req = "INSERT INTO chekh_point(`heure`,`minute`,`id_trajet`,`destination`,`etat`) VALUES("
                +  C.getHeure() + "," + C.getMin()+ "," + C.getTrajet().getId()+ ",'"+C.getAdresse()+"',"+1+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
     public List<CheckPoint> afficher() throws SQLException
     {
         List<CheckPoint> ch=new ArrayList<>();
         String requ="select * from chekh_point where etat=1 ";
         Statement st = cnx.createStatement();
         ResultSet rs =  st.executeQuery(requ);
         while(rs.next()){
             CheckPoint c=new CheckPoint();
             c.setId(rs.getInt("id_check"));
             c.setHeure(rs.getInt("heure"));
             c.setMin(rs.getInt("minute"));
             c.setAdresse(rs.getString("destination"));
             TrajetService ts =new TrajetService();
             for(Trajet t:ts.afficher())
                     {
                       if(t.getId()==rs.getInt("id_trajet"));
                       c.setTrajet(t);
                       c.setDate(t.getDate());
                     }
             ch.add(c);
             
         }
         return ch;
     }
       public boolean Supprimer_check(int id) throws SQLException
    {
        String req="update  chekh_point SET etat=0 where id_check="+id+";";
        Statement st = cnx.createStatement();
        int rowsDeleted = st.executeUpdate(req);
        System.out.println("rowsdeleted"+rowsDeleted);
        return true;
    }
       public void modifier_check(CheckPoint C) throws SQLException
       {
           String req = "UPDATE chekh_point SET Heure = ?,Minute = ?,id_trajet = ?,destination= ? where id_check = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, C.getHeure());
        ps.setInt(2, C.getMin());
        ps.setInt(3, C.getTrajet().getId());
        ps.setString(4, C.getAdresse());
        ps.setInt(5, C.getId());
        
        
        ps.executeUpdate();
       }
      
       }


