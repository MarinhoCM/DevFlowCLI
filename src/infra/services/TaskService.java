package infra.services;

import common.mapper.TaskMapper;
import infra.database.sqlite.models.Task;
import infra.database.sqlite.repositories.TaskRepository;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    public List<String> getAllTasks() {
        this.taskRepository.getAll();
        return this.taskRepository
                .getAll()
                .stream()
                .map(TaskMapper::toDisplay)
                .toList();

    }

    public List<Task> getTaskById(Integer id) {
        return this.taskRepository.getOne("id", id);
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
}
