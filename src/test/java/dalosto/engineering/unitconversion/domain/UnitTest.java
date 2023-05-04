package dalosto.engineering.unitconversion.domain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.units.Length;


@SpringBootTest
public class UnitTest {

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void unitConstructorHasValidProperties() {
        for (UnitFormula formula : formulas) {
            for (UnitType type : formula.getAllUnitTypesOfThisCategory()) {
                double value = 10.0;
                Unit unit = new Unit(value, type);
                assertEquals(value, unit.getValue());
                assertEquals(type, unit.getType());
            }
        }
    }


    @Test
    public void unitsShouldBeComparableForAllTypes() {
        for (UnitFormula formula : formulas) {
            for (UnitType type : formula.getAllUnitTypesOfThisCategory()) {
                assertEquals(new Unit(-12345.6, type), new Unit(-12345.6, type));
                assertEquals(new Unit(-12345.6, null), new Unit(-12345.6, null));
                assertEquals(new Unit(0.0, type), new Unit(0.0, type));
                assertEquals(new Unit(0.0, null), new Unit(0.0, null));
                assertEquals(new Unit(-12345.6, type).hashCode(), new Unit(-12345.6, type).hashCode());
                assertEquals(new Unit(-12345.6, null).hashCode(), new Unit(-12345.6, null).hashCode());
                assertEquals(new Unit(0.0, type).hashCode(), new Unit(0.0, type).hashCode());
                assertEquals(new Unit(0.0, null).hashCode(), new Unit(0.0, null).hashCode());
            }
        }
    }


    @Test
    public void unitShouldBeComparable() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(new Unit(12345.6, Length.Types.M), unit1);
        assertEquals(new Unit(12345.6, Length.Types.M).hashCode(), unit1.hashCode());
        assertEquals(new Unit(12345.6, Length.Types.M).toString(), unit1.toString());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectUnitType() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(Length.Types.M, unit1.getType());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectUnitValues() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(12345.6, unit1.getValue());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectUnityType() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(Length.Types.M, unit1.getType());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectRepresentantionOfUnit() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals("{value=12345.6, type=M}", unit1.toString());
    }


    @Test
    public void testEquals_sameInstance() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        assertTrue(unit1.equals(unit1));
    }


    @Test
    public void testEquals_equalInstance() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        Unit unit2 = new Unit(5.0, Length.Types.M);
        assertTrue(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentValue() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        Unit unit2 = new Unit(3.0, Length.Types.M);
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentType() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        Unit unit2 = new Unit(5.0, Length.Types.CM);
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentValueAndType() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        Unit unit2 = new Unit(3.0, Length.Types.CM);
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_sameHashCodeDifferentValueAndType() {
        Unit unit1 = new Unit(Double.POSITIVE_INFINITY, Length.Types.M);
        Unit unit2 = new Unit(Double.POSITIVE_INFINITY, Length.Types.CM);
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_null() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        assertFalse(unit1.equals(null));
    }


    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals_differentClass() {
        Unit unit1 = new Unit(5.0, Length.Types.M);
        String unit2 = "unit2";
        assertFalse(unit1.equals(unit2));
    }

}
