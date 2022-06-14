package Client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.UnsupportedLookAndFeelException;

import Shared.data.Kategorie;
import Shared.Objednavky;
import Shared.data.Objednavka;

public class App {
    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(startFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        Runnable r = new Runnable() {

            @Override
            public void run() {
                (new startFrame()).setVisible(true);
            }
        };

        r.run();
    }

}
