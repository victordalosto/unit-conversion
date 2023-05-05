package dalosto.engineering.unitconversion.exception;


/**
 * Exception thrown when a unit is not compatible.
 */
public final class UnitException extends RuntimeException {

    public UnitException(String message) {
        super(message);
    }

}
