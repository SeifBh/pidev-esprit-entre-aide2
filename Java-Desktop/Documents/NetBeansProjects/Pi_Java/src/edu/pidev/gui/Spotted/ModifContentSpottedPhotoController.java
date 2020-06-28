/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
import static edu.pidev.gui.Spotted.AccueilSpottedController.copier;
import static edu.pidev.gui.Spotted.AccueilSpottedController.imagepath;
import static edu.pidev.gui.Spotted.AccueilSpottedController.imagep;
import static edu.pidev.gui.Spotted.AccueilSpottedController.tagspub;
import static edu.pidev.gui.Spotted.AccueilSpottedController.idme;
import static edu.pidev.gui.Spotted.DImageController.a1;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import static edu.pidev.gui.Spotted.AccueilSpottedController.imagepath;
import static edu.pidev.gui.Spotted.ContentSpottedPhotoController.contenuecomm;
import static edu.pidev.gui.Spotted.ContentSpottedPhotoController.idcomm;
import static edu.pidev.gui.Spotted.ContentSpottedPhotoController.idpublication;
import edu.pidev.services.CrudCommentaire;
import edu.pidev.services.CrudPublication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 *
 * @author Seif BelHadjAli
 */
public class ModifContentSpottedPhotoController implements Initializable {

    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;
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
    private ImageView imgPhoto;
    @FXML
    private TextField txtTags;
    @FXML
    private ImageView uploadnow;
    @FXML
    private Button btnModifier;
    @FXML
    private ImageView upload;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    Connection cnx = DataSource.getInstance().getConnection();

        System.out.println("ceci : " + imagep);
        txtTags.setText(tagspub);
        try {

            Image im10 = new Image(new FileInputStream(imagep));
            ImageView imvtac = new ImageView();

            imvtac.setImage(im10);
            System.out.println(imagep);
            imgPhoto.setImage(im10);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Spotted" + c + ".jpg");
        lien = "src/Media/Spotted" + c + ".jpg";

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

            });
            Label lab = new Label("com ");

            lab.setText(c.getContent_comm());
            comVB.getChildren().add(lab);

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
    private void logout(ActionEvent event) {
    }

    @FXML
    private void annonces(ActionEvent event) {
    }

    @FXML
    private void clubs(ActionEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void events(ActionEvent event) {
    }

    @FXML
    private void store(ActionEvent event) {
    }

    @FXML
    private void hidemenu(ActionEvent event) {
    }

    @FXML
    private void showmenu(ActionEvent event) {
    }

    @FXML
    private void uploadnow_action(MouseEvent event) throws MalformedURLException {
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
        if (pfile != null) {
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            upload.setImage(image);
        }

    }


    public Boolean FilterTags() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtTags.getText();
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
    private void btnModifier_action(ActionEvent event) throws IOException {
        copier(pfile, pDir);

        Boolean test1;
        test1 = true;

        int idx = AccueilSpottedController.idme;

        System.out.println(idx);
        if (txtTags.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir le champ tags");
            test1 = false;
        }

        if (!FilterTags()) {
            JOptionPane.showMessageDialog(null, "Mot Tags Interdit, Veuillez ressayer");
            txtTags.setStyle("-fx-border-color:red");

        }


            CrudPublication myTool = new CrudPublication();
            Publication p = new Publication();
            System.out.println("lien du nouvelle photo = "+lien);
            p.setImage(lien);
            p.setTags(txtTags.getText());
            myTool.updatePublication(p, idx);
            JOptionPane.showMessageDialog(null, "Publication Photo Modifié");
            //FXMLLoader load = new FXMLLoader();
            //String ap = load.getRoot();
            //System.out.println(ap);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
            Parent root;
            root = loader.load();
            btnModifier.getScene().setRoot(root);

        

    }

}
