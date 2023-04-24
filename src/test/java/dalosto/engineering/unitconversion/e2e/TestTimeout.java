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
    public void testsMustBeExecuted100TimesIn1000SecondsBeforeTimeOut() {
        for (UnitFormula formula : formulas) {
            String type = formula.getClass().getSimpleName().toLowerCase();
            for (UnitType inputType : formula.getAllUnitTypesOfThisCategory()) {
                for (UnitType outputType : formula.getAllUnitTypesOfThisCategory()) {
                    Runnable task = () -> {
                        try {
                            mockMvc.perform(get("/api/" + type + "?value=12345.67&type=" + inputType + "&target=" + outputType))
                                    .andExpect(content().string(containsStringIgnoringCase("\""+RestStatus.SUCCESS+"\":")))
                            ;
                        } catch (Exception e) {
                            fail();
                        }
                    };
                    assertTimeExecutionOfMethodIsExecuted1000InGivenTime(1000, task);
                }
            }
        }

    }


    private void assertTimeExecutionOfMethodIsExecuted1000InGivenTime(long time, Runnable method) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 500; i++) {
            method.run();
        }
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        assertTrue(duration < time);
    }



}