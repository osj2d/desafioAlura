
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;




public class Main {
    public static void main(String[] args) throws Exception {
        String apikey  = ""; //Cola a apiKey da conta do imdb-api
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://imdb-api.com/en/API/Top250Movies/"+apikey)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String teste = response.body();
        System.out.println(response.body());
        String[] part = teste.split("\\},\\{");
        System.out.println(part.length);




    }
}