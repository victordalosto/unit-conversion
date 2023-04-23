package dalosto.engineering.unitconversion.domain;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;


@Getter
public class RestMessage {

    private Map<String, String> header = new LinkedHashMap<>();
    private Map<String, Map<String, String>> result = new LinkedHashMap<>();


    public void addToHeader(String key, String value) {
        this.header.put(key, value);
    }


    public void addToResult(String key, Map<String, String> map) {
        this.result.put(key, map);
    }


    public void setResult(RestStatus status, String ... keysAndValues) {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            map.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        this.result.put(status.toString().toLowerCase(), map);
    }


}
