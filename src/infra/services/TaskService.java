package infra.services;

import common.formatter.TaskFormatter;
import infra.database.sqlite.models.Task;
import infra.database.sqlite.repositories.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    public String getAllTasks(int page, int pageSize) {

        String header = String.format(
                "| %-4s | %-25s | %-10s |",
                "ID", "TITLE", "STATUS"
        );

        String separator = "-".repeat(header.length());

        int total = taskRepository.count();
        int totalPages = (int) Math.ceil((double) total / pageSize);

        String footerText = "Page %d of %d".formatted(page, totalPages);

        int tableWidth = separator.length();
        int padding = (tableWidth - footerText.length()) / 2;

        String footer = " ".repeat(Math.max(0, padding)) + footerText;

        String body = taskRepository.getPaginated(page, pageSize)
                .stream()
                .map(TaskFormatter::toDisplay)
                .collect(Collectors.joining("\n"));

        return separator + "\n"
                + header + "\n"
                + separator + "\n"
                + body + "\n"
                + separator + "\n"
                + footer + "\n"
                + separator;
    }

    public List<String> getTaskById(Integer id) {
        return this.taskRepository
                .getOne("id", id)
                .stream()
                .map(TaskFormatter::toDisplay)
                .toList();
    }

    public int insertTask(String title, String description) {
        Task task = new Task(
                null,
                title,
                description,
                0,
                "",
                ""
        );
        return this.taskRepository.insertOne(task);
    }

    public String maskTaskAsDone(int id) {
        List<Task> queryResult = this.taskRepository.getOne("id", id);

        if (queryResult.isEmpty()) {
            return String.format("Task com Id: %d não encontrada \"\uD83D\uDC40\"\n", id);
        }

        Task task = queryResult.get(0);

        if (task.getDone() == 1) {
            return "A Task com ID %d já foi concluída.".formatted(id);
        }

        this.taskRepository.updateTaskStatus(id, 1);

        return "Task com ID %d concluída com sucesso, parabéns dev! \uD83C\uDF89".formatted(id);
    }
}
