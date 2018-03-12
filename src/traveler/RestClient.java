package traveler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 *
 * @author ebasso
 */
public class RestClient {
    
       public String fetchContent(String uri) throws IOException {

        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        //System.out.printf("\nresponseCode --> %d", responseCode);
        if (responseCode == OK) {
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            return response.toString();
        }

        return null;
    }

    
    public String fetchContent(String uri, String username, String password) throws IOException {
        final int OK = 200;
        URL url = new URL(uri);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        String userpassword = username + ":" + password;
        String base64encodedString = Base64.getEncoder().encodeToString(userpassword.getBytes("utf-8"));

        connection.setRequestProperty("Authorization", "Basic "  + base64encodedString);

        int responseCode = connection.getResponseCode();
        //System.out.printf("\nresponseCode --> %d", responseCode);
        if (responseCode == OK) {
            StringBuffer response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            return response.toString();
        }

        return null;
    }
}
