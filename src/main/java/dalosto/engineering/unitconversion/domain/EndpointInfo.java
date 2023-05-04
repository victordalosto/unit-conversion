package dalosto.engineering.unitconversion.domain;
import dalosto.engineering.unitconversion.units.UnitFormula;
import lombok.Getter;


@Getter
public class EndpointInfo {

    private String title;
    private UnitFormula unitFormula;


    public EndpointInfo(String title, UnitFormula unitFormula) {
        this.title = title;
        this.unitFormula = unitFormula;
    }


    public String getURIofType() {
        return "/api/" + title.toLowerCase();
    }

    
    public String getAllUnitsOfType() {
        return unitFormula.getAllUnitTypesOfThisCategory().toString();
    }


    public String getSIUnitofType() {
        return unitFormula.getSITypeOfThisCategory().toString();
    }

}
