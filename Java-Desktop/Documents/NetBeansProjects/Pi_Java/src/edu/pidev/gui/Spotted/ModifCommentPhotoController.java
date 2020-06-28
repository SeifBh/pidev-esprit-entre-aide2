/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import com.jfoenix.controls.JFXButton;
import edu.pidev.entities.Commentaire;
import static edu.pidev.gui.Spotted.AccueilSpottedController.imagepath;
import static edu.pidev.gui.Spotted.ContentSpottedController.idcomm;
import static edu.pidev.gui.Spotted.ContentSpottedPhotoController.contenuecomm;
import static edu.pidev.gui.Spotted.ContentSpottedPhotoController.idcomm;
import static edu.pidev.gui.Spotted.DImageController.a1;
import edu.pidev.services.CrudCommentaire;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Seif BelHadjAli
 */
public class ModifCommentPhotoController implements Initializable {

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
    private TextArea txtComm;
    @FXML
    private Button btnModif;
    @FXML
    private ImageView imgPhoto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(imagepath);
        try {
            Image im10 = new Image(new FileInputStream(imagepath));
            ImageView imvtac = new ImageView();

            imvtac.setImage(im10);
            System.out.println(imagepath);
            imgPhoto.setImage(im10);

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        txtComm.setText(contenuecomm);
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
        public Boolean FilterPub() {
        Boolean filter = true;
        String focusword = "";
        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtComm.getText();
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
    private void btnModif_action(ActionEvent event) throws IOException {
                Boolean test1;
        test1 = true;

        int idx = ContentSpottedPhotoController.idcomm;
        CrudCommentaire cc = new CrudCommentaire();
        Commentaire cc1 = new Commentaire();
        System.out.println(idx);
        if (txtComm.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Veuillez saisir quelque chose");
            test1 = false;
        }
        if (!FilterPub()) {
            JOptionPane.showMessageDialog(null, "Mot Interdit, Veuillez ressayer");
            txtComm.setStyle("-fx-border-color:red");

        }
                if (test1 && FilterPub()) {


        cc1.setContent_comm(txtComm.getText());
        cc.updateCommentaire(cc1, idx);
        JOptionPane.showMessageDialog(null, "Commentaire Modifié");
                FXMLLoader load = new FXMLLoader();
        String ap = load.getRoot();
        System.out.println(ap);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        btnModif.getScene().setRoot(root);
    }
    }
}
