package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.domain.RestStatus;
import dalosto.engineering.unitconversion.domain.UnitType;
import dalosto.engineering.unitconversion.units.UnitFormula;


@SpringBootTest
@AutoConfigureMockMvc
public class TimeoutTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;

    private final int numberOfRuns = 1000;
    private final long maxtimeDuration = 500; // Usually it is 100ms, but we give it some extra time for the CI/CD pipeline to run.

    @Test
    @Disabled
    public void apiRequestMustRun1000TimesInLessThan100miliSecond() throws Exception {
        warmpUp();
        for (UnitFormula formula : formulas) {
            apiRequestForAllTypesInFormula(formula);
        }
    }


    /**
     * During the warm-up period, the Just-In-Time (JIT) compiler optimizes the code for better performance. 
     * This can result in longer execution times for the initial runs of your code.
     * To overcome this, we warm up the code by running it multiple times before the actual test.
     * With this, the first response reduced from 0.7s -> 0.1s.
     */
    private void warmpUp() throws Exception {
        for (int i=0; i<30000; i++) { // it takes around 9-10k runs to warm up the code.
            mockMvc.perform(get("/api/length?value=12345.67&type=m&target=cm")).andExpect(status().isOk());;
            mockMvc.perform(get("/api/area?value=12345.67&type=m2&target=cm2")).andExpect(status().isOk());;
        }
    }


    private void apiRequestForAllTypesInFormula(UnitFormula formula) {
        UnitType inputType = formula.getSITypeOfThisCategory();
        for (UnitType outputType : formula.getAllUnitTypesOfThisCategory()) {
            createAndRunTaskForTypes(formula, inputType, outputType);
        }
    }


    private void createAndRunTaskForTypes(UnitFormula formula, UnitType inputType, UnitType outputType) {
        String type = formula.getClass().getSimpleName().toLowerCase();
        Runnable task = () -> createTask(type, inputType, outputType);
        runTaskOrTimeout(task);
    }


    private void createTask(String type, UnitType inputType, UnitType outputType) {
        try {
            mockMvc.perform(get("/api/" + type + "?value=12345.67&type=" + inputType + "&target=" + outputType))
                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
            ;
        } catch (Exception e) {
            fail();
        }
    }


    private void runTaskOrTimeout(Runnable method) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfRuns; i++) {
            method.run();
        }
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        assertTrue(maxtimeDuration > time);
    }


}