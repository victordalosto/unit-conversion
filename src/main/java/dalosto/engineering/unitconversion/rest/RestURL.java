package dalosto.engineering.unitconversion.rest;
import jakarta.servlet.http.HttpServletRequest;


public final class RestURL {

    private final HttpServletRequest request;


    public RestURL(HttpServletRequest request) {
        this.request = request;
    }


    public String getHomeURL() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }


    public String getCurrentURI() {
        return request.getRequestURI();
    }


    public boolean isCurrentURIaSIEndPoint() {
        return getCurrentURI().toLowerCase().endsWith("si");
    }


    public String getURIofSI() {
        if (isCurrentURIaSIEndPoint()) {
            return getCurrentURI();
        }
        return getCurrentURI() + "/si";
    }

}
