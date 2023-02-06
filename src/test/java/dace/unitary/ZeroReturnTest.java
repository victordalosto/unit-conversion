package dace.unitary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import dace.model.Unit;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Inertia;
import dace.unit.Length;
import dace.unit.Temperature;
import dace.unit.Time;
import dace.unit.Volume;


public class ZeroReturnTest {

    void assertsZero(Unit [] units) {
        for (Unit u : units)
            assertEquals(0, u.convertValueToSI(0));
    }


    @Test
    void forceShouldReturnZero() {
        assertsZero(Force.values());
    }

    @Test
    void lengthShouldReturnZero() {
        assertsZero(Length.values());
    }

    @Test
    void areaShouldReturnZero() {
        assertsZero(Area.values());
    }

    @Test
    void volumeShouldReturnZero() {
        assertsZero(Volume.values());
    }

    @Test
    void inertiaShouldReturnZero() {
        assertsZero(Inertia.values());
    }

    @Test
    void timeShouldReturnZero() {
        assertsZero(Time.values());
    }

    @Test
    void temperatureShouldReturnZero() {
        assertEquals(0, Temperature.K.convertValueToSI(0));
        assertEquals(0, Temperature.F.convertValueToSI(-459.67));
        assertEquals(0, Temperature.C.convertValueToSI(-273.15));
    }
    
}
