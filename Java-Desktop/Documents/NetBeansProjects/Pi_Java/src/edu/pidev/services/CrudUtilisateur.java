/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Utilisateur;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Seif BelHadjAli
 */
public class CrudUtilisateur {

    Connection cnx = DataSource.getInstance().getConnection();

    public void ajoutUtilisateur(Utilisateur u) {
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            String myQuery2 = "INSERT INTO user (username,username_canonical,email,email_canonical,enabled,salt,password, last_login, Confirmation_token ,password_requested_at,roles, nom, prenom, sexe) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getUsername_canonical());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getEmail_canonical());
            pst.setByte(5, u.getEnabled());
            pst.setString(6, u.getSalt());
            pst.setString(7, u.getPassword());
            pst.setDate(8, u.getLast_login());
            pst.setString(9, u.getConfirmation_token());
            pst.setDate(10, u.getPassword_requested_at());
            pst.setString(11, u.getRoles());
            pst.setString(12, u.getNom());
            pst.setString(13, u.getPrenom());
            pst.setString(14, u.getSexe());

            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connecion echouee");
        }
    }

    public void updateUtilisateur(Utilisateur u, int id) {
        try {
            String myQuery10 = "Update user set sexe = ? WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(2, id);
            ps10.setString(1, u.getSexe());
            ps10.executeUpdate();
            System.out.println("Utilisateur mis a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerUtilisateur(Utilisateur u, int id) {
        try {
            String myQuery10 = "DELETE FROM user WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(1, id);
            ps10.executeUpdate();
            System.out.println("Utilisateur supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> myList = new ArrayList<Utilisateur>();

        try {
            String myQuery30 = "SELECT * FROM user";
            PreparedStatement ps = cnx.prepareStatement(myQuery30);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt(1));
                    u.setUsername(rs.getString(2));
                    u.setUsername_canonical(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setEmail_canonical(rs.getString(5));
                    u.setEnabled(rs.getByte(6));
                    u.setPassword(rs.getString(8));
                    u.setLast_login(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
                    u.setRoles(rs.getString("roles"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setSexe(rs.getString("sexe"));
                    myList.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
     

    
}
