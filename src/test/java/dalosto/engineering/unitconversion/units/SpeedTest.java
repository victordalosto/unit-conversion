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
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Length;
import dalosto.engineering.unitconversion.unit.Speed;
import dalosto.engineering.unitconversion.unit.Time;


@SpringBootTest
public class SpeedTest {

    private UnitFormula unitFormula = new Speed();

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void SIUnitTypeOfForceShouldBeCorrect() {
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), unitFormula.getSITypeOfThisCategory());
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), new Unit(0.0, Speed.factory(Length.Types.KM, Time.Types.MIN)).getType().getSITypeOfThisCategory());
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), new Unit(0.0, Speed.factory(Length.Types.IN, Time.Types.DAY)).getType().getSITypeOfThisCategory());
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), new Unit(0.0, Speed.factory(Length.Types.FT, Time.Types.MONTH)).getType().getSITypeOfThisCategory());
    }

    

    @Test
    public void allTimesArePresentInSpeed() {
        List<UnitType> forces = new Time().getAllUnitTypesOfThisCategory();
        List<UnitType> speeds = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Speed.Types) u).getSecondary())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(forces, speeds);
    }


    @Test
    public void allLengthsArePresentInSpeed() {
        List<UnitType> lengths = new Length().getAllUnitTypesOfThisCategory();
        List<UnitType> speeds = unitFormula.getAllUnitTypesOfThisCategory()
                                            .stream()
                                            .map(u -> ((Speed.Types) u).getPrincipal())
                                            .distinct()
                                            .collect(Collectors.toList());
        assertIterableEquals(lengths, speeds);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Speed.factory(Length.Types.M, Time.Types.S));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Speed.factory(Length.Types.M, Time.Types.S)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Speed.factory(null, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Speed.factory(Force.Types.GF, null)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Speed.factory(null, Length.Types.CM)));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }


    @Test
    public void factoryForSpeedAcceptOnlyValidPrincipalType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Length)) {
                assertThrows(UnitException.class, () -> Speed.factory(formula.getSITypeOfThisCategory(), Time.Types.S));
            }
            else {
                assertDoesNotThrow(() -> Speed.factory(formula.getSITypeOfThisCategory(), Time.Types.S));
            }
        }
    }


    @Test
    public void factoryForSpeedAcceptOnlyValidSecondaryType() {
        for (UnitFormula formula : formulas) {
            if (!(formula instanceof Time)) {
                assertThrows(UnitException.class, () -> Speed.factory(Length.Types.M, formula.getSITypeOfThisCategory()));
            }
            else {
                assertDoesNotThrow(() -> Speed.factory(Length.Types.M, formula.getSITypeOfThisCategory()));
            }
        }
    }

}