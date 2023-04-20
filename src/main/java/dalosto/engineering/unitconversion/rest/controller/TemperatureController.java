package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.RestMessage;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@RestController
@RequestMapping("/api/temperature")
public class TemperatureController extends TemplateController {

    @Autowired
    @Qualifier("temperature")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    public RestMessage home(UnitDAO unitDAO) {
        return super.createRestMessage(unitDAO);
    }


    @Override
    @GetMapping("/example")
    public RestMessage example(UnitDAO unitDAO) {
        return super.createRestMessage(unitDAO);
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return new EndpointInfo("temperature", unitFormula);
    }

    
}