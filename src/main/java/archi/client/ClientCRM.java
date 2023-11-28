package archi.client;
import java.util.Scanner;

public class ClientCRM {
    public static void main(String[] args) {
        String strCommand = "";

        ClientCommand clientCommand = ClientCommand.NONE;
        String query = "";

        Scanner clientScanner = new Scanner(System.in);
        System.out.println("Bienvenue dans ClientCRMinho. Veuillez entrer une requête pour le CRM. Pour accéder à l'aide, tapez '-h', ou 'help'");

        while(clientCommand != ClientCommand.QUIT)
        {
            System.out.print("ClientCRM > ");
            strCommand = clientScanner.nextLine();

            String[] strCommandSplit = strCommand.split(" ");
            ClientQuery cq = null;
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
                case "search":
                case "-s":
                    clientCommand = ClientCommand.SEARCH_FOR_CLIENT;
                    break;
                case "search-all":
                case "-sa":
                    clientCommand = ClientCommand.SEARCH_FOR_ALL_CLIENTS;
                    break;
                case "search-salesforce":
                case "-ss":
                    clientCommand = ClientCommand.SEARCH_FOR_SALESFORCE_CLIENTS;
                    break;
                case "search-internal":
                case "-si":
                    clientCommand = ClientCommand.SEARCH_FOR_INTERNAL_CLIENTS;
                    break;
            }
            if(strCommandSplit.length >= 3)
            {
                QueryType type = QueryType.NONE;
                String queryContent = "";
                switch(strCommandSplit[1].toLowerCase())
                {
                    case "select":
                        type = QueryType.SELECT;
                        break;
                    case "add":
                        type = QueryType.ADD;
                        break;
                    case "delete":
                        type = QueryType.DELETE;
                        break;
                }
                for(int i = 2 ; i < strCommandSplit.length ; i++)
                {
                    queryContent += strCommandSplit[i] + " ";
                }
                cq = new ClientQuery(type, queryContent);
                cq.displayQuery();
            }
            System.out.println(ClientCommandPrompter.getPrompt(clientCommand, cq));
        }
    }
}