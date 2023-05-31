package dalosto.engineering.unitconversion.controller.unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.controller.TemplateController;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.rest.RestAttributes;
import dalosto.engineering.unitconversion.rest.RestEndpointInfo;
import dalosto.engineering.unitconversion.rest.RestMessage;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/area")
@Order(2)
public final class AreaController extends TemplateController {

    @Autowired
    @Qualifier("area")
    private UnitFormula unitFormula;


    @Override
    @GetMapping
    public ResponseEntity<RestMessage> getHOME(UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }


    @Override
    @PostMapping
    public ResponseEntity<RestMessage> postHOME(@RequestBody(required = false) UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }


    @Override
    @GetMapping("/si")
    public ResponseEntity<RestMessage> getSI(UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }


    @Override
    @PostMapping("/si")
    public ResponseEntity<RestMessage> postSI(@RequestBody(required = false) UnitDAO unitDAO, HttpServletRequest request) {
        return super.createRestMessage(getAttribute(unitDAO, request));
    }


    @Override
    protected RestAttributes getAttribute(UnitDAO unitDAO, HttpServletRequest request) {
        return new RestAttributes(unitDAO, request, getEndpointInfo());
    }


    @Override
    public RestEndpointInfo getEndpointInfo() {
        return new RestEndpointInfo("area", unitFormula);
    }

}
