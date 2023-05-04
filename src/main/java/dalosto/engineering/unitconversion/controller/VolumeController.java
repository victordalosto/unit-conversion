package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.rest.RestEndpointInfo;
import dalosto.engineering.unitconversion.rest.RestAttributes;
import dalosto.engineering.unitconversion.rest.RestMessage;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/volume")
@Order(3)
public final class VolumeController extends TemplateController {

    @Autowired
    @Qualifier("volume")
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
    public RestEndpointInfo getEndpointInfo() {
        return new RestEndpointInfo("volume", unitFormula);
    }

    
}
