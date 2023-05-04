package dalosto.engineering.unitconversion.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.units.TemplateUnitFormulas;


@SpringBootTest
public class ControllersTest {

    @Autowired
    private List<TemplateController> controllers;

    @Autowired
    private List<TemplateUnitFormulas> formulas;


    @Test
    public void allUnitsShouldBeCoveredByAController() {
        assertEquals(formulas.size(), controllers.size());
    }


    @Test
    public void allControllersMustHaveAValidUnitFormula() {
        for (TemplateController templateController : controllers) {
            assert(formulas.contains(templateController.getEndpointInfo().getUnitFormula()));
        }
    }


    @Test
    public void allControllersMustHaveAValidEndPoint() {
        for (TemplateController templateController : controllers) {
            String unitFormula = templateController.getEndpointInfo().getUnitFormula().getClass().getSimpleName().toLowerCase();
            String endpointTitle = templateController.getEndpointInfo().getTitle();
            assertEquals(unitFormula, endpointTitle);
            assertEquals(templateController.getEndpointInfo().getURIofType().replaceAll("/api/", ""), unitFormula);
            assert(templateController.getEndpointInfo().getURIofType().matches("/api/[a-z]+"));
        }
    }


}
