/**
 * MediaUnavailableException is an exception class representing an error that occurs when a media object is unavailable.
 * It extends from the Exception class.
 */
public class MediaUnavailableException extends Exception {
    /**
     * Constructor for creating a MediaUnavailableException object with specified error message.
     * @param message The error message for the MediaUnavailableException object.
     */
    public MediaUnavailableException(String message) {
        super(message);
    }
}
