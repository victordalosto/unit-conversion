package dalosto.engineering.unitconversion.rest;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;


public class RestMessageTest {


    @Test
    public void testConstructor() {
        assertDoesNotThrow(() -> new RestMessage());
    }


    @Test
    public void testHeaderMessage() {
        RestMessage message = new RestMessage();
        message.addToHeader(       "home",  "homepage");
        message.addToHeader(      "title",  "Unit Conversion API");
        message.addToHeader(      "about",  "API used for conversion between measurement units most commonly used in the engineering");
        message.addToHeader("description",  "Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit");
        message.addToHeader(  "reference",  "https://github.com/victordalosto/UnitConversion");
        assertEquals(
                     "RestMessage(header={home=homepage, title=Unit Conversion API, about=API used for conversion between measurement units most commonly used in the engineering, description=Given a quantity expressed in a certain measurement unit, the endpoint returns equivalent quantitys expressed in a different measurement unit, reference=https://github.com/victordalosto/UnitConversion}, result={})",
                     message.toString());
    }


}
