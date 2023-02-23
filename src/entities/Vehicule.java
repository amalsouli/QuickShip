/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Vehicule {
    
     private int id, annee, capacite;
     private String marque, modele,matricule;
     private TYPE_VEHICULE type;
     private Remorque remorque;
     private Utilisateur utilisateur;

    public Vehicule(int annee, int capacite, String marque, String modele, TYPE_VEHICULE type ,Remorque remorque,Utilisateur utilisateur, String matricule) {
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.remorque=remorque;
        this.utilisateur=utilisateur;
        this.matricule=matricule;
    }

    public Vehicule(int annee, int capacite, String marque, String modele, TYPE_VEHICULE type, Utilisateur utilisateur,String matricule) {
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.utilisateur = utilisateur;
                this.matricule=matricule;

    }

     
    
    public Vehicule() {
    }

    public Vehicule(int id, int annee, int capacite, String marque, String modele, TYPE_VEHICULE type,Remorque remorque,Utilisateur utilisateur, String matricule) {
        this.id = id;
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.remorque=remorque;
        this.utilisateur=utilisateur;
        this.matricule=matricule;
    }
       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
    
    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Remorque getRemorque() {
        return remorque;
    }

    public void setRemorque(Remorque remorque) {
        this.remorque = remorque;
    }

    public TYPE_VEHICULE getType() {
        return type;
    }

    public void setType(TYPE_VEHICULE type) {
        this.type = type;
    }
       
    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", annee=" + annee + ", capacite=" + capacite + ", marque=" + marque + ", modele=" + modele + ", matricule=" + matricule + ", type=" + type + ", remorque=" + remorque + ", utilisateur=" + utilisateur + '}';
    }
    
    

   
    

  

   }
