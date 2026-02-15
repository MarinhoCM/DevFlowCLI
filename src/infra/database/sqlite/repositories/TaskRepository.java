package infra.database.sqlite.repositories;

import common.mapper.ResultSetMapper;
import infra.database.ConnectionFactory;
import infra.database.sqlite.models.Task;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private final ConnectionFactory connectionFactory;

    public TaskRepository() {
        this.connectionFactory = new ConnectionFactory();
    }

    private <T> List<T> executeQuery(String sql, ResultSetMapper<T> mapper, Object... params) {
        List<T> results = new ArrayList<>();

        try (Connection conn = connectionFactory.getConnection(); var stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapper.map(rs));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar query", e);
        }

        return results;
    }

    public List<Task> getAll() {
        String qry = "SELECT * FROM tasks";

        return executeQuery(qry, rs -> new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("done"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        ));
    }

    public List<Task> getOne(String column, Object value) {

        String sql = "SELECT * FROM tasks WHERE " + column + " = ?";

        return executeQuery(sql, rs -> new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("done"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        ), value);
    }

}
