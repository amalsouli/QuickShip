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
public class CheckPoint {
    private int id;
    private String adresse;
    private int heure ;
    private int min;
    private Date date;
    private ArrayList<Commande>cms;
    private Trajet trajet;

    @Override
    public String toString() {
        return "CheckPoint{" + "id=" + id + ", adresse=" + adresse + ", heure=" + heure + ", min=" + min + ", cms=" + cms + ", date=" + trajet.getDate().toString() + '}';
    }

    public CheckPoint() {
    }

    public CheckPoint(String adresse, int heure, int min, Trajet trajet) {
        this.adresse = adresse;
        this.heure = heure;
        this.min = min;
        
        this.trajet = trajet;
    }

    public CheckPoint(int id, String adresse, int heure, int min, Trajet trajet) {
        this.id = id;
        this.adresse = adresse;
        this.heure = heure;
        this.min = min;
        this.trajet = trajet;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public ArrayList<Commande> getCms() {
        return cms;
    }

    public void setCms(ArrayList<Commande> cms) {
        this.cms = cms;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

  
    
    
}
