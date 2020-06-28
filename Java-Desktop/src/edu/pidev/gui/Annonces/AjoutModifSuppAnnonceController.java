/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Annonces;

import edu.pidev.entities.Annonce;
import edu.pidev.services.GestionAnnonce;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chibani Kanaan
 */
public class AjoutModifSuppAnnonceController implements Initializable {

    @FXML
    private TextField tfIdUser;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDescription;
    @FXML
    private ListView<String> lvCategorie;
    @FXML
    private TextField tfNumTel;
    @FXML
    private Label nonResolue;
    @FXML
    private Label resolue;
    @FXML
    private ChoiceBox<String> cbEtat;
    @FXML
    private Button btnEnregModif;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button reset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<String> et = FXCollections.observableArrayList("Annonce non Resolue", "Annonce Resolue");
        ObservableList<String> categorie = FXCollections.observableArrayList(
                "Objet Perdu/Trouve", "Collocation", "Covoiturage", "Permutation");
        lvCategorie.setItems(categorie);
        cbEtat.setItems(FXCollections.observableArrayList(et));
        cbEtat.setVisible(false);
        btnEnregModif.setVisible(false);
        resolue.setVisible(false);
        nonResolue.setVisible(false);


    }    
    
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        if (tfDescription.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Remplissez la description de votre annonce");
        } else if (tfTitre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Remplissez le titre de votre annonce");
        } else if (lvCategorie.getSelectionModel().getSelectedItem() != null && lvCategorie.getSelectionModel().getSelectedItem().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Choisissez la categorie de votre annonce");
        } else if (tfNumTel.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Remplissez votre numero de telephone");
        } else {

           
            int numTel = Integer.parseInt(tfNumTel.getText());
            java.util.Date date_util = new java.util.Date();
            java.sql.Date date_sql = new java.sql.Date(date_util.getTime());

            System.out.println(Date.valueOf(LocalDate.MAX));

            GestionAnnonce annonceTool = new GestionAnnonce();
            Annonce a = new Annonce(
                    PI_Main.getUser().getId(), tfTitre.getText(), tfDescription.getText(), lvCategorie.getSelectionModel().getSelectedItem(),
                    numTel, date_sql
            );
            //a.setId_user(PI_Main.getUser().getId());
            a.setEtat("Annonce Non Resolue");
            annonceTool.ajouterAnnonce(a);
            tfTitre.clear();
            tfDescription.clear();
            tfNumTel.clear();
            lvCategorie.getSelectionModel().clearSelection();

            
//            FXMLLoader loader;
//        loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/ajoutModifSuppAnnonce.fxml"));
//           
//        try {
//           AnchorPane p = loader.load();
//           paneContainer.getChildren().clear();
//             paneContainer.getChildren().add(p);
//        } catch (IOException ex) {
//            Logger.getLogger(AccueilAnnoncesController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            JOptionPane.showMessageDialog(null, "Annonce ajoutée");
            
           
            
        }
    }

    
    
    
    @FXML
    private void enregistrerModification(ActionEvent event) {
    }

    
    
}
