package dalosto.engineering.unitconversion.domain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.units.UnitFormula;


@SpringBootTest
public class TestUnitDAO {

    @Autowired
    List<UnitFormula> formulas;


    @Test
    public void unitsDaoShouldBeAbleToMatchForAllTypes() {
        for (UnitFormula formula : formulas) {
            for (UnitType type : formula.getAllUnitTypesOfThisCategory()) {
                assertEquals(new UnitDAO("-12345.6", type.toString(), type.toString()), 
                             new UnitDAO("-12345.6", type.toString(), type.toString()));
                assertEquals(new UnitDAO(null, type.toString(), type.toString()), 
                             new UnitDAO(null, type.toString(), type.toString()));
                assertEquals(new UnitDAO("-12345.6", null, type.toString()), 
                             new UnitDAO("-12345.6", null, type.toString()));
                assertEquals(new UnitDAO("-12345.6", type.toString(), null), 
                             new UnitDAO("-12345.6", type.toString(), null));
                assertEquals(new UnitDAO(null, null, type.toString()), 
                             new UnitDAO(null, null, type.toString()));
                assertEquals(new UnitDAO(null, type.toString(),null), 
                             new UnitDAO(null, type.toString(),null));
                assertEquals(new UnitDAO("-12345.6", null, null), 
                             new UnitDAO("-12345.6", null, null));
                assertEquals(new UnitDAO(null, null, null), 
                             new UnitDAO(null, null, null));

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
                assertEquals(new UnitDAO(null, null, null).hashCode(), 
                             new UnitDAO(null, null, null).hashCode());
            }
        }
    }

    @Test
    public void UnitDaoWithNullValuesShouldntHaveData() {
        UnitDAO unit = new UnitDAO(null, null, null);
        assertTrue(unit.doesntHaveData());
    }

    
    @Test
    public void UnitDaoWithOnelValuesShouldHaveData() {
        assertEquals(false, new UnitDAO("mock", null, null).doesntHaveData());
        assertEquals(false, new UnitDAO(null, "mock", null).doesntHaveData());
        assertEquals(false, new UnitDAO(null, null, "mock").doesntHaveData());
    }

    
    @Test
    public void unitShouldBeAbleToReturnCorrectUnitValues() {
        UnitDAO unit = new UnitDAO("12345.67", "M", "CM");
        assertEquals(new UnitDAO("12345.67", "M", "CM"), unit);
        assertEquals(new UnitDAO("12345.67", "M", "CM").hashCode(), unit.hashCode());
    }


   
}
