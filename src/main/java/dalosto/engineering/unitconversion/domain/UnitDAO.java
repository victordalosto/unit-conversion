package dalosto.engineering.unitconversion.domain;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UnitDAO {

    private String value;
    private String type;
    private String target;


    public boolean doesntHaveData() {
        return (this.value == null && this.type == null && this.target == null);
    }


    @Override
    public String toString() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("value", this.value);
        map.put("type", this.type);
        map.put("target", this.target);
        return map.toString();
    }


    public void setValue(String value) {
        this.value = value;
    }


    public void setType(String type) {
        this.type = type.replaceAll("_", "");
    }


    public void setTarget(String target) {
        this.target = target.replaceAll("_", "");
    }

}
