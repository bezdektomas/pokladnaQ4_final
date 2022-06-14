package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Trida extends Item {
    protected int rocnik;
    protected String oznaceni;

    public Trida(int rocnik, String oznaceni) {  // Když se zakládá nová položka
        super();
        this.setRocnik(rocnik).setOznaceni(oznaceni); 
    }

    public Trida(int id) {  // Když znám ID a budu chtít načíst zbytek dat
        super(id);
    }

    private Trida(int id, int rocnik, String oznaceni) {
        super();
        this.id = id;
        this.setRocnik(rocnik).setOznaceni(oznaceni);
    }

    public String getOznaceni() {
        return oznaceni;
    }

    public int getRocnik() {
        return rocnik;
    }

    public Trida setOznaceni(String oznaceni) {
        this.oznaceni = oznaceni;
        return this;
    }

    public Trida setRocnik(int rocnik) {
        this.rocnik = rocnik;
        return this;
    }

    @Override
    public boolean save() {
        if (this.id <= 0) {
            // vložení nového záznamu
            try (
                Connection connection = Db.get().getConnection();
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO tridy (rocnik, oznaceni) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
                stmt.setInt(1, this.rocnik);
                stmt.setString(2, this.oznaceni);
                if (stmt.executeUpdate() == 1) {  // Byl vložen 1 řádek
                    try (ResultSet result = stmt.getGeneratedKeys()) {
                        if (result.next()) {
                            this.id = result.getInt(1);
                            return true;
                        }
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
                PreparedStatement stmt = connection.prepareStatement("UPDATE tridy SET rocnik=? , oznaceni=? WHERE id=?")
            ) {
                stmt.setInt(1, this.rocnik);
                stmt.setString(2, this.oznaceni);
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
            PreparedStatement stmt = connection.prepareStatement("SELECT rocnik, oznaceni FROM tridy WHERE id = ?")
        ) {
            stmt.setInt(1, this.id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    this.setRocnik(result.getInt("rocnik")).setOznaceni(result.getString("oznaceni"));

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

    public static List<Trida> getAll(){
        List<Trida> tridy = new ArrayList<Trida>();

        try (
            Connection connection = Db.get().getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT idPolozky, nazevPolozky, cena FROM polozka")
        ) {
            while (result.next()) {
                tridy.add(new Trida(result.getInt("idPolozky"), result.getInt("cena"), result.getString("nazevPolozky")));
            }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
            tridy = null;
        }

        return tridy;
    }

    
}
