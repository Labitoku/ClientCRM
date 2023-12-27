package archi.leads;

import com.fasterxml.jackson.databind.JsonNode;

public class UserLeadDto
{
    private String firstName;
    private String lastName;
    private double annualRevenue;
    private String phone;
    private String street;
    private String postalCode;
    private String city;
    private String country;
    private String creationDate;
    private String company;
    private String state;
    private GeographicPointDto geographicPointDto;

    public UserLeadDto(JsonNode node)
    {
        String firstName = node.get("firstName").asText();
        String lastName = node.get("lastName").asText();
        double annualRevenue = node.get("annualRevenue").asDouble();
        String phone = node.get("phone").asText();

        String street = node.get("street").asText();
        String postalCode = node.get("postalCode").asText();
        String city = node.get("city").asText();
        String country = node.get("country").asText();

        String creationDate = node.get("creationDate").asText();
        String company = node.get("company").asText();

        String state = node.get("state").asText();


        JsonNode geographicPoint = node.get("geographicPointDto");
        double longitude =  geographicPoint.get("longitude").asDouble();
        double latitude =  geographicPoint.get("latitude").asDouble();

        init(firstName, lastName, annualRevenue, phone, street, postalCode, city, country, creationDate, company, state, longitude, latitude);
    }

    public UserLeadDto(String firstName, String lastName, double annualRevenue, String phone, String street, String postalCode,
                          String city, String country, String creationDate, String company, String state, double longitude, double latitude) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualRevenue = annualRevenue;
        this.phone = phone;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.creationDate = creationDate;
        this.company = company;
        this.state = state;
        this.geographicPointDto = new GeographicPointDto(longitude, latitude);
    }

    private void init(String firstName, String lastName, double annualRevenue, String phone, String street, String postalCode,
                      String city, String country, String creationDate, String company, String state, double longitude, double latitude)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.annualRevenue = annualRevenue;
        this.phone = phone;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.creationDate = creationDate;
        this.company = company;
        this.state = state;
        this.geographicPointDto = new GeographicPointDto(longitude, latitude);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName()
    {
        return getFirstName() + " " + getLastName();
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public GeographicPointDto getGeographicPointDto() {
        return geographicPointDto;
    }

    public void setGeographicPointDto(GeographicPointDto geographicPointDto) {
        this.geographicPointDto = geographicPointDto;
    }

    @Override
    public String toString()
    {
        String str = "";

        str += "Client : " + getFullName();
        str += "\nIs working for : " + getCompany();
        str += "\nPhone coordinates : " + getPhone();
        str += "\nAddress : " + getStreet() + ", " + getPostalCode() + " " + getCity() + ", " + getState() + ", " + getCountry() + "(" + getGeographicPointDto().toString() +")";
        str += "\nEarns : " + getAnnualRevenue();
        str += "\nAccount creation : " + getCreationDate();

        return str;
    }
}
