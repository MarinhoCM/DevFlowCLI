package common.commands;

import infra.services.TaskService;

public class RemoveTaskCommand {

    private final TaskService taskService;

    public RemoveTaskCommand() {
        this.taskService = new TaskService();
    }

    public void execute(int id) {
        System.out.println(this.taskService.removeTask(id));
    }
}
