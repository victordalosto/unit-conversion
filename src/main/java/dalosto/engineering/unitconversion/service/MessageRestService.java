package dalosto.engineering.unitconversion.service;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.domain.EndpointInfo;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@Service
public class MessageRestService {

    @Autowired
    private ConversorService conversorService;


    public MessageRest getMessageForEndPoint(EndpointInfo info, UnitDAO unitDAO) {
        MessageRest messageRest = new MessageRest();
        appendHeader(messageRest, info, unitDAO);
        appendResult(messageRest, info, unitDAO);
        return messageRest;
    }


    private void appendHeader(MessageRest msg, EndpointInfo info, UnitDAO unitDAO) {
        msg.addToHeader("uri", info.getUri());
        msg.addToHeader("input", unitDAO.toString());
    }


    private void appendResult(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
        if (unitDAO.isEmpty()) {
            appendDefaultHATEOASmessage(messageRest, info);
        } else {
            appendConversion(messageRest, info, unitDAO);
        }
    }


    private void appendConversion(MessageRest messageRest, EndpointInfo info, UnitDAO unitDAO) {
        try {
            Unit unit = conversorService.convertUnit(unitDAO, info.getUnitFormula());
            appendHeader(messageRest, info, unitDAO); // This lines fixes the unitDAO
            appendResultOfConversion(messageRest, unit);
        } catch (ParameterException e) {
            appendMessageOfError(messageRest, info, e);
        }
    }


    private void appendResultOfConversion(MessageRest messageRest, Unit unit) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("unit", unit.toString());
        messageRest.addToResult("sucess", map);
    }


    private void appendDefaultHATEOASmessage(MessageRest messageRest, EndpointInfo info) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("about", "Check the /example endpoint for a usage example.");
        map.put("uri", info.getUri() + "/example");
        messageRest.addToResult("info", map);
    }


    private void appendMessageOfError(MessageRest messageRest, EndpointInfo info, ParameterException e) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("ParameterException", e.getMessage());
        map.put("about", "Check the /example endpoint to verify the correct API usage.");
        map.put("uri", info.getUri() + "/example");
        messageRest.addToResult("error", map);
    }

}
