package Shared.list;

import java.util.LinkedList;
import java.util.List;

public class Objednavka implements ObjednavkaRozhrani {

    public int id;
    private int serverId;

    public Objednavka(int id) {
        this.id = id;
    }

    List<Polozka> polozky = new LinkedList<Polozka>();

    @Override
    public int celkovaCena() {
        int cena = 0;
        for (int i = 0; i < polozky.size(); i++) {
            cena += polozky.get(i).cena();
        }
        return cena;
    }

    @Override
    public int pocetPolozek() {
        return this.polozky.size();
    }

    @Override
    public void setPolozka(String nazev, int cena) {
        polozky.add(new Polozka(nazev, cena));
    }

    @Override
    public Polozka getPolozka(int poradi) {
        return this.polozky.get(poradi);
    }

    @Override
    public Polozka removePolozka(int poradi) {
        return this.polozky.remove(poradi);
    }

    public int setServerId(int id) {
        return this.serverId = id;
    }

    public int getServerId() {
        return this.serverId;
    }

}
