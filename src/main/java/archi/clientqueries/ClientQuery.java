package archi.clientqueries;

import archi.clienthttp.HttpClientCRM;

import java.util.ArrayList;

public class ClientQuery
{
    QueryType query;
    String queryContent;
    TypeCRM crm;

    public ClientQuery(QueryType t, String qc)
    {
        query = t;
        queryContent = qc;
        crm = TypeCRM.SALESFORCE;
    }


    public void execute()
    {
        HttpClientCRM http = new HttpClientCRM(crm.toString() + query.toString());
        //ArrayList<String> clients = http.getUserInfo(queryContent);
        ArrayList<String> clients = http.getAllUsersInfo();

    }
}
