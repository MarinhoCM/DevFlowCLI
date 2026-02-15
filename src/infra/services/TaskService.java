package infra.services;

import infra.database.sqlite.models.Task;
import infra.database.sqlite.repositories.TaskRepository;
import java.util.List;

public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    public List<Task> getAllTasks() {
        return this.taskRepository.getAll();
    }

    public List<Task> getTaskById(Integer id) {
        return this.taskRepository.getOne("id", id);
    }
}
