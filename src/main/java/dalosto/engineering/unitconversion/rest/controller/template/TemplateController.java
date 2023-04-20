package dalosto.engineering.unitconversion.rest.controller.template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.RestMessage;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.service.RestMessageService;

@Component
public abstract class TemplateController {

    @Autowired
    private RestMessageService service;


    public final RestMessage createRestMessage(UnitDAO unitDAO) {
        return service.getMessageForEndPoint(this.getEndpointInfo(), unitDAO);
    }


    public abstract EndpointInfo getEndpointInfo();
    protected abstract RestMessage home(UnitDAO unitDAO);
    protected abstract RestMessage example(UnitDAO unitDAO);

}
