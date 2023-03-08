/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ThinkPad
 */
public class maintenace {
    public int id;
    public TYPE TYPE;
    public Utilisateur utilisateur;
    public Date date;
    public Vehicule vehicule;

    public maintenace() {
        
    }

    public maintenace(TYPE type,  Utilisateur utilisateur, Date date,Vehicule v) {
        
        this.TYPE = type;
        this.date = date;
        this.utilisateur = utilisateur;
        this.vehicule=v;
    }

    public maintenace(int id, TYPE type, Utilisateur utilisateur, Date date,Vehicule v){
        this.id = id;
        this.TYPE = type;
        this.utilisateur = utilisateur;
          
           this.date = date;
           this.vehicule=v;
    }

    public TYPE getType() {
        return TYPE;
    }

    public void setType(TYPE type) {
        this.TYPE = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
       
        
          
        return  date;
    }

    public void setDate(Date date) {
          
        this.date = date;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "maintenace{" + "id=" + id + ", type=" + TYPE + ", utilisateur=" + utilisateur + ", date=" + date + '}';
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

   
    
}
