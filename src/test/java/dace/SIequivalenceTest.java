package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Length;
import dace.unit.Volume;
import dace.unit.Time;


public class SIequivalenceTest {

    private double tolerance = Math.pow(10, -12);

    
    @Test
    void forceEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Force.N.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000.0, Force.KN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000000.0, Force.MN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000000000.0, Force.GN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000000000000.0, Force.TN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(4.4482216153, Force.LB.getEquivalenceFactorInSI(), tolerance);
        assertEquals(4.4482216153, Force.POUND.getEquivalenceFactorInSI(), tolerance);
        assertEquals(4448.2216153, Force.KIP.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.00980665, Force.G.getEquivalenceFactorInSI(), tolerance);
        assertEquals(9.80665, Force.KG.getEquivalenceFactorInSI(), tolerance);
        assertEquals(9806.65, Force.T.getEquivalenceFactorInSI(), tolerance);
        assertEquals(11, Force.values().length);
    }



    @Test
    void lengthEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Length.M.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.1, Length.DM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.01, Length.CM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.001, Length.MM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(100.0, Length.HM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000.0, Length.KM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1.0/(1000.0*1000), Length.UM.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.0254, Length.IN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.3048, Length.FT.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.9144, Length.YD.getEquivalenceFactorInSI(), tolerance);
        assertEquals(10, Length.values().length);
    }



    @Test
    void areaEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Area.M_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.1*0.1, Area.DM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.01*0.01, Area.CM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.001*0.001, Area.MM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(100.0*100, Area.HM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000.0*1000, Area.KM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1.0/(1000*1000)*1.0/(1000*1000), Area.UM_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.0254*0.0254, Area.IN_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.3048*0.3048, Area.FT_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.9144*0.9144, Area.YD_2.getEquivalenceFactorInSI(), tolerance);
        assertEquals(10, Area.values().length);
    }



    @Test
    void volumeEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Volume.M_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.DM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.CM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.001*0.001*0.001, Volume.MM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(100.0*100*100, Volume.HM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1000.0*1000*1000, Volume.KM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1.0/(1000*1000)*1.0/(1000*1000)*1.0/(1000*1000), Volume.UM_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.0254*0.0254*0.0254, Volume.IN_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.3048*0.3048*0.3048, Volume.FT_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.9144*0.9144*0.9144, Volume.YD_3.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.ML.getEquivalenceFactorInSI(), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.L.getEquivalenceFactorInSI(), tolerance);
        assertEquals(12, Volume.values().length);
    }




    @Test
    void timeEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Time.S.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1.0/1000, Time.MS.getEquivalenceFactorInSI(), tolerance);
        assertEquals(1.0/1000/1000, Time.US.getEquivalenceFactorInSI(), tolerance);
        assertEquals(60.0, Time.MIN.getEquivalenceFactorInSI(), tolerance);
        assertEquals(3600.0, Time.H.getEquivalenceFactorInSI(), tolerance);
        assertEquals(86400.0, Time.DAY.getEquivalenceFactorInSI(), tolerance);
        assertEquals(604800.0, Time.WEEK.getEquivalenceFactorInSI(), tolerance);
        assertEquals(2628000.0, Time.MONTH.getEquivalenceFactorInSI(), tolerance);
        assertEquals(2592000.0, Time.MONTH_30.getEquivalenceFactorInSI(), tolerance);
        assertEquals(2678400.0, Time.MONTH_31.getEquivalenceFactorInSI(), tolerance);
        assertEquals(31536000.0, Time.YEAR.getEquivalenceFactorInSI(), tolerance);
    }

    
}
