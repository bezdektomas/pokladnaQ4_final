package Shared.list;

import java.util.LinkedList;
import java.util.List;

public class ObjednavkySeznam {
    public static int poradi = -1;
    private static boolean opakovani = false;
    static List<Objednavka> o = new LinkedList<>();
 
    public static Objednavka novaObjednavka() {
        o.add(new Objednavka(poradi));
        poradi++;
        return null;
    }

    public static int pocetObjednavek() {
        return poradi;
    }

    public static void odebratPosledni() {
        poradi--;
    }

    public static void smazatPosledni() {
        o.remove(poradi);
        poradi += -1;
    }
 
    public static int getCisloObjednavky() {
        return poradi;
    }


    public static Objednavka ziskat(int cislo) {
        return o.get(cislo);
    }

 
    public static void resetPoradi() {
        poradi = -1;
    }


	public static Objednavka get(int i) {
		return null;
	}

    public static void opakovaniPridat() {
        poradi++;
    }


    public static void setOpakovaniObjednavek() {
        opakovani = true;
    }


    public static Boolean getOpakovaniObjednavek() {
        return opakovani;
    }


}
