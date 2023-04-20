package dalosto.engineering.unitconversion.rest.domain;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
public class EndpointInfo {

    @Setter 
    private String uri;
    private String title;
    private UnitFormula unitFormula; 


    public boolean isExampleURI() {
        return this.uri.contains("example");
    }


    public String getUri() {
        return uri.toString();
    }
    

    public String getURIExample() {
        if (!this.getUri().endsWith("example")) {
            return this.getUri() + "/example";
        }
        return uri;
    }

}
