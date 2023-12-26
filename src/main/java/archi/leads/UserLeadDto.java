package archi.leads;

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
    //private GeographicPointDto geographicPointDto;

    public UserLeadDto(String firstName, String lastName, double annualRevenue, String phone, String street, String postalCode,
                          String city, String country, String creationDate, String company, String state) {
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
        //this.geographicPointDto = new GeographicPointDto(country, city, street, postalCode);
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

    /*public GeographicPointDto getGeographicPointDto() {
        return geographicPointDto;
    }

    public void setGeographicPointDto(GeographicPointDto geographicPointDto) {
        this.geographicPointDto = geographicPointDto;
    }*/
}
