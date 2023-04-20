package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.DescriptionOfEndpoint;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.service.ConversorService;


@RestController
@RequestMapping("/api/length")
public class LengthController extends TemplateController {

    @Autowired
    @Qualifier("Length")
    private UnitFormula unitFormula;

    @Autowired
    private ConversorService conversorService;


    @GetMapping
    public MessageRest home(UnitDAO unitDAO) {
        return conversorService.generateMessageRest(unitDAO, unitFormula);
    }


    @Override
    public DescriptionOfEndpoint description() {
        return DescriptionOfEndpoint.builder()
                                    .title("Length")
                                    .uri("/api/length")
                                    .unitTypes(unitFormula.getAllUnitTypesOfThisCategory().toString())
                                    .build();
    }
    
}
