package dalosto.engineering.unitconversion.domain;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public abstract class UnitTypeDuo implements UnitType {

    protected UnitType principal;
    protected UnitType secondary;


    public abstract String toString();

}
