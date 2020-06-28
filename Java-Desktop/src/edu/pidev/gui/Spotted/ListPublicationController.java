/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.entities.Commentaire;
import edu.pidev.services.CrudPublication;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import edu.pidev.entities.Publication;
import edu.pidev.services.CrudPublication;
import edu.pidev.utils.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.round;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class ListPublicationController implements Initializable {

    public static int a1, a2, a3, a4, a5, a6, an;

    Connection cnx = DataSource.getInstance().getConnection();

    CrudPublication cr = new CrudPublication();
    Publication c = new Publication();
    Pagination p = new Pagination(round((cr.afficherPublication().size() / 5) + 1));

    private ImageView IMGC1;
    @FXML

    private Label TF1;
    @FXML
    private Button BT1;
    private ImageView IMGC2;
    @FXML
    private Label TF2;
    @FXML
    private Button BT2;
    private ImageView IMGC3;
    @FXML
    private Label TF3;
    @FXML
    private Button BT3;
    private ImageView IMGC4;
    @FXML
    private Label TF4;
    @FXML
    private Button BT4;
    private ImageView IMGC5;
    @FXML
    private Label TF5;
    @FXML
    private Button BT5;
    private ImageView IMGC6;
    @FXML
    private Label TF6;
    @FXML
    private Button BT6;
    private Image IMG1, IMG2, IMG3, IMG4, IMG5, IMG6;
    @FXML
    private Pane P1;
    @FXML
    private Pane P2;
    @FXML
    private Pane P3;
    @FXML
    private Pane P4;
    @FXML
    private Pane P5;
    @FXML
    private Pane P6;
    TextField pageNumber = new TextField();
    @FXML
    private Label TAG1;
    @FXML
    private Label TAG2;
    @FXML
    private Label TAG3;
    @FXML
    private Label TAG4;
    @FXML
    private Label TAG5;
    @FXML
    private Label TAG6;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*  CrudPublication produitService=new CrudPublication();
        ArrayList arrayList= (ArrayList) produitService.selectAll();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        listpublication.setItems(observableList);
        
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        Button btnmodifier = new Button();

         */
        
        
        p.setPageFactory((Integer param) -> {
            pageNumber.setText(Integer.toString(param));
            return new AnchorPane();
        });

        p.setTranslateX(-380);
        p.setTranslateY(150);

        P1.getChildren().add(p);
        P2.getChildren().add(p);
        P3.getChildren().add(p);
        P4.getChildren().add(p);
        P5.getChildren().add(p);
        P6.getChildren().add(p);

        pageNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (Integer.parseInt(newValue) == 0) {

                    try {
                        AfficherClubs(cr.afficherPublication(), 0);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 1) {
                    try {
                        AfficherClubs(cr.afficherPublication(), 6);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 2) {
                    try {
                        AfficherClubs(cr.afficherPublication(), 12);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 3) {
                    try {
                        AfficherClubs(cr.afficherPublication(), 18);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (Integer.parseInt(newValue) == 4) {
                    try {
                        AfficherClubs(cr.afficherPublication(), 24);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ListPublicationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }

        });
    }

    public void AfficherClubs(List<Publication> myList, int CurrentPage) throws FileNotFoundException {

        if (myList.size() >= CurrentPage) {
            //IMG1 = new Image(new FileInputStream(myList.get(CurrentPage).getImage()));
            //IMGC1.setImage(IMG1);
            TF1.setText(myList.get(CurrentPage).getDesc_p());
            TAG1.setText(myList.get(CurrentPage).getTags());
            BT1.setVisible(true);
            BT1.setText("plus d'info");
            a1 = myList.get(CurrentPage).getId();
            an = a1;
            System.out.println(an);

        } else {
            IMGC1.setImage(null);
            TF1.setText("");
            BT1.setVisible(false);

        }
        if (myList.size() >= CurrentPage + 1) {
            //IMG2 = new Image(new FileInputStream(myList.get(CurrentPage + 1).getImage()));

            //IMGC2.setImage(IMG2);
            TF2.setText(myList.get(CurrentPage + 1).getDesc_p());
            BT2.setVisible(true);
            BT2.setText("plus d'info");
            a2 = c.getId();
            an = a2;

        } else {
            IMGC2.setImage(null);
            TF2.setText("");
            BT1.setVisible(false);

        }

        if (myList.size() >= CurrentPage + 2) {
            //IMG3 = new Image(new FileInputStream(myList.get(CurrentPage + 2).getImage()));

            //IMGC3.setImage(IMG3);
            TF3.setText(myList.get(CurrentPage + 2).getDesc_p());
            BT3.setVisible(true);
            BT3.setText("plus d'info");
            a3 = c.getId();
            an = a3;

        } else {
            IMGC3.setImage(null);
            TF3.setText("");

        }

        if (myList.size() >= CurrentPage + 3) {
            //IMG4 = new Image(new FileInputStream(myList.get(CurrentPage + 3).getImage()));

            //IMGC4.setImage(IMG4);
            TF4.setText(myList.get(CurrentPage + 3).getDesc_p());
            BT4.setVisible(true);
            BT4.setText("plus d'info");
            an = a4;

        } else {
            IMGC4.setImage(null);
            TF4.setText("");

        }

        if (myList.size() >= CurrentPage + 4) {
            //IMG5 = new Image(new FileInputStream(myList.get(CurrentPage + 4).getImage()));

            //IMGC5.setImage(IMG5);
            TF5.setText(myList.get(CurrentPage + 4).getDesc_p());
            BT5.setVisible(true);
            BT5.setText("plus d'info");
            a5 = c.getId();
            an = a5;

        } else {
            IMGC5.setImage(null);
            TF5.setText("");

        }

        if (myList.size() >= CurrentPage + 5) {
            //IMG6 = new Image(new FileInputStream(myList.get(CurrentPage + 5).getImage()), 520, 300, false, false);

            //IMGC6.setImage(IMG6);
            TF6.setText(myList.get(CurrentPage + 5).getDesc_p());
            BT6.setVisible(true);
            BT6.setText("plus d'info");
            a6 = c.getId();
            an = a6;

        } else {
            IMGC6.setImage(null);
            TF6.setText("");

        }

    }

    @FXML
    private void detail1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsDesc.fxml"));
        Parent root;
        root = loader.load();
        BT1.getScene().setRoot(root);
    }

}
