package Client;

import java.io.FileWriter;
import java.io.IOException;

import Shared.list.Objednavka;

public class Tiskarna {
    private static Tiskarna instance = null;

    public static Tiskarna getTiskarna() {
        if (instance == null)
            instance = new Tiskarna();
        return instance;
    }

    public void ucetenka(Objednavka objednavka, int id) {
        int delkaRadku = 30;
        String nazevPol;
        try (FileWriter fw = new FileWriter("uctenka_objednavka" + (id + 1) + ".txt")) {
           
            fw.write("        ID objednávky: \t" + (id + 1) + "\n");
            fw.write("####################################" + "\n\n");

            for (int i = 0; i < objednavka.pocetPolozek(); i++) {
            nazevPol = objednavka.getPolozka(i).nazev();
                fw.write(nazevPol.replace("+", "\n"));
                
                for (int j = 0; j < (delkaRadku - nazevPol.substring(nazevPol.lastIndexOf("+")+1).length()); j++) {
                    fw.write("\u00A0");
                }
                fw.write(objednavka.getPolozka(i).cena() + "");

                if (objednavka.getPolozka(i).cena() > 99) {
                    fw.write("\u00A0Kč\n");
                } else {
                    if (objednavka.getPolozka(i).cena() < 10) {
                        fw.write("\u00A0\u00A0\u00A0Kč\n");
                    } else {
                        fw.write("\u00A0\u00A0Kč\n");
                    }
                }
                fw.write("\n");
            }
            fw.write("\u00A0\u00A0Celková cena objednávky: " + objednavka.celkovaCena() + " Kč\n");
            fw.write("####################################" + "\n\n");
            fw.write("\tDěkujeme za návštěvu.");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    } 


}
