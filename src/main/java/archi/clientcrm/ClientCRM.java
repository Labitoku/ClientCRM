package archi.clientcrm;
import archi.clientcommand.ClientCommandPrompter;
import archi.clientqueries.ClientQuery;
import archi.clientqueries.QueryType;
import archi.clienthttp.HttpClientCRM;

import java.util.Scanner;

public class ClientCRM {
    public static void main(String[] args) {
        String strCommand = "";

        Scanner clientScanner = new Scanner(System.in);
        System.out.println("Bienvenue dans ClientCRMinho. Veuillez entrer une requête pour le CRM. Pour accéder à l'aide, tapez '-h', ou 'help'");

        String[] queryContent = new String[1];
        ClientQuery cq = new ClientQuery(QueryType.NONE, queryContent);

        while(cq.getQueryType() != QueryType.QUIT)
        {
            System.out.print("ClientCRM > ");
            strCommand = clientScanner.nextLine();

            String[] strCommandSplit = strCommand.split(" ");

            QueryType type = QueryType.NONE;
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
                        String firstQueryArg = strCommandSplit[1];
                        if (firstQueryArg.equals("all"))
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
                            queryContent = new String[]{ strCommandSplit[1], strCommandSplit[2], strCommandSplit[3] };
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
                            queryContent = new String[] { strCommandSplit[1], strCommandSplit[2] };
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
                        queryContent = new String[10];
                        System.out.println("Lets add the client step by step !\n");

                        System.out.print("First Name > ");
                        String firstName = clientScanner.nextLine();
                        queryContent[0] = firstName;

                        System.out.print("Last Name > ");
                        String lastName = clientScanner.nextLine();
                        queryContent[1] = lastName;

                        System.out.print("Annual Revenue > ");
                        String annualRevenue = clientScanner.nextLine();
                        queryContent[2] = annualRevenue;

                        System.out.print("Phone > ");
                        String phone = clientScanner.nextLine();
                        queryContent[3] = phone;

                        System.out.print("Street > ");
                        String street = clientScanner.nextLine();
                        queryContent[4] = street;

                        System.out.print("Postal Code > ");
                        String postalCode = clientScanner.nextLine();
                        queryContent[5] = postalCode;

                        System.out.print("City > ");
                        String city = clientScanner.nextLine();
                        queryContent[6] = city;

                        System.out.print("Country > ");
                        String country = clientScanner.nextLine();
                        queryContent[7] = country;

                        System.out.print("Company > ");
                        String company = clientScanner.nextLine();
                        queryContent[8] = company;

                        System.out.print("State > ");
                        String state = clientScanner.nextLine();
                        queryContent[9] = state;

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
                case "merge":
                    type = QueryType.MERGE;
            }
            if(type != QueryType.NONE)
            {
                cq = new ClientQuery(type, queryContent);
            }

            System.out.println(ClientCommandPrompter.getPrompt(cq));
        }
    }
}