package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.TestMetrics;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


@SpringBootTest
public class TestArea {

    @Autowired
    @Qualifier("area")
    UnitFormula unitFormula;


    void assertEquivalentAreaInSI(double expected, Unit actual) {
        TestMetrics.assertEquavalentInSI(expected, actual, unitFormula);
    }


    void assertEquivalentArea(double fromValue, Area.Types from, Double toValue, Area.Types to) {
        TestMetrics.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 25000000.0;
        UnitType unitType = Area.Types.MM2;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Area.Types.CM2);
        assertEquals(value, unit.getValue(), TestMetrics.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(25.0, outputSI.getValue(), TestMetrics.tolerance);
        assertEquals(Area.Types.M2, outputSI.getType());
        assertEquals(250000.0, outputAnotherType.getValue(), TestMetrics.tolerance);
        assertEquals(Area.Types.CM2, outputAnotherType.getType());
    }


    @Test
    void SIUnitTypeOfAreaShouldBeMeterSquare() {
        assertEquals(Area.Types.M2, unitFormula.getSITypeOfThisCategory());
        assertEquals(Area.Types.M2, Area.Types.M2.getSITypeOfThisCategory());
        assertEquals(Area.Types.M2, new Unit(0.0, Area.Types.FT2).getType().getSITypeOfThisCategory());
        assertEquals(Area.Types.M2, new Unit(0.0, Area.Types.KM2).getType().getSITypeOfThisCategory());
    }


    @Test
    void allAreaValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(10, Area.Types.values().length);
        assertEquivalentAreaInSI(1.0, new Unit(1, Area.Types.M2));
        assertEquivalentAreaInSI(0.1 * 0.1, new Unit(1, Area.Types.DM2));
        assertEquivalentAreaInSI(0.01 * 0.01, new Unit(1, Area.Types.CM2));
        assertEquivalentAreaInSI(0.001 * 0.001, new Unit(1, Area.Types.MM2));
        assertEquivalentAreaInSI(100.0 * 100.0, new Unit(1, Area.Types.HM2));
        assertEquivalentAreaInSI(1000.0 * 1000.0, new Unit(1, Area.Types.KM2));
        assertEquivalentAreaInSI(Math.pow(10, -12), new Unit(1, Area.Types.UM2));
        assertEquivalentAreaInSI(0.0254 * 0.0254, new Unit(1, Area.Types.IN2));
        assertEquivalentAreaInSI(0.3048 * 0.3048, new Unit(1, Area.Types.FT2));
        assertEquivalentAreaInSI(0.9144 * 0.9144, new Unit(1, Area.Types.YD2));
    }


    @Test
    void allAreaValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(10, Area.Types.values().length);
        double expected = 12345.67;
        assertEquivalentAreaInSI(expected, new Unit(12345.67000000, Area.Types.M2));
        assertEquivalentAreaInSI(expected, new Unit(1234567.000000, Area.Types.DM2));
        assertEquivalentAreaInSI(expected, new Unit(123456700.0000, Area.Types.CM2));
        assertEquivalentAreaInSI(expected, new Unit(12345670000.00, Area.Types.MM2));
        assertEquivalentAreaInSI(expected, new Unit(1.234567000000, Area.Types.HM2));
        assertEquivalentAreaInSI(expected, new Unit(0.01234567000000, Area.Types.KM2));
        assertEquivalentAreaInSI(expected, new Unit(1.234567 * Math.pow(10, 16), Area.Types.UM2));
        assertEquivalentAreaInSI(expected, new Unit(19135826.771653545, Area.Types.IN2));
        assertEquivalentAreaInSI(expected, new Unit(132887.6859142607, Area.Types.FT2));
        assertEquivalentAreaInSI(expected, new Unit(14765.298434917858, Area.Types.YD2));
    }


    @Test
    void allAreaValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(10, Area.Types.values().length);
        double expected = -12345.67;
        assertEquivalentAreaInSI(expected, new Unit(-12345.67000000, Area.Types.M2));
        assertEquivalentAreaInSI(expected, new Unit(-1234567.000000, Area.Types.DM2));
        assertEquivalentAreaInSI(expected, new Unit(-123456700.0000, Area.Types.CM2));
        assertEquivalentAreaInSI(expected, new Unit(-12345670000.00, Area.Types.MM2));
        assertEquivalentAreaInSI(expected, new Unit(-1.234567000000, Area.Types.HM2));
        assertEquivalentAreaInSI(expected, new Unit(-0.01234567000000, Area.Types.KM2));
        assertEquivalentAreaInSI(expected, new Unit(-1.234567 * Math.pow(10, 16), Area.Types.UM2));
        assertEquivalentAreaInSI(expected, new Unit(-19135826.771653545, Area.Types.IN2));
        assertEquivalentAreaInSI(expected, new Unit(-132887.6859142607, Area.Types.FT2));
        assertEquivalentAreaInSI(expected, new Unit(-14765.298434917858, Area.Types.YD2));
    }


    @Test
    void allAreaValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(10, Area.Types.values().length);
        double expected = 0.0;
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.M2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.DM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.CM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.MM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.HM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.KM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.UM2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.IN2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.FT2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.YD2));
    }


    @Test
    void areaValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(10, Area.Types.values().length);
        assertEquivalentArea(0.0, Area.Types.CM2, 0.0, Area.Types.M2);
        assertEquivalentArea(0.0, Area.Types.MM2, 0.0, Area.Types.M2);
        assertEquivalentArea(0.0, Area.Types.IN2, 0.0, Area.Types.M2);
        assertEquivalentArea(0.0, Area.Types.IN2, 0.0, Area.Types.CM2);
        assertEquivalentArea(0.0, Area.Types.IN2, 0.0, Area.Types.MM2);
        assertEquivalentArea(0.0, Area.Types.M2, 0.0, Area.Types.DM2);
        assertEquivalentArea(0.0, Area.Types.M2, 0.0, Area.Types.YD2);
        assertEquivalentArea(0.0, Area.Types.DM2, 0.0, Area.Types.CM2);
        assertEquivalentArea(0.0, Area.Types.DM2, 0.0, Area.Types.M2);
        assertEquivalentArea(0.0, Area.Types.CM2, 0.0, Area.Types.MM2);
        assertEquivalentArea(0.0, Area.Types.CM2, 0.0, Area.Types.DM2);
        assertEquivalentArea(0.0, Area.Types.MM2, 0.0, Area.Types.HM2);
        assertEquivalentArea(0.0, Area.Types.MM2, 0.0, Area.Types.CM2);
        assertEquivalentArea(0.0, Area.Types.HM2, 0.0, Area.Types.KM2);
        assertEquivalentArea(0.0, Area.Types.HM2, 0.0, Area.Types.MM2);
        assertEquivalentArea(0.0, Area.Types.KM2, 0.0, Area.Types.UM2);
        assertEquivalentArea(0.0, Area.Types.KM2, 0.0, Area.Types.HM2);
        assertEquivalentArea(0.0, Area.Types.UM2, 0.0, Area.Types.KM2);
        assertEquivalentArea(0.0, Area.Types.UM2, 0.0, Area.Types.IN2);
        assertEquivalentArea(0.0, Area.Types.IN2, 0.0, Area.Types.FT2);
        assertEquivalentArea(0.0, Area.Types.IN2, 0.0, Area.Types.UM2);
        assertEquivalentArea(0.0, Area.Types.FT2, 0.0, Area.Types.YD2);
        assertEquivalentArea(0.0, Area.Types.FT2, 0.0, Area.Types.IN2);
        assertEquivalentArea(0.0, Area.Types.YD2, 0.0, Area.Types.M2);
        assertEquivalentArea(0.0, Area.Types.YD2, 0.0, Area.Types.FT2);
    }


    @Test
    void areaValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(10, Area.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentArea(randomValue, Area.Types.CM2, randomValue / 100 / 100, Area.Types.M2);
        assertEquivalentArea(randomValue, Area.Types.MM2, randomValue / 1000 / 1000, Area.Types.M2);
        assertEquivalentArea(randomValue, Area.Types.IN2, randomValue * 0.0254 * 0.0254, Area.Types.M2);
        assertEquivalentArea(randomValue, Area.Types.IN2, randomValue * 2.54 * 2.54, Area.Types.CM2);
        assertEquivalentArea(randomValue, Area.Types.IN2, randomValue * 25.4 * 25.4, Area.Types.MM2);
        assertEquivalentArea(randomValue, Area.Types.M2, randomValue * 10 * 10, Area.Types.DM2);
        assertEquivalentArea(randomValue, Area.Types.M2, randomValue * 1.19599004630109, Area.Types.YD2);
        assertEquivalentArea(randomValue, Area.Types.DM2, randomValue * 10 * 10, Area.Types.CM2);
        assertEquivalentArea(randomValue, Area.Types.DM2, randomValue / 10 / 10, Area.Types.M2);
        assertEquivalentArea(randomValue, Area.Types.CM2, randomValue * 10 * 10, Area.Types.MM2);
        assertEquivalentArea(randomValue, Area.Types.CM2, randomValue / 10 / 10, Area.Types.DM2);
        assertEquivalentArea(randomValue, Area.Types.MM2, randomValue * Math.pow(10, -10), Area.Types.HM2);
        assertEquivalentArea(randomValue, Area.Types.MM2, randomValue / 10 / 10, Area.Types.CM2);
        assertEquivalentArea(randomValue, Area.Types.HM2, randomValue / 10 / 10, Area.Types.KM2);
        assertEquivalentArea(randomValue, Area.Types.HM2, randomValue * Math.pow(10, 10), Area.Types.MM2);
        assertEquivalentArea(randomValue, Area.Types.KM2, randomValue * Math.pow(10, 18), Area.Types.UM2);
        assertEquivalentArea(randomValue, Area.Types.KM2, randomValue * 10 * 10, Area.Types.HM2);
        assertEquivalentArea(randomValue, Area.Types.UM2, randomValue / Math.pow(10, 18), Area.Types.KM2);
        assertEquivalentArea(randomValue, Area.Types.UM2, randomValue / 25400 / 25400, Area.Types.IN2);
        assertEquivalentArea(randomValue, Area.Types.IN2, randomValue / 12 / 12, Area.Types.FT2);
        assertEquivalentArea(randomValue, Area.Types.IN2, randomValue * 645160000, Area.Types.UM2);
        assertEquivalentArea(randomValue, Area.Types.FT2, randomValue * 1.0 / 3.0 * 1.0 / 3.0, Area.Types.YD2);
        assertEquivalentArea(randomValue, Area.Types.FT2, randomValue * 12 * 12, Area.Types.IN2);
        assertEquivalentArea(randomValue, Area.Types.YD2, randomValue * 0.9144 * 0.9144, Area.Types.M2);
        assertEquivalentArea(randomValue, Area.Types.YD2, randomValue * 3 * 3, Area.Types.FT2);
    }


    @Test
    void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Area.Types.CM2);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Area.Types.CM2));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Area.Types.CM2));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}