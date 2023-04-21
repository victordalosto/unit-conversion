package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.RestMessage;
import dalosto.engineering.unitconversion.rest.domain.RestStatus;
import dalosto.engineering.unitconversion.rest.domain.RestURL;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@Service
public class RestMessageService {

    @Autowired
    private ConversorService conversorService;

    @Autowired
    private RestURL restURL;


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
        message.setResult(RestStatus.INFO, 
                          "title", "This endpoint provides functionality to convert " + info.getTitle().toUpperCase() + " measurement units.",
                          "types", info.getAllUnits(),
                          "about", "Check the example endpoint for a usage example.",
                          "uri", "/example"
                          );
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
        message.setResult(RestStatus.SUCCESS, "unit", unit.toString());
    }


    private void appendMessageOfError(RestMessage message, EndpointInfo info, ParameterException e) {
        message.setResult(RestStatus.ERROR, 
                          "ParameterException", e.getMessage(), 
                          "about", "Check the example endpoint to verify the correct API usage.",
                          "uri", "/example"
                          );
    }


    private void appendHeader(RestMessage message, EndpointInfo info, UnitDAO unitDAO) {
        message.addToHeader("uri", restURL.getURI());
        message.addToHeader("home", restURL.getHomeURL());
        message.addToHeader("input", unitDAO.toString());
    }

} 