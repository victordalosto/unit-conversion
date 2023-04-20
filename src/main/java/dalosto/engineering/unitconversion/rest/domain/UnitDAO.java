package dalosto.engineering.unitconversion.rest.domain;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UnitDAO {

    private String value;
    private String type;
    private String target;

}
