  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import edu.pidev.entities.Annonce;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chibani Kanaan
 */
public class GestionAnnonce {

    Connection cnx = DataSource.getInstance().getConnection();

    public void ajouterAnnonce(Annonce a) {
     
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        try {
            String myQuery2 = "INSERT INTO annonce (id_user, titre_a, desc_a, categorie_a, num_tel,date_a, etat) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(myQuery2);
           // pst.setInt(1, a.id);
           //ajout id user par defaut et la date de creation 
            pst.setInt(1, a.id_user);
            pst.setString(2, a.titre_a);
            pst.setString(3, a.desc_a);
            pst.setString(4, a.categorie_a);
            pst.setInt(5, a.num_tel);
            pst.setDate(6, a.date_a);
            //pst.setDate(7, a.date_modif);
            pst.setString(7, a.etat);
            pst.executeUpdate();
            System.out.println("Annonce ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Connecion echouee");
        }
    }
    
//    public void updateClub(Club c, int id) {
//        try {
//            String myQuery1 = "Update club set desc_c = ? ,id_user = ? , nom_c = ? , mail_c = ? , nbmemb = ? WHERE id = ? " ;
//            PreparedStatement pst = cnx.prepareStatement(myQuery1);
//            pst.setInt(6,id);
//            pst.setInt(2, c.getId_user());
//            pst.setString(3, c.getNom_c());
//            pst.setString(4, c.getMail_c());
//          //  pst.setDate(5, date_sql);
//           // pst.setString(6, c.getImage());
//            pst.setInt(5, c.getNbmemb());
//            pst.setString(1, c.getDesc_c());
//            pst.executeUpdate();
    
    public void modifierAnnonce(Annonce a,int id)
    {
             try {
            String myQuery10 = "Update annonce set titre_a = ? , desc_a = ? , categorie_a = ? , num_tel = ? , etat = ? WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(6,id);
            ps10.setString(1, a.getTitre_a());
            ps10.setString(2, a.getDesc_a());
            ps10.setString(3, a.getCategorie_a());
            ps10.setInt(4, a.getNum_tel());               
            ps10.setString(5, a.getEtat());
            ps10.executeUpdate();
            System.out.println("Annonce mis a jour");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    
     public void supprimerAnnonce(Annonce a ,int id) {
        try {
            String myQuery10 = "DELETE FROM annonce WHERE id=?";
            PreparedStatement ps10 = cnx.prepareStatement(myQuery10);
            ps10.setInt(1, id);
            ps10.executeUpdate();
            System.out.println("Annonce supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     public List<Annonce> afficherAnnonce() {
            List<Annonce> ListAnnonce = new ArrayList<Annonce>();

        try {
            String myQuery30 = "SELECT * FROM annonce";
            PreparedStatement ps = cnx.prepareStatement(myQuery30);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                Annonce a=new Annonce(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),
                        rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getDate(7),rs1.getDate(8),rs1.getString(9));
                ListAnnonce.add(a);
               
//                System.out.println("ID: " + rs1.getInt(1)+"|*"+"ID User:" + rs1.getInt(2)+"|*"+"Titre:" + rs1.getString(3)+"|*"+"Description:" + rs1.getString(4));
//                System.out.println("Categorie:" + rs1.getString(5)+"|*"+"Numero Telephone:" + rs1.getInt(6)+"|*"+"Date Publication Annonce:" + rs1.getDate(8)+"|*"+"Description: " + rs1.getString(4));
//                System.out.println("Date Modification Annonce: " + rs1.getDate(8)+"|*"+"Etat Annonce : " + rs1.getString(9));           
//                System.out.println("\n------------");
//                System.out.println("\n------------");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListAnnonce;
    }
    
     public List<Annonce> getAnnonce(int id) {
            List<Annonce> ListAnnonce = new ArrayList<Annonce>();

        try {
            
            String myQuery30 = "SELECT * FROM annonce where id=?";
            PreparedStatement ps1 = cnx.prepareStatement(myQuery30);
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                Annonce a=new Annonce(rs1.getInt(1),rs1.getInt(2),rs1.getString(3),
                        rs1.getString(4),rs1.getString(5),rs1.getInt(6),rs1.getDate(7),rs1.getDate(8),rs1.getString(9));
                ListAnnonce.add(a);
                System.out.println("get annonce waaaaaaaaaw ");
           
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListAnnonce;
    }
     
     
      // public List<String> getnnonce() {
      //      List<String> ListAnnonce = new ArrayList<String>();
         public String getUserName(int id){  
       
             String userName ="" ;
             try {
            
            String myQuery30 = "SELECT username FROM user where id=? ";
            PreparedStatement ps1 = cnx.prepareStatement(myQuery30);
            ps1.setInt(1, id);
            ResultSet rs1 = ps1.executeQuery();
            while(rs1.next()){
            userName = rs1.getString("username");
                 System.out.println("************** Select user Name *************");
                 }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userName;
    }
//id
//id_user
//titre_a
//desc_a
//categorie_a
//num_tel
//date_modif
//date_a
//etat  
}
