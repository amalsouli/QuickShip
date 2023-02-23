/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickship;

import entites.Categorie;
import entites.CheckPoint;
import entites.Commande;
import entites.STATUS_COMMANDE;
import entites.Utilisateur;
import java.util.Calendar;
import java.sql.Date;
import services.CategorieService;
import services.CommandeService;

/**
 *
 * @author asus
 */
public class QuickShip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //MyDB cnx = MyDB.getInstance();
        //cnx.getCnx();
        Utilisateur u = new Utilisateur(1, "selim");
        CheckPoint cp = new CheckPoint(1, "sousse");
        CategorieService catSer = new CategorieService();
        Categorie cat = new Categorie("fragile", "aaaaaa");
        Categorie catmod = new Categorie(1, "aaaaaaaaaa", "aaaa");
       // catSer.ajouter(cat);
        catSer.modifier(catmod);
        //catSer.supprimer(cat);
        //System.out.println(catSer.afficher());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 23);
        java.util.Date specifiedDate = calendar.getTime();
        Date sqlDate = new Date(specifiedDate.getTime());
        CommandeService commSer = new CommandeService();
        Commande c = new Commande(18,sqlDate, "aaaaaaaaaaaa", "aaaa", catmod, u, STATUS_COMMANDE.en_attente, cp,20);
       // commSer.ajouter(c);
        //commSer.modifier(c);    
        //commSer.supprimer(c);
     //   System.out.println(commSer.afficher()); 
    }

}
