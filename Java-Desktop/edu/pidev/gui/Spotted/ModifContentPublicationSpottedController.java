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
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import static edu.pidev.gui.Spotted.AccueilSpottedController.idme;
import static edu.pidev.gui.Spotted.AccueilSpottedController.descp;
import static edu.pidev.gui.Spotted.AccueilSpottedController.tagspub;
import static edu.pidev.gui.Spotted.ContentSpottedController.contenuecomm;
import static edu.pidev.gui.Spotted.ContentSpottedController.idcomm;
import static edu.pidev.gui.Spotted.ContentSpottedController.idpublication;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.ListPublicationController.a1;
import edu.pidev.services.CrudPublication;
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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Seif BelHadjAli
 */
public class ModifContentPublicationSpottedController implements Initializable {

    int idx = AccueilSpottedController.idme;

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
    private ImageView bgesprit;
    @FXML
    private Pane paneprof;
    @FXML
    private Label usercon;
    @FXML
    private Label nbPub;
    @FXML
    private Label nbPhoto;
    @FXML
    private Label firstC;
    @FXML
    private VBox comVB;
    @FXML
    private Button btnAcceuilS;
    @FXML
    private TextArea txtModifPub;
    @FXML
    private TextField txtModifTags;
    @FXML
    private Button btnModifPub;

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

    @FXML
    private void btnAcceuilSpotted_action(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        btnAcceuilS.getScene().setRoot(root);
    }

    public Boolean FilterPub() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtModifPub.getText();
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

    public Boolean FilterTags() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtModifTags.getText();
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
    private void btnModifPub_action(ActionEvent event) throws IOException {

        Boolean test1;
        test1 = true;

        int idx = AccueilSpottedController.idme;

        System.out.println(idx);
        if (txtModifPub.getText().matches("") || txtModifTags.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir quelque chose");
            test1 = false;
        }
        if (!FilterPub()) {
            JOptionPane.showMessageDialog(null, "Mot Publication Interdit, Veuillez ressayer");
            txtModifPub.setStyle("-fx-border-color:red");

        }
        if (!FilterTags()) {
            JOptionPane.showMessageDialog(null, "Mot Tags Interdit, Veuillez ressayer");
            txtModifTags.setStyle("-fx-border-color:red");

        }

        if (test1 && FilterPub() && FilterTags()) {

            CrudPublication myTool = new CrudPublication();
            Publication p = new Publication();
            p.setDesc_p(txtModifPub.getText());
            p.setTags(txtModifTags.getText());
            txtModifPub.setStyle("-fx-border-color:grey");
            txtModifTags.setStyle("-fx-border-color:grey");

            myTool.updatePublication(p, idx);
            JOptionPane.showMessageDialog(null, "Publication Modifié");

            FXMLLoader load = new FXMLLoader();
            String ap = load.getRoot();
            System.out.println(ap);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
            Parent root;
            root = loader.load();
            btnModifPub.getScene().setRoot(root);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int idx = AccueilSpottedController.idme;
        Connection cnx = DataSource.getInstance().getConnection();

        txtModifPub.setText(descp);
        txtModifTags.setText(tagspub);

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
                try {
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
                    pane.add(new Label(c.getContent_comm()), 1, 0);
                    Label lab = new Label("com ");
                    
                    lab.setText(c.getContent_comm());
                    comVB.getChildren().add(lab);
                                    comVB.getChildren().add(imvmale);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ModifContentPublicationSpottedController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

}
