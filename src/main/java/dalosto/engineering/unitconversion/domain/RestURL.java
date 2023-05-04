package dalosto.engineering.unitconversion.domain;
import jakarta.servlet.http.HttpServletRequest;


public class RestURL {

    private HttpServletRequest request;


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
