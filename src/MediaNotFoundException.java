/**
 * MediaNotFoundException is an exception class representing an error that occurs when a media object is not found.
 * It extends from the Exception class.
 */
public class MediaNotFoundException extends Exception {
    /**
     * Constructor for creating a MediaNotFoundException object with specified error message.
     * @param message The error message for the MediaNotFoundException object.
     */
    public MediaNotFoundException(String message) {
        super(message);
    }
}
