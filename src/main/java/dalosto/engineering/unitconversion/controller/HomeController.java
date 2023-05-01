package dalosto.engineering.unitconversion.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.RestURL;


@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    List<TemplateController> controllers;


    @Autowired
    RestURL restURL;

    
    @GetMapping
    public RestMessage getHomeMessage() {
        RestMessage message = new RestMessage();
        appendHeaderToMessage(message);
        appendResultsToMessage(message);
        return message;
    }


    private void appendHeaderToMessage(RestMessage message) {
        message.addToHeader("home", restURL.getHomeURL());
        message.addToHeader("title", "Unit Conversion API");
        message.addToHeader("about", "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description", "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader("reference", "https://github.com/victordalosto/UnitConversion");
    }


    private void appendResultsToMessage(RestMessage message) {
        for(TemplateController controller : controllers) {
            Map<String, String> results = new LinkedHashMap<>();
            // The next calls violates the Law of Demeter, but it's the best option considering the design.
            results.put("uri", controller.getEndpointInfo().getURI()); 
            results.put("about", "This endpoint converts "+controller.getEndpointInfo().getTitle()+" measurement units.");
            results.put("units", controller.getEndpointInfo().getAllUnits());
            message.addToResult(controller.getEndpointInfo().getTitle(), results);
        }
    }

}
