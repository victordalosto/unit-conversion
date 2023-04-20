package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;


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
    public EndpointInfo getEndpointInfo() {
        return EndpointInfo.builder()
                                    .title("Area")
                                    .uri("/api/area")
                                    .unitFormula(unitFormula)
                                    .build();
    }

    

  
    
}
