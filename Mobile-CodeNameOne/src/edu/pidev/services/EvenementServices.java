/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import edu.pidev.entities.Evenement;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine
 */
public class EvenementServices {
    public ArrayList<Evenement> afficher() {
        ArrayList<Evenement> listEvents = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PIDEV-final/web/app_dev.php/Events_a/affichermobile");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> events = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                   List<Map<String, Object>> list = (List<Map<String, Object>>) events.get("root");
                    for (Map<String, Object> obj : list) {
                        Evenement event = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        event.setId((int) id);
                        event.setTitre_e(obj.get("titre_e").toString());
                        event.setDesc_e(obj.get("desc_e").toString());
                       /* DateTimeSpinner dt = new DateTimeSpinner();
                        dt.setCurrentDate(Date.valueOf(s));
                        event.setDate_e((obj.get("date_e").toString()) );*/
                        event.setType_e(obj.get("type_e").toString());
                        event.setImage(obj.get("image").toString());
                        event.setGooglemaps(obj.get("googlemaps").toString());
                        listEvents.add(event);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
    
}
