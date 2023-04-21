package dalosto.engineering.unitconversion.e2e;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class FullRequestTests {


    @Autowired
    private MockMvc mockMvc;
  
    @Test
    public void testGetForce() throws Exception {
      mockMvc.perform(get("/api/force")
             .param("value", "1")
             .param("type", "kg")
             .param("target", "kg"))
        .andExpect(status().isOk())
        .andExpect(content().json("{value:1, type:kg}"));
    }
  }