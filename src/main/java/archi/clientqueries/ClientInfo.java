package archi.clientqueries;

public class ClientInfo {

    String name;
    String city;
    String country;

    public ClientInfo(String n, String ci, String co)
    {
        name = n;
        city = ci;
        country = co;
    }

    @Override
    public String toString()
    {
        return "User : " + name
                + "\nCity : " + city
                + "\nCountry : " + country;
    }

}
