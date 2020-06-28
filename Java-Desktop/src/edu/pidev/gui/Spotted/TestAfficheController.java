/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.services.CrudPublication;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class TestAfficheController implements Initializable {

    @FXML
    private TableView<?> TableClub;
    @FXML
    private TableColumn<?, ?> columnNom;
    @FXML
    private TableColumn<?, ?> columnEmail;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnDescription;
    @FXML
    private TableColumn<?, ?> columnNombre;
    @FXML
    private TableColumn<?, ?> columnImage;
    @FXML
    private Label label;
    @FXML
    private Button btLoad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CrudPublication cr=new CrudPublication();
        ArrayList arrayList= (ArrayList) cr.afficherPublication();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        TableClub.setItems(observableList);
        columnNom.setCellValueFactory(new PropertyValueFactory<>("desc_p"));
    }    
    
}
