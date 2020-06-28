/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Utilisateur;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import static edu.pidev.gui.Spotted.AccueilSpottedController.idme;
import static edu.pidev.gui.Spotted.AccueilSpottedController.tagspub;
import static edu.pidev.gui.Spotted.AccueilSpottedController.descp;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.ListPublicationController.a1;
import edu.pidev.services.CrudCommentaire;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Seif BelHadjAli
 */
public class ContentSpottedController implements Initializable {

    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton annonces;
    @FXML
    private JFXButton clubs;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton events;
    @FXML
    private JFXButton stores;
    @FXML
    private ImageView logoE;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;
    @FXML
    private VBox TagVB;
    @FXML
    private Label usercon;
    @FXML
    private Label nbPub;
    @FXML
    private Label nbPhoto;
    @FXML
    private Label firstC;
    @FXML
    private Label txtDescription;
    @FXML
    private VBox comVB;
    public static int idcomm;
    public static int idpublication;

    public static String contenuecomm;
    @FXML
    private TextArea txtCommentaire;
    @FXML
    private Button btnCommenter;
    @FXML
    private ImageView bgesprit;
    @FXML
    private Pane paneprof;
    @FXML
    private Button btnAcceuilS;
    @FXML
    private Label txtTags;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int idx = AccueilSpottedController.idme;
        Connection cnx = DataSource.getInstance().getConnection();

        txtDescription.setText(descp);
        txtTags.setText("#" + tagspub);
        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire where id_publication=" + idx + "";
            System.out.println(req);
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(
                        rs1.getInt("id"),
                        rs1.getInt("id_user"),
                        rs1.getInt("id_publication"),
                        rs1.getDate("date_creation"),
                        rs1.getDate("date_modif"),
                        rs1.getString("content_comm"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        comVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        Label sexeme = new Label("");
        for (Commentaire c : myList) {
            try {
                sexeme.setId("" + c.getId());

                pane.add(new Label(c.getContent_comm()), 1, 0);
                Button btn1 = new Button();
                Button btn2 = new Button();
                btn1.setText("modifier");

                Image im100 = new Image(new FileInputStream("src/Media/male.png"));

                ImageView imvmale = new ImageView(im100);
                imvmale.setFitWidth(35);
                imvmale.setFitHeight(35);
                imvmale.setTranslateX(30);
                imvmale.setTranslateY(30);

                List<Utilisateur> myList2 = new ArrayList<Utilisateur>();

                try {

                    String req = "SELECT * FROM user where id=" + c.getId_user() + "";
                    System.out.println(req);
                    Statement statement = cnx.createStatement();
                    ResultSet rs1 = statement.executeQuery(req);
                    while (rs1.next()) {
                        Utilisateur u0 = new Utilisateur(rs1.getInt("id"), rs1.getString("sexe"));

                        if (u0.getSexe().equals("female")) {
                            System.out.println("Female IF");
                            System.out.println("id publication = " + c.getId() + " id user = " + c.getId_user() + " selected user = " + rs1.getInt("id"));

                            Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                            imvmale.setImage(im1);
                        } else {
                            System.out.println("Male IF");
                            System.out.println("id publication = " + c.getId() + " id user = " + c.getId_user() + " selected user = " + rs1.getInt("id"));

                            Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                            imvmale.setImage(im1);
                        }

                        myList2.add(u0);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                btn1.setOnAction((ActionEventn) -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCommentPublication.fxml"));
                        Parent root;
                        idpublication = c.getId_publication();
                        idcomm = c.getId();
                        contenuecomm = c.getContent_comm();

                        root = loader.load();
                        btn1.getScene().setRoot(root);
                        //JOptionPane.showMessageDialog(null, c.getContent_comm());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });

                btn1.setId("" + c.getId() + "");
                btn2.setText("supprimer");
                btn2.setOnAction((ActionEventn) -> {
                    idcomm = c.getId();
                    CrudCommentaire myTool = new CrudCommentaire();
                    myTool.supprimerCommentaire(c, idcomm);
                    JOptionPane.showMessageDialog(null, "Commentaire supprimé avec succes");
                    callme();

                });
                Label lab = new Label("com ");
                lab.setTranslateX(70);
                btn1.setTranslateX(450);
                
                Image imageDecline = new Image("Media/edit.png");
                ImageView imageView = new ImageView(imageDecline);
                imageView.setFitHeight(17);
                imageView.setFitWidth(17);
                btn1.setGraphic(imageView);
                
                Image imageDecline2 = new Image("Media/remove.png");
                ImageView imageView2 = new ImageView(imageDecline2);
                imageView2.setFitHeight(17);
                imageView2.setFitWidth(17);
                btn2.setGraphic(imageView2);

                btn2.setTranslateX(450);
                Label lbseparate = new Label("");
                lbseparate.setId("idsepcom");
                lbseparate.setMinWidth(900);
                lbseparate.setMinHeight(1);

                lbseparate.setPadding(new Insets(10, 10, 10, 10));

                lbseparate.setStyle("./acceuilspotted.css");

                lab.setText(c.getContent_comm());
                comVB.getChildren().add(imvmale);
                comVB.getChildren().add(lab);
                comVB.getChildren().add(btn1);
                comVB.getChildren().add(btn2);
                comVB.getChildren().add(lbseparate);

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public void callme() {
        int idx = AccueilSpottedController.idme;
        Connection cnx = DataSource.getInstance().getConnection();

        txtDescription.setText(descp);
        txtTags.setText("#" + tagspub);
        List<Commentaire> myList = new ArrayList<Commentaire>();

        try {
            String req = "SELECT * FROM commentaire where id_publication=" + idx + "";
            System.out.println(req);
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Commentaire c = new Commentaire(
                        rs1.getInt("id"),
                        rs1.getInt("id_user"),
                        rs1.getInt("id_publication"),
                        rs1.getDate("date_creation"),
                        rs1.getDate("date_modif"),
                        rs1.getString("content_comm"));

                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        comVB.getChildren().clear();
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(20);
        Label sexeme = new Label("");
        for (Commentaire c : myList) {
            try {
                sexeme.setId("" + c.getId());

                pane.add(new Label(c.getContent_comm()), 1, 0);
                Button btn1 = new Button();
                Button btn2 = new Button();
                btn1.setText("modifier");

                Image im100 = new Image(new FileInputStream("src/Media/male.png"));

                ImageView imvmale = new ImageView(im100);
                imvmale.setFitWidth(35);
                imvmale.setFitHeight(35);
                imvmale.setTranslateX(30);
                imvmale.setTranslateY(30);

                List<Utilisateur> myList2 = new ArrayList<Utilisateur>();

                try {

                    String req = "SELECT * FROM user where id=" + c.getId_user() + "";
                    System.out.println(req);
                    Statement statement = cnx.createStatement();
                    ResultSet rs1 = statement.executeQuery(req);
                    while (rs1.next()) {
                        Utilisateur u0 = new Utilisateur(rs1.getInt("id"), rs1.getString("sexe"));

                        if (u0.getSexe().equals("female")) {
                            System.out.println("Female IF");
                            System.out.println("id publication = " + c.getId() + " id user = " + c.getId_user() + " selected user = " + rs1.getInt("id"));

                            Image im1 = new Image(new FileInputStream("src/Media/female.png"));
                            imvmale.setImage(im1);
                        } else {
                            System.out.println("Male IF");
                            System.out.println("id publication = " + c.getId() + " id user = " + c.getId_user() + " selected user = " + rs1.getInt("id"));

                            Image im1 = new Image(new FileInputStream("src/Media/male.png"));
                            imvmale.setImage(im1);
                        }

                        myList2.add(u0);

                    }

                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

                btn1.setOnAction((ActionEventn) -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCommentPublication.fxml"));
                        Parent root;
                        idpublication = c.getId_publication();
                        idcomm = c.getId();
                        contenuecomm = c.getContent_comm();

                        root = loader.load();
                        btn1.getScene().setRoot(root);
                        //JOptionPane.showMessageDialog(null, c.getContent_comm());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });

                btn1.setId("" + c.getId() + "");
                btn2.setText("supprimer");
                btn2.setOnAction((ActionEventn) -> {
                    idcomm = c.getId();
                    CrudCommentaire myTool = new CrudCommentaire();
                    myTool.supprimerCommentaire(c, idcomm);
                    JOptionPane.showMessageDialog(null, "Commentaire supprimé avec succes");
                    callme();

                });
                Label lab = new Label("com ");
                lab.setTranslateX(70);
                btn1.setTranslateX(500);
                btn2.setTranslateX(500);
                Label lbseparate = new Label("");
                lbseparate.setId("idsepcom");
                lbseparate.setMinWidth(600);
                lbseparate.setMinHeight(1);
                lbseparate.setPadding(new Insets(10, 10, 10, 10));

                lbseparate.setStyle("./acceuilspotted.css");

                lab.setText(c.getContent_comm());
                comVB.getChildren().add(imvmale);
                comVB.getChildren().add(lab);
                comVB.getChildren().add(btn1);
                comVB.getChildren().add(btn2);
                comVB.getChildren().add(lbseparate);

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }

    public Boolean FilterPub() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtCommentaire.getText();
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
    private void btnCommenter_action(ActionEvent event) {
        Boolean test1;
        test1 = true;

        int idx = AccueilSpottedController.idme;
        CrudCommentaire cc = new CrudCommentaire();
        Commentaire cc1 = new Commentaire();
        System.out.println(idx);
        if (txtCommentaire.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir quelque chose");
            test1 = false;
        }
        if (!FilterPub()) {
            JOptionPane.showMessageDialog(null, "Mot Interdit, Veuillez ressayer");
            txtCommentaire.setStyle("-fx-border-color:red");

        }
        if (test1 && FilterPub()) {

            cc.ajoutCommentaire(cc1, idx, txtCommentaire.getText());
            JOptionPane.showMessageDialog(null, "Commentaire Ajoutee avec success");
            txtCommentaire.setText("");
            callme();
        }

    }

    @FXML
    private void btnAcceuilSpotted_action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        btnAcceuilS.getScene().setRoot(root);
    }

}
