/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Date;

/**
 *
 * @author Chibani Kanaan
 */
public class Annonce {

    public int id;
    public int id_user;
    public String titre_a;
    public String desc_a;
    public String categorie_a;
    public int num_tel;
    public Date date_a;
    public Date date_modif;
    public String etat;

  
   

    public Annonce(int id_user, String titre_a, String desc_a, String categorie_a, int num_tel, Date date_a/*, Date date_modif, String etat*/) {

      
        this.id_user = id_user;
        this.titre_a = titre_a;
        this.desc_a = desc_a;
        this.categorie_a = categorie_a;
        this.num_tel = num_tel;
        this.date_a = date_a;
        //this.date_modif = date_modif;
        //this.etat = etat;
    }
    
    public Annonce( int id, int id_user, String titre_a, String desc_a, String categorie_a, int num_tel, Date date_a, Date date_modif, String etat) {

        this.id = id;
        this.id_user = id_user;
        this.titre_a = titre_a;
        this.desc_a = desc_a;
        this.categorie_a = categorie_a;
        this.num_tel = num_tel;
        this.date_a = date_a;
        this.date_modif = date_modif;
        this.etat = etat;
    }

    public Annonce() {
         //To change body of generated methods, choose Tools | Templates.
    }

    public Annonce(String titre_a,  String desc_a, String categorie_a,int num_tel,String etat) {
       
        this.titre_a = titre_a;
        this.desc_a = desc_a;
        this.categorie_a = categorie_a;
        this.num_tel = num_tel;
        this.etat = etat;
        
    }

    
    
    
    /**
     * ***************Getter and Setter **************
     */
    
    /**
     * @return **********
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    /**
     * @return ***********/
    public int getId_user() {
        return id_user;
    }

     public int getId() {
        return id;
    }
    
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

     /**
     * @return ***********/
    public String getTitre_a() {
        return titre_a;
    }

    public void setTitre_a(String titre_a) {
        this.titre_a = titre_a;
    }

     /**
     * @return ***********/
    public String getDesc_a() {
        return desc_a;
    }

    public void setDesc_a(String desc_a) {
        this.desc_a = desc_a;
    }

     /**
     * @return ***********/
    public String getCategorie_a() {
        return categorie_a;
    }

    public void setCategorie_a(String categorie_a) {
        this.categorie_a = categorie_a;
    }

     /**
     * @return ***********/
    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

     /**
     * @return ***********/
    public Date getDate_a() {
        return date_a;
    }

    public void setDate_a(Date date_a) {
        this.date_a = date_a;
    }

     /**
     * @return ***********/
    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }

     /**
     * @return ***********/
    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    /**
     * ********toString**********
     * @return 
     */
    @Override
    public String toString() {
        return "Annonce{" +  ", id_user=" + id_user + ", titre_a=" + titre_a + ", desc_a=" + desc_a + ", categorie_a=" + categorie_a + ", num_tel=" + num_tel + ", date_a=" + date_a + ", date_modif=" + date_modif + ", etat=" + etat + '}';
    }

}
