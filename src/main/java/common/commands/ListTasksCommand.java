package common.commands;

import infra.services.TaskService;

public class ListTasksCommand {

    private final TaskService taskService;

    public ListTasksCommand() {
        this.taskService = new TaskService();
    }

    public void  execute(int page) {
        System.out.println(this.taskService.getAllTasks(page, 15));
    }

}
