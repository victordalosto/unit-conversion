package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Length;


@SpringBootTest
public class ConversorServiceWithSmallNumbersTest {

    @Autowired
    ConversorService service;

    @Test
    public void shouldBeAbleToConvertUnitDaoWithSmallValue() {
        assertEquals(1234567.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("12345.67", "m", "cm"), new Length()).getValue());
        assertEquals(0.0001, service.formatUnitDAOAndConvertToUnit(new UnitDAO("0.000001", "m", "cm"), new Length()).getValue());
        assertEquals(0.000000001, service.formatUnitDAOAndConvertToUnit(new UnitDAO("0.00000000001", "m", "cm"), new Length()).getValue());
        assertEquals(Math.pow(10, -7), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e-9", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, -9), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e-11", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, -11), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e-13", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, -13), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e-15", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, -18), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e-20", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
    }
    
}
