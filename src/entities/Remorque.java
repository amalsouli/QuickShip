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
public class Remorque {
    
    private int id_remorque;
    private int longueur;
    private int largeur;
    private int capacite;
    private String couleur;
    private String marque;

    public Remorque() {
    }
    public Remorque(int id_remorque) {
        this.id_remorque=id_remorque;
    }

    public Remorque(int id_remorque, int longueur, int largeur, int capacite, String couleur, String marque) {
        this.id_remorque = id_remorque;
        this.longueur = longueur;
        this.largeur = largeur;
        this.capacite = capacite;
        this.couleur = couleur;
        this.marque = marque;
    }

    public Remorque(int longueur, int largeur, int capacite, String couleur, String marque) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.capacite = capacite;
        this.couleur = couleur;
        this.marque = marque;
    }


    public int getId_remorque() {
        return id_remorque;
    }

    public int getLongueur() {
        return longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getMarque() {
        return marque;
    }

    public void setId_remorque(int id_remorque) {
        this.id_remorque = id_remorque;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Remorque{" + "id_remorque=" + id_remorque + ", longueur=" + longueur + ", largeur=" + largeur + ", capacite=" + capacite + ", couleur=" + couleur + ", marque=" + marque + '}';
    }
    
    
    
}
