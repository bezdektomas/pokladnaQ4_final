package Server.list;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class Uloziste extends UnicastRemoteObject implements Shared.Uloziste {
    public Uloziste() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    LinkedList<String> objednavky = new LinkedList<String>();

    @Override
    public void pridej(String objednavka) throws RemoteException {
        objednavky.add(objednavka);
        
    }

    @Override
    public String getObjednavka(int index) throws RemoteException {
        return objednavky.get(index);
    }

    @Override
    public int getVelikost() throws RemoteException {
        return objednavky.size();
    }

    @Override
    public void odstranPrvni() throws RemoteException {
        objednavky.removeFirst();
        
    }
    
    
}
