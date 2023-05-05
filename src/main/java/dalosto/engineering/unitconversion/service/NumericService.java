package dalosto.engineering.unitconversion.service;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;
import dalosto.engineering.unitconversion.exception.ParameterException;


@Service
public final class NumericService {

    static {
        setDotAsDefaultDecimalSeparator();
    }

    private final Pattern pattern = Pattern.compile("(?i)(.+)E(.+)");


    public Double convertToNumeric(String rawText) throws ParameterException {
        if (rawText == null || rawText.isBlank()) {
            throw new ParameterException("value can't be NULL.");
        }
        String value;
        value = filterString(rawText);
        value = removesMultiplesDecimalSeparatorBesidesTheFirst(value);
        value = attempsToFixExponentialOperatorExportedForSomePrograms(value);
        return convertStringToNumeric(value);
    }


    private String filterString(String rawText) {
        return rawText.toUpperCase()
                      .replaceAll("\\s", "")
                      .replace(",",".")
                      .replace("10^", "E")
                      .replaceAll("[^E\\d.\\-\\+]", "");
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
            else if (sb.charAt(i) == 'E' || sb.charAt(i) == '-' || sb.charAt(i) == '+') {
                break;
            } 
            
        }
        return sb.toString();
    }


    /** This methods fixes the numeric values using Exponential (10^x) 
     *  Some programs exports numeric without the E (123+5), 
     *  Others programs uses the E(123e+5)                             */
    private String attempsToFixExponentialOperatorExportedForSomePrograms(String value) {
        if (!value.contains("-") && !value.contains("+")) {
            return value;
        }
        for (int i=1; i<value.length()-1; i++) {
            if (value.charAt(i) == '-' || value.charAt(i) == '+') {
                if (value.charAt(i-1) != 'E') {
                    return value.substring(0, i) + "E" + value.substring(i);
                }
            }
        }
        return value;
    }

    
    private Double convertStringToNumeric(String value) {
        try {
            if (!value.contains("E")) {
                return Double.parseDouble(value);
            }
            return handleExponentialValues(value);
        } catch (NumberFormatException e) {
            throw new ParameterException("value must be Numeric.");
        }
    }


    private Double handleExponentialValues(String value) {
        Matcher matcher = pattern.matcher(value);
        matcher.find();
        String coefficient = matcher.group(1);
        String exponential = matcher.group(2);
        if (exponential.contains(".")) {
            return Double.parseDouble(coefficient) * Math.pow(10, Double.parseDouble(exponential));
        }
        return Double.parseDouble(value);
    }


    private static void setDotAsDefaultDecimalSeparator() {
        Locale.setDefault(new Locale("en", "US"));
    }

}
