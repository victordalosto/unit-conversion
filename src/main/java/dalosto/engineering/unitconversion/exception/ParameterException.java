package dalosto.engineering.unitconversion.exception;


/**
 * Exception thrown when a Parameter is not compatible.
 */
public class ParameterException extends RuntimeException {


    public ParameterException() {
        super();
    }


    public ParameterException(String message) {
        super(message);
    }


    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }


    public ParameterException(Throwable cause) {
        super(cause);
    }


    protected ParameterException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
