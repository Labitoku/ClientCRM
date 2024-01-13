package archi.clientcommand;

import archi.clientqueries.ClientQuery;
import archi.clientqueries.QueryType;
import archi.leads.UserLeadDto;

import java.util.ArrayList;

public class ClientCommandPrompter
{
    public static String getPrompt(ClientQuery query)
    {
        String prompt = "";

        QueryType type = query.getQueryType();

        if(type == QueryType.NONE)
        {
            prompt = defaultIncorrectCommandPrompt();
        }

        else if(type == QueryType.HELP)
        {
            prompt = helpPrompt();
        }

        else if(type == QueryType.QUIT)
        {
            prompt = quitPrompt();
        }

        else if(type == QueryType.SELECT_ALL || type == QueryType.SELECT_BY_REVENUE || type == QueryType.SELECT_BY_DATE)
        {
            prompt = selectionPrompt(query);
        }

        else if(type == QueryType.ADD)
        {
            prompt = addPrompt(query);
        }

        else if(type == QueryType.MERGE)
        {
            prompt = mergePrompt(query);
        }

        else
        {
            prompt = defaultIncorrectCommandPrompt();
        }


        return prompt;
    }

    private static String defaultIncorrectCommandPrompt()
    {
        return "Veuillez entrer une commande valide. Entrez '-h' ou 'help' pour accéder à l'aide.";
    }

    private static String helpPrompt()
    {
        return "Bienvenue dans ClientCRMinho. Depuis cette console, vous pouvez faire" +
                "des requêtes pour accéder aux clients existant dans le CRM." +
                "\t\t'-h' ou 'help' : permet d'afficher ce prompt.\n" +
                "\t\t'-q' ou 'quit' : permet de quitter ClientCRMinho\n" +
                "\t\t'select all' : permet de chercher tous les clients dans le CRM\n" +
                "\t\t'selectrev lowrev highrev state' : permet de chercher tous les clients dans le CRM ayant des revenus\n" +
                "\t\tsupérieurs à lowrev et inférieurs à highrev, se situant dans l'état / province state\n" +
                "\t\t'selectdate startdate enddate' : permet de chercher tous les clients dans le CRM ayant été ajoutés\n" +
                "\t\taprès startdate et avant enddate. \n" +
                "\t\t'merge' : permet d'ajouter les leads de Salesforce dans le CRM interne" +
                "\t\tNOTE : Le format de date a utiliser est le suivant : yyyy-MM-dd\n";
    }

    private static String quitPrompt()
    {
        return "Merci d'avoir utilisé ClientCRMinho, à bientôt !";
    }

    private static String selectionPrompt(ClientQuery cq)
    {
        ArrayList<UserLeadDto> leads = cq.execute();
        String selection = "==============================\nCLIENT SELECTION\n==============================\n";
        for(UserLeadDto uld : leads)
        {
            selection += "------------------------------\n" + uld.toString() + "\n------------------------------";
        }
        return selection;
    }

    private static String addPrompt(ClientQuery cq)
    {
        ArrayList<UserLeadDto> leads = cq.execute();
        String addPrompt = "==============================\nCLIENT INSERTION\n==============================\n";
        if(leads.get(0) == null)
        {
            addPrompt += "Something went wrong while inserting the lead...";
        }
        else
        {
            addPrompt += "------------------------------\n" + leads.get(0).toString() + "\n------------------------------";
        }

        return addPrompt;
    }

    private static String mergePrompt(ClientQuery cq)
    {
        ArrayList<UserLeadDto> leads = cq.execute();
        String addPrompt = "==============================\nCLIENT MERGE\n==============================\n";
        if(leads.get(0) == null)
        {
            addPrompt += "Something went wrong while inserting the lead...";
        }
        else
        {
            addPrompt += "------------------------------\n" + leads.get(0).toString() + "\n------------------------------";
        }

        return addPrompt;
    }

}
