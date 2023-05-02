package dalosto.engineering.unitconversion.domain;
import java.util.LinkedHashMap;
import java.util.Map;
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
        Map<String, String> map = new LinkedHashMap<>();
        map.put("value", this.value);
        map.put("type", this.type);
        map.put("target", this.target);
        return map.toString();
    }

}
