package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.domain.RestStatus;


@SpringBootTest
@AutoConfigureMockMvc
public class UnitsEndPointsTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void lengthEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "M";
        String outputType = "CM";
        mockMvc.perform(get("/api/" + "length" + "?value=12345.67&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1234567.0, type=" + outputType + "}\"")))
        ;
    }


    @Test
    public void areaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "M2";
        String outputType = "CM2";
        mockMvc.perform(get("/api/" + "area" + "?value=1.234567&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.234567, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=12345.67, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void volumeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "M3";
        String outputType = "L";
        mockMvc.perform(get("/api/" + "volume" + "?value=1.234567&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.234567, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1234.567, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void inertiaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "CM4";
        String outputType = "MM4";
        mockMvc.perform(get("/api/" + "inertia" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=10000.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void forceEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "KN";
        String outputType = "N";
        mockMvc.perform(get("/api/" + "force" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1000.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void temperatureEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "C";
        String outputType = "K";
        mockMvc.perform(get("/api/" + "temperature" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=274.15, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void timeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "DAY";
        String outputType = "H";
        mockMvc.perform(get("/api/" + "time" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=24.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void torqueEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "KN.CM";
        String outputType = "N.MM";
        mockMvc.perform(get("/api/" + "torque" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=10000.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void linearEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        String inputType = "KG/CM";
        String outputType = "T/M";
        mockMvc.perform(get("/api/" + "linear" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=0.1, type=" + outputType + "}\"")))
        ;
    }


}