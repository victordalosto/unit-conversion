package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import dace.unit.Area;
import dace.unit.Force;
import dace.unit.Length;
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


    @Test
    void shouldBeAbleToReturnAllForceUnits() {
        assertEquals(mockForce.size(), Force.values().length);
        assertTrue(mockForce.containsAll(Stream.of(Force.values())
                                                .map(Enum::name)
                                                .collect(Collectors.toList())));
    }


    @Test
    void shouldBeAbleToReturnAllLengthUnits() {
        assertEquals(mockLength.size(), Length.values().length);
        assertTrue(mockLength.containsAll(Stream.of(Length.values())
                                                 .map(Enum::name)
                                                 .collect(Collectors.toList())));
    }


    @Test
    void shouldBeAbleToReturnAllAreaUnits() {
        assertEquals(mockArea.size(), Area.values().length);
        assertTrue(mockArea.containsAll(Stream.of(Area.values())
                                                 .map(Enum::name)
                                                 .collect(Collectors.toList())));
    }


    @Test
    void shouldBeAbleToReturnAllVolumeUnits() {
        assertEquals(mockVolume.size(), Volume.values().length);
        assertTrue(mockVolume.containsAll(Stream.of(Volume.values())
                                                 .map(Enum::name)
                                                 .collect(Collectors.toList())));
    }

}
