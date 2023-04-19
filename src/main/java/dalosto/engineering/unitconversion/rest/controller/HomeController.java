package dalosto.engineering.unitconversion.rest.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.rest.domain.DescriptionEndpoint;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    List<TemplateController> controllers;


    @GetMapping
    public MessageRest getHomeMessage() {
        MessageRest message = new MessageRest();
        message.addToHeader("title", "Unit Conversion API");
        message.addToHeader("about", "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description", "Given a quantity expressed in a certain length measurement unit, the endpoint returns the equivalent quantity expressed in a different length measurement unit");
        message.addToHeader("reference", "https://github.com/victordalosto/UnitConversion");
        appendResultsToMessage(message);
        return message;
    }

    private void appendResultsToMessage(MessageRest message) {
        for(TemplateController controller : controllers) {
            DescriptionEndpoint description = controller.getDescription();
            Map<String, String> results = new LinkedHashMap<>();
            results.put("uri", description.getUri());
            results.put("about", "This endpoint converts "+description.getTitle()+" measurement units.");
            results.put("units", description.getUnitTypes().replaceAll("_", "\\^"));
            message.addToResult(description.getTitle(), results);
        }
    }
}
