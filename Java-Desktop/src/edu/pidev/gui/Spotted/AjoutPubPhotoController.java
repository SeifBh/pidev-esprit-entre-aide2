/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import static com.sun.org.apache.xml.internal.serialize.LineSeparator.Windows;

import edu.pidev.entities.Publication;
import edu.pidev.services.CrudPublication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class AjoutPubPhotoController implements Initializable {

    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;

    @FXML
    private TextField tfIDUSER;
    @FXML
    private ImageView ImageClub;
    @FXML
    private Button btAJOUTER;
    @FXML
    private Button btAff;
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
    private ImageView noicon;
    @FXML
    private TextField tags;
    @FXML
    private ImageView imgup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        if(pfile != null){
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
    private void AjoutPublicationImage(ActionEvent event) {
        copier(pfile,pDir);
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        p.setImage(lien);
        p.setTags(tags.getText());
        myTool.ajoutPublication(p);
        JOptionPane.showMessageDialog(null, "Publication ajoutée avec succes");
    }

}
