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
import edu.pidev.entities.Commentaire;
import edu.pidev.entities.Evenement;
import edu.pidev.entities.Publication;
import edu.pidev.gui.spotted.ContenuePublication;
import edu.pidev.gui.spotted.ListPublication;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static edu.pidev.gui.spotted.ListPublication.id;
import static edu.pidev.gui.spotted.ContenuePublication.idP;
import static edu.pidev.gui.spotted.ContenuePublication.idC;
import java.util.Date;

/**
 *
 * @author Amine
 */
public class PublicationServices {

    public void ajoutPublication(Publication p) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/ajout_publication?tags=" + p.getTags() + "&desc_p=" + p.getDesc_p();
        con.setUrl(Url);
        System.out.println("Ajout test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    public void ajoutCommentaire(Commentaire c) {
        int idPub = ContenuePublication.idP;
        System.out.println("ID Publication de commentaire = " + idPub);
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/ajoutcommentaire/" + idPub);
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/ajoutcommentaire/" + idPub + "?content_comm=" + c.getContent_comm();
        con.setUrl(Url);
        System.out.println("Ajout test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    public void modifPublication(Publication p) {
        int idx = ListPublication.id;

        ConnectionRequest con = new ConnectionRequest();
        System.err.println("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modif_publication/" + idx + "?desc_p=" + p.getDesc_p() + "&tags=" + p.getTags());
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modif_publication/" + idx + "?desc_p=" + p.getDesc_p() + "&tags=" + p.getTags();
        con.setUrl(Url);
        System.out.println("Modif test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

        public void modifCommentaire(Commentaire c) {
        int idx = ContenuePublication.idC;

        ConnectionRequest con = new ConnectionRequest();
        System.err.println("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modifCommentaire/" + idx + "?content_comm="+c.getContent_comm()+"");
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modifCommentaire/" + idx + "?content_comm="+c.getContent_comm();
        con.setUrl(Url);
        System.out.println("Modif test");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }
        
    public void testPublication(Publication p) {
        int idx = ListPublication.id;

        System.out.println("id pub = " + idx);
        System.out.println("-----------------Supression Publication-----------------");
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/suppressionPublication/" + idx;
        con.setUrl(Url);
        System.out.println("----> Supression effectué + test <----");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

        public void suppCommentaire(Commentaire p) {
        int idx = ContenuePublication.idC;
            System.out.println("id con supp = "+idx);
        System.out.println("id Commen = " + idx);
        System.out.println("-----------------Draft-----------------");
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/suppCommentaire/" + idx;
        con.setUrl(Url);
        System.out.println("----> Supp test + test <----");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

        
    public void suppPublication(Publication p) {
        int idx = ListPublication.id;

        System.out.println("id pub = " + idx);
        System.out.println("-----------------Draft-----------------");
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/supp_publication/" + idx;
        con.setUrl(Url);
        System.out.println("----> Supp test + test <----");
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);//appel asynchrone "success" envoi de la requette et wait 
    }

    public ArrayList<Commentaire> afficherCommentaire() {
        int idPub = ListPublication.id;
        System.out.println("id de la publication = "+idPub);
        ArrayList<Commentaire> listPubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        System.err.println("url comm = http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/afficherCommentaire/" + idPub);
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/afficherCommentaire/" + idPub);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) pubs.get("root");
                    for (Map<String, Object> obj : list) {
                        Commentaire pub = new Commentaire();
                        float id = Float.parseFloat(obj.get("id").toString());

//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());
                        try {
                            float idUser = Float.parseFloat(obj.get("idUser").toString());
                            float idPublication = Float.parseFloat(obj.get("idPublication").toString());

                            pub.setId((int) id);
                            pub.setId_user((int) idUser);
                            pub.setId_publication((int) idPublication);
                            pub.setContent_comm(obj.get("contentComm").toString());
                            System.out.println("liste des commentaires = "+pub);
                            listPubs.add(pub);
                            System.err.println("list pubs = " + pubs);

                        } catch (NullPointerException nl) {
                            System.out.println(nl.getMessage() + " = > null pointer vide");

                        }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs;
    }

    public ArrayList<Publication> afficher() {
        ArrayList<Publication> listPubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/affiche_publication2");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) pubs.get("root");
                    for (Map<String, Object> obj : list) {
                        Publication pub = new Publication();
                        float id = Float.parseFloat(obj.get("id").toString());

//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());
                        try {
                            float idUser = Float.parseFloat(obj.get("idUser").toString());

                            pub.setTags(obj.get("tags").toString());

                            pub.setDesc_p(obj.get("descP").toString());
                            pub.setId((int) id);
                            // pub.setDate_p(new Date(obj.get("dateP").toString()));

                            pub.setUser_id((int) idUser);
                            //pub.setDate_modif(new Date(obj.get("dateModif").toString()));
                            pub.setImage(obj.get("image").toString());
                            System.out.println("tags= " + obj.get("tags").toString());
                            listPubs.add(pub);
                            System.err.println("list pubs = " + pubs);

                        } catch (NullPointerException nl) {
                            System.out.println(nl.getMessage() + " = > null pointer vide");

                        }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs;
    }

    public ArrayList<Publication> afficher2() {
        int idx = ListPublication.id;

        System.out.println("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modif_publication/" + idx);
        ArrayList<Publication> listPubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/modif_publication/" + idx);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) pubs.get("root");
                    System.out.println("liste contient : " + list);
                    for (Map<String, Object> obj : list) {
                        Publication pub = new Publication();
                        float id = Float.parseFloat(obj.get("id").toString());

                        pub.setId((int) id);
//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs;
    }

    public ArrayList<Publication> afficher_texto() {
        ArrayList<Publication> listPubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/affiche_publication2_texto");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) pubs.get("root");
                    for (Map<String, Object> obj : list) {
                        Publication pub = new Publication();
                        float id = Float.parseFloat(obj.get("id").toString());

//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());
                        try {
                            float idUser = Float.parseFloat(obj.get("idUser").toString());
                            pub.setImage(obj.get("image").toString());

                            pub.setTags(obj.get("tags").toString());

                            pub.setDesc_p(obj.get("descP").toString());
                            pub.setId((int) id);
                            // pub.setDate_p(new Date(obj.get("dateP").toString()));

                            pub.setUser_id((int) idUser);
                            //pub.setDate_modif(new Date(obj.get("dateModif").toString()));

                            System.out.println("tags= " + obj.get("tags").toString());
                            listPubs.add(pub);
                            System.err.println("list pubs = " + pubs);

                        } catch (NullPointerException nl) {
                            System.out.println(nl.getMessage() + " = > null pointer vide");

                        }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs;
    }

    public ArrayList<Publication> afficher_image() {
        ArrayList<Publication> listPubs = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/affiche_publication2T");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) pubs.get("root");
                    for (Map<String, Object> obj : list) {
                        Publication pub = new Publication();
                        float id = Float.parseFloat(obj.get("id").toString());

//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());
                        try {
                            float idUser = Float.parseFloat(obj.get("idUser").toString());
                            pub.setImage(obj.get("image").toString());

                            pub.setTags(obj.get("tags").toString());

                            pub.setDesc_p(obj.get("descP").toString());
                            pub.setId((int) id);
                            // pub.setDate_p(new Date(obj.get("dateP").toString()));

                            pub.setUser_id((int) idUser);
                            //pub.setDate_modif(new Date(obj.get("dateModif").toString()));

                            System.out.println("tags= " + obj.get("tags").toString());
                            listPubs.add(pub);
                            System.err.println("list pubs = " + pubs);

                        } catch (NullPointerException nl) {
                            System.out.println(nl.getMessage() + " = > null pointer vide");

                        }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs;
    }

    public ArrayList<Publication> afficherById(int id) {
        int idx = ListPublication.id;

        ArrayList<Publication> listPubs2 = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        System.out.println("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/find_publication/" + id);
        con.setUrl("http://localhost:8081/PIDEV-final2/web/app_dev.php/mobile/find_publication/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> pubs = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list_sm = (List<Map<String, Object>>) pubs.get("root");
                    System.out.println("vori la liste" + list_sm);
                    for (Map<String, Object> obj : list_sm) {
                        Publication pub = new Publication();
                        float id = Float.parseFloat(obj.get("id").toString());

//                        System.out.println(obj.get("desc_p").toString());
                        //pub.setDesc_p(obj.get("desc_p").toString());
                        try {
                            float idUser = Float.parseFloat(obj.get("idUser").toString());

                            pub.setTags(obj.get("tags").toString());

                            pub.setDesc_p(obj.get("descP").toString());
                            pub.setId((int) id);
                            // pub.setDate_p(new Date(obj.get("dateP").toString()));

                            pub.setUser_id((int) idUser);
                            //pub.setDate_modif(new Date(obj.get("dateModif").toString()));
                            pub.setImage(obj.get("image").toString());
                            System.out.println("tags= " + obj.get("tags").toString());
                            listPubs2.add(pub);
                            System.err.println("list pubs = " + pubs);

                        } catch (NullPointerException nl) {
                            System.out.println(nl.getMessage() + " = > null pointer vide");

                        }

                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPubs2;
    }

}
