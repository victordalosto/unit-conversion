package dalosto.engineering.unitconversion.rest.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.rest.controller.template.TemplateController;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    List<TemplateController> controllers;


    @GetMapping
    public MessageRest getHomeMessage() {
        MessageRest message = new MessageRest();
        appendHeaderToMessage(message);
        appendResultsToMessage(message);
        return message;
    }


    private void appendHeaderToMessage(MessageRest message) {
        message.addToHeader("title", "Unit Conversion API");
        message.addToHeader("about", "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description", "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader("reference", "https://github.com/victordalosto/UnitConversion");
    }


    private void appendResultsToMessage(MessageRest message) {
        appendEndPointsToResults(message);
    }


    private void appendEndPointsToResults(MessageRest message) {
        for(TemplateController controller : controllers) {
            Map<String, String> results = new LinkedHashMap<>();
            // The next calls violates the Law of Demeter, but it's the best option considering the design.
            results.put("uri", controller.getEndpointInfo().getUri()); 
            results.put("about", "This endpoint converts "+controller.getEndpointInfo().getTitle()+" measurement units.");
            results.put("units", controller.getEndpointInfo().getUnitFormula().getAllUnitTypesOfThisCategory().toString().replaceAll("_", "\\^"));
            message.addToResult(controller.getEndpointInfo().getTitle(), results);
        }
    }
}
