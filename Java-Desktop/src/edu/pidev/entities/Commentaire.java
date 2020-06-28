/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.util.Date;

/**
 *
 * @author Seif BelHadjAli
 */

public class Commentaire {
    public int id ;
    public int id_user;
    public int id_publication;
    public Date date_creation;
    public Date date_modif;
    public String content_comm;

    public Commentaire() {
    }

    public Commentaire( int id , int id_user, int id_publication, Date date_creation, Date date_modif, String content_comm) {
        
        
        this.id = id;
        this.id_user = id_user;
        this.id_publication = id_publication;
        this.date_creation = date_creation;
        this.date_modif = date_modif;
        this.content_comm = content_comm;
    }
    public Commentaire(String content_comm) {

        this.content_comm = content_comm;
    }
    
    public Commentaire(int id , String content_comm) {
                this.id = id;

        this.content_comm = content_comm;
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

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }

    public String getContent_comm() {
        return content_comm;
    }

    public void setContent_comm(String content_comm) {
        this.content_comm = content_comm;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_user=" + id_user + ", id_publication=" + id_publication + ", date_creation=" + date_creation + ", date_modif=" + date_modif + ", content_comm=" + content_comm + '}';
    }
    
    
}
