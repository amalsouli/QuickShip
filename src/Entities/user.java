package Entities;

import java.util.Objects;

public class user {
    
    private int id;
    private String nom_u;
    private String prenom_u;
    private int age;
    private String adresse;
    private String tel;
    private String role;
    private String genere;
    private String pwd;

    public user(){}
    
    public user(int id, String nom_u, String prenom_u, int age, String adresse, String tel, String role, String genere,String pwd) {
        this.id = id;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.age = age;
        this.adresse = adresse;
        this.tel = tel;
        this.role = role;
        this.genere = genere;
        this.pwd=pwd;
    } 
     public user(String nom_u, String prenom_u, int age, String adresse, String tel,String pwd) {    
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.age = age;
        this.adresse = adresse;
        this.tel = tel;
        this.pwd=pwd;
    }
     public user(String nom_u, String prenom_u, int age, String adresse, String tel, String role, String genere,String pwd) {    
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.age = age;
        this.adresse = adresse;
        this.tel = tel;
        this.role = role;
        this.genere = genere;
        this.pwd=pwd;
    }
     
        public user(String nom_u, String prenom_u, int age, String adresse , String role, String genere,String pwd) {    
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.age = age;
        this.adresse = adresse;
        this.role = role;
        this.genere = genere;
        this.pwd=pwd;
    }
     

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_u() {
        return nom_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public String getPrenom_u() {
        return prenom_u;
    }

    public void setPrenom_u(String prenom_u) {
        this.prenom_u = prenom_u;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.nom_u);
        hash = 17 * hash + Objects.hashCode(this.prenom_u);
        hash = 17 * hash + this.age;
        hash = 17 * hash + Objects.hashCode(this.adresse);
        hash = 17 * hash + Objects.hashCode(this.tel);
        hash = 17 * hash + Objects.hashCode(this.role);
        hash = 17 * hash + Objects.hashCode(this.genere);
        hash = 17 * hash + Objects.hashCode(this.pwd);
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
        final user other = (user) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.nom_u, other.nom_u)) {
            return false;
        }
        if (!Objects.equals(this.prenom_u, other.prenom_u)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.tel, other.tel)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.genere, other.genere)) {
            return false;
        }
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", nom_u=" + nom_u + ", prenom_u=" + prenom_u + ", age=" + age + ", adresse=" + adresse + ", tel=" + tel + ", role=" + role + ", genere=" + genere + ", pwd=" + pwd + '}';
    }

        
}

