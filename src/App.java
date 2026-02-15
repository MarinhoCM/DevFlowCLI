import infra.database.sqlite.config.DatabaseConfig;
import infra.services.TaskService;
import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(final String[] _args) throws Exception {
        try {
            new DatabaseConfig().initialize();
            System.exit(_execute(_args));
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(-1);
        }
    }
    
    public static int _execute(String[] _args) throws Exception {
        List<String> args = Arrays.asList(_args);
        TaskService taskService = new TaskService();
        return 0;
    }
}
