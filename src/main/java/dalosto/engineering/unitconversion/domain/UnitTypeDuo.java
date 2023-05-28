package dalosto.engineering.unitconversion.domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
/**
 * Representation of a Measurement Unit, containing two different Type.
 * Example: Pressure = Force / Area   or  Speed = Distance / Time
 */
public abstract class UnitTypeDuo implements UnitType {

    protected UnitType principal;
    protected UnitType secondary;


    public abstract String toString();

}
