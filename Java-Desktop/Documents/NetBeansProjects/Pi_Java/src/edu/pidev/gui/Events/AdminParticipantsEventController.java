/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import edu.pidev.entities.Utilisateur;
import static edu.pidev.gui.Events.AccueilEventsController.i;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class AdminParticipantsEventController implements Initializable {

    @FXML
    private AnchorPane acMain;
    @FXML
    private AnchorPane acDashBord;
    @FXML
    private ScrollPane leftSideBarScroolPan;
    @FXML
    private Button btnClub;
    @FXML
    private Button btnStore;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSpot;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAnnonce;
    @FXML
    private BorderPane appContent;
    @FXML
    private AnchorPane acHead;
    @FXML
    private MenuButton mbtnUsrInfoBox;
    @FXML
    private MenuItem miPopOver;
    @FXML
    private Circle circleImgUsr;
    @FXML
    private Label lblUsrNamePopOver;
    @FXML
    private Label lblFullName;
    @FXML
    private Label lblRoleAs;
    @FXML
    private Hyperlink hlEditUpdateAccount;
    @FXML
    private Button btnLogOut;
    @FXML
    private Circle imgUsrTop;
    @FXML
    private Label lblUsrName;
    @FXML
    private ToggleButton sideMenuToogleBtn;
    @FXML
    private Label lblUserId;
    @FXML
    private StackPane acContent;
    @FXML
    private JFXListView<Utilisateur> list;
    @FXML
    private JFXButton retour;
    @FXML
    private Label nbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection cnx = DataSource.getInstance().getConnection();
        int j = AccueilEventsController.i;
        Image i00 = new Image("Media/retour.png");
        ImageView imv00 = new ImageView(i00);
        imv00.setFitHeight(50);
        imv00.setFitWidth(80);
        retour.setGraphic(imv00);  
        
        List<Utilisateur> listeU = new ArrayList();
        List<Integer> l = new ArrayList();
        
        String query = "SELECT * from participants_evenements where evenement_id=?";
        PreparedStatement pst;
                try {
                    pst = cnx.prepareStatement(query);
                    pst.setInt(1, i);
                   ResultSet rs = pst.executeQuery();
                   while(rs.next())
                   {
                       l.add(rs.getInt("user_id"));
                   }
                } catch (SQLException ex1) {
                    System.out.println(ex1.getMessage());
                }
        for (int a : l)
        {
            try {
            String myQuery30 = "SELECT * FROM user where id=?";
            PreparedStatement ps = cnx.prepareStatement(myQuery30);
            ps.setInt(1, a);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    Utilisateur u = new Utilisateur();
                    u.setId(rs.getInt(1));
                    u.setUsername(rs.getString(2));
                    u.setUsername_canonical(rs.getString(3));
                    u.setEmail(rs.getString(4));
                    u.setEmail_canonical(rs.getString(5));
                    u.setEnabled(rs.getByte(6));
                    u.setPassword(rs.getString(8));
                    u.setLast_login(java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
                    u.setRoles(rs.getString("roles"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setSexe(rs.getString("sexe"));
                    listeU.add(u);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
        nbr.setText(String.valueOf(listeU.size()));
         ObservableList<Utilisateur> data = FXCollections.observableArrayList(listeU);
        list.getItems().clear();
        list.getItems().addAll(data);
        
        list.setCellFactory(new Callback<ListView<Utilisateur>, ListCell<Utilisateur>>() {

            @Override
            public ListCell<Utilisateur> call(ListView<Utilisateur> arg0) {
                return new ListCell<Utilisateur>() {
                    
                    @Override
                    protected void updateItem(Utilisateur item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            try {
                                
                                VBox vBox1 = new VBox(
                                       
                                        new Text(item.getUsername())
                                );

                                vBox1.setSpacing(4);
                               vBox1.setPrefWidth(150);
                               vBox1.setMaxWidth(150);
                               vBox1.setMinWidth(149);
                               vBox1.setAlignment(Pos.CENTER);
                                VBox vBox2 = new VBox(
                                        
                                        new Text(item.getNom())
                                );
                                
                                vBox2.setSpacing(4);
                                
                                vBox2.setPrefWidth(150);
                               vBox2.setMaxWidth(150);
                               vBox2.setMinWidth(149);
                                vBox2.setAlignment(Pos.CENTER);
                                VBox vBox3 = new VBox(
                                        
                                        new Text(item.prenom)
                                );
                                
                                vBox3.setSpacing(4);
                               
                                vBox3.setPrefWidth(150);
                               vBox3.setMaxWidth(150);
                               vBox3.setMinWidth(149);
                                vBox3.setAlignment(Pos.CENTER);
                                VBox vBox4 = new VBox(
                                        
                                        new Text(String.valueOf(item.getEmail()))
                                );

                                vBox4.setSpacing(4);
                                vBox4.setPrefWidth(150);
                               vBox4.setMaxWidth(150);
                               vBox4.setMinWidth(149);
                                vBox4.setAlignment(Pos.CENTER);
     
                               
                                
                                
                                HBox hBox = new HBox(vBox1, vBox2, vBox3, vBox4);
                                hBox.setSpacing(50);
                                setGraphic(hBox);
                            } catch (IllegalArgumentException ex) {
                                System.out.println(ex.getMessage());
                            }
                        }
                    }

                };
            }

        });
        
    }    
     @FXML
    public void retour(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AdminEventHome.fxml"));
        Parent root;
        root = loader.load();
        retour.getScene().setRoot(root);
    }

    
@FXML
    private void btnHomeOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Home.fxml"));
        Parent root;
        root = loader.load();
        btnHome.getScene().setRoot(root);
    }


    @FXML
    private void btnUserOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./GererUser.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);

    }
    @FXML
    public void btnEvenement(ActionEvent e) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Events/AdminEventHome.fxml"));
        Parent root;
        root = loader.load();
        btnEvent.getScene().setRoot(root);
    }

    @FXML
    private void btnStoreOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AdminStoreHome.fxml"));
        Parent root;
        root = loader.load();
        btnStore.getScene().setRoot(root);
    }

    @FXML
    private void btnSettingsOnClick(ActionEvent event) {
        
    }


    @FXML
    private void hlUpdateAccount(ActionEvent event) {
    }

    @FXML
    private void btnLogOut(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        
        btnLogOut.getScene().setRoot(root);
    }

    @FXML
    private void mbtnOnClick(ActionEvent event) {
    }

    @FXML
    private void sideMenuToogleBtnOnCLick(ActionEvent event) {
    }

    @FXML
    private void acMainOnMouseMove(MouseEvent event) {
    }

    @FXML
    private void btnClubOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AdminClubHome.fxml"));
        Parent root;
        root = loader.load();
        btnClub.getScene().setRoot(root);
    }

    @FXML
    private void btnSpottedOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AdminSpottedHome.fxml"));
        Parent root;
        root = loader.load();
        btnSpot.getScene().setRoot(root);
    }
@FXML
    private void btnAnnonceOnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/AdminAnnonceHome.fxml"));
        Parent root;
        root = loader.load();
        btnUser.getScene().setRoot(root);
    }
    
    
    
    
   
}
