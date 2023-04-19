package dalosto.engineering.unitconversion.service;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


@Service
public class MapUnitTypeService {


    public UnitType getUnitTypeFromString(String string, UnitFormula unitFormula) {
        if (string == null || unitFormula == null) {
            throw new ParameterException("UnitType can't be NULL.");
        }
        return mapUnity(fixStringToFindUnityType(string), unitFormula);
    }


    private UnitType mapUnity(String string, UnitFormula unitFormula) {
        for (UnitType unitType : unitFormula.getAllUnitTypesOfThisCategory()) {
            if (unitType.toString().equals(string)) {
                return unitType;
            }
        }
        throw new UnitException("UnitType " + string + " not found.");
    }


    public String fixStringToFindUnityType(String string) {
        string = string.toUpperCase();
        string = string.replaceAll("\\s", "");
        string = string.replaceAll("-", "_").replaceAll("\\^", "_").replaceAll("~", "_");
        string = string.replaceAll("¹", "1").replaceAll("²", "2").replaceAll("³", "3").replaceAll("⁴", "4");
        string = string.replaceAll("₁", "1").replaceAll("₂", "2").replaceAll("₃", "3").replaceAll("₄", "4");
        string = string.replaceAll("1", "").replaceAll("0", "").replaceAll("₀", "").replaceAll("⁰", "");
        string = string.replaceAll("[^a-zA-Z0-9_]+", "");
        string = string.replaceAll("_{2,}", "_"); // replace multiple _ with one
        string = fixMissingUnderlineOrWithVogalEasExponential(string);
        return string;
    }


    private String fixMissingUnderlineOrWithVogalEasExponential(String string) {
        if (string.matches(".*\\d+.*") && !string.contains("_")) {
            for (int i = 0; i < string.length(); i++) {
                if (Character.isDigit(string.charAt(i))) {
                    if (i >0 && string.charAt(i-1) == 'E') {
                        return string.substring(0, i-1) + "_" + string.substring(i, string.length());
                    }
                    return string.substring(0, i) + "_" + string.substring(i);
                }
            }
        }
        return string;
    }


}