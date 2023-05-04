package dalosto.engineering.unitconversion.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.controller.TemplateController;
import dalosto.engineering.unitconversion.formula.UnitFormula;


@SpringBootTest
public class RestEndPointInfoTest {

    @Autowired
    private List<TemplateController> controllers;

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void titleShouldBeValid() {
        for (TemplateController controller : controllers) {
            assertEquals(controller.getEndpointInfo().getTitle(), 
                         controller.getClass().getSimpleName().toLowerCase().replaceAll("controller", ""));
        }
    }

    
    @Test
    public void getAPIHomeURIShouldBeValid() {
        for (TemplateController controller : controllers) {
            assertEquals(controller.getEndpointInfo().getURIofType(), 
                         "/api/" + controller.getClass().getSimpleName().toLowerCase().replaceAll("controller", ""));
        }
    }


    @Test
    public void shouldBeAbleToAcessAllUnitTypes() {
        for (TemplateController controller : controllers) {
            assertNotNull(controller.getEndpointInfo().getAllUnitsOfType());
            for (UnitFormula formula : formulas) {
                if (formula.getAllUnitTypesOfThisCategory().equals(controller.getEndpointInfo().getUnitFormula().getAllUnitTypesOfThisCategory())) {
                    break;
                }
                fail("Didnt find Collection<UnitType>");
            }
        }
    }


    @Test
    public void shouldBeAbleToAcessSI() {
        for (TemplateController controller : controllers) {
            assertNotNull(controller.getEndpointInfo().getSIUnitofType());
            for (UnitFormula formula : formulas) {
                if (formula.getSITypeOfThisCategory().equals(controller.getEndpointInfo().getUnitFormula().getSITypeOfThisCategory())) {
                    break;
                }
                fail("Didnt find a valid SI Type");
            }
        }
    }


}
