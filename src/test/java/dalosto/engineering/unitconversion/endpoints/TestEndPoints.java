package dalosto.engineering.unitconversion.endpoints;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.interfaces.UnitFormula;


@SpringBootTest
@AutoConfigureMockMvc
public class TestEndPoints {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;


    @Test
    void shouldBeAbleToAcessAllFormulasEndPoints() throws Exception {
        assert(formulas.size() >= 7);
        for (UnitFormula formula : formulas) {
            String type = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + type))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("header")))
                    .andExpect(content().string(containsString("result")))
                    .andExpect(content().string(containsString("\"uri\":\"/api/" + type + "\"")))
                    .andExpect(content().string(containsString("\"home\":")))
            ;
        }
    }


    @Test
    void endPointsShouldDisplayInfoWhenNoParamsArePresented() throws Exception {
        for (UnitFormula formula : formulas) {
            String type = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/api/" + type))
                    .andExpect(content().string(containsString("\"input\":\"{value=null, type=null, target=null}\"")))
                    .andExpect(content().string(containsString("\"info\":")))
                    .andExpect(content().string(containsString("\"title\":")))
                    .andExpect(content().string(containsString("\"types\":\"" + formula.getAllUnitTypesOfThisCategory().toString() + "\"")))
                    .andExpect(content().string(containsString("\"about\":")))
                    .andExpect(content().string(containsString("\"uri\":\"/example")))
            ;
        }
    }


    @Test
    void endPointsShouldDisplayExceptionIfInvalidParametersAreGiven() {
        // TODO
    }
}
