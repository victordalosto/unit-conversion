package dalosto.engineering.unitconversion.domain;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


/**
 * Representation of a Measurement Unit, containing a value and a Type.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class Unit {

    /** Returns the value of the unit in the defined unit type. */
    public final double value;
    
    /** Returns the unit type defined. */
    private final UnitType type;


    @Override
    public String toString() {
        return "{value=" + value + ", type=" + type.toString().replaceAll("_", "") + "}";
    }

}
