/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Annonces;

import edu.pidev.entities.Annonce;
import edu.pidev.services.GestionAnnonce;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author kanaan
 */
public class AffichageTestController implements Initializable {

    public static int a1, a2, a3, a4;

    
    Connection cnx = DataSource.getInstance().getConnection();

    GestionAnnonce annonceTool = new GestionAnnonce();
    Annonce c = new Annonce();
    //if((annonceTool.afficherAnnonce().size()) % 2 == 0){
    Pagination p = new Pagination((annonceTool.afficherAnnonce().size() / 4) + 1);
//}
    @FXML
    private ImageView IMGC1;
    @FXML
    private Label TF1;
    @FXML
    private Button BT1;
    @FXML
    private ImageView IMGC2;
    @FXML
    private Label TF2;
    @FXML
    private Button BT2;
    @FXML
    private ImageView IMGC3;
    @FXML
    private Label TF3;
    @FXML
    private Button BT3;
    @FXML
    private ImageView IMGC4;
    @FXML
    private Label TF4;
    @FXML
    private Button BT4;

    private Image IMG1, IMG2, IMG3, IMG4;
    @FXML
    private Pane P1;
    @FXML
    private Pane P2;
    @FXML
    private Pane P3;
    @FXML
    private Pane P4;
    TextField pageNumber = new TextField();
    @FXML
    private Label L1Pane1;
    @FXML
    private Label L2Pane2;
    @FXML
    private Label L3Pane3;
    @FXML
    private Label L4Pane4;
    @FXML
    private AnchorPane paneContainer;
    @FXML
    private Label tfUsername1;
    @FXML
    private Label tfDescription1;
    @FXML
    private Label tfUsername2;
    @FXML
    private Label tfDescription2;
    @FXML
    private Label tfUsername4;
    @FXML
    private Label tfDescription4;
    @FXML
    private Label tfUsername3;
    @FXML
    private Label tfDescription3;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        p.setPageFactory((Integer param) -> {
            pageNumber.setText(Integer.toString(param));
            return new AnchorPane();
        });

        p.setTranslateX(-300);
        p.setTranslateY(450);

        P1.getChildren().add(p);
        P2.getChildren().add(p);
        P3.getChildren().add(p);
        P4.getChildren().add(p);

        pageNumber.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println("******************** newValue **********************");
            System.out.println("*************************newValue*****************");
            System.out.println("************************newValue******************");
            System.out.println(newValue);
            System.out.println("************************newValue******************");
            System.out.println("**************************newValue****************");
            System.out.println("****************************newValue**************");
            if (Integer.parseInt(newValue) == 0) {
                
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 0);
                    
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 0 currentpage = 0 ");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    // Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 0 ");
                    System.out.println("\n------------------------------------");
                }
                
            }
            if (Integer.parseInt(newValue) == 1) {
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 4);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 1 currentpage = 4");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 1 ");
                    System.out.println("\n------------------------------------");
                }
                
            }
            if (Integer.parseInt(newValue) == 2) {
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 8);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 2 currentpage = 8");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 2 ");
                    System.out.println("\n------------------------------------");
                }
                
            }
            if (Integer.parseInt(newValue) == 3) {
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 12);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 3 currentpage = 12");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 3 ");
                    System.out.println("\n------------------------------------");
                }
                
            }
            if (Integer.parseInt(newValue) == 4) {
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 16);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 4 currentpage = 16");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 4 ");
                    System.out.println("\n------------------------------------");
                }
            }
            if (Integer.parseInt(newValue) == 5) {
                try {
                    AfficherAnnonce(annonceTool.afficherAnnonce(), 20);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi if 5 currentpage = 20");
                    System.out.println("\n------------------------------------");
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AffichageTestController.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("\n------------------------------------");
                    System.out.println(" hedhi caaaatch 5 ");
                    System.out.println("\n------------------------------------");
                }
            }
        });
        System.out.println(annonceTool.afficherAnnonce().size());
        
    }

    public void AfficherAnnonce(List<Annonce> myList, int CurrentPage) throws FileNotFoundException {
        try {
            p.setVisible(true);
            IMGC1.setVisible(false);
            IMGC2.setVisible(false);
            IMGC3.setVisible(false);
            IMGC4.setVisible(false);

            TF1.setVisible(false);
            TF2.setVisible(false);
            TF3.setVisible(false);
            TF4.setVisible(false);

            BT1.setVisible(false);
            BT2.setVisible(false);
            BT3.setVisible(false);
            BT4.setVisible(false);

            L1Pane1.setVisible(false);
            L2Pane2.setVisible(false);
            L3Pane3.setVisible(false);
            L4Pane4.setVisible(false);
            
//            P1.setVisible(false);
//            P2.setVisible(false);
//            P3.setVisible(false);
//            P4.setVisible(false);

            if (myList.get(CurrentPage) != null) {
                IMGC1.setVisible(true);
                TF1.setVisible(true);
                BT1.setVisible(true);
                L1Pane1.setVisible(true);
                P1.setStyle("-fx-border-color: black;");
                if ("Objet Perdu/Trouve".equals(myList.get(CurrentPage).getCategorie_a())) {
                    IMG1 = new Image("edu/pidev/gui/Annonces/image/question.png");
                }
                if ("Collocation".equals(myList.get(CurrentPage).getCategorie_a())) {
                    IMG1 = new Image("edu/pidev/gui/Annonces/image/maison.png");
                }
                if ("Covoiturage".equals(myList.get(CurrentPage).getCategorie_a())) {
                    IMG1 = new Image("edu/pidev/gui/Annonces/image/voiture.png");
                }
                if ("Permutation".equals(myList.get(CurrentPage).getCategorie_a())) {
                    IMG1 = new Image("edu/pidev/gui/Annonces/image/vaVient.png");
                }

                a1 =myList.get(CurrentPage).getId();
                IMGC1.setImage(IMG1);
                tfDescription1.setText(myList.get(CurrentPage).getDesc_a());
                tfUsername1.setText(annonceTool.getUserName(myList.get(CurrentPage).id_user));
                TF1.setText(myList.get(CurrentPage).getTitre_a());
                //TF1.setText(PI_Main.getUser().getUsername());
                BT1.setVisible(true);
                BT1.setStyle("-fx-background-color: #337AB7; -fx-text-fill: white; -fx-font-size: 14;");
                BT1.setText("Voir");
                if ("Annonce non Resolue".equals(myList.get(CurrentPage).getEtat())) {
                    L1Pane1.setStyle("-fx-background-color: #ff9800;");
                    L1Pane1.setText("Non Resolue !!");
                } else {
                    L1Pane1.setStyle("-fx-background-color:  #42f44b;");
                    L1Pane1.setText("Resolue !!");
                }

                System.out.println("**************myList.get(CurrentPage).getId()+\"/\"+a****************");
                System.out.println(myList.get(CurrentPage).getId() + "/" + a1);
                System.out.println("******************myList.get(CurrentPage).getId()+\"/\"+a************");

                System.out.println("ID: " + myList.get(CurrentPage).getId() + "|*" + "ID User:" + myList.get(CurrentPage).getId_user() + "|*" + "Titre:" + myList.get(CurrentPage).getTitre_a()
                        + "|*" + "Description:" + myList.get(CurrentPage).getDesc_a());
                System.out.println("Categorie: " + myList.get(CurrentPage).getCategorie_a() + "|*" + "Numero Telephone:" + myList.get(CurrentPage).getNum_tel() + "|*" + "Date Publication Annonce:"
                        + myList.get(CurrentPage).getDate_a() + "|*" + "Description:" + myList.get(CurrentPage).getDesc_a());
                System.out.println("Date Modification Annonce:: " + myList.get(CurrentPage).getDate_modif() + "|*" + "Etat Annonce :" + myList.get(CurrentPage).getEtat());

            } else {

                IMGC1.setImage(null);
                TF1.setText("");
                BT1.setVisible(false);
                System.out.println("d5alna fil else");

            }
            if (myList.get(CurrentPage + 1) != null) {
                IMGC2.setVisible(true);
                TF2.setVisible(true);
                BT2.setVisible(true);
                L2Pane2.setVisible(true);
                P2.setStyle("-fx-border-color: black;");
                if ("Objet Perdu/Trouve".equals(myList.get(CurrentPage + 1).getCategorie_a())) {
                    IMG2 = new Image("edu/pidev/gui/Annonces/image/question.png");
                }
                if ("Collocation".equals(myList.get(CurrentPage + 1).getCategorie_a())) {
                    IMG2 = new Image("edu/pidev/gui/Annonces/image/maison.png");
                }
                if ("Covoiturage".equals(myList.get(CurrentPage + 1).getCategorie_a())) {
                    IMG2 = new Image("edu/pidev/gui/Annonces/image/voiture.png");
                }
                if ("Permutation".equals(myList.get(CurrentPage + 1).getCategorie_a())) {
                    IMG2 = new Image("edu/pidev/gui/Annonces/image/vaVient.png");
                }
                int aa = CurrentPage + 1;
                IMGC2.setImage(IMG2);
                tfDescription2.setText(myList.get(CurrentPage +1).getDesc_a());
                tfUsername2.setText(annonceTool.getUserName(myList.get(CurrentPage +1).id_user));
                TF2.setText(myList.get(CurrentPage + 1).getTitre_a());
                BT2.setVisible(true);
                BT2.setStyle("-fx-background-color: #337AB7; -fx-text-fill: white; -fx-font-size: 14;");
                BT2.setText("Voir");
                if (myList.get(CurrentPage + 1).getEtat().equals("Annonce non Resolue")) {
                    L2Pane2.setStyle("-fx-background-color: #ff9800;");
                    L2Pane2.setText("Non Resolue !!");
                } else {
                    L2Pane2.setStyle("-fx-background-color:  #42f44b;");
                    L2Pane2.setText("Resolue !!");
                }
                a2 = myList.get(CurrentPage + 1).getId();
                System.out.println("*************myList.get(CurrentPage + 1).getId()+\"/\"+aa*****************");
                System.out.println(myList.get(CurrentPage + 1).getId() + "/" + aa);
                System.out.println("******************myList.get(CurrentPage + 1).getId()+\"/\"+aa************");
                System.out.println("ID: " + myList.get(CurrentPage + 1).getId() + "|*" + "ID User:" + myList.get(CurrentPage + 1).getId_user() + "|*" + "Titre:" + myList.get(CurrentPage + 1).getTitre_a()
                        + "|*" + "Description:" + myList.get(CurrentPage + 1).getDesc_a());
                System.out.println("Categorie: " + myList.get(CurrentPage + 1).getCategorie_a() + "|*" + "Numero Telephone:" + myList.get(CurrentPage + 1).getNum_tel() + "|*" + "Date Publication Annonce:"
                        + myList.get(CurrentPage + 1).getDate_a() + "|*" + "Description:" + myList.get(CurrentPage + 1).getDesc_a());
                System.out.println("Date Modification Annonce:: " + myList.get(CurrentPage + 1).getDate_modif() + "|*" + "Etat Annonce :" + myList.get(CurrentPage + 1).getEtat());

            } else {
                IMGC2.setImage(null);
                TF2.setText("");
                BT2.setVisible(false);
                System.out.println("d5alna fil else");

            }

            if (myList.get(CurrentPage + 2) != null) {
 
                IMGC3.setVisible(true);
                TF3.setVisible(true);
                BT3.setVisible(true);
                L3Pane3.setVisible(true);
                P3.setStyle("-fx-border-color: black;");
                if ("Objet Perdu/Trouve".equals(myList.get(CurrentPage + 2).getCategorie_a())) {
                    IMG3 = new Image("edu/pidev/gui/Annonces/image/question.png");
                }
                if ("Collocation".equals(myList.get(CurrentPage + 2).getCategorie_a())) {
                    IMG3 = new Image("edu/pidev/gui/Annonces/image/maison.png");
                }
                if ("Covoiturage".equals(myList.get(CurrentPage + 2).getCategorie_a())) {
                    IMG3 = new Image("edu/pidev/gui/Annonces/image/voiture.png");
                }
                if ("Permutation".equals(myList.get(CurrentPage + 2).getCategorie_a())) {
                    IMG3 = new Image("edu/pidev/gui/Annonces/image/vaVient.png");
                }
                int aaa = CurrentPage + 2;
                IMGC3.setImage(IMG3);
                tfDescription3.setText(myList.get(CurrentPage +2).getDesc_a());
                tfUsername3.setText(annonceTool.getUserName(myList.get(CurrentPage +2).id_user));
                TF3.setText(myList.get(CurrentPage + 2).getTitre_a());
                BT3.setVisible(true);
                BT3.setStyle("-fx-background-color: #337AB7; -fx-text-fill: white; -fx-font-size: 14;");
                BT3.setText("Voir");
                if (myList.get(CurrentPage + 2).getEtat().equals("Annonce non Resolue")) {
                    L3Pane3.setStyle("-fx-background-color: #ff9800;");
                    L3Pane3.setText("Non Resolue !!");
                } else {
                    L3Pane3.setStyle("-fx-background-color:  #42f44b;");
                    L3Pane3.setText("Resolue !!");
                }
                a3 = myList.get(CurrentPage + 2).getId();
                System.out.println("***************myList.get(CurrentPage + 2).getId()+\"/\"+aaa***************");
                System.out.println(myList.get(CurrentPage + 2).getId() + "/" + aaa);
                System.out.println("***************myList.get(CurrentPage + 2).getId()+\"/\"+aaa***************");
                System.out.println("ID: " + myList.get(CurrentPage + 2).getId() + "|*" + "ID User:" + myList.get(CurrentPage + 2).getId_user() + "|*" + "Titre:" + myList.get(CurrentPage + 2).getTitre_a()
                        + "|*" + "Description:" + myList.get(CurrentPage + 2).getDesc_a());
                System.out.println("Categorie: " + myList.get(CurrentPage + 2).getCategorie_a() + "|*" + "Numero Telephone:" + myList.get(CurrentPage + 2).getNum_tel() + "|*" + "Date Publication Annonce:"
                        + myList.get(CurrentPage + 2).getDate_a() + "|*" + "Description:" + myList.get(CurrentPage + 2).getDesc_a());
                System.out.println("Date Modification Annonce:: " + myList.get(CurrentPage + 2).getDate_modif() + "|*" + "Etat Annonce :" + myList.get(CurrentPage + 2).getEtat());

            } else {
                IMGC3.setImage(null);
                TF3.setText("");
                BT3.setVisible(false);
                System.out.println("d5alna fil else");

            }

            if (myList.get(CurrentPage + 3) != null) {

                IMGC4.setVisible(true);
                TF4.setVisible(true);
                BT4.setVisible(true);
                L4Pane4.setVisible(true);
                P4.setStyle("-fx-border-color: black;");
                if ("Objet Perdu/Trouve".equals(myList.get(CurrentPage + 3).getCategorie_a())) {
                    IMG4 = new Image("edu/pidev/gui/Annonces/image/question.png");
                }
                if ("Collocation".equals(myList.get(CurrentPage + 3).getCategorie_a())) {
                    IMG4 = new Image("edu/pidev/gui/Annonces/image/maison.png");
                }
                if ("Covoiturage".equals(myList.get(CurrentPage + 3).getCategorie_a())) {
                    IMG4 = new Image("edu/pidev/gui/Annonces/image/voiture.png");
                }
                if ("Permutation".equals(myList.get(CurrentPage + 3).getCategorie_a())) {
                    IMG4 = new Image("edu/pidev/gui/Annonces/image/vaVient.png");
                }
                int aaaa = CurrentPage + 3;
                tfDescription4.setText(myList.get(CurrentPage +3).getDesc_a());
                tfUsername4.setText(annonceTool.getUserName(myList.get(CurrentPage +3).id_user));
                IMGC4.setImage(IMG4);
                TF4.setText(myList.get(CurrentPage + 3).getTitre_a());
                BT4.setVisible(true);
                BT4.setText("Voir");
                BT4.setStyle("-fx-background-color: #337AB7; -fx-text-fill: white; -fx-font-size: 14;");
                if (myList.get(CurrentPage + 3).getEtat().equals("Annonce non Resolue")) {
                    L4Pane4.setStyle("-fx-background-color: #ff9800;");
                    L4Pane4.setText("Non Resolue !!");
                } else {
                    L4Pane4.setStyle("-fx-background-color:  #42f44b;");
                    L4Pane4.setText("Resolue !!");
                }
                a4 = myList.get(CurrentPage + 3).getId();
                System.out.println("************myList.get(CurrentPage + 3).getId()+\"/\"+aaaa******************");
                System.out.println(myList.get(CurrentPage + 3).getId() + "/" + aaaa);
                System.out.println("**************myList.get(CurrentPage + 3).getId()+\"/\"+aaaa****************");
                System.out.println("ID: " + myList.get(CurrentPage + 3).getId() + "|*" + "ID User:" + myList.get(CurrentPage + 3).getId_user() + "|*" + "Titre:" + myList.get(CurrentPage + 3).getTitre_a()
                        + "|*" + "Description:" + myList.get(CurrentPage + 3).getDesc_a());
                System.out.println("Categorie: " + myList.get(CurrentPage + 3).getCategorie_a() + "|*" + "Numero Telephone:" + myList.get(CurrentPage + 3).getNum_tel() + "|*" + "Date Publication Annonce:"
                        + myList.get(CurrentPage + 3).getDate_a() + "|*" + "Description:" + myList.get(CurrentPage + 2).getDesc_a());
                System.out.println("Date Modification Annonce:: " + myList.get(CurrentPage + 3).getDate_modif() + "|*" + "Etat Annonce :" + myList.get(CurrentPage + 3).getEtat());

            } else {
                IMGC4.setImage(null);
                TF4.setText("");
                BT4.setVisible(false);
                System.out.println("d5alna fil else");

            }

        } catch (Exception e) {

        }
    }

}
