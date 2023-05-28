package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.RestAttributes;
import dalosto.engineering.unitconversion.rest.RestMessage;
import dalosto.engineering.unitconversion.rest.RestStatus;


@Service
public final class RestMessageService {

    @Autowired
    private ConversorService conversorService;


    public RestMessage getMessageForEndPoint(RestAttributes restAttributes) {
        RestMessage message = new RestMessage();
        appendResult(message, restAttributes);
        appendHeader(message, restAttributes);
        return message;
    }


    private void appendResult(RestMessage message, RestAttributes restAttributes) {
        if (restAttributes.getRestURL().isCurrentURIaSIEndPoint()) {
            conversorService.convertToSItheUnitDAO(restAttributes.getUnitDAO(), restAttributes.getEndpointInfo().getUnitFormula());
        }
        if (restAttributes.getUnitDAO().doesntHaveData()) {
            appendDefaultHATEOASmessage(message, restAttributes);
        } else {
            appendConversionMessage(message, restAttributes);
        }   
    }


    private void appendDefaultHATEOASmessage(RestMessage message, RestAttributes restAttributes) {
        message.addToHeader(RestStatus.INFO);
        if (restAttributes.getRestURL().isCurrentURIaSIEndPoint()) {
            message.addToResult("title", "This endpoint converts values to the International Standard ");
            message.addToResult("types", restAttributes.getEndpointInfo().getAllUnitsOfType());
            message.addToResult("si", "Converts values into: " + restAttributes.getEndpointInfo().getSIUnitofType());
        } else {
            message.addToResult("title", "This endpoint provides functionality to convert " + restAttributes.getEndpointInfo().getTitle().toUpperCase() + " measurement units.");
            message.addToResult("types", restAttributes.getEndpointInfo().getAllUnitsOfType());
            message.addToResult("example", "Check the example endpoint for a usage example.");
            message.addToResult("uri-example", "/example");
            message.addToResult("si", "Check the SI endpoint to convert the value to the International Standard");
            message.addToResult("uri-si", restAttributes.getRestURL().getURIofSI());
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
        message.addToHeader(RestStatus.SUCCESS);
        message.addToResult("unit", unit.toString());
    }


    private void appendMessageOfError(RestMessage message, ParameterException exception) {
        message.addToHeader(RestStatus.ERROR);
        message.addToResult("ParameterException", exception.getMessage());
        message.addToResult("example", "If you dont know how to use this API, check the example endpoint.");
        message.addToResult("uri-example", "/example");
    }


    private void appendHeader(RestMessage message, RestAttributes restAttributes) {
        message.addToHeader("input", restAttributes.getUnitDAO().toString());
        message.addToHeader("uri", restAttributes.getRestURL().getCurrentURI());
        message.addToHeader("home", restAttributes.getRestURL().getHomeURL());
    }

} 