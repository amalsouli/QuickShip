/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutulisateur;

import gestionutulisateur.entities.utulisateur;
import gestionutulisateur.Services.ServiceUtulisateur;
import gestionutulisateur.Utils.MyDB;

/**
 *
 * @author chino
 */
public class Gestionutulisateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //     A a = A.getInstance();
//     A a1 =A.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());
//        MyDB a = MyDB.getInstance();
//        MyDB a1 = MyDB.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());

      utulisateur p = new utulisateur("amaal", "souli","amal.souli@esssprit.tn","1482001","Jendouba","28758826");
    utulisateur p1 = new utulisateur( 7,"souli", "malek","souli.maalek@essprit.tn","131212","temime","51013000");
       


      ServiceUtulisateur sp = new ServiceUtulisateur();

   // sp.add(p);
   // sp.supprimer(7);
     sp.modifier(p1);
    //System.out.println( sp.afficher());
      
      
    
      
      
      
    }
    
}