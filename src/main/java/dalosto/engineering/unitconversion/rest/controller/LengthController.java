package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@RestController
@RequestMapping("/api/length")
public class LengthController extends TemplateController {

    @Autowired
    @Qualifier("length")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    public MessageRest home(UnitDAO unitDAO) {
        return super.getHome(unitDAO);
    }


    @Override
    @GetMapping("/example")
    public MessageRest example(UnitDAO unitDAO) {
        return super.getExample(unitDAO);
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return new EndpointInfo("length", unitFormula);
    }

    
}
