package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
public class EndPointsTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void shouldBeAbleToAcessAllEndPoints() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category))
                   .andExpect(status().isOk())
                   .andExpect(content().string(containsString("header")))
                   .andExpect(content().string(containsString("result")))
                   .andExpect(content().string(containsString("\"uri\":\"/api/" + category + "\"")))
                   .andExpect(content().string(containsString("\"home\":")))
            ;
        }
    }

    @Test
    public void shouldBeAbleToAcessSI() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "/si"))
                   .andExpect(status().isOk())
                   .andExpect(content().string(containsString("header")))
                   .andExpect(content().string(containsString("result")))
                   .andExpect(content().string(containsString("\"uri\":\"/api/" + category + "/si\"")))
                   .andExpect(content().string(containsString("\"home\":")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayInfoWhenNoParamsArePresented() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category))
                    .andExpect(content().string(containsString("\"input\":\"{value=null, type=null, target=null}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.INFO+"\":")))
                    .andExpect(content().string(containsString("\"title\":")))
                    .andExpect(content().string(containsString("\"types\":\"" + formula.getAllUnitTypesOfThisCategory().toString() + "\"")))
                    .andExpect(content().string(containsString("\"example\":")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplaySIWhenNoParamsArePresented() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category))
                    .andExpect(content().string(containsString("\"si\":")))
                    .andExpect(content().string(containsString("\"uri-si\":\"/api/"+category+"/si")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsValue() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=invalid"))
                    .andExpect(content().string(containsString("\"input\":\"{value=invalid, type=null, target=null}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.ERROR+"\":")))
                    .andExpect(content().string(containsString("\"ParameterException\":\"value must be Numeric.\"")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }

    
    @Test
    public void endPointsShouldDisplayExceptionIfNullParametersAreGivenAsType() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=12345.67"))
                    .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=null, target=null}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.ERROR+"\":")))
                    .andExpect(content().string(containsString("\"ParameterException\":\"type can't be NULL")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }

    
    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsType() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=invalid"))
                    .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=invalid, target=null}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.ERROR+"\":")))
                    .andExpect(content().string(containsStringIgnoringCase("\"ParameterException\":\"type INVALID")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfNullParametersAreGivenAsTarget() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            String type = formula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=" + type))
                    .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=" + type + ", target=null}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.ERROR+"\":")))
                    .andExpect(content().string(containsString("\"ParameterException\":\"target can't be NULL.")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsTarget() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            String type = formula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=" + type + "&target=invalid"))
                    .andExpect(content().string(containsString("\"input\":\"{value=12345.67, type=" + type + ", target=invalid}\"")))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.ERROR+"\":")))
                    .andExpect(content().string(containsStringIgnoringCase("\"ParameterException\":\"type INVALID")))
                    .andExpect(content().string(containsString("\"uri-example\":\"/example")))
            ;
        }
    }
}
