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
import edu.pidev.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import static java.sql.Types.NULL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class AccueilEventsController implements Initializable, MapComponentInitializedListener {

    @FXML
    private JFXButton home;
    @FXML
    private JFXButton clubs;
    @FXML
    private JFXButton annonces;
    @FXML
    private JFXButton stores;
    @FXML
    private JFXButton spotted;
    @FXML
    private JFXButton logout;
    @FXML
    private AnchorPane menu;
    @FXML
    private JFXButton hideMenu;
    @FXML
    private JFXButton showMenu;
    @FXML
    private ListView<Evenement> list;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button participer;
    @FXML
    private TextField rechercher;
    @FXML
    private JFXButton recherchebtn;

    private GoogleMap map;
    public static int i;
    
    @FXML
    private Button participants;
    @FXML
    private Button propositions;
    @FXML
    private Button eventsPassés;
    @FXML
    private Button eventsVenir;

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

        if (PI_Main.getUser().getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            participer.setVisible(false);
            propositions.setVisible(true);
        } else {
            participer.setVisible(true);
            propositions.setVisible(false);
        }

        mapView.addMapInializedListener(this);
        
        eventsVenir.setVisible(false);
        EvenementServices ev = EvenementServices.getInstance();
        List<Evenement> myList = new ArrayList(ev.afficherEvenement());
        List<Evenement> theList = new ArrayList();

        for (Evenement e : myList) {
            LocalDate d = e.getDate_e().toLocalDateTime().toLocalDate();
            Date date = java.sql.Date.valueOf(d);
            LocalDate d0 = LocalDate.now().minusDays(1);

            Date date0 = java.sql.Date.valueOf(d0);
            if (e.getEtat() == 0 && date.after(date0)) {
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

                                ImageView imv = new ImageView(new Image("Media/" + item.getImage()));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pidev/gui/Clubs/AffichageV2.fxml"));
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

    @FXML
    public void setMaps(MouseEvent event) {
        mapView.addMapInializedListener(this);
        String i = list.getSelectionModel().getSelectedItem().getGooglemaps();
        double longi;
        double lati;

        i = i.substring(i.indexOf("lat"));
        i = i.substring(i.indexOf(":"));
        Integer a = Integer.parseInt(i.substring(1, 3));
        //   System.out.println(a);
        i = i.substring(i.indexOf('"') + 1);
        String lat = i.substring(0, a);
        lati = Double.parseDouble(lat);
        //   System.out.println("lati: "+lati);
        i = i.substring(i.indexOf("lng"));
        i = i.substring(i.indexOf(":"));
        Integer b = Integer.parseInt(i.substring(1, 3));
        i = i.substring(i.indexOf('"') + 1);
        String lng = i.substring(0, b);
        longi = Double.parseDouble(lng);
        LatLong Location = new LatLong(lati, longi);

        MapOptions mapOptions = new MapOptions();
        mapOptions.center(Location)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(false)
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

    }

    @FXML
    public void modifierEvent(ActionEvent event) throws IOException {
        if (list.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas choisi un évenement", new ButtonType("ok"));
            alert.showAndWait();
        } else {
            i = list.getSelectionModel().getSelectedItem().getId();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("./ModifierEvents.fxml"));
            Parent root;
            root = loader.load();
            modifier.getScene().setRoot(root);
        }
    }

    @FXML
    public void participerEvent(ActionEvent eve) {

        if (list.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas choisi un évenement", new ButtonType("ok"));
            alert.showAndWait();
        } else {
            i = list.getSelectionModel().getSelectedItem().getId();
            int w = PI_Main.getUser().getId();
            Connection cnx = DataSource.getInstance().getConnection();
            if (w != NULL) {
                try {
                    String myQuery2 = "INSERT INTO participants_evenements (evenement_id,user_id) VALUES (?,?)";
                    PreparedStatement pst = cnx.prepareStatement(myQuery2);
                    pst.setInt(1, i);
                    pst.setInt(2, w);
                    pst.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Votre participation a été enregistrée", ButtonType.OK);
                    alert.showAndWait();
                } catch (SQLException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous avez déja enregistré votre participation, voulez-vous l'annulez?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult().getText().equalsIgnoreCase("oui")) {
                        String myQuery2 = "DELETE FROM participants_evenements WHERE evenement_id=? AND user_id=?";
                        PreparedStatement pst;
                        try {
                            pst = cnx.prepareStatement(myQuery2);
                            pst.setInt(1, i);
                            pst.setInt(2, w);
                            pst.executeUpdate();
                            System.out.println("participation supprimée");
                            Alert alerte = new Alert(Alert.AlertType.CONFIRMATION, "Votre participation a été annulée", ButtonType.OK);
                            alerte.showAndWait();
                        } catch (SQLException ex1) {
                            System.out.println(ex.getMessage());
                        }

                    }

                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'etes pas connecté, merci de vous authentfier!", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void ajouterEvenement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AjoutEvent.fxml"));
        Parent root;
        root = loader.load();
        ajouter.getScene().setRoot(root);
    }

    @FXML
    public void supprimerEvent(ActionEvent event) throws IOException {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, +3);
        long threeDaysahead = cal.getTimeInMillis();
        if (list.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas choisi un évenement", ButtonType.OK);
            alert.showAndWait();
        } else if (list.getSelectionModel().getSelectedItem().getId_user() != PI_Main.getUser().getId() && !PI_Main.getUser().getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas supprimer un évenement qui n'est pas le votre !", ButtonType.OK);
            alert.showAndWait();
        } else if (list.getSelectionModel().getSelectedItem().getDate_e().before(new Timestamp(threeDaysahead)) && !PI_Main.getUser().getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous ne pouvez pas supprimer cet événement car il reste moins de 3 jours à sa date, merci de contacter votre adminsitrateur!", ButtonType.OK);
            alert.showAndWait();
        } else if (list.getSelectionModel().getSelectedItem().getId_user() == PI_Main.getUser().getId() || PI_Main.getUser().getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Vous êtes sûr de vouloir supprimer cet évènement?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult().getText().equalsIgnoreCase("oui")) {
                i = list.getSelectionModel().getSelectedItem().getId();
                EvenementServices evenement = EvenementServices.getInstance();
                evenement.supprimerEvenement(i);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilEvents.fxml"));
                Parent root;
                root = loader.load();
                supprimer.getScene().setRoot(root);
            }
        }
    }

    @FXML
    public void rechercherEvent(ActionEvent ev) {
        String r = rechercher.getText();
        EvenementServices eve = EvenementServices.getInstance();
        List<Evenement> liste = new ArrayList<>(eve.afficherEvenement());
        List<Evenement> finale = new ArrayList();
        for (Evenement e : liste) {
            LocalDate d = e.getDate_e().toLocalDateTime().toLocalDate();
            Date date = java.sql.Date.valueOf(d);
            LocalDate d0 = LocalDate.now().minusDays(1);
            Date date0 = java.sql.Date.valueOf(d0);
            if (e.getEtat() == 0 && date.after(date0)) {
                finale.add(e);
            }
        }
        List<Evenement> liste2 = new ArrayList();
        for (Evenement evenement : finale) {
            if (evenement.getTitre_e().contains(r)
                    || evenement.getDesc_e().contains(r)
                    || evenement.getDate_e().toString().contains(r)
                    || evenement.getType_e().contains(r)
                    || evenement.getGooglemaps().contains(r)) {
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

                                ImageView imv = new ImageView(new Image("Media/" + item.getImage()));
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

    @Override
    public void mapInitialized() {

        MapOptions mapOptions = new MapOptions();
        LatLong Location = new LatLong(36.8981646, 10.1876613);
        MarkerOptions markerOptions1 = new MarkerOptions();
        Marker m = new Marker(markerOptions1);
        //  System.out.println(Location);
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

    }

    @FXML
    public void voirParticipant(ActionEvent e) throws IOException {
        if (list.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas choisi un évenement", new ButtonType("ok"));
            alert.showAndWait();
        } else {
            i = list.getSelectionModel().getSelectedItem().getId();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./ParticipantsEvents.fxml"));
            Parent root;
            root = loader.load();
            participants.getScene().setRoot(root);
        }
    }

    @FXML
    public void TraiterDemandes(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("./TraiterDemandesEvents.fxml"));
        Parent root;
        root = loader.load();
        propositions.getScene().setRoot(root);

    }
    
    @FXML
    public void afficherEventsPassés(ActionEvent eve){
        eventsPassés.setVisible(false);
        eventsVenir.setVisible(true);
        EvenementServices ev = EvenementServices.getInstance();
        List<Evenement> myList = new ArrayList(ev.afficherEvenement());
        List<Evenement> theList = new ArrayList();

        for (Evenement e : myList) {
            LocalDate d = e.getDate_e().toLocalDateTime().toLocalDate();
            Date date = java.sql.Date.valueOf(d);
            LocalDate d0 = LocalDate.now();

            Date date0 = java.sql.Date.valueOf(d0);
            if (e.getEtat() == 0 && date.before(date0)) {
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

                                ImageView imv = new ImageView(new Image("Media/" + item.getImage()));
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
    public void afficherEventsAVenir(ActionEvent eve) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("./AccueilEvents.fxml"));
        Parent root;
        root = loader.load();
        propositions.getScene().setRoot(root);
    }
}
