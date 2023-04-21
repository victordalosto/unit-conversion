package dalosto.engineering.unitconversion.e2e;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


// @WebMvcTest
public class FullRequestTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetForce() throws Exception {
        // mockMvc.perform(get("/force?mass=1&acceleration=1"))
        // .andExpect(status().isOk())
        // .andExpect(content().string("1.0"));
    }

}