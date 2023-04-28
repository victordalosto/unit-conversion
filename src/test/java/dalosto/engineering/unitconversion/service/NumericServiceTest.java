package dalosto.engineering.unitconversion.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import dalosto.engineering.unitconversion.MetricTest;
import dalosto.engineering.unitconversion.exception.ParameterException;


@SpringBootTest
public class NumericServiceTest {

    @Autowired
    private NumericService service;


    @Test
    public void invalidNumericShouldThrowsParameterExceptionNotNUmericException() {
        assertThrows(ParameterException.class, () -> service.convertToNumeric(""));
        assertThrows(ParameterException.class, () -> service.convertToNumeric(" "));
        assertThrows(ParameterException.class, () -> service.convertToNumeric(null));
        assertThrows(ParameterException.class, () -> service.convertToNumeric("null"));
    }


    @Test
    public void serviceShouldBeAbleToHandleExponentialValuesWithExplicitE() {
        assert(service.convertToNumeric("1e-3").equals(0.001));
        assert(service.convertToNumeric("1.0e-3").equals(0.001));
        assert(service.convertToNumeric("1e-03").equals(0.001));
        assert(service.convertToNumeric("1E-3").equals(0.001));
        assert(service.convertToNumeric("1 e - 3 ").equals(0.001));
        assert(service.convertToNumeric("1e3").equals(1000.0));
        assert(service.convertToNumeric("1e03").equals(1000.0));
        assert(service.convertToNumeric("1e+3").equals(1000.0));
        assert(service.convertToNumeric("1e+03").equals(1000.0));
        assert(service.convertToNumeric("1 e+3 ").equals(1000.0));
        assert(service.convertToNumeric("1E3").equals(1000.0));
        assert(service.convertToNumeric("1E+3").equals(1000.0));
    }


    @Test
    public void serviceShouldBeAbleToHandleEspecialExponenitals() {
        assertEquals(0.001, service.convertToNumeric("1e-3.0"), MetricTest.tolerance);
        assertEquals(-0.001, service.convertToNumeric("-1.0e-3.0"), MetricTest.tolerance);
        assertEquals(1000.0, service.convertToNumeric("1.0e+3.0"), MetricTest.tolerance);
        assertEquals(1743258.1080900824, service.convertToNumeric("3,1e5.75"), MetricTest.tolerance);
        assertEquals(1743258.1080900824, service.convertToNumeric("3,1e+5.75"), MetricTest.tolerance);
        assertEquals(33951205.17627744, service.convertToNumeric("0.557e+7.785"), MetricTest.tolerance);
        assertEquals(-0.14025230678774545, service.convertToNumeric("-0.0125e+1.05"), MetricTest.tolerance);
        assertEquals(-0.00257714042, service.convertToNumeric("-0.0332e-1.11"), MetricTest.tolerance);
        assertEquals(1743258.1080900824, service.convertToNumeric("+3,1.000e+5.75000"), MetricTest.tolerance);
    }


    @Test
    public void serviceShouldBeAbleToConvertSomeTypesToExponential() {
        assertEquals(0.001, service.convertToNumeric("1*10^^-3.0"), MetricTest.tolerance);
        assertEquals(0.00075178085, service.convertToNumeric("1.5 * 10^ -3.3"), MetricTest.tolerance);
        assertEquals(0.00075178085, service.convertToNumeric("001.500*10^^-3.300"), MetricTest.tolerance);
        assertEquals(2992.8934724533183, service.convertToNumeric("1.5*10^+3.3"), MetricTest.tolerance);
        assertEquals(2992.8934724533183, service.convertToNumeric("1.5*10^3.3"), MetricTest.tolerance);
    }


    @Test
    public void serviceShouldBeABleToFixExponentialValuesExportedBySomeProgramsLikeGoogleEarth() {
        assertEquals(0.001, service.convertToNumeric("1-3.0"), MetricTest.tolerance);
        assertEquals(0.00075178085, service.convertToNumeric("1.5  -3.3"), MetricTest.tolerance);
        assertEquals(0.00075178085, service.convertToNumeric("001.500-3.300"), MetricTest.tolerance);
        assertEquals(2992.8934724533183, service.convertToNumeric("1.5*+3.3"), MetricTest.tolerance);
    }


    @Test
    public void serviceShouldRemoveNoiseFromStringAndLeavesOnlyNumeric() {
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(0)aksdoaskod!)@(#)!@()").equals(0.0));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(0)kasdko.d!)@(#)0!@()").equals(0.0));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(1)o/*x=d!)@(#)!@()").equals(1.0));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(5)o/*x=d!)@(#)!@()").equals(5.0));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(-5)o/*x=d!)@(#)!@().23").equals(-5.23));
        assert(service.convertToNumeric("-qwrty_!@#$%¨&*(15;.3a7)o/*x=d!)@(#)!@()").equals(-15.37));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(12345.67)kod!)@(#)0!@()").equals(12345.67));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(12345)kod!)@(#)!@().67").equals(12345.67));
        assert(service.convertToNumeric("qwrty_!@#$%¨&*(1)a(2)k(3)sdo#$%!4ask5od!.)@6(#7)!@()").equals(12345.67));
    }
    

    @Test
    public void serviceShouldFixMultiplesDecimalSeparatorAndLeavesOnlyTheFirstOcurrences() {
        assert(service.convertToNumeric("1.0.0").equals(1.0));
        assert(service.convertToNumeric("123....456").equals(123.456));
        assert(service.convertToNumeric(".1.2.3.4.5.6.").equals(0.123456));
        assert(service.convertToNumeric("0000.0.0.0.0.1.0.0.0.0").equals(0.00001));
    }


    @Test
    public void serviceShouldKeepsTheSignalValueFromString() {
        assert(service.convertToNumeric("-1.0").equals(-1.0));
        assert(service.convertToNumeric("-123.456").equals(-123.456));
        assert(service.convertToNumeric("-0000.123").equals(-0.123));
        assert(service.convertToNumeric("+1234.56").equals(1234.56));
    }

    
    @Test
    public void assertThatConversionUsingCommandAndDotAreWorking() {
        assert(service.convertToNumeric("1.0").equals(1.0));
        assert(service.convertToNumeric("1,0").equals(1.0));
        assert(service.convertToNumeric("123.45").equals(123.45));
        assert(service.convertToNumeric("-123.45").equals(-123.45));
        assert(service.convertToNumeric("123,45").equals(123.45));
        assert(service.convertToNumeric("-123,45").equals(-123.45));
    }


}
