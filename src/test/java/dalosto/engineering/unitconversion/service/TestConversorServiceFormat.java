package dalosto.engineering.unitconversion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.rest.domain.UnitDAO;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Volume;

@SpringBootTest
public class TestConversorServiceFormat {

    @Autowired
    private ConversorService service;


    @Test
    void shouldBeAbreToFormatUnitDAOForPresentationAndKeepInfoIfTheyAreCorrect() {
        UnitDAO inputDAO; 

        inputDAO = new UnitDAO("12345.67", "MM", "MM");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Length());
        assertEquals(new UnitDAO("12345.67", "MM", "MM"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^2", "MM^2");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Area());
        assertEquals(new UnitDAO("12345.67", "MM^2", "MM^2"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^3", "MM^3");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Volume());
        assertEquals(new UnitDAO("12345.67", "MM^3", "MM^3"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "MM^4", "MM^4");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Inertia());
        assertEquals(new UnitDAO("12345.67", "MM^4", "MM^4"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "N", "N");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Force());
        assertEquals(new UnitDAO("12345.67", "N", "N"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "C", "C");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Temperature());
        assertEquals(new UnitDAO("12345.67", "C", "C"), inputDAO);

        inputDAO = new UnitDAO("12345.67", "S", "S");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Time());
        assertEquals(new UnitDAO("12345.67", "S", "S"), inputDAO);
    }


    @Test
    void shouldBeAbreToFormatUnitDAOAndChangeItsValueForBetterPresentation() {
        UnitDAO inputDAO;

        inputDAO = new UnitDAO("12345,67", " mM ", " MM ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Length());
        assertEquals(new UnitDAO("12345.67", "MM", "MM"), inputDAO);

        inputDAO = new UnitDAO("12345.6700000", " M m _ 2", " M M 2 ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Area());
        assertEquals(new UnitDAO("12345.67", "MM^2", "MM^2"), inputDAO);

        inputDAO = new UnitDAO("12345.,.67", " MM3", "MM3");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Volume());
        assertEquals(new UnitDAO("12345.67", "MM^3", "MM^3"), inputDAO);

        inputDAO = new UnitDAO(" 1 2 3 4 5.67 ", "MM ^ 4", "MM _ 4");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Inertia());
        assertEquals(new UnitDAO("12345.67", "MM^4", "MM^4"), inputDAO);

        inputDAO = new UnitDAO("  12345.67", "  n  ", " n ");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Force());
        assertEquals(new UnitDAO("12345.67", "N", "N"), inputDAO);

        inputDAO = new UnitDAO("12 345.67", "ºC", "ºC");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Temperature());
        assertEquals(new UnitDAO("12345.67", "C", "C"), inputDAO);

        inputDAO = new UnitDAO("12345 . 67", "s", "S");
        service.formatUnitDAOAndConvertToUnit(inputDAO, new Time());
        assertEquals(new UnitDAO("12345.67", "S", "S"), inputDAO);
    }

}