/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Clubs;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Club;
import edu.pidev.entities.Utilisateur;
import static edu.pidev.gui.Events.AccueilEventsController.i;
import edu.pidev.services.CrudClub;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author radhwen
 */
public class ClubAddController implements Initializable {

    ArrayList user = new ArrayList();
    List<Utilisateur> listeUser = new ArrayList();

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
    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;

    @FXML
    private TextField tfIDUSER;
    @FXML
    private TextField tfNOMDECLUB;
    @FXML
    private TextField tfMAILDECLUB;
    @FXML
    private TextArea tfDESCRIPTION;

    @FXML
    private ImageView ImageClub;
    @FXML
    private TextField tfNBR;
    @FXML
    private Button btAJOUTER;
    @FXML
    private Button btAff;

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView verif_NOMDECLUB;
    @FXML
    private ImageView verif_MAILDECLUB;
    @FXML
    private ImageView verif_IMG;
    @FXML
    private ImageView verif_IDUSER;
    @FXML
    private ImageView verif_NBR;
    @FXML
    private ImageView verif_NOMDECLUB5;
    @FXML
    private ImageView verif_DESCRIPTION;
    @FXML
    private Button BtClear;
    @FXML
    private ComboBox<?> combobox;
    public int idu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getConnection();

        //List<Club> myList = new ArrayList<Club>();
        try {
            String req = "SELECT * from user ";
            Statement statement = cnx.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setUsername_canonical(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setEmail_canonical(rs.getString(5));
                u.setEnabled(rs.getByte(6));
                u.setPassword(rs.getString(8));
                u.setLast_login(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
                u.setRoles(rs.getString("roles"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setSexe(rs.getString("sexe"));
                listeUser.add(u);

            }

            String req1 = "SELECT * from user where roles ='a:1:{i:0;s:21:\"ROLE_RESPONSABLE_CLUB\";}'";
            ResultSet rs1 = statement.executeQuery(req1);
            while (rs1.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs1.getInt(1));
                u.setUsername(rs1.getString(2));
                u.setUsername_canonical(rs1.getString(3));
                u.setEmail(rs1.getString(4));
                u.setEmail_canonical(rs1.getString(5));
                u.setEnabled(rs1.getByte(6));
                u.setPassword(rs1.getString(8));
                u.setLast_login(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
                u.setRoles(rs1.getString("roles"));
                u.setNom(rs1.getString("nom"));
                u.setPrenom(rs1.getString("prenom"));
                u.setSexe(rs1.getString("sexe"));
                user.add(u.getUsername());

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        ObservableList obList1 = FXCollections.observableList(user);
        combobox.getItems().clear();
        combobox.setItems(obList1);
        //////////
//            for (Utilisateur u:listeUser){
//        
//            System.out.println(u)
//                    ;}

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
        ///////
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Club" + c + ".jpg");
        lien = "src/Media/Club" + c + ".jpg";

    }

    @FXML
    private void AddImage(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file = 1;

            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            ImageClub.setImage(image);
        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   

    }

    @FXML
    private void Ajout(ActionEvent event) throws IOException {

        /////////////
        java.util.Date date_util = new java.util.Date();
        java.sql.Date date_sql = new java.sql.Date(date_util.getTime());
        Boolean test1, test2, test3;
        test1 = true;
        test2 = true;
        test3 = true;
//
//        if (tfNOMDECLUB.getText().matches("") || tfNOMDECLUB.getText().matches(".*\\d+.*")) {
//            JOptionPane.showMessageDialog(null, "verifier votre nom de club");
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/no-icon.png");
//            verif_NOMDECLUB.setImage(image1);
//            test1 = false;
//        } else {
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/yes-icon.png");
//            verif_NOMDECLUB.setImage(image1);
//        }
//
//        if (tfMAILDECLUB.getText().matches("") || tfNOMDECLUB.getText().matches(".*\\d+.*")) {
//            JOptionPane.showMessageDialog(null, "verifier votre Adresse Mail");
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/no-icon.png");
//            verif_MAILDECLUB.setImage(image1);
//            test2 = false;
//        } else {
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/yes-icon.png");
//            verif_MAILDECLUB.setImage(image1);
//        }
//
//        //validation image
//        if (tfDESCRIPTION.getText().matches("") || tfDESCRIPTION.getText().matches(".*\\d+.*")) {
//            JOptionPane.showMessageDialog(null, "verifier la Description Valide de la Club");
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/no-icon.png");
//            verif_DESCRIPTION.setImage(image1);
//            test3 = false;
//        } else {
//            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/yes-icon.png");
//            verif_DESCRIPTION.setImage(image1);
//        }

        //
        CrudClub myTool = new CrudClub();
        Club c = new Club();

        /////
        if (test1 && test2 && test3) {

            String value = (String) combobox.getValue();
            for (int i = 0; i < listeUser.size(); i++) {
                if (value.equals(listeUser.get(i).username)) {
                    idu = listeUser.get(i).getId();
                }

            }

            copier(pfile, pDir);
            c.setNom_c(tfNOMDECLUB.getText());
            c.setDate_creation(date_sql);
            c.setDesc_c(tfDESCRIPTION.getText());
            //int iduser = Integer.parseInt(tfIDUSER.getText());
            c.setId_user(idu);
            c.setImage(lien);
            c.setMail_c(tfMAILDECLUB.getText() + "@esprit.tn");
            int number = Integer.parseInt(tfNBR.getText());
            c.setNbmemb(number);
            myTool.ajoutClub(c);
            //ajout avec affichage
//        FXMLLoader loader =new FXMLLoader(getClass().getResource("ClubAffiche.fxml"));
//        Parent root;
//        root=loader.load();
//        btAJOUTER.getScene().setRoot(root);
            JOptionPane.showMessageDialog(null, "Club ajouté");
        }
    }

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AffichageV2.fxml"));
        Parent root;
        root = loader.load();
        btAJOUTER.getScene().setRoot(root);

     
    }

    @FXML
    private void clear(ActionEvent event) throws IOException {
        Button buttonClear = new Button("Clear");
        BtClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tfDESCRIPTION.clear();
                tfIDUSER.clear();
                tfMAILDECLUB.clear();
                tfNBR.clear();
                tfNOMDECLUB.clear();

            }
        }
        );
    }

    public void home(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    public void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AffichageV2.fxml"));
        Parent root;
        root = loader.load();
        clubs.getScene().setRoot(root);
    }

    public void events(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        events.getScene().setRoot(root);
    }

    public void store(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        stores.getScene().setRoot(root);
    }

    public void spotted(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        spotted.getScene().setRoot(root);
    }

    public void logout(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        logout.getScene().setRoot(root);
    }

    public void showmenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(0.0);
        f.setToValue(1.0);
        f.play();
        showMenu.setVisible(false);
        hideMenu.setVisible(true);

    }

    public void hidemenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        showMenu.setVisible(true);
        hideMenu.setVisible(false);

    }
}
