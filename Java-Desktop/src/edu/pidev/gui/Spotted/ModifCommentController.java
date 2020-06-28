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
import java.net.URL;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import edu.pidev.services.CrudCommentaire;
import edu.pidev.services.CrudPublication;
import edu.pidev.tests.PI_Main;
import java.io.IOException;

import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class ModifCommentController implements Initializable {

    @FXML
    private Button btnModif;
    @FXML
    private TextArea txtComment;

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

    public static String contenuecomm;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DetailsDescController dis = new DetailsDescController();
        int ad = dis.idme;
        String contentme = dis.contentme;
        txtComment.setText(contentme);
        
    }    

    @FXML
    private void btnModif_action(ActionEvent event) {
                CrudCommentaire myTool = new CrudCommentaire();
        Commentaire c = new Commentaire();
        c.setContent_comm(txtComment.getText());
        myTool.updateCommentaire(c, idme);
        JOptionPane.showMessageDialog(null, "Commentaire Modifié");
    }
    
}
