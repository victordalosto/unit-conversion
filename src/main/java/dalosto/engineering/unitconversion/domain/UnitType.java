package dalosto.engineering.unitconversion.domain;
import java.util.Set;


/**
 * Representation of a Measurement Unit Type.
 */
public interface UnitType {

    
    /** Gets the UnitType in the International System of Units (SI). */
    UnitType getSITypeOfThisCategory();


    /** Returns all UnitTypes of this category  */
    Set<UnitType> getAllUnitTypesOfThisCategory();


}
