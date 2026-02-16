package common.commands;

import infra.services.TaskService;

public class ShowTaskCommand {
    
    private final TaskService taskService;

    public ShowTaskCommand(){
        this.taskService = new TaskService();
    }

    public void execute(int id) {
        System.out.println(this.taskService.showTask(id));
    }
}
