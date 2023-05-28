package dalosto.engineering.unitconversion.domain;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class UnitDAO {

    private String value;
    private String type;
    private String target;


    public boolean doesntHaveData() {
        return (this.value == null && this.type == null);
    }


    @Override
    public String toString() {
        return "{value=" + value + ", type=" + type + ", target=" + target + "}";
    }

}
