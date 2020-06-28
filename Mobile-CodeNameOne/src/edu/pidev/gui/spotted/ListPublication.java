/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.spotted;

import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import edu.pidev.entities.Publication;
import edu.pidev.services.PublicationServices;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Seif BelHadjAli
 */
public class ListPublication {

    public static int id;
    public static String desc_static, tags_static, image_static;

    Form f, hi2, hi3;
    SpanLabel lb;

    private EncodedImage ei;
    private EncodedImage ei1;
    private Resources theme;

    public ListPublication() {
        f = new Form();
        Form hi2 = new Form("Pub Texo", BoxLayout.y());
        Form hi3 = new Form("Pub Photo", BoxLayout.y());

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        hi2.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
            f.showBack();

        });
        hi3.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
            f.showBack();

        });

        try {
            ei = EncodedImage.create("/WorldCup.PNG");
            ei1 = EncodedImage.create("/WorldCup.PNG");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        lb = new SpanLabel("");
        f.add(lb);
        PublicationServices ps = new PublicationServices();
        ArrayList<Publication> listePublication = ps.afficher();
        ArrayList<Publication> liste_texto = ps.afficher_image();
        ArrayList<Publication> liste_image = ps.afficher_texto();

        ComboBox cb1 = new ComboBox();
        cb1.addItem("Filter");
        cb1.addItem("Filter By Publication Texto");
        cb1.addItem("Filter By Publication Photo");
        f.add(cb1);

        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if (cb1.getSelectedItem().equals("Filter By Publication Texto")) {
                    hi2.removeAll();

                    Label st = new Label("Liste publication texto");
                    hi2.add(st);

                    for (Publication item_text : liste_texto) {
                        Label myLabelIdText = new Label();
                        Label myLabelDescText = new Label();
                        Label myLabelTagsText = new Label();

                        Button myLabelButtonTest_text = new Button();
                        Button myLabelButton_text = new Button();
                        Button myLabelButton2_text = new Button();
                        Container c_text = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        myLabelIdText.setText("ID = " + item_text.getId());
                        myLabelDescText.setText("" + item_text.getDesc_p());
                        myLabelTagsText.setText("#" + item_text.getTags());
                        myLabelTagsText.getStyle().setFgColor(0xff000);
                        c_text.add(myLabelIdText);
                        c_text.add(myLabelDescText);
                        c_text.add(myLabelTagsText);

                        hi2.add(c_text);

                    }

                    hi2.show();
                }

                if (cb1.getSelectedItem().equals("Filter By Publication Photo")) {
                    hi3.removeAll();
                    Label st2 = new Label("Liste publication photo");
                    for (Publication item_photo : liste_image) {
                        Label myLabelIdPhoto = new Label();
                        Label myLabelDescPhoto = new Label();
                        Label myLabelTagsPhoto = new Label();

                        Button myLabelButtonTest_text = new Button();
                        Button myLabelButton_text = new Button();
                        Button myLabelButton2_text = new Button();
                        Label ImagetxtPhoto = new Label();

                        Container c_photo = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                        Image img;
                        try {

                            img = URLImage.createToStorage(ei, item_photo.getImage(),
                                    "http://localhost:8081/PIDEV-final2/web/uploads/images/" + item_photo.getImage(), URLImage.RESIZE_FAIL);

//                img.scale(100, 100);
//
//                img.scaledWidth(20);
                            ImagetxtPhoto.setIcon(img);
//                img.scale(100, 100);
                            ImagetxtPhoto.setPreferredH(100);
                            ImagetxtPhoto.setPreferredW(100);
                            c_photo.add(ImagetxtPhoto);

                        } catch (NullPointerException ex) {
                            System.out.println(ex.getMessage() + " Image vide");
                        }

                        myLabelIdPhoto.setText("ID = " + item_photo.getId());
                        myLabelDescPhoto.setText("" + item_photo.getDesc_p());
                        myLabelTagsPhoto.setText("#" + item_photo.getTags());
                        myLabelTagsPhoto.getStyle().setFgColor(0xff000);
                        c_photo.add(myLabelIdPhoto);
                        c_photo.add(myLabelDescPhoto);
                        c_photo.add(myLabelTagsPhoto);
                        PublicationServices ps = new PublicationServices();
                        ps.afficher_image();
                        hi3.add(c_photo);

                    }

                    hi3.add(st2);

                    hi3.show();
                }

            }
        });
        for (Publication item : listePublication) {
//            Container cnDiv = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//            
            Container cnPub = new Container(new BoxLayout(BoxLayout.X_AXIS));

            System.err.println("ID = " + item.getId());
            System.err.println("ID_User = " + item.getUser_id());
            System.err.println("desc = " + item.getDesc_p());
            System.err.println("Date Ajout = " + item.getDate_p());
            System.err.println("Date MODIF = " + item.getDate_modif());
            System.err.println("Image = " + item.getImage());
            System.err.println("Tags  = " + item.getTags());
            Label myLabelId = new Label();
            Label myLabelDesc = new Label();
            Label myLabelTags = new Label();

            Button myLabelButtonTest = new Button("", "RedButton");
            Button myLabelButton = new Button("", "GreenButton");
            Button myLabelButton2 = new Button("", "OpenButton");
            Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label Imagetxt = new Label();
            myLabelId.setText("ID = " + item.getId());
            myLabelDesc.setText("" + item.getDesc_p());
            myLabelTags.setText("#" + item.getTags());
            myLabelTags.getStyle().setFgColor(0xff000);

//            Label myLabelDesc = new Label();
//            Label myLbaleImage = new Label();
//            Button myLabelButton = new Button();
//            Button myLabelButton2 = new Button();
//            Container c = new Container(k);
//            myLabelDesc.setText("" + item.getDesc_p());
//            //myLbaleImage.setText(""+item.getImage());
//            Label Imagetxt = new Label();
            myLabelButtonTest.setText("");
            Font materialFont = FontImage.getMaterialDesignFont();
            int size = Display.getInstance().convertToPixels(6, true);
            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            myLabelButtonTest.setIcon(FontImage.create("\uE872", myLabelButtonTest.getUnselectedStyle(), materialFont));

            myLabelButtonTest.addActionListener((e) -> {
                id = item.getId();
                PublicationServices ps2 = new PublicationServices();
                Publication pub = new Publication();
                ps.testPublication(pub);

                InteractionDialog dlg = new InteractionDialog("Succés");
                dlg.setLayout(new BorderLayout());

                Label successLabel = new Label("Publication avec l ID : " + item.getId() + "a ete supprimé avec succes");
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

                ListPublication listpubli = new ListPublication();
                System.out.println("avant affichage");
                listpubli.getF().show();
                listpubli.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                    f.showBack();

                });

            });

            myLabelButton.setText("");

            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            myLabelButton.setIcon(FontImage.create("\uE254", myLabelButton.getUnselectedStyle(), materialFont));

            //myLabelButton.getStyle().setBgColor(0xff000);
            myLabelButton.addActionListener((e) -> {
                id = item.getId();
                desc_static = item.getDesc_p();
                tags_static = item.getTags();
                ModifPublication md = new ModifPublication();
                System.out.println("avant click modifi");

                md.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                    f.show();

                });

                md.getF().show();
//                 ListPublication listpublication = new ListPublication();
//             listpublication.getF().show();
                System.out.println("aprés click modifi");
                //f.showBack();

            });
            myLabelButton.setPreferredSize(new Dimension(100, 50));
            myLabelButton2.setPreferredSize(new Dimension(100, 50));
            myLabelButtonTest.setPreferredSize(new Dimension(100, 50));

            myLabelButton2.setText("");

            materialFont = materialFont.derive(size, Font.STYLE_PLAIN);
            myLabelButton2.setIcon(FontImage.create("\uE163", myLabelButton2.getUnselectedStyle(), materialFont));

            myLabelButton2.addActionListener((e) -> {
                id = item.getId();
                desc_static = item.getDesc_p();
                tags_static = item.getTags();
                image_static = item.getImage();

                System.out.println("this is the id clicked ! " + item.getId());
                System.out.println("Virtual ID = " + item.getId());
                int id = item.getId();
                System.out.println("original ID = " + id);
                //             ContenuePublication cps2 = new ContenuePublication();
//
//                PublicationServices ps2 = new PublicationServices();
//                Publication pub = new Publication();
//                ps2.afficherById();

                ContenuePublication md2 = new ContenuePublication();
                System.out.println("avant click ouvrir");

                md2.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                    f.show();

                });

                md2.getF().show();

            });
            lb = new SpanLabel("");
            System.out.println("image = " + item.getImage());
            Label TypeP = new Label();
            TypeP.setText("T = v");

            if (item.getDesc_p().equals("")) {

                TypeP.setText("T = image");
                Image img;
                try {

                    img = URLImage.createToStorage(ei, item.getImage(),
                            "http://localhost:8081/PIDEV-final2/web/uploads/images/" + item.getImage(), URLImage.RESIZE_FAIL);

//                img.scale(100, 100);
//
//                img.scaledWidth(20);
                    Imagetxt.setIcon(img);
//                img.scale(100, 100);
                    Imagetxt.setPreferredH(100);
                    Imagetxt.setPreferredW(100);
                    c.add(Imagetxt);

                } catch (NullPointerException ex) {
                    System.out.println(ex.getMessage() + " Image vide");
                }

            } else {
                TypeP.setText("T = publi");
                c.add(myLabelDesc);

            }

            f.revalidate();

            c.add(myLabelTags);
            cnPub.add(myLabelButtonTest);
            cnPub.add(myLabelButton);
            cnPub.add(myLabelButton2);
            c.add(lb);
            c.add(cnPub);
            f.add(c);

        }
        f.show();

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
