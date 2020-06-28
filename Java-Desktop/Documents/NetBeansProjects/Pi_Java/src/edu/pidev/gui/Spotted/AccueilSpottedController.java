/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
import edu.pidev.entities.Utilisateur;
import edu.pidev.utils.DataSource;
import edu.pidev.gui.HomeController;
import static edu.pidev.gui.Spotted.AjoutPubPhotoController.copier;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.ListPublicationController.a1;
import edu.pidev.services.CrudPublication;
import edu.pidev.tests.PI_Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Rating;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AccueilSpottedController implements Initializable {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton clubs;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton stores;
    @FXML
    private JFXButton annonces;
    @FXML
    private JFXButton logout;
    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;
    @FXML
    private VBox PubVB;
    @FXML
    private ImageView logoE;
    @FXML
    private Button btnPhoto;
    @FXML
    private Button btnPub;
    @FXML
    private TextField tags_pub;
    @FXML
    private TextField tags_photo;
    @FXML
    private TextArea txtPublication;
    private Button btn_Publier;
    @FXML
    private Button btn_Publier_Pub;
    @FXML
    private Button btn_Publier_Photo;
    private ImageView imgUploaded;
    @FXML
    private Button btnRetour;
    @FXML
    private Pane paneme;
    @FXML
    private ImageView upload;

    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;
    String name_user = PI_Main.getUser().getNom();
    String last_user = PI_Main.getUser().getNom();
    String complete_name = name_user + last_user;
    int id_current_user = PI_Main.getUser().getId();
    String role_user = PI_Main.getUser().getRoles();
    String role;

    String sexe = PI_Main.getUser().getSexe();
    @FXML
    private Label usercon;
    @FXML
    private Label nbPub;
    @FXML
    private Label nbPhoto;
    @FXML
    private Label firstC;
    @FXML
    private Label nbTotal;
    @FXML
    private VBox TagVB;
    @FXML
    private ImageView uploadnow;
    @FXML
    private Button btnAll;
    @FXML
    private Button btnI;
    @FXML
    private Button btnI2;

    public static int idme;
    public static String descp;
    public static String tagspub;
    public static String imagepath;
    public static String imagep;
    @FXML
    private ImageView bgesprit;
    @FXML
    private Pane paneprof;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role = "";
        if (role_user == "a:1:{i:0;s:8:\"ETUDIANT\";}") {
            role = "ETUDIANT";
        }
        if (role_user == "a:1:{i:0;s:10:\"ROLE_ADMIN\";}") {
            role = "ADMIN";
        }
        String first = name_user.substring(0, 1);
        firstC.setText(first);
        firstC.setAlignment(Pos.CENTER);
        usercon.setText(complete_name);

        try {
            String req = "SELECT COUNT(*) AS total FROM publication ";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet nbtotal = statement.executeQuery(req);
            while (nbtotal.next()) {
                nbtotal.getInt("total");
                nbTotal.setText("" + nbtotal.getInt("total"));
                nbTotal.setAlignment(Pos.CENTER);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NULL and  id_user=" + id_current_user + "";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberpicture = statement.executeQuery(req);
            while (numberpicture.next()) {
                numberpicture.getInt("total");
                nbPhoto.setText("" + numberpicture.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NOT NULL and  id_user=" + id_current_user + "";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberPub = statement.executeQuery(req);
            while (numberPub.next()) {
                numberPub.getInt("total");
                nbPub.setText("" + numberPub.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //JOptionPane.showMessageDialog(null, "Bonjour " + role_user);
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Spotted" + c + ".jpg");
        lien = "src/Media/Spotted" + c + ".jpg";

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
        Image i5 = new Image("Media/annonces.png");
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
        annonces.setGraphic(imv2);
        events.setGraphic(imv3);
        stores.setGraphic(imv4);
        clubs.setGraphic(imv5);
        logout.setGraphic(imv6);
        FadeTransition f = new FadeTransition(Duration.millis(1), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        hideMenu.setVisible(false);
        //Spotted Style
        btnPub.setVisible(true);
        btnPhoto.setVisible(true);

        btnRetour.setVisible(false);
        btnPhoto.setVisible(true);
        txtPublication.setVisible(false);
        tags_photo.setVisible(false);
        tags_pub.setVisible(false);
        btn_Publier_Pub.setVisible(false);
        btn_Publier_Photo.setVisible(false);
        paneme.setVisible(false);

        System.out.println("Bonjour Test");
        btnRetour.setOnAction((ActionEventn) -> {
            nbTotal.setVisible(true);

            btnPub.setVisible(true);
            btnPhoto.setVisible(true);
            txtPublication.setVisible(false);
            tags_photo.setVisible(false);
            tags_pub.setVisible(false);
            btn_Publier_Pub.setVisible(false);
            btn_Publier_Photo.setVisible(false);
            btnRetour.setVisible(true);
            paneme.setVisible(false);
            btnRetour.setVisible(false);

        });

        btnPub.setOnAction((ActionEventn) -> {

            System.out.println("Publication Texto");
            nbTotal.setVisible(false);
            btnPhoto.setVisible(false);
            btnPub.setVisible(false);
            txtPublication.setVisible(true);
            tags_pub.setVisible(true);
            tags_photo.setVisible(false);
            btn_Publier_Pub.setVisible(true);
            btn_Publier_Photo.setVisible(false);
            btnRetour.setVisible(true);
            paneme.setVisible(false);

        });
        btnPhoto.setOnAction((ActionEventn) -> {
            nbTotal.setVisible(false);

            System.out.println("Publication Image");
            btnPhoto.setVisible(false);
            btnPub.setVisible(false);
            txtPublication.setVisible(false);
            tags_pub.setVisible(false);
            tags_photo.setVisible(true);
            btn_Publier_Pub.setVisible(false);
            btn_Publier_Photo.setVisible(true);
            paneme.setVisible(true);
            btnRetour.setVisible(true);

        });
        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication Order BY date_p DESC";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication p = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString(7));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PubVB.getChildren().clear();

        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Publication p : myList) {
            Label tagmes = new Label(p.getTags());
            TagVB.getChildren().add(tagmes);
            if (p.getDesc_p() == null) {
                //String fullink = "src/Media/" + p.getImage();
                //System.out.println(logoE.getImage().getClass().);

                try {
                    Image im10 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im10);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");
                    List<Rating> myListRating = new ArrayList<Rating>();
                    Rating rating = new Rating();
                    rating.setRating(0);
                    rating.setDisable(true);
                    rating.setOpacity(1);
                    try {
                        String req = "SELECT SUM(rating) AS total, COUNT(id_user) AS numuser FROM rating where id_publication=" + p.getId() + "";
                        System.out.println(req);
                        Connection cnx = DataSource.getInstance().getConnection();
                        Statement statement = cnx.createStatement();
                        ResultSet nbtotal = statement.executeQuery(req);
                        while (nbtotal.next()) {

                            try {
                                nbtotal.getInt("total");
                                nbtotal.getInt("numuser");
                                int somme = nbtotal.getInt("total") / nbtotal.getInt("numuser");
                                float finalresult = (float) Math.round(somme);
                                System.out.println(nbtotal.getInt("total") + "/" + nbtotal.getInt("numuser"));
                                System.out.println(finalresult);
                                rating.setRating(finalresult);
                            } catch (ArithmeticException e) {
                                System.out.println("Division par zéro !");
                            }

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Label nomuser = new Label();
                    nomuser.setId("nomuser");
                    nomuser.setStyle("./acceuilspotted.css");
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);

                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);
                    Hyperlink btnModif = new Hyperlink("Modifier");
                    //herehere
                    Hyperlink btnSupprimer = new Hyperlink("Supprimer");
                    btnModif.setTranslateX(500);
                    btnSupprimer.setTranslateX(500);
                    if (role.equals("ETUDIANT")) {
                        if (p.getUser_id() == id_current_user) {
                            btnModif.setVisible(true);
                            btnSupprimer.setVisible(true);
                        } else {
                            btnModif.setVisible(false);
                            btnSupprimer.setVisible(false);

                        }
                    }
                    if (role.equals("ADMIN")) {
                        btnModif.setVisible(true);
                        btnSupprimer.setVisible(true);
                    }
                    btnModif.setOnAction((ActionEventn) -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifContentSpottedPhoto.fxml"));
                            Parent root;
                            idme = p.getId();
                            descp = p.getDesc_p();
                            tagspub = p.getTags();
                            imagep = p.getImage();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });
                    btnSupprimer.setId("" + p.getId());
                    btnSupprimer.setOnAction((ActionEventn) -> {
                        CrudPublication myTool = new CrudPublication();
                        JOptionPane.showMessageDialog(null, p.getId());

                        myTool.supprimerPublication(p, p.getId());

                        callme();

                    });
                    btnOuvrir.setTranslateX(280);
                    btnOuvrir.setPadding(new Insets(20, 20, 20, 20));
                    btnOuvrir.setOnAction((ActionEventn) -> {
                        //JOptionPane.showMessageDialog(null, "Verifier le champ de publication");

                        try {
                            //JOptionPane.showMessageDialog(null, "Verifier le champ de publication");

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContentSpottedPhoto.fxml"));
                            Parent root;
                            imagepath = p.getImage();
                            tagspub = p.getTags();

                            idme = p.getId();
                            descp = p.getDesc_p();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });

//                    Label datepub = new Label();
//                    datepub.setText(p.getDate_p().toString());
//                    nomuser.setId("datepub");
//                    nomuser.setStyle("./acceuilspotted.css");
//                    nomuser.setTranslateX(680);
//                    nomuser.setTranslateY(50);
//                    //nomuser.setPadding(new Insets(20, 10, 50, 20));
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    List<Utilisateur> myList2 = new ArrayList<Utilisateur>();

                    try {
                        String req = "SELECT * FROM user where id=" + p.getUser_id() + "";
                        System.out.println(req);
                        Connection cnx = DataSource.getInstance().getConnection();
                        Statement statement = cnx.createStatement();
                        ResultSet rs1 = statement.executeQuery(req);
                        while (rs1.next()) {
                            Utilisateur u0 = new Utilisateur(rs1.getInt("id"), rs1.getString("sexe"));

                            if (u0.getSexe().equals("female")) {
                                System.out.println("Female IF");
                                System.out.println("id publication = " + p.getId() + " id user = " + p.getUser_id() + " selected user = " + rs1.getInt("id"));

                                Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                                imvmale.setImage(im1);
                            } else {
                                System.out.println("Male IF");
                                System.out.println("id publication = " + p.getId() + " id user = " + p.getUser_id() + " selected user = " + rs1.getInt("id"));

                                Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                                imvmale.setImage(im1);
                            }

//                        if (rs1.getString("sexe") == "female") {
//                            System.out.println("Female IF");
//                            System.out.println("id publication = "+p.getId()+" id user = "+p.getUser_id()+" selected user = "+rs1.getInt("id"));
//
//
//                            Image im1 = new Image(new FileInputStream("src/Media/female.png"));
//                            imvmale.setImage(im1);
//                        } else {
//                            System.out.println("Male IF");
//                            System.out.println("id publication = "+p.getId()+" id user = "+p.getUser_id()+" selected user = "+rs1.getInt("id"));
//
//                            Image im1 = new Image(new FileInputStream("src/Media/male.png"));
//                            imvmale.setImage(im1);
//                        }
                            myList2.add(u0);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Image im = new Image(new FileInputStream(p.getImage()));
                    ImageView imv = new ImageView(im);
                    imagepath = im.toString();
                    Label septa = new Label("");
                    septa.setMinWidth(800);
                    septa.setId("septa");
                    septa.setStyle("./acceuilspotted.css");
                    imv.setFitWidth(744);
                    imv.setFitHeight(413);

                    imv.setStyle("./acceuilspotted.css");

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    lbTag.setText("#" + p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 10, 20));

                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);
                    PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(imv);
                    PubVB.getChildren().add(lbTag);
                    //PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(rating);

                    PubVB.getChildren().add(btnOuvrir);
                    PubVB.getChildren().add(btnModif);
                    PubVB.getChildren().add(btnSupprimer);

                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println("link pic :" + p.getImage());
                    System.out.println(ex.getMessage());
                }
//elsecondition
            } else {
                try {
                    Image im100 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im100);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");

                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }
                    Label grpbtn = new Label();
                    Label nomuser = new Label();

                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);
//                    	DateTime dt1 = new DateTime(date1);
//                        DateTime dt2 = new DateTime(date2);

                    Pane pn = new Pane();

                    Hyperlink btnModif = new Hyperlink("Modifier");
                    Label lbbtns = new Label();

                    Hyperlink btnSupprimer = new Hyperlink("Supprimer");

                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {

                            nomuser.setText(name_user + "                                                                                   " + c_date);

                        } else {
                            nomuser.setGraphic(btnModif);

                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    System.out.println("id= " + p.getId() + " " + Days.daysBetween(date_publication, datenow_c).getDays() + " days, ");
//                    System.out.println("id= " + p.getId() + " " + Hours.hoursBetween(datenow_c, date_publication).getHours() % 24 + "hours, ");
//
//                    System.out.println("ID = " + p.getId() + "date now : " + datenow_c + "/// date publication  : " + date_publication);
                    System.out.println(""
                            + "if (" + p.getId()
                            + " == " + id_current_user + ""
                    );
                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);
                    pn.getChildren().add(btnOuvrir);
                    pn.getChildren().add(btnModif);
                    pn.getChildren().add(btnSupprimer);
                    ////lbbtns.getChildren().add(btnModif);
                    //lbbtns.getChildren().add(btnSupprimer);
                    //lbbtns.set(btnModif+"  "+btnSupprimer);
                    if (role.equals("ETUDIANT")) {
                        if (p.getUser_id() == id_current_user) {
                            btnModif.setVisible(true);
                            btnSupprimer.setVisible(true);
                        } else {
                            btnModif.setVisible(false);
                            btnSupprimer.setVisible(false);

                        }
                    }
                    if (role.equals("ADMIN")) {
                        btnModif.setVisible(true);
                        btnSupprimer.setVisible(true);
                    }
                    btnOuvrir.setTranslateX(280);
                    btnOuvrir.setPadding(new Insets(20, 20, 20, 20));
                    btnOuvrir.setOnAction((ActionEventn) -> {
                        try {
                            System.out.println("Ouvrir la page content spotted encours");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContentSpotted.fxml"));
                            System.out.println("Probleme survenue");

                            Parent root;
                            idme = p.getId();
                            tagspub = p.getTags();
                            descp = p.getDesc_p();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });

                    btnModif.setOnAction((ActionEventn) -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifContentPublicationSpotted.fxml"));
                            Parent root;
                            idme = p.getId();
                            descp = p.getDesc_p();
                            tagspub = p.getTags();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });
                    btnSupprimer.setId("" + p.getId());
                    btnSupprimer.setOnAction((ActionEventn) -> {
                        CrudPublication myTool = new CrudPublication();
                        JOptionPane.showMessageDialog(null, p.getId());

                        myTool.supprimerPublication(p, p.getId());

                        callme();

                    });
                    Label lbDesc = new Label();
                    lbDesc.setId("desc");
                    lbDesc.setStyle("./acceuilspotted.css");
                    lbDesc.setText(p.getDesc_p());
                    lbDesc.setPadding(new Insets(20, 10, 5, 20));

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    lbTag.setText(p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 20, 20));
                    btnModif.setTranslateX(500);
                    btnSupprimer.setTranslateX(500);
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);

                    PubVB.getChildren().add(lbDesc);
                    PubVB.getChildren().add(lbTag);
                    // PubVB.getChildren().add(pn);
                    PubVB.getChildren().add(btnOuvrir);
                    PubVB.getChildren().add(btnModif);
                    PubVB.getChildren().add(btnSupprimer);
                    PubVB.getChildren().add(lbbtns);
                    PubVB.getChildren().add(pn);
                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
    }

    public void callme() {
        role = "";
        if (role_user == "a:1:{i:0;s:8:\"ETUDIANT\";}") {
            role = "ETUDIANT";
        }
        if (role_user == "a:1:{i:0;s:10:\"ROLE_ADMIN\";}") {
            role = "ADMIN";
        }
        String first = name_user.substring(0, 1);
        firstC.setText(first);
        firstC.setAlignment(Pos.CENTER);
        usercon.setText(complete_name);

        try {
            String req = "SELECT COUNT(*) AS total FROM publication ";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet nbtotal = statement.executeQuery(req);
            while (nbtotal.next()) {
                nbtotal.getInt("total");
                nbTotal.setText("" + nbtotal.getInt("total"));
                nbTotal.setAlignment(Pos.CENTER);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NULL and  id_user=" + id_current_user + "";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberpicture = statement.executeQuery(req);
            while (numberpicture.next()) {
                numberpicture.getInt("total");
                nbPhoto.setText("" + numberpicture.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NOT NULL and  id_user=" + id_current_user + "";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberPub = statement.executeQuery(req);
            while (numberPub.next()) {
                numberPub.getInt("total");
                nbPub.setText("" + numberPub.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //JOptionPane.showMessageDialog(null, "Bonjour " + role_user);
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Spotted" + c + ".jpg");
        lien = "src/Media/Spotted" + c + ".jpg";

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
        Image i5 = new Image("Media/annonces.png");
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
        annonces.setGraphic(imv2);
        events.setGraphic(imv3);
        stores.setGraphic(imv4);
        clubs.setGraphic(imv5);
        logout.setGraphic(imv6);
        FadeTransition f = new FadeTransition(Duration.millis(1), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        hideMenu.setVisible(false);
        //Spotted Style
        btnPub.setVisible(true);
        btnPhoto.setVisible(true);

        btnRetour.setVisible(false);
        btnPhoto.setVisible(true);
        txtPublication.setVisible(false);
        tags_photo.setVisible(false);
        tags_pub.setVisible(false);
        btn_Publier_Pub.setVisible(false);
        btn_Publier_Photo.setVisible(false);
        paneme.setVisible(false);

        System.out.println("Bonjour Test");
        btnRetour.setOnAction((ActionEventn) -> {
            nbTotal.setVisible(true);

            btnPub.setVisible(true);
            btnPhoto.setVisible(true);
            txtPublication.setVisible(false);
            tags_photo.setVisible(false);
            tags_pub.setVisible(false);
            btn_Publier_Pub.setVisible(false);
            btn_Publier_Photo.setVisible(false);
            btnRetour.setVisible(true);
            paneme.setVisible(false);
            btnRetour.setVisible(false);

        });

        btnPub.setOnAction((ActionEventn) -> {

            System.out.println("Publication Texto");
            nbTotal.setVisible(false);
            btnPhoto.setVisible(false);
            btnPub.setVisible(false);
            txtPublication.setVisible(true);
            tags_pub.setVisible(true);
            tags_photo.setVisible(false);
            btn_Publier_Pub.setVisible(true);
            btn_Publier_Photo.setVisible(false);
            btnRetour.setVisible(true);
            paneme.setVisible(false);

        });
        btnPhoto.setOnAction((ActionEventn) -> {
            nbTotal.setVisible(false);

            System.out.println("Publication Image");
            btnPhoto.setVisible(false);
            btnPub.setVisible(false);
            txtPublication.setVisible(false);
            tags_pub.setVisible(false);
            tags_photo.setVisible(true);
            btn_Publier_Pub.setVisible(false);
            btn_Publier_Photo.setVisible(true);
            paneme.setVisible(true);
            btnRetour.setVisible(true);

        });
        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication Order BY date_p DESC";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication p = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString(7));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PubVB.getChildren().clear();

        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Publication p : myList) {
            Label tagmes = new Label(p.getTags());
            TagVB.getChildren().add(tagmes);
            if (p.getDesc_p() == null) {
                //String fullink = "src/Media/" + p.getImage();
                //System.out.println(logoE.getImage().getClass().);

                try {
                    Image im10 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im10);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");
                    List<Rating> myListRating = new ArrayList<Rating>();
                    Rating rating = new Rating();
                    rating.setRating(0);
                    rating.setDisable(true);
                    rating.setOpacity(1);
                    try {
                        String req = "SELECT SUM(rating) AS total, COUNT(id_user) AS numuser FROM rating where id_publication=" + p.getId() + "";
                        System.out.println(req);
                        Connection cnx = DataSource.getInstance().getConnection();
                        Statement statement = cnx.createStatement();
                        ResultSet nbtotal = statement.executeQuery(req);
                        while (nbtotal.next()) {

                            try {
                                nbtotal.getInt("total");
                                nbtotal.getInt("numuser");
                                int somme = nbtotal.getInt("total") / nbtotal.getInt("numuser");
                                float finalresult = (float) Math.round(somme);
                                System.out.println(nbtotal.getInt("total") + "/" + nbtotal.getInt("numuser"));
                                System.out.println(finalresult);
                                rating.setRating(finalresult);
                            } catch (ArithmeticException e) {
                                System.out.println("Division par zéro !");
                            }

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Label nomuser = new Label();
                    nomuser.setId("nomuser");
                    nomuser.setStyle("./acceuilspotted.css");
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);

                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);
                    Hyperlink btnModif = new Hyperlink("Modifier");
                    //herehere
                    Hyperlink btnSupprimer = new Hyperlink("Supprimer");
                    btnModif.setTranslateX(500);
                    btnSupprimer.setTranslateX(500);
                    if (role.equals("ETUDIANT")) {
                        if (p.getUser_id() == id_current_user) {
                            btnModif.setVisible(true);
                            btnSupprimer.setVisible(true);
                        } else {
                            btnModif.setVisible(false);
                            btnSupprimer.setVisible(false);

                        }
                    }
                    if (role.equals("ADMIN")) {
                        btnModif.setVisible(true);
                        btnSupprimer.setVisible(true);
                    }
                    btnModif.setOnAction((ActionEventn) -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifContentSpottedPhoto.fxml"));
                            Parent root;
                            idme = p.getId();
                            descp = p.getDesc_p();
                            tagspub = p.getTags();
                            imagep = p.getImage();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });
                    btnSupprimer.setId("" + p.getId());
                    btnSupprimer.setOnAction((ActionEventn) -> {
                        CrudPublication myTool = new CrudPublication();
                        JOptionPane.showMessageDialog(null, p.getId());

                        myTool.supprimerPublication(p, p.getId());

                        callme();

                    });
                    btnOuvrir.setTranslateX(280);
                    btnOuvrir.setPadding(new Insets(20, 20, 20, 20));
                    btnOuvrir.setOnAction((ActionEventn) -> {
                        //JOptionPane.showMessageDialog(null, "Verifier le champ de publication");

                        try {
                            //JOptionPane.showMessageDialog(null, "Verifier le champ de publication");

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContentSpottedPhoto.fxml"));
                            Parent root;
                            imagepath = p.getImage();
                            tagspub = p.getTags();

                            idme = p.getId();
                            descp = p.getDesc_p();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });

//                    Label datepub = new Label();
//                    datepub.setText(p.getDate_p().toString());
//                    nomuser.setId("datepub");
//                    nomuser.setStyle("./acceuilspotted.css");
//                    nomuser.setTranslateX(680);
//                    nomuser.setTranslateY(50);
//                    //nomuser.setPadding(new Insets(20, 10, 50, 20));
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    List<Utilisateur> myList2 = new ArrayList<Utilisateur>();

                    try {
                        String req = "SELECT * FROM user where id=" + p.getUser_id() + "";
                        System.out.println(req);
                        Connection cnx = DataSource.getInstance().getConnection();
                        Statement statement = cnx.createStatement();
                        ResultSet rs1 = statement.executeQuery(req);
                        while (rs1.next()) {
                            Utilisateur u0 = new Utilisateur(rs1.getInt("id"), rs1.getString("sexe"));

                            if (u0.getSexe().equals("female")) {
                                System.out.println("Female IF");
                                System.out.println("id publication = " + p.getId() + " id user = " + p.getUser_id() + " selected user = " + rs1.getInt("id"));

                                Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                                imvmale.setImage(im1);
                            } else {
                                System.out.println("Male IF");
                                System.out.println("id publication = " + p.getId() + " id user = " + p.getUser_id() + " selected user = " + rs1.getInt("id"));

                                Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                                imvmale.setImage(im1);
                            }

//                        if (rs1.getString("sexe") == "female") {
//                            System.out.println("Female IF");
//                            System.out.println("id publication = "+p.getId()+" id user = "+p.getUser_id()+" selected user = "+rs1.getInt("id"));
//
//
//                            Image im1 = new Image(new FileInputStream("src/Media/female.png"));
//                            imvmale.setImage(im1);
//                        } else {
//                            System.out.println("Male IF");
//                            System.out.println("id publication = "+p.getId()+" id user = "+p.getUser_id()+" selected user = "+rs1.getInt("id"));
//
//                            Image im1 = new Image(new FileInputStream("src/Media/male.png"));
//                            imvmale.setImage(im1);
//                        }
                            myList2.add(u0);

                        }

                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Image im = new Image(new FileInputStream(p.getImage()));
                    ImageView imv = new ImageView(im);
                    imagepath = im.toString();
                    Label septa = new Label("");
                    septa.setMinWidth(800);
                    septa.setId("septa");
                    septa.setStyle("./acceuilspotted.css");
                    imv.setFitWidth(744);
                    imv.setFitHeight(413);

                    imv.setStyle("./acceuilspotted.css");

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    lbTag.setText("#" + p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 10, 20));

                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);
                    PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(imv);
                    PubVB.getChildren().add(lbTag);
                    //PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(rating);

                    PubVB.getChildren().add(btnOuvrir);
                    PubVB.getChildren().add(btnModif);
                    PubVB.getChildren().add(btnSupprimer);

                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println("link pic :" + p.getImage());
                    System.out.println(ex.getMessage());
                }
//elsecondition
            } else {
                try {
                    Image im100 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im100);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");

                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }
                    Label grpbtn = new Label();
                    Label nomuser = new Label();

                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);
//                    	DateTime dt1 = new DateTime(date1);
//                        DateTime dt2 = new DateTime(date2);

                    Pane pn = new Pane();

                    Hyperlink btnModif = new Hyperlink("Modifier");
                    Label lbbtns = new Label();

                    Hyperlink btnSupprimer = new Hyperlink("Supprimer");

                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {

                            nomuser.setText(name_user + "                                                                                   " + c_date);

                        } else {
                            nomuser.setGraphic(btnModif);

                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    System.out.println("id= " + p.getId() + " " + Days.daysBetween(date_publication, datenow_c).getDays() + " days, ");
//                    System.out.println("id= " + p.getId() + " " + Hours.hoursBetween(datenow_c, date_publication).getHours() % 24 + "hours, ");
//
//                    System.out.println("ID = " + p.getId() + "date now : " + datenow_c + "/// date publication  : " + date_publication);
                    System.out.println(""
                            + "if (" + p.getId()
                            + " == " + id_current_user + ""
                    );
                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);
                    pn.getChildren().add(btnOuvrir);
                    pn.getChildren().add(btnModif);
                    pn.getChildren().add(btnSupprimer);
                    ////lbbtns.getChildren().add(btnModif);
                    //lbbtns.getChildren().add(btnSupprimer);
                    //lbbtns.set(btnModif+"  "+btnSupprimer);
                    if (role.equals("ETUDIANT")) {
                        if (p.getUser_id() == id_current_user) {
                            btnModif.setVisible(true);
                            btnSupprimer.setVisible(true);
                        } else {
                            btnModif.setVisible(false);
                            btnSupprimer.setVisible(false);

                        }
                    }
                    if (role.equals("ADMIN")) {
                        btnModif.setVisible(true);
                        btnSupprimer.setVisible(true);
                    }
                    btnOuvrir.setTranslateX(280);
                    btnOuvrir.setPadding(new Insets(20, 20, 20, 20));
                    btnOuvrir.setOnAction((ActionEventn) -> {
                        try {
                            System.out.println("Ouvrir la page content spotted encours");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ContentSpotted.fxml"));
                            System.out.println("Probleme survenue");
                            Parent root;
                            idme = p.getId();
                            tagspub = p.getTags();
                            descp = p.getDesc_p();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });

                    btnModif.setOnAction((ActionEventn) -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifContentPublicationSpotted.fxml"));
                            Parent root;
                            idme = p.getId();
                            descp = p.getDesc_p();
                            tagspub = p.getTags();
                            System.out.println("" + idme + "");
                            root = loader.load();
                            btnOuvrir.getScene().setRoot(root);
                            //JOptionPane.showMessageDialog(null, c.getContent_comm());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }

                    });
                    btnSupprimer.setId("" + p.getId());
                    btnSupprimer.setOnAction((ActionEventn) -> {
                        CrudPublication myTool = new CrudPublication();
                        JOptionPane.showMessageDialog(null, p.getId());

                        myTool.supprimerPublication(p, p.getId());

                        callme();

                    });
                    Label lbDesc = new Label();
                    lbDesc.setId("desc");
                    lbDesc.setStyle("./acceuilspotted.css");
                    lbDesc.setText(p.getDesc_p());
                    lbDesc.setPadding(new Insets(20, 10, 5, 20));

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    lbTag.setText(p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 20, 20));
                    btnModif.setTranslateX(500);
                    btnSupprimer.setTranslateX(500);
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);

                    PubVB.getChildren().add(lbDesc);
                    PubVB.getChildren().add(lbTag);
                    // PubVB.getChildren().add(pn);
                    PubVB.getChildren().add(btnOuvrir);
                    PubVB.getChildren().add(btnModif);
                    PubVB.getChildren().add(btnSupprimer);
                    PubVB.getChildren().add(lbbtns);
                    PubVB.getChildren().add(pn);
                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }

            }

        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    @FXML
    private void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AccueilClubs.fxml"));
        Parent root;
        root = loader.load();
        clubs.getScene().setRoot(root);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        events.getScene().setRoot(root);
    }

    @FXML
    private void store(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        stores.getScene().setRoot(root);
    }

    @FXML
    private void annonces(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/AccueilAnnonces.fxml"));
        Parent root;
        root = loader.load();
        annonces.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        logout.getScene().setRoot(root);
    }

    @FXML
    public void showmenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(0.0);
        f.setToValue(1.0);
        f.play();
        showMenu.setVisible(false);
        hideMenu.setVisible(true);

    }

    @FXML
    public void hidemenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        showMenu.setVisible(true);
        hideMenu.setVisible(false);

    }

    public Boolean FilterPub() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtPublication.getText();
        String[] words = s.split("\\s+");
        for (int j = 0; j < filterwords.length; j++) {
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].replaceAll("[^\\w]", "");
                System.out.println(filterwords[j] + " == " + words[i]);
                if (filterwords[j].equals(words[i])) {
                    System.out.println(filterwords[j] + " == " + words[i]);
                    focusword = words[i];
                    filter = false;
                    return filter;

                } else {
                    filter = true;
                }
            }
        }
        return filter;
    }

    @FXML
    private void btn_Publier_Pub_action(ActionEvent event) {
        Boolean test1, filter;
        test1 = true;
        filter = true;
        if (FilterPub()) {
            System.out.println("Mafeha chay");
        } else {
            System.out.println("9ader sahbii");

        }
        if (txtPublication.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Verifier le champ de publication");
            test1 = false;
        }
        if (!FilterPub()) {
            JOptionPane.showMessageDialog(null, "Mot Interdit, Veuillez ressayer");
            txtPublication.setStyle("-fx-border-color:red");

        }
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();

        if (test1 && FilterPub()) {

            p.setDesc_p(txtPublication.getText());
            p.setTags(tags_pub.getText());
            myTool.ajoutPublication(p);
            JOptionPane.showMessageDialog(null, "Publication ajoutée");
            txtPublication.setText("");
            tags_pub.setText("");
            callme();
        }
    }

    @FXML
    private void btn_Publier_Photo_action(ActionEvent event) {
        copier(pfile, pDir);
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        p.setImage(lien);
        p.setTags(tags_photo.getText());
        myTool.ajoutPublication(p);
        JOptionPane.showMessageDialog(null, "Publication ajoutée avec succes");
        tags_photo.setText("");

        callme();
    }

    @FXML
    private void uploadnow_action(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);
        if (pfile != null) {
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            upload.setImage(image);
        }
    }

    @FXML
    private void btnAll_action(ActionEvent event) {
        callme();

    }

    @FXML
    private void btnI_action(ActionEvent event) {
        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NOT NULL ";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberPub = statement.executeQuery(req);
            while (numberPub.next()) {
                numberPub.getInt("total");
                nbTotal.setText("" + numberPub.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication where desc_p is NOT NULL Order BY date_p DESC";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication p = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString(7));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PubVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Publication p : myList) {
            if (p.getDesc_p() == null) {
                //String fullink = "src/Media/" + p.getImage();
                //System.out.println(logoE.getImage().getClass().);

                try {
                    Image im10 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im10);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");
                    Label nomuser = new Label();
                    nomuser.setId("nomuser");
                    nomuser.setStyle("./acceuilspotted.css");
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);

//                    Label datepub = new Label();
//                    datepub.setText(p.getDate_p().toString());
//                    nomuser.setId("datepub");
//                    nomuser.setStyle("./acceuilspotted.css");
//                    nomuser.setTranslateX(680);
//                    nomuser.setTranslateY(50);
//                    //nomuser.setPadding(new Insets(20, 10, 50, 20));
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }

                    Image im = new Image(new FileInputStream(p.getImage()));
                    ImageView imv = new ImageView(im);
                    Label septa = new Label("");
                    septa.setMinWidth(800);
                    septa.setId("septa");
                    septa.setStyle("./acceuilspotted.css");
                    imv.setFitWidth(744);
                    imv.setFitHeight(413);

                    imv.setStyle("./acceuilspotted.css");

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    lbTag.setText("#" + p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 10, 20));
                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);
                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);
                    PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(imv);
                    PubVB.getChildren().add(lbTag);
                    PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(btnOuvrir);

                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println("link pic :" + p.getImage());
                    System.out.println(ex.getMessage());
                }

            } else {
                try {
                    Image im100 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im100);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");
                    Image imageDecline = new Image("Media/open.png");
                    ImageView imageView = new ImageView(imageDecline);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    Button btnOuvrir = new Button("ouvrir");
                    btnOuvrir.setGraphic(imageView);

                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }
                    Label nomuser = new Label();
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Label lbDesc = new Label();
                    lbDesc.setId("desc");
                    lbDesc.setStyle("./acceuilspotted.css");
                    lbDesc.setText(p.getDesc_p());
                    lbDesc.setPadding(new Insets(20, 10, 5, 20));

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    lbTag.setText(p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 20, 20));

                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);

                    PubVB.getChildren().add(lbDesc);
                    PubVB.getChildren().add(lbTag);
                    PubVB.getChildren().add(btnOuvrir);
                    PubVB.getChildren().add(lbseparate);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AccueilSpottedController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    @FXML
    private void btnI2_action(ActionEvent event) {
        try {
            String req = "SELECT COUNT(*) AS total FROM publication where desc_p is  NULL ";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet numberpicture = statement.executeQuery(req);
            while (numberpicture.next()) {
                numberpicture.getInt("total");
                nbTotal.setText("" + numberpicture.getInt("total"));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        List<Publication> myList = new ArrayList<Publication>();

        try {
            String req = "SELECT * FROM publication where desc_p is NULL Order BY date_p DESC";
            System.out.println(req);
            Connection cnx = DataSource.getInstance().getConnection();
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Publication p = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString(7));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PubVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        for (Publication p : myList) {
            if (p.getDesc_p() == null) {
                //String fullink = "src/Media/" + p.getImage();
                //System.out.println(logoE.getImage().getClass().);

                try {
                    Image im10 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im10);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");
                    Label nomuser = new Label();
                    nomuser.setId("nomuser");
                    nomuser.setStyle("./acceuilspotted.css");
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);

//                    Label datepub = new Label();
//                    datepub.setText(p.getDate_p().toString());
//                    nomuser.setId("datepub");
//                    nomuser.setStyle("./acceuilspotted.css");
//                    nomuser.setTranslateX(680);
//                    nomuser.setTranslateY(50);
//                    //nomuser.setPadding(new Insets(20, 10, 50, 20));
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }

                    Image im = new Image(new FileInputStream(p.getImage()));
                    ImageView imv = new ImageView(im);
                    Label septa = new Label("");
                    septa.setMinWidth(800);
                    septa.setId("septa");
                    septa.setStyle("./acceuilspotted.css");
                    imv.setFitWidth(744);
                    imv.setFitHeight(413);

                    imv.setStyle("./acceuilspotted.css");

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    lbTag.setText("#" + p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 10, 20));

                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);
                    PubVB.getChildren().add(septa);
                    PubVB.getChildren().add(imv);
                    PubVB.getChildren().add(lbTag);
                    PubVB.getChildren().add(lbseparate);

                } catch (FileNotFoundException ex) {
                    System.out.println("link pic :" + p.getImage());
                    System.out.println(ex.getMessage());
                }

            } else {
                try {
                    Image im100 = new Image(new FileInputStream("src/Media/male.png"));
                    ImageView imvmale = new ImageView(im100);
                    imvmale.setFitWidth(50);
                    imvmale.setFitHeight(50);
                    //imvmale.setScaleX(200);
                    imvmale.setTranslateX(20);
                    imvmale.setId("sex");
                    imvmale.setStyle("./acceuilspotted.css");

                    if (sexe == "male") {
                        Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                        imvmale.setImage(im1);
                    } else {
                        Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                        imvmale.setImage(im1);
                    }
                    Label nomuser = new Label();
                    nomuser.setTranslateX(80);
                    nomuser.setTranslateY(30);
                    try {
                        Timestamp datenow = Timestamp.valueOf(LocalDateTime.now());

                        DateTime datenow_c = new DateTime(datenow.getTime());
                        DateTime date_publication = new DateTime(p.getDate_p().getTime());
                        DateTime dt1 = new DateTime(p.getDate_p());
                        //DateTime po = (DateTime)p.getDate_p();
                        System.out.println("id publication = " + p.getId());
                        System.out.println("date publication = " + date_publication);
                        System.out.println("date now c= " + datenow_c);
                        System.out.println("date now = " + datenow);
                        System.out.println("horus here : " + Hours.hoursBetween(date_publication, datenow_c).getHours() % 24 + "hours, ");
                        int day_number = Days.daysBetween(date_publication, datenow_c).getDays();
                        int hour_number = Hours.hoursBetween(date_publication, datenow_c).getHours() % 24;
                        int minutes_number = Minutes.minutesBetween(date_publication, datenow_c).getMinutes() % 60;
                        int secondes_number = Seconds.secondsBetween(date_publication, datenow_c).getSeconds() % 60;

                        String c_date = "";
                        if (day_number == 0) {
                            c_date = hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0)) {
                            c_date = minutes_number + "m, " + secondes_number + "s ";

                        }
                        if ((day_number == 0) && (hour_number == 0) && (minutes_number == 0)) {
                            c_date = secondes_number + "s ";

                        }
                        if ((day_number != 0) && (hour_number != 0) && (minutes_number != 0)) {
                            c_date = day_number + "j, " + hour_number + "h, " + minutes_number + "m, " + secondes_number + "s ";

                        }
                        if (p.getUser_id() == id_current_user) {
                            nomuser.setText(name_user + "                                                                                   " + c_date);
                        } else {
                            nomuser.setText("hiden me" + "                                                                                   " + c_date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Label lbDesc = new Label();
                    lbDesc.setId("desc");
                    lbDesc.setStyle("./acceuilspotted.css");
                    lbDesc.setText(p.getDesc_p());
                    lbDesc.setPadding(new Insets(20, 10, 5, 20));

                    Label lbTag = new Label();
                    lbTag.setId("tags");
                    lbTag.setStyle("./acceuilspotted.css");
                    lbTag.setText(p.getTags());
                    lbTag.setPadding(new Insets(20, 10, 20, 20));

                    Label lbseparate = new Label("");
                    lbseparate.setId("idsep");
                    lbseparate.setMinWidth(800);
                    lbseparate.setStyle("./acceuilspotted.css");
                    PubVB.getChildren().add(nomuser);
                    PubVB.getChildren().add(imvmale);

                    PubVB.getChildren().add(lbDesc);
                    PubVB.getChildren().add(lbTag);
                    PubVB.getChildren().add(lbseparate);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AccueilSpottedController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }

    @FXML
    private void btnPhoto_Action(ActionEvent event) {
    }

    @FXML
    private void btnPub_action(ActionEvent event) {
    }

}
