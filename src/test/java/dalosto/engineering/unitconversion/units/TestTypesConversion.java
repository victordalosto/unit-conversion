package dalosto.engineering.unitconversion.units;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;


@SpringBootTest
public class TestTypesConversion {

    @Autowired
    Set<UnitFormula> formulas;


    @Test
    void assertThatFormulasExtendsTemplateFormula() {
        for (UnitFormula unitFormula : formulas) {
            assert(unitFormula instanceof TemplateUnitFormulas);
        }
    }


    @Test
    void assertThatFormulasTypeExtendsUnitType() {
        for (UnitFormula unitFormula : formulas) {
            assert(unitFormula.getSITypeOfThisCategory() instanceof UnitType);
        }
    }


    @Test
    void assertThatFormulasThrowsExceptionIfTheyAreNotSameTypeOfFormula() {
        for (UnitFormula unitFormula : formulas) {
            assertThrowsIfNotSameTypeOfFormula(unitFormula);
        }
    }



    @Test
    void assertThatUnitFormulaGetTypesAndUnitTypeGetTypesAreEquivalent() {
        for (UnitFormula unitFormula : formulas) {
            assertEquals(unitFormula.getSITypeOfThisCategory(), unitFormula.getSITypeOfThisCategory().getSITypeOfThisCategory());
            assertEquals(unitFormula.getAllUnitTypesOfThisCategory(), unitFormula.getSITypeOfThisCategory().getAllUnitTypesOfThisCategory());
        }
    }



    private void assertThrowsIfNotSameTypeOfFormula(UnitFormula formula) {
        for (UnitFormula other : formulas) {
            if (formula != other) {
                assertThrows(UnitException.class, () -> {
                        formula.buildUnitIntoAnotherType(
                                                         new Unit(1, formula.getSITypeOfThisCategory()),
                                                         other.getSITypeOfThisCategory());
                });
            } else {
                assertDoesNotThrow(() -> 
                        formula.buildUnitIntoAnotherType(
                                                         new Unit(1, formula.getSITypeOfThisCategory()), 
                                                         other.getSITypeOfThisCategory()));
            }
        }
    }

}
