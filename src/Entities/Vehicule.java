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
public class Vehicule {
    private int id;
    private String marque;

    public Vehicule(int id, String marque) {
        this.id = id;
        this.marque = marque;
    }

    @Override
    public String toString() {
        return  marque  ;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Vehicule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
