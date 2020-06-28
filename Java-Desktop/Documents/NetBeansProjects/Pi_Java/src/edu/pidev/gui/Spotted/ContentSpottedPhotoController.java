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
import static edu.pidev.gui.Spotted.AccueilSpottedController.descp;
import static edu.pidev.gui.Spotted.AccueilSpottedController.idme;
import static edu.pidev.gui.Spotted.AccueilSpottedController.tagspub;
import static edu.pidev.gui.Spotted.DImageController.a1;
import edu.pidev.services.CrudCommentaire;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.IOException;
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
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static edu.pidev.gui.Spotted.AccueilSpottedController.imagepath;
import static edu.pidev.gui.Spotted.ContentSpottedController.contenuecomm;
import static edu.pidev.gui.Spotted.ContentSpottedController.idcomm;
import static edu.pidev.gui.Spotted.ContentSpottedController.idpublication;
import edu.pidev.services.CrudRating;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.controlsfx.control.Rating;

/**
 *
 * @author Seif BelHadjAli
 */
public class ContentSpottedPhotoController implements Initializable {

    int id_current_user = PI_Main.getUser().getId();

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
    private ImageView imgPhoto;
    private Image img;
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    @FXML
    private Button btnAcceuil;
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
        int id_current_user = PI_Main.getUser().getId();
        Connection cnx = DataSource.getInstance().getConnection();

        try {
            int idx = AccueilSpottedController.idme;
            Image im10 = new Image(new FileInputStream(imagepath));
            ImageView imvtac = new ImageView();

            imvtac.setImage(im10);
            System.out.println(imagepath);

            imgPhoto.setImage(im10);
            txtTags.setText("#" + tagspub);
            System.out.println(a1);
//        txtDescription.setText(descp);
            //if (idx)
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    CrudRating cra = new CrudRating();
                    edu.pidev.entities.Rating r1 = new edu.pidev.entities.Rating();
                    cra.ajoutRating(r1, idx, id_current_user, t1.toString());
                    msg.setText("Rating : " + t1.toString());
                   // JOptionPane.showMessageDialog(null, "Vote enregistré avec succes");

                    rating.setDisable(true);
                }
            });

            List<edu.pidev.entities.Rating> myListRate = new ArrayList<edu.pidev.entities.Rating>();
            try {
                String req = "SELECT * FROM rating  where id_publication=" + idx + " and  id_user=" + id_current_user + "";
                System.out.println(req);
                Statement statement = cnx.createStatement();
                ResultSet rs1 = statement.executeQuery(req);
                while (rs1.next()) {
                    edu.pidev.entities.Rating r = new edu.pidev.entities.Rating(
                            rs1.getInt("id"),
                            rs1.getInt("id_publication"),
                            rs1.getInt("id_user"),
                            rs1.getDate("created"),
                            rs1.getString("rating"));
                    myListRate.add(r);

                    if (rs1.getString("rating").equals("")) {
                        System.out.println("mafeha chay");
                    } else {
                        System.out.println("m3ebia");
                    }
                    System.out.println("condition here : " + rs1.getString("rating"));

                }
                boolean okverif = false;
                for (edu.pidev.entities.Rating r2 : myListRate) {
                    if (r2.getId_publication() == idx) {
                        okverif = true;
                        System.out.println("mraytia");
                        Double d = Double.parseDouble(r2.getRating());

                        rating.setRating(d);
                        rating.setDisable(okverif);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

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
            for (Commentaire c : myList) {
                pane.add(new Label(c.getContent_comm()), 1, 0);
                Button btn1 = new Button();
                Button btn2 = new Button();
                btn1.setText("modifier");

                btn1.setOnAction((ActionEventn) -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCommentPhoto.fxml"));
                        Parent root;
                        idpublication = c.getId_publication();
                        idcomm = c.getId();
                        contenuecomm = c.getContent_comm();

                        root = loader.load();
                        btn1.getScene().setRoot(root);
                        //JOptionPane.showMessageDialog(null, c.getContent_comm());
                    } catch (IOException ex) {
                        Logger.getLogger(DetailsDescController.class.getName()).log(Level.SEVERE, null, ex);
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

                lab.setText(c.getContent_comm());
                comVB.getChildren().add(lab);
                comVB.getChildren().add(btn1);
                comVB.getChildren().add(btn2);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContentSpottedPhotoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void callme() {
        int id_current_user = PI_Main.getUser().getId();
        Connection cnx = DataSource.getInstance().getConnection();

        try {
            int idx = AccueilSpottedController.idme;
            Image im10 = new Image(new FileInputStream(imagepath));
            ImageView imvtac = new ImageView();

            imvtac.setImage(im10);
            System.out.println(imagepath);

            imgPhoto.setImage(im10);
            txtTags.setText("#" + tagspub);
            System.out.println(a1);
//        txtDescription.setText(descp);
            //if (idx)
            rating.ratingProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                    CrudRating cra = new CrudRating();
                    edu.pidev.entities.Rating r1 = new edu.pidev.entities.Rating();
                    cra.ajoutRating(r1, idx, id_current_user, t1.toString());
                    msg.setText("Rating : " + t1.toString());
                    rating.setDisable(true);
                }
            });

            List<edu.pidev.entities.Rating> myListRate = new ArrayList<edu.pidev.entities.Rating>();
            try {
                String req = "SELECT * FROM rating  where id_publication=" + idx + " and  id_user=" + id_current_user + "";
                System.out.println(req);
                Statement statement = cnx.createStatement();
                ResultSet rs1 = statement.executeQuery(req);
                while (rs1.next()) {
                    edu.pidev.entities.Rating r = new edu.pidev.entities.Rating(
                            rs1.getInt("id"),
                            rs1.getInt("id_publication"),
                            rs1.getInt("id_user"),
                            rs1.getDate("created"),
                            rs1.getString("rating"));
                    myListRate.add(r);

                    if (rs1.getString("rating").equals("")) {
                        System.out.println("mafeha chay");
                    } else {
                        System.out.println("m3ebia");
                    }
                    System.out.println("condition here : " + rs1.getString("rating"));

                }
                boolean okverif = false;
                for (edu.pidev.entities.Rating r2 : myListRate) {
                    if (r2.getId_publication() == idx) {
                        okverif = true;
                        System.out.println("mraytia");
                        Double d = Double.parseDouble(r2.getRating());

                        rating.setRating(d);
                        rating.setDisable(okverif);
                    }
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

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
            for (Commentaire c : myList) {
                pane.add(new Label(c.getContent_comm()), 1, 0);
                Button btn1 = new Button();
                Button btn2 = new Button();
                btn1.setText("modifier");

                btn1.setOnAction((ActionEventn) -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifCommentPhoto.fxml"));
                        Parent root;
                        idpublication = c.getId_publication();
                        idcomm = c.getId();
                        contenuecomm = c.getContent_comm();

                        root = loader.load();
                        btn1.getScene().setRoot(root);
                        //JOptionPane.showMessageDialog(null, c.getContent_comm());
                    } catch (IOException ex) {
                        Logger.getLogger(DetailsDescController.class.getName()).log(Level.SEVERE, null, ex);
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

                lab.setText(c.getContent_comm());
                comVB.getChildren().add(lab);
                comVB.getChildren().add(btn1);
                comVB.getChildren().add(btn2);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContentSpottedPhotoController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void btnAcceuil_action(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilSpotted.fxml"));
        Parent root;

        root = loader.load();
        btnAcceuil.getScene().setRoot(root);

    }

}
