package dalosto.engineering.unitconversion.service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestConvertToNumericService {

    @Autowired
    private ConvertToNumericService service;


    @Test
    void serviceShouldRemoveNoiseFromStringAndLeavesOnlyNumeric() {
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(0)aksdoaskod!)@(#)!@()").equals(0.0));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(0)kasdko.d!)@(#)0!@()").equals(0.0));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(1)o/+*x=d!)@(#)!@()").equals(1.0));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(5)o/+*x=d!)@(#)!@()").equals(5.0));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(---5)o/+*x=d!)@(#)!@().23").equals(-5.23));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(15-;.3a7)o/+*x=d!)@(#)!@()").equals(-15.37));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(12345.67)kod!)@(#)0!@()").equals(12345.67));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(12345)kod!)@(#)!@().67").equals(12345.67));
        assert(service.convertToNumeric("qwerty_!@#$%¨&*(1)a(2)k(3)sdo#$%!4ask5od!.)@6(#7)!@()").equals(12345.67));
    }
    

    @Test
    void serviceShouldFixMultiplesDecimalSeparatorAndLeavesOnlyTheFirstOcurrences() {
        assert(service.convertToNumeric("1.0.0").equals(1.0));
        assert(service.convertToNumeric("123....456").equals(123.456));
        assert(service.convertToNumeric(".1.2.3.4.5.6.").equals(0.123456));
        assert(service.convertToNumeric("0000.0.0.0.0.1.0.0.0.0").equals(0.00001));
    }


    @Test
    void serviceShouldKeepsTheSignalValueFromString() {
        assert(service.convertToNumeric("-1.0").equals(-1.0));
        assert(service.convertToNumeric("-123.456").equals(-123.456));
        assert(service.convertToNumeric("-0000.123").equals(-0.123));
        assert(service.convertToNumeric("-0-0-0-0.1-2-3").equals(-0.123));
        assert(service.convertToNumeric("+1234.56").equals(1234.56));
    }

    
    @Test
    void assertThatConversionUsingCommandAndDotAreWorking() {
        assert(service.convertToNumeric("1.0").equals(1.0));
        assert(service.convertToNumeric("1,0").equals(1.0));
        assert(service.convertToNumeric("123.45").equals(123.45));
        assert(service.convertToNumeric("-123.45").equals(-123.45));
        assert(service.convertToNumeric("123,45").equals(123.45));
        assert(service.convertToNumeric("-123,45").equals(-123.45));
    }
}
