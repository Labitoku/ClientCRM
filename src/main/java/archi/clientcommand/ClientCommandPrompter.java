package archi.clientcommand;

import archi.clienthttp.HttpClientCRM;
import archi.clientqueries.ClientQuery;
import archi.leads.UserLeadDto;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

public class ClientCommandPrompter
{
    public static String getPrompt(ClientCommand cmd, ClientQuery query)
    {
        String prompt = "";
        switch(cmd)
        {
            case NONE:
                prompt = defaultIncorrectCommandPrompt();
                break;
            case HELP:
                prompt = helpPrompt();
                break;
            case QUIT:
                break;
            case SELECT_CLIENT:
                ArrayList<UserLeadDto> foundLeads = getUserWithQuery(query);
                prompt = leadsPrompt(foundLeads);
                break;
/*            case SELECT_ALL_CLIENTS:
                HttpClientCRM.getInstance().getAllUsersInfo();
                break;
            case SELECT_INTERNAL_CLIENTS:
                break;
            case SELECT_SALESFORCE_CLIENTS:
                break;*/
            default:
                prompt = defaultIncorrectCommandPrompt();
                break;
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

    private static String leadsPrompt(ArrayList<UserLeadDto> leads)
    {
        String retStr = "Clients trouvés :\n";

        for(UserLeadDto user : leads)
        {
            retStr += "User : " + user.getFirstName() + " " + user.getLastName() +"\n";
        }

        return retStr;
    }

    private static ArrayList<UserLeadDto> getUserWithQuery(ClientQuery query)
    {
        ArrayList<UserLeadDto> users = new ArrayList<UserLeadDto>();
        UserLeadDto u1 = new UserLeadDto("Loulou1", "EuEuv1", 1000.0, "0707070707", "Rue de loulou", "49LOULOU", "LoulouCity", "Loulouland", "2000:12:08", "Loulou Corp", "Maine-et-Loulou");
        UserLeadDto u2 = new UserLeadDto("Loulou2", "EuEuv2", 10000.0, "0707070708", "Rue de loulou", "49LOULOU", "LoulouCity", "Loulouland", "2000:12:07", "Loulou Corp", "Maine-et-Loulou");
        UserLeadDto u3 = new UserLeadDto("Loulou3", "EuEuv3", 100000.0, "0707070709", "Rue de loulou", "49LOULOU", "LoulouCity", "Loulouland", "2000:12:06", "Loulou Corp", "Maine-et-Loulou");

        UserLeadDto u4 = new UserLeadDto("Loulou1", "EuEuv1", 1000.0, "0707070707", "Rue de loulou", "49LOULOU", "LoulouCity", "Loulouland", "2000:12:08", "Loulou Corp", "Maine-et-Loulou");

        if(query.content().equals("single"))
        {
            users.add(u1);
        }
        else if(query.content().equals("multiple"))
        {
            users.add(u1);
            users.add(u2);
            users.add(u3);
        }

        return users;
    }

}
