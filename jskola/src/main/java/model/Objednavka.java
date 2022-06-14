package model;

import java.util.List;

public class Objednavka {
    public List<Objednavky> setAll() {
        return Objednavky.getObjednavkyVydej();
    }
}
