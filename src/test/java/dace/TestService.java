package dace;
import java.util.Set;


public class TestService {

    public static Set<String> mockForce = Set.of("N", "KN", "MN", "GN", "TN", "LB", 
                                                 "POUND", "KIP", "G", "KG", "T");

    public static Set<String> mockLength = Set.of("M", "DM", "CM", "MM", "HM",
                                                  "KM", "UM", "IN", "FT", "YD");

    public static Set<String> mockArea = Set.of("M_2", "DM_2", "CM_2", "MM_2", "HM_2",
                                                "KM_2", "UM_2", "IN_2", "FT_2", "YD_2");

    public static Set<String> mockVolume = Set.of("M_3", "DM_3", "CM_3", "MM_3", "HM_3", "KM_3",
                                                  "UM_3", "IN_3", "FT_3", "YD_3", "ML", "L");

    public static Set<String> mockTime = Set.of("S", "MS", "US", "MIN", "H", "DAY", "WEEK",
                                                "MONTH", "MONTH_30", "MONTH_31", "YEAR");
                                          
    public static Set<String> mockInertia = Set.of("M_4",  "DM_4", "CM_4", "MM_4", "HM_4",
                                                   "KM_4", "UM_4", "IN_4", "FT_4", "YD_4");
                                      
    public static Set<String> mockTemperature = Set.of("K", "F", "C");
    
}
