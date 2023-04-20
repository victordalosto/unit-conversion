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
import dalosto.engineering.unitconversion.service.MessageRestService;


@RestController
@RequestMapping("/api/length")
public class LengthController extends TemplateController {

    @Autowired
    @Qualifier("Length")
    private UnitFormula unitFormula;

    @Autowired
    private MessageRestService service;


    @GetMapping
    public MessageRest home(UnitDAO unitDAO) {
        return service.getMessageForEndPoint(this.getEndpointInfo(), unitDAO);
    }


    @Override
    public EndpointInfo getEndpointInfo() {
        return EndpointInfo.builder()
                                    .title("Length")
                                    .uri("/api/length")
                                    .unitFormula(unitFormula)
                                    .build();
    }
    
}
