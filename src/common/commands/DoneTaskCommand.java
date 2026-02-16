package common.commands;

import infra.services.TaskService;

public class DoneTaskCommand {

    private final TaskService taskService;

    public DoneTaskCommand() {
        this.taskService = new TaskService();
    }

    public void execute(int id) {
        System.out.println(this.taskService.maskTaskAsDone(id));
    }
}
