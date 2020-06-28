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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.MyApplication;
import edu.pidev.entities.Publication;
import static edu.pidev.gui.spotted.ListPublication.desc_static;
import static edu.pidev.gui.spotted.ListPublication.id;
import static edu.pidev.gui.spotted.ListPublication.tags_static;
import edu.pidev.services.PublicationServices;

/**
 *
 * @author Seif BelHadjAli
 */
public class ModifPublication {

    Form f;
    SpanLabel lbDesc;
    SpanLabel lbTags;
    Button btnModif;
    TextArea desc_p;
    TextField tags;
    private Resources theme;

    public ModifPublication() {
        theme = UIManager.initFirstTheme("/theme");

//            this.getF().getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_HOTEL, b -> {
//            f.showBack();
//        });
        int idx = ListPublication.id;
        String descx = ListPublication.desc_static;
        String tagsx = ListPublication.tags_static;

        System.out.println("id publcation = " + idx);
        System.out.println("desc publcation = " + descx);
        System.out.println("tags publcation = " + tagsx);
        f = new Form("Modifier Publication", BoxLayout.y());

//         PublicationServices ps = new PublicationServices();
//        ArrayList<Publication> listePublication = ps.afficher();
//         
        // for (Publication item : listePublication) {
        lbDesc = new SpanLabel("Contenue de la publication du modification");
        lbTags = new SpanLabel("Tags");
        desc_p = new TextArea();
        desc_p.getStyle().setFgColor(0xff000);
        desc_p.setText(descx);
        tags = new TextField();
        tags.setText(tagsx);
        btnModif = new Button("Modifier", "GreenButton");

        int size = Display.getInstance().convertToPixels(6, true);
        Font materialFont = FontImage.getMaterialDesignFont();

        materialFont = materialFont.derive(size, Font.STYLE_PLAIN);

        btnModif.setIcon(FontImage.create("\uE254", btnModif.getUnselectedStyle(), materialFont));

        btnModif.addActionListener((e) -> {
            PublicationServices ps = new PublicationServices();
            Publication pub = new Publication();
            pub.setDesc_p(desc_p.getText());
            pub.setTags(tags.getText());
            ps.modifPublication(pub);

            InteractionDialog dlg = new InteractionDialog("Succés");
            dlg.setLayout(new BorderLayout());

            Label successLabel = new Label("Publication Modifie avec succés");
            successLabel.getStyle().setFgColor(0xff000);

            dlg.add(BorderLayout.CENTER, successLabel);
            Button close = new Button("Fermer");
            close.addActionListener((ee) -> dlg.dispose());
            dlg.addComponent(BorderLayout.SOUTH, close);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.show(110, 120, 0, 0);
            PublicationServices pst = new PublicationServices();

            ListPublication listpublication = new ListPublication();
            pst.afficher();

//            listpublication.getF().getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_HOTEL, b -> {
//                f.showBack();
//
//            });
            listpublication.getF().show();
            listpublication.getF().getToolbar().addCommandToLeftBar("retour", theme.getImage("back-command.png"), b -> {
                f.showBack();

            });
        });
        //}
        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c1.add(lbDesc);
        c1.add(desc_p);
        c1.add(lbTags);
        c1.add(tags);
        c1.add(btnModif);
        f.add(c1);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
