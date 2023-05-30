package dalosto.engineering.unitconversion.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.rest.RestStatus;
import dalosto.engineering.unitconversion.rest.RestURL;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/error")
public class GlobalExceptionHandler implements ErrorController {

    @RequestMapping
    public ResponseEntity<RestMessage> handleError(HttpServletRequest request) {
        RestMessage restMessage = new RestMessage();
        appendHeander(restMessage, request);
        appendBody(restMessage, request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restMessage);
    }


    private void appendHeander(RestMessage restMessage, HttpServletRequest request) {
        restMessage.addToHeader(RestStatus.ERROR);
        restMessage.addToHeader("message", "404 - Not Found");
    }


    private void appendBody(RestMessage restMessage, HttpServletRequest request) {
        restMessage.addToResult("message", "invalid endpoint");
        restMessage.addToResult("about", "Go back to home to see all avaliable end-points");
        restMessage.addToResult("uri", new RestURL(request).getHomeURL());
    }

}
