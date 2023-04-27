package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
public class ConversorServiceForDataCreationTet {

    @Autowired
    private ConversorService service;


    @Test
    public void shouldBeAbreToCreateLengthUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "MM", "MM");
        Unit unit = new Unit(3.5, Length.Types.MM);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }

    
    @Test
    public void shouldBeAbreToCreateAreaUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "IN^2", "IN^2");
        Unit unit = new Unit(3.5, Area.Types.IN2);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Area()));
    }

    
    @Test
    public void shouldBeAbreToCreateVolumeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "L", "L");
        Unit unit = new Unit(3.5, Volume.Types.L);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Volume()));
    }

    
    @Test
    public void shouldBeAbreToCreateForceUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "N", "N");
        Unit unit = new Unit(3.5, Force.Types.N);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Force()));
    }

    
    @Test
    public void shouldBeAbreToCreateTemperatureUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "C", "C");
        Unit unit = new Unit(3.5, Temperature.Types.C);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Temperature()));
    }

    
    @Test
    public void shouldBeAbreToCreateTimeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "S", "S");
        Unit unit = new Unit(3.5, Time.Types.S);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Time()));
    }


    @Test
    public void shouldThrowExceptionWhenInvalidParamIsGiven() {
        UnitDAO unitDAO = new UnitDAO("3.5", "INVALID", "mock");
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }


    @Test
    public void shouldThrowExceptionWhenWrongUnitTypeIsGiven() {
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Area()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Force()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Inertia()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M_2", "mock"), new Length()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Temperature()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Time()));
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(new UnitDAO("3.5", "M", "mock"), new Volume()));
    }


    @Test
    public void serviceShouldThrowExceptionIfNullValueIsPassed() {
        UnitDAO unitDAO = new UnitDAO(null, "M2", "mock");
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }

    @Test
    public void serviceShouldThrowExceptionIfNullTypeIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", null, "mock");
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }


    @Test
    public void serviceShouldThrowExceptionIfNullFormulaIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", "M2", null);
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }

    
    @Test
    public void serviceShouldThrowExceptionEmptyValueIsPassed() {
        UnitDAO unitDAO = new UnitDAO("0.0", "", "mock");
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
        UnitDAO unitDAO2 = new UnitDAO("", "M", "mock");
        assertThrows(ParameterException.class, () -> service.formatUnitDAOAndConvertToUnit(unitDAO2, new Length()));
    }
}
