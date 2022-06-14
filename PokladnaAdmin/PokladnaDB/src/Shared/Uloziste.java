package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Uloziste extends Remote{
    void pridej(String objednavka) throws RemoteException;
    String getObjednavka(int index) throws RemoteException;
    int getVelikost() throws RemoteException;
    void odstranPrvni() throws RemoteException;
}
