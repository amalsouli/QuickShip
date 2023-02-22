/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ThinkPad
 */
public class Conducteur {
    private int id;
    private String nom;

    @Override
    public String toString() {
        return  nom ; 
    }

    public Conducteur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    

    public String getNom() {
        return nom ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Conducteur() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
