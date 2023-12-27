package archi.leads;

import com.fasterxml.jackson.databind.JsonNode;

public class GeographicPointDto {

    private double longitude;
    private double latitude;

    public GeographicPointDto(double lon, double lat) {
        longitude = lon;
        latitude = lat;
    }

    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString()
    {
        return longitude + ", " + latitude;
    }

}
