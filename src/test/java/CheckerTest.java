import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckerTest {

    private final Checker checker = new Checker();

    @Test
    public void testUrlReturnsSuccess() {
        Result result = checker.check("https://google.com");
        assertTrue(result.isSuccess());
        assertTrue(result.getStatusCode() >= 200 && result.getStatusCode() < 400);
    }

    @Test
    public void testInvalidUrlReturnsError() {
        Result result = checker.check("not-a-real-url");
        assertFalse(result.isSuccess());
        assertEquals("invalid url", result.getError());
    }

    @Test
    public void testUnknownHostReturnsError() {
        Result result = checker.check("https://this-domain-does-not-exist-99999.com");
        assertFalse(result.isSuccess());
        assertTrue(result.getError().equals("unknown host")
                || result.getError().equals("could not connect"));
    }

    @Test
    public void testResponseTimeIsPositive() {
        Result result = checker.check("https://google.com");
        assertTrue(result.getResponseTimeMs() > 0);
    }
}