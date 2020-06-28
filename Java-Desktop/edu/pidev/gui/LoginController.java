/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Utilisateur;
import edu.pidev.services.CrudUtilisateur;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author Amine
 */
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnannuler;
    @FXML
    private TextField username;
    @FXML
    private PasswordField mdp;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton register;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image i1= new Image("Media/inscrire.png");
        ImageView im1 = new ImageView(i1);
        im1.setFitWidth(100);
        im1.setFitHeight(50);
        register.setGraphic(im1);
        Image i2= new Image("Media/login.png");
        ImageView im2 = new ImageView(i2);
        im2.setFitWidth(100);
        im2.setFitHeight(50);
        login.setGraphic(im2);
        Image i3= new Image("Media/Annuler.png");
        ImageView im3 = new ImageView(i3);
        im3.setFitWidth(100);
        im3.setFitHeight(50);
        btnannuler.setGraphic(im3);
    }    

    @FXML
    public void register(ActionEvent event) throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("./Register.fxml"));
        Parent root;
        root = loader.load();
        register.getScene().setRoot(root);
    }
    
    @FXML
    public void annuler(ActionEvent event){
    username.clear();
    mdp.clear();
    }
    @FXML
    public void login(ActionEvent event) throws IOException{
    
        String pseudo = username.getText();
        String pw = mdp.getText();
        Boolean t=false;
        Utilisateur myUser= new Utilisateur();
        String pwd;
        pwd = BCrypt.hashpw(pw, BCrypt.gensalt());
        CrudUtilisateur cr = new CrudUtilisateur();
        List<Utilisateur> list = new ArrayList<>(cr.afficherUtilisateur());
        for(Utilisateur us : list)
        {
            if(us.getUsername().matches(pseudo)||us.getEmail().matches(pseudo))
            { t= true;
              myUser=us;
            }
        }
        
        if (t==false) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Identifiants invalides", ButtonType.OK);
            alert.showAndWait();
        }
        else 
        {
            if (BCrypt.checkpw(pw, myUser.getPassword().replaceFirst("y", "a"))){
                PI_Main.setUser(myUser);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Succès", ButtonType.OK);
                alert.showAndWait();
                System.out.println("succes");
                if(myUser.getRoles().equalsIgnoreCase("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./Application.fxml"));
                Parent root;
                root = loader.load();
                login.getScene().setRoot(root);
                }else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./Home.fxml"));
                Parent root;
                root = loader.load();
                login.getScene().setRoot(root);
                }
            }
            else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Mot de passe invalide", ButtonType.OK);
            alert.showAndWait();
            }
                
        }
    }
    
}
