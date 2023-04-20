package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
public class TestConversorService {

    @Autowired
    private ConversorService service;


    @Test
    void shouldBeAbreToCreateLengthUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "MM", "mock");
        Unit unit = new Unit(3.5, Length.Types.MM);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Length()));
    }

    
    @Test
    void shouldBeAbreToCreateAreaUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "IN^2", "mock");
        Unit unit = new Unit(3.5, Area.Types.IN_2);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Area()));
    }

    
    @Test
    void shouldBeAbreToCreateVolumeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "L", "mock");
        Unit unit = new Unit(3.5, Volume.Types.L);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Volume()));
    }

    
    @Test
    void shouldBeAbreToCreateForceUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "N", "mock");
        Unit unit = new Unit(3.5, Force.Types.N);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Force()));
    }

    
    @Test
    void shouldBeAbreToCreateTemperatureUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "C", "mock");
        Unit unit = new Unit(3.5, Temperature.Types.C);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Temperature()));
    }

    
    @Test
    void shouldBeAbreToCreateTimeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "S", "mock");
        Unit unit = new Unit(3.5, Time.Types.S);
        assertEquals(unit , service.createUnitFromUnitDAO(unitDAO, new Time()));
    }


    @Test
    void shouldThrowExceptionWhenInvalidParamIsGiven() {
        UnitDAO unitDAO = new UnitDAO("3.5", "INVALID", "mock");
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO, new Length()));
    }


    @Test
    void shouldThrowExceptionWhenWrongUnitTypeIsGiven() {
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Area()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Force()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Inertia()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M_2", "mock"), new Length()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Temperature()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Time()));
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(new UnitDAO("3.5", "M", "mock"), new Volume()));
    }


    @Test
    void serviceShouldThrowExceptionIfNullValueIsPassed() {
        UnitDAO unitDAO = new UnitDAO(null, "M2", "mock");
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO, new Length()));
    }

    @Test
    void serviceShouldThrowExceptionIfNullTypeIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", null, "mock");
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO, new Length()));
    }


    @Test
    void serviceShouldThrowExceptionIfNullFormulaIsPassed() {
        UnitDAO unitDAO = new UnitDAO("3.5", "M2", null);
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO, new Length()));
    }

    
    @Test
    void serviceShouldThrowExceptionEmptyValueIsPassed() {
        UnitDAO unitDAO = new UnitDAO("0.0", "", "mock");
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO, new Length()));
        UnitDAO unitDAO2 = new UnitDAO("", "M", "mock");
        assertThrows(ParameterException.class, () -> service.createUnitFromUnitDAO(unitDAO2, new Length()));
    }
}
