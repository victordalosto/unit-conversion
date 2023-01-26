package dace;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import dace.unit.Force;
import dace.unit.Length;


public class UnitsTest {

    private Set<String> forceUnits = Set.of("N", "KN", "MN", "GN", "TN", "LB", 
                                            "POUND", "KIP", "G", "KG", "T");

    private Set<String> lengthUnits = Set.of("M", "DM", "CM", "MM", "HM",
                                             "KM", "UM", "IN", "FT", "YD");


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

}
