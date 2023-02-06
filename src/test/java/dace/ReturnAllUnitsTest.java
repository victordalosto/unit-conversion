package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import dace.model.Unit;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Inertia;
import dace.unit.Length;
import dace.unit.Temperature;
import dace.unit.Time;
import dace.unit.Volume;


public class ReturnAllUnitsTest {

    private Set<String> mockForce = Set.of("N", "KN", "MN", "GN", "TN", "LB", 
                                            "POUND", "KIP", "G", "KG", "T");

    private Set<String> mockLength = Set.of("M", "DM", "CM", "MM", "HM",
                                             "KM", "UM", "IN", "FT", "YD");

    private Set<String> mockArea = Set.of("M_2", "DM_2", "CM_2", "MM_2", "HM_2",
                                             "KM_2", "UM_2", "IN_2", "FT_2", "YD_2");

    private Set<String> mockVolume = Set.of("M_3", "DM_3", "CM_3", "MM_3", "HM_3", "KM_3",
                                            "UM_3", "IN_3", "FT_3", "YD_3", "ML", "L");

    private Set<String> mockTime = Set.of("S", "MS", "US", "MIN", "H", "DAY", "WEEK",
                                          "MONTH", "MONTH_30", "MONTH_31", "YEAR");
                                          
    private Set<String> mockInertia = Set.of("M_4",  "DM_4", "CM_4", "MM_4", "HM_4",
                                             "KM_4", "UM_4", "IN_4", "FT_4", "YD_4");
                                      
    private Set<String> mockTemperature = Set.of("K", "F", "C");

    
    @Test
    void forceIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Force.class));
        assertEquals(mockForce.size(), Force.values().length);
        assertTrue(mockForce.containsAll(Stream.of(Force.values())
                                               .map(Enum::name)
                                               .collect(Collectors.toList())));
    }


    @Test
    void lengthIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Length.class));
        assertEquals(mockLength.size(), Length.values().length);
        assertTrue(mockLength.containsAll(Stream.of(Length.values())
                                                .map(Enum::name)
                                                .collect(Collectors.toList())));
    }


    @Test
    void areaIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Area.class));
        assertEquals(mockArea.size(), Area.values().length);
        assertTrue(mockArea.containsAll(Stream.of(Area.values())
                                              .map(Enum::name)
                                              .collect(Collectors.toList())));
    }


    @Test
    void volumeIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Volume.class));
        assertEquals(mockVolume.size(), Volume.values().length);
        assertTrue(mockVolume.containsAll(Stream.of(Volume.values())
                                                .map(Enum::name)
                                                .collect(Collectors.toList())));
    }


    @Test
    void inertiaBeAbleToReturnAllInertiaUnits() {
        assertTrue(Unit.class.isAssignableFrom(Inertia.class));
        assertEquals(mockInertia.size(), Inertia.values().length);
        assertTrue(mockInertia.containsAll(Stream.of(Inertia.values())
                                                .map(Enum::name)
                                                .collect(Collectors.toList())));
    }


    @Test
    void timeIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Time.class));
        assertEquals(mockTime.size(), Time.values().length);
        assertTrue(mockTime.containsAll(Stream.of(Time.values())
                                              .map(Enum::name)
                                              .collect(Collectors.toList())));
    }


    @Test
    void temperatureIsReturningAllUnits() {
        assertTrue(Unit.class.isAssignableFrom(Temperature.class));
        assertEquals(mockTemperature.size(), Temperature.values().length);
        assertTrue(mockTemperature.containsAll(Stream.of(Temperature.values())
                                              .map(Enum::name)
                                              .collect(Collectors.toList())));
    }

}
