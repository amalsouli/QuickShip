/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ThinkPad
 */
public class rapport {
     public int id;
    public String description;
    public int duree;
    public String piece;
    public int Cout;
    public String observation;

    private maintenace id_maintenance;

    public rapport(int id, String description, int duree, String piece, int Cout, String observation, maintenace id_maintenance) {
        this.id = id;
        this.description = description;
        this.duree = duree;
        this.piece = piece;
        this.Cout = Cout;
        this.observation = observation;
        this.id_maintenance = id_maintenance;
    }

    public rapport(String description, int duree, String piece, int Cout, String observation, maintenace id_maintenance) {
        this.description = description;
        this.duree = duree;
        this.piece = piece;
        this.Cout = Cout;
        this.observation = observation;
        this.id_maintenance = id_maintenance;
    }

    public rapport() {
    }

    public rapport(int idr, String des, int duR, String pi, int ctR, String ob, int idm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getPiece() {
        return piece;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPiece(String piece) {
        this.piece = piece;
    }

    public int getCout() {
        return Cout;
    }

    public void setCout(int Cout) {
        this.Cout = Cout;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public maintenace getId_maintenance() {
        return id_maintenance;
    }

    public void setId_maintenance(maintenace id_maintenance) {
        this.id_maintenance = id_maintenance;
    }

    @Override
    public String toString() {
        return "rapport{" + "id=" + id + ", description=" + description + ", duree=" + duree + ", piece=" + piece + ", Cout=" + Cout + ", observation=" + observation + ", id_maintenance=" + id_maintenance + '}';
    }

    public void open() {
        try {
            File file = new File("rapport.pdf");
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) {
                desktop.open(file);
            } else {
                System.out.println("Le fichier n'existe pas.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            File file = new File("rapport.pdf");
            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.edit(file);
            } else {
                System.out.println("Le fichier n'existe pas.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}