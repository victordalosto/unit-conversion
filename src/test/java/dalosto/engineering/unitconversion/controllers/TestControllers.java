package dalosto.engineering.unitconversion.controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.units.TemplateUnitFormulas;


@SpringBootTest
public class TestControllers {

    @Autowired
    Set<TemplateController> controllers;

    @Autowired
    Set<TemplateUnitFormulas> formulas;


    @Test
    void allUnitsShouldBeCoveredByAController() {
        assertEquals(formulas.size(), controllers.size());
    }


    @Test
    void allControllersMustHaveAValidUnitFormula() {
        for (TemplateController templateController : controllers) {
            assert(formulas.contains(templateController.getEndpointInfo().getUnitFormula()));
        }
    }


    @Test
    void allControllersMustHaveAValidEndPoint() {
        for (TemplateController templateController : controllers) {
            String unitFormula = templateController.getEndpointInfo().getUnitFormula().getClass().getSimpleName().toLowerCase();
            String endpointTitle = templateController.getEndpointInfo().getTitle();
            assertEquals(unitFormula, endpointTitle);
            assertEquals(templateController.getEndpointInfo().getURI().replaceAll("/api/", ""), unitFormula);
            assertEquals(templateController.getEndpointInfo().getURI().replaceAll("/api/", ""), endpointTitle);
            assert(templateController.getEndpointInfo().getURI().matches("/api/[a-z]+"));
        }
    }


}
