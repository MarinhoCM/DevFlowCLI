package infra.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlite:base.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
