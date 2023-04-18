package dalosto.engineering.unitconversion.interfaces;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;


/**
 * Interface for the Unit Formulas
 */
public interface UnitFormula {


    /** Build another Unit in the International System of Units (SI).  */
    Unit buildUnitToSI(Unit unit);


    /** Returns another Unit converthing this unit into another Unit Type. */
    Unit buildUnitIntoAnotherType(Unit unit, UnitType anotherType);


    
}
