/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.tests;

import edu.pidev.entities.Utilisateur;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Amine
 */
public class PI_Main extends Application {
    private static Utilisateur user ;

    public static Utilisateur getUser() {
        return user;
    }

    public static void setUser(Utilisateur user) {
        PI_Main.user = user;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/edu/pidev/gui/Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Bonjour!");
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
