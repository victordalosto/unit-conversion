package dalosto.engineering.unitconversion.service;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.formula.UnitFormula;


@Service
public class MapUnitTypeService {


    public UnitType getUnitTypeFromString(String unitType, UnitFormula unitFormula) throws ParameterException {
        if (unitType == null || unitFormula == null) {
            throw new ParameterException("type can't be NULL.");
        }
        return mapUnity(unitType, unitFormula);
    }


    private UnitType mapUnity(String string, UnitFormula unitFormula) {
        string = filterString(string);
        for (UnitType type : unitFormula.getAllUnitTypesOfThisCategory()) {
            if (string.equalsIgnoreCase(type.toString())) {
                return type;
            }
        }
        throw new ParameterException("type " + string + " not found.");
    }


    private String filterString(String string) {
        return string.toUpperCase()
                     .replaceAll("\\s", "")
                     .replaceAll("-", "").replaceAll("_", "")
                     .replaceAll("\\^", "").replaceAll("~", "")
                     .replaceAll("¹", "").replaceAll("²", "2").replaceAll("³", "3").replaceAll("⁴", "4")
                     .replaceAll("₁", "").replaceAll("₂", "2").replaceAll("₃", "3").replaceAll("₄", "4")
                     .replaceAll("·", ".").replaceAll("X", ".").replaceAll("×", ".").replaceAll("\\*", ".")
                     .replaceAll("\\\\", "/").replaceAll("÷", "/")
                     .replaceAll("[^A-Z0-9\\./]+", "");
    }


}