package dalosto.engineering.unitconversion;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class UnitconversionApplicationTest {

    @Test
    public void mainClassShouldntThrowError() {
        assertDoesNotThrow(() -> UnitconversionApplication.main(new String[] {}));
    }

}
