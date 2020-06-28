/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Date;
import java.util.List;


/**
 *
 * @author radhwen
 */
public class Club {
 public int id;
    public  int id_user;
    public  String nom_c;
    public String mail_c;
    public Date date_creation;
    public String image;
    public int nbmemb; 
    public List<Utilisateur> membres;
    public String desc_c;


    public Club() {
    }

    public Club(int id, int id_user, String nom_c, String mail_c, Date date_creation, String image, int nbmemb, List<Utilisateur> membres, String desc_c) {
        this.id = id;
        this.id_user = id_user;
        this.nom_c = nom_c;
        this.mail_c = mail_c;
        this.date_creation = date_creation;
        this.image = image;
        this.nbmemb = nbmemb;
        this.membres = membres;
        this.desc_c = desc_c;
    }

    public Club(int id, int id_user, String nom_c, String mail_c, Date date_creation, String image, int nbmemb, String desc_c) {
        this.id = id;
        this.id_user = id_user;
        this.nom_c = nom_c;
        this.mail_c = mail_c;
        this.date_creation = date_creation;
        this.image = image;
        this.nbmemb = nbmemb;
        this.desc_c = desc_c;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", id_user=" + id_user + ", nom_c=" + nom_c + ", mail_c=" + mail_c + ", date_creation=" + date_creation + ", image=" + image + ", nbmemb=" + nbmemb + ", membres=" + membres + ", desc_c=" + desc_c + '}';
    }

  

 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom_c() {
        return nom_c;
    }

    public void setNom_c(String nom_c) {
        this.nom_c = nom_c;
    }

    public String getMail_c() {
        return mail_c;
    }

    public void setMail_c(String mail_c) {
        this.mail_c = mail_c;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

  

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbmemb() {
        return nbmemb;
    }

    public void setNbmemb(int nbmemb) {
        this.nbmemb = nbmemb;
    }

    public List<Utilisateur> getMembres() {
        return membres;
    }

    public void setMembres(List<Utilisateur> membres) {
        this.membres = membres;
    }

    public String getDesc_c() {
        return desc_c;
    }

    public void setDesc_c(String desc_c) {
        this.desc_c = desc_c;
    }
    
    
    
       
}
