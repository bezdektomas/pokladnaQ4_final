package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private static Db instance;

    public static Db get() {
        try {
            instance = new Db();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private Db() throws ClassNotFoundException, SQLException {
        String connectionString = String.format("jdbc:%s://%s:%d/%s", TYP_DB, SERVER, PORT, DEFAULT_DB);
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(connectionString, "root", "");
    }

    private final String TYP_DB = "mysql";
    private final String SERVER = "localhost";
    private final int PORT = 3306;
    private final String DEFAULT_DB = "pokladna";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }
}
