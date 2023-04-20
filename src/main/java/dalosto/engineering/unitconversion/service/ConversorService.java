package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.domain.MessageRest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@Service
public class ConversorService {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;

    @Autowired
    private NumericService numericService;


    public MessageRest generateMessageRest(UnitDAO unitDAO, UnitFormula unitFormula) {
        // Unit unit = createUnitFromUnitDAO(unitDAO, unitFormula);
        return null;
    }


    public Unit createUnitFromUnitDAO(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        double value = numericService.convertToNumeric(unitDAO.getValue());
        UnitType type = mapUnitTypeService.getUnitTypeFromString(unitDAO.getType(), unitFormula);
        return new Unit(value, type);
    }
    
}
