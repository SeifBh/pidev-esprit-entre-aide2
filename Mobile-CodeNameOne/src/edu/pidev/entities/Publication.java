/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

//import org.joda.time.DateTime;
import java.util.Date;

/**
 *
 * @author Seif BelHadjAli
 */
public class Publication {

    public int id;
    public int user_id;
    public String desc_p;
    public Date date_p;
    public Date date_modif;
    public String tags;
    public String image;

    public Publication() {
    }

    public Publication(int id) {
        this.id = id;
    }

    public Publication(int id, String desc_p, String tags, String image) {
        this.id = id;
        this.desc_p = desc_p;
        this.tags = tags;
        this.image = image;
    }

    public Publication(int id, String desc_p, String image) {
        this.id = id;
        this.desc_p = desc_p;
        this.image = image;
    }



//    public Publication(int id , int user_id, String desc_p, Date date_p, Date date_modif, String image, String tags) {
//
//        this.id = id;
//        this.user_id = user_id;
//        this.desc_p = desc_p;
//        this.date_p = date_p;
//        this.date_modif = date_modif;
//        this.image = image;
//        this.tags = tags;
//
//    }
    public Publication(int id, int user_id, String desc_p, Date date_p, Date date_modif, String image, String tags) {
        this.id = id;
        this.user_id = user_id;
        this.desc_p = desc_p;
        this.date_p = date_p;
        this.date_modif = date_modif;
        this.image = image;
        this.tags = tags;
    }


        public Publication(int id, int user_id, String desc_p,  String image, String tags) {
        this.id = id;
        this.user_id = user_id;
        this.desc_p = desc_p;
        this.image = image;
        this.tags = tags;
    }
        

    public int getId() {
        return id;
    }

    public Date getDate_p() {
        return date_p;
    }

    public void setDate_p(Date date_p) {
        this.date_p = date_p;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDesc_p() {
        return desc_p;
    }

    public void setDesc_p(String desc_p) {
        this.desc_p = desc_p;
    }

    public Date getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Date date_modif) {
        this.date_modif = date_modif;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", user_id=" + user_id + ", desc_p=" + desc_p + ", date_p=" + date_p + ", date_modif=" + date_modif + ", tags=" + tags + ", image=" + image + '}';
    }

}
