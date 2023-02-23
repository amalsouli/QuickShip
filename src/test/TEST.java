/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Remorque;
import entities.Utilisateur;
import entities.Vehicule;
import entities.TYPE_VEHICULE;
import java.sql.Connection;
import services.RemorqueServices;
import services.VehiculeServices;
import utils.MyDB;

/**
 *
 * @author USER
 */
public class TEST {

    public static void main(String[] args) {
        // Connection cnx;
        //cnx = MyDB.getInstance().getCnx();
        Utilisateur u = new Utilisateur(2, "selim", "sahli", "selimsahli2@gmail.com", "123456789", "la marsa", "123456789", "");
        RemorqueServices rs = new RemorqueServices();
        Remorque r = new Remorque(3,10, 5, 4000, "verte", "dddddddddd");
         //rs.ajouter(r);
       // rs.Modifier(r);
       // rs.Supprimer(r);
        System.out.println(rs.afficher());
        VehiculeServices vs = new VehiculeServices();
        //Vehicule v = new Vehicule(2020, 1000, "ford", "dfghjklmù", TYPE_VEHICULE.semi_remorque,r,u);
       // vs.ajouter(v);
        //vs.Modifier(v);
        //vs.Supprimer(v);
 System.out.println(vs.afficher());        
    }
;

}
