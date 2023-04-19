package dalosto.engineering.unitconversion.rest.domain;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class DescriptionOfEndpoint {

    private String title;
    private String uri;
    private String unitTypes; 

}
