package dalosto.engineering.unitconversion.rest;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dalosto.engineering.unitconversion.domain.UnitDAO;


public class RestAttributesTest {

    private RestAttributes attributes;


    @BeforeEach
    public void beforeEach() {
        this.attributes = null;
    }


    @Test
    public void constructorIsValid() {
        UnitDAO unitDAO = new UnitDAO("12345.67", "MM", "CM");
        attributes = new RestAttributes(unitDAO, null, null);
        assertEquals(unitDAO, attributes.getUnitDAO());
    }

}
