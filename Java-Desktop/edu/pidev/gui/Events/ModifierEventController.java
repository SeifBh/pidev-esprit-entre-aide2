/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.Events;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import edu.pidev.entities.Evenement;
import edu.pidev.entities.Utilisateur;

import edu.pidev.services.EvenementServices;
import edu.pidev.tests.PI_Main;
import edu.pidev.utils.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Amine
 */
public class ModifierEventController implements Initializable, MapComponentInitializedListener {

    int c;
    File pDir;
    File pfile;
    String lien;
    int file = 0;

    private GoogleMap map;
    private GeocodingService geocodingService;

    @FXML
    private TextField titreE;
    @FXML
    private TextArea descE;
    @FXML
    private DatePicker dateE;
    @FXML
    private Button annuler;
    @FXML
    private ComboBox typeE;
    @FXML
    private ImageView imageEvent;
    @FXML
    private ImageView verif_titre;
    @FXML
    private ImageView verif_desc;
    @FXML
    private ImageView verif_date;
    @FXML
    private ImageView verif_type;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextArea adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private ImageView verif_map;
    @FXML
    private Button modifier;

    private static String i;
    @FXML
    private Button gardermaps;
    @FXML
    private JFXTimePicker time;
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
        showMenu.setGraphic(imv0);
        hideMenu.setGraphic(imv00);
        home.setGraphic(imv1);
        clubs.setGraphic(imv2);
        annonces.setGraphic(imv3);
        stores.setGraphic(imv4);
        spotted.setGraphic(imv5);
        logout.setGraphic(imv6);

        FadeTransition f = new FadeTransition(Duration.millis(1), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        hideMenu.setVisible(false);

        typeE.getItems().addAll("Seminaire", "Workshop", "Culturel", "Gaming", "Coding", "Sportif", "Autre");
        c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/Media/Evenement" + c + ".jpg");
        lien = "Evenement" + c + ".jpg";

        int j = AccueilEventsController.i;
        mapView.addMapInializedListener(this);
        EvenementServices ev = EvenementServices.getInstance();
        String req = "SELECT * FROM evenement where id=" + j;

        Connection cnx = DataSource.getInstance().getConnection();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement event = new Evenement(rs.getString("titre_e"), rs.getString("desc_e"), rs.getTimestamp("date_e"), 1, "ADMIN", 1, rs.getString("image"), rs.getString("type_e"), rs.getString("googlemaps"));
                titreE.setText(event.getTitre_e());
                descE.setText(event.getDesc_e());
                LocalDate d = event.getDate_e().toLocalDateTime().toLocalDate();
                dateE.setValue(d);
                time.setValue(event.getDate_e().toLocalDateTime().toLocalTime());

                imageEvent.setImage(new Image("Media/" + event.getImage()));


                typeE.setValue(typeE.getItems().get(typeE.getItems().indexOf(event.getType_e())));

            }

        } catch (SQLException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void choisirImage(MouseEvent event) throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG, JPEG, PNG, BMP", "*.jpg", "*.jpeg", "*.png", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file = 1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imageEvent.setImage(image);
        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @FXML
    public void modifierEvent(ActionEvent event) {

        Boolean test1, test2, test3, test4, test5;
        test1 = test2 = test3 = test4 = test5 = true;
        if (typeE.getValue() == null) {
            System.out.println("pas de categorie choisie");
            Image image1 = new Image("Media/no-icon.png");
            verif_type.setImage(image1);
            test1 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_type.setImage(image1);
        }
        if (titreE.getText().matches("")) {
            System.out.println("pas de nom");
            Image image2 = new Image("Media/no-icon.png");
            verif_titre.setImage(image2);
            test2 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_titre.setImage(image1);
        }
        if (descE.getText().matches("")) {
            System.out.println("pas de description");
            Image image3 = new Image("Media/no-icon.png");
            verif_desc.setImage(image3);
            test3 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_desc.setImage(image1);
        }

        if (dateE.getValue() == null) {
            System.out.println("pas de date insérée");
            Image image5 = new Image("Media/no-icon.png");
            verif_date.setImage(image5);
            test4 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_date.setImage(image1);
        }
        if (longitude.getText().matches("")) {
            System.out.println("pas de localisation");
            Image image7 = new Image("Media/no-icon.png");
            verif_map.setImage(image7);
            test3 = false;
        } else {
            Image image1 = new Image("Media/yes-icon.png");
            verif_map.setImage(image1);
        }

        System.out.println("tests ok");
        if (test1 && test2 && test3 && test4 && test5) {
            if (file == 0) {
                String im = imageEvent.getImage().impl_getUrl().substring(imageEvent.getImage().impl_getUrl().indexOf("Media"));
                im = im.substring(im.indexOf("/") + 1);

                EvenementServices evenement = EvenementServices.getInstance();
                LocalTime t = time.getValue();
                LocalDate date = dateE.getValue();
                Date d = convertToDateViaSqlDate(date);
                if (date.isBefore(LocalDate.now())){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas choisir une date antérieure à la date du jour", ButtonType.OK);
                    alert.showAndWait();
                }else{
                
                Timestamp ts = new Timestamp(d.getYear(), d.getMonth(), date.getDayOfMonth(), t.getHour(), t.getMinute(), 00, 00);
                Evenement e = new Evenement(titreE.getText(),
                        descE.getText(),
                        ts,
                        1,
                        "ADMIN",
                        1,
                        im,
                        typeE.getValue().toString(),
                        "a:4:{s:3:\"lat\";s:" + latitude.getText().length() + ":\"" + latitude.getText() + "\";s:3:\"lng\";s:" + longitude.getText().length() + ":\"" + longitude.getText() + "\";s:4:\"city\";s:" + ville.getText().length() + ":\"" + ville.getText() + "\";s:7:\"address\";s:" + adresse.getText().length() + ":\"" + adresse.getText() + "\";}");
               
                evenement.modifierEvenement(e, AccueilEventsController.i);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenenement modifié avec succès", ButtonType.YES);
                alert.showAndWait();
                }
            } else {
                copier(pfile, pDir);

                EvenementServices evenement = EvenementServices.getInstance();

                LocalTime t = time.getValue();
                LocalDate date = dateE.getValue();
                Date d = convertToDateViaSqlDate(date);
                if (date.isBefore(LocalDate.now())){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas choisir une date antérieure à la date du jour", ButtonType.OK);
                    alert.showAndWait();
                }else{
                
                Timestamp ts = new Timestamp(d.getYear(), d.getMonth(), date.getDayOfMonth(), t.getHour(), t.getMinute(), 00, 00);
                Evenement e = new Evenement(
                        titreE.getText(),
                        descE.getText(),
                        ts,
                        1,
                        "ADMIN",
                        1,
                        lien,
                        typeE.getValue().toString(),
                        "a:4:{s:3:\"lat\";s:" + latitude.getText().length() + ":\"" + latitude.getText() + "\";s:3:\"lng\";s:" + longitude.getText().length() + ":\"" + longitude.getText() + "\";s:4:\"city\";s:" + ville.getText().length() + ":\"" + ville.getText() + "\";s:7:\"address\";s:" + adresse.getText().length() + ":\"" + adresse.getText() + "\";}");
                

                evenement.modifierEvenement(e, AccueilEventsController.i);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Evenenement modifié avec succès", ButtonType.OK);
                alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Champs non saisis ou invalides", ButtonType.OK);
            alert.showAndWait();

        }

    }

    @FXML
    public void annuler(ActionEvent ev) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        annuler.getScene().setRoot(root);
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        System.out.println(Location);
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(13);
        map = mapView.createMap(mapOptions);

        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            markerOptions1.position(event.getLatLong())
                    .visible(true);
            m.setOptions(markerOptions1);
            map.addMarker(m);
            double longi = event.getLatLong().getLongitude();

            double lat = event.getLatLong().getLatitude();
            longitude.setText(Double.toString(longi));
            latitude.setText(Double.toString(lat));

            geocodingService.reverseGeocode(event.getLatLong().getLatitude(), event.getLatLong().getLongitude(), new GeocodingServiceCallback() {
                @Override
                public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                    String address = grs[2].toString().substring(grs[2].toString().indexOf("Address: "), grs[2].toString().indexOf(", Tunisia"));
                    address = address.substring(address.indexOf(" ") + 1, address.length());
                    ville.setText(address);
                    System.out.println(address);
                }
            });

        });

    }

    @FXML
    public void garderMaps(ActionEvent e) {
        int j = AccueilEventsController.i;
        EvenementServices ev = EvenementServices.getInstance();
        String req = "SELECT * FROM evenement where id=" + j;

        Connection cnx = DataSource.getInstance().getConnection();
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Evenement event = new Evenement(rs.getString("titre_e"), rs.getString("desc_e"), rs.getTimestamp("date_e"), 1, "ADMIN", 1, rs.getString("image"), rs.getString("type_e"), rs.getString("googlemaps"));

                i = event.getGooglemaps();

                double longi;
                double lati;

                i = i.substring(i.indexOf("lat"));
                i = i.substring(i.indexOf(":") + 1);
                Integer a = Integer.parseInt(i.substring(0, 2));
                //   System.out.println("nb caract  " + a);
                i = i.substring(i.indexOf('"') + 1);
                String lat = i.substring(0, a);
                lati = Double.parseDouble(lat);
                //  System.out.println("lati: " + lati);
                i = i.substring(i.indexOf("lng"));
                i = i.substring(i.indexOf(":") + 1);
                Integer b = Integer.parseInt(i.substring(0, 2));
                //    System.out.println("nb caract  " + b);
                i = i.substring(i.indexOf('"') + 1);
                String lng = i.substring(0, b);
                longi = Double.parseDouble(lng);
                //   System.out.println("longi :" + longi);
                longitude.setText(lng);
                latitude.setText(lat);
                LatLong Location = new LatLong(lati, longi);
                i = i.substring(i.indexOf("address"));
                i = i.substring(i.indexOf(":") + 1);
                // System.out.println(i);
                Integer c = 0;
                try {
                    c = Integer.parseInt(i.substring(0, 2));

                } catch (NumberFormatException ex) {
                    c = Integer.parseInt(i.substring(0, 1));
                }
                i = i.substring(i.indexOf('"') + 1);
                String address = i.substring(0, c);
                adresse.setText(address);
                MapOptions mapOptions = new MapOptions();
                mapOptions.center(Location)
                        .mapType(MapTypeIdEnum.ROADMAP)
                        .overviewMapControl(true)
                        .panControl(true)
                        .rotateControl(true)
                        .scaleControl(true)
                        .streetViewControl(false)
                        .zoomControl(true)
                        .zoom(13);

                map = mapView.createMap(mapOptions);

                MarkerOptions markerOptions1 = new MarkerOptions();
                Marker m = new Marker(markerOptions1);

                markerOptions1.position(Location)
                        .visible(true);
                m.setOptions(markerOptions1);
                map.addMarker(m);

                geocodingService.reverseGeocode(Location.getLatitude(), Location.getLongitude(), new GeocodingServiceCallback() {
                    @Override
                    public void geocodedResultsReceived(GeocodingResult[] grs, GeocoderStatus gs) {
                        String address = grs[2].toString().substring(grs[2].toString().indexOf("Address: "), grs[2].toString().indexOf(", Tunisia"));
                        address = address.substring(address.indexOf(" ") + 1, address.length());
                        ville.setText(address);
                        System.out.println(address);
                    }
                });

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

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
    public void showmenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(0.0);
        f.setToValue(1.0);
        f.play();
        showMenu.setVisible(false);
        hideMenu.setVisible(true);

    }

    @FXML
    public void hidemenu(ActionEvent e) {
        FadeTransition f = new FadeTransition(Duration.millis(500), menu);
        f.setFromValue(1.0);
        f.setToValue(0.0);
        f.play();
        showMenu.setVisible(true);
        hideMenu.setVisible(false);

    }

}
