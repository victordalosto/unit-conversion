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
import dalosto.engineering.unitconversion.unit.Torque;


@SpringBootTest
public class TorqueTest {

    private UnitFormula unitFormula = new Torque();

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 50.0;
        UnitType unitType = Torque.factory(Force.Types.KN, Length.Types.CM);

        Unit unit = new Unit(50.0, unitType);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        assertEquals(500.0, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), outputSI.getType());
        
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Torque.factory(Force.Types.KN, Length.Types.MM));
        assertEquals(500.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.MM), outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfForceShouldBeNVersusMeter() {
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), unitFormula.getSITypeOfThisCategory());
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Torque.factory(Force.Types.KN, Length.Types.MM)).getType().getSITypeOfThisCategory());
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Torque.factory(Force.Types.KGF, Length.Types.CM)).getType().getSITypeOfThisCategory());
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), new Unit(0.0, Torque.factory(Force.Types.POUND, Length.Types.IN)).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(1.0,                unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 3),    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.KN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 6),    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.MN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 9),    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.GN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 12),   unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.TN,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.LB,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.27801385176568125,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.OZ, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(4448.2216282509,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.KIP,   Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.GF,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.G,     Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.KG,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.KGF,    Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(9806.6500286389,    unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.T,     Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(11, Length.Types.values().length);
        assertEquals(1.0,              unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.M))).getValue() , MetricTest.tolerance);
        assertEquals(0.1,              unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.DM))).getValue(), MetricTest.tolerance);
        assertEquals(0.01,             unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.CM))).getValue(), MetricTest.tolerance);
        assertEquals(0.001,            unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.MM))).getValue(), MetricTest.tolerance);
        assertEquals(100.0,            unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.HM))).getValue(), MetricTest.tolerance);
        assertEquals(1000.0,           unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.KM))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, -6), unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.UM))).getValue(), MetricTest.tolerance);
        assertEquals(0.0254,           unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.IN))).getValue(), MetricTest.tolerance);
        assertEquals(0.3048,           unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.FT))).getValue(), MetricTest.tolerance);
        assertEquals(0.9144,           unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.YD))).getValue(), MetricTest.tolerance);
        assertEquals(1609.344,           unitFormula.buildUnitToSI(new Unit(1, Torque.factory(Force.Types.N, Length.Types.MI))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = 12345.67;
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67, Torque.factory(Force.Types.N, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12.34567, Torque.factory(Force.Types.KN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(0.01234567, Torque.factory(Force.Types.MN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -9), Torque.factory(Force.Types.GN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -12), Torque.factory(Force.Types.TN, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Torque.factory(Force.Types.LB, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Torque.factory(Force.Types.POUND, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(44406.67226324144, Torque.factory(Force.Types.OZ, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2.77541701645259, Torque.factory(Force.Types.KIP, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Torque.factory(Force.Types.GF, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Torque.factory(Force.Types.G, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Torque.factory(Force.Types.KG, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Torque.factory(Force.Types.KGF, Length.Types.M))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1.25890798223106, Torque.factory(Force.Types.T, Length.Types.M))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(11, Length.Types.values().length);
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.MN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.GN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.TN, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.LB, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.POUND, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.OZ, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KIP, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.GF, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.G, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KG, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KGF, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.T, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.N, Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KN,    Length.Types.M))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.MN,    Length.Types.DM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.GN,    Length.Types.CM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.TN,    Length.Types.MM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.LB,    Length.Types.HM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.POUND, Length.Types.KM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KIP,   Length.Types.UM))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.GF,     Length.Types.IN))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.KGF,    Length.Types.FT))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.T,     Length.Types.YD))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Torque.factory(Force.Types.T,     Length.Types.MI))).getValue());
    }
    
    
    @Test
    public void shouldBeAbleToConvertCorrectly() {
        assertEquals(1.5,unitFormula.buildUnitIntoAnotherType(
            new Unit(1.5,Torque.factory(Force.Types.T, Length.Types.YD)),Torque.factory(Force.Types.T, Length.Types.YD)).getValue(), MetricTest.tolerance);
        assertEquals(1.0936132983377,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.N, Length.Types.MM)),Torque.factory(Force.Types.N, Length.Types.YD)).getValue(), MetricTest.tolerance);
        assertEquals(386088.58380468114,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.KGF, Length.Types.M)),Torque.factory(Force.Types.N, Length.Types.IN)).getValue(), MetricTest.tolerance);
        assertEquals(-1.0872590904,unitFormula.buildUnitIntoAnotherType(
            new Unit(-3.567123,Torque.factory(Force.Types.KN, Length.Types.FT)),Torque.factory(Force.Types.KN, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(0.11283696,unitFormula.buildUnitIntoAnotherType(
            new Unit(123.4,Torque.factory(Force.Types.N, Length.Types.YD)),Torque.factory(Force.Types.KN, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(1.016,unitFormula.buildUnitIntoAnotherType(
            new Unit(3.3333333333333333333333,Torque.factory(Force.Types.KN, Length.Types.FT)),Torque.factory(Force.Types.KN, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(3.8057742782152224,unitFormula.buildUnitIntoAnotherType(
            new Unit(1.16,Torque.factory(Force.Types.KN, Length.Types.M)),Torque.factory(Force.Types.KN, Length.Types.FT)).getValue(), MetricTest.tolerance);
        assertEquals(28.24620733939322,unitFormula.buildUnitIntoAnotherType(
            new Unit(2500.0,Torque.factory(Force.Types.LB, Length.Types.IN)),Torque.factory(Force.Types.KN, Length.Types.CM)).getValue(), MetricTest.tolerance);
        assertEquals(98.066500286389,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.T, Length.Types.CM)),Torque.factory(Force.Types.KN, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,unitFormula.buildUnitIntoAnotherType(
            new Unit(1.0,Torque.factory(Force.Types.T, Length.Types.M)),Torque.factory(Force.Types.KN, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(31.080950080799845,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.KN, Length.Types.FT)),Torque.factory(Force.Types.T, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(31080.950080799845,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.KN, Length.Types.FT)),Torque.factory(Force.Types.KGF, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(1.0,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.KGF, Length.Types.M)),Torque.factory(Force.Types.T, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(1.0,unitFormula.buildUnitIntoAnotherType(
            new Unit(100000.0,Torque.factory(Force.Types.KGF, Length.Types.CM)),Torque.factory(Force.Types.T, Length.Types.M)).getValue(), MetricTest.tolerance);
        assertEquals(1.0,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000.0,Torque.factory(Force.Types.GF, Length.Types.M)),Torque.factory(Force.Types.T, Length.Types.MM)).getValue(), MetricTest.tolerance);
        assertEquals(1.0,unitFormula.buildUnitIntoAnotherType(
            new Unit(1000000.0,Torque.factory(Force.Types.GF, Length.Types.MM)),Torque.factory(Force.Types.T, Length.Types.MM)).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForcesArePresentInTorque() {
        List<UnitType> forces = new Force().getAllUnitTypesOfThisCategory();
        List<UnitType> torques = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Torque.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(forces, torques);
    }


    @Test
    public void allLengthsArePresentInTorque() {
        List<UnitType> lengths = new Length().getAllUnitTypesOfThisCategory();
        List<UnitType> torques = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Torque.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(lengths, torques);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Force.Types.N);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Torque.factory(Force.Types.KN, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Torque.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Torque.factory(Force.Types.GF, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Torque.factory(null, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForTorqueAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Force)) {
                assertThrows(UnitException.class, () -> Torque.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
            else {
                assertDoesNotThrow(() -> Torque.factory(formula.getSITypeOfThisCategory(), Length.Types.M));
            }
        }
    }


    @Test
    public void factoryForTorqueAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Length)) {
                assertThrows(UnitException.class, () -> Torque.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Torque.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
        }
    }

}