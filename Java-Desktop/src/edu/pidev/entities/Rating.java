/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Date;

/**
 *
 * @author Seif BelHadjAli
 */
public class Rating {

    public int id;
    public int id_publication;
    public int id_user;
    public Date created;
    public String rating;

    public Rating() {
    }

    public Rating(int id_publication, int id_user, Date created, String rating) {
        this.id_publication = id_publication;
        this.id_user = id_user;
        this.created = created;
        this.rating = rating;
    }

    
    public Rating(int id, int id_publication, int id_user, Date created, String rating) {
        this.id = id;
        this.id_publication = id_publication;
        this.id_user = id_user;
        this.created = created;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    

}
