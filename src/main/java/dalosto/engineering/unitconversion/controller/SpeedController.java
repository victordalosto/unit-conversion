package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.EndpointInfo;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.UnitFormula;


@RestController
@RequestMapping("/api/speed")
public class SpeedController extends TemplateController {

    @Autowired
    @Qualifier("speed")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    @PostMapping
    public RestMessage home(UnitDAO unitDAO) {
        return super.createRestMessage(unitDAO);
    }

    
    @Override
    @GetMapping("/si")
    @PostMapping("/si")
    public RestMessage si(UnitDAO unitDAO) {
        return super.createRestMessage(unitDAO);
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return new EndpointInfo("speed", unitFormula);
    }

    
}
