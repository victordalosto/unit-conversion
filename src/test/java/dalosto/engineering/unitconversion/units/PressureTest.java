package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Collections;
import java.util.Comparator;
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
import dalosto.engineering.unitconversion.unit.Area;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Pressure;


@SpringBootTest
public class PressureTest {

    private UnitFormula unitFormula = new Pressure();

    @Autowired
    private List<UnitFormula> formulas;



    @Test
    public void SIUnitTypeOfForceShouldBeNVersusMeter() {
        assertEquals(Pressure.factory(Force.Types.N, Area.Types.M2), unitFormula.getSITypeOfThisCategory());
        assertEquals(Pressure.factory(Force.Types.N, Area.Types.M2), new Unit(0.0, Pressure.factory(Force.Types.KN, Area.Types.MM2)).getType().getSITypeOfThisCategory());
        assertEquals(Pressure.factory(Force.Types.N, Area.Types.M2), new Unit(0.0, Pressure.factory(Force.Types.KGF, Area.Types.CM2)).getType().getSITypeOfThisCategory());
        assertEquals(Pressure.factory(Force.Types.N, Area.Types.M2), new Unit(0.0, Pressure.factory(Force.Types.POUND, Area.Types.IN2)).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(1.0,                unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.N,     Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 3),    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.KN,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 6),    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.MN,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 9),    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.GN,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 12),   unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.TN,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.LB,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.POUND, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(0.27801385176568125,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.OZ, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(4448.2216282509,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.KIP,   Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.GF,     Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.G,     Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.KG,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.KGF,    Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(9806.6500286389,    unitFormula.buildUnitToSI(new Unit(1, Pressure.factory(Force.Types.T,     Area.Types.M2))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = 12345.67;
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67, Pressure.factory(Force.Types.N, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12.34567, Pressure.factory(Force.Types.KN, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(0.01234567, Pressure.factory(Force.Types.MN, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -9), Pressure.factory(Force.Types.GN, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -12), Pressure.factory(Force.Types.TN, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Pressure.factory(Force.Types.LB, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Pressure.factory(Force.Types.POUND, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(44406.67226324144, Pressure.factory(Force.Types.OZ, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2.77541701645259, Pressure.factory(Force.Types.KIP, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Pressure.factory(Force.Types.GF, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Pressure.factory(Force.Types.G, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Pressure.factory(Force.Types.KG, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Pressure.factory(Force.Types.KGF, Area.Types.M2))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1.25890798223106, Pressure.factory(Force.Types.T, Area.Types.M2))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(10, Area.Types.values().length);
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KN,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.MN,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.GN,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.TN,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.LB,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.POUND, Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.OZ,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KIP,   Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.GF,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.G,     Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KG,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KGF,   Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.T,     Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.N,     Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KN,    Area.Types.M2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.MN,    Area.Types.DM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.GN,    Area.Types.CM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.TN,    Area.Types.MM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.LB,    Area.Types.HM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.POUND, Area.Types.KM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KIP,   Area.Types.UM2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.GF,    Area.Types.IN2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.KGF,   Area.Types.FT2))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Pressure.factory(Force.Types.T,     Area.Types.YD2))).getValue());
    }
    


    @Test
    public void allForcesArePresentInPressure() {
        List<UnitType> forces = new Force().getAllUnitTypesOfThisCategory();
        List<UnitType> pressure = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Pressure.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        Comparator<UnitType> comparator = new Comparator<UnitType>() {
            @Override
            public int compare(UnitType o1, UnitType o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
        Collections.sort(forces, comparator);
        Collections.sort(pressure, comparator);
        assertIterableEquals(forces, pressure);
    }


    @Test
    public void allAreasArePresentInPressure() {
        List<UnitType> areas = new Area().getAllUnitTypesOfThisCategory();
        List<UnitType> pressure = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Pressure.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        Comparator<UnitType> comparator = new Comparator<UnitType>() {
            @Override
            public int compare(UnitType o1, UnitType o2) {
                return o1.toString().compareTo(o2.toString());
            }
        };
        Collections.sort(areas, comparator);
        Collections.sort(pressure, comparator);
        assertIterableEquals(areas, pressure);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Force.Types.N);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Pressure.factory(Force.Types.KN, Area.Types.CM2)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Pressure.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Pressure.factory(Force.Types.GF, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Pressure.factory(null, Area.Types.CM2)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForPressureAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Force)) {
                assertThrows(UnitException.class, () -> Pressure.factory(formula.getSITypeOfThisCategory(), Area.Types.M2));
            }
            else {
                assertDoesNotThrow(() -> Pressure.factory(formula.getSITypeOfThisCategory(), Area.Types.M2));
            }
        }
    }


    @Test
    public void factoryForPressureAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Area)) {
                assertThrows(UnitException.class, () -> Pressure.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Pressure.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
        }
    }

}