package dalosto.engineering.unitconversion.rest.controller.template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.service.MessageRestService;

@Component
public abstract class TemplateController {

    @Autowired
    private MessageRestService service;

    public final MessageRest getHome(UnitDAO unitDAO) {
        return service.getMessageForEndPoint(this.getEndpointInfo(), unitDAO);
    }

    
    public final MessageRest getExample(UnitDAO unitDAO) {
        EndpointInfo endpointInfo = this.getEndpointInfo();
        endpointInfo.setUri(endpointInfo.getUri() + "/example");
        return service.getMessageForEndPoint(endpointInfo, unitDAO);
    }


    public abstract MessageRest home(UnitDAO unitDAO);
    public abstract MessageRest example(UnitDAO unitDAO);
    public abstract EndpointInfo getEndpointInfo();

}
