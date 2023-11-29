package archi.clienthttp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

public class ClientHttp {

    public static List<String> getUserInfo(String username) {
        // Spécifier l'URL du serveur
        String url = "https://votre-serveur.com/api/getUserInfo";

        // Créer un objet RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Créer les entêtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Créer les paramètres de la requête
        String jsonInputString = "{\"username\": \"" + username + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonInputString, headers);

        // Envoyer la requête HTTP POST
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                String.class);

        // Obtenir la réponse
        String responseString = responseEntity.getBody();

        // Analyser la réponse JSON et extraire les informations sur l'utilisateur
        List<String> userInfo = parseUserInfo(responseString);

        return userInfo;
    }

    private static List<String> parseUserInfo(String responseString) {
        // Implémenter la logique pour extraire les informations sur l'utilisateur
        // à partir de la chaîne de caractères JSON de la réponse.
        // Vous pouvez utiliser une bibliothèque JSON comme Jackson ou Gson pour simplifier cette tâche.

        // Exemple simple :
        List<String> userInfo = List.of("Nom: John Doe", "Pays: France", "Ville: Paris");

        return userInfo;
    }
}
