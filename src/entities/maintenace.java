/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Louay
 */
public class maintenace {

    public int id;
    public TYPE TYPE;
    public utilisateur utilisateur;
    public String date;

    public maintenace() {
    }

    public maintenace(TYPE type,  utilisateur utilisateur, String date) {
        this.TYPE = type;
        this.date = date;
        this.utilisateur = utilisateur;
    }

    public maintenace(int id, TYPE type, utilisateur utilisateur, String date) {
        this.id = id;
        this.TYPE = type;
        this.utilisateur = utilisateur;
        this.date = date;
    }

    public TYPE getType() {
        return TYPE;
    }

    public void setType(TYPE type) {
        this.TYPE = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "maintenace{" + "id=" + id + ", type=" + TYPE + ", utilisateur=" + utilisateur + ", date=" + date + '}';
    }

    public String getTYPE() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTYPE(TYPE type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
