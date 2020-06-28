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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;



/**
 * FXML Controller class
 *
 * @author Chibani Kanaan
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfmail;
    @FXML
    private ListView<String> lvrole;
    @FXML
    private RadioButton rsexe1;
    @FXML
    private RadioButton rsexe2;
    @FXML
    private PasswordField tfmdp1;
    @FXML
    private PasswordField tfmdp2;
    @FXML
    private JFXButton btnajout;
    @FXML
    private JFXButton btnannuler;
    @FXML
    private JFXButton btnlogin;
    @FXML
    private ToggleGroup sexegroup;
    @FXML
    private TextField username;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> role = FXCollections.observableArrayList(
                "Etudiant", "Enseignant", "Responsble Club", "Responsble Store");
        lvrole.setItems(role);
        
        Image i1= new Image("Media/inscrire.png");
        ImageView im1 = new ImageView(i1);
        im1.setFitWidth(100);
        im1.setFitHeight(50);
        btnajout.setGraphic(im1);
        Image i2= new Image("Media/login.png");
        ImageView im2 = new ImageView(i2);
        im2.setFitWidth(100);
        im2.setFitHeight(50);
        btnlogin.setGraphic(im2);
        Image i3= new Image("Media/Annuler.png");
        ImageView im3 = new ImageView(i3);
        im3.setFitWidth(100);
        im3.setFitHeight(50);
        btnannuler.setGraphic(im3);
        
    }

    public boolean validerEmail(){
    Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
    Matcher m = p.matcher(tfmail.getText());
    if (m.find() && m.group().equals(tfmail.getText())){
        return true;
    }
    else 
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation de l'email");
        alert.setHeaderText(null);
        alert.setContentText("Merci d'entrer une adrese email valide");
        alert.showAndWait();
        return false;
    }
    }
    
    @FXML
    public void annuler(ActionEvent event){
        tfnom.clear();
        tfprenom.clear();
        tfmail.clear();
        tfmdp1.clear();
        tfmdp2.clear();
        username.clear();
        
    }
    
    @FXML
    private void ajout(ActionEvent event) throws IOException {
        RadioButton chk = (RadioButton) sexegroup.getSelectedToggle(); 
        CrudUtilisateur addToolPersonne = new CrudUtilisateur();
        String mdp = tfmdp1.getText();
        mdp = BCrypt.hashpw(mdp, BCrypt.gensalt(13));
        mdp= mdp.replaceFirst("a", "y");
        
       String role = lvrole.getSelectionModel().getSelectedItem();
        if (role.equals("Etudiant")) role="a:1:{i:0;s:8:\"ETUDIANT\";}"; 
        else if(role.equals("Enseignant")) role="a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}";
        else if(role.equals("Responsble Club")) role="a:1:{i:0;s:21:\"ROLE_RESPONSABLE_CLUB\";}";
        else if(role.equals("Responsble Store")) role="a:1:{i:0;s:22:\"ROLE_RESPONSABLE_STORE\";}";
        Byte b = 1;
        
        if (validerEmail()){
        Utilisateur u = new Utilisateur(
                username.getText(),
                username.getText(),
                tfmail.getText(),
                tfmail.getText(),
                b,
                null,
                mdp,
                null,
                null,
                null,
                role,
                tfnom.getText(),
                tfprenom.getText(),
                chk.getText()
        );

       
       addToolPersonne.ajoutUtilisateur(u);
       CrudUtilisateur cr = new CrudUtilisateur();
       List<Utilisateur> list = new ArrayList<>(cr.afficherUtilisateur());
        for(Utilisateur us : list)
        {
            if(us.getUsername().matches(username.getText())||us.getEmail().matches(tfmail.getText()))
            { 
              u=us;
            }
        }
       
       PI_Main.setUser(u);
       JOptionPane.showMessageDialog(null, "Enregistré avec succès");
       
       FXMLLoader loader = new FXMLLoader(getClass().getResource("./Home.fxml"));
        Parent root;
        root = loader.load();
        btnajout.getScene().setRoot(root);
        
        
        }

    }
    
    @FXML 
    public void login(ActionEvent event) throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("./Login.fxml"));
        Parent root;
        root = loader.load();
        btnlogin.getScene().setRoot(root);
    
    }
}
