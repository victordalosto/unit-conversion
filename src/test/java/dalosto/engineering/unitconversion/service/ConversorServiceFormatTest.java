package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.domain.UnitDAO;
import dalosto.engineering.unitconversion.unit.Area;
import dalosto.engineering.unitconversion.unit.Density;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Inertia;
import dalosto.engineering.unitconversion.unit.Length;
import dalosto.engineering.unitconversion.unit.Linear;
import dalosto.engineering.unitconversion.unit.Pressure;
import dalosto.engineering.unitconversion.unit.Speed;
import dalosto.engineering.unitconversion.unit.Temperature;
import dalosto.engineering.unitconversion.unit.Time;
import dalosto.engineering.unitconversion.unit.Torque;
import dalosto.engineering.unitconversion.unit.Volume;


@SpringBootTest
public class ConversorServiceFormatTest {

    @Autowired
    private ConversorService service;


    @Test
    public void shouldBeAbreToFormatUnitDAOForPresentationAndKeepInfoIfTheyAreCorrect() {
        UnitDAO inputDAO; 

        inputDAO = new UnitDAO("12345.67", "MM", "MM");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Length());
        assertEquals(new UnitDAO("12345.67", "MM", "MM"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^2", "MM^2");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Area());
        assertEquals(new UnitDAO("12345.67", "MM2", "MM2"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^3", "MM^3");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Volume());
        assertEquals(new UnitDAO("12345.67", "MM3", "MM3"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^4", "MM^4");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Inertia());
        assertEquals(new UnitDAO("12345.67", "MM4", "MM4"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "N", "N");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Force());
        assertEquals(new UnitDAO("12345.67", "N", "N"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "C", "C");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Temperature());
        assertEquals(new UnitDAO("12345.67", "C", "C"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "S", "S");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Time());
        assertEquals(new UnitDAO("12345.67", "S", "S"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "KGF.M", "T.IN");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Torque());
        assertEquals(new UnitDAO("12345.67", "KGF.M", "T.IN"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "KGF/M", "T/IN");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Linear());
        assertEquals(new UnitDAO("12345.67", "KGF/M", "T/IN"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "KGF/M2", "T/IN2");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Pressure());
        assertEquals(new UnitDAO("12345.67", "KGF/M2", "T/IN2"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "KGF/M3", "T/IN3");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Density());
        assertEquals(new UnitDAO("12345.67", "KGF/M3", "T/IN3"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "M/S", "KM/H");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Speed());
        assertEquals(new UnitDAO("12345.67", "M/S", "KM/H"), inputDAO);
    }


    @Test
    public void shouldBeAbreToFormatUnitDAOAndChangeItsValueForBetterPresentation() {
        UnitDAO inputDAO;

        inputDAO = new UnitDAO("12345,67", " mM ", " MM ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Length());
        assertEquals(new UnitDAO("12345.67", "MM", "MM"), inputDAO);

        inputDAO = new UnitDAO("12345.6700000", " M m _ 2", " M M 2 ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Area());
        assertEquals(new UnitDAO("12345.67", "MM2", "MM2"), inputDAO);

        inputDAO = new UnitDAO("12345.,.67", " MM3", "MM3");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Volume());
        assertEquals(new UnitDAO("12345.67", "MM3", "MM3"), inputDAO);

        inputDAO = new UnitDAO(" 1 2 3 4 5.67 ", "MM ^ 4", "MM _ 4");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Inertia());
        assertEquals(new UnitDAO("12345.67", "MM4", "MM4"), inputDAO);

        inputDAO = new UnitDAO("  12345.67", "  n  ", " n ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Force());
        assertEquals(new UnitDAO("12345.67", "N", "N"), inputDAO);

        inputDAO = new UnitDAO("12 345.67", "ºC", "ºC");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Temperature());
        assertEquals(new UnitDAO("12345.67", "C", "C"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "kg*m", "kgf . m");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Torque());
        assertEquals(new UnitDAO("12345.67", "KG.M", "KGF.M"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "N/m", "N/m");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Linear());
        assertEquals(new UnitDAO("12345.67", "N/M", "N/M"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "KpA", "K P A");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Pressure());
        assertEquals(new UnitDAO("12345.67", "KPA", "KPA"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "N/L", "N/l");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Density());
        assertEquals(new UnitDAO("12345.67", "N/L", "N/L"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "m/s", "m/S");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Speed());
        assertEquals(new UnitDAO("12345.67", "M/S", "M/S"), inputDAO);
    }

}