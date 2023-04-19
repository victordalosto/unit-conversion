package dalosto.engineering.unitconversion.rest.domain;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;


@Getter
public class MessageRest {

    private Map<String, String> header = new LinkedHashMap<>();
    private Map<String, Map<String, String>> result = new LinkedHashMap<>();


    public void addToHeader(String key, String value) {
        this.header.put(key, value);
    }


    public void addToResult(String key, Map<String, String> map) {
        this.result.put(key, map);
    }

}
