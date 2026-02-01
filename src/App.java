
import infra.database.sqlite.config.DatabaseConfig;

public class App {

    public static void main(String[] args) throws Exception {
        new DatabaseConfig().initialize();
    }
}
