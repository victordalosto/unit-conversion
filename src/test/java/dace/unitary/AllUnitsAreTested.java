package dace.unitary;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import dace.TestService;
import dace.model.Unit;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Inertia;
import dace.unit.Length;
import dace.unit.Temperature;
import dace.unit.Time;
import dace.unit.Volume;


public class AllUnitsAreTested {

    void returnAllUnits(Set<String> mock, Unit [] units) {
        assertEquals(mock.size(), units.length);
        assertTrue(mock.containsAll(Stream.of(units)
                                          .map(Unit::toString)
                                          .collect(Collectors.toList())));
    }
    
    @Test
    void forceIsReturningAllUnits() {
        returnAllUnits(TestService.mockForce, Force.values());
    }

    @Test
    void lengthIsReturningAllUnits() {
        returnAllUnits(TestService.mockLength, Length.values());
    }

    @Test
    void areaIsReturningAllUnits() {
        returnAllUnits(TestService.mockArea, Area.values());
    }

    @Test
    void volumeIsReturningAllUnits() {
        returnAllUnits(TestService.mockVolume, Volume.values());
    }

    @Test
    void inertiaBeAbleToReturnAllInertiaUnits() {
        returnAllUnits(TestService.mockInertia, Inertia.values());
    }

    @Test
    void timeIsReturningAllUnits() {
        returnAllUnits(TestService.mockTime, Time.values());
    }

    @Test
    void temperatureIsReturningAllUnits() {
        returnAllUnits(TestService.mockTemperature, Temperature.values());
    }

}
