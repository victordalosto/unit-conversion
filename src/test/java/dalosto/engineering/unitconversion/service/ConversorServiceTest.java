package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.Unit;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Density;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Linear;
import dalosto.engineering.unitconversion.units.Pressure;
import dalosto.engineering.unitconversion.units.Speed;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Torque;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
public class ConversorServiceTest {

    @Autowired
    private ConversorService service;


    @Test
    public void shouldBeAbreToConvertLengthUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("100", "MM", "CM");
        Unit unit = new Unit(10, Length.Types.CM);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Length()));
    }

    
    @Test
    public void shouldBeAbreToConvertAreaUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "IN^2", "MM^2");
        Unit unit = new Unit(2258.06, Area.Types.MM2);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Area()));
    }

    
    @Test
    public void shouldBeAbreToConvertVolumeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "L", "CM^3");
        Unit unit = new Unit(3500.0, Volume.Types.CM3);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Volume()));
    }

    
    @Test
    public void shouldBeAbreToConvertForceUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3500", "N", "KN");
        Unit unit = new Unit(3.5, Force.Types.KN);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Force()));
    }

    
    @Test
    public void shouldBeAbreToConvertTemperatureUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "C", "K");
        Unit unit = new Unit(276.65, Temperature.Types.K);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Temperature()));
    }

    
    @Test
    public void shouldBeAbreToConvertTimeUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("3.5", "DAY", "S");
        Unit unit = new Unit(3.5*86400, Time.Types.S);
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Time()));
    }

    
    @Test
    public void shouldBeAbreToConvertTorqueUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("1000000", "GF.M", "T.M");
        Unit unit = new Unit(1.0, Torque.factory(Force.Types.T, Length.Types.M));
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Torque()));
    }

    
    @Test
    public void shouldBeAbreToConvertLinearUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("5", "KGF/CM", "T/M");
        Unit unit = new Unit(0.5, Linear.factory(Force.Types.T, Length.Types.M));
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Linear()));
    }

    
    @Test
    public void shouldBeAbreToConvertPressureUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("5", "KGF/CM2", "T/M2");
        Unit unit = new Unit(50.0, Pressure.factory(Force.Types.T, Area.Types.M2));
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Pressure()));
    }

    
    @Test
    public void shouldBeAbreToConvertDensityUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("5", "KGF/M3", "GF/CM3");
        Unit unit = new Unit(0.005, Density.factory(Force.Types.GF, Volume.Types.CM3));
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Density()));
    }

    
    @Test
    public void shouldBeAbreToConvertSpeedUnitsFromUnitDAO() {
        UnitDAO unitDAO = new UnitDAO("35", "M/S", "KM/H");
        Unit unit = new Unit(126, Speed.factory(Length.Types.KM, Time.Types.H));
        assertEquals(unit , service.formatUnitDAOAndConvertToUnit(unitDAO, new Speed()));
    }



}
