package dace.unitary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;
import org.junit.jupiter.api.Test;
import dace.model.Unit;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Inertia;
import dace.unit.Length;
import dace.unit.Temperature;
import dace.unit.Time;
import dace.unit.Volume;


public class ReturnSameSIValueTest {

    private Set<Double> valuesToTest = Set.of(-32767.1, -255.001, -801.551245, -99.0, -1.0, 0.0,
                                              +32767.1, +255.001, +801.551245, +99.0, +1.0);

    void testVariousValues(Unit u) {
        for (Double i : valuesToTest) {
            assertEquals(i, u.convertValueToSI(i));
        }
    }


    @Test
    void forceShouldReturnSameValue() {
        testVariousValues(Force.N);
    }

    @Test
    void lengthShouldReturnSameValue() {
        testVariousValues(Length.M);
    }

    @Test
    void areaShouldReturnSameValue() {
        testVariousValues(Area.M_2);
    }

    @Test
    void volumeShouldReturnSameValue() {
        testVariousValues(Volume.M_3);
    }

    @Test
    void inertiaShouldReturnSameValue() {
        testVariousValues(Inertia.M_4);
    }

    @Test
    void timeShouldReturnSameValue() {
        testVariousValues(Time.S);
    }

    @Test
    void temperatureShouldReturnSameValue() {
        testVariousValues(Temperature.K);
    }
    
}
