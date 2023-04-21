package dalosto.engineering.unitconversion.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestConversorServiceWithBigNumbers {

    @Test
    void serviceShouldBeAbleTOHandleBigNumbersWithoutErrors() {
        // CREATED ISSUE to TEST THIS USING EXPONENTIAL.
        // assertEquals("9999999999999999999", conversorService.formatUnitDAOAndConvertToUnit(new UnitDAO("9999999999999999999", "N", "N"), new Force()));
    }
    
}
