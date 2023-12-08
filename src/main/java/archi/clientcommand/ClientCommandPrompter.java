package archi.clientcommand;

import archi.clientqueries.ClientQuery;

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
                break;
            case SELECT_ALL_CLIENTS:
                break;
            case SELECT_INTERNAL_CLIENTS:
                break;
            case SELECT_SALESFORCE_CLIENTS:
                break;
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

}
