package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


@SpringBootTest
public class TestUnit {

    @Autowired
    List<UnitFormula> formulas;

    @ParameterizedTest
    @EnumSource(Length.Types.class)
    public void constructor_withValidValues_setsProperties(UnitType type) {
        double value = 10.0;
        Unit unit = new Unit(value, type);
        assertEquals(unit.getValue(), value);
        assertEquals(unit.getType(), type);
    }


    @Test
    public void unitsShouldBeAbleToMatchForAllTypes() {
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
    public void unitShouldBeAbleToMatchWithCorrespondentUnitFor() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(new Unit(12345.6, Length.Types.M), unit1);
        assertEquals(new Unit(12345.6, Length.Types.M).hashCode(), unit1.hashCode());
        Unit unit2 = new Unit(12345.6, Area.Types.M2);
        assertEquals(new Unit(12345.6, Area.Types.M2), unit2);
        assertEquals(new Unit(12345.6, Area.Types.M2).hashCode(), unit2.hashCode());
        Unit unit3 = new Unit(12345.6, Volume.Types.M3);
        assertEquals(new Unit(12345.6, Volume.Types.M3), unit3);
        assertEquals(new Unit(12345.6, Volume.Types.M3).hashCode(), unit3.hashCode());
        Unit unit4 = new Unit(12345.6, Force.Types.KN);
        assertEquals(new Unit(12345.6, Force.Types.KN), unit4);
        assertEquals(new Unit(12345.6, Force.Types.KN).hashCode(), unit4.hashCode());
    }

    
    @Test
    public void unitShouldBeAbleToReturnCorrectUnitValues() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(12345.6, unit1.getValue());
        Unit unit2 = new Unit(12345.6, Area.Types.M2);
        assertEquals(12345.6, unit2.getValue());
        Unit unit3 = new Unit(12345.6, Volume.Types.M3);
        assertEquals(12345.6, unit3.getValue());
        Unit unit4 = new Unit(12345.6, Force.Types.KN);
        assertEquals(12345.6, unit4.getValue());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectUnityType() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals(Length.Types.M, unit1.getType());
        Unit unit2 = new Unit(12345.6, Area.Types.M2);
        assertEquals(Area.Types.M2, unit2.getType());
        Unit unit3 = new Unit(12345.6, Volume.Types.M3);
        assertEquals(Volume.Types.M3, unit3.getType());
        Unit unit4 = new Unit(12345.6, Force.Types.KN);
        assertEquals(Force.Types.KN, unit4.getType());
    }


    @Test
    public void unitShouldBeAbleToReturnCorrectRepresentantionOfUnit() {
        Unit unit1 = new Unit(12345.6, Length.Types.M);
        assertEquals("{value=12345.6, type=M}", unit1.toString());
        Unit unit2 = new Unit(12345.6, Area.Types.M2);
        assertEquals("{value=12345.6, type=M2}", unit2.toString());
        Unit unit3 = new Unit(12345.6, Volume.Types.M3);
        assertEquals("{value=12345.6, type=M3}", unit3.toString());
        Unit unit4 = new Unit(12345.6, Force.Types.KN);
        assertEquals("{value=12345.6, type=KN}", unit4.toString());
    }
}
