package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.TestMetrics;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.interfaces.UnitFormulas;


@SpringBootTest
public class TestLength {


    @Autowired
    @Qualifier("LengthFormula")
    UnitFormulas unitFormulas;



    void assertEqualLengthInSI(double expected, Unit actual) {
        assertEquals(expected, unitFormulas.buildUnitToSI(actual).getValue(), TestMetrics.tolerance);
    }

    void assertEqualLength(double fromValue, Length.Types from, Double toValue, Length.Types to) {
        assertEquals(toValue, unitFormulas.buildUnitIntoAnotherType(new Unit(fromValue, from), to).getValue(), TestMetrics.tolerance);
    }


    @Test
    void SIUnitTypeOfLengthShouldBeMeter() {
        assertEquals(Length.Types.M, Length.Types.CM.getSITypeOfThisCategory());
        assertEquals(Length.Types.M, Length.Types.DM.getSITypeOfThisCategory());
        assertEquals(Length.Types.M, new Unit(0.0, Length.Types.FT).getUnitType().getSITypeOfThisCategory());
        assertEquals(Length.Types.M, new Unit(0.0, Length.Types.KM).getUnitType().getSITypeOfThisCategory());
    }


    @Test
    void AllLengthValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(10, Length.Types.values().length);
        assertEqualLengthInSI(1.0, new Unit(1, Length.Types.M));
        assertEqualLengthInSI(0.1, new Unit(1, Length.Types.DM));
        assertEqualLengthInSI(0.01, new Unit(1, Length.Types.CM));
        assertEqualLengthInSI(0.001, new Unit(1, Length.Types.MM));
        assertEqualLengthInSI(100.0, new Unit(1, Length.Types.HM));
        assertEqualLengthInSI(1000.0, new Unit(1, Length.Types.KM));
        assertEqualLengthInSI(Math.pow(10, -6), new Unit(1, Length.Types.UM));
        assertEqualLengthInSI(0.0254, new Unit(1, Length.Types.IN));
        assertEqualLengthInSI(0.3048, new Unit(1, Length.Types.FT));
        assertEqualLengthInSI(0.9144, new Unit(1, Length.Types.YD));
    }


    @Test
    void AllLengthValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(10, Length.Types.values().length);
        double expected = 12345.67;
        assertEqualLengthInSI(expected, new Unit(12345.67000000, Length.Types.M));
        assertEqualLengthInSI(expected, new Unit(123456.7000000, Length.Types.DM));
        assertEqualLengthInSI(expected, new Unit(1234567.000000, Length.Types.CM));
        assertEqualLengthInSI(expected, new Unit(12345670.00000, Length.Types.MM));
        assertEqualLengthInSI(expected, new Unit(123.4567000000, Length.Types.HM));
        assertEqualLengthInSI(expected, new Unit(12.34567000000, Length.Types.KM));
        assertEqualLengthInSI(expected, new Unit(12345670000.00, Length.Types.UM));
        assertEqualLengthInSI(expected, new Unit(486050, Length.Types.IN));
        assertEqualLengthInSI(expected, new Unit(40504.166666666666667, Length.Types.FT));
        assertEqualLengthInSI(expected, new Unit(13501.388888888888889, Length.Types.YD));
    }


    @Test
    void AllLengthValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(10, Length.Types.values().length);
        double expected = -12345.67;
        assertEqualLengthInSI(expected, new Unit(-12345.67000000, Length.Types.M));
        assertEqualLengthInSI(expected, new Unit(-123456.7000000, Length.Types.DM));
        assertEqualLengthInSI(expected, new Unit(-1234567.000000, Length.Types.CM));
        assertEqualLengthInSI(expected, new Unit(-12345670.00000, Length.Types.MM));
        assertEqualLengthInSI(expected, new Unit(-123.4567000000, Length.Types.HM));
        assertEqualLengthInSI(expected, new Unit(-12.34567000000, Length.Types.KM));
        assertEqualLengthInSI(expected, new Unit(-12345670000.00, Length.Types.UM));
        assertEqualLengthInSI(expected, new Unit(-486050, Length.Types.IN));
        assertEqualLengthInSI(expected, new Unit(-40504.166666666666667, Length.Types.FT));
        assertEqualLengthInSI(expected, new Unit(-13501.388888888888889, Length.Types.YD));
    }


    @Test
    void AllLengthValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(10, Length.Types.values().length);
        double expected = 0.0;
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.M));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.DM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.CM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.MM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.HM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.KM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.UM));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.IN));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.FT));
        assertEqualLengthInSI(expected, new Unit(0.0, Length.Types.YD));
    }


    @Test
    void LengthValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(10, Length.Types.values().length);
        assertEqualLength(0.0, Length.Types.CM, 0.0, Length.Types.M);
        assertEqualLength(0.0, Length.Types.MM, 0.0, Length.Types.M);
        assertEqualLength(0.0, Length.Types.IN, 0.0, Length.Types.M);
        assertEqualLength(0.0, Length.Types.IN, 0.0, Length.Types.CM);
        assertEqualLength(0.0, Length.Types.IN, 0.0, Length.Types.MM);
        assertEqualLength(0.0, Length.Types.M, 0.0, Length.Types.DM);
        assertEqualLength(0.0, Length.Types.M, 0.0, Length.Types.YD);
        assertEqualLength(0.0, Length.Types.DM, 0.0, Length.Types.CM);
        assertEqualLength(0.0, Length.Types.DM, 0.0, Length.Types.M);
        assertEqualLength(0.0, Length.Types.CM, 0.0, Length.Types.MM);
        assertEqualLength(0.0, Length.Types.CM, 0.0, Length.Types.DM);
        assertEqualLength(0.0, Length.Types.MM, 0.0, Length.Types.HM);
        assertEqualLength(0.0, Length.Types.MM, 0.0, Length.Types.CM);
        assertEqualLength(0.0, Length.Types.HM, 0.0, Length.Types.KM);
        assertEqualLength(0.0, Length.Types.HM, 0.0, Length.Types.MM);
        assertEqualLength(0.0, Length.Types.KM, 0.0, Length.Types.UM);
        assertEqualLength(0.0, Length.Types.KM, 0.0, Length.Types.HM);
        assertEqualLength(0.0, Length.Types.UM, 0.0, Length.Types.KM);
        assertEqualLength(0.0, Length.Types.UM, 0.0, Length.Types.IN);
        assertEqualLength(0.0, Length.Types.IN, 0.0, Length.Types.FT);
        assertEqualLength(0.0, Length.Types.IN, 0.0, Length.Types.UM);
        assertEqualLength(0.0, Length.Types.FT, 0.0, Length.Types.YD);
        assertEqualLength(0.0, Length.Types.FT, 0.0, Length.Types.IN);
        assertEqualLength(0.0, Length.Types.YD, 0.0, Length.Types.M);
        assertEqualLength(0.0, Length.Types.YD, 0.0, Length.Types.FT);
    }


    @Test
    void LengthValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(10, Length.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEqualLength(randomValue, Length.Types.CM, randomValue / 100, Length.Types.M);
        assertEqualLength(randomValue, Length.Types.MM, randomValue / 1000, Length.Types.M);
        assertEqualLength(randomValue, Length.Types.IN, randomValue * 0.0254, Length.Types.M);
        assertEqualLength(randomValue, Length.Types.IN, randomValue * 2.54, Length.Types.CM);
        assertEqualLength(randomValue, Length.Types.IN, randomValue * 25.4, Length.Types.MM);
        assertEqualLength(randomValue, Length.Types.M, randomValue * 10, Length.Types.DM);
        assertEqualLength(randomValue, Length.Types.M, randomValue * 1.09361329833771, Length.Types.YD);
        assertEqualLength(randomValue, Length.Types.DM, randomValue * 10, Length.Types.CM);
        assertEqualLength(randomValue, Length.Types.DM, randomValue / 10, Length.Types.M);
        assertEqualLength(randomValue, Length.Types.CM, randomValue * 10, Length.Types.MM);
        assertEqualLength(randomValue, Length.Types.CM, randomValue / 10, Length.Types.DM);
        assertEqualLength(randomValue, Length.Types.MM, randomValue * Math.pow(10, -5), Length.Types.HM);
        assertEqualLength(randomValue, Length.Types.MM, randomValue / 10, Length.Types.CM);
        assertEqualLength(randomValue, Length.Types.HM, randomValue / 10, Length.Types.KM);
        assertEqualLength(randomValue, Length.Types.HM, randomValue * 100000, Length.Types.MM);
        assertEqualLength(randomValue, Length.Types.KM, randomValue * Math.pow(10, 9), Length.Types.UM);
        assertEqualLength(randomValue, Length.Types.KM, randomValue * 10, Length.Types.HM);
        assertEqualLength(randomValue, Length.Types.UM, randomValue / Math.pow(10, 9), Length.Types.KM);
        assertEqualLength(randomValue, Length.Types.UM, randomValue / 25400, Length.Types.IN);
        assertEqualLength(randomValue, Length.Types.IN, randomValue / 12, Length.Types.FT);
        assertEqualLength(randomValue, Length.Types.IN, randomValue * 25400, Length.Types.UM);
        assertEqualLength(randomValue, Length.Types.FT, randomValue * 1.0/3.0   , Length.Types.YD);
        assertEqualLength(randomValue, Length.Types.FT, randomValue * 12, Length.Types.IN);
        assertEqualLength(randomValue, Length.Types.YD, randomValue * 0.9144, Length.Types.M);
        assertEqualLength(randomValue, Length.Types.YD, randomValue * 3, Length.Types.FT);
    }


    @Test
    void UnitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Length.Types.CM);
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitIntoAnotherType(null, Length.Types.CM));
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitIntoAnotherType(null, null));
    }


    @Test
    void UnitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitIntoAnotherType(unit, Length.Types.CM));
        assertThrows(UnitException.class, () -> unitFormulas.buildUnitIntoAnotherType(unit, null));
    }

}
