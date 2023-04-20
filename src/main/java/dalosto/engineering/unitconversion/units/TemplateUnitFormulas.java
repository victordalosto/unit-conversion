package dalosto.engineering.unitconversion.units;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.UnitException;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


abstract class TemplateUnitFormulas implements UnitFormula {

    
    public final Unit buildUnitToSI(Unit unit) {
        if (unit == null) {
            throw new UnitException("Unit can't be NULL.");
        }
        if (unit.getType() == null) {
            throw new UnitException("Unit Type can't be NULL.");
        }
        return buildUnitIntoAnotherType(unit, unit.getType().getSITypeOfThisCategory());
    }


    public final Unit buildUnitIntoAnotherType(Unit unit, UnitType anotherType) {
        if (unit == null) {
            throw new UnitException("Unit can't be NULL.");
        }
        if (unit.getType() == null || anotherType == null) {
            throw new UnitException("Unit Type can't be NULL.");
        }
        if (!unit.getType().getClass().equals(anotherType.getClass())) {
            throw new UnitException("UnitTypes are not compatible.");
        }
        return convertUnitIntoAnotherType(unit, anotherType);
    }


    public abstract Unit convertUnitIntoAnotherType(Unit unit, UnitType anotherType);

}
