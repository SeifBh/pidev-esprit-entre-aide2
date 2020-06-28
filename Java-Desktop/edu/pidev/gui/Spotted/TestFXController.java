/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Seif BelHadjAli
 */
public class TestFXController implements Initializable {

    @FXML
    private TextArea txtDesc;
    @FXML
    private Button btnTest;
    @FXML
    private Rating rating;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                msg.setText("Rating : " + t1.toString());
            }
        });

    }

    @FXML
    private void btnTest_action(ActionEvent event) {

        String[] filterwords = {"Mot1", "Mot2", "Mot3"};
        String s = txtDesc.getText();
        String[] words = s.split("\\s+");
        for (int j = 0; j < filterwords.length; j++) {
            for (int i = 0; i < words.length; i++) {
                words[i] = words[i].replaceAll("[^\\w]", "");
                System.out.println(filterwords[j] + " == " + words[i]);
                if (filterwords[j].equals(words[i])) {
                    System.out.println(filterwords[j] + " == " + words[i]);
                    System.out.println("Ok");
                } else {
                    System.out.println("Not Ok");
                }
            }
        }

    }

    @FXML
    private void doso(MouseEvent event) {
    }

}
