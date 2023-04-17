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
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


@SpringBootTest
public class TestArea {


    @Autowired
    @Qualifier("AreaFormula")
    UnitFormula unitFormula;


    void assertEquivalentAreaInSI(double expected, Unit actual) {
        TestMetrics.assertEquavalentInSI(expected, actual, unitFormula);
    }

    void assertEquivalentArea(double fromValue, Area.Types from, Double toValue, Area.Types to) {
        TestMetrics.assertEquivalentUnit(fromValue, from, toValue, to, unitFormula);
    }


    @Test
    void SIUnitTypeOfAreaShouldBeMeter() {
        assertEquals(Area.Types.M_2, Area.Types.CM_2.getSITypeOfThisCategory());
        assertEquals(Area.Types.M_2, Area.Types.DM_2.getSITypeOfThisCategory());
        assertEquals(Area.Types.M_2, new Unit(0.0, Area.Types.FT_2).getUnitType().getSITypeOfThisCategory());
        assertEquals(Area.Types.M_2, new Unit(0.0, Area.Types.KM_2).getUnitType().getSITypeOfThisCategory());
    }


    @Test
    void AllAreaValuesShouldBeTestedAndAreCorrectInSIForUnitaryValue() {
        assertEquals(10, Area.Types.values().length);
        assertEquivalentAreaInSI(1.0, new Unit(1, Area.Types.M_2));
        assertEquivalentAreaInSI(0.1*0.1, new Unit(1, Area.Types.DM_2));
        assertEquivalentAreaInSI(0.01*0.01, new Unit(1, Area.Types.CM_2));
        assertEquivalentAreaInSI(0.001*0.001, new Unit(1, Area.Types.MM_2));
        assertEquivalentAreaInSI(100.0*100.0, new Unit(1, Area.Types.HM_2));
        assertEquivalentAreaInSI(1000.0*1000.0, new Unit(1, Area.Types.KM_2));
        assertEquivalentAreaInSI(Math.pow(10, -12), new Unit(1, Area.Types.UM_2));
        assertEquivalentAreaInSI(0.0254*0.0254, new Unit(1, Area.Types.IN_2));
        assertEquivalentAreaInSI(0.3048*0.3048, new Unit(1, Area.Types.FT_2));
        assertEquivalentAreaInSI(0.9144*0.9144, new Unit(1, Area.Types.YD_2));
    }


    @Test
    void AllAreaValuesShouldBeTestedAndAreCorrectInSIForPostiveValue() {
        assertEquals(10, Area.Types.values().length);
        double expected = 12345.67;
        assertEquivalentAreaInSI(expected, new Unit(12345.67000000, Area.Types.M_2));
        assertEquivalentAreaInSI(expected, new Unit(1234567.000000, Area.Types.DM_2));
        assertEquivalentAreaInSI(expected, new Unit(123456700.0000, Area.Types.CM_2));
        assertEquivalentAreaInSI(expected, new Unit(12345670000.00, Area.Types.MM_2));
        assertEquivalentAreaInSI(expected, new Unit(1.234567000000, Area.Types.HM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.01234567000000, Area.Types.KM_2));
        assertEquivalentAreaInSI(expected, new Unit(1.234567 * Math.pow(10, 16), Area.Types.UM_2));
        assertEquivalentAreaInSI(expected, new Unit(19135826.771653545, Area.Types.IN_2));
        assertEquivalentAreaInSI(expected, new Unit(132887.6859142607, Area.Types.FT_2));
        assertEquivalentAreaInSI(expected, new Unit(14765.298434917858, Area.Types.YD_2));
    }


    @Test
    void AllAreaValuesShouldBeTestedAndAreCorrectInSIForNegativeValue() {
        assertEquals(10, Area.Types.values().length);
        double expected = -12345.67;
        assertEquivalentAreaInSI(expected, new Unit(-12345.67000000, Area.Types.M_2));
        assertEquivalentAreaInSI(expected, new Unit(-1234567.000000, Area.Types.DM_2));
        assertEquivalentAreaInSI(expected, new Unit(-123456700.0000, Area.Types.CM_2));
        assertEquivalentAreaInSI(expected, new Unit(-12345670000.00, Area.Types.MM_2));
        assertEquivalentAreaInSI(expected, new Unit(-1.234567000000, Area.Types.HM_2));
        assertEquivalentAreaInSI(expected, new Unit(-0.01234567000000, Area.Types.KM_2));
        assertEquivalentAreaInSI(expected, new Unit(-1.234567 * Math.pow(10, 16), Area.Types.UM_2));
        assertEquivalentAreaInSI(expected, new Unit(-19135826.771653545, Area.Types.IN_2));
        assertEquivalentAreaInSI(expected, new Unit(-132887.6859142607, Area.Types.FT_2));
        assertEquivalentAreaInSI(expected, new Unit(-14765.298434917858, Area.Types.YD_2));
    }


    @Test
    void AllAreaValuesShouldBeTestedAndAreCorrectInSIForZero() {
        assertEquals(10, Area.Types.values().length);
        double expected = 0.0;
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.M_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.DM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.CM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.MM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.HM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.KM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.UM_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.IN_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.FT_2));
        assertEquivalentAreaInSI(expected, new Unit(0.0, Area.Types.YD_2));
    }


    @Test
    void AreaValuesShouldBeCorrectForConversionBetweenTypesUsingZero() {
        assertEquals(10, Area.Types.values().length);
        assertEquivalentArea(0.0, Area.Types.CM_2, 0.0, Area.Types.M_2);
        assertEquivalentArea(0.0, Area.Types.MM_2, 0.0, Area.Types.M_2);
        assertEquivalentArea(0.0, Area.Types.IN_2, 0.0, Area.Types.M_2);
        assertEquivalentArea(0.0, Area.Types.IN_2, 0.0, Area.Types.CM_2);
        assertEquivalentArea(0.0, Area.Types.IN_2, 0.0, Area.Types.MM_2);
        assertEquivalentArea(0.0, Area.Types.M_2, 0.0, Area.Types.DM_2);
        assertEquivalentArea(0.0, Area.Types.M_2, 0.0, Area.Types.YD_2);
        assertEquivalentArea(0.0, Area.Types.DM_2, 0.0, Area.Types.CM_2);
        assertEquivalentArea(0.0, Area.Types.DM_2, 0.0, Area.Types.M_2);
        assertEquivalentArea(0.0, Area.Types.CM_2, 0.0, Area.Types.MM_2);
        assertEquivalentArea(0.0, Area.Types.CM_2, 0.0, Area.Types.DM_2);
        assertEquivalentArea(0.0, Area.Types.MM_2, 0.0, Area.Types.HM_2);
        assertEquivalentArea(0.0, Area.Types.MM_2, 0.0, Area.Types.CM_2);
        assertEquivalentArea(0.0, Area.Types.HM_2, 0.0, Area.Types.KM_2);
        assertEquivalentArea(0.0, Area.Types.HM_2, 0.0, Area.Types.MM_2);
        assertEquivalentArea(0.0, Area.Types.KM_2, 0.0, Area.Types.UM_2);
        assertEquivalentArea(0.0, Area.Types.KM_2, 0.0, Area.Types.HM_2);
        assertEquivalentArea(0.0, Area.Types.UM_2, 0.0, Area.Types.KM_2);
        assertEquivalentArea(0.0, Area.Types.UM_2, 0.0, Area.Types.IN_2);
        assertEquivalentArea(0.0, Area.Types.IN_2, 0.0, Area.Types.FT_2);
        assertEquivalentArea(0.0, Area.Types.IN_2, 0.0, Area.Types.UM_2);
        assertEquivalentArea(0.0, Area.Types.FT_2, 0.0, Area.Types.YD_2);
        assertEquivalentArea(0.0, Area.Types.FT_2, 0.0, Area.Types.IN_2);
        assertEquivalentArea(0.0, Area.Types.YD_2, 0.0, Area.Types.M_2);
        assertEquivalentArea(0.0, Area.Types.YD_2, 0.0, Area.Types.FT_2);
    }


    @Test
    void AreaValuesShouldBeCorrectForConversionBetweenTypesUsingRandomValues() {
        assertEquals(10, Area.Types.values().length);
        double randomValue = Math.random() * 100;
        assertEquivalentArea(randomValue, Area.Types.CM_2, randomValue / 100  / 100, Area.Types.M_2);
        assertEquivalentArea(randomValue, Area.Types.MM_2, randomValue / 1000  / 1000, Area.Types.M_2);
        assertEquivalentArea(randomValue, Area.Types.IN_2, randomValue * 0.0254 * 0.0254, Area.Types.M_2);
        assertEquivalentArea(randomValue, Area.Types.IN_2, randomValue * 2.54 * 2.54, Area.Types.CM_2);
        assertEquivalentArea(randomValue, Area.Types.IN_2, randomValue * 25.4 * 25.4, Area.Types.MM_2);
        assertEquivalentArea(randomValue, Area.Types.M_2, randomValue * 10 * 10, Area.Types.DM_2);
        assertEquivalentArea(randomValue, Area.Types.M_2, randomValue * 1.09361329833771 * 1.09361329833771, Area.Types.YD_2);
        assertEquivalentArea(randomValue, Area.Types.DM_2, randomValue * 10 * 10, Area.Types.CM_2);
        assertEquivalentArea(randomValue, Area.Types.DM_2, randomValue / 10 / 10, Area.Types.M_2);
        assertEquivalentArea(randomValue, Area.Types.CM_2, randomValue * 10 * 10, Area.Types.MM_2);
        assertEquivalentArea(randomValue, Area.Types.CM_2, randomValue / 10 / 10, Area.Types.DM_2);
        assertEquivalentArea(randomValue, Area.Types.MM_2, randomValue * Math.pow(10, -10), Area.Types.HM_2);
        assertEquivalentArea(randomValue, Area.Types.MM_2, randomValue / 10 / 10, Area.Types.CM_2);
        assertEquivalentArea(randomValue, Area.Types.HM_2, randomValue / 10 / 10, Area.Types.KM_2);
        assertEquivalentArea(randomValue, Area.Types.HM_2, randomValue * Math.pow(10, 10), Area.Types.MM_2);
        assertEquivalentArea(randomValue, Area.Types.KM_2, randomValue * Math.pow(10, 18), Area.Types.UM_2);
        assertEquivalentArea(randomValue, Area.Types.KM_2, randomValue * 10 *10, Area.Types.HM_2);
        assertEquivalentArea(randomValue, Area.Types.UM_2, randomValue / Math.pow(10, 18), Area.Types.KM_2);
        assertEquivalentArea(randomValue, Area.Types.UM_2, randomValue / 25400 / 25400, Area.Types.IN_2);
        assertEquivalentArea(randomValue, Area.Types.IN_2, randomValue / 12 / 12, Area.Types.FT_2);
        assertEquivalentArea(randomValue, Area.Types.IN_2, randomValue * 25400 * 25400, Area.Types.UM_2);
        assertEquivalentArea(randomValue, Area.Types.FT_2, randomValue * 1.0/3.0 * 1.0/3.0, Area.Types.YD_2);
        assertEquivalentArea(randomValue, Area.Types.FT_2, randomValue * 12 * 12, Area.Types.IN_2);
        assertEquivalentArea(randomValue, Area.Types.YD_2, randomValue * 0.9144 * 0.9144, Area.Types.M_2);
        assertEquivalentArea(randomValue, Area.Types.YD_2, randomValue * 3 * 3, Area.Types.FT_2);
    }


    @Test
    void UnitExceptionShouldBeThrownWhenNullValuesArePassed() {
        Unit unit = new Unit(1, Area.Types.CM_2);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, Area.Types.CM_2));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(null, null));
    }


    @Test
    void UnitExceptionShouldBeThrownWhenNullUnitTypeValuesArePassed() {
        Unit unit = new Unit(1, null);
        assertThrows(UnitException.class, () -> unitFormula.buildUnitToSI(unit));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, Area.Types.CM_2));
        assertThrows(UnitException.class, () -> unitFormula.buildUnitIntoAnotherType(unit, null));
    }

}
