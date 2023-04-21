package dalosto.engineering.unitconversion.rest.domain;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import lombok.Getter;
import lombok.Setter;


@Getter
public class EndpointInfo {

    @Setter 
    private String title;
    private UnitFormula unitFormula;


    public EndpointInfo(String title, UnitFormula unitFormula) {
        this.title = title;
        this.unitFormula = unitFormula;
    }


    public String getURI() {
        return "/api/" + title.toLowerCase();
    }


    public String getAllUnits() {
        return unitFormula.getAllUnitTypesOfThisCategory().toString().replaceAll("_", "");
    }


}
