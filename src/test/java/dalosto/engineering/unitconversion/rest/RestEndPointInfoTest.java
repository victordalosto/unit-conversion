package dalosto.engineering.unitconversion.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.controller.TemplateController;


@SpringBootTest
public class RestEndPointInfoTest {

    @Autowired
    private List<TemplateController> controllers;


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
        }
    }


    @Test
    public void shouldBeAbleToAcessSI() {
        for (TemplateController controller : controllers) {
            assertNotNull(controller.getEndpointInfo().getSIUnitofType());
        }
    }


}
