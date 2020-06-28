/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Clubs;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import edu.pidev.entities.Club;
import edu.pidev.entities.Utilisateur;
import edu.pidev.services.CrudClub;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.Duration;
import edu.pidev.entities.Club;
import edu.pidev.entities.Evenement;
import edu.pidev.services.CrudClub;
import edu.pidev.services.EvenementServices;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author radhwen
 */
public class AffichageV2Controller implements Initializable {
//ini affichage
    public static int a1, a2, a3, a4, a5, a6, b;
    public static String rech;
     public static  List<Club> finale = new ArrayList();

    Connection cnx = DataSource.getInstance().getConnection();

    CrudClub cr = new CrudClub();
    Club c = new Club();
    Pagination cl = new Pagination((cr.afficherClub().size() / 4) + 1);
    TextField pageNumber = new TextField();
    private Image IMG1, IMG2, IMG3, IMG4, IMG5, IMG6;
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
    @FXML
    private Button rechercher;

    @FXML
    private Pane P3;
    @FXML
    private ImageView IMGC3;
    @FXML
    private Pane P2;
    @FXML
    private ImageView IMGC2;
    @FXML
    private Pane P4;
    @FXML
    private ImageView IMGC4;
    @FXML
    private Label TF1;
    @FXML
    private Label TF3;
    @FXML
    private Label TF2;
    @FXML
    private Label TF4;
    @FXML
    private Pane P5;
    @FXML
    private ImageView IMGC1;
    @FXML
    private Button BT1;
    @FXML
    private Button BT2;
    @FXML
    private Button BT4;
    @FXML
    private Button BT3;
    @FXML
    private JFXTextField re;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        ////
        cl.setPageFactory((Integer param) -> {
            pageNumber.setText(Integer.toString(param));

            return new AnchorPane();
        });

        cl.setTranslateX(-280);
        cl.setTranslateY(250);

        //System.out.println(p);
        P5.getChildren().add(cl);
        P2.getChildren().add(cl);
        P3.getChildren().add(cl);
        P4.getChildren().add(cl);

        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (Integer.parseInt(newValue) == 0) {

                    try {
                        AfficherClubs(cr.afficherClub(), 0);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 1) {
                    try {
                        AfficherClubs(cr.afficherClub(), 4);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 2) {
                    try {
                        AfficherClubs(cr.afficherClub(), 8);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 3) {
                    try {
                        AfficherClubs(cr.afficherClub(), 12);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 4) {
                    try {
                        AfficherClubs(cr.afficherClub(), 16);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Integer.parseInt(newValue) == 5) {
                    try {
                        AfficherClubs(cr.afficherClub(), 20);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(AffichageV2Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });

    }

    public void home(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    public void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AffichageV2.fxml"));
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

    public void AfficherClubs(List<Club> myList, int CurrentPage) throws FileNotFoundException {

        IMGC1.setImage(null);
        IMGC2.setImage(null);
        IMGC3.setImage(null);
        IMGC4.setImage(null);
        ///
        TF1.setText("");
        TF2.setText("");
        TF3.setText("");
        TF4.setText("");
        ////
        BT1.setVisible(false);
        BT2.setVisible(false);
        BT3.setVisible(false);
        BT4.setVisible(false);

        if (myList.size() < CurrentPage) {

            IMGC1.setImage(null);
            TF1.setText("");
            BT1.setVisible(false);

        } else {
            IMG1 = new Image(new FileInputStream(myList.get(CurrentPage).getImage()));

            IMGC1.setImage(IMG1);
            TF1.setText(myList.get(CurrentPage).getNom_c());
            BT1.setVisible(true);
            BT1.setText("plus d'info");
            a1 = myList.get(CurrentPage).getId();

        }
        if (myList.size() < CurrentPage + 1) {
            IMGC2.setImage(null);
            TF2.setText("");
            BT2.setVisible(false);

        } else {

            IMG2 = new Image(new FileInputStream(myList.get(CurrentPage + 1).getImage()));

            IMGC2.setImage(IMG2);
            TF2.setText(myList.get(CurrentPage + 1).getNom_c());
            BT2.setVisible(true);
            BT2.setText("plus d'info");
            a2 = myList.get(CurrentPage + 1).getId();
        }

        if (myList.size() < CurrentPage + 2) {
            IMGC3.setImage(null);
            TF3.setText("");

        } else {

            IMG3 = new Image(new FileInputStream(myList.get(CurrentPage + 2).getImage()));

            IMGC3.setImage(IMG3);
            TF3.setText(myList.get(CurrentPage + 2).getNom_c());
            BT3.setVisible(true);
            BT3.setText("Plus d'info");
            //            BT3.setVisible(true);
            //            BT3.setText("plus d'info");
            a3 = myList.get(CurrentPage + 2).getId();
        }

        if (myList.size() < CurrentPage + 3) {
            IMGC4.setImage(null);
            TF4.setText("");
        } else {

            IMG4 = new Image(new FileInputStream(myList.get(CurrentPage + 3).getImage()));

            IMGC4.setImage(IMG4);
            TF4.setText(myList.get(CurrentPage + 3).getNom_c());
            BT4.setVisible(true);
            BT4.setText("plus d'info");
            a4 = myList.get(CurrentPage + 3).getId();

        }

    }

    @FXML
    public void detail1(ActionEvent event) throws IOException {
        b = a1;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubAfficheInsta.fxml"));
        Parent root;
        root = loader.load();
        BT1.getScene().setRoot(root);

    }

    @FXML
    public void detail2(ActionEvent event) throws IOException {
        b = a2;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubAfficheInsta.fxml"));
        Parent root;
        root = loader.load();
        BT2.getScene().setRoot(root);

    }

    @FXML
    public void detail3(ActionEvent event) throws IOException {
        b = a3;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubAfficheInsta.fxml"));
        Parent root;
        root = loader.load();
        BT3.getScene().setRoot(root);

    }

    @FXML
    public void detail4(ActionEvent event) throws IOException {
        b = a4;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubAdd.fxml"));
        Parent root;
        root = loader.load();
        BT4.getScene().setRoot(root);

    }

    public static String getRech() {
        return rech;
    }
    
    @FXML
        public void recherche(ActionEvent event) throws IOException {
               for (int i = 0; i < cr.afficherClub().size(); i++) {
            if ((cr.afficherClub().get(i).getNom_c().contains(re.getText())) || (cr.afficherClub().get(i).getMail_c().contains(re.getText()))) {
                finale.add(cr.afficherClub().get(i));

            }

        }
               
               for (int i = 0; i < finale.size(); i++) {
                   System.out.println(finale.get(i).toString());
               }
        rech = re.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./ClubRech.fxml"));
        Parent root;
        root = loader.load();
        rechercher.getScene().setRoot(root);

    }
    
}

    
