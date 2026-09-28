import javax.net.ssl.SSLHandshakeException;
import java.net.ConnectException;
import java.net.URI;
import java.net.UnknownHostException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class Checker {

    private final HttpClient client;

    public Checker(){
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public Result check(String url){

        boolean isHttps = url.startsWith("https://");

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
            long end = System.currentTimeMillis() - start;

            return new Result(url, response.statusCode(), end, null,isHttps ? " Valid" : "-");

        } catch (HttpTimeoutException e) {
            return new Result(url,-1, -1, "timeout"," -");
        } catch (ConnectException e) {
            return new Result(url, -1, -1,"could not connect"," -");
        } catch (UnknownHostException e) {
            return new Result(url, -1, -1,"unknown host", " -");
        } catch (IllegalArgumentException e) {
            return new Result(url,-1,-1, "invalid url", " -");
        } catch (SSLHandshakeException e) {
            return new Result(url, -1, -1, "ssl error", " invalid");
        } catch (Exception e) {
            return new Result(url,-1, -1,  e.getClass().getSimpleName()," -");
        }
    }
}
