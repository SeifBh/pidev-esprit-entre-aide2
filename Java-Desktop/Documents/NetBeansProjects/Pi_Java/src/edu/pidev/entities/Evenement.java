/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entities;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Amine
 */
public class Evenement {
    
     
    private int id;

    
    private String titre_e;

    /**
     * @var string
     *
     * @ORM\Column(name="desc_e", type="text", nullable=true)
     */
    private String desc_e;

    
    private Timestamp date_e;


    /**
     * @ORM\ManyToOne(targetEntity="EspritEntreAide\UserBundle\Entity\User", inversedBy="evenements")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id")
     */
    private int id_user;

    /**
     * @var string
     *
     * @ORM\Column(name="usr_role", type="string", nullable=true)
     */
    private String usr_role;

   


    /**
     * @ORM\ManyToMany(targetEntity="EspritEntreAide\UserBundle\Entity\User", inversedBy="participations")
     * @ORM\JoinTable(name="participants_evenements")
     */
    private List<Utilisateur> participants;

    
    /**
     * @ORM\ManyToOne(targetEntity="EspritEntreAide\ClubBundle\Entity\Club", inversedBy="evenements")
     * @ORM\JoinColumn(name="id_club",referencedColumnName="id")
     */
    private int id_club;


    /**
     * @ORM\Column(type="string")
     *
     * @Assert\NotBlank(message="Ajouter une image jpg")
     * @Assert\File(mimeTypes={ "image/jpeg" })
     */
    private String image;

        /**
     * @var string
     *
     * @ORM\Column(name="type_e", type="string", length=255, nullable=true)
     */
    private String type_e;

    
    private int etat;

    




    
    private String googlemaps;

    




    
    private Timestamp date_modif;

    
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre_e() {
        return titre_e;
    }

    public void setTitre_e(String titre_e) {
        this.titre_e = titre_e;
    }

    public String getDesc_e() {
        return desc_e;
    }

    public void setDesc_e(String desc_e) {
        this.desc_e = desc_e;
    }

    public Timestamp getDate_e() {
        return date_e;
    }

    public void setDate_e(Timestamp date_e) {
        this.date_e = date_e;
    }

    

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsr_role() {
        return usr_role;
    }

    public void setUsr_role(String usr_role) {
        this.usr_role = usr_role;
    }

    public List<Utilisateur> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }
    
    public void addParticipants(Utilisateur u){
    this.participants.add(u);
    }
    
    public void removeParticipants(Utilisateur u)
    {
        this.participants.remove(u);
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType_e() {
        return type_e;
    }

    public void setType_e(String type_e) {
        this.type_e = type_e;
    }

   

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getGooglemaps() {
        return googlemaps;
    }

    public void setGooglemaps(String googlemaps) {
        this.googlemaps = googlemaps;
    }

    public Timestamp getDate_modif() {
        return date_modif;
    }

    public void setDate_modif(Timestamp date_modif) {
        this.date_modif = date_modif;
    }

    

    public Evenement(String titre_e, String desc_e, Timestamp date_e, int id_user, String usr_role, int idClub, String image, String type_e, String googlemaps) {
        this.titre_e = titre_e;
        this.desc_e = desc_e;
        this.date_e = date_e;
        this.id_user = id_user;
        this.usr_role = usr_role;
        this.image = image;
        this.etat=0;
        this.type_e = type_e;
        this.id_club=idClub;
        this.googlemaps=googlemaps;
    }
    
    
    
   // titre_e,desc_e,date_e,id_user,usr_role,id_club,image,type_e,googlemaps

    public Evenement() {
    }

    @Override
    public String toString() {
        return "Evenement:" + "id=" + id + "\ntitre_e= " + titre_e + "\ndesc_e= " + desc_e + "\ndate_e= " + date_e + "\nid_user= " + id_user + "\nusr_role= " 
                + usr_role + "\nparticipants= " + participants + "\nid_club= " + id_club + "\nimage= " + image + "\ntype_e= " + type_e +  "\ngooglemaps= " + googlemaps;
    }
    
    
    
}
