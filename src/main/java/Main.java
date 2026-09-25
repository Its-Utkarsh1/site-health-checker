
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        List<String> urls = Files.readAllLines(Path.of("urls.txt"));

        Checker checker = new Checker();

        for(String url : urls){
            Result result = checker.check(url);
            System.out.println(result);
        }
    }
}