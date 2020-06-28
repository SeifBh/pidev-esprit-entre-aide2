/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.entities.Publication;
import static edu.pidev.gui.Spotted.AjoutPubPhotoController.copier;
import edu.pidev.services.CrudPublication;
import edu.pidev.utils.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ModifPublicationImageController implements Initializable {

    int j;
    Connection cnx = DataSource.getInstance().getConnection();

    @FXML
    private ImageView AddImage;
    @FXML
    private Button btnModifier;
    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;
    @FXML
    private ImageView ImageClub;
    @FXML
    private TextField tags;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        j = ListPublicationController.a1;

        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Club" + c + ".jpg");
        lien = "src/Media/Club" + c + ".jpg";
        CrudPublication myTool = new CrudPublication();

        Publication c = new Publication();

        try {
            String req = "SELECT * FROM publication WHERE id=" + j;
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            if (rs1.next()) {
                c = new Publication(rs1.getInt(1), rs1.getInt(2), rs1.getString(3), rs1.getTimestamp(4), rs1.getDate(5), rs1.getString(6), rs1.getString(7));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        tags.setText(c.getTags());

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
        if (pfile != null) {
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            ImageClub.setImage(image);
            System.out.println("Image importe avec success");
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
        System.out.println("ok");
        return true; // Résultat OK   
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        copier(pfile, pDir);
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        p.setImage(lien);
        p.setTags(p.getTags());
        myTool.updatePublication(p, j);
        JOptionPane.showMessageDialog(null, "Publication Image Modifié");
    }

}
