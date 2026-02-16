package common.commands;

public class HelpCommand {

    public static void execute() {
        System.out.println("""
            Commands:
                - add - Realiza a adição de uma atividade;
                - done - Marca uma atividade como finalizada;
                - list - Lista todas as atividades;
                - remove - Remove task;
                - show - Mostra informações da atividade;
            
            Flags:
                [list]:
                    `--page`: Flag utilizada para realizar a troca de paginas no retorno de consulta de atividades.

            Command Args: 
                [add]:
                    title - Titulo da atividade indicado por aspas duplas;
                    description - Descrição da atividade indicado por aspas duplas;

                [done]: 
                    id - Número identificador da atividade a ser concluida;

                [remove]: 
                    id - Número identificador da atividade a ser removida;

                [show]: 
                    id - Número identificador da atividade a ser exibida;
        """);
    }
}
