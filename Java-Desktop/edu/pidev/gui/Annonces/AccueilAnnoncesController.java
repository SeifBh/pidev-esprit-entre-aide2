/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Annonces;

import com.jfoenix.controls.JFXButton;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import edu.pidev.entities.Utilisateur;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AccueilAnnoncesController implements Initializable {

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
    private Pane paneContainer;
    @FXML
    private Button btnAjouterAnnonce;
    @FXML
    private AnchorPane paneContainerAjout;
    @FXML
    private Button btnfb;

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
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/affichageTest.fxml"));
           
        try {
           AnchorPane p = loader.load();
           paneContainer.getChildren().clear();
             paneContainer.getChildren().add(p);
        } catch (IOException ex) {
            Logger.getLogger(AccueilAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    @FXML
    public void ajouterAnnonce(ActionEvent event) throws IOException {

        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/ajoutModifSuppAnnonce.fxml"));
           
        try {
           AnchorPane p = loader.load();
           paneContainer.getChildren().clear();
             paneContainer.getChildren().add(p);
        } catch (IOException ex) {
            Logger.getLogger(AccueilAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    public void home(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    @FXML
    public void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AccueilClubs.fxml"));
        Parent root;
        root = loader.load();
        clubs.getScene().setRoot(root);
    }

    @FXML
    public void events(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        events.getScene().setRoot(root);
    }

    @FXML
    public void store(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        stores.getScene().setRoot(root);
    }

    @FXML
    public void spotted(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        spotted.getScene().setRoot(root);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
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
    public void shareFB(ActionEvent e) {
        String accessToken = "EAACEdEose0cBAOYQbVUF7Ic0O0Xs8WmtUxsDr36QVi1TMqk0GHMHvUbyqZAhvcwaAULrlcwdjvoPebkDfRpf7S1odYPX4NvT86LMEOZCCTiXD322WzPwfyzKzCx1uMEOLwze5GRWSFT4qPiXAxKb9H6v36B0Ch0UZBZBFU3yRYzuAfoFLyZCHZAkloiEEUPsgZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        FacebookType response = fbClient.publish("me/feed", FacebookType.class,Parameter.with("message", "test java publish"));
    }


}
