package dalosto.engineering.unitconversion;
import static org.junit.jupiter.api.Assertions.assertEquals;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.units.UnitFormula;


public class MetricTest {

    public static double tolerance = Math.pow(10, -9);


    public static void assertEquivalentUnit(double fromValue, UnitType fromType, 
                                            double toValue,   UnitType toType,
                                            UnitFormula formula) {
        assertEquals(toValue, formula.buildUnitIntoAnotherType(new Unit(fromValue, fromType), toType).getValue(), MetricTest.tolerance);
        assertEqualForSmallNumber(fromValue, fromType, toValue, toType, formula);
    }


    private static void assertEqualForSmallNumber(double fromValue, UnitType fromType, 
                                                 double toValue,    UnitType toType,
                                                 UnitFormula formula) {
        if (toValue != 0) {
            assertEquals(1, toValue/formula.buildUnitIntoAnotherType(new Unit(fromValue, fromType), toType).getValue(), MetricTest.tolerance);
        }
    }


    public static void assertEquavalentInSI(double expected, Unit actual, UnitFormula unitFormula) {
        assertEquivalentUnit(actual.getValue(), actual.getType(), expected, unitFormula.getSITypeOfThisCategory(), unitFormula);
    }

}
