package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.domain.RestStatus;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.units.Area;
import dalosto.engineering.unitconversion.units.Force;
import dalosto.engineering.unitconversion.units.Inertia;
import dalosto.engineering.unitconversion.units.Length;
import dalosto.engineering.unitconversion.units.Temperature;
import dalosto.engineering.unitconversion.units.Time;
import dalosto.engineering.unitconversion.units.Volume;


@SpringBootTest
@AutoConfigureMockMvc
public class TestUnitsEndPoints {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void lengthEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Length.Types.M;
        UnitType outputType = Length.Types.CM;
        mockMvc.perform(get("/api/" + "length" + "?value=12345.67&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1234567.0, type=" + outputType + "}\"")))
        ;
    }


    @Test
    public void areaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Area.Types.M2;
        UnitType outputType = Area.Types.CM2;
        mockMvc.perform(get("/api/" + "area" + "?value=1.234567&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.234567, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=12345.67, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void volumeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Volume.Types.M3;
        UnitType outputType = Volume.Types.L;
        mockMvc.perform(get("/api/" + "volume" + "?value=1.234567&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.234567, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1234.567, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void inertiaEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Inertia.Types.CM4;
        UnitType outputType = Inertia.Types.MM4;
        mockMvc.perform(get("/api/" + "inertia" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=10000.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void forceEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Force.Types.KN;
        UnitType outputType = Force.Types.N;
        mockMvc.perform(get("/api/" + "force" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=1000.0, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void temperatureEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Temperature.Types.C;
        UnitType outputType = Temperature.Types.K;
        mockMvc.perform(get("/api/" + "temperature" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=274.15, type=" + outputType + "}\"")))
        ;
    }

    
    @Test
    public void timeEndPointShouldBeAbleToConvertCorrectly() throws Exception {
        UnitType inputType = Time.Types.DAY;
        UnitType outputType = Time.Types.H;
        mockMvc.perform(get("/api/" + "time" + "?value=1.0&type=" + inputType + "&target=" + outputType))
                .andExpect(content().string(containsString("\"input\":\"{value=1.0, type=" + inputType + ", target=" + outputType + "}\"")))
                .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                .andExpect(content().string(containsString("\"unit\":\"{value=24.0, type=" + outputType + "}\"")))
        ;
    }


}