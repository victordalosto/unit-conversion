package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.rest.RestStatus;


@SpringBootTest
@AutoConfigureMockMvc
public class UnitsEndPointsTest {

    @Autowired
    private MockMvc mockMvc;

    public void testEndPoint(String endpoint, String inputType, String outputType, String inputValue, String outputValue) throws Exception {
        mockMvc.perform(get("/api/" + endpoint + "?value=" + inputValue + "&type=" + inputType + "&target=" + outputType))
                .andExpectAll(
                    content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.SUCCESS+"\"")),
                    content().string(containsStringIgnoringCase("\"input\":\"{value=" + inputValue + ", type=" + inputType + ", target=" + outputType + "}\"")),
                    content().string(containsStringIgnoringCase("\"unit\":\"{value=" + outputValue)))
        ;
    }


    @Test
    public void lengthEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("length", "M", "CM", "12345.67", "1234567.0");
        testEndPoint("length", "M", "MM", "12345.67", "1.234567E7");
        testEndPoint("length", "M", "DM", "12345.67", "123456.7");
        testEndPoint("length", "M", "HM", "12345.67", "123.4567");
        testEndPoint("length", "M", "KM", "12345.67", "12.34567");
        testEndPoint("length", "M", "UM", "12345.67", "1.234567E10");
        testEndPoint("length", "M", "IN", "12345.67", "486050.0");
        testEndPoint("length", "M", "FT", "12345.67", "40504.16666666666");
        testEndPoint("length", "M", "YD", "12345.67", "13501.388888888889");
        testEndPoint("length", "CM", "M", "123.4567", "1.234567");
        testEndPoint("length", "CM", "DM", "12345.67", "1234.567");
        testEndPoint("length", "CM", "MM", "123.4567", "1234.567");
        testEndPoint("length", "CM", "HM", "12345.67", "1.234567");
        testEndPoint("length", "CM", "KM", "1234567.0", "12.34567");
        testEndPoint("length", "CM", "UM", "0.1234567", "1234.567");
        testEndPoint("length", "CM", "IN", "1234.567", "486.05");
        testEndPoint("length", "CM", "FT", "1234.567", "40.504166666667");
        testEndPoint("length", "CM", "YD", "1234.567", "13.501388888889");
    }


    @Test
    public void areaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("area", "M2", "MM2", "12345.67", "1.234567E10");
        testEndPoint("area", "M2", "DM2", "12345.67", "1234567.0");
        testEndPoint("area", "M2", "CM2", "12345.67", "1.234567E8");
    }


    @Test
    public void volumeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("volume", "M3", "L", "1.234567", "1234.567");
    }


    @Test
    public void inertiaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("inertia", "CM4", "MM4", "1.0", "10000.0");
    }


    @Test
    public void forceEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("force", "KN", "N", "1.0", "1000.0");
    }


    @Test
    public void temperatureEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("temperature", "C", "K", "1.0", "274.15");
    }


    @Test
    public void timeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("time", "DAY", "H", "1.0", "24.0");
    }


    @Test
    public void torqueEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("torque", "KN.CM", "N.MM", "1.0", "10000.0");
    }


    @Test
    public void linearEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("linear", "KGF/CM", "T/M", "1.0", "0.1");
    }


    @Test
    public void stressEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("stress", "MPA", "N/MM2", "123.45", "123.45");
    }


    @Test
    public void densityEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("density", "KG/M3", "G/CM3", "1.0", "0.001");
    }


    @Test
    public void speedEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        testEndPoint("speed", "km/h", "m/s", "3.6", "1.0");
    }


}