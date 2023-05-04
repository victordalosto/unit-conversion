package dalosto.engineering.unitconversion.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import jakarta.servlet.http.HttpServletRequest;


@RunWith(MockitoJUnitRunner.class)
public class RestURLTest {

    @InjectMocks
    private RestURL restURL;
    
    @Mock
    private HttpServletRequest request;


    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldGetHomeURL() {
        when(request.getScheme()).thenReturn("http");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);
        assertEquals("http://localhost:8080", restURL.getHomeURL());
    }


    @Test
    public void shouldGetCurrentURI() {
        when(request.getRequestURI()).thenReturn("/api/length");
        assertEquals("/api/length", restURL.getCurrentURI());
    }


    @Test
    public void trueIfSIEndPoint() {
        when(request.getRequestURI()).thenReturn("/api/length/si");
        assertTrue(restURL.isCurrentURIaSIEndPoint());
    }


    @Test
    public void falseIfNotSIEndPoint() {
        when(request.getRequestURI()).thenReturn("/api/length");
        assertFalse(restURL.isCurrentURIaSIEndPoint());
    }


    @Test
    public void shouldGetSIEndPoint() {
        when(request.getRequestURI()).thenReturn("/api/length/si");
        assertEquals("/api/length/si", restURL.getURIofSI());
    }


    @Test
    public void shouldGetSIForNonSIEndPoint() {
        when(request.getRequestURI()).thenReturn("/api/length");
        assertEquals("/api/length/si", restURL.getURIofSI());
    }


    @Test
    public void shouldHaveConstructorForTests() {
        RestURL restURL = new RestURL(request);
        when(request.getRequestURI()).thenReturn("/api/length/si");
        assertEquals("/api/length/si", restURL.getURIofSI());
    }

}
