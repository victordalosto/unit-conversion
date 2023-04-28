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
public class TimeoutTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private List<UnitFormula> formulas;

    private final int numberOfRuns = 100;
    private final long maxtimeDuration = 200;

    @Test
    public void apiRequestMustRun100TimesInLessThan200Miliseconds() {
        for (UnitFormula formula : formulas) {
            apiRequestForAllTypesInFormula(formula);
        }

    }


    private void apiRequestForAllTypesInFormula(UnitFormula formula) {
        for (UnitType inputType : formula.getAllUnitTypesOfThisCategory()) {
            for (UnitType outputType : formula.getAllUnitTypesOfThisCategory()) {
                createAndRunTaskForTypes(formula, inputType, outputType);
            }
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
        for (int i = 0; i < numberOfRuns; i++)
            method.run();
        long endTime = System.currentTimeMillis();
        assertTrue(maxtimeDuration > (endTime - startTime));
    }



}