package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Objednavky extends Item {
    protected int cena;
    protected String nazev;
    private int idHlavniho;
    private int status;

    public Objednavky(int cena, String nazev) {  // Když se zakládá nová položka
        super();
        this.setCena(cena).setNazev(nazev); 
    }

    public Objednavky(int id) {  // Když znám ID a budu chtít načíst zbytek dat
        super(id);
    }

    private Objednavky(int id, int cena, String nazev, int status) {
        super();
        this.id = id;
        this.setCena(cena).setNazev(nazev).setStatus(status);
    }

    public Objednavky setIdHlavnihoProduktu(int cislo) {
        this.idHlavniho = cislo;
        return this;
    }

    public int getIdHlavnihoProduktu() {
        return this.idHlavniho;
    }

    public String getNazev() {
        return nazev;
    }

    public int getCena() {
        return cena;
    }

    public int getStatus() {
        return status;
    }

    public Objednavky setStatus(int status) {
        this.status = status;
        return this;
    }

    public Objednavky setNazev(String nazev) {
        this.nazev = nazev;
        return this;
    }

    public Objednavky setCena(int cena) {
        this.cena = cena;
        return this;
    }

    @Override
    public boolean save() {
        if (this.id >= 0) {
            // vložení nového záznamu
            try (
                Connection connection = Db.get().getConnection();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES (?, (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as obj), IFNULL(((SELECT MAX(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as polozka_objednavka)) + 1), 1), (SELECT COUNT(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM objednavka) as polozka_objednavka)) + 1)", PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
                stmt.setInt(1, this.id);
                if (stmt.executeUpdate() == 1) {  // Byl vložen 1 řádek
                    try (ResultSet result = stmt.getGeneratedKeys()) {
                        return true;
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
            } catch (SQLException /*| ClassNotFoundException*/ e) {
                e.printStackTrace();
            }
        } else {
            // úprava existujícího záznamu
            try (
                Connection connection = Db.get().getConnection();
                PreparedStatement stmt = connection.prepareStatement("UPDATE tridy SET Cena=? , Nazev=? WHERE id=?")
            ) {
                stmt.setInt(1, this.cena);
                stmt.setString(2, this.nazev);
                stmt.setInt(3, this.id);
                return stmt.executeUpdate() == 1;  // Byl vložen 1 řádek
            
            } catch (SQLException /*| ClassNotFoundException*/ e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean savePridavek(int idHlavniho) {
        if (this.id >= 0) {
            // vložení nového záznamu
            try (
                Connection connection = Db.get().getConnection();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO `polozka_has_objednavka` (`idPolozky`, `idObjednavky`, `poradiVObjednavce`, `prirazeniPolozky`) VALUES (?, (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka), (SELECT MAX(poradiVObjednavce) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka)) + 1, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
                stmt.setInt(1, this.id);
                stmt.setInt(2, idHlavniho);
                if (stmt.executeUpdate() == 1) {  // Byl vložen 1 řádek
                    try (ResultSet result = stmt.getGeneratedKeys()) {
                        return true;
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                }
            } catch (SQLException /*| ClassNotFoundException*/ e) {
                e.printStackTrace();
            }
        } else {
            // úprava existujícího záznamu
            try (
                Connection connection = Db.get().getConnection();
                PreparedStatement stmt = connection.prepareStatement("UPDATE tridy SET Cena=? , Nazev=? WHERE id=?")
            ) {
                stmt.setInt(1, this.cena);
                stmt.setString(2, this.nazev);
                stmt.setInt(3, this.id);
                return stmt.executeUpdate() == 1;  // Byl vložen 1 řádek
            
            } catch (SQLException /*| ClassNotFoundException*/ e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void load() {
        if (this.id <= 0) {
            throw new IllegalStateException("Není definované ID.");
        }
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT cena, nazevPolozky FROM polozka WHERE idPolozky = ?")
        ) {
            stmt.setInt(1, this.id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    this.setCena(result.getInt("cena")).setNazev(result.getString("nazevPolozky"));

                }
            }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void delete() {
        if (this.id <= 0){
            throw new IllegalStateException("Není definované ID.");
        }
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tridy WHERE id = ?")
        ) {
            stmt.setInt(1, this.id);
            stmt.executeUpdate();
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
        }
    }

    public static List<Polozka> getAll(int id){
        List<Polozka> polozky = new ArrayList<Polozka>();

        try (
            Connection connection = Db.get().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT idPolozky, nazevPolozky, cena FROM polozka WHERE kategorie_id = " + id)
        ) {
            while (result.next()) {
               }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            polozky = null;
        }

        return polozky;
    }

    public int getMaxProdukt() {
        int cislo = 1;
        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT MAX(poradiVObjednavce) FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM polozka_has_objednavka)");) {
            while (tridyRs.next()) {
                cislo = tridyRs.getInt("MAX(poradiVObjednavce)");
            }   
        } catch (Exception e) {
            e.printStackTrace();
            return cislo;
        }
        return cislo;
    }

    public static List<Polozka> getObjednavkaPolozky() {
        List<Polozka> polozky = new ArrayList<Polozka>();

        try (
            Connection connection = Db.get().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT nazevPolozky, cena, poradiVObjednavce, prirazeniPolozky FROM `polozka_has_objednavka` JOIN polozka ON polozka.idPolozky = polozka_has_objednavka.idPolozky WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM objednavka) ORDER BY `polozka_has_objednavka`.`poradiVObjednavce` ASC")
        ) {
            while (result.next()) {
                }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            polozky = null;
        }

        return polozky;
    }

    public static List<Polozka> getObjednavkaPolozkyMazani() {
        List<Polozka> polozky = new ArrayList<Polozka>();

        try (
            Connection connection = Db.get().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT nazevPolozky, cena, poradiVObjednavce, prirazeniPolozky FROM `polozka_has_objednavka` JOIN polozka ON polozka.idPolozky = polozka_has_objednavka.idPolozky WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM objednavka) ORDER BY `polozka_has_objednavka`.`poradiVObjednavce` ASC")
        ) {
            while (result.next()) {
                }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            polozky = null;
        }

        return polozky;
    }

    public boolean smazaniPolozky(int id) {
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka) AND prirazeniPolozky = ?")
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean zahozeniObjednavky() {
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM polozka_has_objednavka WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM (SELECT * FROM polozka_has_objednavka) as polozka_objednavka)")
        ) {
            stmt.executeUpdate();
            return true;
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dokonceniObjednavky() {
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); 


            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE objednavka SET cas = ?, datum = ?, status = 1 WHERE cas IS NULL",
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

    public static void pridatObjednavku() {
        // TODO Auto-generated method stub
        try (Connection conn = Db.get().getConnection()) {
            conn.setAutoCommit(false); // zacatek transakce

            // priprava dotazu s pozadavkem na poskytnuti doplnenych automaticky
            // generovanych klicu
            try (java.sql.PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO `objednavka` (`idObjednavky`, `cas`, `datum`, `status`) VALUES (NULL, NULL, ?, 0)",
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
    
    public static int getCas() {
        int cislo = 0;
        try (Connection conn = Db.get().getConnection();
                Statement tridyStmt = conn.createStatement();
                ResultSet tridyRs = tridyStmt.executeQuery(
                        "SELECT idObjednavky, cas, datum FROM `objednavka` WHERE idObjednavky = (SELECT MAX(idObjednavky) FROM objednavka)");) {
            while (tridyRs.next()) {
                if (tridyRs.getString("cas") == "") {
                    cislo = 0;
                } else {
                    cislo = 1;
                }
            }   
        } catch (Exception e) {
            e.printStackTrace();
            return cislo;
        }
        return cislo;
    }

    public static List<Objednavky> getObjednavkyVydej() {
        List<Objednavky> obj = new ArrayList<Objednavky>();

        try (
            Connection connection = Db.get().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT polozka_has_objednavka.idObjednavky, nazevPolozky, cena, poradiVObjednavce, prirazeniPolozky, objednavka.status FROM `polozka_has_objednavka` JOIN polozka ON polozka.idPolozky = polozka_has_objednavka.idPolozky JOIN objednavka ON objednavka.idObjednavky = polozka_has_objednavka.idObjednavky  WHERE status = 1 OR status = 2 OR status = 3 ORDER BY polozka_has_objednavka.idObjednavky ASC, `polozka_has_objednavka`.`poradiVObjednavce` ASC")
        ) {
            while (result.next()) {
               obj.add(new Objednavky(result.getInt("idObjednavky"), result.getInt("cena"), result.getString("nazevPolozky"), result.getInt("status")));
            }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            obj = null;
        }

        return obj;
    }

    public boolean zmenaStatusu(int id, int status) {
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("UPDATE `objednavka` SET `status` = ? WHERE `objednavka`.`idObjednavky` = ?")
        ) {
            stmt.setInt(1, status);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            return false;
        }
    }
}
