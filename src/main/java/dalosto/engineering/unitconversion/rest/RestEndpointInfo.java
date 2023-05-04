package dalosto.engineering.unitconversion.rest;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public final class RestEndpointInfo {

    private final String title;
    private final UnitFormula unitFormula;


    public String getURIofType() {
        return "/api/" + getTitle().toLowerCase();
    }


    public String getAllUnitsOfType() {
        return unitFormula.getAllUnitTypesOfThisCategory().toString();
    }


    public String getSIUnitofType() {
        return unitFormula.getSITypeOfThisCategory().toString();
    }

}
