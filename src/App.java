
import common.commands.AddTaskCommand;
import common.commands.DoneTaskCommand;
import common.commands.HelpCommand;
import common.commands.ListTasksCommand;
import common.commands.RemoveTaskCommand;
import infra.database.sqlite.config.DatabaseConfig;
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

        if (args.isEmpty()) {
            System.out.println("Nenhum argumento informado utilize --help para identificar os argumentos");
            return 1;
        }

        String command = _args[0];

        switch (command) {
            case "add":
                if (args.size() < 3) {
                    System.out.println("Uso: add \"title\" \"description\"");
                    return 1;
                }

                String title = args.get(1);
                String description = args.get(2);

                new AddTaskCommand().execute(title, description);
                return 1;
            case "list":
                int page = 1;
                for (int i = 1; i < args.size(); i++) {
                    if (args.get(i).equals("--page")) {
                        if (i + 1 >= args.size()) {
                            System.out.println("Informe o número da página após --page");
                            return 1;
                        }

                        try {
                            page = Integer.parseInt(args.get(i + 1));
                        } catch (NumberFormatException e) {
                            System.out.println("Página deve ser um número.");
                            return 1;
                        }

                        i++;
                    }
                }

                new ListTasksCommand().execute(page);
                return 0;
            case "done":
                try {
                    int id = Integer.parseInt(args.get(1));
                    new DoneTaskCommand().execute(id);
                } catch (NumberFormatException e) {
                    System.out.println("Id deve ser um número.");
                    return 1;
                }

                return 1;

            case "remove":
                try {
                    int id = Integer.parseInt(args.get(1));
                    new RemoveTaskCommand().execute(id);
                } catch (NumberFormatException e) {
                    System.out.println("Id deve ser um número.");
                    return 1;
                }

                return 1;
            case "--help":
                HelpCommand.execute();
                return 1;
            default:
                System.out.println("Comando inválido.");
                return 1;
        }
    }
}
