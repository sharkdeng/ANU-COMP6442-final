/**
 * invalid student id
 */
public class InvalidIDException extends Exception {
    InvalidIDException(String errorMsg) {
        super(errorMsg);
    }
}