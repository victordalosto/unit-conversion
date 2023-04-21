package dalosto.engineering.unitconversion.service;
import java.util.Locale;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.exception.ParameterException;


@Service
public final class NumericService {

    static {
        setDotAsDefaultDecimalSeparator();
    }


    public Double convertToNumeric(String rawText) throws ParameterException {
        if (rawText == null || rawText.isBlank()) {
            throw new ParameterException("value can't be NULL.");
        }
        String value = rawText.replace(",",".")
                              .replaceAll("[^\\d.\\-\\+eE]", "");
        value = removesMultiplesDecimalSeparatorBesidesTheFirst(value);
        return convertStringToNumeric(value);
    }


    private String removesMultiplesDecimalSeparatorBesidesTheFirst(String value) {
        if (!value.contains(".")) {
            return value;
        }
        StringBuilder sb = new StringBuilder(value);
        for (int i = value.indexOf(".") + 1; i < sb.length(); i++) {
            if (sb.charAt(i) == '.') {
                sb.deleteCharAt(i);
                i--;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        
    }

    
    private Double convertStringToNumeric(String newValue) {
        try {
            return Double.parseDouble(newValue);
        } catch (NumberFormatException e) {
            throw new ParameterException("value must be Numeric.");
        }
    }


    private static void setDotAsDefaultDecimalSeparator() {
        Locale.setDefault(new Locale("en", "US"));
    }

}
