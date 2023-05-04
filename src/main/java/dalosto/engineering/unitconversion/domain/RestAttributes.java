package dalosto.engineering.unitconversion.domain;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;


@Getter
public class RestAttributes {

    private UnitDAO unitDAO;
    private RestURL restURL;
    private EndpointInfo endpointInfo;

    
    public RestAttributes(UnitDAO unitDAO, HttpServletRequest request, EndpointInfo endpointInfo) {
        this.unitDAO = unitDAO;
        this.restURL = new RestURL(request);
        this.endpointInfo = endpointInfo;
    }
    
}
