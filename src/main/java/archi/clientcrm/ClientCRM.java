package archi.clientcrm;
import archi.clientcommand.ClientCommandPrompter;
import archi.clientqueries.ClientQuery;
import archi.clientqueries.QueryType;
import archi.clienthttp.HttpClientCRM;

import java.util.Scanner;

public class ClientCRM {
    public static void main(String[] args) {
        String strCommand = "";

        String query = "";

        Scanner clientScanner = new Scanner(System.in);
        System.out.println("Bienvenue dans ClientCRMinho. Veuillez entrer une requête pour le CRM. Pour accéder à l'aide, tapez '-h', ou 'help'");

        HttpClientCRM http;
        ClientQuery cq = new ClientQuery(QueryType.NONE, "");

        while(cq.getQueryType() != QueryType.QUIT)
        {
            System.out.print("ClientCRM > ");
            strCommand = clientScanner.nextLine();

            String[] strCommandSplit = strCommand.split(" ");

            QueryType type = QueryType.NONE;
            String queryContent = "";
            switch(strCommandSplit[0])
            {
                case "help":
                case "-h":
                    type = QueryType.HELP;
                    break;

                case "quit":
                case "-q":
                    type = QueryType.QUIT;
                    break;

                case "select":
                    try {
                        queryContent = strCommandSplit[1];
                        if (queryContent.equals("all"))
                        {
                            type = QueryType.SELECT_ALL;
                        }
                        else
                        {
                            type = QueryType.NONE;
                        }
                    }
                    catch(Exception e)
                    {
                        type = QueryType.NONE;
                    }

                    break;

                case"selectrev":
                    try {
                        if (!Double.valueOf(strCommandSplit[1]).isNaN() && !Double.valueOf(strCommandSplit[2]).isNaN() && strCommandSplit.length == 4) {
                            queryContent = strCommandSplit[1] + " " + strCommandSplit[2] + " " + strCommandSplit[3];
                            type = QueryType.SELECT_BY_REVENUE;
                        }
                    }
                    catch(Exception e)
                    {
                        type = QueryType.NONE;
                    }
                    break;

                case"selectdate":
                    try {
                        if (strCommandSplit.length == 3)
                        {
                            queryContent = strCommandSplit[1] + " " + strCommandSplit[2];
                            type = QueryType.SELECT_BY_DATE;
                        }
                    }
                    catch(Exception e)
                    {
                        type = QueryType.NONE;
                    }
                    break;

                case "add":
                    try
                    {
                        type = QueryType.ADD;
                    }
                    catch (Exception e)
                    {
                        type = QueryType.NONE;
                    }
                    break;

                case "delete":
                    try {
                        type = QueryType.DELETE;

                    }
                    catch(Exception e)
                    {
                        type = QueryType.NONE;
                    }
                    break;
            }
            if(type != QueryType.NONE)
            {
                cq = new ClientQuery(type, queryContent);
            }

            System.out.println(ClientCommandPrompter.getPrompt(cq));
        }
    }
}