package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;


@SpringBootTest
public class TypesConversionTest {

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void assertThatFormulasExtendsTemplateFormula() {
        for (UnitFormula unitFormula : formulas) {
            assert(unitFormula instanceof TemplateUnitFormulas);
        }
    }


    @Test
    public void assertThatFormulasTypeExtendsUnitType() {
        for (UnitFormula unitFormula : formulas) {
            assert(unitFormula.getSITypeOfThisCategory() instanceof UnitType);
        }
    }


    @Test
    public void assertThatUnitFormulaGetTypesAndUnitTypeGetTypesAreEquivalent() {
        for (UnitFormula unitFormula : formulas) {
            assertEquals(unitFormula.getSITypeOfThisCategory(), unitFormula.getSITypeOfThisCategory().getSITypeOfThisCategory());
            assertEquals(unitFormula.getAllUnitTypesOfThisCategory(), unitFormula.getSITypeOfThisCategory().getAllUnitTypesOfThisCategory());
        }
    }


    @Test
    public void assertThatFormulasThrowsExceptionIfTheyAreNotSameTypeOfFormula() {
        for (UnitFormula unitFormula : formulas) {
            checkException(unitFormula);
        }
    }


    private void checkException(UnitFormula formula) {
        for (UnitFormula other : formulas) {
            if (formula != other) {
                assertThrows(UnitException.class, () -> {
                        formula.buildUnitIntoAnotherType(
                                                         new Unit(12345.67, formula.getSITypeOfThisCategory()),
                                                         other.getSITypeOfThisCategory());
                });
            } else {
                assertDoesNotThrow(() -> 
                        formula.buildUnitIntoAnotherType(
                                                         new Unit(12345.67, formula.getSITypeOfThisCategory()), 
                                                         other.getSITypeOfThisCategory()));
            }
        }
    }

}
