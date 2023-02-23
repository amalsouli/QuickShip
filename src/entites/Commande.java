/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;
/**
 *
 * @author asus
 */
public class Commande {
    private int id ,capacite;	
    private Date date;
    private String adresse_départ;
    private String nom_produit;
    private Categorie categorie;
    private Utilisateur utilisateur;
    private CheckPoint checkPoint;
   private STATUS_COMMANDE status_commande;
   
    public Commande() {
    }

    public Commande(Date date, String adresse_départ, String nom_produit, Categorie categorie,Utilisateur utilisateur,STATUS_COMMANDE status_commande,CheckPoint checkPoint,int capacite) {
        this.date = date;
        this.adresse_départ = adresse_départ;
        this.nom_produit = nom_produit;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
        this.checkPoint = checkPoint;
        this.status_commande = status_commande;
        this.capacite=capacite;
    }

    public Commande(int id ,Date date, String adresse_départ, String nom_produit, Categorie categorie,Utilisateur utilisateur,STATUS_COMMANDE status_commande,CheckPoint checkPoint, int capacite) {
        this.id = id;
        this.date = date;
        this.adresse_départ = adresse_départ;
        this.nom_produit = nom_produit;
        this.categorie = categorie;
        this.utilisateur = utilisateur;
        this.checkPoint = checkPoint;
        this.status_commande = status_commande;
                this.capacite=capacite;

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

    public String getAdresse_départ() {
        return adresse_départ;
    }

    public void setAdresse_départ(String adresse_départ) {
        this.adresse_départ = adresse_départ;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

    public STATUS_COMMANDE getStatus_commande() {
        return status_commande;
    }

    public void setStatus_commande(STATUS_COMMANDE status_commande) {
        this.status_commande = status_commande;
    }
    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", capacite=" + capacite + ", date=" + date + ", adresse_d\u00e9part=" + adresse_départ + ", nom_produit=" + nom_produit + ", categorie=" + categorie + ", utilisateur=" + utilisateur + ", checkPoint=" + checkPoint + ", status_commande=" + status_commande + '}';
    }

        
}
