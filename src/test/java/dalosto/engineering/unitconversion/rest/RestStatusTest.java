package dalosto.engineering.unitconversion.rest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class RestStatusTest {

    @Test
    public void testPossibleStatus() {
        String result = RestStatus.possibleStatus();
        String expected = "{ SUCCESS | ERROR | INFO }";
        assertEquals(expected, result);
    }

}
