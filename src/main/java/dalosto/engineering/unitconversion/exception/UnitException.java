package dalosto.engineering.unitconversion.exception;


/**
 * Exception thrown when a unit is not compatible.
 */
public class UnitException extends RuntimeException {

    public UnitException(String message) {
        super(message);
    }

}
