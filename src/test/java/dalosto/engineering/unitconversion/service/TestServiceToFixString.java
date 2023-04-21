package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
public class TestServiceToFixString {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;



    @Test
    void serviceShouldMakeUpperCaseOfInputs() {
        assertEquals(Length.Types.MM, mapUnitTypeService.getUnitTypeFromString("mM", new Length()));
    }


    @Test
    void serviceShouldFixStringContainingSpaces() {
        assertEquals(Length.Types.M, mapUnitTypeService.getUnitTypeFromString("  M  ", new Length()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString(" M  _  2 ", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString(" M _ 2 ", new Area()));
    }


    @Test
    void serviceShouldFixStringContaingEspecialCharacter() {
        assertEquals(Length.Types.M, mapUnitTypeService.getUnitTypeFromString("M!@#!", new Length()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("!@#$%¨&&*()M_!@*#*!@)2!@#$%¨&*()", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("!@#M~^!@#___2!", new Area()));
    }


    @Test
    void serviceShouldFixStringToMapExponentialSymbol() {
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M^2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M^^2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M^^^2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M~2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M~~2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M-2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M--2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M_2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M__2", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M~^2", new Area()));
    }


    @Test
    void serviceShouldFixStringContainingNumbersSuperScriptAndSubscript() {
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M²", new Area()));
        assertEquals(Volume.Types.M3, mapUnitTypeService.getUnitTypeFromString("M³", new Volume()));
        assertEquals(Inertia.Types.M4, mapUnitTypeService.getUnitTypeFromString("M⁴", new Inertia()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M₂", new Area()));
        assertEquals(Volume.Types.M3, mapUnitTypeService.getUnitTypeFromString("M₃", new Volume()));
        assertEquals(Inertia.Types.M4, mapUnitTypeService.getUnitTypeFromString("M₄", new Inertia()));
    }

}
