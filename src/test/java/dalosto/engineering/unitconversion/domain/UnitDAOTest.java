package dalosto.engineering.unitconversion.domain;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.formula.UnitFormula;


@SpringBootTest
public class UnitDAOTest {

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void UnitDAOWithNullValuesShouldntHaveData() {
        UnitDAO dao = new UnitDAO(null, null, null);
        assertTrue(dao.doesntHaveData());
    }


    @Test
    public void UnitDAOWithValueOrType() {
        assertFalse(new UnitDAO("mock", null, null).doesntHaveData());
        assertFalse(new UnitDAO(null, "mock", null).doesntHaveData());
    }


    @Test
    public void unitDAOShouldBeAbleToReturnCorrectUnitValues() {
        UnitDAO dao = new UnitDAO("12345.67", "M", "CM");
        assertEquals(new UnitDAO("12345.67", "M", "CM"), dao);
        assertEquals(new UnitDAO("12345.67", "M", "CM").hashCode(), dao.hashCode());
        assertEquals(new UnitDAO("12345.67", "M", "CM").toString(), dao.toString());
    }


    @Test
    public void unitsDAONeedsToBeComparable() {
        for (UnitFormula formula : formulas) {
            for (UnitType type : formula.getAllUnitTypesOfThisCategory()) {
                assert(new UnitDAO("-12345.6", type.toString(), type.toString()).equals(
                       new UnitDAO("-12345.6", type.toString(), type.toString())));

                assert(new UnitDAO(null, type.toString(), type.toString()).equals(
                       new UnitDAO(null, type.toString(), type.toString())));

                assert(new UnitDAO("-12345.6", null, type.toString()).equals(
                       new UnitDAO("-12345.6", null, type.toString())));

                assert(new UnitDAO("-12345.6", type.toString(), null).equals(
                       new UnitDAO("-12345.6", type.toString(), null)));

                assert(new UnitDAO(null, null, type.toString()).equals(new UnitDAO(null, null, type.toString())));

                assert(new UnitDAO(null, type.toString(), null).equals( new UnitDAO(null, type.toString(), null)));

                assert(new UnitDAO("-12345.6", null, null).equals( new UnitDAO("-12345.6", null, null)));

                assert(new UnitDAO(null, null, null).equals( new UnitDAO(null, null, null)));

                assertEquals(new UnitDAO("-12345.6", type.toString(), type.toString()).hashCode(),
                             new UnitDAO("-12345.6", type.toString(), type.toString()).hashCode());

                assertEquals(new UnitDAO(null, type.toString(), type.toString()).hashCode(),
                             new UnitDAO(null, type.toString(), type.toString()).hashCode());

                assertEquals(new UnitDAO("-12345.6", null, type.toString()).hashCode(),
                             new UnitDAO("-12345.6", null, type.toString()).hashCode());

                assertEquals(new UnitDAO("-12345.6", type.toString(), null).hashCode(),
                             new UnitDAO("-12345.6", type.toString(), null).hashCode());

                assertEquals(new UnitDAO(null, null, type.toString()).hashCode(),
                             new UnitDAO(null, null, type.toString()).hashCode());

                assertEquals(new UnitDAO(null, type.toString(), null).hashCode(),
                             new UnitDAO(null, type.toString(), null).hashCode());

                assertEquals(new UnitDAO("-12345.6", null, null).hashCode(),
                             new UnitDAO("-12345.6", null, null).hashCode());

                assertEquals(new UnitDAO(null, null, null).hashCode(), new UnitDAO(null, null, null).hashCode());
            }
        }
    }


    @Test
    public void testEquals_sameInstance() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        assertTrue(unit1.equals(unit1));
    }


    @Test
    public void testEquals_equalInstance() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("5.0", "M", "KM");
        assertTrue(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentValue() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("3.0", "M", "KM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentType() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("5.0", "KM", "KM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentTarget() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("5.0", "M", "CM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_null() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        assertFalse(unit1.equals(null));
    }


    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void testEquals_differentClass() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        String unit2 = "unit2";
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testDoesntHaveData_true() {
        UnitDAO unit = new UnitDAO(null, null, null);
        assertTrue(unit.doesntHaveData());
    }


    @Test
    public void testDoesntHaveData_false() {
        UnitDAO unit = new UnitDAO("5.0", "M", "KM");
        assertFalse(unit.doesntHaveData());
    }


    @Test
    public void testToString() {
        UnitDAO unit = new UnitDAO("5.0", "M", "KM");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("value", "5.0");
        map.put("type", "M");
        map.put("target", "KM");
        String expected = map.toString();
        assertEquals(expected, unit.toString());
    }


    @Test
    public void testEquals_differentValueAndType() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("3.0", "KM", "KM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentValueAndTarget() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("3.0", "M", "CM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_differentTypeAndTarget() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("5.0", "KM", "CM");
        assertFalse(unit1.equals(unit2));
    }


    @Test
    public void testEquals_allDifferent() {
        UnitDAO unit1 = new UnitDAO("5.0", "M", "KM");
        UnitDAO unit2 = new UnitDAO("3.0", "KM", "CM");
        assertFalse(unit1.equals(unit2));
    }

}
