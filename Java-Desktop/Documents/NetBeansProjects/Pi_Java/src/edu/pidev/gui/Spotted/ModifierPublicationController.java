/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.entities.Publication;
import static edu.pidev.gui.Spotted.DetailsDescController.contentme;
import static edu.pidev.gui.Spotted.DetailsDescController.idme;
import static edu.pidev.gui.Spotted.ListPublicationController.a1;
import edu.pidev.services.CrudPublication;
import edu.pidev.utils.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class ModifierPublicationController implements Initializable {

    Connection cnx = DataSource.getInstance().getConnection();

    int j;
    @FXML
    private TextArea iddes;
    @FXML
    private Button btnmodif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        j = ListPublicationController.a1;
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

        iddes.setText(c.getDesc_p());

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
    private void modifierPublication(ActionEvent event) throws IOException {
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        p.setDesc_p(iddes.getText());
        myTool.updatePublication(p, j);
        JOptionPane.showMessageDialog(null, "Publication Modifié");
        FXMLLoader load = new FXMLLoader();
        String ap = load.getRoot();
        System.out.println(ap);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        btnmodif.getScene().setRoot(root);

    }

}
