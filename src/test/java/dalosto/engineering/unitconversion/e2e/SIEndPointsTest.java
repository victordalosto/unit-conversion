package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.formula.UnitFormula;
import dalosto.engineering.unitconversion.rest.RestStatus;


@SpringBootTest
@AutoConfigureMockMvc
public class SIEndPointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;


    public void testEndPoint(String endpoint, String inputType, String outputType, String inputValue, String outputValue) throws Exception {
        mockMvc.perform(get("/api/" + endpoint + "/si" + "?value=" + inputValue + "&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsStringIgnoringCase("\"input\":\"{value=" + inputValue + ", type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsStringIgnoringCase("\"unit\":\"{value=" + outputValue + ", type=" + outputType + "}\"")))
        ;
    }


    @Test
    public void testWithSITypeShouldResultInSameValue() throws Exception {
        assertEquals(formulas.size(), 12);
        testEndPoint("length",  "M",    "M",   "12345.67", "12345.67");
        testEndPoint("area",    "M2",   "M2",  "12345.67", "12345.67");
        testEndPoint("volume",  "M3",   "M3",  "12345.67", "12345.67");
        testEndPoint("inertia", "M4",   "M4",  "12345.67", "12345.67");
        testEndPoint("force",   "N",    "N",   "12345.67", "12345.67");
        testEndPoint("torque",  "N.M",  "N.M", "12345.67", "12345.67");
        testEndPoint("linear",  "N/M",  "N/M", "12345.67", "12345.67");
        testEndPoint("pressure","N/M2", "N/M2","12345.67", "12345.67");
        testEndPoint("density", "N/M3", "N/M3","12345.67", "12345.67");
        testEndPoint("speed",   "M/S",  "M/S", "12345.67", "12345.67");
        testEndPoint("time",    "S",    "S", "12345.67", "12345.67");
        testEndPoint("temperature", "K", "K", "12345.67", "12345.67");
    }


    @Test
    public void lengthEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("force", "N",     "N", "1.0", "1.0");
        testEndPoint("force", "KN",    "N", "1.0", "1000.0");
        testEndPoint("force", "MN",    "N", "1.0", "1000000.0");
        testEndPoint("force", "GN",    "N", "1.0", "1.0E9");
        testEndPoint("force", "TN",    "N", "1.0", "1.0E12");
        testEndPoint("force", "LB",    "N", "1.0", "4.448221628251");
        testEndPoint("force", "POUND", "N", "1.0", "4.448221628251");
        testEndPoint("force", "OZ",    "N", "1.0", "0.278013851766");
        testEndPoint("force", "KIP",   "N", "1.0", "4448.2216282509");
        testEndPoint("force", "GF",    "N", "1.0", "0.009806650029");
        testEndPoint("force", "G",     "N", "1.0", "0.009806650029");
        testEndPoint("force", "KGF",   "N", "1.0", "9.806650028639");
        testEndPoint("force", "KG",    "N", "1.0", "9.806650028639");
        testEndPoint("force", "T",     "N", "1.0", "9806.6500286389");
    }




}