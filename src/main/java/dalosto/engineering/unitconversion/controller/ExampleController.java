package dalosto.engineering.unitconversion.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.rest.RestStatus;
import dalosto.engineering.unitconversion.rest.RestURL;
import dalosto.engineering.unitconversion.service.ConversorService;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("example")
public final class ExampleController {
    
    @Autowired
    @Qualifier("area")
    private UnitFormula unitFormula;

    @Autowired
    private ConversorService service;


    @GetMapping
    public RestMessage home(UnitDAO unitDAO, HttpServletRequest request) {
        RestMessage message = new RestMessage();
        appendHeader(message, request);
        appendResult(message);
        return message;
    }


    private void appendHeader(RestMessage message, HttpServletRequest request) {
        message.addToHeader(RestStatus.INFO);
        message.addToHeader("uri", "/example");
        message.addToHeader("home", new RestURL(request).getHomeURL());
        message.addToHeader("title", "This endpoint provides example in how to use this API to convert measurement units.");
        message.addToHeader("description", "Given a quantity expressed in a unit type, the end-points returns the equivalent quantity in a different measurement unit.");
    }


    private void appendResult(RestMessage message) {
        String value = "12345.67";
        String type = unitFormula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString();
        String target = unitFormula.getAllUnitTypesOfThisCategory().stream().skip(1).findFirst().get().toString();
        message.addToResult("example",  "How to convert " + value + " " + type + " into " + target + "  ?");
        message.addToResult("GET  Request",  "/api/area?value=" + value + "&type=" + type + "&target=" + target);
        message.addToResult("POST Request",  "/api/area" + "  Body: {'value': " + value + ", 'type': '" + type + "', 'target': '" + target + "'}");
        message.addToResult("Response",  RestStatus.possibleStatus() + "   " + service.formatUnitDAOAndConvertToUnit(new UnitDAO(value, type, target), unitFormula));
        message.addToResult("observation",  "Parameters are resilient. Values can be represented using comma (1,23), dot (1.23), or contain noise (myVal is 1.23)");
        message.addToResult("observation2",  "Types are also resilient. Types can be presented in: [ M2 ] or [ M² ] or [ M^2 ] or [ M_2 ] or [ M 2 ]...");
    }

}
