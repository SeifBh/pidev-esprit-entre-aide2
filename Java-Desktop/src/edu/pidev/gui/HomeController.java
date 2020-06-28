/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entities.Utilisateur;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class HomeController implements Initializable {

    @FXML
    private ImageView annonce;
    @FXML
    private ImageView club;
    @FXML
    private ImageView event;
    @FXML
    private ImageView photocopie;
    @FXML
    private ImageView spotted;
    @FXML
    private ImageView logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }    
    
    @FXML
    public void logout(MouseEvent event) throws IOException {

        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Login.fxml"));
        Parent root;
        root = loader.load();
        logout.getScene().setRoot(root);

    }
    @FXML
    public void annonces(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Annonces/AccueilAnnonces.fxml"));
        Parent root;
        root = loader.load();
        annonce.getScene().setRoot(root);
    }
    
    @FXML
    public void events(MouseEvent ev) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Events/AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        event.getScene().setRoot(root);
    }
    @FXML
    public void clubs(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Clubs/AffichageV2.fxml"));
        Parent root;
        root = loader.load();
        club.getScene().setRoot(root);
    }
    @FXML
    public void stores(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        photocopie.getScene().setRoot(root);
    }
    @FXML
    public void spotted(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        spotted.getScene().setRoot(root);
    }
    
    
    
}
