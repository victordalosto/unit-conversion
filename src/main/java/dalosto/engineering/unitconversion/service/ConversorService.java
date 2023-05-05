package dalosto.engineering.unitconversion.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.formula.UnitFormula;


@Service
public final class ConversorService {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;

    @Autowired
    private NumericService numericService;


    public Unit formatUnitDAOAndConvertToUnit(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        Unit unit = createUnitFromUnitDAO(unitDAO, unitFormula);
        UnitType targetType = getTargetUnitType(unitDAO, unitFormula);
        return createNewUnit(unitFormula, unit, targetType);
    }


    public void convertToSItheUnitDAO(UnitDAO unitDAO, UnitFormula unitFormula) {
        unitDAO.setTarget(unitFormula.getSITypeOfThisCategory().toString());
    }


    private Unit createUnitFromUnitDAO(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        double value = numericService.convertToNumeric(unitDAO.getValue());
        unitDAO.setValue(String.valueOf(value));
        UnitType type = mapUnitTypeService.getUnitTypeFromString(unitDAO.getType(), unitFormula);
        unitDAO.setType(String.valueOf(type));
        return new Unit(value, type);
    }


    private UnitType getTargetUnitType(UnitDAO unitDAO, UnitFormula unitFormula) throws ParameterException {
        if (unitDAO.getTarget() == null) {
            throw new ParameterException("target can't be NULL.");
        }
        UnitType targetType = mapUnitTypeService.getUnitTypeFromString(unitDAO.getTarget(), unitFormula);
        unitDAO.setTarget(String.valueOf(targetType));
        return targetType;
    }


    private Unit createNewUnit(UnitFormula unitFormula, Unit unit, UnitType targetType) {
        Unit convertedUnit = unitFormula.buildUnitIntoAnotherType(unit, targetType);
        return formatUnit(convertedUnit);
    }


    private Unit formatUnit(Unit unit) {
        if (Math.abs(unit.getValue()) < Math.pow(10, -12)) {
            return unit;
        }
        BigDecimal roundedValue = new BigDecimal(unit.getValue()).setScale(12, RoundingMode.HALF_UP);
        return new Unit(roundedValue.doubleValue(), unit.getType());
    }


}
