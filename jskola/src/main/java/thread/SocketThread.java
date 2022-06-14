package thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Session;

import model.Objednavka;
import model.Objednavky;

public class SocketThread extends Thread {

    private Session session;
    private List<Objednavky> currentList;
    private Objednavka objednavka;
    private int currentIndex;
    private int count;
    private String text;

    public SocketThread(Session session) {
        this.session = session;
        currentList = new ArrayList<Objednavky>();
        objednavka = new Objednavka();
        currentIndex = 0;
        text = "";
    }

    @Override
    public void run() {
        while (true) {
            List<Objednavky> list = null;

            list = objednavka.setAll();


            if (list != null) {
                try {
                    
                    
                    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

                    for (int i = 0; i < list.size(); i++) {
                        arrBuilder.add(Json.createObjectBuilder()
                        .add("id", list.get(i).getId())
                        .add("nazev", list.get(i).getNazev())
                        .add("cena", list.get(i).getCena())
                        .add("status", list.get(i).getStatus())
                        );
                    }

                    JsonArray arr = arrBuilder.build();

                    JsonObject jsonObject = Json.createObjectBuilder().add("objednavka", arr).build();
                    
                    session.getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                text = "";
            }
            // Thread polling after sleeping for 1000ms
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
