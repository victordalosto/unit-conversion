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
public class VolumeTest {

    @Autowired
    @Qualifier("volume")
    UnitFormula unitFormula;


    void assertEquivalentVolumeInSI(double expected, Unit actual) {
        MetricTest.assertEquavalentInSI(expected, actual, unitFormula);
    }


    void assertEquivalentVolume(double fromValue, Volume.Types from, Double toValue, Volume.Types to) {
        MetricTest.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    public void shouldBeAbleToCreateAUnitAndConvertToAnotherUnitWithoutChangingTheOriginalType() {
        double value = 25000000.0;
        UnitType unitType = Volume.Types.MM3;
        Unit unit = new Unit(value, unitType);
        Unit outputSI = unitFormula.buildUnitToSI(unit);
        Unit outputAnotherType = unitFormula.buildUnitIntoAnotherType(unit, Volume.Types.CM3);
        assertEquals(value, unit.getValue(), MetricTest.tolerance);
        assertEquals(unitType, unit.getType());
        assertEquals(0.025, outputSI.getValue(), MetricTest.tolerance);
        assertEquals(Volume.Types.M3, outputSI.getType());
        assertEquals(25000.0, outputAnotherType.getValue(), MetricTest.tolerance);
        assertEquals(Volume.Types.CM3, outputAnotherType.getType());
    }


    @Test
    public void SIUnitTypeOfVolulmeShouldBeMeterCubic() {
        assertEquals(Volume.Types.M3, unitFormula.getSITypeOfThisCategory());
        assertEquals(Volume.Types.M3, Volume.Types.DM3.getSITypeOfThisCategory());
        assertEquals(Volume.Types.M3, new Unit(0.0, Volume.Types.FT3).getType().getSITypeOfThisCategory());
        assertEquals(Volume.Types.M3, new Unit(0.0, Volume.Types.KM3).getType().getSITypeOfThisCategory());
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(12, Volume.Types.values().length);
        assertEquivalentVolumeInSI(1.0, new Unit(1, Volume.Types.M3));
        assertEquivalentVolumeInSI(0.1 * 0.1 * 0.1, new Unit(1, Volume.Types.DM3));
        assertEquivalentVolumeInSI(0.01 * 0.01 * 0.01, new Unit(1, Volume.Types.CM3));
        assertEquivalentVolumeInSI(0.001 * 0.001 * 0.001, new Unit(1, Volume.Types.MM3));
        assertEquivalentVolumeInSI(100.0 * 100.0 * 100.0, new Unit(1, Volume.Types.HM3));
        assertEquivalentVolumeInSI(1000.0 * 1000.0 * 1000.0, new Unit(1, Volume.Types.KM3));
        assertEquivalentVolumeInSI(Math.pow(10, -18), new Unit(1, Volume.Types.UM3));
        assertEquivalentVolumeInSI(0.0254 * 0.0254 * 0.0254, new Unit(1, Volume.Types.IN3));
        assertEquivalentVolumeInSI(0.3048 * 0.3048 * 0.3048, new Unit(1, Volume.Types.FT3));
        assertEquivalentVolumeInSI(0.9144 * 0.9144 * 0.9144, new Unit(1, Volume.Types.YD3));
        assertEquivalentVolumeInSI(Math.pow(10, -6), new Unit(1, Volume.Types.ML));
        assertEquivalentVolumeInSI(0.001, new Unit(1, Volume.Types.L));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(12, Volume.Types.values().length);
        double expected = 12345.67;
        assertEquivalentVolumeInSI(expected, new Unit(12345.67000000, Volume.Types.M3));
        assertEquivalentVolumeInSI(expected, new Unit(12345670.00000, Volume.Types.DM3));
        assertEquivalentVolumeInSI(expected, new Unit(12345670000.00, Volume.Types.CM3));
        assertEquivalentVolumeInSI(expected, new Unit(1.234567 * Math.pow(10, 13), Volume.Types.MM3));
        assertEquivalentVolumeInSI(expected, new Unit(.0123456700000, Volume.Types.HM3));
        assertEquivalentVolumeInSI(expected, new Unit(1.234567 * Math.pow(10, -5), Volume.Types.KM3));
        assertEquivalentVolumeInSI(expected, new Unit(12345.67 * Math.pow(10, 18), Volume.Types.UM3));
        assertEquivalentVolumeInSI(expected, new Unit(753379006.75801, Volume.Types.IN3));
        assertEquivalentVolumeInSI(expected, new Unit(435983.22150348, Volume.Types.FT3));
        assertEquivalentVolumeInSI(expected, new Unit(16147.526722351, Volume.Types.YD3));
        assertEquivalentVolumeInSI(expected, new Unit(12345670.000000, Volume.Types.L));
        assertEquivalentVolumeInSI(expected, new Unit(12345670000.000, Volume.Types.ML));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(12, Volume.Types.values().length);
        double expected = -12345.67;
        assertEquivalentVolumeInSI(expected, new Unit(-12345.67000000, Volume.Types.M3));
        assertEquivalentVolumeInSI(expected, new Unit(-12345670.00000, Volume.Types.DM3));
        assertEquivalentVolumeInSI(expected, new Unit(-12345670000.00, Volume.Types.CM3));
        assertEquivalentVolumeInSI(expected, new Unit(-1.234567 * Math.pow(10, 13), Volume.Types.MM3));
        assertEquivalentVolumeInSI(expected, new Unit(-.0123456700000, Volume.Types.HM3));
        assertEquivalentVolumeInSI(expected, new Unit(-1.234567 * Math.pow(10, -5), Volume.Types.KM3));
        assertEquivalentVolumeInSI(expected, new Unit(-12345.67 * Math.pow(10, 18), Volume.Types.UM3));
        assertEquivalentVolumeInSI(expected, new Unit(-753379006.75801, Volume.Types.IN3));
        assertEquivalentVolumeInSI(expected, new Unit(-435983.22150348, Volume.Types.FT3));
        assertEquivalentVolumeInSI(expected, new Unit(-16147.526722351, Volume.Types.YD3));
        assertEquivalentVolumeInSI(expected, new Unit(-12345670.000000, Volume.Types.L));
        assertEquivalentVolumeInSI(expected, new Unit(-12345670000.000, Volume.Types.ML));
    }


    @Test
    public void allVolumeValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(12, Volume.Types.values().length);
        double expected = 0.0;
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.M3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.DM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.CM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.MM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.HM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.KM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.UM3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.IN3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.FT3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.YD3));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.ML));
        assertEquivalentVolumeInSI(expected, new Unit(0.0, Volume.Types.L));
    }


    @Test
    public void volumeValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(12, Volume.Types.values().length);
        assertEquivalentVolume(0.0, Volume.Types.L, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.L, 0.0, Volume.Types.ML);
        assertEquivalentVolume(0.0, Volume.Types.L, 0.0, Volume.Types.CM3);
        assertEquivalentVolume(0.0, Volume.Types.ML, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.ML, 0.0, Volume.Types.DM3);
        assertEquivalentVolume(0.0, Volume.Types.ML, 0.0, Volume.Types.IN3);
        assertEquivalentVolume(0.0, Volume.Types.CM3, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.MM3, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.IN3, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.IN3, 0.0, Volume.Types.CM3);
        assertEquivalentVolume(0.0, Volume.Types.IN3, 0.0, Volume.Types.MM3);
        assertEquivalentVolume(0.0, Volume.Types.M3, 0.0, Volume.Types.DM3);
        assertEquivalentVolume(0.0, Volume.Types.M3, 0.0, Volume.Types.YD3);
        assertEquivalentVolume(0.0, Volume.Types.DM3, 0.0, Volume.Types.CM3);
        assertEquivalentVolume(0.0, Volume.Types.DM3, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.CM3, 0.0, Volume.Types.MM3);
        assertEquivalentVolume(0.0, Volume.Types.CM3, 0.0, Volume.Types.DM3);
        assertEquivalentVolume(0.0, Volume.Types.MM3, 0.0, Volume.Types.HM3);
        assertEquivalentVolume(0.0, Volume.Types.MM3, 0.0, Volume.Types.CM3);
        assertEquivalentVolume(0.0, Volume.Types.HM3, 0.0, Volume.Types.KM3);
        assertEquivalentVolume(0.0, Volume.Types.HM3, 0.0, Volume.Types.MM3);
        assertEquivalentVolume(0.0, Volume.Types.KM3, 0.0, Volume.Types.UM3);
        assertEquivalentVolume(0.0, Volume.Types.KM3, 0.0, Volume.Types.HM3);
        assertEquivalentVolume(0.0, Volume.Types.UM3, 0.0, Volume.Types.KM3);
        assertEquivalentVolume(0.0, Volume.Types.UM3, 0.0, Volume.Types.IN3);
        assertEquivalentVolume(0.0, Volume.Types.IN3, 0.0, Volume.Types.FT3);
        assertEquivalentVolume(0.0, Volume.Types.IN3, 0.0, Volume.Types.UM3);
        assertEquivalentVolume(0.0, Volume.Types.FT3, 0.0, Volume.Types.YD3);
        assertEquivalentVolume(0.0, Volume.Types.FT3, 0.0, Volume.Types.IN3);
        assertEquivalentVolume(0.0, Volume.Types.YD3, 0.0, Volume.Types.M3);
        assertEquivalentVolume(0.0, Volume.Types.YD3, 0.0, Volume.Types.FT3);
    }


    @Test
    public void volumeValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(12, Volume.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentVolume(randomValue, Volume.Types.L, randomValue * 1000, Volume.Types.CM3);
        assertEquivalentVolume(randomValue, Volume.Types.L, randomValue * 61.023744094732283952756881891717, Volume.Types.IN3);
        assertEquivalentVolume(randomValue, Volume.Types.L, randomValue * 0.001, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.ML, randomValue / 100 / 100 / 100, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.ML, randomValue, Volume.Types.CM3);
        assertEquivalentVolume(randomValue, Volume.Types.ML, randomValue / 16.38706400, Volume.Types.IN3);
        assertEquivalentVolume(randomValue, Volume.Types.CM3, randomValue / 100 / 100 / 100, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.MM3, randomValue / 1000 / 1000 / 1000, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.IN3, randomValue * 0.0254 * 0.0254 * 0.0254, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.IN3, randomValue * 2.54 * 2.54 * 2.54, Volume.Types.CM3);
        assertEquivalentVolume(randomValue, Volume.Types.IN3, randomValue * 16387.064, Volume.Types.MM3);
        assertEquivalentVolume(randomValue, Volume.Types.M3, randomValue * 10 * 10 * 10, Volume.Types.DM3);
        assertEquivalentVolume(randomValue, Volume.Types.M3, randomValue * 1.3079506193143922314977040871853, Volume.Types.YD3);
        assertEquivalentVolume(randomValue, Volume.Types.DM3, randomValue * 10 * 10 * 10, Volume.Types.CM3);
        assertEquivalentVolume(randomValue, Volume.Types.DM3, randomValue / 10 / 10 / 10, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.CM3, randomValue * 10 * 10 * 10, Volume.Types.MM3);
        assertEquivalentVolume(randomValue, Volume.Types.CM3, randomValue / 10 / 10 / 10, Volume.Types.DM3);
        assertEquivalentVolume(randomValue, Volume.Types.MM3, randomValue * Math.pow(10, -15), Volume.Types.HM3);
        assertEquivalentVolume(randomValue, Volume.Types.MM3, randomValue / 10 / 10  / 10, Volume.Types.CM3);
        assertEquivalentVolume(randomValue, Volume.Types.HM3, randomValue / 10 / 10  / 10, Volume.Types.KM3);
        assertEquivalentVolume(randomValue, Volume.Types.HM3, randomValue * Math.pow(10, 15), Volume.Types.MM3);
        assertEquivalentVolume(randomValue, Volume.Types.KM3, randomValue * 10 * 10 * 10, Volume.Types.HM3);
        assertEquivalentVolume(randomValue, Volume.Types.UM3, randomValue / Math.pow(10, 27), Volume.Types.KM3);
        assertEquivalentVolume(randomValue, Volume.Types.UM3, randomValue / 25400 / 25400 / 25400, Volume.Types.IN3);
        assertEquivalentVolume(randomValue, Volume.Types.IN3, randomValue / 12 / 12 /12, Volume.Types.FT3);
        assertEquivalentVolume(randomValue, Volume.Types.FT3, randomValue * 1.0 / 3.0 / 3.0 / 3.0, Volume.Types.YD3);
        assertEquivalentVolume(randomValue, Volume.Types.FT3, randomValue * 12 * 12 * 12, Volume.Types.IN3);
        assertEquivalentVolume(randomValue, Volume.Types.YD3, randomValue * 0.9144 * 0.9144 * 0.9144, Volume.Types.M3);
        assertEquivalentVolume(randomValue, Volume.Types.YD3, randomValue * 3 * 3 * 3, Volume.Types.FT3);
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Volume.Types.CM3);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Volume.Types.CM3));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    public void unitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Volume.Types.CM3));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}