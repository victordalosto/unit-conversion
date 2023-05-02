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


    public String getCurrentURI() {
        return request.getRequestURI();
    }


    public String getURIofSI() {
        if (isCurrentURIaSIEndPoint()) {
            return getCurrentURI();
        }
        return getCurrentURI() + "/si";
    }


    public boolean isCurrentURIaSIEndPoint() {
        return getCurrentURI().toLowerCase().endsWith("si");
    }

}
