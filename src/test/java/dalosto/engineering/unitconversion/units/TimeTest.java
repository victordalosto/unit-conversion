package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;


public class TimeTest {

    UnitFormula unitFormula = new Time();


    public void assertEquivalentTimeInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    public void assertEquivalentTime(double fromValue, Time.Types from, Double toValue, Time.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 60;
        UnitType unitType = Time.Types.MIN;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Time.Types.MS);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(3600.0, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Time.Types.S, outputSI.getType());
        assertEquals(3600000.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Time.Types.MS, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfVolulmeShouldBeMeterCubic() {
        assertEquals(Time.Types.S, unitFormula.getSITypeOfThisCategory());
        assertEquals(Time.Types.S, Time.Types.MIN.getSITypeOfThisCategory());
        assertEquals(Time.Types.S, new Unit(0.0, Time.Types.H).getType().getSITypeOfThisCategory());
        assertEquals(Time.Types.S, new Unit(0.0, Time.Types.DAY).getType().getSITypeOfThisCategory());
    }



    @Test
    public void allTimeValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(11, Time.Types.values().length);
        assertEquivalentTimeInSI(1.0, new Unit(1, Time.Types.S));
        assertEquivalentTimeInSI(0.001, new Unit(1, Time.Types.MS));
        assertEquivalentTimeInSI(Math.pow(10, -6), new Unit(1, Time.Types.US));
        assertEquivalentTimeInSI(60.0, new Unit(1, Time.Types.MIN));
        assertEquivalentTimeInSI(3600.0, new Unit(1, Time.Types.H));
        assertEquivalentTimeInSI(86400.0, new Unit(1, Time.Types.DAY));
        assertEquivalentTimeInSI(604800.0, new Unit(1, Time.Types.WEEK));
        assertEquivalentTimeInSI(3600.0*24*(365.0/12), new Unit(1, Time.Types.MONTH));
        assertEquivalentTimeInSI(3600.0*24*(30), new Unit(1, Time.Types.MONTH30));
        assertEquivalentTimeInSI(3600.0*24*(31), new Unit(1, Time.Types.MONTH31));
        assertEquivalentTimeInSI(3600.0*24*365.0, new Unit(1, Time.Types.YEAR));
    }


    @Test
    public void allTimeValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(11, Time.Types.values().length);
        double expected = 12345.67;
        assertEquivalentTimeInSI(expected, new Unit(12345.67, Time.Types.S));
        assertEquivalentTimeInSI(expected, new Unit(12345670, Time.Types.MS));
        assertEquivalentTimeInSI(expected, new Unit(12345670000.0, Time.Types.US));
        assertEquivalentTimeInSI(expected, new Unit(205.761166666667, Time.Types.MIN));
        assertEquivalentTimeInSI(expected, new Unit(3.42935277777778, Time.Types.H));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400, Time.Types.DAY));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400/7, Time.Types.WEEK));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400/(365.0/12), Time.Types.MONTH));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400/(30), Time.Types.MONTH30));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400/(31), Time.Types.MONTH31));
        assertEquivalentTimeInSI(expected, new Unit(12345.67/86400/(365), Time.Types.YEAR));
    }


    @Test
    public void allTimeValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(11, Time.Types.values().length);
        double expected = -12345.67;
        assertEquivalentTimeInSI(expected, new Unit(-12345.67, Time.Types.S));
        assertEquivalentTimeInSI(expected, new Unit(-12345670, Time.Types.MS));
        assertEquivalentTimeInSI(expected, new Unit(-12345670000.0, Time.Types.US));
        assertEquivalentTimeInSI(expected, new Unit(-205.761166666667, Time.Types.MIN));
        assertEquivalentTimeInSI(expected, new Unit(-3.42935277777778, Time.Types.H));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400, Time.Types.DAY));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400/7, Time.Types.WEEK));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400/(365.0/12), Time.Types.MONTH));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400/(30), Time.Types.MONTH30));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400/(31), Time.Types.MONTH31));
        assertEquivalentTimeInSI(expected, new Unit(-12345.67/86400/(365), Time.Types.YEAR));
    }


    @Test
    public void allTimeValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(11, Time.Types.values().length);
        double expected = 0.0;
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.S));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.MS));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.US));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.MIN));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.H));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.DAY));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.WEEK));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.MONTH));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.MONTH30));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.MONTH31));
        assertEquivalentTimeInSI(expected, new Unit(0.0, Time.Types.YEAR));
    }


    @Test
    public void TimeValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(11, Time.Types.values().length);
        assertEquivalentTime(0.0, Time.Types.S, 0.0, Time.Types.S);
        assertEquivalentTime(0.0, Time.Types.MS, 0.0, Time.Types.S);
        assertEquivalentTime(0.0, Time.Types.US, 0.0, Time.Types.MS);
        assertEquivalentTime(0.0, Time.Types.MIN, 0.0, Time.Types.US);
        assertEquivalentTime(0.0, Time.Types.H, 0.0, Time.Types.MIN);
        assertEquivalentTime(0.0, Time.Types.DAY, 0.0, Time.Types.H);
        assertEquivalentTime(0.0, Time.Types.WEEK, 0.0, Time.Types.DAY);
        assertEquivalentTime(0.0, Time.Types.MONTH, 0.0, Time.Types.WEEK);
        assertEquivalentTime(0.0, Time.Types.MONTH30, 0.0, Time.Types.MONTH);
        assertEquivalentTime(0.0, Time.Types.MONTH31, 0.0, Time.Types.MONTH30);
        assertEquivalentTime(0.0, Time.Types.YEAR, 0.0, Time.Types.MONTH31);
    }


    @Test
    public void TimeValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(11, Time.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentTime(randomValue, Time.Types.S, randomValue, Time.Types.S);
        assertEquivalentTime(randomValue, Time.Types.MS, randomValue * 0.001, Time.Types.S);
        assertEquivalentTime(randomValue, Time.Types.US, randomValue * 0.001, Time.Types.MS);
        assertEquivalentTime(randomValue, Time.Types.MIN, randomValue/24.0/60.0, Time.Types.DAY);
        assertEquivalentTime(randomValue, Time.Types.H, randomValue*60.0, Time.Types.MIN);
        assertEquivalentTime(randomValue, Time.Types.DAY, randomValue*24.0, Time.Types.H);
        assertEquivalentTime(randomValue, Time.Types.WEEK, randomValue*7.0, Time.Types.DAY);
        assertEquivalentTime(randomValue, Time.Types.MONTH,         randomValue * 365.0/12/7, Time.Types.WEEK);
        assertEquivalentTime(randomValue, Time.Types.MONTH30, randomValue*30.0/(365.0/12), Time.Types.MONTH);
        assertEquivalentTime(randomValue, Time.Types.MONTH31, randomValue*31.0/30.0, Time.Types.MONTH30);
        assertEquivalentTime(randomValue, Time.Types.YEAR, randomValue*365.0/31, Time.Types.MONTH31);
        assertEquivalentTime(randomValue, Time.Types.YEAR, randomValue*365.0/30, Time.Types.MONTH30);
        assertEquivalentTime(randomValue, Time.Types.YEAR, randomValue*365.0/(365.0/12), Time.Types.MONTH);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Time.Types.S);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Time.Types.S));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Time.Types.S));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}