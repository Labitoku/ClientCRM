package archi.clienthttp;

import archi.leads.UserLeadDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

public class HttpClientCRM {

    String urlCRM;

    public static HttpClientCRM instance;

    public static HttpClientCRM getInstance()
    {
        if(instance == null)
        {
            instance = new HttpClientCRM();
        }
        return instance;
    }

    private HttpClientCRM()
    {
        urlCRM = "http://localhost:8081/api/";
    }

    public ArrayList<UserLeadDto> getAllUsersInfo()
    {
        ArrayList<UserLeadDto> users = new ArrayList<UserLeadDto>();
        String urlAllUsers = urlCRM + "allUsers";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlAllUsers))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());

            if(jsonNode.isArray()) {
                for(JsonNode node : jsonNode)
                {
                    UserLeadDto uld = new UserLeadDto(node);
                    users.add(uld);

                }
            }

            //Object jsonObject = objectMapper.readValue(response.body(), Object.class);
            //String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            //System.out.println("JSON structure : ");
            //System.out.println(formattedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<UserLeadDto> getUserInfoByRevenue(double lowRevenue, double highRevenue, String state) throws JsonProcessingException
    {
        ArrayList<UserLeadDto> users = new ArrayList<UserLeadDto>();
        String urlGetLeadsByRevenue = urlCRM + "getLeads";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode queryJsonNode = objectMapper.createObjectNode()
                .put("lowAnnualRevenue", ""+lowRevenue)
                .put("highAnnualRevenue", ""+highRevenue)
                .put("state", state);

        // Convertissez l'objet JsonNode en une chaîne JSON
        String requestBody = objectMapper.writeValueAsString(queryJsonNode);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlGetLeadsByRevenue))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = objectMapper.readTree(response.body());

            if(jsonNode.isArray()) {
                for(JsonNode node : jsonNode)
                {
                    UserLeadDto uld = new UserLeadDto(node);
                    users.add(uld);

                }
            }

            //Object jsonObject = objectMapper.readValue(response.body(), Object.class);
            //String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            //System.out.println("JSON structure : ");
            //System.out.println(formattedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<UserLeadDto> getUserInfoByDate(String startDate, String endDate) throws JsonProcessingException
    {
        ArrayList<UserLeadDto> users = new ArrayList<UserLeadDto>();
        String urlGetLeadsByRevenue = urlCRM + "getLeadsByDate";


        String formattedStartDate = convertToISO8601(startDate) + "T00:00:00.000+0000";
        String formattedEndDate = convertToISO8601(endDate) + "T00:00:00.000+0000";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode queryJsonNode = objectMapper.createObjectNode()
                .put("startDate", formattedStartDate)
                .put("endDate", formattedEndDate);

        // Convertissez l'objet JsonNode en une chaîne JSON
        String requestBody = objectMapper.writeValueAsString(queryJsonNode);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlGetLeadsByRevenue))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonNode jsonNode = objectMapper.readTree(response.body());

            if(jsonNode.isArray()) {
                for(JsonNode node : jsonNode)
                {
                    UserLeadDto uld = new UserLeadDto(node);
                    users.add(uld);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // Méthode pour convertir la date au format ISO 8601
    private static String convertToISO8601(String inputDate) {
        // Définir le format de la date en entrée
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parser la date en tant qu'objet LocalDate
        LocalDate localDate = LocalDate.parse(inputDate, inputFormatter);

        // Définir le format de sortie (ISO 8601)
        DateTimeFormatter outputFormatter = DateTimeFormatter.ISO_DATE;

        // Formater la date en tant que chaîne ISO 8601
        return localDate.format(outputFormatter);
    }

}
