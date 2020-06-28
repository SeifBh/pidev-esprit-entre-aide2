/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import edu.pidev.entities.Publication;
import edu.pidev.services.CrudPublication;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class SupprimerPublicationController implements Initializable {

    @FXML
    private Button idsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerPubication(ActionEvent event) {
        CrudPublication myTool = new CrudPublication();
        Publication p = new Publication();
        myTool.supprimerPublication(p,21000027);
        JOptionPane.showMessageDialog(null, "Publication supprimé avec succes");
    }
    
}
