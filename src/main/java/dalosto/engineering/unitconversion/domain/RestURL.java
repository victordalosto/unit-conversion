package dalosto.engineering.unitconversion.domain;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;


public class RestURL {

    private HttpServletRequest request;


    public RestURL() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    public RestURL(HttpServletRequest request) {
        this.request = request;
    }


    public String getHomeURL() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }


    public String getURI() {
        return request.getRequestURI();
    }


    public String getSIEndPoint() {
        if (isSIEndPoint()) {
            return getURI();
        }
        return getURI() + "/si";
    }


    public boolean isSIEndPoint() {
        return getURI().toLowerCase().endsWith("si");
    }

}
