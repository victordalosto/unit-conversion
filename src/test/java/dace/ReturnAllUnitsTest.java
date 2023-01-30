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


public class ReturnAllUnitsTest {

    private Set<String> forceUnits = Set.of("N", "KN", "MN", "GN", "TN", "LB", 
                                            "POUND", "KIP", "G", "KG", "T");

    private Set<String> lengthUnits = Set.of("M", "DM", "CM", "MM", "HM",
                                             "KM", "UM", "IN", "FT", "YD");

    private Set<String> AreaUnits = Set.of("M_2", "DM_2", "CM_2", "MM_2", "HM_2",
                                             "KM_2", "UM_2", "IN_2", "FT_2", "YD_2");


    @Test
    void shouldBeAbleToReturnAllForceUnits() {
        assertEquals(forceUnits.size(), Force.values().length);
        assertTrue(forceUnits.containsAll(Stream.of(Force.values())
                                                .map(Enum::name)
                                                .collect(Collectors.toList())));
    }


    @Test
    void shouldBeAbleToReturnAllLengthUnits() {
        assertEquals(lengthUnits.size(), Length.values().length);
        assertTrue(lengthUnits.containsAll(Stream.of(Length.values())
                                                 .map(Enum::name)
                                                 .collect(Collectors.toList())));
    }


    @Test
    void shouldBeAbleToReturnAllAreaUnits() {
        assertEquals(AreaUnits.size(), Length.values().length);
        assertTrue(AreaUnits.containsAll(Stream.of(Area.values())
                                                 .map(Enum::name)
                                                 .collect(Collectors.toList())));
    }

}
