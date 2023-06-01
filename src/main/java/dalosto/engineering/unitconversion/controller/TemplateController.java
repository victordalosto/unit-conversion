package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.rest.RestAttributes;
import dalosto.engineering.unitconversion.rest.RestEndpointInfo;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.rest.RestStatus;
import dalosto.engineering.unitconversion.service.RestMessageService;
import jakarta.servlet.http.HttpServletRequest;


public abstract class TemplateController {

    @Autowired
    private RestMessageService service;


    public final ResponseEntity<RestMessage> createRestMessage(RestAttributes attributes) {
        RestMessage msg = service.getMessageForEndPoint(attributes);
        if(RestStatus.ERROR.equals(msg.getStatus())) {
           return ResponseEntity.status(400).body(msg);
        }
        return ResponseEntity.status(200).body(msg);
    }
    

    public abstract RestEndpointInfo getEndpointInfo();
    protected abstract RestAttributes getAttribute(UnitDAO unitDAO, HttpServletRequest request);

    protected abstract ResponseEntity<RestMessage> getHOME(UnitDAO unitDAO, HttpServletRequest request);
    protected abstract ResponseEntity<RestMessage> postHOME(UnitDAO unitDAO, HttpServletRequest request);

    protected abstract ResponseEntity<RestMessage> getSI(UnitDAO unitDAO, HttpServletRequest request);
    protected abstract ResponseEntity<RestMessage> postSI(UnitDAO unitDAO, HttpServletRequest request);

}