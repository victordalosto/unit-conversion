package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Length;
import dace.unit.Volume;


public class SIequivalenceTest {

    private double tolerance = Math.pow(10, -12);

    
    @Test
    void allForceEquivalenceInSIShouldBeCorrect() {
        assertEquals(1d, Force.N.getEquivalenceInSI(), tolerance);
        assertEquals(1000d, Force.KN.getEquivalenceInSI(), tolerance);
        assertEquals(1000000d, Force.MN.getEquivalenceInSI(), tolerance);
        assertEquals(1000000000d, Force.GN.getEquivalenceInSI(), tolerance);
        assertEquals(1000000000000d, Force.TN.getEquivalenceInSI(), tolerance);
        assertEquals(4.4482216153, Force.LB.getEquivalenceInSI(), tolerance);
        assertEquals(4.4482216153, Force.POUND.getEquivalenceInSI(), tolerance);
        assertEquals(4448.2216153, Force.KIP.getEquivalenceInSI(), tolerance);
        assertEquals(0.00980665, Force.G.getEquivalenceInSI(), tolerance);
        assertEquals(9.80665, Force.KG.getEquivalenceInSI(), tolerance);
        assertEquals(9806.65, Force.T.getEquivalenceInSI(), tolerance);
        assertEquals(11, Force.values().length);
    }



    @Test
    void allLengthEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Length.M.getEquivalenceInSI(), tolerance);
        assertEquals(0.1, Length.DM.getEquivalenceInSI(), tolerance);
        assertEquals(0.01, Length.CM.getEquivalenceInSI(), tolerance);
        assertEquals(0.001, Length.MM.getEquivalenceInSI(), tolerance);
        assertEquals(100, Length.HM.getEquivalenceInSI(), tolerance);
        assertEquals(1000, Length.KM.getEquivalenceInSI(), tolerance);
        assertEquals(1.0/(1000*1000), Length.UM.getEquivalenceInSI(), tolerance);
        assertEquals(0.0254, Length.IN.getEquivalenceInSI(), tolerance);
        assertEquals(0.3048, Length.FT.getEquivalenceInSI(), tolerance);
        assertEquals(0.9144, Length.YD.getEquivalenceInSI(), tolerance);
        assertEquals(10, Length.values().length);
    }



    @Test
    void allAreaEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Area.M_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.1*0.1, Area.DM_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.01*0.01, Area.CM_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.001*0.001, Area.MM_2.getEquivalenceInSI(), tolerance);
        assertEquals(100*100, Area.HM_2.getEquivalenceInSI(), tolerance);
        assertEquals(1000*1000, Area.KM_2.getEquivalenceInSI(), tolerance);
        assertEquals(1.0/(1000*1000)*1.0/(1000*1000), Area.UM_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.0254*0.0254, Area.IN_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.3048*0.3048, Area.FT_2.getEquivalenceInSI(), tolerance);
        assertEquals(0.9144*0.9144, Area.YD_2.getEquivalenceInSI(), tolerance);
        assertEquals(10, Area.values().length);
    }



    @Test
    void allVolumeEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Volume.M_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.DM_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.CM_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.001*0.001*0.001, Volume.MM_3.getEquivalenceInSI(), tolerance);
        assertEquals(100*100*100, Volume.HM_3.getEquivalenceInSI(), tolerance);
        assertEquals(1000*1000*1000, Volume.KM_3.getEquivalenceInSI(), tolerance);
        assertEquals(1.0/(1000*1000)*1.0/(1000*1000)*1.0/(1000*1000), Volume.UM_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.0254*0.0254*0.0254, Volume.IN_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.3048*0.3048*0.3048, Volume.FT_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.9144*0.9144*0.9144, Volume.YD_3.getEquivalenceInSI(), tolerance);
        assertEquals(0.01*0.01*0.01, Volume.ML.getEquivalenceInSI(), tolerance);
        assertEquals(0.1*0.1*0.1, Volume.L.getEquivalenceInSI(), tolerance);
        assertEquals(12, Volume.values().length);
    }

    
}
