package dace.unitary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Inertia;
import dace.unit.Length;
import dace.unit.Temperature;
import dace.unit.Time;
import dace.unit.Volume;


public class SIequivalenceTest {

    private double tolerance = Math.pow(10, -12);

    
    @Test
    void forceEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Force.N.convertValueToSI(1.0), tolerance);
        assertEquals(1000.0, Force.KN.convertValueToSI(1.0), tolerance);
        assertEquals(1000000.0, Force.MN.convertValueToSI(1.0), tolerance);
        assertEquals(1000000000.0, Force.GN.convertValueToSI(1.0), tolerance);
        assertEquals(1000000000000.0, Force.TN.convertValueToSI(1.0), tolerance);
        assertEquals(4.4482216153, Force.LB.convertValueToSI(1.0), tolerance);
        assertEquals(4.4482216153, Force.POUND.convertValueToSI(1.0), tolerance);
        assertEquals(4448.2216153, Force.KIP.convertValueToSI(1.0), tolerance);
        assertEquals(0.00980665, Force.G.convertValueToSI(1.0), tolerance);
        assertEquals(9.80665, Force.KG.convertValueToSI(1.0), tolerance);
        assertEquals(9806.65, Force.T.convertValueToSI(1.0), tolerance);
        assertEquals(11, Force.values().length);
    }



    @Test
    void lengthEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Length.M.convertValueToSI(1.0), tolerance);
        assertEquals(0.1, Length.DM.convertValueToSI(1.0), tolerance);
        assertEquals(0.01, Length.CM.convertValueToSI(1.0), tolerance);
        assertEquals(0.001, Length.MM.convertValueToSI(1.0), tolerance);
        assertEquals(100.0, Length.HM.convertValueToSI(1.0), tolerance);
        assertEquals(1000.0, Length.KM.convertValueToSI(1.0), tolerance);
        assertEquals(Math.pow(10, -6), Length.UM.convertValueToSI(1.0), tolerance);
        assertEquals(0.0254, Length.IN.convertValueToSI(1.0), tolerance);
        assertEquals(0.3048, Length.FT.convertValueToSI(1.0), tolerance);
        assertEquals(0.9144, Length.YD.convertValueToSI(1.0), tolerance);
        assertEquals(10, Length.values().length);
    }



    @Test
    void areaEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Area.M_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.1*0.1, Area.DM_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.01*0.01, Area.CM_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.001*0.001, Area.MM_2.convertValueToSI(1.0), tolerance);
        assertEquals(100.0*100, Area.HM_2.convertValueToSI(1.0), tolerance);
        assertEquals(1000.0*1000, Area.KM_2.convertValueToSI(1.0), tolerance);
        assertEquals(Math.pow(10, -12), Area.UM_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.0254*0.0254, Area.IN_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.3048*0.3048, Area.FT_2.convertValueToSI(1.0), tolerance);
        assertEquals(0.9144*0.9144, Area.YD_2.convertValueToSI(1.0), tolerance);
        assertEquals(10, Area.values().length);
    }



    @Test
    void volumeEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Volume.M_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.DM_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.CM_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.001*0.001*0.001, Volume.MM_3.convertValueToSI(1.0), tolerance);
        assertEquals(100.0*100*100, Volume.HM_3.convertValueToSI(1.0), tolerance);
        assertEquals(1000.0*1000*1000, Volume.KM_3.convertValueToSI(1.0), tolerance);
        assertEquals(Math.pow(10, -18), Volume.UM_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.0254*0.0254*0.0254, Volume.IN_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.3048*0.3048*0.3048, Volume.FT_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.9144*0.9144*0.9144, Volume.YD_3.convertValueToSI(1.0), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.ML.convertValueToSI(1.0), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.L.convertValueToSI(1.0), tolerance);
        assertEquals(12, Volume.values().length);
    }



    @Test
    void inertiaEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Inertia.M_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.1*0.1*0.1*0.1, Inertia.DM_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.01*0.01*0.01*0.01, Inertia.CM_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.001*0.001*0.001*0.001, Inertia.MM_4.convertValueToSI(1.0), tolerance);
        assertEquals(100.0*100*100*100, Inertia.HM_4.convertValueToSI(1.0), tolerance);
        assertEquals(1000.0*1000*1000*1000, Inertia.KM_4.convertValueToSI(1.0), tolerance);
        assertEquals(Math.pow(10, -24), Inertia.UM_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.0254*0.0254*0.0254*0.0254, Inertia.IN_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.3048*0.3048*0.3048*0.3048, Inertia.FT_4.convertValueToSI(1.0), tolerance);
        assertEquals(0.9144*0.9144*0.9144*0.9144, Inertia.YD_4.convertValueToSI(1.0), tolerance);
        assertEquals(10, Inertia.values().length);
    }




    @Test
    void timeEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Time.S.convertValueToSI(1.0), tolerance);
        assertEquals(1.0/1000, Time.MS.convertValueToSI(1.0), tolerance);
        assertEquals(1.0/1000/1000, Time.US.convertValueToSI(1.0), tolerance);
        assertEquals(60.0, Time.MIN.convertValueToSI(1.0), tolerance);
        assertEquals(3600.0, Time.H.convertValueToSI(1.0), tolerance);
        assertEquals(86400.0, Time.DAY.convertValueToSI(1.0), tolerance);
        assertEquals(604800.0, Time.WEEK.convertValueToSI(1.0), tolerance);
        assertEquals(2628000.0, Time.MONTH.convertValueToSI(1.0), tolerance);
        assertEquals(2592000.0, Time.MONTH_30.convertValueToSI(1.0), tolerance);
        assertEquals(2678400.0, Time.MONTH_31.convertValueToSI(1.0), tolerance);
        assertEquals(31536000.0, Time.YEAR.convertValueToSI(1.0), tolerance);
        assertEquals(11, Time.values().length);
    }




    @Test
    void temperatureEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Temperature.K.convertValueToSI(1), tolerance);
        assertEquals(10.0, Temperature.K.convertValueToSI(10), tolerance);
        assertEquals(100.0, Temperature.K.convertValueToSI(100), tolerance);
        assertEquals(0, Temperature.F.convertValueToSI(-459.67), tolerance);
        assertEquals(10.0, Temperature.F.convertValueToSI(-441.67), tolerance);
        assertEquals(350.0, Temperature.F.convertValueToSI(170.33), tolerance);
        assertEquals(0, Temperature.C.convertValueToSI(-273.15), tolerance);
        assertEquals(150.0, Temperature.C.convertValueToSI(-123.15), tolerance);
        assertEquals(350.0, Temperature.C.convertValueToSI(76.85), tolerance);
        assertEquals(3, Temperature.values().length);
    }

    
}
