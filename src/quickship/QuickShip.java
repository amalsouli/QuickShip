/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickship;


import services.maintenaceService;
import entities.maintenace;
import services.rapportService;
import entities.rapport;
import entities.TYPE;
import entities.utilisateur;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.MyDB;

/**
 *
 * @author Louay
 */
public class QuickShip {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
       // MyDB cnx=MyDB.getInstance();
       //cnx.getCnx();
        utilisateur u = new utilisateur(1, "selim");
       Date date = new Date("12/12/2022");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf2.format(date);
  maintenace m = new maintenace(2,TYPE.corrective,u, formattedDate);
  try{
        maintenaceService ms = new maintenaceService();
              // ms.ajouter(m);
              //ms.modifier(m);
             // ms.supprimer(m);
           System.out.println(ms.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try{

        rapport R = new rapport(7,"joint_culasse", 4,"aaaaaaaaaaaaaaaaaaaa",3000,"trop_dure_a_faire",m);
            rapportService rs = new rapportService();
          //  rs.ajouter(R);
          //    rs.modifier(R);
           //  rs.supprimer(R);
            System.out.println(rs.recuperer());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

    

