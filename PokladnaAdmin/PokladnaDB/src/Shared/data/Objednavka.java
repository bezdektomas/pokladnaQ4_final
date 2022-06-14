package Shared.data;

import java.util.Map;

public class Objednavka extends Polozka {
    private int id;
    private String oznaceni;
    private String cas;

    public Objednavka() {
    }

    public String getOznaceni() {
        return oznaceni;
    }

    public Objednavka setOznaceni(String oznaceni) {
        this.oznaceni = oznaceni;
        return this;
    }
    /*
     * @Override public Objednavka setId(int id) { return
     * (Objednavka)super.setId(id); }
     */

    public Objednavka setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Objednavka setCas(String cas) {
        this.cas = cas;
        return this;
    }

    public String getCas() {
        return cas;
    }

}
