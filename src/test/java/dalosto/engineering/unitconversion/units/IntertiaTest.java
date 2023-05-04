package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;


public class IntertiaTest {

    private UnitFormula unitFormula = new Inertia();


    public void assertEquivalentInertiaInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    public void assertEquivalentInertia(double fromValue, Inertia.Types from, Double toValue, Inertia.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 25000000.0;
        UnitType unitType = Inertia.Types.MM4;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Inertia.Types.CM4);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(2.5*Math.pow(10, -5), outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Inertia.Types.M4, outputSI.getType());
        assertEquals(2500.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Inertia.Types.CM4, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfVolulmeShouldBeMeterCubic() {
        assertEquals(Inertia.Types.M4, unitFormula.getSITypeOfThisCategory());
        assertEquals(Inertia.Types.M4, Inertia.Types.DM4.getSITypeOfThisCategory());
        assertEquals(Inertia.Types.M4, new Unit(0.0, Inertia.Types.FT4).getType().getSITypeOfThisCategory());
        assertEquals(Inertia.Types.M4, new Unit(0.0, Inertia.Types.KM4).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(10, Inertia.Types.values().length);
        assertEquivalentInertiaInSI(1.0, new Unit(1, Inertia.Types.M4));
        assertEquivalentInertiaInSI(0.1 * 0.1 * 0.1 * 0.1, new Unit(1, Inertia.Types.DM4));
        assertEquivalentInertiaInSI(0.01 * 0.01 * 0.01 * 0.01, new Unit(1, Inertia.Types.CM4));
        assertEquivalentInertiaInSI(0.001 * 0.001 * 0.001 * 0.001, new Unit(1, Inertia.Types.MM4));
        assertEquivalentInertiaInSI(100.0 * 100.0 * 100.0 * 100.0, new Unit(1, Inertia.Types.HM4));
        assertEquivalentInertiaInSI(1000.0 * 1000.0 * 1000.0 * 1000.0, new Unit(1, Inertia.Types.KM4));
        assertEquivalentInertiaInSI(Math.pow(10, -24), new Unit(1, Inertia.Types.UM4));
        assertEquivalentInertiaInSI(0.0254 * 0.0254 * 0.0254 * 0.0254, new Unit(1, Inertia.Types.IN4));
        assertEquivalentInertiaInSI(0.3048 * 0.3048 * 0.3048 * 0.3048, new Unit(1, Inertia.Types.FT4));
        assertEquivalentInertiaInSI(0.9144 * 0.9144 * 0.9144 * 0.9144, new Unit(1, Inertia.Types.YD4));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(10, Inertia.Types.values().length);
        double expected = 12345.67;
        assertEquivalentInertiaInSI(expected, new Unit(12345.67000000, Inertia.Types.M4));
        assertEquivalentInertiaInSI(expected, new Unit(123456700.0000, Inertia.Types.DM4));
        assertEquivalentInertiaInSI(expected, new Unit(1234567000000.0, Inertia.Types.CM4));
        assertEquivalentInertiaInSI(expected, new Unit(1.234567 * Math.pow(10, 16), Inertia.Types.MM4));
        assertEquivalentInertiaInSI(expected, new Unit(.0001234567000, Inertia.Types.HM4));
        assertEquivalentInertiaInSI(expected, new Unit(1.234567 * Math.pow(10, -8), Inertia.Types.KM4));
        assertEquivalentInertiaInSI(expected, new Unit(12345.67 * Math.pow(10, 24), Inertia.Types.UM4));
        assertEquivalentInertiaInSI(expected, new Unit(2.9660590817244626615237482443469 * Math.pow(10,10), Inertia.Types.IN4));
        assertEquivalentInertiaInSI(expected, new Unit(1430391.14666495, Inertia.Types.FT4));
        assertEquivalentInertiaInSI(expected, new Unit(17659.1499588268, Inertia.Types.YD4));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(10, Inertia.Types.values().length);
        double expected = -12345.67;
        assertEquivalentInertiaInSI(expected, new Unit(-12345.67000000, Inertia.Types.M4));
        assertEquivalentInertiaInSI(expected, new Unit(-123456700.0000, Inertia.Types.DM4));
        assertEquivalentInertiaInSI(expected, new Unit(-1234567000000.0, Inertia.Types.CM4));
        assertEquivalentInertiaInSI(expected, new Unit(-1.234567 * Math.pow(10, 16), Inertia.Types.MM4));
        assertEquivalentInertiaInSI(expected, new Unit(-.0001234567000, Inertia.Types.HM4));
        assertEquivalentInertiaInSI(expected, new Unit(-1.234567 * Math.pow(10, -8), Inertia.Types.KM4));
        assertEquivalentInertiaInSI(expected, new Unit(-12345.67 * Math.pow(10, 24), Inertia.Types.UM4));
        assertEquivalentInertiaInSI(expected, new Unit(-2.9660590817244626615237482443469 * Math.pow(10,10), Inertia.Types.IN4));
        assertEquivalentInertiaInSI(expected, new Unit(-1430391.14666495, Inertia.Types.FT4));
        assertEquivalentInertiaInSI(expected, new Unit(-17659.1499588268, Inertia.Types.YD4));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(10, Inertia.Types.values().length);
        double expected = 0.0;
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.M4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.DM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.CM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.MM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.HM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.KM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.UM4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.IN4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.FT4));
        assertEquivalentInertiaInSI(expected, new Unit(0.0, Inertia.Types.YD4));
    }


    @Test
    public void volumeValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(10, Inertia.Types.values().length);
        assertEquivalentInertia(0.0, Inertia.Types.CM4, 0.0, Inertia.Types.M4);
        assertEquivalentInertia(0.0, Inertia.Types.MM4, 0.0, Inertia.Types.M4);
        assertEquivalentInertia(0.0, Inertia.Types.IN4, 0.0, Inertia.Types.M4);
        assertEquivalentInertia(0.0, Inertia.Types.IN4, 0.0, Inertia.Types.CM4);
        assertEquivalentInertia(0.0, Inertia.Types.IN4, 0.0, Inertia.Types.MM4);
        assertEquivalentInertia(0.0, Inertia.Types.M4, 0.0, Inertia.Types.DM4);
        assertEquivalentInertia(0.0, Inertia.Types.M4, 0.0, Inertia.Types.YD4);
        assertEquivalentInertia(0.0, Inertia.Types.DM4, 0.0, Inertia.Types.CM4);
        assertEquivalentInertia(0.0, Inertia.Types.DM4, 0.0, Inertia.Types.M4);
        assertEquivalentInertia(0.0, Inertia.Types.CM4, 0.0, Inertia.Types.MM4);
        assertEquivalentInertia(0.0, Inertia.Types.CM4, 0.0, Inertia.Types.DM4);
        assertEquivalentInertia(0.0, Inertia.Types.MM4, 0.0, Inertia.Types.HM4);
        assertEquivalentInertia(0.0, Inertia.Types.MM4, 0.0, Inertia.Types.CM4);
        assertEquivalentInertia(0.0, Inertia.Types.HM4, 0.0, Inertia.Types.KM4);
        assertEquivalentInertia(0.0, Inertia.Types.HM4, 0.0, Inertia.Types.MM4);
        assertEquivalentInertia(0.0, Inertia.Types.KM4, 0.0, Inertia.Types.UM4);
        assertEquivalentInertia(0.0, Inertia.Types.KM4, 0.0, Inertia.Types.HM4);
        assertEquivalentInertia(0.0, Inertia.Types.UM4, 0.0, Inertia.Types.KM4);
        assertEquivalentInertia(0.0, Inertia.Types.UM4, 0.0, Inertia.Types.IN4);
        assertEquivalentInertia(0.0, Inertia.Types.IN4, 0.0, Inertia.Types.FT4);
        assertEquivalentInertia(0.0, Inertia.Types.IN4, 0.0, Inertia.Types.UM4);
        assertEquivalentInertia(0.0, Inertia.Types.FT4, 0.0, Inertia.Types.YD4);
        assertEquivalentInertia(0.0, Inertia.Types.FT4, 0.0, Inertia.Types.IN4);
        assertEquivalentInertia(0.0, Inertia.Types.YD4, 0.0, Inertia.Types.M4);
        assertEquivalentInertia(0.0, Inertia.Types.YD4, 0.0, Inertia.Types.FT4);
    }


    @Test
    public void volumeValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(10, Inertia.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentInertia(randomValue, Inertia.Types.CM4, randomValue / 100000000, Inertia.Types.M4);
        assertEquivalentInertia(randomValue, Inertia.Types.MM4, randomValue / 1000 / 1000 / 1000 / 1000, Inertia.Types.M4);
        assertEquivalentInertia(randomValue, Inertia.Types.IN4, randomValue * 0.0254 * 0.0254 * 0.0254 * 0.0254, Inertia.Types.M4);
        assertEquivalentInertia(randomValue, Inertia.Types.IN4, randomValue * 2.54 * 2.54 * 2.54 * 2.54, Inertia.Types.CM4);
        assertEquivalentInertia(randomValue, Inertia.Types.IN4, randomValue * 416231.4256, Inertia.Types.MM4);
        assertEquivalentInertia(randomValue, Inertia.Types.M4, randomValue * 10000, Inertia.Types.DM4);
        assertEquivalentInertia(randomValue, Inertia.Types.M4, randomValue * 1.4303921908512600956886527637634, Inertia.Types.YD4);
        assertEquivalentInertia(randomValue, Inertia.Types.DM4, randomValue * 10000, Inertia.Types.CM4);
        assertEquivalentInertia(randomValue, Inertia.Types.DM4, randomValue / 10000, Inertia.Types.M4);
        assertEquivalentInertia(randomValue, Inertia.Types.CM4, randomValue * 10000, Inertia.Types.MM4);
        assertEquivalentInertia(randomValue, Inertia.Types.CM4, randomValue / 10000, Inertia.Types.DM4);
        assertEquivalentInertia(randomValue, Inertia.Types.MM4, randomValue * Math.pow(10, -20), Inertia.Types.HM4);
        assertEquivalentInertia(randomValue, Inertia.Types.MM4, randomValue / 10000, Inertia.Types.CM4);
        assertEquivalentInertia(randomValue, Inertia.Types.HM4, randomValue / 10000, Inertia.Types.KM4);
        assertEquivalentInertia(randomValue, Inertia.Types.HM4, randomValue * Math.pow(10, 20), Inertia.Types.MM4);
        assertEquivalentInertia(randomValue, Inertia.Types.KM4, randomValue * 10000, Inertia.Types.HM4);
        assertEquivalentInertia(randomValue, Inertia.Types.UM4, randomValue / Math.pow(10, 36), Inertia.Types.KM4);
        assertEquivalentInertia(randomValue, Inertia.Types.UM4, randomValue / 25400 / 25400 / 25400 / 25400, Inertia.Types.IN4);
        assertEquivalentInertia(randomValue, Inertia.Types.IN4, randomValue / 12 / 12 / 12 / 12, Inertia.Types.FT4);
        assertEquivalentInertia(randomValue, Inertia.Types.FT4, randomValue * 1.0 / 3.0 / 3.0 / 3.0 / 3.0, Inertia.Types.YD4);
        assertEquivalentInertia(randomValue, Inertia.Types.FT4, randomValue * 12 * 12 * 12 * 12, Inertia.Types.IN4);
        assertEquivalentInertia(randomValue, Inertia.Types.YD4, randomValue * 0.9144 * 0.9144 * 0.9144 * 0.9144, Inertia.Types.M4);
        assertEquivalentInertia(randomValue, Inertia.Types.YD4, randomValue * 3 * 3 * 3 * 3, Inertia.Types.FT4);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Inertia.Types.CM4);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Inertia.Types.CM4));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Inertia.Types.CM4));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}