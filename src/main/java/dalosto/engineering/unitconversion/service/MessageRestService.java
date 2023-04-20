package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.RestStatus;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@Service
public class MessageRestService {

    @Autowired
    private ConversorService conversorService;


    public MessageRest getMessageForEndPoint(EndpointInfo info, UnitDAO unitDAO) {
        MessageRest messageRest = new MessageRest();
        appendResult(messageRest, info, unitDAO);
        appendHeader(messageRest, info, unitDAO);
        return messageRest;
    }


    private void appendResult(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
        if (info.isExampleURI()) {
            appendExampleMessage(messageRest, info, unitDAO);
        } else if (unitDAO.doesntHaveData()) {
            appendDefaultHATEOASmessage(messageRest, info);
        } else {
            appendConversionMessage(messageRest, info, unitDAO);
        }   
    }


    private void appendDefaultHATEOASmessage(MessageRest messageRest, EndpointInfo info) {
        messageRest.setResult(RestStatus.INFO, 
                              "about", "Check the /example endpoint for a usage example.", 
                              "uri", info.getURIExample());
    }


    private void appendExampleMessage(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
    }


    private void appendConversionMessage(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
        try {
            Unit unitConverted = conversorService.formatUnitDAOAndConvertToUnit(unitDAO, info.getUnitFormula());
            appendResultOfConversion(messageRest, unitConverted);
        } catch (ParameterException e) {
            appendMessageOfError(messageRest, info, e);
        }
    }


    private void appendResultOfConversion(MessageRest messageRest, Unit unit) {
        messageRest.setResult(RestStatus.SUCESS, "unit", unit.toString());
    }


    private void appendMessageOfError(MessageRest messageRest, EndpointInfo info, ParameterException e) {
        messageRest.setResult(RestStatus.ERROR, 
                              "ParameterException", e.getMessage(), 
                              "about", "Check the /example endpoint to verify the correct API usage.", 
                              "uri", info.getURIExample());
    }


    private void appendHeader(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
        messageRest.addToHeader("url", info.getUri());
        messageRest.addToHeader("input", unitDAO.toString());
    }

} 