package common.commands;

import infra.services.TaskService;

public class AddTaskCommand {

    private final TaskService taskService;

    public AddTaskCommand() {
        this.taskService = new TaskService();
    }

    public void execute(String title, String description) {
        taskService.insertTask(title, description);
        System.out.println("Atividade inserida com sucesso!");
    }
}
