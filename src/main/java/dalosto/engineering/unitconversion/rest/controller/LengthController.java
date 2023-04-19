package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.domain.DescriptionEndpoint;


@RestController
@RequestMapping("/api/length")
public class LengthController extends TemplateController {

    @Autowired
    @Qualifier("Length")
    private UnitFormula unitFormula;


    @Override
    public DescriptionEndpoint getDescription() {
        return DescriptionEndpoint.builder()
                                  .title("Length")
                                  .uri("/api/length")
                                  .unitTypes(unitFormula.getAllUnitTypesOfThisCategory().toString())
                                  .build();
    }

    

  
    
}
