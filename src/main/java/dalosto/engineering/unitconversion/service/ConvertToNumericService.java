package dalosto.engineering.unitconversion.service;
import java.util.Locale;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.exception.ParameterException;


@Service
public final class ConvertToNumericService {

    static {
        setDotAsDefaultDecimalSeparator();
    }


    public Double convertToNumeric(String value) {
        if (value == null || value.isEmpty()) {
            throw new ParameterException("value can't be NULL.");
        }
        String newValue = value.replace(",",".")
                               .replaceAll("[^\\d.-]", "");
        newValue = removesMultiplesDecimalSeparator(newValue);
        newValue = fixSignalValueFromString(newValue);
        return Double.parseDouble(newValue);
    }

    
    private String removesMultiplesDecimalSeparator(String value) {
        if (!value.contains(".")) {
            return value;
        }
        int dotIndex = value.indexOf(".");
        StringBuilder sb = new StringBuilder(value);
        for (int i = dotIndex + 1; i < sb.length(); i++) {
            if (sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
                i--;
            }
        }
        return sb.toString();
    }


    private String fixSignalValueFromString(String value) {
        if (!value.contains("-")) {
            return value;
        }
        return "-" + value.replace("-", "");
    }


    private static void setDotAsDefaultDecimalSeparator() {
        Locale.setDefault(new Locale("en", "US"));
    }

}
