package net.etfbl.ip.helper;

/**
 *
 * @author lx.ds
 */
public class PropertiesException extends RuntimeException {
    
    public PropertiesException() {
        super();
    }
    
    public PropertiesException(String pattern, Object... args) {
        this(String.format(pattern, args));
    }
    public PropertiesException(String message) {
        super(message);
    }
    
    public PropertiesException(Throwable cause, String pattern, Object... args) {
        this(String.format(pattern, args), cause);
    }
    public PropertiesException(Throwable cause, String message) {
        super(message, cause);
    }
    
    public PropertiesException(Throwable cause) {
        super(cause);
    }
}
