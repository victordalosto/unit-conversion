package dalosto.engineering.unitconversion.rest;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;

/**
 *  CREATES a REST MESSAGE in JSON FORMAT
    {
        "header": {
            header.key1: header.value1
            header.key2: header.value2
        },
        "result": {
            "result.key1": {
                result.key1.key1: result.key1.value1
                result.key1.key2: result.key1.value2
            }
        }
    }
*/
@Getter
public final class RestMessage {

    private final Map<String, String> header = new LinkedHashMap<>();
    private final Map<String, Map<String, String>> result = new LinkedHashMap<>();


    public void addToHeader(String key, String value) {
        this.header.put(key, value);
    }


    public void setResult(String key, Map<String, String> map) {
        this.result.put(key, map);
    }


    public void addResultWithStatus(RestStatus status, String ... keysAndValues) {
        Map<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            map.put(keysAndValues[i], keysAndValues[i + 1]);
        }
        this.result.put(status.toString().toLowerCase(), map);
    }


}
