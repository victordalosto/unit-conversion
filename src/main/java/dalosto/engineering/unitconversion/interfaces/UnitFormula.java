package dalosto.engineering.unitconversion.interfaces;
import java.util.Set;
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


    /** Get the International System of Units (SI) Type of the Implemented Category */
    UnitType getSITypeOfThisCategory();

    
    /** Get All Unit Types of the Implemented Category */
    Set<UnitType> getAllUnitTypesOfThisCategory();
    
    
}
