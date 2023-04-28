package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Length;


@SpringBootTest
public class ConversorServiceWithBigNumbersTest {

    @Autowired
    ConversorService service;


    @Test
    public void serviceShouldBeAbleTOHandleBigNumbersWithoutErrors() {
        assertEquals(1234567.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("12345.67", "m", "cm"), new Length()).getValue());
        assertEquals(1000.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("10.0", "m", "cm"), new Length()).getValue());
        assertEquals(1000000.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("10000.0", "m", "cm"), new Length()).getValue());
        assertEquals(1000000000.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("10000000.0", "m", "cm"), new Length()).getValue());
        assertEquals(1000000000000.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("10000000000.0", "m", "cm"), new Length()).getValue());
        assertEquals(1000000000000000.0, service.formatUnitDAOAndConvertToUnit(new UnitDAO("10000000000000.0", "m", "cm"), new Length()).getValue());
        assertEquals(Math.pow(10, 8), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+6", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 11), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+9", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 14), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+12", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 17), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+15", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 20), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+18", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 23), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+21", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 26), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+24", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
        assertEquals(Math.pow(10, 32), service.formatUnitDAOAndConvertToUnit(new UnitDAO("1e+30", "m", "cm"), new Length()).getValue(),MetricTest.tolerance);
    }
    
}
