package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.Duration;
import java.time.LocalTime;

import Shared.list.ObjednavkySeznam;
import Server.list.Uloziste;

public class App {
    public static void main(String[] args) {
        Registry reg;
        try {
            reg = LocateRegistry.createRegistry(12345); // port, na kterém aplikace naslouchá
            reg.rebind("objednavka", new Objednavky()); // zaregistrování služby, kterou aplikace poskytuje
            reg.rebind("uloziste", new Uloziste());
            System.out.println("Server ready");
            Timer t = new Timer();
            t.run();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
