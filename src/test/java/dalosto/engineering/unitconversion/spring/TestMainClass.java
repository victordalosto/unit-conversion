package dalosto.engineering.unitconversion.spring;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.UnitconversionApplication;


@SpringBootTest
public class TestMainClass {

    @Test
    public void mainClassShouldBeCalledWithtoutErrors() {
        assertDoesNotThrow(() -> UnitconversionApplication.main(new String[] {}));
    }

}
