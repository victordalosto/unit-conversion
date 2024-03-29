package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.unit.Area;
import dalosto.engineering.unitconversion.unit.Force;
import dalosto.engineering.unitconversion.unit.Inertia;
import dalosto.engineering.unitconversion.unit.Length;
import dalosto.engineering.unitconversion.unit.Linear;
import dalosto.engineering.unitconversion.unit.Temperature;
import dalosto.engineering.unitconversion.unit.Time;
import dalosto.engineering.unitconversion.unit.Torque;
import dalosto.engineering.unitconversion.unit.Volume;


@SpringBootTest
public class MapUnityTest {

    @Autowired
    private MapUnitTypeService service;


    @Test
    public void serviceShouldHandleNullString() {
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString(null, null));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("Text", null));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString(null, new Length()));
    }


    @Test
    public void shouldThrowExceptionWhenInvalidParamIsGiven() {
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("", new Area()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M", new Area()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M_2", new Length()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M_3", new Length()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("invalid param", new Area()));
    }


    @Test
    public void serviceShouldBeAbleToMapUnitsEvenWithNoiseInString() {
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("mm", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("MM", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("mM", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString(" m M ", new Length()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString(" M ^ 2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("m ^2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString(" m _^_ 2", new Area()));
        assertEquals(Temperature.Types.C, service.getUnitTypeFromString("  ºC ", new Temperature()));
    }

    @Test
    public void serviceShouldBeAbleToConvertWithDifferentFormats() {
        assertEquals(Length.Types.M, service.getUnitTypeFromString("m", new Length()));
        assertEquals(Length.Types.M, service.getUnitTypeFromString("M", new Length()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M_2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M^2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M ^ 2", new Area()));
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M²", new Area()));
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M3", new Volume()));
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M_3", new Volume()));
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M^3", new Volume()));
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M ^ 3", new Volume()));
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M³", new Volume()));
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M4", new Inertia()));
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M_4", new Inertia()));
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M^4", new Inertia()));
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M ^ 4", new Inertia()));
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M⁴", new Inertia()));
    }


    @Test
    public void serviceShouldBeAbleToMapLenghtUnits() {
        assertEquals(Length.Types.M, service.getUnitTypeFromString("M", new Length()));
        assertEquals(Length.Types.DM, service.getUnitTypeFromString("DM", new Length()));
        assertEquals(Length.Types.CM, service.getUnitTypeFromString("CM", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("MM", new Length()));
        assertEquals(Length.Types.HM, service.getUnitTypeFromString("HM", new Length()));
        assertEquals(Length.Types.KM, service.getUnitTypeFromString("KM", new Length()));
        assertEquals(Length.Types.UM, service.getUnitTypeFromString("UM", new Length()));
        assertEquals(Length.Types.IN, service.getUnitTypeFromString("IN", new Length()));
        assertEquals(Length.Types.FT, service.getUnitTypeFromString("FT", new Length()));
        assertEquals(Length.Types.YD, service.getUnitTypeFromString("YD", new Length()));
    }


    @Test
    public void serviceShouldBeAbleToMapAreaUnits() {
        assertEquals(Area.Types.M2, service.getUnitTypeFromString("M2", new Area()));
        assertEquals(Area.Types.DM2, service.getUnitTypeFromString("DM2", new Area()));
        assertEquals(Area.Types.CM2, service.getUnitTypeFromString("CM2", new Area()));
        assertEquals(Area.Types.MM2, service.getUnitTypeFromString("MM2", new Area()));
        assertEquals(Area.Types.HM2, service.getUnitTypeFromString("HM2", new Area()));
        assertEquals(Area.Types.KM2, service.getUnitTypeFromString("KM2", new Area()));
        assertEquals(Area.Types.UM2, service.getUnitTypeFromString("UM2", new Area()));
        assertEquals(Area.Types.IN2, service.getUnitTypeFromString("IN2", new Area()));
        assertEquals(Area.Types.FT2, service.getUnitTypeFromString("FT2", new Area()));
        assertEquals(Area.Types.YD2, service.getUnitTypeFromString("YD2", new Area()));
    }


    @Test
    public void serviceShouldBeAbleToMapVolumeUnits() {
        assertEquals(Volume.Types.M3, service.getUnitTypeFromString("M3", new Volume()));
        assertEquals(Volume.Types.DM3, service.getUnitTypeFromString("DM3", new Volume()));
        assertEquals(Volume.Types.CM3, service.getUnitTypeFromString("CM3", new Volume()));
        assertEquals(Volume.Types.MM3, service.getUnitTypeFromString("MM3", new Volume()));
        assertEquals(Volume.Types.HM3, service.getUnitTypeFromString("HM3", new Volume()));
        assertEquals(Volume.Types.KM3, service.getUnitTypeFromString("KM3", new Volume()));
        assertEquals(Volume.Types.UM3, service.getUnitTypeFromString("UM3", new Volume()));
        assertEquals(Volume.Types.IN3, service.getUnitTypeFromString("IN3", new Volume()));
        assertEquals(Volume.Types.FT3, service.getUnitTypeFromString("FT3", new Volume()));
        assertEquals(Volume.Types.YD3, service.getUnitTypeFromString("YD3", new Volume()));
        assertEquals(Volume.Types.ML, service.getUnitTypeFromString("ML", new Volume()));
        assertEquals(Volume.Types.L, service.getUnitTypeFromString("L", new Volume()));
    }


    @Test
    public void serviceShouldBeAbleToMapInertiaUnits() {
        assertEquals(Inertia.Types.M4, service.getUnitTypeFromString("M4", new Inertia()));
        assertEquals(Inertia.Types.DM4, service.getUnitTypeFromString("DM4", new Inertia()));
        assertEquals(Inertia.Types.CM4, service.getUnitTypeFromString("CM4", new Inertia()));
        assertEquals(Inertia.Types.MM4, service.getUnitTypeFromString("MM4", new Inertia()));
        assertEquals(Inertia.Types.HM4, service.getUnitTypeFromString("HM4", new Inertia()));
        assertEquals(Inertia.Types.KM4, service.getUnitTypeFromString("KM4", new Inertia()));
        assertEquals(Inertia.Types.UM4, service.getUnitTypeFromString("UM4", new Inertia()));
        assertEquals(Inertia.Types.IN4, service.getUnitTypeFromString("IN4", new Inertia()));
        assertEquals(Inertia.Types.FT4, service.getUnitTypeFromString("FT4", new Inertia()));
        assertEquals(Inertia.Types.YD4, service.getUnitTypeFromString("YD4", new Inertia()));
    }


    @Test
    public void serviceShouldBeAbleToMapForceUnits() {
        assertEquals(Force.Types.N, service.getUnitTypeFromString("N", new Force()));
        assertEquals(Force.Types.KN, service.getUnitTypeFromString("KN", new Force()));
        assertEquals(Force.Types.MN, service.getUnitTypeFromString("MN", new Force()));
        assertEquals(Force.Types.GN, service.getUnitTypeFromString("GN", new Force()));
        assertEquals(Force.Types.TN, service.getUnitTypeFromString("TN", new Force()));
        assertEquals(Force.Types.LB, service.getUnitTypeFromString("LB", new Force()));
        assertEquals(Force.Types.POUND, service.getUnitTypeFromString("POUND", new Force()));
        assertEquals(Force.Types.KIP, service.getUnitTypeFromString("KIP", new Force()));
        assertEquals(Force.Types.GF, service.getUnitTypeFromString("GF", new Force()));
        assertEquals(Force.Types.KGF, service.getUnitTypeFromString("KGF", new Force()));
        assertEquals(Force.Types.T, service.getUnitTypeFromString("T", new Force()));
    }


    @Test
    public void serviceShouldBeAbleToMapTemperatureUnits() {
        assertEquals(Temperature.Types.K, service.getUnitTypeFromString("K", new Temperature()));
        assertEquals(Temperature.Types.C, service.getUnitTypeFromString("C", new Temperature()));
        assertEquals(Temperature.Types.F, service.getUnitTypeFromString("F", new Temperature()));
        assertEquals(Temperature.Types.R, service.getUnitTypeFromString("R", new Temperature()));
    }


    @Test
    public void serviceShouldBeAbleToMapTimeUnits() {
        assertEquals(Time.Types.S, service.getUnitTypeFromString("S", new Time()));
        assertEquals(Time.Types.MS, service.getUnitTypeFromString("MS", new Time()));
        assertEquals(Time.Types.US, service.getUnitTypeFromString("US", new Time()));
        assertEquals(Time.Types.MIN, service.getUnitTypeFromString("MIN", new Time()));
        assertEquals(Time.Types.H, service.getUnitTypeFromString("H", new Time()));
        assertEquals(Time.Types.DAY, service.getUnitTypeFromString("DAY", new Time()));
        assertEquals(Time.Types.WEEK, service.getUnitTypeFromString("WEEK", new Time()));
        assertEquals(Time.Types.MONTH, service.getUnitTypeFromString("MONTH", new Time()));
        assertEquals(Time.Types.MONTH30, service.getUnitTypeFromString("MONTH_30", new Time()));
        assertEquals(Time.Types.MONTH31, service.getUnitTypeFromString("MONTH_31", new Time()));
        assertEquals(Time.Types.YEAR, service.getUnitTypeFromString("YEAR", new Time()));
    }


    @Test
    public void serviceShouldBeAbleToMapTorqueUnits() {
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.M), service.getUnitTypeFromString("KGF.M", new Torque()));
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.CM), service.getUnitTypeFromString("KGF.CM", new Torque()));
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.MM), service.getUnitTypeFromString("KGF.MM", new Torque()));
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.IN), service.getUnitTypeFromString("KGF.IN", new Torque()));
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.FT), service.getUnitTypeFromString("KGF.FT", new Torque()));
        assertEquals(Torque.factory(Force.Types.KGF, Length.Types.YD), service.getUnitTypeFromString("KGF.YD", new Torque()));
        assertEquals(Torque.factory(Force.Types.LB, Length.Types.IN), service.getUnitTypeFromString("LB.IN", new Torque()));
        assertEquals(Torque.factory(Force.Types.LB, Length.Types.FT), service.getUnitTypeFromString("LB.FT", new Torque()));
        assertEquals(Torque.factory(Force.Types.LB, Length.Types.YD), service.getUnitTypeFromString("LB.YD", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.M), service.getUnitTypeFromString("N.M", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.CM), service.getUnitTypeFromString("N.CM", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.MM), service.getUnitTypeFromString("N.MM", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.IN), service.getUnitTypeFromString("N.IN", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.FT), service.getUnitTypeFromString("N.FT", new Torque()));
        assertEquals(Torque.factory(Force.Types.N, Length.Types.YD), service.getUnitTypeFromString("N.YD", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.M), service.getUnitTypeFromString("KN.M", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.CM), service.getUnitTypeFromString("KN.CM", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.MM), service.getUnitTypeFromString("KN.MM", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.IN), service.getUnitTypeFromString("KN.IN", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.FT), service.getUnitTypeFromString("KN.FT", new Torque()));
        assertEquals(Torque.factory(Force.Types.KN, Length.Types.YD), service.getUnitTypeFromString("KN.YD", new Torque()));
        assertEquals(Torque.factory(Force.Types.KIP, Length.Types.IN), service.getUnitTypeFromString("KIP.IN", new Torque()));
        assertEquals(Torque.factory(Force.Types.KIP, Length.Types.FT), service.getUnitTypeFromString("KIP.FT", new Torque()));
        assertEquals(Torque.factory(Force.Types.KIP, Length.Types.YD), service.getUnitTypeFromString("KIP.YD", new Torque()));
    }


    @Test
    public void serviceShoudlBeAbleToMapLinearUnits() {
        assertEquals(Linear.factory(Force.Types.N, Length.Types.M), service.getUnitTypeFromString("N/M", new Linear()));
        assertEquals(Linear.factory(Force.Types.N, Length.Types.CM), service.getUnitTypeFromString("N/CM", new Linear()));
        assertEquals(Linear.factory(Force.Types.N, Length.Types.MM), service.getUnitTypeFromString("N/MM", new Linear()));
        assertEquals(Linear.factory(Force.Types.N, Length.Types.IN), service.getUnitTypeFromString("N/IN", new Linear()));
        assertEquals(Linear.factory(Force.Types.N, Length.Types.FT), service.getUnitTypeFromString("N/FT", new Linear()));
        assertEquals(Linear.factory(Force.Types.N, Length.Types.YD), service.getUnitTypeFromString("N/YD", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.M), service.getUnitTypeFromString("KN/M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.CM), service.getUnitTypeFromString("KN/CM", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.MM), service.getUnitTypeFromString("KN/MM", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.IN), service.getUnitTypeFromString("KN/IN", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.FT), service.getUnitTypeFromString("KN/FT", new Linear()));
        assertEquals(Linear.factory(Force.Types.KN, Length.Types.YD), service.getUnitTypeFromString("KN/YD", new Linear()));
        assertEquals(Linear.factory(Force.Types.LB, Length.Types.IN), service.getUnitTypeFromString("LB/IN", new Linear()));
        assertEquals(Linear.factory(Force.Types.LB, Length.Types.FT), service.getUnitTypeFromString("LB/FT", new Linear()));
        assertEquals(Linear.factory(Force.Types.LB, Length.Types.YD), service.getUnitTypeFromString("LB/YD", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.M), service.getUnitTypeFromString("KGF/M", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.CM), service.getUnitTypeFromString("KGF/CM", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.MM), service.getUnitTypeFromString("KGF/MM", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.IN), service.getUnitTypeFromString("KGF/IN", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.FT), service.getUnitTypeFromString("KGF/FT", new Linear()));
        assertEquals(Linear.factory(Force.Types.KGF, Length.Types.YD), service.getUnitTypeFromString("KGF/YD", new Linear()));
    }

}
