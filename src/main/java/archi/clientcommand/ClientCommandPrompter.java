package archi.clientcommand;

import archi.clienthttp.HttpClientCRM;
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
            prompt = showSelectionPrompt(query);
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
                "\t\t'-s <query>' ou 'select <query>' : permet de chercher un client dans le CRM\n" +
                "\t\t'-sa' ou 'select-all' : permet de chercher tous les clients\n" +
                "\t\t'-ss <query>' ou 'select-salesforce <query>' : cherche un client dans le CRM Salesforce\n" +
                "\t\t'-si <query>' ou 'select-internal <query>' : cherche un client dans le CRM interne";
    }

    private static String quitPrompt()
    {
        return "Merci d'avoir utilisé ClientCRMinho, à bientôt !";
    }

    private static String showSelectionPrompt(ClientQuery cq)
    {
        ArrayList<UserLeadDto> leads = cq.execute();
        String selection = "==============================\nCLIENT SELECTION\n==============================";
        for(UserLeadDto uld : leads)
        {
            selection += "------------------------------\n" + uld.toString() + "\n------------------------------";
        }
        return selection;
    }

}
