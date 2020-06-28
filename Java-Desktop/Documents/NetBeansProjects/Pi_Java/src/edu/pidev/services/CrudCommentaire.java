/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
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
public class CrudCommentaire {

    Connection cnx = DataSource.getInstance().getConnection();

    public void ajoutCommentaire(Commentaire c,int id,String conp) {
        int current_user = PI_Main.getUser().getId();
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            String myQuery2 = "INSERT INTO commentaire (id_user,id_publication,date_creation,date_modif,content_comm) VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
            pst.setInt(1, current_user);
            pst.setInt(2, id);
            pst.setDate(3, date_sql);
            pst.setDate(4, date_sql);
            pst.setString(5, conp);
            pst.executeUpdate();
            System.out.println("Commentaire ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connecion echouee");
        }
    }

    public void updateCommentaire(Commentaire p, int id) {
        try {
            String myQuery10 = "Update commentaire set content_comm = ? WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(2, id);
            ps10.setString(1, p.getContent_comm());
            ps10.executeUpdate();
            System.out.println("Commentaire mis a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerCommentaire(Commentaire c, int id) {
        try {
            String myQuery10 = "DELETE FROM commentaire WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(1, id);
            ps10.executeUpdate();
            System.out.println("Commentaire supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Commentaire> afficherCommentaire() {
        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire ";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(rs1.getInt("id"), rs1.getInt("id_user"), rs1.getInt("id_publication"), rs1.getDate("date_creation"), rs1.getDate("date_modif"), rs1.getString("content_comm"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }
    
        public List<Commentaire> afficherCommentaire2(int id) {
        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire where id_publication='"+id+"'" ;
            System.out.println(req);
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(rs1.getInt("id"), rs1.getInt("id_user"), rs1.getInt("id_publication"), rs1.getDate("date_creation"), rs1.getDate("date_modif"), rs1.getString("content_comm"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;

    }
        
        
}
