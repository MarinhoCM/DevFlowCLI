package infra.database.sqlite.config;

import infra.database.sqlite.DatabaseService;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private final DatabaseService databaseService;

    public DatabaseConfig() {
        this.databaseService = new DatabaseService();
    }

    public Connection getConnection() throws SQLException {
        return databaseService.getConnection();
    }

    public void initialize() {
        String createTableQuery = """
                CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                description TEXT,
                done INTEGER NOT NULL DEFAULT 0,
                created_at TEXT NOT NULL,
                updated_at TEXT
            )
        """;

        try (Connection conn = this.getConnection(); Statement stmt = conn.createStatement();) {
            stmt.execute(createTableQuery);
        } catch (SQLException err) {
            throw new RuntimeException("Erro ao inicializar banco de dados:\n", err);
        }
    }
}
