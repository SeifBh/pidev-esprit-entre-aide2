/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Chibani Kanaan
 */
public class ApplicationController implements Initializable {

    @FXML
    private AnchorPane acMain;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private Button btnClub;
    @FXML
    private Button btnStore;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSpot;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAnnonce;
    @FXML
    private BorderPane appContent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Button btnLogOut;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Label lblUsrName;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private Label lblUserId;
    @FXML
    private StackPane acContent;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnHomeOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Home.fxml"));
        Parent root;
        root = loader.load();
        btnHome.getScene().setRoot(root);
    }


    @FXML
    private void btnUserOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GererUser.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);

    }
    @FXML
    public void btnEvenement(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Events/AdminEventHome.fxml"));
        Parent root;
        root = loader.load();
        btnEvent.getScene().setRoot(root);
    }

    @FXML
    private void btnStoreOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Stores/AdminStoreHome.fxml"));
        Parent root;
        root = loader.load();
        btnStore.getScene().setRoot(root);
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) {
        
    }


    @FXML
    private void hlUpdateAccount(ActionEvent event) {
    }

    @FXML
    private void btnLogOut(ActionEvent event) {
    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) {
    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
    }

    @FXML
    private void btnClubOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Clubs/AdminClubHome.fxml"));
        Parent root;
        root = loader.load();
        btnClub.getScene().setRoot(root);
    }

    @FXML
    private void btnSpottedOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Spotted/AdminSpottedHome.fxml"));
        Parent root;
        root = loader.load();
        btnSpot.getScene().setRoot(root);
    }

    @FXML
    private void btnAnnonceOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./Annonces/AdminAnnonceHome.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);
    }

    
}
