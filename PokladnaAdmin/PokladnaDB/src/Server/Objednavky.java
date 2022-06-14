package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Server.db.Db;
import Shared.data.Kategorie;
import Shared.data.Objednavka;
import Shared.data.Prirazeni;
import Shared.data.Produkt;

/**
 * Objednavky
 */
public class Objednavky extends UnicastRemoteObject implements Shared.Objednavky {

    protected Objednavky() throws RemoteException {
        super();
    }

    @Override
    public List<Objednavka> getObejdnavka() throws RemoteException {
        List<Objednavka> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery("SELECT idObjednavky, datum, cas FROM objednavka");) {
            while (tridyRs.next()) {
                System.out.println(tridyRs.getInt("idObjednavky"));
                Objednavka ob = new Objednavka().setId(tridyRs.getInt("idObjednavky"))
                        .setOznaceni(tridyRs.getString("datum")).setCas(tridyRs.getString("cas"));
                System.out.println(ob.getOznaceni());

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;

    }

    @Override
    public Objednavka getObjednavka(int id) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Kategorie> getKategorie() throws RemoteException {
        List<Kategorie> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery("SELECT id, nazev FROM kategorie");) {
            while (tridyRs.next()) {
                System.out.println(tridyRs.getInt("id"));
                Kategorie ob = new Kategorie().setId(tridyRs.getInt("id")).setOznaceni(tridyRs.getString("nazev"));
                System.out.println(ob.getOznaceni());

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public boolean writeKategorie(Kategorie kategorie) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement("INSERT INTO kategorie (nazev) values (?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, kategorie.getOznaceni());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    kategorie.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + kategorie.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean editKategorie(Kategorie kategorie) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement("UPDATE kategorie SET nazev = ? WHERE id = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                stmt.setString(1, kategorie.getOznaceni());
                stmt.setInt(2, kategorie.getId());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    kategorie.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + kategorie.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Produkt> getProdukty() throws RemoteException {
        List<Produkt> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idPolozky, nazev AS nazevKategorie, nazevPolozky, cena, aktivni FROM `polozka` JOIN kategorie ON polozka.kategorie_id = kategorie.id ORDER BY `polozka`.`idPolozky` ASC");) {
            while (tridyRs.next()) {
                Produkt ob = new Produkt().setIdProduktu(tridyRs.getInt("idPolozky"))
                        .setNazevPolozky(tridyRs.getString("nazevPolozky")).setCena(tridyRs.getInt("cena"))
                        .setNazevKategorie(tridyRs.getString("nazevKategorie")).setAktivni(tridyRs.getInt("aktivni"));
                System.out.println(ob.getNazevPolozky());

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public boolean editProdukt(Produkt produkt) throws RemoteException {
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE polozka SET kategorie_id = ?, nazevPolozky = ?, cena = ?, aktivni = ? WHERE idPolozky = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {

                stmt.setInt(1, produkt.getidKategorie());
                stmt.setString(2, produkt.getNazevPolozky());
                stmt.setInt(3, produkt.getCena());
                stmt.setInt(4, produkt.getAktivni());
                stmt.setInt(5, produkt.getIdProduktu());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + produkt.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Produkt getProdukt(int id) throws RemoteException {
        Produkt pro = new Produkt();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idPolozky, nazev AS nazevKategorie, nazevPolozky, cena, aktivni FROM `polozka` JOIN kategorie ON polozka.kategorie_id = kategorie.id WHERE idPolozky = "
                                + id);) {
            while (tridyRs.next()) {
                pro = new Produkt().setIdProduktu(tridyRs.getInt("idPolozky"))
                        .setNazevPolozky(tridyRs.getString("nazevPolozky")).setCena(tridyRs.getInt("cena"))
                        .setNazevKategorie(tridyRs.getString("nazevKategorie")).setAktivni(tridyRs.getInt("aktivni"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return pro;
    }

    @Override
    public boolean writeProdukt(Produkt produkt) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO `polozka` (`idPolozky`, `kategorie_id`, `nazevPolozky`, `cena`, `aktivni`) VALUES (?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, produkt.getIdProduktu());
                stmt.setInt(2, produkt.getidKategorie());
                stmt.setString(3, produkt.getNazevPolozky());
                stmt.setInt(4, produkt.getCena());
                stmt.setInt(5, produkt.getAktivni());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + produkt.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Produkt> getProduktKategorie(int id) throws RemoteException {

        List<Produkt> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idPolozky, nazev AS nazevKategorie, id AS idKategorie, nazevPolozky, cena, aktivni FROM `polozka` JOIN kategorie ON polozka.kategorie_id = kategorie.id WHERE aktivni = 1 AND kategorie.id = "
                                + id);) {
            while (tridyRs.next()) {
                Produkt ob = new Produkt().setIdProduktu(tridyRs.getInt("idPolozky"))
                        .setNazevPolozky(tridyRs.getString("nazevPolozky")).setCena(tridyRs.getInt("cena"))
                        .setNazevKategorie(tridyRs.getString("nazevKategorie")).setAktivni(tridyRs.getInt("aktivni"));

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public List<Produkt> getProduktyObjednavky() throws RemoteException {
        List<Produkt> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT nazevPolozky, cena, poradiVObjednavce, prirazeniPolozky FROM `polozka_has_objednavka` JOIN polozka ON polozka.idPolozky = polozka_has_objednavka.idPolozky WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM objednavka) ORDER BY `polozka_has_objednavka`.`poradiVObjednavce` ASC");) {
            while (tridyRs.next()) {
                Produkt ob = new Produkt().setNazevPolozky(tridyRs.getString("nazevPolozky"))
                        .setCena(tridyRs.getInt("cena")).setIdHlavnihoProduktu(tridyRs.getInt("prirazeniPolozky"));
                System.out.println(ob.getNazevPolozky());

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public boolean writeProduktObjednavka(Produkt produkt) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu

            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES (?, (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as obj), IFNULL(((SELECT MAX(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as polozka_objednavka)) + 1), 1), (SELECT COUNT(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as polozka_objednavka)) + 1)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, produkt.getIdProduktu());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + produkt.getId());
                }
                rs.close();
            } catch (Exception e) {
                try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES (?, (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as obj), 1, 1)",
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
                    stmt.setInt(1, produkt.getIdProduktu());
                    if (stmt.executeUpdate() != 1) {
                        throw new Exception("Nepodaril se zapis kategorie");
                    }

                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                        System.out.println("ziskane id: " + produkt.getId());
                    }
                    rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    conn.rollback();
                    throw e;
                }
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean writePridavekObjednavka(Produkt produkt) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES (?, (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka), (SELECT MAX(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka)) + 1, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, produkt.getIdProduktu());
                stmt.setInt(2, produkt.getIdHlavnihoProduktu());
                if (stmt.executeUpdate() != 1) {
                    throw new Exception("Nepodaril se zapis kategorie");
                }

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + produkt.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Prirazeni getMaxProdukt() throws RemoteException {
        Prirazeni pro = new Prirazeni();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT MAX(poradiVObjednavce) FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM polozka_has_objednavka)");) {
            while (tridyRs.next()) {
                pro = new Prirazeni().setIdPrirazeni(tridyRs.getInt("MAX(poradiVObjednavce)"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return pro;
    }

    @Override
    public boolean smazaniProduktu(Produkt produkt) throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka) AND prirazeniPolozky = ?",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, produkt.getIdHlavnihoProduktu());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    produkt.setId(rs.getInt(1)); // ziskani vygenerovaneho id
                    System.out.println("ziskane id: " + produkt.getId());
                }
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void smazaniPosledniObjednavky() throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void dokonceniObjednavky() throws RemoteException {
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE objednavka SET cas = ?, datum = ? WHERE cas IS NULL",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                java.util.Date date = new java.util.Date();

                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                java.sql.Timestamp sqlTime = new java.sql.Timestamp(date.getTime());

                stmt.setTimestamp(1, sqlTime);
                stmt.setDate(2, sqlDate);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Objednavka> ziskejObjednavku() throws RemoteException {
        List<Objednavka> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idObjednavky, cas, datum FROM `objednavka` WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM objednavka)");) {
            while (tridyRs.next()) {
                Objednavka ob = new Objednavka().setCas(tridyRs.getString("cas"));

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

    @Override
    public void pridatObjednavku() throws RemoteException {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO `objednavka` (`idObjednavky`, `cas`, `datum`) VALUES (NULL, NULL, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                java.util.Date date = new java.util.Date();

                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                stmt.setDate(1, sqlDate);
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
                conn.rollback();
                throw e;
            }

            conn.commit(); // potvrzeni transakce
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Objednavka> getObjednavkyCisla() throws RemoteException {
        List<Objednavka> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idObjednavky FROM `objednavka` WHERE status > 0");) {
            while (tridyRs.next()) {
                Objednavka ob = new Objednavka().setId(tridyRs.getInt("idObjednavky"));

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }



    @Override
    public List<Produkt> getObejdnavkaId(int id) throws RemoteException {
        List<Produkt> obj = new ArrayList<>();

        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT nazevPolozky, cena, poradiVObjednavce, prirazeniPolozky FROM `polozka_has_objednavka` JOIN polozka ON polozka.idPolozky = polozka_has_objednavka.idPolozky WHERE idObjednavky = " + id  + " ORDER BY `polozka_has_objednavka`.`poradiVObjednavce` ASC");) {
            while (tridyRs.next()) {
                Produkt ob = new Produkt().setNazevPolozky(tridyRs.getString("nazevPolozky"))
                        .setCena(tridyRs.getInt("cena")).setIdHlavnihoProduktu(tridyRs.getInt("prirazeniPolozky"));
                System.out.println(ob.getNazevPolozky());

                obj.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return obj;
    }

}