package dalosto.engineering.unitconversion.controller;
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
                    .andExpectAll(
                        status().isOk(),
                        content().string(containsStringIgnoringCase("header")),
                        content().string(containsStringIgnoringCase("\"uri\":\"/api/" + category + "\"")),
                        content().string(containsStringIgnoringCase("\"home\":")),
                        content().string(containsStringIgnoringCase("result")))
            ;
        }
    }

    
    @Test
    public void shouldBeAbleToAcessSI() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "/si"))
                    .andExpectAll(
                        status().isOk(),
                        content().string(containsStringIgnoringCase("header")),
                        content().string(containsStringIgnoringCase("\"uri\":\"/api/" + category + "/si\"")),
                        content().string(containsStringIgnoringCase("\"home\":")),
                        content().string(containsStringIgnoringCase("result")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayInfoWhenNoParamsArePresented() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=null, type=null, target=null}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.INFO+"\"")),
                        content().string(containsStringIgnoringCase("\"title\":")),
                        content().string(containsStringIgnoringCase("\"types\":\"" + formula.getAllUnitTypesOfThisCategory().toString() + "\"")),
                        content().string(containsStringIgnoringCase("\"example\":")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayInfoAboutSIWhenNoParamsArePresented() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category))
                    .andExpect(content().string(containsStringIgnoringCase("\"si\":")))
                    .andExpect(content().string(containsStringIgnoringCase("\"uri-si\":\"/api/"+category+"/si")))
            ;
        }
    }


    @Test
    public void siEndPointsShouldDisplayTheSIUnit() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "/si"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=null, type=null, target="+formula.getSITypeOfThisCategory()+"}\"")),
                        content().string(containsStringIgnoringCase("\"types\":\"" + formula.getAllUnitTypesOfThisCategory().toString() + "\"")),
                        content().string(containsStringIgnoringCase("\"si\":\"Converts values into: " + formula.getSITypeOfThisCategory() + "\"")))
            ;
        }
    }


    @Test
    public void siEndPointsShouldIgnoreTargetOfSiUNIT() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "/si?target=invalid"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=null, type=null, target="+formula.getSITypeOfThisCategory()+"}\"")),
                        content().string(containsStringIgnoringCase("\"types\":\"" + formula.getAllUnitTypesOfThisCategory().toString() + "\"")),
                        content().string(containsStringIgnoringCase("\"si\":\"Converts values into: " + formula.getSITypeOfThisCategory() + "\"")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsValue() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=invalid"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=invalid, type=null, target=null}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")),
                        content().string(containsStringIgnoringCase("\"ParameterException\":\"value must be Numeric.\"")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }

    
    @Test
    public void endPointsShouldDisplayExceptionIfNullParametersAreGivenAsType() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=12345.67"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=12345.67, type=null, target=null}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")),
                        content().string(containsStringIgnoringCase("\"ParameterException\":\"type can't be NULL")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }

    
    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsType() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=invalid"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=12345.67, type=invalid, target=null}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")),
                        content().string(containsStringIgnoringCase("\"ParameterException\":\"type INVALID")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfNullParametersAreGivenAsTarget() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            String type = formula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=" + type))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=12345.67, type=" + type + ", target=null}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")),
                        content().string(containsStringIgnoringCase("\"ParameterException\":\"target can't be NULL.")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }


    @Test
    public void endPointsShouldDisplayExceptionIfInvalidParametersAreGivenAsTarget() throws Exception {
        for (UnitFormula formula : formulas) {
            String category = formula.getClass().getSimpleName().toLowerCase();
            String type = formula.getAllUnitTypesOfThisCategory().stream().findFirst().get().toString();
            mockMvc.perform(get("/api/" + category + "?value=12345.67&type=" + type + "&target=invalid"))
                    .andExpectAll(
                        content().string(containsStringIgnoringCase("\"input\":\"{value=12345.67, type=" + type + ", target=invalid}\"")),
                        content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")),
                        content().string(containsStringIgnoringCase("\"ParameterException\":\"type INVALID")),
                        content().string(containsStringIgnoringCase("\"uri-example\":\"/example")))
            ;
        }
    }
}
