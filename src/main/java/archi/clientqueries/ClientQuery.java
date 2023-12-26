package archi.clientqueries;

import archi.clienthttp.HttpClientCRM;

import java.util.ArrayList;

public class ClientQuery
{
    QueryType query;
    String queryContent;

    public ClientQuery(QueryType t, String qc)
    {
        query = t;
        queryContent = qc;
    }

    public String content() { return queryContent; }


    public void execute()
    {
        switch(query)
        {
            case SELECT_ALL:
                HttpClientCRM.getInstance().getAllUsersInfo();
                break;
        }
        //ArrayList<String> clients = http.getUserInfo(queryContent);
        //ArrayList<String> clients = http.getAllUsersInfo();

    }
}
