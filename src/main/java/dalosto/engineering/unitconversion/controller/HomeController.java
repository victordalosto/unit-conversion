package dalosto.engineering.unitconversion.controller;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.rest.RestURL;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;


@RestController
@RequestMapping("/")
public final class HomeController {

    @Autowired
    private List<TemplateController> controllers;

    
    @GetMapping
    public Message getHomeMessage(HttpServletRequest request) {
        Message message = new Message();
        appendHeaderToMessage(message, request);
        appendResultsToMessage(message);
        return message;
    }


    private void appendHeaderToMessage(Message message, HttpServletRequest request) {
        message.addToHeader(      "title",  "Unit Conversion API");
        message.addToHeader(      "about",  "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description",  "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader(       "home",  new RestURL(request).getHomeURL());
        message.addToHeader(  "reference",  "https://github.com/victordalosto/unit-conversion");
    }


    private void appendResultsToMessage(Message message) {
        for(TemplateController controller : controllers) {
            String title = controller.getEndpointInfo().getTitle();
            Map<String, String> result = new LinkedHashMap<>();
            result.put("uri", controller.getEndpointInfo().getURIofType());
            result.put("about", "This endpoint converts "+title+" measurement units");
            result.put("units", controller.getEndpointInfo().getAllUnitsOfType());
            message.addToResult(title, result);
        }
    }


    @Data
    class Message {
        private Map<String, String> header = new LinkedHashMap<>();
        private Map<String, Map<String, String>> result = new LinkedHashMap<>();

        public void addToHeader(String key, String value) {
            header.put(key, value);
        }

        public void addToResult(String title, Map<String, String> map) {
            result.put(title, map);
        }


    }

}
