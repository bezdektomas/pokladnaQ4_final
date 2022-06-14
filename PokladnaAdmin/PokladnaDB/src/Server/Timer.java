package Server;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Shared.Uloziste;

public class Timer extends Thread {

    public Timer() {
        super("Timer");
    }

    public void run() {
        String obj;
        int poradi;

        try {
            do {
                sleep(180000 * 1000);
                Uloziste uloziste = (Uloziste) Naming.lookup("rmi://jskola:12345/uloziste");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                System.out.println(uloziste.getVelikost());
                for (int i = 0; i < uloziste.getVelikost(); i++) {
                    obj = uloziste.getObjednavka(i);
                    poradi = obj.indexOf(' ');
                    String casObjednavky = obj.substring(0, poradi);

                    String time1 = dtf.format(now);
                    LocalTime t1 = LocalTime.parse(time1);
                    LocalTime t2 = LocalTime.parse(casObjednavky);
                    Duration diff = Duration.between(t2, t1);
                    long rozdil = diff.toMinutes();
                    if (diff.toMinutes() < 0)
                        rozdil += 1440;
                    if (rozdil > 10) {
                        uloziste.odstranPrvni();

                        i--;
                    }
                }
            } while (true);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
