/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.MyDB;
/**
 *
 * @author mahmo
 */
public class ServiceAdmin{
    
    
    Connection cnx = MyDB.getInstance().getCnx();
    private Object connexion;
    
    
   
    public void ajouterUser(user u) {
           try {
            
            String requete = "INSERT INTO admin (nom_u,prenom_u,age,adresse,tel,role,genere,pwd) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, u.getNom_u());
            pst.setString(2, u.getPrenom_u());
            pst.setInt(3, u.getAge());
            pst.setString(4, u.getAdresse());
            pst.setString(5, u.getTel());
            pst.setString(6, u.getRole());
            pst.setString(7, u.getGenere());
            pst.setString(8, u.getPwd());
            
            pst.executeUpdate();
            
             System.out.println("user ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     }
    public  List<user> selectAll() throws SQLException {
        //LIST
        List<user> arrayList = new ArrayList<>();
        //request 
        String req ="SELECT * FROM admin ";

            //insert
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next())
            {
                arrayList.add(new user(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }
        return arrayList;
    }
    
        public List<user> getAlla() {
        List<user> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM admin where id_u = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1,1);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new user(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public void supprimerUser(int id) {
    try {
        String req=" DELETE FROM admin WHERE id_user="+ id ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.executeUpdate();
        System.out.println("L'utilisateur est supprimé");}
     catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }    
   }

        public void update(user u,String tel) {
      
    try {
            String requete = "UPDATE admin SET nom_u=?,prenom_u=?,age=?,adresse=?,role=?,genere=?,pwd=? WHERE tel=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            
            pst.setString(1, u.getNom_u());
            pst.setString(2, u.getPrenom_u());
            pst.setInt(3, u.getAge());
            pst.setString(4, u.getAdresse());
            pst.setString(5, u.getRole());
            pst.setString(6, u.getGenere());
            pst.setString(7, u.getPwd());
            
            pst.setString(8, tel);
            
             pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    
        /*    Statement st  ;
        try {
        st = cnx.createStatement() ;
        st.executeUpdate() ;
        System.out.println("L'utilisateur est modifié");}
        catch (SQLException ex) {
            System.err.println(ex.getMessage());}*/
        
    }
    
    public List<user> Afficher() {
        return null;
    }
        public void supprimerUser_home(String nom,String prenom) {
        try {
            
        String req=" DELETE FROM admin WHERE (nom_u=? AND prenom_u=?)" ;

        PreparedStatement St = cnx.prepareStatement(req);
        St.setString(1, nom);
        St.setString(2,prenom);
        St.executeUpdate();
        System.out.println("L'utilisateur est supprimé");}
        
        catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
        
    }
   
        public user getUniqueUser(int id_u) {
        user u = new user();
        
        try {
            String requete = "SELECT * FROM admin WHERE id_user=" + id_u;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                u.setNom_u(rs.getString("nom_u"));
                u.setPrenom_u(rs.getString("prenom_u"));
                u.setAge(rs.getInt("age"));
                u.setAdresse(rs.getString("adresse"));
                u.setGenere(rs.getString("genere"));
                u.setRole(rs.getString("role"));
                u.setTel(rs.getString("tel"));
                u.setPwd(rs.getString("pwd"));
                
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return u;
    }
           public ObservableList<user> searchent2(String searchTerm) {
        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = cnx.prepareStatement("SELECT * FROM admin");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user offre = new user(
                          rs.getString(2), 
                          rs.getString(3),
                          rs.getInt(4),
                          rs.getString(5),
                          rs.getString(6),
                          rs.getString(7),
                          rs.getString(8),
                         rs.getString(9)
                         
                );
                list.add(offre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void supprimerClassUser() {
 String req = "DELETE * FROM user ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.executeUpdate();
            System.out.println("report supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }




}
