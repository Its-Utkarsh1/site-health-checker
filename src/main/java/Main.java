import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> urls = Files.readAllLines(Path.of("urls.txt"));

        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        for(String url : urls){
            if (url.isBlank()) continue;

            try{
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
            }catch(Exception e){
                System.out.println(url + " Failed "+ e.getMessage());
            }

        }
    }
}