package dalosto.engineering.unitconversion.rest;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;


@Getter
public final class RestAttributes {

    private final UnitDAO unitDAO;
    private final RestURL restURL;
    private final RestEndpointInfo endpointInfo;


    public RestAttributes(UnitDAO unitDAO, HttpServletRequest request, RestEndpointInfo endpointInfo) {
        this.unitDAO = unitDAO;
        this.restURL = new RestURL(request);
        this.endpointInfo = endpointInfo;
    }


    public boolean doesntHaveValues() {
        return unitDAO == null || unitDAO.doesntHaveData();
    }
    
}
