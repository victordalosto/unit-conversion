package dalosto.engineering.unitconversion.e2e;
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
import dalosto.engineering.unitconversion.units.UnitFormula;


@SpringBootTest
@AutoConfigureMockMvc
public class HomePageTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private List<UnitFormula> formulas;


    @Test
    public void homePageShouldReturnHeaderAndResultAndInformations() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("header")))
               .andExpect(content().string(containsString("result")))
               .andExpect(content().string(containsString("Unit Conversion API")))
               .andExpect(content().string(containsString("https://github.com/victordalosto")))
            ;
    }

    
    @Test
    public void homePageMustListAllTypes() throws Exception {
        assert(formulas.size() >= 7);
        for (UnitFormula formula : formulas) {
            String type = formula.getClass().getSimpleName().toLowerCase();
            mockMvc.perform(get("/"))
                   .andExpect(status().isOk())
                   .andExpect(content().string(containsString(type)))
                   .andExpect(content().string(containsString(formula.getAllUnitTypesOfThisCategory().toString())))
                   .andExpect(content().string(containsString("\"uri\":\"/api/" + type + "\"")))

                ;
        }
    }


}