package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
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
public class TestTimeout {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;

    @Test
    public void testsMustBeExecuted100TimesIn500SecondsBeforeTimeOut() {
        for (UnitFormula formula : formulas) {
            for (UnitType inputType : formula.getAllUnitTypesOfThisCategory()) {
                for (UnitType outputType : formula.getAllUnitTypesOfThisCategory()) {
                    createAndRunTask(formula, inputType, outputType);
                }
            }
        }

    }


    private void createAndRunTask(UnitFormula formula, UnitType inputType, UnitType outputType) {
        String type = formula.getClass().getSimpleName().toLowerCase();
        Runnable task = () -> createTask(type, inputType, outputType);
        assertTimeExecutionOfMethodIsExecutedInGivenTime(task, 300, 100);
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


    private void assertTimeExecutionOfMethodIsExecutedInGivenTime(Runnable method, long time, int numberOfRuns) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numberOfRuns; i++)
            method.run();
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        assertTrue(duration < time);
    }



}