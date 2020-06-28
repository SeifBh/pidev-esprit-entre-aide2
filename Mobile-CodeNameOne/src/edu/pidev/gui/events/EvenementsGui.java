/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui.events;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import edu.pidev.entities.Evenement;
import edu.pidev.services.EvenementServices;
import java.util.ArrayList;

/**
 *
 * @author Amine
 */
public class EvenementsGui {
    Form f;
    SpanLabel lb;
  
    public EvenementsGui() {
        
        f = new Form("Evenements", BoxLayout.y());
        
        lb = new SpanLabel("");
        f.add(lb);
        EvenementServices service=new EvenementServices();
        ArrayList<Evenement> lis=service.afficher();
        for(Evenement e : lis)
        {
            Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));
            SpanLabel sl1 = new SpanLabel(e.getTitre_e());
            
            SpanLabel sl2 = new SpanLabel(e.getDesc_e());
            c.add(sl1);
            c.add(sl2);
            f.add(c);
        }
        
         /* f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });*/
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
