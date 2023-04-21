package dalosto.engineering.unitconversion.rest.domain;
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


    public String getURL() {
        return request.getRequestURL().toString();
    }


    public String getURI() {
        return request.getRequestURI();
    }

}
