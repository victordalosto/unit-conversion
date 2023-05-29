package dalosto.engineering.unitconversion.rest;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.ToString;


/** CREATES a REST MESSAGE in JSON FORMAT
    {
        "header": {
            header.key1: header.value1
            header.key2: header.value2
        },
        "result": {
            result.key1: result.value1
            result.key2: result.value2
        }
    }                                                  */
@Getter
@ToString
public final class RestMessage {

    private final Map<String, String> header = new LinkedHashMap<>();
    private final Map<String, String> result = new LinkedHashMap<>();

    @JsonIgnore 
    private RestStatus status;


    public void addToHeader(String key, String value) {
        this.header.put(key, value);
    }


    public void addToHeader(RestStatus status) {
        this.status = status;
        addToHeader("status", status.toString().toLowerCase());
    }


    public void addToResult(String key, String value) {
        this.result.put(key, value);
    }

}
