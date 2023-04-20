package dalosto.engineering.unitconversion.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;


@Service
public class ConversorService {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;

    @Autowired
    private NumericService numericService;


    public Unit formatUnitDAOAndConvertToUnit(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        Unit unit = createUnitFromUnitDAO(unitDAO, unitFormula);
        UnitType targetType = getTargetUnitType(unitDAO, unitFormula);
        formatUnitDAO(unitDAO, unit, targetType);
        return unitFormula.buildUnitIntoAnotherType(unit, targetType);
    }


    public Unit createUnitFromUnitDAO(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        double value = numericService.convertToNumeric(unitDAO.getValue());
        UnitType type = mapUnitTypeService.getUnitTypeFromString(unitDAO.getType(), unitFormula);
        return new Unit(value, type);
    }


    private UnitType getTargetUnitType(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        if (unitDAO.getTarget() == null) {
            throw new ParameterException("target can't be NULL.");
        }
        return mapUnitTypeService.getUnitTypeFromString(unitDAO.getTarget(), unitFormula);
    }


    private void formatUnitDAO(UnitDAO unitDAO, Unit unit, UnitType targetType) {
        unitDAO.setValue(String.valueOf(unit.getValue()));
        unitDAO.setTarget(String.valueOf(targetType));
    }


}
