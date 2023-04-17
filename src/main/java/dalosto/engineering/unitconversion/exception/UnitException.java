package dalosto.engineering.unitconversion.exception;


/**
 * Exception thrown when a unit is not compatible.
 */
public class UnitException extends RuntimeException {


    public UnitException() {
        super();
    }


    public UnitException(String message) {
        super(message);
    }


    public UnitException(String message, Throwable cause) {
        super(message, cause);
    }


    public UnitException(Throwable cause) {
        super(cause);
    }


    protected UnitException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
