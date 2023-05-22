package dalosto.engineering.unitconversion;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;


public class UnitconversionApplicationTest {

    @Test
    public void mainClassShouldntThrowError() {
        assertDoesNotThrow(() -> UnitconversionApplication.main(new String[] {}));
    }


    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new UnitconversionApplication());
        assertNotNull(UnitconversionApplication.class);
    }

}
