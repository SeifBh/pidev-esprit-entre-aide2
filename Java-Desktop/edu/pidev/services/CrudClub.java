/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;
import edu.pidev.entities.Club;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radhwen
 */
public class CrudClub {
    Connection cnx = DataSource.getInstance().getConnection();

    public void ajoutClub(Club c) {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            String myQuery1 = "INSERT INTO club (id,id_user,nom_c, mail_c, date_creation,image,nbmemb,desc_c) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery1);
            pst.setInt(1, c.getId());
            pst.setInt(2, c.getId_user());
            pst.setString(3, c.getNom_c());
            pst.setString(4, c.getMail_c());
            pst.setDate(5, date_sql);
            pst.setString(6, c.getImage());
            pst.setInt(7, c.getNbmemb());
            pst.setString(8, c.getDesc_c());
            pst.executeUpdate();
            System.out.println("Club ajouté avec succès");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Connection perdu");
        }
    }

    public void updateClub(Club c, int id) {
        try {
            String myQuery1 = "Update club set desc_c = ? ,id_user = ? , nom_c = ? , mail_c = ? , nbmemb = ? , image = ? WHERE id = ? " ;
            PreparedStatement pst = cnx.prepareStatement(myQuery1);
            pst.setInt(7,id);
            pst.setInt(2, c.getId_user());
            pst.setString(3, c.getNom_c());
            pst.setString(4, c.getMail_c());
          //  pst.setDate(5, date_sql);
            pst.setString(6, c.getImage());
            pst.setInt(5, c.getNbmemb());
            pst.setString(1, c.getDesc_c());
            pst.executeUpdate();
            System.out.println("Club modifier avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerClub(Club c, int id) {
        try {
            String myQuery1 = "DELETE FROM club WHERE id=?";
            PreparedStatement ps1 = cnx.prepareStatement(myQuery1);
            ps1.setInt(1, id);
            ps1.executeUpdate();
            System.out.println("Club supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Club> afficherClub() {
        List<Club> myList = new ArrayList<Club>();

        try {
            String req = "SELECT * FROM club";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Club c = new Club(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getDate(5), rs1.getString(7), rs1.getInt(8), rs1.getString(6));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    
}
