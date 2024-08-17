package qaNexusAssertion.utils;

import qaNexusAssertion.constants.Constants;

public class AssertionException extends RuntimeException {
    public AssertionException(String message) {
        super(Constants.RED_CONSOLE_COLOR + message + Constants.DEFAULT_CONSOLE_COLOR);
    }
}
