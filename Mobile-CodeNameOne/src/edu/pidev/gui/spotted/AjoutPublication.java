/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.spotted;

import com.codename1.components.InteractionDialog;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import edu.pidev.entities.Publication;
import edu.pidev.services.PublicationServices;

/**
 *
 * @author Seif BelHadjAli
 */
public class AjoutPublication {

    Form f;
    SpanLabel lbDesc;
    SpanLabel lbTags;
    Button btnAjout;
    TextArea desc_p;
    TextField tags;
    private Resources theme;

    public AjoutPublication() {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        f = new Form("Ajout Publication", BoxLayout.y());
        Form f2 = new Form("Ajout Publication Texto", BoxLayout.y());
        Form f3 = new Form("Ajout Publication Photo", BoxLayout.y());

        Container c0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c0.setVisible(true);
        c1.setVisible(false);
        c2.setVisible(false);
        Label txtP1 = new Label("Ceci est pour l ajout dune publication");
        Label txtP2 = new Label("Ceci est pour l ajout dune Photo");
        Button btn1 = new Button("Publication");
        Button btn2 = new Button("Photo");

        lbDesc = new SpanLabel("Contenue de la publication");
        SpanLabel lbImage = new SpanLabel("importer une image");
        lbTags = new SpanLabel("Tags");
        SpanLabel lbTags2 = new SpanLabel("Tags");

        desc_p = new TextArea("");
        desc_p.getStyle().setBorder(Border.createLineBorder(1, 1));
        desc_p.getStyle().setFgColor(0xff000);
        tags = new TextField("");
        TextField tags2 = new TextField();
        btnAjout = new Button("Ajout Publication", "SaveButton");
        Font materialFont = FontImage.getMaterialDesignFont();
        int size = Display.getInstance().convertToPixels(6, true);
        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnAjout.setIcon(FontImage.create("\uE161", btnAjout.getUnselectedStyle(), materialFont));
        Button btnUploadImage = new Button("upload");
        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnUploadImage.setIcon(FontImage.create("\uE161", btnUploadImage.getUnselectedStyle(), materialFont));

        Button btnAjout2 = new Button("Ajout Photo", "SaveButton");

        Validator val = new Validator();

        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnAjout2.setIcon(FontImage.create("\uE161", btnAjout2.getUnselectedStyle(), materialFont));

        txtP1.setVisible(true);
        txtP2.setVisible(true);
        btn1.setVisible(true);
        btn2.setVisible(true);
        f2.add(txtP1);
        f3.add(txtP2);
        f2.add(lbDesc);
        desc_p.setHint("Ecirire ici...");

        tags.setHint("tags ici...");
        f2.add(desc_p);
        f2.add(lbTags);
        f2.add(tags);
        f2.add(btnAjout);

        f3.add(lbImage);
        f3.add(btnUploadImage);
        f3.add(lbTags2);
        tags2.setHint("tags ici...");

        f3.add(tags2);
        f3.add(btnAjout2);

        btnAjout.addActionListener((e) -> {
            System.out.println("you have clicked here !");
            //System.out.println("ok = ");
            boolean ok1 = false;
            boolean ok2 = false;
                   Boolean test1;
        test1 = true;
        
            System.out.println("initialiesed ok true");
            f.refreshTheme();
            if (desc_p.getText().matches("") || desc_p.getText().matches(".*\\d+.*")) {
                val.addConstraint(desc_p, new LengthConstraint(2));
                 test1 = false;
                 
                ok1 = false;
                val.addConstraint(desc_p, new LengthConstraint(2));
                f.refreshTheme();
                System.out.println("ok false");


            }
            else{
                ok1=true;
                System.out.println("ok true");
            }
            if (tags.getText().equals("")) {
//                val.addConstraint(tags, new LengthConstraint(2));
//                ok2 = false;
//                                val.addConstraint(tags, new LengthConstraint(2));
//                f.refreshTheme();
                    System.out.println("ok2 false");

            }
            else{
                System.out.println("ok2 true");
//                ok2=true;
//                f.refreshTheme();
            }
            if (ok1 && ok2 ) {
                System.out.println("all vrai");
//                f.refreshTheme();
//                PublicationServices ps = new PublicationServices();
//                Publication pub = new Publication();
//                pub.setDesc_p(desc_p.getText());
//                pub.setTags(tags.getText());
//                ps.ajoutPublication(pub);
//
//                InteractionDialog dlg = new InteractionDialog("Succés");
//                dlg.setLayout(new BorderLayout());
//
//                Label successLabel = new Label("Publication ajoute avec succés");
//                successLabel.getStyle().setFgColor(0xff000);
//
//                dlg.add(BorderLayout.CENTER, successLabel);
//                Button close = new Button("Fermer");
//                desc_p.setText("");
//                tags.setText("");
//                close.addActionListener((ee) -> dlg.dispose());
//                dlg.addComponent(BorderLayout.SOUTH, close);
//                Dimension pre = dlg.getContentPane().getPreferredSize();
//                dlg.show(110, 120, 0, 0);
//
//                PublicationServices pst = new PublicationServices();
//
//                pst.afficher();
//
//                ListPublication listpublication = new ListPublication();
//
//                listpublication.getF().show();
//                listpublication.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
//                    f.showBack();
//
//                });
//                f.refreshTheme();
            }
f.refreshTheme();
        });

        btn1.addActionListener((e) -> {
            f2.show();

        });

        btn2.addActionListener((e) -> {
            f3.show();

        });

        f2.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
            f.showBack();

        });

        f3.getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
            f.showBack();

        });

        f.add(btn1);
        //f.add(b0x);
        f.add(btn2);
        f.show();

        Button btnAjoutPublication = new Button("Ajout Publication");

        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnAjoutPublication.setIcon(FontImage.create("\uE161", btnAjoutPublication.getUnselectedStyle(), materialFont));

        Button btnAjoutPhoto = new Button("Ajout Photo");

        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnAjoutPhoto.setIcon(FontImage.create("\uE161", btnAjoutPhoto.getUnselectedStyle(), materialFont));

        Button btnRetour = new Button("Retour");

        c0.add(btnAjoutPublication);
        c0.add(btnAjoutPhoto);
        c1.add(btnRetour);
//        c2.add(btnRetour);

        btnAjoutPublication.addActionListener((e) -> {
            System.out.println("Begin Ajout PUBLICATION");
            c0.setVisible(false);
            c1.setVisible(true);
            //btnRetour.setVisible(true);
            System.out.println("End Ajout PUBLICATION");

        });

        btnAjoutPhoto.addActionListener((e) -> {
            System.out.println("Begin Ajout Photo Publication");

            c0.setVisible(false);
            c2.setVisible(true);
            System.out.println("End Ajout Photo Publication");

        });
//
//        btnRetour.addActionListener((e) -> {
//            System.out.println("Begin Retour Publication");
//
//            c0.setVisible(true);
//            c1.setVisible(false);
//            c2.setVisible(false);
//            System.out.println("End Retour Publication");
//
//        });
//
//        lbDesc = new SpanLabel("Contenue de la publication");
//        lbTags = new SpanLabel("Tags");
//
//        desc_p = new TextArea();
//        desc_p.getStyle().setBorder(Border.createLineBorder(1, 1));
//        desc_p.getStyle().setFgColor(0xff000);
//        tags = new TextField();
//        btnAjout = new Button("Ajout Publication");
//
//        c1.add(lbDesc);
//        c1.add(desc_p);
//        c1.add(lbTags);
//        c1.add(tags);
//        c1.add(btnAjout);
//        f.add(c0);
//        f.add(c1);
//        f.add(c2);
//
//        btnAjout.addActionListener((e) -> {
//            PublicationServices ps = new PublicationServices();
//            Publication pub = new Publication();
//            pub.setDesc_p(desc_p.getText());
//            pub.setTags(tags.getText());
//            ps.ajoutPublication(pub);
//
//            InteractionDialog dlg = new InteractionDialog("Succés");
//            dlg.setLayout(new BorderLayout());
//
//            Label successLabel = new Label("Publication ajoute avec succés");
//            successLabel.getStyle().setFgColor(0xff000);
//
//            dlg.add(BorderLayout.CENTER, successLabel);
//            Button close = new Button("Fermer");
//            close.addActionListener((ee) -> dlg.dispose());
//            dlg.addComponent(BorderLayout.SOUTH, close);
//            Dimension pre = dlg.getContentPane().getPreferredSize();
//            dlg.show(110, 120, 0, 0);
//            ListPublication listpublication = new ListPublication();
//            listpublication.getF().show();
//
//        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
