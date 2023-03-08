/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author ThinkPad
 */
public class Vehicule {
    private int id, annee, capacite;
    private String marque, modele, couleur, statut, matricule;
    private TYPE_VEHICULE type;
    private Remorque remorque;
    private Utilisateur utilisateur;

    public Vehicule(int annee, int capacite, String marque, String modele, TYPE_VEHICULE type, Remorque remorque, Utilisateur utilisateur, String matricule, String couleur, String statut) {
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.remorque = remorque;
        this.utilisateur = utilisateur;
        this.matricule=matricule;
        this.couleur = couleur;
        this.statut = statut;
    }

    public Vehicule(int annee, int capacite, String marque, String modele, TYPE_VEHICULE type, Utilisateur utilisateur, String matricule, String couleur, String statut) {
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.utilisateur = utilisateur;
        this.matricule=matricule;
        this.couleur = couleur;
        this.statut = statut;
    }

    public Vehicule() {
    }

    public Vehicule(int id, int annee, int capacite, String marque, String modele, TYPE_VEHICULE type, Remorque remorque, Utilisateur utilisateur, String matricule, String couleur, String statut) {
        this.id = id;
        this.annee = annee;
        this.capacite = capacite;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.remorque = remorque;
        this.utilisateur = utilisateur;
        this.matricule=matricule;
        this.couleur = couleur;
        this.statut = statut;
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

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", annee=" + annee + ", capacite=" + capacite + ", marque=" + marque + ", modele=" + modele + ", couleur=" + couleur + ", statut=" + statut + ", matricule=" + matricule + ", type=" + type + ", remorque=" + remorque + ", utilisateur=" + utilisateur + '}';
    }

}
