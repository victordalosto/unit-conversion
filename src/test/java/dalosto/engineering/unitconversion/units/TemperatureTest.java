package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;


@SpringBootTest
public class TemperatureTest {

    @Autowired
    @Qualifier("temperature")
    UnitFormula unitFormula;


    void assertEquivalentTemperatureInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    void assertEquivalentTemperature(double fromValue, Temperature.Types from, Double toValue, Temperature.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 100;
        UnitType unitType = Temperature.Types.C;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Temperature.Types.F);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(373.15, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Temperature.Types.K, outputSI.getType());
        assertEquals(212, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Temperature.Types.F, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfVolulmeShouldBeMeterCubic() {
        assertEquals(Temperature.Types.K, unitFormula.getSITypeOfThisCategory());
        assertEquals(Temperature.Types.K, Temperature.Types.C.getSITypeOfThisCategory());
        assertEquals(Temperature.Types.K, new Unit(0.0, Temperature.Types.F).getType().getSITypeOfThisCategory());
        assertEquals(Temperature.Types.K, new Unit(0.0, Temperature.Types.R).getType().getSITypeOfThisCategory());
    }



    @Test
    public void allTemperatureValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(4, Temperature.Types.values().length);
        assertEquivalentTemperatureInSI(1.0, new Unit(1, Temperature.Types.K));
        assertEquivalentTemperatureInSI(255.927777777777777778, new Unit(1, Temperature.Types.F));
        assertEquivalentTemperatureInSI(274.15, new Unit(1, Temperature.Types.C));
        assertEquivalentTemperatureInSI(0.55555555555555555556, new Unit(1, Temperature.Types.R));
    }


    @Test
    public void allTemperatureValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(4, Temperature.Types.values().length);
        double expected = 12345.67;
        assertEquivalentTemperatureInSI(expected, new Unit(12345.670, Temperature.Types.K));
        assertEquivalentTemperatureInSI(expected, new Unit(21762.536, Temperature.Types.F));
        assertEquivalentTemperatureInSI(expected, new Unit(12072.520, Temperature.Types.C));
        assertEquivalentTemperatureInSI(expected, new Unit(22222.206, Temperature.Types.R));
    }


    @Test
    public void allTemperatureValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        //Some of theses tests aren't correctly, since in some Units, negative values are not allowed.
        assertEquals(4, Temperature.Types.values().length);
        double expected = -12345.67;
        assertEquivalentTemperatureInSI(expected, new Unit(-12345.67, Temperature.Types.K));
        assertEquivalentTemperatureInSI(expected, new Unit(-22681.876, Temperature.Types.F));
        assertEquivalentTemperatureInSI(expected, new Unit(-12618.82, Temperature.Types.C));
        assertEquivalentTemperatureInSI(expected, new Unit(-22222.206, Temperature.Types.R));
    }


    @Test
    public void allTemperatureValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(4, Temperature.Types.values().length);
        double expected = 0.0;
        assertEquivalentTemperatureInSI(expected, new Unit(0.0, Temperature.Types.K));
        assertEquivalentTemperatureInSI(expected, new Unit(-459.67, Temperature.Types.F));
        assertEquivalentTemperatureInSI(expected, new Unit(-273.15, Temperature.Types.C));
        assertEquivalentTemperatureInSI(expected, new Unit(0.0, Temperature.Types.R));
    }


    @Test
    public void TemperatureValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(4, Temperature.Types.values().length);
        assertEquivalentTemperature(0.0, Temperature.Types.K, 0.0, Temperature.Types.K);
        assertEquivalentTemperature(-459.67, Temperature.Types.F, 0.0, Temperature.Types.K);
        assertEquivalentTemperature(-273.15, Temperature.Types.C, 0.0, Temperature.Types.K);
        assertEquivalentTemperature(0.0, Temperature.Types.R, 0.0, Temperature.Types.K);
        assertEquivalentTemperature(255.3722222222222222, Temperature.Types.K, 0.0, Temperature.Types.F);
        assertEquivalentTemperature(0.0, Temperature.Types.F, 0.0, Temperature.Types.F);
        assertEquivalentTemperature(-17.777777777777778, Temperature.Types.C, 0.0, Temperature.Types.F);
        assertEquivalentTemperature(459.67, Temperature.Types.R, 0.0, Temperature.Types.F);
        assertEquivalentTemperature(273.15, Temperature.Types.K, 0.0, Temperature.Types.C);
        assertEquivalentTemperature(32, Temperature.Types.F, 0.0, Temperature.Types.C);
        assertEquivalentTemperature(0.0, Temperature.Types.C, 0.0, Temperature.Types.C);
        assertEquivalentTemperature(491.67, Temperature.Types.R, 0.0, Temperature.Types.C);
        assertEquivalentTemperature(0.0, Temperature.Types.K, 0.0, Temperature.Types.R);
        assertEquivalentTemperature(-459.67, Temperature.Types.F, 0.0, Temperature.Types.R);
        assertEquivalentTemperature(-273.15, Temperature.Types.C, 0.0, Temperature.Types.R);
        assertEquivalentTemperature(0.0, Temperature.Types.R, 0.0, Temperature.Types.R);
    }


    @Test
    public void TemperatureValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(4, Temperature.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentTemperature(randomValue, Temperature.Types.K, randomValue, Temperature.Types.K);
        assertEquivalentTemperature(randomValue, Temperature.Types.F, (randomValue - 32) * 5.0/9.0 + 273.15, Temperature.Types.K);
        assertEquivalentTemperature(randomValue, Temperature.Types.C, randomValue + 273.15, Temperature.Types.K);
        assertEquivalentTemperature(randomValue, Temperature.Types.R, randomValue * 5.0/9.0, Temperature.Types.K);
        assertEquivalentTemperature(randomValue, Temperature.Types.K, (randomValue - 273.15) * 9.0/5.0 + 32, Temperature.Types.F);
        assertEquivalentTemperature(randomValue, Temperature.Types.F, randomValue, Temperature.Types.F);
        assertEquivalentTemperature(randomValue, Temperature.Types.C, (randomValue * 9.0 / 5.0) + 32, Temperature.Types.F);
        assertEquivalentTemperature(randomValue, Temperature.Types.R, randomValue - 459.67, Temperature.Types.F);
        assertEquivalentTemperature(randomValue, Temperature.Types.K, randomValue - 273.15, Temperature.Types.C);
        assertEquivalentTemperature(randomValue, Temperature.Types.F, (randomValue - 32) * 5.0 / 9.0, Temperature.Types.C);
        assertEquivalentTemperature(randomValue, Temperature.Types.C, randomValue, Temperature.Types.C);
        assertEquivalentTemperature(randomValue, Temperature.Types.R, (randomValue - 491.67) * 5.0/9.0, Temperature.Types.C);
        assertEquivalentTemperature(randomValue, Temperature.Types.K, randomValue * 9.0 / 5.0, Temperature.Types.R);
        assertEquivalentTemperature(randomValue, Temperature.Types.F, randomValue + 459.67, Temperature.Types.R);
        assertEquivalentTemperature(randomValue, Temperature.Types.C, randomValue * 9.0/5.0 + 491.67, Temperature.Types.R);
        assertEquivalentTemperature(randomValue, Temperature.Types.R, randomValue, Temperature.Types.R);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Temperature.Types.K);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Temperature.Types.K));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Temperature.Types.F));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}