package archi.clienthttp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public ArrayList<String> getAllUsersInfo()
    {
        String urlAllUsers = urlCRM + "allUsers";
        ArrayList<String> allUsers = new ArrayList<String>();

        // Créer un objet RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Créer les entêtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Créer les paramètres de la requête
        String jsonInputString = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlAllUsers))
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("All Users : " + response.body());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            Object jsonObject = objectMapper.readValue(response.body(), Object.class);

            String formattedJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

            System.out.println("JSON structuré : ");
            System.out.println(formattedJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Envoyer la requête HTTP POST
        /*ResponseEntity<String> responseEntity = restTemplate.exchange(
                urlCRM,
                HttpMethod.GET,
                requestEntity,
                String.class);*/

        //String responseString = responseEntity.getBody();
        //allUsers = parseUserInfo(responseString);

        //allUsers.add(responseEntity.getBody());


        return allUsers;
    }

    public ArrayList<String> getUserInfo(String username) {
        // Spécifier l'URL du serveur


        // Créer un objet RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Créer les entêtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Créer les paramètres de la requête
        String jsonInputString = "";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        // Envoyer la requête HTTP POST
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                urlCRM,
                HttpMethod.GET,
                requestEntity,
                String.class);

        // Obtenir la réponse
        String responseString = responseEntity.getBody();

        // Analyser la réponse JSON et extraire les informations sur l'utilisateur
        ArrayList<String> userInfo = parseUserInfo(responseString);

        return userInfo;
    }

    private static ArrayList<String> parseUserInfo(String responseString) {
        // Implémenter la logique pour extraire les informations sur l'utilisateur
        // à partir de la chaîne de caractères JSON de la réponse.
        // Vous pouvez utiliser une bibliothèque JSON comme Jackson ou Gson pour simplifier cette tâche.

        // Exemple simple :
        ArrayList<String> userInfo = new ArrayList<String>(Arrays.asList("Nom: John Doe", "Pays: France", "Ville: Paris"));

        return userInfo;
    }
}
