package Shared.data;

import java.util.Map;

public class Kategorie extends Polozka {
    private int id;
    private String oznaceni;
    private String cas;

    public Kategorie() {
    }

    public String getOznaceni() {
        return oznaceni;
    }

    public Kategorie setOznaceni(String oznaceni) {
        this.oznaceni = oznaceni;
        return this;
    }
    /*
     * @Override public Kategorie setId(int id) { return
     * (Kategorie)super.setId(id); }
     */

    public Kategorie setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Kategorie setCas(String cas) {
        this.cas = cas;
        return this;
    }

    public String getCas() {
        return cas;
    }

    public Object getKategorie() {
        return null;
    }

}
