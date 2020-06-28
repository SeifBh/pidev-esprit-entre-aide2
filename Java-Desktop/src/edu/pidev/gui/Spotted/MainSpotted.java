/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Spotted;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;


/**
 *
 * @author Seif BelHadjAli
 */

public class MainSpotted extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.println(dateFormat.toString());
            Parent root = FXMLLoader.load(getClass().getResource("./testFX.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setMaximized(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainSpotted.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
  
       
String startingDate ="06/14/2015 08:40:58";
String endingDate ="06/15/2015 11:34:20";

SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

Date date1 = null;
Date date2 = null;

try{
	date1 = format.parse(startingDate);
	date2 = format.parse(endingDate);

	DateTime dt1 = new DateTime(date1);
	DateTime dt2 = new DateTime(date2);
        System.out.println("date 1 = "+dt1);
        System.out.println("date 2 = "+dt1);
        Timestamp ap =  Timestamp.valueOf(LocalDateTime.now());
        DateTime date = new DateTime(ap.getTime());
/*
        
	 System.out.println(Days.daysBetween(dt1,date).getDays()+" days, ");
	  System.out.println(Hours.hoursBetween(dt1,dt2).getHours() % 24 + "hours, ");*/
}

catch(Exception e){
    e.printStackTrace();
}
    }
    
}

