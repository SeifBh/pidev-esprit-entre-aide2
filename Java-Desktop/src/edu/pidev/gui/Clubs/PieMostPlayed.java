import edu.pidev.entities.Club;
import edu.pidev.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.Group;
 
public class PieMostPlayed extends Application {
     Connection cnx = DataSource.getInstance().getConnection();

    @Override public void start(Stage stage) throws SQLException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);
 
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
              
            String req = "SELECT nom_c , nbmemb FROM club";
            Statement statement = cnx.createStatement();
            ResultSet rs1 = statement.executeQuery(req);
            while (rs1.next()) {
                new PieChart.Data((rs1.getString(1)),rs1.getInt(2));

                
            }
            

            
                
                
                     
                
                
                
                
             //   new PieChart.Data("Grapefruit", 13)
                
                
                
        
        
        
        
        
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Imported Fruits");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}