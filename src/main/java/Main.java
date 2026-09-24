import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Exception {
        String url = "https://facebook.com";

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        long start = System.currentTimeMillis();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        long ms = System.currentTimeMillis() - start;
        System.out.println(url + "  " + response.statusCode() + "  " + ms + "ms");
    }
}