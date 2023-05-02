package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.EndpointInfo;
import dalosto.engineering.unitconversion.domain.RestMessage;
import dalosto.engineering.unitconversion.domain.RestStatus;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.exception.ParameterException;


@Service
public class RestMessageService {

    @Autowired
    private ConversorService conversorService;


    public RestMessage getMessageForEndPoint(EndpointInfo info, UnitDAO unitDAO) {
        RestMessage message = new RestMessage();
        appendResult(message, info, unitDAO);
        appendHeader(message, info, unitDAO);
        return message;
    }


    private void appendResult(RestMessage message, EndpointInfo info, UnitDAO unitDAO) {
        if (unitDAO.doesntHaveData()) {
            appendDefaultHATEOASmessage(message, info);
        } else {
            appendConversionMessage(message, info, unitDAO);
        }   
    }


    private void appendDefaultHATEOASmessage(RestMessage message, EndpointInfo info) {
        if (info.isSIEndPoint()) {
            message.addResult(RestStatus.INFO, 
                    "title",  "This endpoint converts values to the International Standard ",
                    "types",  info.getAllUnits(),
                       "si", "converts values into: " + info.getUnitFormula().getSITypeOfThisCategory());
        } else {
            message.addResult(RestStatus.INFO, 
                    "title",  "This endpoint provides functionality to convert " + info.getTitle().toUpperCase() + " measurement units.",
                    "types",  info.getAllUnits(),
                  "example",  "Check the example endpoint for a usage example.",
              "uri-example",  "/example",
                       "si",  "Check the SI endpoint to convert the value to the International Standard",
                   "uri-si",  info.getSIEndPoint());
        }
        
    }


    private void appendConversionMessage(RestMessage message, EndpointInfo info, UnitDAO unitDAO) {
        try {
            Unit unitConverted = conversorService.formatUnitDAOAndConvertToUnit(unitDAO, info.getUnitFormula());
            appendResultOfConversion(message, unitConverted);
        } catch (ParameterException e) {
            appendMessageOfError(message, info, e);
        }
    }


    private void appendResultOfConversion(RestMessage message, Unit unit) {
        message.addResult(RestStatus.SUCCESS, "unit", unit.toString());
    }


    private void appendMessageOfError(RestMessage message, EndpointInfo info, ParameterException e) {
        message.addResult(RestStatus.ERROR, 
                          "ParameterException", e.getMessage(), 
                          "example", "If you dont know how to use this API, check the example endpoint.",
                          "uri-example", "/example"
                          );
    }


    private void appendHeader(RestMessage message, EndpointInfo info, UnitDAO unitDAO) {
        message.addToHeader("uri", info.getURI());
        message.addToHeader("home", info.getHomeURL());
        message.addToHeader("input", unitDAO.toString());
    }

} 