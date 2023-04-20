package dalosto.engineering.unitconversion.rest.domain;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class EndpointInfo {

    private String title;
    private String uri;
    private UnitFormula unitFormula; 

}
