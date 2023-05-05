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
import dalosto.engineering.unitconversion.unit.Volume;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Density;


@SpringBootTest
public class DensityTest {

    private UnitFormula unitFormula = new Density();

    @Autowired
    private List<UnitFormula> formulas;



    @Test
    public void SIUnitTypeOfForceShouldBeNVersusMeterCube() {
        assertEquals(Density.factory(Force.Types.N, Volume.Types.M3), unitFormula.getSITypeOfThisCategory());
        assertEquals(Density.factory(Force.Types.N, Volume.Types.M3), new Unit(0.0, Density.factory(Force.Types.KN, Volume.Types.MM3)).getType().getSITypeOfThisCategory());
        assertEquals(Density.factory(Force.Types.N, Volume.Types.M3), new Unit(0.0, Density.factory(Force.Types.KGF, Volume.Types.CM3)).getType().getSITypeOfThisCategory());
        assertEquals(Density.factory(Force.Types.N, Volume.Types.M3), new Unit(0.0, Density.factory(Force.Types.POUND, Volume.Types.IN3)).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(1.0,                unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.N,     Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 3),    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.KN,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 6),    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.MN,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 9),    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.GN,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(Math.pow(10, 12),   unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.TN,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.LB,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(4.4482216282509,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.POUND, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(0.27801385176568125,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.OZ, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(4448.2216282509,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.KIP,   Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.GF,     Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(0.0098066500286389, unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.G,     Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.KG,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(9.8066500286389,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.KGF,    Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(9806.6500286389,    unitFormula.buildUnitToSI(new Unit(1, Density.factory(Force.Types.T,     Volume.Types.M3))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void allForceValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(14, Force.Types.values().length);
        double expected = 12345.67;
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67, Density.factory(Force.Types.N, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12.34567, Density.factory(Force.Types.KN, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(0.01234567, Density.factory(Force.Types.MN, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -9), Density.factory(Force.Types.GN, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(12345.67 * Math.pow(10, -12), Density.factory(Force.Types.TN, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Density.factory(Force.Types.LB, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2775.41701645259, Density.factory(Force.Types.POUND, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(44406.67226324144, Density.factory(Force.Types.OZ, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(2.77541701645259, Density.factory(Force.Types.KIP, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Density.factory(Force.Types.GF, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258907.98223106, Density.factory(Force.Types.G, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Density.factory(Force.Types.KG, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1258.90798223106, Density.factory(Force.Types.KGF, Volume.Types.M3))).getValue(), MetricTest.tolerance);
        assertEquals(expected, unitFormula.buildUnitToSI(new Unit(1.25890798223106, Density.factory(Force.Types.T, Volume.Types.M3))).getValue(), MetricTest.tolerance);
    }


    @Test
    public void forceValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(14, Force.Types.values().length);
        assertEquals(13, Volume.Types.values().length);
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KN,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.MN,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.GN,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.TN,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.LB,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.POUND, Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.OZ,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KIP,   Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.GF,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.G,     Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KG,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KGF,   Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.T,     Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.N,     Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KN,    Volume.Types.M3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.MN,    Volume.Types.DM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.GN,    Volume.Types.CM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.TN,    Volume.Types.MM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.LB,    Volume.Types.HM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.POUND, Volume.Types.KM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KIP,   Volume.Types.UM3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.GF,    Volume.Types.IN3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.KGF,   Volume.Types.FT3))).getValue());
        assertEquals(0.0, unitFormula.buildUnitToSI(new Unit(0.0, Density.factory(Force.Types.T,     Volume.Types.YD3))).getValue());
    }
    


    @Test
    public void allForcesArePresentInDensity() {
        List<UnitType> forces = new Force().getAllUnitTypesOfThisCategory();
        List<UnitType> density = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Density.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        Comparator<UnitType> comparator = new Comparator<UnitType>() {
            @Override
            public int compare(UnitType o1, UnitType o3) {
                return o1.toString().compareTo(o3.toString());
            }
        };
        Collections.sort(forces, comparator);
        Collections.sort(density, comparator);
        assertIterableEquals(forces, density);
    }


    @Test
    public void allVolumesArePresentInDensity() {
        List<UnitType> volumes = new Volume().getAllUnitTypesOfThisCategory();
        List<UnitType> density = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Density.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        Comparator<UnitType> comparator = new Comparator<UnitType>() {
            @Override
            public int compare(UnitType o1, UnitType o3) {
                return o1.toString().compareTo(o3.toString());
            }
        };
        Collections.sort(volumes, comparator);
        Collections.sort(density, comparator);
        assertIterableEquals(volumes, density);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Force.Types.N);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Density.factory(Force.Types.KN, Volume.Types.CM3)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Density.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Density.factory(Force.Types.GF, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Density.factory(null, Volume.Types.CM3)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForDensityAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Force)) {
                assertThrows(UnitException.class, () -> Density.factory(formula.getSITypeOfThisCategory(), Volume.Types.M3));
            }
            else {
                assertDoesNotThrow(() -> Density.factory(formula.getSITypeOfThisCategory(), Volume.Types.M3));
            }
        }
    }


    @Test
    public void factoryForDensityAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Volume)) {
                assertThrows(UnitException.class, () -> Density.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Density.factory(Force.Types.KN, formula.getSITypeOfThisCategory()));
            }
        }
    }

}