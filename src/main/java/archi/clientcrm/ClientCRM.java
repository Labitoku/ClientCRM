package archi.clientcrm;
import archi.clientcommand.ClientCommand;
import archi.clientcommand.ClientCommandPrompter;
import archi.clientqueries.ClientQuery;
import archi.clientqueries.QueryType;
import archi.clienthttp.HttpClientCRM;

import java.util.Scanner;

public class ClientCRM {
    public static void main(String[] args) {
        String strCommand = "";

        ClientCommand clientCommand = ClientCommand.NONE;
        String query = "";

        Scanner clientScanner = new Scanner(System.in);
        System.out.println("Bienvenue dans ClientCRMinho. Veuillez entrer une requête pour le CRM. Pour accéder à l'aide, tapez '-h', ou 'help'");

        HttpClientCRM http;

        while(clientCommand != ClientCommand.QUIT)
        {
            System.out.print("ClientCRM > ");
            strCommand = clientScanner.nextLine();

            String[] strCommandSplit = strCommand.split(" ");
            ClientQuery cq = null;

            QueryType type = QueryType.NONE;
            String queryContent = "";
            switch(strCommandSplit[0])
            {
                case "help":
                case "-h":
                    clientCommand = ClientCommand.HELP;
                    break;
                case "quit":
                case "-q":
                    clientCommand = ClientCommand.QUIT;
                    break;
                case "select":
                    clientCommand = ClientCommand.SELECT_CLIENT;
                    queryContent = strCommandSplit[1];
                    if(queryContent.equals("all"))
                        type = QueryType.SELECT_ALL;
                    else
                        type = QueryType.NONE;
                    break;
                case "add":
                    type = QueryType.ADD;
                    break;
                case "delete":
                    type = QueryType.DELETE;
                    break;
            }
            if(type != QueryType.NONE)
            {
                cq = new ClientQuery(type, queryContent);
                cq.execute();
            }

            System.out.println(ClientCommandPrompter.getPrompt(clientCommand, cq));
        }
    }
}