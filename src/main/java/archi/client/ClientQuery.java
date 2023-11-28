package archi.client;

public class ClientQuery
{
    QueryType type;
    String queryContent;

    public ClientQuery(QueryType t, String qc)
    {
        type = t;
        queryContent = qc;
    }

    public void displayQuery()
    {
        System.out.println(type.toString() + " : " + queryContent);
    }
}
