package print;



import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import model.Polozka;

public class Tiskarna {
    private static Tiskarna instance = null;

    public static Tiskarna getTiskarna() {
        if (instance == null)
            instance = new Tiskarna();
        return instance;
    }

    public void ucetenka(List<Polozka> pol, int id) {
        
        System.out.println("SPUŠTĚNO");
        int celkovaCena = 0;
        int delkaRadku = 30;
        String nazevPol;
        try (FileWriter fw = new FileWriter("C:/Users/Tomáš/Documents/pgQ4/pokladnaQ4/jskola/src/main/uctenka_objednavka" + (id) + ".txt")) {
            fw.write("        ID objednávky: \t" + (id) + "\n");
            fw.write("####################################" + "\n\n");

            for (int i = 0; i < pol.size(); i++) {
            nazevPol = pol.get(i).getNazev();
            System.out.println(nazevPol);
                fw.write(nazevPol.replace("+", "\n"));
                
                for (int j = 0; j < (delkaRadku - nazevPol.substring(nazevPol.lastIndexOf("+")+1).length()); j++) {
                    fw.write("\u00A0");
                }
                fw.write(pol.get(i).getCena() + "");
                celkovaCena += pol.get(i).getCena();

                if (pol.get(i).getCena() > 99) {
                    fw.write("\u00A0Kč\n");
                } else {
                    if (pol.get(i).getCena() < 10) {
                        fw.write("\u00A0\u00A0\u00A0Kč\n");
                    } else {
                        fw.write("\u00A0\u00A0Kč\n");
                    }
                }
                fw.write("\n");
            }
            fw.write("\u00A0\u00A0Celková cena objednávky: " + celkovaCena + " Kč\n");
            fw.write("####################################" + "\n\n");
            fw.write("\tDěkujeme za návštěvu.");
            fw.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    } 


}
