package sureShotAssertion.utils;

import sureShotAssertion.constants.Constants;

public class AssertionException extends RuntimeException {
    public AssertionException(String message) {
        super(Constants.RED_CONSOLE_COLOR + message + Constants.DEFAULT_CONSOLE_COLOR);
    }
}
