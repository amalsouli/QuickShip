/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author USER
 */
public interface IServices <T>{
    
    public void ajouter(T t);
    public void modifier(T t);
    public boolean supprimer(T t);
    public List<T> afficher();
    
    
}
