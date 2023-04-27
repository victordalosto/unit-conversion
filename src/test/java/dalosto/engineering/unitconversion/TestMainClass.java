package dalosto.engineering.unitconversion;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMainClass {

    @Test
    public void mainClassShouldBeCalledWithtoutErrors() {
        assertDoesNotThrow(() -> UnitconversionApplication.main(new String[] {}));
    }

}
