package Shared.list;

public class Polozka implements PolozkaRozhrani {
    
    private String nazev;
    private int cena;

    public Polozka(String nazev, int cena) {
        this.nazev = nazev;
        this.cena = cena;
    }

    @Override
    public String nazev() {
        return this.nazev;
    }

    @Override
    public int cena() {
        return this.cena;
    }
}
