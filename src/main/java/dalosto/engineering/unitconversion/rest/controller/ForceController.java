package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.RestMessage;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@RestController
@RequestMapping("/api/force")
public class ForceController extends TemplateController {

    @Autowired
    @Qualifier("force")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    @PostMapping
    public RestMessage home(UnitDAO unitDAO) {
        return super.createRestMessage(unitDAO);
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return new EndpointInfo("force", unitFormula);
    }

    
}
