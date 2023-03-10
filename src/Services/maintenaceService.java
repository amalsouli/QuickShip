/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author ThinkPad
 */
import Entities.maintenace;
import Entities.TYPE;
import Entities.Utilisateur;
import Entities.Vehicule;
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
 * @author Louay
 */
public class maintenaceService {

    Connection cnx;

    public maintenaceService() {
        cnx = MyDB.getInstance().getCnx();
    }

    public void ajouter(maintenace t) throws SQLException {
        String req = "INSERT INTO `maintenace`(`type`, `date`,`utilisateur_id`,`vehicule_id`) VALUES("
                + "'" + t.getType() + "','" + t.getDate() + "','" + t.getUtilisateur().getId() + "',"+t.getVehicule().getId()+");";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);

    }

    public void modifier(maintenace t) throws SQLException {
        String req = "UPDATE `maintenace` SET `utilisateur_id`='" + t.getUtilisateur().getId() + "',`type`='" + t.getType() + "',`date`='" + t.getDate() + "' WHERE id = '" + t.getId() + "'";
        PreparedStatement ms = cnx.prepareStatement(req);
        ms.executeUpdate();
    }

    public List<maintenace> recuperer() throws SQLException {
        List<maintenace> maintenace = new ArrayList<>();
        String s =  " SELECT maintenace.id,maintenace.utilisateur_id,maintenace.type,maintenace.vehicule_id,maintenace.date,vehicule.matricule,utilisateur.id,utilisateur.nom " 
                  + "FROM maintenace join vehicule on maintenace.vehicule_id=vehicule.id " +
                    "JOIN utilisateur on utilisateur.id=maintenace.utilisateur_id where maintenace.etat=0; ";
        Statement st = cnx.createStatement();
        ResultSet ms = st.executeQuery(s);
        while (ms.next()) {
            maintenace M = new maintenace();
            M.setId(ms.getInt("id"));
            M.setDate(ms.getDate("date"));
            String type=ms.getNString("type");
            TYPE tv = TYPE.valueOf(type);
            M.setType(tv);
            Vehicule V =new Vehicule();
            Utilisateur u=new Utilisateur();
            int id_user=ms.getInt("utilisateur_id");
            String nom=ms.getString("nom");
            int id_vehic=ms.getInt("vehicule_id");
            String matr=ms.getString("matricule");
            
            V.setId(id_vehic);
            V.setMatricule(matr);
            u.setId(id_user);
            u.setNom(nom);
            M.setVehicule(V);
            M.setUtilisateur(u);
            System.out.println(ms.getType());
           
            maintenace.add(M);
        }
        return maintenace;
    }

    public boolean supprimer(maintenace t) throws SQLException {
        String sql = "UPDATE maintenace SET `etat`=1  WHERE id = " + t.getId() + ";";
        PreparedStatement ms = cnx.prepareStatement(sql);
        ms.executeUpdate();
        return true;
    }
}

