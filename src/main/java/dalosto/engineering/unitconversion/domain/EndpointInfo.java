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


    public String getURIofType() {
        return "/api/" + title.toLowerCase();
    }


    public String getURIofSI() {
        return restURL.getURIofSI();
    }


    public String getURLhome() {
        return restURL.getHomeURL();
    }


    public String getCurrentURI() {
        return restURL.getCurrentURI();
    }


    public boolean isCurrentURIaSIEndPoint() {
        return restURL.isCurrentURIaSIEndPoint();
    }


    public String getAllUnitsOfType() {
        return unitFormula.getAllUnitTypesOfThisCategory().toString();
    }


    public String getSIUnitofType() {
        return unitFormula.getSITypeOfThisCategory().toString();
    }

}
