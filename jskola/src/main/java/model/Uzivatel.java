package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import org.mindrot.jbcrypt.BCrypt;

public class Uzivatel extends Item {

    protected String jmeno;
    protected byte uroven;

    public Uzivatel() {
        super();
    }

    public Uzivatel(int id) {
        super(id);
    }

    public String getJmeno() {
        return jmeno;
    }

    public byte getUroven() {
        return uroven;
    }

    protected Uzivatel setJmeno(String jmeno) {
        this.jmeno = jmeno;
        return this;
    }

    protected Uzivatel setUroven(byte uroven) {
        this.uroven = uroven;
        return this;
    }

    @Override
    public boolean save() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void load() {
        if (this.id <= 0) {
            throw new IllegalStateException("Není definované ID.");
        }
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT jmeno, uroven FROM uzivatele WHERE id = ?")
        ) {
            stmt.setInt(1, this.id);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    this.setJmeno(result.getString("jmeno")).setUroven(result.getByte("uroven"));
                }
            }
        } catch (SQLException /*| ClassNotFoundException*/ e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        // TODO Auto-generated method stub
        
    }

    public static Uzivatel overeni(String jmeno, char[] heslo) {
        try (
            Connection connection = Db.get().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT id, heslo FROM uzivatele WHERE jmeno = ?")
        ) {
            stmt.setString(1, jmeno);
            try (ResultSet vysledek = stmt.executeQuery()) {
                if (vysledek.next()) {
                    if (BCrypt.checkpw(new String(heslo), vysledek.getString("heslo"))){
                        return new Uzivatel(vysledek.getInt("id"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
