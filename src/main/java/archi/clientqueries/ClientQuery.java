package archi.clientqueries;

import archi.clienthttp.HttpClientCRM;
import archi.leads.UserLeadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.catalina.User;

import javax.management.Query;
import java.util.ArrayList;

public class ClientQuery
{
    QueryType queryType;
    String[] queryContent;

    public ClientQuery(QueryType t, String[] qc)
    {
        queryType = t;
        queryContent = qc;
    }

    public String[] getQueryContent() { return queryContent; }
    public void setQueryContent(String[] s) { queryContent = s; }

    public QueryType getQueryType() { return queryType; }
    public void setQueryType(QueryType qt) { queryType = qt; }

    public ArrayList<UserLeadDto> execute()
    {
        ArrayList<UserLeadDto> users = new ArrayList<UserLeadDto>();
        switch(queryType)
        {
            case SELECT_ALL:
                users = HttpClientCRM.getInstance().getAllUsersInfo();
                break;

            case SELECT_BY_REVENUE:
                double lowRevenue = Double.valueOf(queryContent[0]);
                double highRevenue = Double.valueOf(queryContent[1]);
                String state = queryContent[2];
                try {
                    users = HttpClientCRM.getInstance().getUserInfoByRevenue(lowRevenue, highRevenue, state);
                }
                catch (JsonProcessingException e)
                {
                    throw new RuntimeException(e);
                }
                break;

            case SELECT_BY_DATE:
                String startDate = queryContent[0];
                String endDate = queryContent[1];
                try {
                    users = HttpClientCRM.getInstance().getUserInfoByDate(startDate, endDate);
                }
                catch (JsonProcessingException e)
                {
                    throw new RuntimeException(e);
                }
                break;


            case ADD:
                try {
                    users.add(HttpClientCRM.getInstance().AddUser(queryContent));
                }
                catch (JsonProcessingException e)
                {
                    throw new RuntimeException(e);
                }
                break;

            case DELETE:
                System.out.println("Marche pas sur Salesforce mais lets go");
                break;
        }

        return users;
    }
}
