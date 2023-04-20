package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Length;


@SpringBootTest
public class TestConversorService {

    @Autowired
    private ConversorService conversorService;

    @Test
    void serviceShouldThrowExceptionIfNullValueIsPassed() {
        UnitDAO unitDAO = new UnitDAO(null, "mock", "mock");
        assertThrows(ParameterException.class, () -> conversorService.createUnitFromUnitDAO(unitDAO, new Length()));
    }


    @Test
    void serviceShouldThrowExceptionIfNullTypeIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", null, "mock");
        assertThrows(ParameterException.class, () -> conversorService.createUnitFromUnitDAO(unitDAO, new Length()));
    }


    @Test
    void serviceShouldThrowExceptionIfNullFormulaIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", "mock", null);
        assertThrows(ParameterException.class, () -> conversorService.createUnitFromUnitDAO(unitDAO, new Length()));
    }
}
