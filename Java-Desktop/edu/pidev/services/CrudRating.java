/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
import edu.pidev.entities.Rating;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.pidev.tests.PI_Main;
/**
 *
 * @author Seif BelHadjAli
 */
public class CrudRating {
    Connection cnx = DataSource.getInstance().getConnection();

    public void ajoutRating(Rating r,int id_publication,int id_user,String rating) {
        int current_user = PI_Main.getUser().getId();
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            String myQuery2 = "INSERT INTO rating (id_publication,id_user,created,rating) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
            pst.setInt(1, id_publication);
            pst.setInt(2, current_user);
            pst.setDate(3, date_sql);
            pst.setString(4, rating);
            pst.executeUpdate();
            System.out.println("Rating ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connecion echouee");
        }
    }  
        public List<Rating> afficherrating() {
        List<Rating> myList = new ArrayList<Rating>();

        try {
            String req = "SELECT * FROM rating ";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Rating r = new Rating(
                        rs1.getInt("id"),
                        rs1.getInt("id_publication"),
                        rs1.getInt("id_user"),
                        rs1.getDate("created"),
                        rs1.getString("rating")
                );

                myList.add(r);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }
}
