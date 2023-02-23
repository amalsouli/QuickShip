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
public class Conducteur {
    private int id;
    private String nom;

    @Override
    public String toString() {
        return  nom ; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Conducteur other = (Conducteur) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
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
