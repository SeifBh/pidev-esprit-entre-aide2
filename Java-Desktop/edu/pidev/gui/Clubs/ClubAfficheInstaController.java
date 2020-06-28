/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Clubs;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Club;
import edu.pidev.entities.Utilisateur;
import static edu.pidev.gui.Clubs.AffichageV2Controller.finale;
import edu.pidev.services.CrudClub;
import edu.pidev.services.CrudUtilisateur;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author radhwen
 */
public class ClubAfficheInstaController implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton clubs;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton stores;
    @FXML
    private JFXButton spotted;
    @FXML
    private JFXButton logout;
    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;

    public static int b1;

    Connection cnx = DataSource.getInstance().getConnection();

    CrudClub cr = new CrudClub();
    Club c = new Club();
    private Image img;

    @FXML
    private ImageView IMGC;
    @FXML
    private Label LaNomc;
    @FXML
    private Label LaMailc;
    @FXML
    private Label LaDate;
    @FXML
    private Label LaNbr;
    @FXML
    private Label LaDesc;
    @FXML
    private Button BtModif;
    @FXML
    private Button BtSupp;

    private Button BtRetour;
    @FXML
    private Button abboner;
    @FXML
    private Label mem;
    @FXML
    private Button desabonner;
    List<Club> myList = new ArrayList<Club>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        desabonner.setVisible(true);
        abboner.setVisible(true);
        List<Integer> listuserclub = new ArrayList<Integer>();

        boolean w = false;
        b1 = AffichageV2Controller.b;

        Image i00 = new Image("Media/hidemenu.png");
        ImageView imv00 = new ImageView(i00);
        imv00.setFitHeight(50);
        imv00.setFitWidth(80);
        Image i0 = new Image("Media/menu.png");
        ImageView imv0 = new ImageView(i0);
        imv0.setFitHeight(50);
        imv0.setFitWidth(80);
        Image i1 = new Image("Media/home.png");
        ImageView imv1 = new ImageView(i1);
        imv1.setFitHeight(50);
        imv1.setFitWidth(80);
        Image i2 = new Image("Media/ClubsButton.png");
        ImageView imv2 = new ImageView(i2);
        imv2.setFitHeight(50);
        imv2.setFitWidth(80);
        Image i3 = new Image("Media/EventsButton.png");
        ImageView imv3 = new ImageView(i3);
        imv3.setFitHeight(50);
        imv3.setFitWidth(80);
        Image i4 = new Image("Media/storeButton.png");
        ImageView imv4 = new ImageView(i4);
        imv4.setFitHeight(50);
        imv4.setFitWidth(80);
        Image i5 = new Image("Media/spottedButton.png");
        ImageView imv5 = new ImageView(i5);
        imv5.setFitHeight(50);
        imv5.setFitWidth(80);
        Image i6 = new Image("Media/Logout.png");
        ImageView imv6 = new ImageView(i6);
        imv6.setFitHeight(50);
        imv6.setFitWidth(80);

        showMenu.setGraphic(imv0);
        hideMenu.setGraphic(imv00);
        home.setGraphic(imv1);
        clubs.setGraphic(imv2);
        events.setGraphic(imv3);
        stores.setGraphic(imv4);
        spotted.setGraphic(imv5);
        logout.setGraphic(imv6);

        FadeTransition f = new FadeTransition(Duration.millis(1), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        hideMenu.setVisible(false);
        //////////
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        b1 = AffichageV2Controller.b;
        CrudClub myTool = new CrudClub();
        Club c = new Club();
        try {
            String req = "SELECT * FROM club WHERE id=" + b1;
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                c = new Club(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getString(4), rs1.getDate(5), rs1.getString(7), rs1.getInt(8), rs1.getString(6));
                myList.add(c);

            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ///////////API

        try {
            img = new Image(new FileInputStream(c.getImage()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClubAfficheInstaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IMGC.setImage(img);
        LaNomc.setText(c.getNom_c());
        LaDesc.setText(c.getDesc_c());
        LaMailc.setText(c.mail_c);
        String datecreation = df.format(c.getDate_creation());
        LaDate.setText(datecreation);
        String nbr = Integer.toString(c.getNbmemb());
        LaNbr.setText(nbr);
//        System.out.println(b1);

        String role = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
//        System.out.println(role);
//        System.out.println(PI_Main.getUser().getRoles());
//         BtSupp.setVisible(false);
//                    BtModif.setVisible(false);

        if ((PI_Main.getUser().getId() == c.id_user) || PI_Main.getUser().getRoles().equals(role)) {
            BtModif.setVisible(true);
            BtSupp.setVisible(true);

        } else {
            BtSupp.setVisible(false);
            BtModif.setVisible(false);
        }

        //fonction de botton abboner
        abboner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                     CrudUtilisateur cu = new CrudUtilisateur();
//        Utilisateur  u = new Utilisateur();*
                Club c = new Club();
                int x=0 ;

                for (int i = 0; i < myList.size(); i++) {
                   
                  if(myList.get(i).getId()==b1){
                  x = myList.get(i).getNbmemb();

                  }
                  
               }
                x=x+1;
                System.out.println(x);
                
                    try {
            String myQuery1 = "Update club set nbmemb = ?  WHERE id = ?"  ;
            PreparedStatement pst = cnx.prepareStatement(myQuery1);
            pst.setInt(1,x);
          pst.setInt(2, b1);
            pst.executeUpdate();
            System.out.println("Club modifier avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

                try {
                    String myQuery1 = "INSERT INTO membres_club (club_id,user_id) VALUES (?,?)";
                    PreparedStatement pst = cnx.prepareStatement(myQuery1);
                    pst.setInt(1, b1);
                    pst.setInt(2, PI_Main.getUser().getId());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Bienvenu,t'es un nouveau étudiant dans notre club");


                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                    Alert alert = new Alert(Alert.AlertType.WARNING, "tu es déja un membre de ce groupe", ButtonType.OK);
                    alert.showAndWait();
                }
            }


        });

        desabonner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Club c = new Club();
                int x=0 ;

                for (int i = 0; i < myList.size(); i++) {
                   
                  if(myList.get(i).getId()==b1){
                  x = myList.get(i).getNbmemb();

                  }
                  
               }
                x=x-1;
                System.out.println(x);
                
                    try {
            String myQuery1 = "Update club set nbmemb = ?  WHERE id = ?"  ;
            PreparedStatement pst = cnx.prepareStatement(myQuery1);
            pst.setInt(1,x);
          pst.setInt(2, b1);
            pst.executeUpdate();
            System.out.println("Club modifier avec succès");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


                try {
                    String myQuery1 = "DELETE FROM membres_club WHERE club_id=? and user_id=?";
                    PreparedStatement ps1 = cnx.prepareStatement(myQuery1);
                    ps1.setInt(1, b1);
                    ps1.setInt(2, PI_Main.getUser().getId());
                    ps1.executeUpdate();
                    System.out.println("Club supprimée");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Au revoire", ButtonType.OK);

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    System.err.println("Connection perdu");

                }
            }
        });

    }

    @FXML
    public void update(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubUpdate.fxml"));
        Parent root;
        root = loader.load();
        BtModif.getScene().setRoot(root);
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        cr.supprimerClub(c, b1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AffichageV2.fxml"));
        Parent root;
        root = loader.load();
        BtSupp.getScene().setRoot(root);

    }

//    private void Retour(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AffichageV2.fxml"));
//        Parent root;
//        root = loader.load();
//        BtRetour.getScene().setRoot(root);
//
//    }
//    private void abboner(ActionEvent event, int i, Utilisateur e) throws IOException {
//
//    }
    public void home(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    public void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AccueilClubs.fxml"));
        Parent root;
        root = loader.load();
        clubs.getScene().setRoot(root);
    }

    public void events(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        events.getScene().setRoot(root);
    }

    public void store(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        stores.getScene().setRoot(root);
    }

    public void spotted(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        spotted.getScene().setRoot(root);
    }

    public void logout(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        logout.getScene().setRoot(root);
    }

    public void showmenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(0.0);
        f.setToValue(1.0);
        f.play();
        showMenu.setVisible(false);
        hideMenu.setVisible(true);

    }

    public void hidemenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        showMenu.setVisible(true);
        hideMenu.setVisible(false);

    }

    public static int getB1() {
        return b1;
    }

}
