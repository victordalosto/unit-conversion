package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Length;
import dalosto.engineering.unitconversion.unit.Linear;


@SpringBootTest
public class LinearTest {

    private UnitFormula unitFormula = new Linear();

    @Autowired
    private List<UnitFormula> formulas;



    @Test
    public void SIUnitTypeOfForceShouldBeNVersusMeter() {
        assertEquals(Linear.factory(Force.Types.N, Length.Types.M), unitFormula.getSITypeOfThisCategory());
        assertEquals(Linear.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Linear.factory(Force.Types.KN, Length.Types.MM)).getType().getSITypeOfThisCategory());
        assertEquals(Linear.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Linear.factory(Force.Types.KGF, Length.Types.CM)).getType().getSITypeOfThisCategory());
        assertEquals(Linear.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Linear.factory(Force.Types.POUND, Length.Types.IN)).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(1.0,                unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.N,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 3),    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.KN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 6),    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.MN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 9),    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.GN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 12),   unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.TN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.LB,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.27801385176568125,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.OZ, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4448.2216282509,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.KIP,   Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.GF,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.G,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.KG,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.KGF,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9806.6500286389,    unitFormula.buildUnitToSI(new Unit(1, Linear.factory(Force.Types.T,     Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = 12345.67;
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67, Linear.factory(Force.Types.N, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12.34567, Linear.factory(Force.Types.KN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(0.01234567, Linear.factory(Force.Types.MN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -9), Linear.factory(Force.Types.GN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -12), Linear.factory(Force.Types.TN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Linear.factory(Force.Types.LB, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Linear.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(44406.67226324144, Linear.factory(Force.Types.OZ, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2.77541701645259, Linear.factory(Force.Types.KIP, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Linear.factory(Force.Types.GF, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Linear.factory(Force.Types.G, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Linear.factory(Force.Types.KG, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Linear.factory(Force.Types.KGF, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1.25890798223106, Linear.factory(Force.Types.T, Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(11, Length.Types.values().length);
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.MN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.GN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.TN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.LB, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.POUND, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.OZ, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KIP, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.GF, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.G, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KG, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KGF, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.T, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.N, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KN,    Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.MN,    Length.Types.DM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.GN,    Length.Types.CM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.TN,    Length.Types.MM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.LB,    Length.Types.HM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.POUND, Length.Types.KM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KIP,   Length.Types.UM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.GF,     Length.Types.IN))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.KGF,    Length.Types.FT))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.T,     Length.Types.YD))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Linear.factory(Force.Types.T,     Length.Types.MI))).getValue());
    }
    


    @Test
    public void allForcesArePresentInLinear() {
        List<UnitType> forces = new Force().getAllUnitTypesOfThisCategory();
        List<UnitType> linear = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Linear.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(forces, linear);
    }


    @Test
    public void allLengthsArePresentInLinear() {
        List<UnitType> lengths = new Length().getAllUnitTypesOfThisCategory();
        List<UnitType> linear = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Linear.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(lengths, linear);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Linear.factory(Force.Types.KN, Length.Types.CM));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Linear.factory(Force.Types.KN, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Linear.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Linear.factory(Force.Types.GF, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Linear.factory(null, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForLinearAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Force)) {
                assertThrows(UnitException.class, () -> Linear.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
            else {
                assertDoesNotThrow(() -> Linear.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
        }
    }


    @Test
    public void factoryForLinearAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Length)) {
                assertThrows(UnitException.class, () -> Linear.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Linear.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
        }
    }

}