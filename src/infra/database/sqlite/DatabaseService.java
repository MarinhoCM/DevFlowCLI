package infra.database.sqlite;

import common.mapper.ResultSetMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {

    private static final String URL = "jdbc:sqlite:base.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public void execute(String sql) {
        try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar commando SQL:\n", e);
        }
    }

    public <T> T query(String sql, ResultSetMapper<T> mapper) {
        try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            return mapper.map(rs);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar query", e);
        }
    }
}
