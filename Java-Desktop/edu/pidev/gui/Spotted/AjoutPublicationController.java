/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import java.io.File;
import java.io.IOException;
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

import edu.pidev.entities.Publication;
import edu.pidev.services.CrudPublication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class AjoutPublicationController implements Initializable {

    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;

    @FXML
    private TextField tfIDUSER;
    @FXML
    private TextArea tfDESCRIPTION;

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
    private ImageView noicon;
    @FXML
    private TextField tags;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void ajoutPublication(ActionEvent event) {
        Boolean test1;
        test1 = true;

        if (tfDESCRIPTION.getText().matches("") || tfDESCRIPTION.getText().matches(".*\\d+.*")) {
            JOptionPane.showMessageDialog(null, "verifier la Description Valide de la Club");
            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/no-icon.png");
            verif_DESCRIPTION.setImage(image1);
            test1 = false;
        } else {
            javafx.scene.image.Image image1 = new javafx.scene.image.Image("Media/yes-icon.png");
            verif_DESCRIPTION.setImage(image1);
        }

        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        if (test1) {

            p.setDesc_p(tfDESCRIPTION.getText());
            p.setTags(tags.getText());
            myTool.ajoutPublication(p);
            JOptionPane.showMessageDialog(null, "Personne ajoutée");
        }
    }

    @FXML
    private void Afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listPublication.fxml"));
        Parent root;
        root = loader.load();
        btAJOUTER.getScene().setRoot(root);
        /*/
        Parent root=FXMLLoader.load(getClass().getResource("./List.fxml"));
        Scene scene =new  Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
        //*/
        // new Alert(Alert.AlertType.INFORMATION, "sucess").show();
    }

}
