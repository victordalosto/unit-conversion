package dalosto.engineering.unitconversion.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


/**
 * Representation of a Measurement Unit, containing a value and a Type.
 */
@Getter
@AllArgsConstructor
@ToString
public final class Unit {


    /** Returns the value of the unit in the defined unit type. */
    public final double value;


    /** Returns the unit type defined. */
    private final UnitType unitType;


}
