package Shared.list;

import Shared.list.Polozka;

public interface ObjednavkaRozhrani {
    int celkovaCena();
    int pocetPolozek();
    void setPolozka(String nazev, int cena);
    Polozka getPolozka(int poradi);
    Polozka removePolozka(int poradi);
}
