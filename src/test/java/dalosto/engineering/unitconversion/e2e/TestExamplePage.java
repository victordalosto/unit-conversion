package dalosto.engineering.unitconversion.e2e;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import dalosto.engineering.unitconversion.domain.RestStatus;


@SpringBootTest
@AutoConfigureMockMvc
public class TestExamplePage {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void examplePageShouldReturnHeaderAndResult() throws Exception {
        mockMvc.perform(get("/example"))
               .andExpect(status().isOk())
               .andExpect(content().string(containsString("header")))
               .andExpect(content().string(containsString("result")))
               .andExpect(content().string(containsString("\"uri\":\"/example\"")))
               .andExpect(content().string(containsString("This endpoint provides example in how to use this API to convert measurement units.")))
        ;
    }


    @Test
    void statusMustBePresentedIntExamplePage() throws Exception {
        for (RestStatus status : RestStatus.values()) {
            mockMvc.perform(get("/example"))
                   .andExpect(status().isOk())
                   .andExpect(content().string(containsString(status.toString())))
            ;
        }
    }


    @Test
    void observationsAboutResilienceSHouldBeInExample() throws Exception {
        mockMvc.perform(get("/example"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("resilient")))
                .andExpect(content().string(containsString("Values can be represented using comma (1,23), dot (1.23), or contain noise (myVal is 1.23)")))
        ;
    }

}