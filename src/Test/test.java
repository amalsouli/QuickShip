/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Entities.CheckPoint;
import Entities.Conducteur;
import Entities.Trajet;
import Entities.Vehicule;
import Services.CheckService;
import Services.TrajetService;
import Utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author ThinkPad
 */
public class test {
     public static void main(String[] args) throws SQLException {
         Vehicule V =new Vehicule();
           Conducteur C =new Conducteur();
           V.setId(1);
          C.setId(1);
           TrajetService S =new TrajetService();
          Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2022);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 23);
            java.util.Date specifiedDate = calendar.getTime();
            Date sqlDate = new Date(specifiedDate.getTime());
            Trajet T =new Trajet(21,sqlDate,"tuuozer",V,C);
           // S.modifier(T);
          // System.out.println(S.afficher()); 
          System.out.println(S.afficher());
           CheckService ce = new CheckService();
                 CheckPoint c=new CheckPoint("marsa",2,3,T);
                
              //  ce.ajouter(c);   
               
           
            System.out.println(ce.afficher()); 
           
          // S.modifier(T);
          //S.Supprimer_trajet(8);
          //ce.Supprimer_check(1);
           
           
     }
}
