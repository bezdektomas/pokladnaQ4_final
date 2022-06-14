package Shared.data;

public class Produkt extends Polozka {
    private int idKategorie;
    private int idProduktu;
    private String nazevPolozky;
    private String nazevKategorie;
    private int cena;
    private int aktivni;
    private int idHlavnihoProduktu;

    public Produkt() {
    }

    public String getNazevKategorie() {
        return nazevKategorie;
    }

    public Produkt setNazevKategorie(String nazevKategorie) {
        this.nazevKategorie = nazevKategorie;
        return this;
    }

    public String getNazevPolozky() {
        return nazevPolozky;
    }

    public Produkt setNazevPolozky(String nazevPolozky) {
        this.nazevPolozky = nazevPolozky;
        return this;
    }


    public Produkt setidKategorie(int idKategorie) {
        this.idKategorie = idKategorie;
        return this;
    }

    public int getidKategorie() {
        return idKategorie;
    }

    public Produkt setCena(int cena) {
        this.cena = cena;
        return this;
    }

    public int getCena() {
        return cena;
    }

    public Produkt setIdProduktu(int idProduktu) {
        this.idProduktu = idProduktu;
        return this;
    }

    public int getIdProduktu() {
        return idProduktu;
    }

    public Produkt setIdHlavnihoProduktu(int idHlavnihoProduktu) {
        this.idHlavnihoProduktu = idHlavnihoProduktu;
        return this;
    }

    public int getIdHlavnihoProduktu() {
        return idHlavnihoProduktu;
    }

    public Produkt setAktivni(int aktivni) {
        this.aktivni = aktivni;
        return this;
    }

    public int getAktivni() {
        return aktivni;
    }



    public Object getProdukt() {
        return null;
    }


}
