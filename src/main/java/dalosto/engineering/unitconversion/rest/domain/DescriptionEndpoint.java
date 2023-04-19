package dalosto.engineering.unitconversion.rest.domain;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class DescriptionEndpoint {

    private String title;
    private String uri;
    private String unitTypes; 

}
