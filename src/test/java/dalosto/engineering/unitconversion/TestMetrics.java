package dalosto.engineering.unitconversion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


public class TestMetrics {

    public static double tolerance = Math.pow(10, -10);


    public static void assertEquavalentInSI(double expected, Unit actual, UnitFormula unitFormula) {
        assertEquals(expected, unitFormula.buildUnitToSI(actual).getValue(), TestMetrics.tolerance);
    }


    public static void assertEquivalentUnit(double fromValue, UnitType from, Double toValue, UnitType to,
            UnitFormula unitFormula) {
        assertEquals(toValue, unitFormula.buildUnitIntoAnotherType(new Unit(fromValue, from), to).getValue(),
                TestMetrics.tolerance);
    }

}
