package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.rest.RestEndpointInfo;
import dalosto.engineering.unitconversion.rest.RestAttributes;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.service.RestMessageService;
import jakarta.servlet.http.HttpServletRequest;


@Component
public abstract class TemplateController {

    @Autowired
    private RestMessageService service;


    public final RestMessage createRestMessage(RestAttributes attributes) {
        if (attributes.getRestURL().isCurrentURIaSIEndPoint()) {
            attributes.getUnitDAO().setTarget(getEndpointInfo().getSIUnitofType());
        }
        return service.getMessageForEndPoint(attributes);
    }


    protected abstract RestMessage home(UnitDAO unitDAO, HttpServletRequest request);
    protected abstract RestMessage si(UnitDAO unitDAO, HttpServletRequest request);

    protected abstract RestAttributes getAttribute(UnitDAO unitDAO, HttpServletRequest request);
    public abstract RestEndpointInfo getEndpointInfo();

}
