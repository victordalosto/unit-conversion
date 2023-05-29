package dalosto.engineering.unitconversion.rest;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import dalosto.engineering.unitconversion.controller.TemplateController;


@SpringBootTest
@AutoConfigureMockMvc
public class RequestStatus {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    public void shouldReturnOkNoParametersAreGiven() throws Exception {
        mockMvc.perform(get("/api/length"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.INFO+"\"")));
        ;
    }

    @Test
    public void shouldReturnOkWhenAllParametersAreGiven() throws Exception {
        mockMvc.perform(get("/api/length" + "?value=" + 5.0 + "&type=" + "cm" + "&target=" + "mm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.SUCCESS+"\"")));
        ;
    }
     

    @Test
    public void shouldReturnErrorWithInvalidParameters1() throws Exception {
        mockMvc.perform(get("/api/length?value=5"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")));
        ;
    }
     

    @Test
    public void shouldReturnErrorWithInvalidParameters2() throws Exception {
        mockMvc.perform(get("/api/length?value=5&type=CM"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")));
        ;
    }
     

    @Test
    public void shouldReturnErrorWithInvalidParameters3() throws Exception {
        mockMvc.perform(get("/api/length?value=5&type=CM&target=invalid"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string(containsStringIgnoringCase("\"status\":\""+RestStatus.ERROR+"\"")));
        ;
    }
    
}
