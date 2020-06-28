/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entities.Publication;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.pidev.tests.PI_Main;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.joda.time.DateTime;
/**
 *
 * @author Seif BelHadjAli
 */
public class CrudPublication {

    Connection cnx = DataSource.getInstance().getConnection();

    public void ajoutPublication(Publication p) {
        int current_user = PI_Main.getUser().getId();
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        
        try {
            String myQuery2 = "INSERT INTO publication (id_user,desc_p,date_p,date_modif,image,tags) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
            pst.setInt(1, current_user);
            pst.setString(2, p.getDesc_p());
            pst.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pst.setDate(4, date_sql);
            pst.setString(5, p.getImage());
            pst.setString(6, p.getTags());
            pst.executeUpdate();
            System.out.println("Publication ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connecion echouee");
        }
    }

    public void updatePublication(Publication p, int id) {
        try {
            String myQuery10 = "Update publication set desc_p = ?,tags = ?,image = ? WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(4, id);
            ps10.setString(1, p.getDesc_p());
            ps10.setString(2, p.getTags());
            ps10.setString(3, p.getImage());
            ps10.executeUpdate();
            System.out.println("Publication mis a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerPublication(Publication p, int id) {
        try {
            String myQuery10 = "DELETE FROM publication WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(1, id);
            ps10.executeUpdate();
            System.out.println("Publication supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Publication> selectAll() {
        List<Publication> publication = new ArrayList<Publication>();
        String req = "select * from publication";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                Publication p = new Publication(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                publication.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudPublication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publication;
    }

    public List<Publication> afficherPublication() {
        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication where desc_p IS NOT NULL ORDER BY date_p DESC";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication c = new Publication(
                            rs1.getInt("id"),
                            rs1.getInt("id_user"),
                            rs1.getString("desc_p"),
                            rs1.getTimestamp("date_p"),
                            rs1.getDate("date_modif"),
                            rs1.getString("image"),
                            rs1.getString("tags"));


                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

    public List<Publication> afficherPublication2() {
        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication where image is not null";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication c = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(7), rs1.getString(6));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }

}
