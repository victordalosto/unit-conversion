package dalosto.engineering.unitconversion.domain;
import dalosto.engineering.unitconversion.units.UnitFormula;
import lombok.Getter;


@Getter
public class EndpointInfo {

    private String title;
    private UnitFormula unitFormula;
    private RestURL restURL;


    public EndpointInfo(String title, UnitFormula unitFormula) {
        this.title = title;
        this.unitFormula = unitFormula;
        this.restURL = new RestURL();
    }


    public String getAPIHomeURI() {
        return "/api/" + title.toLowerCase();
    }


    public String getAllUnits() {
        return unitFormula.getAllUnitTypesOfThisCategory().toString();
    }


    public String getSI() {
        return unitFormula.getSITypeOfThisCategory().toString();
    }


    public boolean isSIEndPoint() {
        return restURL.isSIEndPoint();
    }


    public String getSIEndPoint() {
        return restURL.getSIEndPoint();
    }


    public String getHomeURL() {
        return restURL.getHomeURL();
    }


    public String getURI() {
        return restURL.getURI();
    }

}
