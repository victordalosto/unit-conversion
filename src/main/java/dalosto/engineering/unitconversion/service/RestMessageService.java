package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.RestAttributes;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.RestStatus;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;


@Service
public class RestMessageService {

    @Autowired
    private ConversorService conversorService;


    public RestMessage getMessageForEndPoint(RestAttributes restAttributes) {
        RestMessage message = new RestMessage();
        appendResult(message, restAttributes);
        appendHeader(message, restAttributes);
        return message;
    }


    private void appendResult(RestMessage message, RestAttributes restAttributes) {
        if (restAttributes.getUnitDAO().doesntHaveData()) {
            appendDefaultHATEOASmessage(message, restAttributes);
        } else {
            appendConversionMessage(message, restAttributes);
        }   
    }


    private void appendDefaultHATEOASmessage(RestMessage message, RestAttributes restAttributes) {
        if (restAttributes.getRestURL().isCurrentURIaSIEndPoint()) {
            message.addResult(RestStatus.INFO, 
                    "title",  "This endpoint converts values to the International Standard ",
                    "types",  restAttributes.getEndpointInfo().getAllUnitsOfType(),
                       "si", "Converts values into: " + restAttributes.getEndpointInfo().getSIUnitofType());
        } else {
            message.addResult(RestStatus.INFO, 
                    "title",  "This endpoint provides functionality to convert " + restAttributes.getEndpointInfo().getTitle().toUpperCase() + " measurement units.",
                    "types",  restAttributes.getEndpointInfo().getAllUnitsOfType(),
                  "example",  "Check the example endpoint for a usage example.",
              "uri-example",  "/example",
                       "si",  "Check the SI endpoint to convert the value to the International Standard",
                   "uri-si",  restAttributes.getRestURL().getURIofSI());
        }
        
    }


    private void appendConversionMessage(RestMessage message, RestAttributes restAttributes) {
        try {
            Unit unitConverted = conversorService.formatUnitDAOAndConvertToUnit(restAttributes.getUnitDAO(), restAttributes.getEndpointInfo().getUnitFormula());
            appendResultOfConversion(message, unitConverted);
        } catch (ParameterException exception) {
            appendMessageOfError(message, exception);
        }
    }


    private void appendResultOfConversion(RestMessage message, Unit unit) {
        message.addResult(RestStatus.SUCCESS, "unit", unit.toString());
    }


    private void appendMessageOfError(RestMessage message, ParameterException exception) {
        message.addResult(RestStatus.ERROR, 
                          "ParameterException", exception.getMessage(), 
                          "example", "If you dont know how to use this API, check the example endpoint.",
                          "uri-example", "/example"
                          );
    }


    private void appendHeader(RestMessage message, RestAttributes restAttributes) {
        message.addToHeader("uri", restAttributes.getRestURL().getCurrentURI());
        message.addToHeader("home", restAttributes.getRestURL().getHomeURL());
        message.addToHeader("input", restAttributes.getUnitDAO().toString());
    }

} 