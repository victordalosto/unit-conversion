package dalosto.engineering.unitconversion.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.rest.RestURL;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public final class HomeController {

    @Autowired
    private List<TemplateController> controllers;

    
    @GetMapping
    public RestMessage getHomeMessage(HttpServletRequest request) {
        RestMessage message = new RestMessage();
        appendHeaderToMessage(message, request);
        appendResultsToMessage(message);
        return message;
    }


    private void appendHeaderToMessage(RestMessage message, HttpServletRequest request) {
        message.addToHeader("home", new RestURL(request).getHomeURL());
        message.addToHeader("title", "Unit Conversion API");
        message.addToHeader("about", "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description", "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader("reference", "https://github.com/victordalosto/UnitConversion");
    }


    private void appendResultsToMessage(RestMessage message) {
        for(TemplateController controller : controllers) {
            Map<String, String> results = new LinkedHashMap<>();
            // The next calls violates the Law of Demeter, but it's the best option considering the design.
            results.put("uri", controller.getEndpointInfo().getURIofType()); 
            results.put("about", "This endpoint converts "+controller.getEndpointInfo().getTitle()+" measurement units.");
            results.put("units", controller.getEndpointInfo().getAllUnitsOfType());
            message.setResult(controller.getEndpointInfo().getTitle(), results);
        }
    }

}
