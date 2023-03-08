/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ThinkPad
 */
public interface IService <T>{
   
    
   public void ajouter(T t);
    public List<T> afficher();
    public void modifier(T t);
    public boolean supprimer(T t);
    

}
