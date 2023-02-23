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
public class Utilisateur {
    
    private int utilisateur_id;
    private String nom;
    private String prenom;
    private String email;
    private String mot_de_passe;
    private String adresse;
    private String telephone;
    private String role;

    public Utilisateur(int utilisateur_id, String nom, String prenom, String email, String mot_de_passe, String adresse, String telephone, String role) {
        this.utilisateur_id = utilisateur_id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.adresse = adresse;
        this.telephone = telephone;
        this.role = role;
    }

    public Utilisateur() {
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getRole() {
        return role;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "utilisateur_id=" + utilisateur_id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", mot_de_passe=" + mot_de_passe + ", adresse=" + adresse + ", telephone=" + telephone + ", role=" + role + '}';
    }
    
    
}
