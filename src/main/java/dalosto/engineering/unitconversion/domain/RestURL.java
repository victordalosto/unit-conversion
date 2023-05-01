package dalosto.engineering.unitconversion.domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;


@Component
public class RestURL {

    @Autowired
    private HttpServletRequest request;


    public String getHomeURL() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }


    public String getURI() {
        return request.getRequestURI();
    }

    
    public boolean isSIEndPoint() {
        return getURI().toLowerCase().endsWith("si");
    }

}
