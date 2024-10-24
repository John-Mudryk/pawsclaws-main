package wcci.org.pawsclaws.DTO;

/**
 * Data Transfer Object (DTO) for handling error information.
 * This class contains details about error messages and corresponding error codes.
 * It can be extended by other DTO classes to include error-handling capabilities.
 */
public class ErrorDataDTO {
    private String errorMessage;
    private int errorCode = 0;

    /**
     * Gets the error message.
     *
     * @return The error message as a String.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage The error message to set.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage; // Assigns the provided error message
    }

    /**
     * Gets the error code.
     *
     * @return The error code as an integer.
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode The error code to set.
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode; // Assigns the provided error code
    }
}
