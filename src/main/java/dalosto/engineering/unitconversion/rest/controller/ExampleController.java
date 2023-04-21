package dalosto.engineering.unitconversion.rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.domain.RestMessage;
import dalosto.engineering.unitconversion.rest.domain.RestStatus;
import dalosto.engineering.unitconversion.rest.domain.RestURL;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.service.ConversorService;


@RestController
@RequestMapping("example")
public class ExampleController {
    
    @Autowired
    @Qualifier("area")
    private UnitFormula unitFormula;

    @Autowired
    ConversorService service;

    @Autowired
    RestURL restURL;


    @GetMapping
    public RestMessage home(UnitDAO unitDAO) {
        RestMessage message = new RestMessage();
        appendHeader(message);
        appendResult(message);
        return message;
    }


    private void appendHeader(RestMessage message) {
        message.addToHeader("uri", "/example");
        message.addToHeader("home", restURL.getHomeURL());
        message.addToHeader("title", "This endpoint provides example in how to use this API to convert measurement units.");
        message.addToHeader("description", "Given a quantity expressed in a unit type, the end-points returns the equivalent quantity in a different measurement unit.");
    }


    private void appendResult(RestMessage message) {
        String value = "12345.67";
        String type = unitFormula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString().replaceAll("_", "");
        String target = unitFormula.getAllUnitTypesOfThisCategory().stream().skip(1).findFirst().get().toString().replaceAll("_", "");
        
        message.setResult(RestStatus.INFO, 
                          "example", "How to convert " + value + " " + type + " into " + target + "  ?",
                          "GET  Request",  "/api/area?value=" + value + "&type=" + type + "&target=" + target,
                          "POST Request",  "/api/area" + "  Body: {'value': " + value + ", 'type': '" + type + "', 'target': '" + target + "'}",
                          "Response", RestStatus.possibleStatus() + "   " + service.formatUnitDAOAndConvertToUnit(new UnitDAO(value, type, target), unitFormula),
                          "observation", "Parameters are resilient. Values can be represented using comma (1,23), dot (1.23), or contain noise (myValue is 1.23)",
                          "observation2", "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]..."
                          );
    }

}
