package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.unit.Area;
import dalosto.engineering.unitconversion.unit.Density;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Inertia;
import dalosto.engineering.unitconversion.unit.Length;
import dalosto.engineering.unitconversion.unit.Linear;
import dalosto.engineering.unitconversion.unit.Pressure;
import dalosto.engineering.unitconversion.unit.Speed;
import dalosto.engineering.unitconversion.unit.Time;
import dalosto.engineering.unitconversion.unit.Torque;
import dalosto.engineering.unitconversion.unit.Volume;


@SpringBootTest
public class ServiceToFixStringTest {

    @Autowired
    private MapUnitTypeService mapUnitTypeService;



    @Test
    public void serviceShouldMakeUpperCaseOfInputs() {
        assertEquals(Length.Types.MM, mapUnitTypeService.getUnitTypeFromString("mM", new Length()));
    }


    @Test
    public void serviceShouldFixStringContainingSpaces() {
        assertEquals(Length.Types.M, mapUnitTypeService.getUnitTypeFromString("  M  ", new Length()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString(" M  _  2 ", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString(" M _ 2 ", new Area()));
    }


    @Test
    public void serviceShouldFixStringContaingEspecialCharacter() {
        assertEquals(Length.Types.M, mapUnitTypeService.getUnitTypeFromString("M!@#!", new Length()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("!@#$%¨&&()M_!@#!@)2!@#$%¨&()", new Area()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("!@#M~^!@#___2!", new Area()));
    }


    @Test
    public void serviceShouldFixStringToMapExponentialSymbol() {
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
    public void serviceShouldFixStringContainingNumbersSuperScriptAndSubscript() {
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M²", new Area()));
        assertEquals(Volume.Types.M3, mapUnitTypeService.getUnitTypeFromString("M³", new Volume()));
        assertEquals(Inertia.Types.M4, mapUnitTypeService.getUnitTypeFromString("M⁴", new Inertia()));
        assertEquals(Area.Types.M2, mapUnitTypeService.getUnitTypeFromString("M₂", new Area()));
        assertEquals(Volume.Types.M3, mapUnitTypeService.getUnitTypeFromString("M₃", new Volume()));
        assertEquals(Inertia.Types.M4, mapUnitTypeService.getUnitTypeFromString("M₄", new Inertia()));
    }


    @Test
    public void serviceShouldFixStringForTorque() {
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN.M", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN . M", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KNxM", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN*M", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN×M", new Torque()));
    }
    
    
    @Test
    public void serviceShouldFixStringForLinear() {
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN/M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN / M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN\\M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN÷M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), mapUnitTypeService.getUnitTypeFromString("KN ÷ M", new Linear()));
    }
    
    
    @Test
    public void serviceShouldFixStringForPressure() {
        assertEquals(Pressure.factory(Force.Types.KN, Area.Types.M2), mapUnitTypeService.getUnitTypeFromString("KN/M2", new Pressure()));
        assertEquals(Pressure.factory(Force.Types.KN, Area.Types.M2), mapUnitTypeService.getUnitTypeFromString("KN / M2", new Pressure()));
        assertEquals(Pressure.factory(Force.Types.KN, Area.Types.M2), mapUnitTypeService.getUnitTypeFromString("KN\\M2", new Pressure()));
        assertEquals(Pressure.factory(Force.Types.KN, Area.Types.M2), mapUnitTypeService.getUnitTypeFromString("KN÷M2", new Pressure()));
        assertEquals(Pressure.factory(Force.Types.KN, Area.Types.M2), mapUnitTypeService.getUnitTypeFromString("KN ÷ M2", new Pressure()));
    }
    
    
    @Test
    public void serviceShouldFixStringForDensity() {
        assertEquals(Density.factory(Force.Types.KN, Volume.Types.M3), mapUnitTypeService.getUnitTypeFromString("KN/M3", new Density()));
        assertEquals(Density.factory(Force.Types.KN, Volume.Types.M3), mapUnitTypeService.getUnitTypeFromString("KN / M3", new Density()));
        assertEquals(Density.factory(Force.Types.KN, Volume.Types.M3), mapUnitTypeService.getUnitTypeFromString("KN\\M3", new Density()));
        assertEquals(Density.factory(Force.Types.KN, Volume.Types.M3), mapUnitTypeService.getUnitTypeFromString("KN÷M3", new Density()));
        assertEquals(Density.factory(Force.Types.KN, Volume.Types.M3), mapUnitTypeService.getUnitTypeFromString("KN ÷ M3", new Density()));
    }
    
    
    @Test
    public void serviceShouldFixStringForSpeed() {
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), mapUnitTypeService.getUnitTypeFromString("M/S", new Speed()));
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), mapUnitTypeService.getUnitTypeFromString("M / S", new Speed()));
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), mapUnitTypeService.getUnitTypeFromString("M /S", new Speed()));
        assertEquals(Speed.factory(Length.Types.M, Time.Types.S), mapUnitTypeService.getUnitTypeFromString("M/ S", new Speed()));
    }

}
