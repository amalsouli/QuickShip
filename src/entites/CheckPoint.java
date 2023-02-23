/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author asus
 */
public class CheckPoint {
    private int id;
    private String destionation; 

    public CheckPoint(int id, String destionation) {
        this.id = id;
        this.destionation = destionation;
    }

    public CheckPoint() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestionation() {
        return destionation;
    }

    public void setDestionation(String destionation) {
        this.destionation = destionation;
    }

    @Override
    public String toString() {
        return "CheckPoint{" + "id=" + id + ", destionation=" + destionation + '}';
    }
    
}
