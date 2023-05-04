package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.formula.UnitFormula;


public class LengthTest {

    private UnitFormula unitFormula = new Length();


    public void assertEquivalentLengthInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    public void assertEquivalentLength(double fromValue, Length.Types from, Double toValue, Length.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateALengthAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 5000.0;
        UnitType unitType = Length.Types.MM;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Length.Types.CM);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(5.0, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Length.Types.M, outputSI.getType());
        assertEquals(500.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Length.Types.CM, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfLengthShouldBeMeter() {
        assertEquals(Length.Types.M, unitFormula.getSITypeOfThisCategory());
        assertEquals(Length.Types.M, Length.Types.DM.getSITypeOfThisCategory());
        assertEquals(Length.Types.M, new Unit(0.0, Length.Types.FT).getType().getSITypeOfThisCategory());
        assertEquals(Length.Types.M, new Unit(0.0, Length.Types.KM).getType().getSITypeOfThisCategory());
    }


    @Test
    public void unitsReturnFromTypeAreEqualThoseReturnedFromUnitFormula() {
        assertEquals(Length.Types.CM.getAllUnitTypesOfThisCategory(), unitFormula.getAllUnitTypesOfThisCategory());
    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(11, Length.Types.values().length);
        assertEquivalentLengthInSI(1.0, new Unit(1, Length.Types.M));
        assertEquivalentLengthInSI(0.1, new Unit(1, Length.Types.DM));
        assertEquivalentLengthInSI(0.01, new Unit(1, Length.Types.CM));
        assertEquivalentLengthInSI(0.001, new Unit(1, Length.Types.MM));
        assertEquivalentLengthInSI(100.0, new Unit(1, Length.Types.HM));
        assertEquivalentLengthInSI(1000.0, new Unit(1, Length.Types.KM));
        assertEquivalentLengthInSI(Math.pow(10, -6), new Unit(1, Length.Types.UM));
        assertEquivalentLengthInSI(0.0254, new Unit(1, Length.Types.IN));
        assertEquivalentLengthInSI(0.3048, new Unit(1, Length.Types.FT));
        assertEquivalentLengthInSI(0.9144, new Unit(1, Length.Types.YD));
        assertEquivalentLengthInSI(1609.344, new Unit(1, Length.Types.MI));
    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(11, Length.Types.values().length);
        double expected = 12345.67;
        assertEquivalentLengthInSI(expected, new Unit(12345.67000000, Length.Types.M));
        assertEquivalentLengthInSI(expected, new Unit(123456.7000000, Length.Types.DM));
        assertEquivalentLengthInSI(expected, new Unit(1234567.000000, Length.Types.CM));
        assertEquivalentLengthInSI(expected, new Unit(12345670.00000, Length.Types.MM));
        assertEquivalentLengthInSI(expected, new Unit(123.4567000000, Length.Types.HM));
        assertEquivalentLengthInSI(expected, new Unit(12.34567000000, Length.Types.KM));
        assertEquivalentLengthInSI(expected, new Unit(12345670000.00, Length.Types.UM));
        assertEquivalentLengthInSI(expected, new Unit(486050, Length.Types.IN));
        assertEquivalentLengthInSI(expected, new Unit(40504.166666666666667, Length.Types.FT));
        assertEquivalentLengthInSI(expected, new Unit(13501.388888888888889, Length.Types.YD));
        assertEquivalentLengthInSI(expected, new Unit(7.6712436868686868687, Length.Types.MI));
    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(11, Length.Types.values().length);
        double expected = -12345.67;
        assertEquivalentLengthInSI(expected, new Unit(-12345.67000000, Length.Types.M));
        assertEquivalentLengthInSI(expected, new Unit(-123456.7000000, Length.Types.DM));
        assertEquivalentLengthInSI(expected, new Unit(-1234567.000000, Length.Types.CM));
        assertEquivalentLengthInSI(expected, new Unit(-12345670.00000, Length.Types.MM));
        assertEquivalentLengthInSI(expected, new Unit(-123.4567000000, Length.Types.HM));
        assertEquivalentLengthInSI(expected, new Unit(-12.34567000000, Length.Types.KM));
        assertEquivalentLengthInSI(expected, new Unit(-12345670000.00, Length.Types.UM));
        assertEquivalentLengthInSI(expected, new Unit(-486050, Length.Types.IN));
        assertEquivalentLengthInSI(expected, new Unit(-40504.166666666666667, Length.Types.FT));
        assertEquivalentLengthInSI(expected, new Unit(-13501.388888888888889, Length.Types.YD));
        assertEquivalentLengthInSI(expected, new Unit(-7.6712436868686868687, Length.Types.MI));

    }


    @Test
    public void allLengthValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(11, Length.Types.values().length);
        double expected = 0.0;
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.M));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.DM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.CM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.MM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.HM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.KM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.UM));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.IN));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.FT));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.YD));
        assertEquivalentLengthInSI(expected, new Unit(0.0, Length.Types.MI));
    }


    @Test
    public void lengthValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(11, Length.Types.values().length);
        assertEquivalentLength(0.0, Length.Types.CM, 0.0, Length.Types.M);
        assertEquivalentLength(0.0, Length.Types.MM, 0.0, Length.Types.M);
        assertEquivalentLength(0.0, Length.Types.IN, 0.0, Length.Types.M);
        assertEquivalentLength(0.0, Length.Types.IN, 0.0, Length.Types.CM);
        assertEquivalentLength(0.0, Length.Types.IN, 0.0, Length.Types.MM);
        assertEquivalentLength(0.0, Length.Types.M, 0.0, Length.Types.DM);
        assertEquivalentLength(0.0, Length.Types.M, 0.0, Length.Types.YD);
        assertEquivalentLength(0.0, Length.Types.DM, 0.0, Length.Types.CM);
        assertEquivalentLength(0.0, Length.Types.DM, 0.0, Length.Types.M);
        assertEquivalentLength(0.0, Length.Types.CM, 0.0, Length.Types.MM);
        assertEquivalentLength(0.0, Length.Types.CM, 0.0, Length.Types.MI);
        assertEquivalentLength(0.0, Length.Types.MI, 0.0, Length.Types.MM);
        assertEquivalentLength(0.0, Length.Types.CM, 0.0, Length.Types.DM);
        assertEquivalentLength(0.0, Length.Types.MM, 0.0, Length.Types.HM);
        assertEquivalentLength(0.0, Length.Types.MM, 0.0, Length.Types.CM);
        assertEquivalentLength(0.0, Length.Types.HM, 0.0, Length.Types.KM);
        assertEquivalentLength(0.0, Length.Types.HM, 0.0, Length.Types.MM);
        assertEquivalentLength(0.0, Length.Types.KM, 0.0, Length.Types.UM);
        assertEquivalentLength(0.0, Length.Types.KM, 0.0, Length.Types.HM);
        assertEquivalentLength(0.0, Length.Types.UM, 0.0, Length.Types.KM);
        assertEquivalentLength(0.0, Length.Types.UM, 0.0, Length.Types.IN);
        assertEquivalentLength(0.0, Length.Types.IN, 0.0, Length.Types.FT);
        assertEquivalentLength(0.0, Length.Types.IN, 0.0, Length.Types.UM);
        assertEquivalentLength(0.0, Length.Types.FT, 0.0, Length.Types.YD);
        assertEquivalentLength(0.0, Length.Types.FT, 0.0, Length.Types.IN);
        assertEquivalentLength(0.0, Length.Types.YD, 0.0, Length.Types.M);
        assertEquivalentLength(0.0, Length.Types.YD, 0.0, Length.Types.FT);
    }


    @Test
    public void lengthValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(11, Length.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentLength(randomValue, Length.Types.CM, randomValue / 100, Length.Types.M);
        assertEquivalentLength(randomValue, Length.Types.MM, randomValue / 1000, Length.Types.M);
        assertEquivalentLength(randomValue, Length.Types.IN, randomValue * 0.0254, Length.Types.M);
        assertEquivalentLength(randomValue, Length.Types.IN, randomValue * 2.54, Length.Types.CM);
        assertEquivalentLength(randomValue, Length.Types.IN, randomValue * 25.4, Length.Types.MM);
        assertEquivalentLength(randomValue, Length.Types.M, randomValue * 10, Length.Types.DM);
        assertEquivalentLength(randomValue, Length.Types.M, randomValue * 1.09361329833771, Length.Types.YD);
        assertEquivalentLength(randomValue, Length.Types.DM, randomValue * 10, Length.Types.CM);
        assertEquivalentLength(randomValue, Length.Types.DM, randomValue / 10, Length.Types.M);
        assertEquivalentLength(randomValue, Length.Types.CM, randomValue * 10, Length.Types.MM);
        assertEquivalentLength(randomValue, Length.Types.CM, randomValue / 10, Length.Types.DM);
        assertEquivalentLength(randomValue, Length.Types.MM, randomValue * Math.pow(10, -5), Length.Types.HM);
        assertEquivalentLength(randomValue, Length.Types.MM, randomValue / 10, Length.Types.CM);
        assertEquivalentLength(randomValue, Length.Types.HM, randomValue / 10, Length.Types.KM);
        assertEquivalentLength(randomValue, Length.Types.HM, randomValue * 100000, Length.Types.MM);
        assertEquivalentLength(randomValue, Length.Types.KM, randomValue * Math.pow(10, 9), Length.Types.UM);
        assertEquivalentLength(randomValue, Length.Types.KM, randomValue * 10, Length.Types.HM);
        assertEquivalentLength(randomValue, Length.Types.UM, randomValue / Math.pow(10, 9), Length.Types.KM);
        assertEquivalentLength(randomValue, Length.Types.UM, randomValue / 25400, Length.Types.IN);
        assertEquivalentLength(randomValue, Length.Types.IN, randomValue / 12, Length.Types.FT);
        assertEquivalentLength(randomValue, Length.Types.IN, randomValue * 25400, Length.Types.UM);
        assertEquivalentLength(randomValue, Length.Types.FT, randomValue * 1.0 / 3.0, Length.Types.YD);
        assertEquivalentLength(randomValue, Length.Types.FT, randomValue * 12, Length.Types.IN);
        assertEquivalentLength(randomValue, Length.Types.YD, randomValue * 0.9144, Length.Types.M);
        assertEquivalentLength(randomValue, Length.Types.YD, randomValue * 3, Length.Types.FT);
        assertEquivalentLength(randomValue, Length.Types.MI, randomValue * 5280, Length.Types.FT);
        assertEquivalentLength(randomValue, Length.Types.MI, randomValue * 1760, Length.Types.YD);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Length.Types.CM);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Length.Types.CM));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Length.Types.CM));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}
