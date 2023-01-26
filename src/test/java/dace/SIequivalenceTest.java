package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dace.unit.Force;
import dace.unit.Length;


public class SIequivalenceTest {

    
    @Test
    void allForceEquivalenceInSIShouldBeCorrect() {
        assertEquals(1d, Force.N.getEquivalenceInSI());
        assertEquals(1000d, Force.KN.getEquivalenceInSI());
        assertEquals(1000000d, Force.MN.getEquivalenceInSI());
        assertEquals(1000000000d, Force.GN.getEquivalenceInSI());
        assertEquals(1000000000000d, Force.TN.getEquivalenceInSI());
        assertEquals(4.4482216153, Force.LB.getEquivalenceInSI());
        assertEquals(4.4482216153, Force.POUND.getEquivalenceInSI());
        assertEquals(4448.2216153, Force.KIP.getEquivalenceInSI());
        assertEquals(0.00980665, Force.G.getEquivalenceInSI());
        assertEquals(9.80665, Force.KG.getEquivalenceInSI());
        assertEquals(9806.65, Force.T.getEquivalenceInSI());
        assertEquals(11, Force.values().length);
    }


    @Test
    void allLengthEquivalenceInSIShouldBeCorrect() {
        assertEquals(1.0, Length.M.getEquivalenceInSI());
        assertEquals(0.1, Length.DM.getEquivalenceInSI());
        assertEquals(0.01, Length.CM.getEquivalenceInSI());
        assertEquals(0.001, Length.MM.getEquivalenceInSI());
        assertEquals(100, Length.HM.getEquivalenceInSI());
        assertEquals(1000, Length.KM.getEquivalenceInSI());
        assertEquals(Math.pow(10, -6), Length.UM.getEquivalenceInSI());
        assertEquals(0.0254, Length.IN.getEquivalenceInSI());
        assertEquals(0.3048, Length.FT.getEquivalenceInSI());
        assertEquals(0.9144, Length.YD.getEquivalenceInSI());
        assertEquals(10, Length.values().length);
    }
    
}
