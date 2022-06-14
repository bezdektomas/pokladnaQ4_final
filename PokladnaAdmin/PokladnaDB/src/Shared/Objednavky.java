package Shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import Shared.data.Objednavka;
import Shared.data.Prirazeni;
import Shared.data.Produkt;
import Shared.data.Kategorie;

public interface Objednavky extends Remote {
    List<Objednavka> getObejdnavka() throws RemoteException;

    List<Kategorie> getKategorie() throws RemoteException;

    Objednavka getObjednavka(int id) throws RemoteException;

    boolean writeKategorie(Kategorie kategorie) throws RemoteException;

    boolean editKategorie(Kategorie kategorie) throws RemoteException;

    List<Produkt> getProdukty() throws RemoteException;

    boolean editProdukt(Produkt produkt) throws RemoteException;

    Produkt getProdukt(int id) throws RemoteException;

    boolean writeProdukt(Produkt produkt) throws RemoteException;

    List<Produkt> getProduktKategorie(int id) throws RemoteException;

    List<Produkt> getProduktyObjednavky() throws RemoteException;

    boolean writeProduktObjednavka(Produkt produkt) throws RemoteException;

    boolean writePridavekObjednavka(Produkt produkt) throws RemoteException;

    Prirazeni getMaxProdukt() throws RemoteException;

    boolean smazaniProduktu(Produkt produkt) throws RemoteException;

    void smazaniPosledniObjednavky() throws RemoteException;

    void dokonceniObjednavky() throws RemoteException;

    List<Objednavka> ziskejObjednavku() throws RemoteException;

    void pridatObjednavku() throws RemoteException;

    List<Objednavka> getObjednavkyCisla() throws RemoteException;

    List<Produkt> getObejdnavkaId(int id) throws RemoteException;
}
