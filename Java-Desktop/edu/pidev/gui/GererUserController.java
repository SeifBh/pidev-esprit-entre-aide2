/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entities.Annonce;
import edu.pidev.entities.Utilisateur;
import edu.pidev.services.CrudUtilisateur;
import edu.pidev.services.GestionAnnonce;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Chibani Kanaan
 */
public class GererUserController implements Initializable {

    @FXML
    private Button btnAjouter;
    @FXML
    private Button reset;
    @FXML
    private TextField username;
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
    private ToggleGroup sexegroup;
    @FXML
    private RadioButton rsexe2;
    @FXML
    private PasswordField tfmdp1;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnLogout1;
    @FXML
    private Button btnRefrech;
    @FXML
    private TableColumn<Utilisateur, String> pseudo;
    @FXML
    private TableColumn<Utilisateur, String> nom;
    @FXML
    private TableColumn<Utilisateur, String> prenom;
    @FXML
    private TableColumn<Utilisateur, String> email;
    @FXML
    private TableColumn<Utilisateur, String> role;
    @FXML
    private TableView<Utilisateur> listUser;
    @FXML
    private TableColumn<Utilisateur,Integer> idUser;
    @FXML
    private Button btnEnregModif;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 ObservableList<String> role = FXCollections.observableArrayList(
                "Etudiant", "Enseignant", "Responsble Club", "Responsble Store");
        lvrole.setItems(role);  
        
        CrudUtilisateur user = new CrudUtilisateur();
        ArrayList arrayList = (ArrayList) user.afficherUtilisateur();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        
        //id.setCellValueFactory(new PropertyValueFactory<>("id"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        pseudo.setCellValueFactory(new PropertyValueFactory<>("username"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.role.setCellValueFactory(new PropertyValueFactory<>("roles"));
       
        listUser.setItems(observableList);
        
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
        
       // if (validerEmail()){
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
       
       
       PI_Main.setUser(u);
       JOptionPane.showMessageDialog(null, "Enregistré avec succès");
       
      
        
        
        

    }
    
    @FXML
    private int modifier(ActionEvent event) {

        Connection cnx = DataSource.getInstance().getConnection();
        GestionAnnonce gestionannonce = new GestionAnnonce();
       
        btnEnregModif.setVisible(true);
        btnAjouter.setVisible(false);
        reset.setVisible(false);
        int t = listUser.getSelectionModel().getSelectedItem().getId();
        System.out.println("Annonce Selectionnée");
        Utilisateur user1 = new Utilisateur();
        try {
            String req = "SELECT * FROM user WHERE id=" + t;
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                Utilisateur a = new Utilisateur(rs1.getString(2), rs1.getString(13), rs1.getString(14), rs1.getString(4));
                
                username.setText(a.username);
                tfnom.setText(a.nom);
                tfprenom.setText(a.prenom);
                tfmail.setText(a.email);
                
                tfmdp1.setVisible(false);
                btnAjouter.setVisible(false);
                lvrole.setVisible(false);
                rsexe1.setVisible(false);
                rsexe2.setVisible(false);
                }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return t;
    }

    @FXML
    private void enregistrerModification(ActionEvent event) {
        if (tfnom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "nom vide !");
        } else if (tfprenom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "prenom vide !");
        }else if(tfmail.getText().equals("")){
            JOptionPane.showMessageDialog(null, "mail vide !");
        }else if(username.getText().equals("")){
            JOptionPane.showMessageDialog(null, "username vide !");
        }else {
            CrudUtilisateur UserTool = new CrudUtilisateur();
            Utilisateur a = new Utilisateur();
            a.setUsername(username.getText());
            a.setNom(tfnom.getText());
            a.setPrenom(tfprenom.getText());
            a.setEmail(tfmail.getText());

            UserTool.updateUtilisateur(a, modifier(event));
            
        }
    }
    
    @FXML
    private void supprimerUser(ActionEvent event) throws IOException {
        Utilisateur a = listUser.getSelectionModel().getSelectedItem();
        int v = listUser.getSelectionModel().getSelectedItem().getId();
        CrudUtilisateur userTool = new CrudUtilisateur();
        userTool.supprimerUtilisateur(a, v);

        listUser.getItems().removeAll(listUser.getSelectionModel().getSelectedItem());
    }
    
     @FXML
    private void btnRetourOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Application.fxml"));
        Parent root;
        root = loader.load();
        retour.getScene().setRoot(root);

    }
    
    
}
