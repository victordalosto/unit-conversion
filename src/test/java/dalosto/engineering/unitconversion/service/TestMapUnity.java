package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.exception.ParameterException;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
public class TestMapUnity {

    @Autowired
    private MapUnitTypeService service;


    @Test
    void serviceShouldHandleNullString() {
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString(null, null));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("Text", null));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString(null, new Length()));
    }


    @Test
    void shouldThrowExceptionWhenInvalidParamIsGiven() {
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("", new Area()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M", new Area()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M_2", new Length()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("M_3", new Length()));
        assertThrows(ParameterException.class, () -> service.getUnitTypeFromString("invalid param", new Area()));
    }


    @Test
    void serviceShouldBeAbleToMapUnitsEvenWithNoiseInString() {
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("mm", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("MM", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString("mM", new Length()));
        assertEquals(Length.Types.MM, service.getUnitTypeFromString(" m M ", new Length()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString(" M ^ 2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("m ^2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString(" m _^_ 2", new Area()));
        assertEquals(Temperature.Types.C, service.getUnitTypeFromString("  ºC ", new Temperature()));
    }

    @Test
    void serviceShouldBeAbleToConvertWithDifferentFormats() {
        assertEquals(Length.Types.M, service.getUnitTypeFromString("m", new Length()));
        assertEquals(Length.Types.M, service.getUnitTypeFromString("M", new Length()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M_2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M^2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M ^ 2", new Area()));
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M²", new Area()));
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M3", new Volume()));
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M_3", new Volume()));
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M^3", new Volume()));
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M ^ 3", new Volume()));
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M³", new Volume()));
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M4", new Inertia()));
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M_4", new Inertia()));
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M^4", new Inertia()));
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M ^ 4", new Inertia()));
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M⁴", new Inertia()));
    }


    @Test
    void serviceShouldBeAbleToMapLenghtUnits() {
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
    void serviceShouldBeAbleToMapAreaUnits() {
        assertEquals(Area.Types.M_2, service.getUnitTypeFromString("M2", new Area()));
        assertEquals(Area.Types.DM_2, service.getUnitTypeFromString("DM2", new Area()));
        assertEquals(Area.Types.CM_2, service.getUnitTypeFromString("CM2", new Area()));
        assertEquals(Area.Types.MM_2, service.getUnitTypeFromString("MM2", new Area()));
        assertEquals(Area.Types.HM_2, service.getUnitTypeFromString("HM2", new Area()));
        assertEquals(Area.Types.KM_2, service.getUnitTypeFromString("KM2", new Area()));
        assertEquals(Area.Types.UM_2, service.getUnitTypeFromString("UM2", new Area()));
        assertEquals(Area.Types.IN_2, service.getUnitTypeFromString("IN2", new Area()));
        assertEquals(Area.Types.FT_2, service.getUnitTypeFromString("FT2", new Area()));
        assertEquals(Area.Types.YD_2, service.getUnitTypeFromString("YD2", new Area()));
    }


    @Test
    void serviceShouldBeAbleToMapVolumeUnits() {
        assertEquals(Volume.Types.M_3, service.getUnitTypeFromString("M3", new Volume()));
        assertEquals(Volume.Types.DM_3, service.getUnitTypeFromString("DM3", new Volume()));
        assertEquals(Volume.Types.CM_3, service.getUnitTypeFromString("CM3", new Volume()));
        assertEquals(Volume.Types.MM_3, service.getUnitTypeFromString("MM3", new Volume()));
        assertEquals(Volume.Types.HM_3, service.getUnitTypeFromString("HM3", new Volume()));
        assertEquals(Volume.Types.KM_3, service.getUnitTypeFromString("KM3", new Volume()));
        assertEquals(Volume.Types.UM_3, service.getUnitTypeFromString("UM3", new Volume()));
        assertEquals(Volume.Types.IN_3, service.getUnitTypeFromString("IN3", new Volume()));
        assertEquals(Volume.Types.FT_3, service.getUnitTypeFromString("FT3", new Volume()));
        assertEquals(Volume.Types.YD_3, service.getUnitTypeFromString("YD3", new Volume()));
        assertEquals(Volume.Types.ML, service.getUnitTypeFromString("ML", new Volume()));
        assertEquals(Volume.Types.L, service.getUnitTypeFromString("L", new Volume()));
    }


    @Test
    void serviceShouldBeAbleToMapInertiaUnits() {
        assertEquals(Inertia.Types.M_4, service.getUnitTypeFromString("M4", new Inertia()));
        assertEquals(Inertia.Types.DM_4, service.getUnitTypeFromString("DM4", new Inertia()));
        assertEquals(Inertia.Types.CM_4, service.getUnitTypeFromString("CM4", new Inertia()));
        assertEquals(Inertia.Types.MM_4, service.getUnitTypeFromString("MM4", new Inertia()));
        assertEquals(Inertia.Types.HM_4, service.getUnitTypeFromString("HM4", new Inertia()));
        assertEquals(Inertia.Types.KM_4, service.getUnitTypeFromString("KM4", new Inertia()));
        assertEquals(Inertia.Types.UM_4, service.getUnitTypeFromString("UM4", new Inertia()));
        assertEquals(Inertia.Types.IN_4, service.getUnitTypeFromString("IN4", new Inertia()));
        assertEquals(Inertia.Types.FT_4, service.getUnitTypeFromString("FT4", new Inertia()));
        assertEquals(Inertia.Types.YD_4, service.getUnitTypeFromString("YD4", new Inertia()));
    }


    @Test
    void serviceShouldBeAbleToMapForceUnits() {
        assertEquals(Force.Types.N, service.getUnitTypeFromString("N", new Force()));
        assertEquals(Force.Types.KN, service.getUnitTypeFromString("KN", new Force()));
        assertEquals(Force.Types.MN, service.getUnitTypeFromString("MN", new Force()));
        assertEquals(Force.Types.GN, service.getUnitTypeFromString("GN", new Force()));
        assertEquals(Force.Types.TN, service.getUnitTypeFromString("TN", new Force()));
        assertEquals(Force.Types.LB, service.getUnitTypeFromString("LB", new Force()));
        assertEquals(Force.Types.POUND, service.getUnitTypeFromString("POUND", new Force()));
        assertEquals(Force.Types.KIP, service.getUnitTypeFromString("KIP", new Force()));
        assertEquals(Force.Types.G, service.getUnitTypeFromString("G", new Force()));
        assertEquals(Force.Types.KG, service.getUnitTypeFromString("KG", new Force()));
        assertEquals(Force.Types.T, service.getUnitTypeFromString("T", new Force()));
    }


    @Test
    void serviceShouldBeAbleToMapTemperatureUnits() {
        assertEquals(Temperature.Types.K, service.getUnitTypeFromString("K", new Temperature()));
        assertEquals(Temperature.Types.C, service.getUnitTypeFromString("C", new Temperature()));
        assertEquals(Temperature.Types.F, service.getUnitTypeFromString("F", new Temperature()));
        assertEquals(Temperature.Types.R, service.getUnitTypeFromString("R", new Temperature()));
    }


    @Test
    void serviceShouldBeAbleToMapTimeUnits() {
        assertEquals(Time.Types.S, service.getUnitTypeFromString("S", new Time()));
        assertEquals(Time.Types.MS, service.getUnitTypeFromString("MS", new Time()));
        assertEquals(Time.Types.US, service.getUnitTypeFromString("US", new Time()));
        assertEquals(Time.Types.MIN, service.getUnitTypeFromString("MIN", new Time()));
        assertEquals(Time.Types.H, service.getUnitTypeFromString("H", new Time()));
        assertEquals(Time.Types.DAY, service.getUnitTypeFromString("DAY", new Time()));
        assertEquals(Time.Types.WEEK, service.getUnitTypeFromString("WEEK", new Time()));
        assertEquals(Time.Types.MONTH, service.getUnitTypeFromString("MONTH", new Time()));
        assertEquals(Time.Types.MONTH_30, service.getUnitTypeFromString("MONTH_30", new Time()));
        assertEquals(Time.Types.MONTH_31, service.getUnitTypeFromString("MONTH_31", new Time()));
        assertEquals(Time.Types.YEAR, service.getUnitTypeFromString("YEAR", new Time()));
    }

}
