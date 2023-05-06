package dalosto.engineering.unitconversion.controller;
import java.util.List;
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
        message.addToHeader(       "home",  new RestURL(request).getHomeURL());
        message.addToHeader(      "title",  "Unit Conversion API");
        message.addToHeader(      "about",  "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description",  "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader(  "reference",  "https://github.com/victordalosto/unit-conversion");
    }


    private void appendResultsToMessage(RestMessage message) {
        for(TemplateController controller : controllers) {
            String title = controller.getEndpointInfo().getTitle();
            message.addResultWithTitle(title, 
                            "uri", controller.getEndpointInfo().getURIofType(),
                            "about", "This endpoint converts "+title+" measurement units.",
                            "units", controller.getEndpointInfo().getAllUnitsOfType());
        }
    }

}
