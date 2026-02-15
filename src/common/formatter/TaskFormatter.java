package common.formatter;

import infra.database.sqlite.models.Task;

public class TaskFormatter {

    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static String toDisplay(Task task) {

        String rawStatus = task.getDone() == 0
                ? "PENDING"
                : "DONE";

        String paddedStatus = String.format("%-10s", rawStatus);

        String coloredStatus = task.getDone() == 0
                ? YELLOW + paddedStatus + RESET
                : GREEN + paddedStatus + RESET;

        return String.format(
                "| %-4d | %-25s | %s |",
                task.getId(),
                task.getTitle(),
                coloredStatus
        );
    }
}
