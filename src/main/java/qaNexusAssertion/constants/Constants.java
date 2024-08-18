package qaNexusAssertion.constants;

/**
 * Contains console color codes used for formatting text output in the QA Nexus application.
 * <p>
 * This class provides static final constants for color codes that can be used to modify the appearance of text
 * in the console. The color codes are ANSI escape codes used to change text color and reset formatting.
 * </p>
 */
public class Constants {

    /**
     * ANSI escape code for resetting console color to default.
     * <p>
     * This constant is used to reset the text color to the console's default color after applying any color.
     * </p>
     */
    public static final String DEFAULT_CONSOLE_COLOR = "\u001B[0m";

    /**
     * ANSI escape code for setting console text color to red.
     * <p>
     * This constant is used to change the text color to red in the console.
     * </p>
     */
    public static final String RED_CONSOLE_COLOR = "\u001B[31m";
}