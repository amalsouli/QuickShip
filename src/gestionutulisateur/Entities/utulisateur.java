/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionutulisateur.entities;

/**
 *
 * @author amal
 */
public class utulisateur {
        private int id ;
    private String nom,prenom,email,mot_de_passe,adresse,telephone;

    public utulisateur() {
    }

    public utulisateur( String nom, String prenom, String email,  String mot_de_passe,String adresse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
         this.mot_de_passe = mot_de_passe;
         this.adresse = adresse;
        this.telephone = telephone;
    }

    public utulisateur(int id,  String nom, String prenom, String email,  String mot_de_passe, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
         this.mot_de_passe = mot_de_passe;
         this.adresse = adresse;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
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

    public String getAdresse() {
        return adresse;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "utulisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email +",mot_de_passe=" + mot_de_passe + ",adresse=" + adresse + ",telephone=" + telephone + "\n}";
                                                                                                                                                                                            
    }
    
}
    
    
