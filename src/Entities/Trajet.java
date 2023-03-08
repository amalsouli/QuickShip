/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ThinkPad
 */
public class Trajet {
    private int id;
    private Date date;
    private String  point_dep;
    private Vehicule Vehicule;
    private Conducteur Conducteur;
    private int etat;
    private String etatTraj;
    private ArrayList<Commande>commandes;

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", date=" + date + ", point_dep=" + point_dep + ", Vehicule=" + Vehicule + ", Conducteur=" + Conducteur +",etatTraj="+ etatTraj+", commandes=" + commandes + '}';
    }

    public Trajet(int id, Date date, String point_dep, Vehicule Vehicule, Conducteur Conducteur,String etat) {
        this.id = id;
        this.date = date;
        this.point_dep = point_dep;
        this.Vehicule = Vehicule;
        this.Conducteur = Conducteur;
        this.commandes = commandes;
        this.etatTraj=etat;
    }
    
    

    public Trajet(Date date, String point_dep, Vehicule Vehicule, Conducteur Conducteur) {
        this.date = date;
        this.point_dep = point_dep;
        this.Vehicule = Vehicule;
        this.Conducteur = Conducteur;
        
    }

    public Trajet() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPoint_dep() {
        return point_dep;
    }

    public void setPoint_dep(String point_dep) {
        this.point_dep = point_dep;
    }

    public Vehicule getVehicule() {
        return Vehicule;
    }

    public void setVehicule(Vehicule Vehicule) {
        this.Vehicule = Vehicule;
    }

    public Conducteur getConducteur() {
        return Conducteur;
    }

    public void setConducteur(Conducteur Conducteur) {
        this.Conducteur = Conducteur;
    }

    public ArrayList<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(ArrayList<Commande> commandes) {
        this.commandes = commandes;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getEtatTraj() {
        return etatTraj;
    }

    public void setEtatTraj(String etatTraj) {
        this.etatTraj = etatTraj;
    }
    
    
}
