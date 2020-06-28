/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.spotted;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Publication;
import static edu.pidev.gui.spotted.ListPublication.desc_static;
import static edu.pidev.gui.spotted.ListPublication.id;
import static edu.pidev.gui.spotted.ListPublication.tags_static;
import static edu.pidev.gui.spotted.ListPublication.image_static;
import edu.pidev.services.PublicationServices;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Seif BelHadjAli
 */
public class ContenuePublication {

    Form f;
    SpanLabel lbDesc;
    SpanLabel lbTags;
    Button btnModif;
    TextArea desc_p;
    TextField tags;
    Container cCommentaire2;
    private Resources theme;
    private EncodedImage ei;
    private EncodedImage ei1;
    public static int idP, idC;
    ArrayList<Commentaire> listeCommentaire;
    Container cCommentaire, cButtons, cButtons2;

    public ContenuePublication() {

        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

        try {
            ei = EncodedImage.create("/WorldCup.PNG");
            ei1 = EncodedImage.create("/WorldCup.PNG");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//            this.getF().getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_HOTEL, b -> {
//            f.showBack();
//        });
        int idx = ListPublication.id;
        String descx = ListPublication.desc_static;
        String tagsx = ListPublication.tags_static;
        String imagex = ListPublication.image_static;
        System.out.println("id publcation = " + idx);
        System.out.println("desc publcation = " + descx);
        System.out.println("tags publcation = " + tagsx);
        System.out.println("image publcation = " + imagex);
        f = new Form("Contenue Publication", BoxLayout.y());

//         PublicationServices ps = new PublicationServices();
//        ArrayList<Publication> listePublication = ps.afficher();
//         
        // for (Publication item : listePublication) {
        lbDesc = new SpanLabel("Contenue de la publication ");
        lbTags = new SpanLabel("Tags");
        Label ImagetxtPhoto = new Label();

        SpanLabel desc_txt = new SpanLabel();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel lbAjoutCommentaire = new SpanLabel();
        TextField Commtxt = new TextField();
        Commtxt.setHint("Ajouter votre commentaire ici");

        Button AjoutCommentaire = new Button("Commenter", "SaveButton");
        c2.add(lbAjoutCommentaire);
        c2.add(Commtxt);
        c2.add(AjoutCommentaire);
        PublicationServices ps = new PublicationServices();
        listeCommentaire = ps.afficherCommentaire();
        // SpanLabel newCom = new SpanLabel("");

        if (descx.equals("")) {
            String imagex2 = ListPublication.image_static;

            System.out.println("image 00" + imagex);
            System.out.println("type image clicked");
            System.err.println("ok -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + imagex);
            System.err.println("ko -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + image_static);
            Image img;
            try {
                String imagex3 = ListPublication.image_static;

                img = URLImage.createToStorage(ei, imagex,
                        imagex + "6e8e28840394ae17f3bf2c6d0a0d317a.jpeg", URLImage.RESIZE_FAIL);

//                img.scale(100, 100);
//
//                img.scaledWidth(20);
                ImagetxtPhoto.setIcon(img);
//                img.scale(100, 100);
                ImagetxtPhoto.setPreferredH(100);
                ImagetxtPhoto.setPreferredW(100);
                c1.add(ImagetxtPhoto);

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage() + " Image vide");
            }
        } else {
            System.out.println("NOT type image clicked");

        }

        SpanLabel tags_txt = new SpanLabel();
        desc_txt.setText(descx);
        tags_txt.setText(tagsx);

        //}
        c1.add(lbDesc);

        c1.add(desc_txt);
        c1.add(lbTags);
        c1.add(tags_txt);
        // c2.add(newCom);

        f.add(c1);
        f.add(c2);

        for (Commentaire item05 : listeCommentaire) {

            /*
            btnModif.setPreferredSize(new Dimension(30, 30));
            btnSupprimer.setPreferredSize(new Dimension(30, 30));
             */
            cCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cButtons = new Container(new BoxLayout(BoxLayout.X_AXIS));
            SpanLabel conCommtxt = new SpanLabel();
            conCommtxt.setText(item05.getContent_comm());

            Button btnModif = new Button("", "GreenButton");
            Button btnSupprimer = new Button("", "RedButton");
            Button btnSave = new Button("", "SaveButton");

            TextField zoneComm = new TextField();
            zoneComm.setText(item05.getContent_comm());
            zoneComm.setPreferredH(50);

            zoneComm.setVisible(false);

//            
////            btnModif.setVisible(true);
//            
//
//
            btnSupprimer.addActionListener((e) -> {
                idC = item05.getId();
                System.out.println("id c =" + idC);
                PublicationServices ps2 = new PublicationServices();
                Commentaire pub = new Commentaire();
                ps.suppCommentaire(pub);

                InteractionDialog dlg = new InteractionDialog("Succés");
                dlg.setLayout(new BorderLayout());

                Label successLabel = new Label("Commentaire supp");
                successLabel.getStyle().setFgColor(0xff000);

                dlg.add(BorderLayout.CENTER, successLabel);
                Button close = new Button("Fermer");
                close.addActionListener((ee) -> dlg.dispose());
                dlg.addComponent(BorderLayout.SOUTH, close);
                Dimension pre = dlg.getContentPane().getPreferredSize();
                dlg.show(110, 120, 0, 0);
                f.revalidate();
                PublicationServices pst = new PublicationServices();

                ContenuePublication contenuepublication = new ContenuePublication();
                System.out.println("avant affichage");
                contenuepublication.getF().show();
                System.out.println("apres affichage");
                contenuepublication.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                    f.showBack();

                });
                //f.show();

            });

            cCommentaire.add(conCommtxt);
            zoneComm.setEditable(true);
            zoneComm.setEnabled(false);
            btnSave.setVisible(false);
            zoneComm.setEditable(false);

            f.refreshTheme();

            btnModif.addActionListener((e) -> {
                btnSave.setVisible(true);
                btnModif.setVisible(false);
                f.refreshTheme();

                zoneComm.setEnabled(true);

                zoneComm.setVisible(true);
                cCommentaire.removeComponent(conCommtxt);
                conCommtxt.setVisible(false);
                conCommtxt.setEnabled(false);
                cCommentaire.refreshTheme();
                zoneComm.setEditable(true);

                f.refreshTheme();
                System.out.println("clicked modfiied button and hide label");

                //  cCommentaire.add(zoneComm);
//                btnModif.setVisible(false);
//                btnSave.setVisible(true);
//                conCommtxt.setVisible(false);
            });
            btnSave.addActionListener((e) -> {
                btnSave.setVisible(false);
                btnModif.setVisible(true);
                f.refreshTheme();

                idC = item05.getId();
                zoneComm.setEnabled(false);

                zoneComm.setEditable(false);

                zoneComm.setVisible(false);
                conCommtxt.setVisible(true);

                f.refreshTheme();
                if (conCommtxt.getText().equals(zoneComm.getText())) {
                    System.out.println("c kifkif");
                } else {
                    conCommtxt.setText("" + zoneComm.getText());

                    Commentaire com1 = new Commentaire();
                    com1.setContent_comm(zoneComm.getText());
                    PublicationServices psty = new PublicationServices();
                    psty.modifCommentaire(com1);

                    Dialog.show("Terminé", "Modification terminé avec succes", "Fermer", null);
                    //int REPEAT_NONE = LocalNotification.REPEAT_NONE;
                    LocalNotification n = new LocalNotification();
                    n.setId("TGN-notifications");
                    n.setAlertBody("Vous avez modifier un commentaire!");
                    n.setAlertTitle(" Comment!");
                    //n.setAlertSound("beep-01a.mp3");
                    Display.getInstance().scheduleLocalNotification(
                            n,
                            System.currentTimeMillis() + 10, // fire date/time
                            LocalNotification.REPEAT_NONE // Whether to repeat and what frequency
                    );

                    System.out.println("c not kifkif");

                }

                f.refreshTheme();

                System.out.println("clicked saved button and saved label");

            });

            int size = Display.getInstance().convertToPixels(6, true);
            Font materialFont = FontImage.getMaterialDesignFont();

            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            btnModif.setIcon(FontImage.create("\uE254", btnModif.getUnselectedStyle(), materialFont));
            btnSupprimer.setIcon(FontImage.create("\uE872", btnSupprimer.getUnselectedStyle(), materialFont));
            btnSave.setIcon(FontImage.create("\uE161", btnSave.getUnselectedStyle(), materialFont));

            //conCommtxt.setPreferredW(100);
            cButtons.add(btnSupprimer);
            cButtons.add(btnModif);

            cButtons.add(btnSave);

            cCommentaire.add(zoneComm);
            cCommentaire.add(cButtons);
            f.add(cCommentaire);

        }
        AjoutCommentaire.addActionListener((e) -> {
            System.out.println("it supposed to be NOT EMPTY:" + listeCommentaire);

            listeCommentaire.clear();

            System.out.println("it suppsed tp be empty :" + listeCommentaire);

            idP = idx;
            Commentaire com = new Commentaire();
            com.setContent_comm(Commtxt.getText());
            //newCom.setText("new added "+Commtxt.getText());
            ps.ajoutCommentaire(com);
            InteractionDialog dlg = new InteractionDialog("Succés");
            dlg.setLayout(new BorderLayout());

            Label successLabel = new Label("Commentaire ajoute avec succés");
            successLabel.getStyle().setFgColor(0xff000);

            dlg.add(BorderLayout.CENTER, successLabel);
            Button close = new Button("Fermer");
            f.setTintColor(0);

            Commtxt.setText("");

            close.addActionListener((ee) -> dlg.dispose());
            dlg.addComponent(BorderLayout.SOUTH, close);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.show(110, 120, 0, 0);
            PublicationServices ps7 = new PublicationServices();

            listeCommentaire = ps7.afficherCommentaire();

//            for (Commentaire item05 : listeCommentaire) {
//                cCommentaire2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//                cButtons2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//                SpanLabel conCommtxt2 = new SpanLabel();
//
//                Button btnModif2 = new Button("Modifier");
//                Button btnSupprimer2 = new Button("Supprimer");
//                Button btnSave2 = new Button("save");
//
//                TextField zoneComm2 = new TextField();
//                zoneComm2.setText(item05.getContent_comm());
//                zoneComm2.setPreferredH(50);
//                zoneComm2.setVisible(false);
//
//                //zoneComm2.setVisible(false);
//                conCommtxt2.setText(item05.getContent_comm());
//                cCommentaire2.add(conCommtxt2);
//
//                btnModif2.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//
//                        zoneComm2.setVisible(true);
//                        f.refreshTheme();
////                        zoneComm2.setVisible(true);
////                        cCommentaire2.removeComponent(conCommtxt2);
////                        cCommentaire2.setVisible(false);
////                        cCommentaire2.setEnabled(false);
////                        cCommentaire2.refreshTheme();
////                        
////                        System.out.println("clicked modfiied button and hide label");
////
////                        //  cCommentaire.add(zoneComm);
//////                btnModif.setVisible(false);
//////                btnSave.setVisible(true);
//////                conCommtxt.setVisible(false);
//                        System.out.println("modif2");
//                    }
//                });
//
//                btnSupprimer2.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        System.out.println("ABCD");
//                    }
//                });
//                btnSave2.addActionListener(new ActionListener() {
//
//                    @Override
//                    public void actionPerformed(ActionEvent evt) {
//                        idC = item05.getId();
//                        System.out.println("save2");
//                        zoneComm2.setVisible(false);
//                        f.refreshTheme();
////
////                        zoneComm2.setVisible(false);
////                        conCommtxt2.setVisible(true);
////                        if (conCommtxt2.getText().equals(zoneComm2.getText())) {
////                            System.out.println("c kifkif");
////                        } else {
////                            conCommtxt2.setText("" + zoneComm2.getText());
////
////                            Commentaire com2 = new Commentaire();
////                            com2.setContent_comm(zoneComm2.getText());
////                            PublicationServices psty = new PublicationServices();
////                            psty.modifCommentaire(com2);
////                            InteractionDialog dlg = new InteractionDialog("Succés");
////                            dlg.setLayout(new BorderLayout());
////
////                            Label successLabel = new Label("Commentaire modifie avec succés");
////                            successLabel.getStyle().setFgColor(0xff000);
////
////                            dlg.add(BorderLayout.CENTER, successLabel);
////                            Button close = new Button("Fermer");
////                            close.addActionListener((ee) -> dlg.dispose());
////                            dlg.addComponent(BorderLayout.SOUTH, close);
////                            Dimension pre = dlg.getContentPane().getPreferredSize();
////                            dlg.show(110, 120, 0, 0);
////
////                            System.out.println("c not kifkif");
////
////                        }
//
//                        f.refreshTheme();
//
//                        System.out.println("clicked saved button and saved label");
//
//                    }
//                });
//
//                cButtons2.add(btnSave2);
//                cButtons2.add(btnModif2);
//
//                cButtons2.add(btnSupprimer2);
//                cCommentaire2.add(zoneComm2);
//
//                cCommentaire2.add(cButtons2);
//                f.add(cCommentaire2);
//
//            }
            PublicationServices pst = new PublicationServices();

            ContenuePublication contenuepublication = new ContenuePublication();
            System.out.println("avant affichage");
            contenuepublication.getF().show();
            System.out.println("apres affichage");
            contenuepublication.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                f.showBack();

            });

        });
    }

    public void fn2() {

        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

        try {
            ei = EncodedImage.create("/WorldCup.PNG");
            ei1 = EncodedImage.create("/WorldCup.PNG");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//            this.getF().getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_HOTEL, b -> {
//            f.showBack();
//        });
        int idx = ListPublication.id;
        String descx = ListPublication.desc_static;
        String tagsx = ListPublication.tags_static;
        String imagex = ListPublication.image_static;
        System.out.println("id publcation = " + idx);
        System.out.println("desc publcation = " + descx);
        System.out.println("tags publcation = " + tagsx);
        System.out.println("image publcation = " + imagex);
        f = new Form("Contenue Publication", BoxLayout.y());

//         PublicationServices ps = new PublicationServices();
//        ArrayList<Publication> listePublication = ps.afficher();
//         
        // for (Publication item : listePublication) {
        lbDesc = new SpanLabel("Contenue de la publication ");
        lbTags = new SpanLabel("Tags");
        Label ImagetxtPhoto = new Label();

        SpanLabel desc_txt = new SpanLabel();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel lbAjoutCommentaire = new SpanLabel();
        TextField Commtxt = new TextField();
        Commtxt.setHint("Ajouter votre commentaire ici");

        Button AjoutCommentaire = new Button("Commenter");
        c2.add(lbAjoutCommentaire);
        c2.add(Commtxt);
        c2.add(AjoutCommentaire);
        PublicationServices ps = new PublicationServices();
        listeCommentaire = ps.afficherCommentaire();
        // SpanLabel newCom = new SpanLabel("");

        if (descx.equals("")) {
            String imagex2 = ListPublication.image_static;

            System.out.println("image 00" + imagex);
            System.out.println("type image clicked");
            System.err.println("ok -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + imagex);
            System.err.println("ko -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + image_static);
            Image img;
            try {
                String imagex3 = ListPublication.image_static;

                img = URLImage.createToStorage(ei, imagex,
                        imagex + "6e8e28840394ae17f3bf2c6d0a0d317a.jpeg", URLImage.RESIZE_FAIL);

//                img.scale(100, 100);
//
//                img.scaledWidth(20);
                ImagetxtPhoto.setIcon(img);
//                img.scale(100, 100);
                ImagetxtPhoto.setPreferredH(100);
                ImagetxtPhoto.setPreferredW(100);
                c1.add(ImagetxtPhoto);

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage() + " Image vide");
            }
        } else {
            System.out.println("NOT type image clicked");

        }

        SpanLabel tags_txt = new SpanLabel();
        desc_txt.setText(descx);
        tags_txt.setText(tagsx);

        //}
        c1.add(lbDesc);

        c1.add(desc_txt);
        c1.add(lbTags);
        c1.add(tags_txt);
        // c2.add(newCom);

        f.add(c1);
        f.add(c2);

        for (Commentaire item05 : listeCommentaire) {

            /*
            btnModif.setPreferredSize(new Dimension(30, 30));
            btnSupprimer.setPreferredSize(new Dimension(30, 30));
             */
            cCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cButtons = new Container(new BoxLayout(BoxLayout.X_AXIS));
            SpanLabel conCommtxt = new SpanLabel();
            conCommtxt.setText(item05.getContent_comm());

            Button btnModif = new Button();
            Button btnSupprimer = new Button();
            Button btnSave = new Button("save");

            TextField zoneComm = new TextField();
            zoneComm.setText(item05.getContent_comm());
            zoneComm.setPreferredH(50);

            btnModif.setText("Modifier");
            btnSupprimer.setText("Supprimer");
            zoneComm.setVisible(false);

//            
////            btnModif.setVisible(true);
//            
//
//
            btnSupprimer.addActionListener((e) -> {
                idC = item05.getId();
                System.out.println("id c =" + idC);
                PublicationServices ps2 = new PublicationServices();
                Commentaire pub = new Commentaire();
                ps.suppCommentaire(pub);

                InteractionDialog dlg = new InteractionDialog("Succés");
                dlg.setLayout(new BorderLayout());

                Label successLabel = new Label("Commentaire supp");
                successLabel.getStyle().setFgColor(0xff000);

                dlg.add(BorderLayout.CENTER, successLabel);
                Button close = new Button("Fermer");
                close.addActionListener((ee) -> dlg.dispose());
                dlg.addComponent(BorderLayout.SOUTH, close);
                Dimension pre = dlg.getContentPane().getPreferredSize();
                dlg.show(110, 120, 0, 0);
                f.revalidate();
                f.show();
            });

            cCommentaire.add(conCommtxt);

            btnModif.addActionListener((e) -> {
                zoneComm.setVisible(true);
                cCommentaire.removeComponent(conCommtxt);
                conCommtxt.setVisible(false);
                conCommtxt.setEnabled(false);
                cCommentaire.refreshTheme();
                f.refreshTheme();
                System.out.println("clicked modfiied button and hide label");

                //  cCommentaire.add(zoneComm);
//                btnModif.setVisible(false);
//                btnSave.setVisible(true);
//                conCommtxt.setVisible(false);
            });
            btnSave.addActionListener((e) -> {
                idC = item05.getId();

                zoneComm.setVisible(false);
                conCommtxt.setVisible(true);
                if (conCommtxt.getText().equals(zoneComm.getText())) {
                    System.out.println("c kifkif");
                } else {
                    conCommtxt.setText("" + zoneComm.getText());

                    Commentaire com1 = new Commentaire();
                    com1.setContent_comm(zoneComm.getText());
                    PublicationServices psty = new PublicationServices();
                    psty.modifCommentaire(com1);
                    InteractionDialog dlg = new InteractionDialog("Succés");
                    dlg.setLayout(new BorderLayout());

                    Label successLabel = new Label("Commentaire modifie avec succés");
                    successLabel.getStyle().setFgColor(0xff000);

                    dlg.add(BorderLayout.CENTER, successLabel);
                    Button close = new Button("Fermer");
                    close.addActionListener((ee) -> dlg.dispose());
                    dlg.addComponent(BorderLayout.SOUTH, close);
                    Dimension pre = dlg.getContentPane().getPreferredSize();
                    dlg.show(110, 120, 0, 0);

                    System.out.println("c not kifkif");

                }

                f.refreshTheme();

                System.out.println("clicked saved button and saved label");

            });

            int size = Display.getInstance().convertToPixels(6, true);
            Font materialFont = FontImage.getMaterialDesignFont();

            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            btnModif.setIcon(FontImage.create("\uE254", btnModif.getUnselectedStyle(), materialFont));
            btnSupprimer.setIcon(FontImage.create("\uE872", btnSupprimer.getUnselectedStyle(), materialFont));

            //conCommtxt.setPreferredW(100);
            cButtons.add(btnSave);
            cButtons.add(btnModif);
            cButtons.add(btnSupprimer);
            cCommentaire.add(zoneComm);
            cCommentaire.add(cButtons);
            f.add(cCommentaire);

        }
        AjoutCommentaire.addActionListener((e) -> {
            System.out.println("it supposed to be NOT EMPTY:" + listeCommentaire);

            listeCommentaire.clear();
//            f.removeComponent(cCommentaire);
            //f.add(cCommentaire2);

            //f.removeComponent(cCommentaire2);
            System.out.println("it suppsed tp be empty :" + listeCommentaire);

            idP = idx;
            Commentaire com = new Commentaire();
            com.setContent_comm(Commtxt.getText());
            //newCom.setText("new added "+Commtxt.getText());
            ps.ajoutCommentaire(com);
            InteractionDialog dlg = new InteractionDialog("Succés");
            dlg.setLayout(new BorderLayout());

            Label successLabel = new Label("Commentaire ajoute avec succés");
            successLabel.getStyle().setFgColor(0xff000);

            dlg.add(BorderLayout.CENTER, successLabel);
            Button close = new Button("Fermer");
            Commtxt.setText("");

            close.addActionListener((ee) -> dlg.dispose());
            dlg.addComponent(BorderLayout.SOUTH, close);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.show(110, 120, 0, 0);
            PublicationServices ps7 = new PublicationServices();

            listeCommentaire = ps7.afficherCommentaire();

            for (Commentaire item05 : listeCommentaire) {
                cCommentaire2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                cButtons2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                SpanLabel conCommtxt2 = new SpanLabel();

                Button btnModif2 = new Button("Modifier");
                Button btnSupprimer2 = new Button("Supprimer");
                Button btnSave2 = new Button("save");

                TextField zoneComm2 = new TextField();
                zoneComm2.setText(item05.getContent_comm());
                zoneComm2.setPreferredH(50);
                zoneComm2.setVisible(false);

                //zoneComm2.setVisible(false);
                conCommtxt2.setText(item05.getContent_comm());
                cCommentaire2.add(conCommtxt2);

                btnModif2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        zoneComm2.setVisible(true);
                        f.refreshTheme();
//                        zoneComm2.setVisible(true);
//                        cCommentaire2.removeComponent(conCommtxt2);
//                        cCommentaire2.setVisible(false);
//                        cCommentaire2.setEnabled(false);
//                        cCommentaire2.refreshTheme();
//                        
//                        System.out.println("clicked modfiied button and hide label");
//
//                        //  cCommentaire.add(zoneComm);
////                btnModif.setVisible(false);
////                btnSave.setVisible(true);
////                conCommtxt.setVisible(false);
                        System.out.println("modif2");
                    }
                });

                btnSupprimer2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println("ABCD");
                    }
                });
                btnSave2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        idC = item05.getId();
                        System.out.println("save2");
                        zoneComm2.setVisible(false);
                        f.refreshTheme();
//
//                        zoneComm2.setVisible(false);
//                        conCommtxt2.setVisible(true);
//                        if (conCommtxt2.getText().equals(zoneComm2.getText())) {
//                            System.out.println("c kifkif");
//                        } else {
//                            conCommtxt2.setText("" + zoneComm2.getText());
//
//                            Commentaire com2 = new Commentaire();
//                            com2.setContent_comm(zoneComm2.getText());
//                            PublicationServices psty = new PublicationServices();
//                            psty.modifCommentaire(com2);
//                            InteractionDialog dlg = new InteractionDialog("Succés");
//                            dlg.setLayout(new BorderLayout());
//
//                            Label successLabel = new Label("Commentaire modifie avec succés");
//                            successLabel.getStyle().setFgColor(0xff000);
//
//                            dlg.add(BorderLayout.CENTER, successLabel);
//                            Button close = new Button("Fermer");
//                            close.addActionListener((ee) -> dlg.dispose());
//                            dlg.addComponent(BorderLayout.SOUTH, close);
//                            Dimension pre = dlg.getContentPane().getPreferredSize();
//                            dlg.show(110, 120, 0, 0);
//
//                            System.out.println("c not kifkif");
//
//                        }

                        f.refreshTheme();

                        System.out.println("clicked saved button and saved label");

                    }
                });

                cButtons2.add(btnSave2);
                cButtons2.add(btnModif2);

                cButtons2.add(btnSupprimer2);
                cCommentaire2.add(zoneComm2);

                cCommentaire2.add(cButtons2);
                f.add(cCommentaire2);

            }

            ps.afficherCommentaire();

            f.revalidate();
        });
//f.show();
    }

    public void fn3() {

        theme = UIManager.initFirstTheme("/theme");
        Toolbar.setGlobalToolbar(true);

        try {
            ei = EncodedImage.create("/WorldCup.PNG");
            ei1 = EncodedImage.create("/WorldCup.PNG");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//            this.getF().getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_HOTEL, b -> {
//            f.showBack();
//        });
        int idx = ListPublication.id;
        String descx = ListPublication.desc_static;
        String tagsx = ListPublication.tags_static;
        String imagex = ListPublication.image_static;
        System.out.println("id publcation = " + idx);
        System.out.println("desc publcation = " + descx);
        System.out.println("tags publcation = " + tagsx);
        System.out.println("image publcation = " + imagex);
        f = new Form("Contenue Publication", BoxLayout.y());

//         PublicationServices ps = new PublicationServices();
//        ArrayList<Publication> listePublication = ps.afficher();
//         
        // for (Publication item : listePublication) {
        lbDesc = new SpanLabel("new  Load ");
        lbTags = new SpanLabel("Tags");
        Label ImagetxtPhoto = new Label();

        SpanLabel desc_txt = new SpanLabel();
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel lbAjoutCommentaire = new SpanLabel();
        TextField Commtxt = new TextField();
        Commtxt.setHint("Ajouter votre commentaire ici");

        Button AjoutCommentaire = new Button("Commenter");
        c2.add(lbAjoutCommentaire);
        c2.add(Commtxt);
        c2.add(AjoutCommentaire);
        PublicationServices ps = new PublicationServices();
        listeCommentaire = ps.afficherCommentaire();
        // SpanLabel newCom = new SpanLabel("");

        if (descx.equals("")) {
            String imagex2 = ListPublication.image_static;

            System.out.println("image 00" + imagex);
            System.out.println("type image clicked");
            System.err.println("ok -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + imagex);
            System.err.println("ko -- http://localhost:8081/PIDEV-final2/web/uploads/images/" + image_static);
            Image img;
            try {
                String imagex3 = ListPublication.image_static;

                img = URLImage.createToStorage(ei, imagex,
                        imagex + "6e8e28840394ae17f3bf2c6d0a0d317a.jpeg", URLImage.RESIZE_FAIL);

//                img.scale(100, 100);
//
//                img.scaledWidth(20);
                ImagetxtPhoto.setIcon(img);
//                img.scale(100, 100);
                ImagetxtPhoto.setPreferredH(100);
                ImagetxtPhoto.setPreferredW(100);
                c1.add(ImagetxtPhoto);

            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage() + " Image vide");
            }
        } else {
            System.out.println("NOT type image clicked");

        }

        SpanLabel tags_txt = new SpanLabel();
        desc_txt.setText(descx);
        tags_txt.setText(tagsx);

        //}
        c1.add(lbDesc);

        c1.add(desc_txt);
        c1.add(lbTags);
        c1.add(tags_txt);
        // c2.add(newCom);

        f.add(c1);
        f.add(c2);

        for (Commentaire item05 : listeCommentaire) {

            /*
            btnModif.setPreferredSize(new Dimension(30, 30));
            btnSupprimer.setPreferredSize(new Dimension(30, 30));
             */
            cCommentaire = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            cButtons = new Container(new BoxLayout(BoxLayout.X_AXIS));
            SpanLabel conCommtxt = new SpanLabel();
            conCommtxt.setText(item05.getContent_comm());

            Button btnModif = new Button();
            Button btnSupprimer = new Button();
            Button btnSave = new Button("save");

            TextField zoneComm = new TextField();
            zoneComm.setText(item05.getContent_comm());
            zoneComm.setPreferredH(50);

            btnModif.setText("Modifier");
            btnSupprimer.setText("Supprimer");
            zoneComm.setVisible(false);

//            
////            btnModif.setVisible(true);
//            
//
//
            btnSupprimer.addActionListener((e) -> {
                idC = item05.getId();
                System.out.println("id c =" + idC);
                PublicationServices ps2 = new PublicationServices();
                Commentaire pub = new Commentaire();
                ps.suppCommentaire(pub);

                InteractionDialog dlg = new InteractionDialog("Succés");
                dlg.setLayout(new BorderLayout());

                Label successLabel = new Label("Commentaire supp");
                successLabel.getStyle().setFgColor(0xff000);

                dlg.add(BorderLayout.CENTER, successLabel);
                Button close = new Button("Fermer");
                close.addActionListener((ee) -> dlg.dispose());
                dlg.addComponent(BorderLayout.SOUTH, close);
                Dimension pre = dlg.getContentPane().getPreferredSize();
                dlg.show(110, 120, 0, 0);
                f.revalidate();
                f.show();
                PublicationServices pst = new PublicationServices();

                ContenuePublication contenuepublication = new ContenuePublication();

                contenuepublication.getF().show();

                fn3();
            });

            cCommentaire.add(conCommtxt);

            btnModif.addActionListener((e) -> {
                zoneComm.setVisible(true);
                cCommentaire.removeComponent(conCommtxt);
                conCommtxt.setVisible(false);
                conCommtxt.setEnabled(false);
                cCommentaire.refreshTheme();
                f.refreshTheme();
                System.out.println("clicked modfiied button and hide label");

                //  cCommentaire.add(zoneComm);
//                btnModif.setVisible(false);
//                btnSave.setVisible(true);
//                conCommtxt.setVisible(false);
            });
            btnSave.addActionListener((e) -> {
                idC = item05.getId();

                zoneComm.setVisible(false);
                conCommtxt.setVisible(true);
                if (conCommtxt.getText().equals(zoneComm.getText())) {
                    System.out.println("c kifkif");
                } else {
                    conCommtxt.setText("" + zoneComm.getText());

                    Commentaire com1 = new Commentaire();
                    com1.setContent_comm(zoneComm.getText());
                    PublicationServices psty = new PublicationServices();
                    psty.modifCommentaire(com1);
                    InteractionDialog dlg = new InteractionDialog("Succés");
                    dlg.setLayout(new BorderLayout());

                    Label successLabel = new Label("Commentaire modifie avec succés");
                    successLabel.getStyle().setFgColor(0xff000);

                    dlg.add(BorderLayout.CENTER, successLabel);
                    Button close = new Button("Fermer");
                    close.addActionListener((ee) -> dlg.dispose());
                    dlg.addComponent(BorderLayout.SOUTH, close);
                    Dimension pre = dlg.getContentPane().getPreferredSize();
                    dlg.show(110, 120, 0, 0);

                    System.out.println("c not kifkif");

                }

                f.refreshTheme();

                System.out.println("clicked saved button and saved label");

            });

            int size = Display.getInstance().convertToPixels(6, true);
            Font materialFont = FontImage.getMaterialDesignFont();

            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            btnModif.setIcon(FontImage.create("\uE254", btnModif.getUnselectedStyle(), materialFont));
            btnSupprimer.setIcon(FontImage.create("\uE872", btnSupprimer.getUnselectedStyle(), materialFont));

            //conCommtxt.setPreferredW(100);
            cButtons.add(btnSave);
            cButtons.add(btnModif);
            cButtons.add(btnSupprimer);
            cCommentaire.add(zoneComm);
            cCommentaire.add(cButtons);
            f.add(cCommentaire);

        }
        AjoutCommentaire.addActionListener((e) -> {
//            System.out.println("it supposed to be NOT EMPTY:" + listeCommentaire);
//
//            listeCommentaire.clear();
////            f.removeComponent(cCommentaire);
//            //f.add(cCommentaire2);
//
//            //f.removeComponent(cCommentaire2);
//            System.out.println("it suppsed tp be empty :" + listeCommentaire);
//
//            idP = idx;
//            Commentaire com = new Commentaire();
//            com.setContent_comm(Commtxt.getText());
//            //newCom.setText("new added "+Commtxt.getText());
//            ps.ajoutCommentaire(com);
//            InteractionDialog dlg = new InteractionDialog("Succés");
//            dlg.setLayout(new BorderLayout());
//
//            Label successLabel = new Label("Commentaire ajoute avec succés");
//            successLabel.getStyle().setFgColor(0xff000);
//
//            dlg.add(BorderLayout.CENTER, successLabel);
//            Button close = new Button("Fermer");
//            Commtxt.setText("");
//
//            close.addActionListener((ee) -> dlg.dispose());
//            dlg.addComponent(BorderLayout.SOUTH, close);
//            Dimension pre = dlg.getContentPane().getPreferredSize();
//            dlg.show(110, 120, 0, 0);
//            PublicationServices ps7 = new PublicationServices();
//
//            listeCommentaire = ps7.afficherCommentaire();
////
////            for (Commentaire item05 : listeCommentaire) {
////                cCommentaire2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
////                cButtons2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
////                SpanLabel conCommtxt2 = new SpanLabel();
////
////                Button btnModif2 = new Button("Modifier");
////                Button btnSupprimer2 = new Button("Supprimer");
////                Button btnSave2 = new Button("save");
////
////                TextField zoneComm2 = new TextField();
////                zoneComm2.setText(item05.getContent_comm());
////                zoneComm2.setPreferredH(50);
////                zoneComm2.setVisible(false);
////
////                //zoneComm2.setVisible(false);
////                conCommtxt2.setText(item05.getContent_comm());
////                cCommentaire2.add(conCommtxt2);
////
////                btnModif2.addActionListener(new ActionListener() {
////
////                    @Override
////                    public void actionPerformed(ActionEvent evt) {
////
////                        zoneComm2.setVisible(true);
////                        f.refreshTheme();
//////                        zoneComm2.setVisible(true);
//////                        cCommentaire2.removeComponent(conCommtxt2);
//////                        cCommentaire2.setVisible(false);
//////                        cCommentaire2.setEnabled(false);
//////                        cCommentaire2.refreshTheme();
//////                        
//////                        System.out.println("clicked modfiied button and hide label");
//////
//////                        //  cCommentaire.add(zoneComm);
////////                btnModif.setVisible(false);
////////                btnSave.setVisible(true);
////////                conCommtxt.setVisible(false);
////                        System.out.println("modif2");
////                    }
////                });
////
////                btnSupprimer2.addActionListener(new ActionListener() {
////
////                    @Override
////                    public void actionPerformed(ActionEvent evt) {
////                        System.out.println("ABCD");
////                    }
////                });
////                btnSave2.addActionListener(new ActionListener() {
////
////                    @Override
////                    public void actionPerformed(ActionEvent evt) {
////                        idC = item05.getId();
////                        System.out.println("save2");
////                        zoneComm2.setVisible(false);
////                        f.refreshTheme();
//////
//////                        zoneComm2.setVisible(false);
//////                        conCommtxt2.setVisible(true);
//////                        if (conCommtxt2.getText().equals(zoneComm2.getText())) {
//////                            System.out.println("c kifkif");
//////                        } else {
//////                            conCommtxt2.setText("" + zoneComm2.getText());
//////
//////                            Commentaire com2 = new Commentaire();
//////                            com2.setContent_comm(zoneComm2.getText());
//////                            PublicationServices psty = new PublicationServices();
//////                            psty.modifCommentaire(com2);
//////                            InteractionDialog dlg = new InteractionDialog("Succés");
//////                            dlg.setLayout(new BorderLayout());
//////
//////                            Label successLabel = new Label("Commentaire modifie avec succés");
//////                            successLabel.getStyle().setFgColor(0xff000);
//////
//////                            dlg.add(BorderLayout.CENTER, successLabel);
//////                            Button close = new Button("Fermer");
//////                            close.addActionListener((ee) -> dlg.dispose());
//////                            dlg.addComponent(BorderLayout.SOUTH, close);
//////                            Dimension pre = dlg.getContentPane().getPreferredSize();
//////                            dlg.show(110, 120, 0, 0);
//////
//////                            System.out.println("c not kifkif");
//////
//////                        }
////
////                        f.refreshTheme();
////
////                        System.out.println("clicked saved button and saved label");
////
////                    }
////                });
////
////                cButtons2.add(btnSave2);
////                cButtons2.add(btnModif2);
////
////                cButtons2.add(btnSupprimer2);
////                cCommentaire2.add(zoneComm2);
////
////                cCommentaire2.add(cButtons2);
////                f.add(cCommentaire2);
////
////            }

            PublicationServices pst = new PublicationServices();

            ContenuePublication contenuepublication = new ContenuePublication();
            System.out.println("avant affichage");
            contenuepublication.getF().show();
            System.out.println("apres affichage");

        });
//f.show();
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
