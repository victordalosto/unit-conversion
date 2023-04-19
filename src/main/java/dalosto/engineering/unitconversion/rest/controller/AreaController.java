package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.DescriptionOfEndpoint;


@RestController
@RequestMapping("/api/area")
public class AreaController extends TemplateController {

    @Autowired
    @Qualifier("Area")
    private UnitFormula unitFormula;


    public AreaController() {
        super();
    }


    @Override
    public DescriptionOfEndpoint description() {
        return DescriptionOfEndpoint.builder()
                                    .title("Area")
                                    .uri("/api/area")
                                    .unitTypes(unitFormula.getAllUnitTypesOfThisCategory().toString())
                                    .build();
    }

    

  
    
}
