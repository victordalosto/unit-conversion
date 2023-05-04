package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.EndpointInfo;
import dalosto.engineering.unitconversion.domain.RestAttributes;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.UnitFormula;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/inertia")
@Order(4)
public class InertiaController extends TemplateController {

    @Autowired
    @Qualifier("inertia")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    @PostMapping
    public RestMessage home(UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }

    
    @Override
    @GetMapping("/si")
    @PostMapping("/si")
    public RestMessage si(UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }


    @Override
    protected RestAttributes getAttribute(UnitDAO unitDAO, HttpServletRequest request) {
        return new RestAttributes(unitDAO, request, getEndpointInfo());
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return new EndpointInfo("inertia", unitFormula);
    }

    
}
