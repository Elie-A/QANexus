package qaNexusAssertion.utils;

import qaNexusAssertion.constants.Constants;

/**
 * Custom runtime exception used for assertions in the QA Nexus application.
 * <p>
 * This exception extends {@link RuntimeException} and is intended to be thrown when an assertion fails.
 * The exception message will be formatted with a predefined color specified in {@link Constants}.
 * </p>
 */
public class AssertionException extends RuntimeException {

    /**
     * Constructs a new {@code AssertionException} with the specified detail message.
     * <p>
     * The message is prefixed and suffixed with color codes from {@link Constants} to highlight the error
     * in the console output.
     * </p>
     *
     * @param message the detail message to be used for this exception. This message is formatted with color codes.
     */
    public AssertionException(String message) {
        super(Constants.RED_CONSOLE_COLOR + message + Constants.DEFAULT_CONSOLE_COLOR);
    }
}
