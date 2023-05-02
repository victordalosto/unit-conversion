package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dalosto.engineering.unitconversion.domain.EndpointInfo;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.service.RestMessageService;

@Component
public abstract class TemplateController {

    @Autowired
    private RestMessageService service;


    public final RestMessage createRestMessage(UnitDAO unitDAO) {
        if (getEndpointInfo().isSIEndPoint()) {
            unitDAO.setTarget(getEndpointInfo().getSI());
        }
        return service.getMessageForEndPoint(this.getEndpointInfo(), unitDAO);
    }


    public abstract EndpointInfo getEndpointInfo();
    protected abstract RestMessage home(UnitDAO unitDAO);
    protected abstract RestMessage si(UnitDAO unitDAO);

}
