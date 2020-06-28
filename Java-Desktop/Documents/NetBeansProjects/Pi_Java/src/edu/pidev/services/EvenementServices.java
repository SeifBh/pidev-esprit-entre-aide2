package edu.pidev.services;

import edu.pidev.entities.Evenement;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amine
 */
public class EvenementServices {
    
    private static EvenementServices instance;
    public static EvenementServices getInstance(){
    if (instance == null) {
            instance = new EvenementServices();
        }
        return instance;
    }
    Connection cnx = DataSource.getInstance().getConnection();
    public void ajouterEvenement(Evenement e) {
          
        try {
            String myQuery2 = "INSERT INTO evenement (titre_e,desc_e,date_e,id_user,usr_role,id_club,etat,image,type_e,googlemaps) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
            pst.setString(1, e.getTitre_e());
            pst.setString(2, e.getDesc_e());
            pst.setTimestamp(3, e.getDate_e());
            pst.setInt(4, e.getId_user());
            pst.setString(5, e.getUsr_role());
            pst.setInt(6, e.getId_club());
            pst.setInt(7, e.getEtat());
            pst.setString(8, e.getImage());
            pst.setString(9, e.getType_e());
            pst.setString(10, e.getGooglemaps());
            pst.executeUpdate();
            System.out.println("Evenement ajouté avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connection echouée");
        }
    }
    
    
    public void modifierEvenement(Evenement e,int id)
    {
             try {
            String myQuery10 = "Update evenement set titre_e=?, desc_e=? ,date_e = ? , image= ? , type_e= ? , googlemaps=?, etat=? WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(8,id);
            ps10.setString(1, e.getTitre_e());
            ps10.setString(2, e.getDesc_e());
            ps10.setTimestamp(3, e.getDate_e());
            ps10.setString(4, e.getImage());
            ps10.setString(5, e.getType_e());
            ps10.setString(6, e.getGooglemaps());
            ps10.setInt(7, e.getEtat());
            ps10.executeUpdate();
            System.out.println("Evenement mis à jour avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }
        public void supprimerEvenement(int id) {
        try {
            String myQuery10 = "DELETE FROM evenement WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(1, id);
            ps10.executeUpdate();
            System.out.println("Evenement supprimé avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
            public List<Evenement> afficherEvenement() {
                List<Evenement> myList = new ArrayList<>() ;

        try {
            String myQuery30 = "SELECT * FROM evenement";
            Statement st = cnx.createStatement();
           ResultSet rs = st.executeQuery(myQuery30);
            while (rs.next()) {
                
                Evenement e = new Evenement();
                
                e.setId(rs.getInt(1));
                e.setTitre_e(rs.getString(2));
                e.setDesc_e(rs.getString(3));
                e.setDate_e(rs.getTimestamp(4));
                e.setType_e(rs.getString(5));
                e.setId_user(rs.getInt(7));
                e.setId_club(rs.getInt(8));
                e.setEtat(rs.getInt(9));
                e.setUsr_role(rs.getString(10));
                e.setImage(rs.getString(11));
                e.setGooglemaps(rs.getString(12));
                myList.add(e);
               
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
}
