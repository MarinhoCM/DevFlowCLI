package infra.database.sqlite.repositories;

import common.mapper.ResultSetMapper;
import infra.database.ConnectionFactory;
import infra.database.sqlite.models.Task;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private int executeUpdate(String sql, Object... params) {

        try (Connection conn = connectionFactory.getConnection(); var stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar update", e);
        }
    }

    public List<Task> getPaginated(int page, int pageSize) {

        String sql = """
            SELECT *
            FROM tasks
            ORDER BY id
            LIMIT ? OFFSET ?
        """;

        int offset = (page - 1) * pageSize;

        return executeQuery(sql, rs -> new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getInt("done"),
                rs.getString("created_at"),
                rs.getString("updated_at")
        ), pageSize, offset);
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

    public int insertOne(Task task) {
        String sql = """
            INSERT INTO tasks 
                (title, description, done)
            values 
                (?, ?, ?)        
        """;

        return executeUpdate(
                sql,
                task.getTitle(),
                task.getDescription(),
                task.getDone()
        );
    }

    public int updateTaskStatus(int id, int done) {

        String sql = "UPDATE tasks SET done = ?, updated_at = ? WHERE id = ?";

        return executeUpdate(
                sql,
                done,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                id
        );
    }

    public int count() {
        String sql = "SELECT COUNT(*) as total FROM tasks";
        List<Integer> result = executeQuery(
                sql,
                rs -> rs.getInt("total")
        );

        return result.isEmpty() ? 0 : result.get(0);
    }

}
