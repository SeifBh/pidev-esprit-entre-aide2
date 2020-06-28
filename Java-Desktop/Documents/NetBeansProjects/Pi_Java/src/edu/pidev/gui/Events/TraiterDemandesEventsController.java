/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Events;

import com.jfoenix.controls.JFXButton;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import edu.pidev.entities.Evenement;
import edu.pidev.entities.Utilisateur;
import edu.pidev.services.EvenementServices;
import edu.pidev.tests.PI_Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class TraiterDemandesEventsController implements Initializable, MapComponentInitializedListener {
private GoogleMap map;
    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton logout;
    @FXML
    private JFXButton annonces;
    @FXML
    private JFXButton spotted;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton clubs;
    @FXML
    private JFXButton stores;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;
    @FXML
    private ListView<Evenement> list;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField rechercher;
    @FXML
    private JFXButton recherchebtn;
    @FXML
    private JFXButton retour;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image i00 = new Image("Media/hidemenu.png");
        ImageView imv00 = new ImageView(i00);
        imv00.setFitHeight(50);
        imv00.setFitWidth(80);
        Image i0 = new Image("Media/menu.png");
        ImageView imv0 = new ImageView(i0);
        imv0.setFitHeight(50);
        imv0.setFitWidth(80);
        Image i1 = new Image("Media/home.png");
        ImageView imv1 = new ImageView(i1);
        imv1.setFitHeight(50);
        imv1.setFitWidth(80);
        Image i2 = new Image("Media/ClubsButton.png");
        ImageView imv2 = new ImageView(i2);
        imv2.setFitHeight(50);
        imv2.setFitWidth(80);
        Image i3 = new Image("Media/annonces.png");
        ImageView imv3 = new ImageView(i3);
        imv3.setFitHeight(50);
        imv3.setFitWidth(80);
        Image i4 = new Image("Media/storeButton.png");
        ImageView imv4 = new ImageView(i4);
        imv4.setFitHeight(50);
        imv4.setFitWidth(80);
        Image i5 = new Image("Media/spottedButton.png");
        ImageView imv5 = new ImageView(i5);
        imv5.setFitHeight(50);
        imv5.setFitWidth(80);
        Image i6 = new Image("Media/Logout.png");
        ImageView imv6 = new ImageView(i6);
        imv6.setFitHeight(50);
        imv6.setFitWidth(80);
        Image i7 = new Image("Media/recherche.png");
        ImageView imv7 = new ImageView(i7);
        imv7.setFitHeight(50);
        imv7.setFitWidth(80);
        showMenu.setGraphic(imv0);
        hideMenu.setGraphic(imv00);
        home.setGraphic(imv1);
        clubs.setGraphic(imv2);
        annonces.setGraphic(imv3);
        stores.setGraphic(imv4);
        spotted.setGraphic(imv5);
        logout.setGraphic(imv6);
        recherchebtn.setGraphic(imv7);
        
       
        FadeTransition f = new FadeTransition(Duration.millis(1), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        hideMenu.setVisible(false);
        
        
        Image i000 = new Image("Media/retour.png");
        ImageView imv000 = new ImageView(i00);
        imv000.setFitHeight(50);
        imv000.setFitWidth(80);
        retour.setGraphic(imv000);  
        
         mapView.addMapInializedListener(this);
        EvenementServices ev = EvenementServices.getInstance();
        List<Evenement> myList = new ArrayList(ev.afficherEvenement());
        List<Evenement> theList = new ArrayList();
        
        for (Evenement e : myList){
            
            if(e.getEtat()==1){
                theList.add(e);
                
                
            }
        }
        ObservableList<Evenement> data = FXCollections.observableArrayList(theList);
        list.getItems().clear();
        list.getItems().addAll(data);
        
        list.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

            @Override
            public ListCell<Evenement> call(ListView<Evenement> arg0) {
                return new ListCell<Evenement>() {
                    
                    @Override
                    protected void updateItem(Evenement item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            try {
                                 
                                ImageView imv = new ImageView(new Image("Media/"+item.getImage()));
                                imv.setFitHeight(100);
                                imv.setFitWidth(150);
                                
                                
                                VBox vBox1 = new VBox(
                                       
                                        imv
                                );

                                vBox1.setSpacing(4);
                               vBox1.setPrefWidth(130);
                               vBox1.setMaxWidth(130);
                               vBox1.setMinWidth(129);
                               
                                VBox vBox2 = new VBox(
                                        
                                        new Text(item.getTitre_e())
                                );
                                
                                vBox2.setSpacing(4);
                                
                                vBox2.setPrefWidth(130);
                               vBox2.setMaxWidth(130);
                               vBox2.setMinWidth(129);
                                
                                VBox vBox3 = new VBox(
                                        
                                        new Text(item.getDesc_e())
                                );
                                
                                vBox3.setSpacing(4);
                               
                                vBox3.setPrefWidth(130);
                               vBox3.setMaxWidth(130);
                               vBox3.setMinWidth(129);
                                
                                VBox vBox4 = new VBox(
                                        
                                        new Text(String.valueOf(item.getDate_e()))
                                );

                                vBox4.setSpacing(4);
                                vBox4.setPrefWidth(130);
                               vBox4.setMaxWidth(130);
                               vBox4.setMinWidth(129);
                                VBox vBox5 = new VBox(
                                        
                                        new Text(item.getType_e())
                                );

                                vBox5.setSpacing(4);
                                vBox5.setPrefWidth(120);
                               vBox5.setMaxWidth(120);
                               vBox5.setMinWidth(119);
     
                               
                                
                                
                                HBox hBox = new HBox(vBox1, vBox2, vBox3, vBox4, vBox5);
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
    private void home(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Home.fxml"));
        Parent root;
        root = loader.load();
        home.getScene().setRoot(root);
    }

    @FXML
    private void clubs(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AccueilClubs.fxml"));
        Parent root;
        root = loader.load();
        clubs.getScene().setRoot(root);
    }

    @FXML
    private void annonces(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Annonces/AccueilAnnonces.fxml"));
        Parent root;
        root = loader.load();
        annonces.getScene().setRoot(root);
    }

    @FXML
    private void store(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Stores/AccueilStore.fxml"));
        Parent root;
        root = loader.load();
        stores.getScene().setRoot(root);
    }

    @FXML
    private void spotted(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Spotted/AccueilSpotted.fxml"));
        Parent root;
        root = loader.load();
        spotted.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Utilisateur u = new Utilisateur();
        PI_Main.setUser(u);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Login.fxml"));
        Parent root;
        root = loader.load();
        logout.getScene().setRoot(root);
    }

    @FXML
    public void showmenu(ActionEvent e){
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(0.0);
        f.setToValue(1.0);
        f.play();
        showMenu.setVisible(false);
        hideMenu.setVisible(true);
    
    }
    
    @FXML
    public void hidemenu(ActionEvent e){
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        showMenu.setVisible(true);
        hideMenu.setVisible(false);
    
    }

    @FXML
    public void setMaps(MouseEvent event){
        mapView.addMapInializedListener(this);
     String i = list.getSelectionModel().getSelectedItem().getGooglemaps();
     double longi;
     double lati;
     
     i = i.substring(i.indexOf("lat"));
     i = i.substring(i.indexOf(":"));
     Integer a = Integer.parseInt(i.substring(1,3));
     //   System.out.println(a);
     i = i.substring(i.indexOf('"')+1);  
     String lat = i.substring(0, a);
     lati = Double.parseDouble(lat);
     //   System.out.println("lati: "+lati);
     i = i.substring(i.indexOf("lng"));
     i = i.substring(i.indexOf(":"));
     Integer b = Integer.parseInt(i.substring(1,3));
     i = i.substring(i.indexOf('"')+1);  
     String lng = i.substring(0, b);
     longi = Double.parseDouble(lng);
     LatLong Location = new LatLong(lati, longi);
     
     MapOptions mapOptions = new MapOptions();
     mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(13);
     
     
        map = mapView.createMap(mapOptions);

     MarkerOptions markerOptions1 = new MarkerOptions();
     Marker m = new Marker(markerOptions1);
     
     markerOptions1.position(Location)
                    .visible(true)
                    ;
            m.setOptions(markerOptions1);
            map.addMarker(m);
     
     
    }
    
    @FXML
    private void rechercherEvent(ActionEvent event) {
        String r =rechercher.getText();
        EvenementServices eve = EvenementServices.getInstance();
        List<Evenement> liste = new ArrayList<>(eve.afficherEvenement());
        List<Evenement> finale = new ArrayList();
        for (Evenement e : liste){
            if(e.getEtat()==1)
                finale.add(e);
        }
        List<Evenement> liste2 = new ArrayList();
        for (Evenement evenement : finale) {
            if(evenement.getTitre_e().contains(r)
                    ||evenement.getDesc_e().contains(r)
                    ||evenement.getDate_e().toString().contains(r)
                    ||evenement.getType_e().contains(r)
                    ||evenement.getGooglemaps().contains(r)
                    )
            {
                liste2.add(evenement);
            }
                
                
        }
        list.getItems().clear();
        ObservableList<Evenement> data2 = FXCollections.observableArrayList(liste2);
        list.getItems().addAll(data2);

        list.setCellFactory(new Callback<ListView<Evenement>, ListCell<Evenement>>() {

            @Override
            public ListCell<Evenement> call(ListView<Evenement> arg0) {
                return new ListCell<Evenement>() {

                    @Override
                    protected void updateItem(Evenement item, boolean bln) {
                        super.updateItem(item, bln);
                        if (item != null) {
                            try {

                                ImageView imv = new ImageView(new Image("Media/"+item.getImage()));
                                imv.setFitHeight(100);
                                imv.setFitWidth(150);
                                
                                
                                VBox vBox1 = new VBox(
                                       
                                        imv
                                );

                                vBox1.setSpacing(4);
                               vBox1.setPrefWidth(130);
                               vBox1.setMaxWidth(130);
                               vBox1.setMinWidth(129);
                               
                                VBox vBox2 = new VBox(
                                        
                                        new Text(item.getTitre_e())
                                );
                                
                                vBox2.setSpacing(4);
                                
                                vBox2.setPrefWidth(130);
                               vBox2.setMaxWidth(130);
                               vBox2.setMinWidth(129);
                                
                                VBox vBox3 = new VBox(
                                        
                                        new Text(item.getDesc_e())
                                );
                                
                                vBox3.setSpacing(4);
                               
                                vBox3.setPrefWidth(130);
                               vBox3.setMaxWidth(130);
                               vBox3.setMinWidth(129);
                                
                                VBox vBox4 = new VBox(
                                        
                                        new Text(String.valueOf(item.getDate_e()))
                                );

                                vBox4.setSpacing(4);
                                vBox4.setPrefWidth(130);
                               vBox4.setMaxWidth(130);
                               vBox4.setMinWidth(129);
                                VBox vBox5 = new VBox(
                                        
                                        new Text(item.getType_e())
                                );

                                vBox5.setSpacing(4);
                                vBox5.setPrefWidth(120);
                               vBox5.setMaxWidth(120);
                               vBox5.setMinWidth(119);
     
                               
                                
                                
                                HBox hBox = new HBox(vBox1, vBox2, vBox3, vBox4, vBox5);
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
    public void accepterEvent(ActionEvent e) throws IOException{
        Evenement event = new Evenement();
        event= list.getSelectionModel().getSelectedItem();
        event.setEtat(0);
        EvenementServices evenement = EvenementServices.getInstance();
        evenement.modifierEvenement(event, list.getSelectionModel().getSelectedItem().getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./TraiterDemandesEvents.fxml"));
        Parent root;
        root = loader.load();
        accepter.getScene().setRoot(root);
        
    }
    @FXML
    public void refuserEvent(ActionEvent e) throws IOException{
        Evenement event = new Evenement();
        event= list.getSelectionModel().getSelectedItem();
        event.setEtat(2);
        EvenementServices evenement = EvenementServices.getInstance();
        evenement.modifierEvenement(event, list.getSelectionModel().getSelectedItem().getId());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./TraiterDemandesEvents.fxml"));
        Parent root;
        root = loader.load();
        refuser.getScene().setRoot(root);
    }

    @Override
    public void mapInitialized() {
 MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
      //  System.out.println(Location);
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(13);
        map = mapView.createMap(mapOptions);
            }
    
    @FXML
    public void retour(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        retour.getScene().setRoot(root);
    }
    
}
