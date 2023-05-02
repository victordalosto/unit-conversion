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


@SpringBootTest
public class MomentTest {

    UnitFormula unitFormula = new Moment();

    @Autowired
    List<UnitFormula> formulas;


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 50.0;
        UnitType unitType = Moment.factory(Force.Types.KN, Length.Types.CM);

        Unit unit = new Unit(50.0, unitType);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        System.out.println(outputSI);
        assertEquals(500.0, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Moment.factory(Force.Types.N, Length.Types.M), outputSI.getType());
        
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Moment.factory(Force.Types.KN, Length.Types.MM));
        assertEquals(500.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Moment.factory(Force.Types.KN, Length.Types.MM), outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfForceShouldBeNVersusMeter() {
        assertEquals(Moment.factory(Force.Types.N, Length.Types.M), unitFormula.getSITypeOfThisCategory());
        assertEquals(Moment.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Moment.factory(Force.Types.KN, Length.Types.MM)).getType().getSITypeOfThisCategory());
        assertEquals(Moment.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Moment.factory(Force.Types.KG, Length.Types.CM)).getType().getSITypeOfThisCategory());
        assertEquals(Moment.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Moment.factory(Force.Types.POUND, Length.Types.IN)).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(11, Force.Types.values().length);
        assertEquals(1.0,                unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 3),    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.KN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 6),    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.MN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 9),    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.GN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 12),   unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.TN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.LB,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4448.2216282509,    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.KIP,   Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.G,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.KG,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9806.6500286389,    unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.T,     Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(10, Length.Types.values().length);
        assertEquals(1.0,              unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.M))).getValue() , MetricTest.tolerance);
        assertEquals(0.1,              unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.DM))).getValue(), MetricTest.tolerance);
        assertEquals(0.01,             unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.CM))).getValue(), MetricTest.tolerance);
        assertEquals(0.001,            unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.MM))).getValue(), MetricTest.tolerance);
        assertEquals(100.0,            unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.HM))).getValue(), MetricTest.tolerance);
        assertEquals(1000.0,           unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.KM))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, -6), unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.UM))).getValue(), MetricTest.tolerance);
        assertEquals(0.0254,           unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.IN))).getValue(), MetricTest.tolerance);
        assertEquals(0.3048,           unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.FT))).getValue(), MetricTest.tolerance);
        assertEquals(0.9144,           unitFormula.buildUnitToSI(new Unit(1, Moment.factory(Force.Types.N, Length.Types.YD))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(11, Force.Types.values().length);
        double expected = 12345.67;
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67, Moment.factory(Force.Types.N, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12.34567, Moment.factory(Force.Types.KN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(0.01234567, Moment.factory(Force.Types.MN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -9), Moment.factory(Force.Types.GN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -12), Moment.factory(Force.Types.TN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Moment.factory(Force.Types.LB, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Moment.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2.77541701645259, Moment.factory(Force.Types.KIP, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Moment.factory(Force.Types.G, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Moment.factory(Force.Types.KG, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1.25890798223106, Moment.factory(Force.Types.T, Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(11, Force.Types.values().length);
        assertEquals(10, Length.Types.values().length);
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.MN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.GN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.TN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.LB, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.POUND, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KIP, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.G, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KG, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.T, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.N, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KN,    Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.MN,    Length.Types.DM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.GN,    Length.Types.CM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.TN,    Length.Types.MM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.LB,    Length.Types.HM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.POUND, Length.Types.KM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KIP,   Length.Types.UM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.G,     Length.Types.IN))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.KG,    Length.Types.FT))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Moment.factory(Force.Types.T,     Length.Types.YD))).getValue());
    }


    @Test
    public void allForcesArePresentInMoment() {
        List<UnitType> forceTypes = new Force().getAllUnitTypesOfThisCategory();
        List<UnitType> moments = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Moment.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(forceTypes, moments);
    }


    @Test
    public void allLengthsArePresentInMoment() {
        List<UnitType> forceTypes = new Length().getAllUnitTypesOfThisCategory();
        List<UnitType> moments = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Moment.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(forceTypes, moments);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Force.Types.N);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Moment.factory(Force.Types.KN, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Moment.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Moment.factory(Force.Types.G, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Moment.factory(null, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForMomentAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Force)) {
                assertThrows(UnitException.class, () -> Moment.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
            else {
                assertDoesNotThrow(() -> Moment.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
        }
    }


    @Test
    public void factoryForMomentAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Length)) {
                assertThrows(UnitException.class, () -> Moment.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Moment.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
        }
    }

}