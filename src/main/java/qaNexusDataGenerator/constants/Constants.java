package qaNexusDataGenerator.constants;

/**
 * Contains constant values used for data generation in the QA Nexus Data Generator application.
 * <p>
 * This class provides static final constants that are used for generating alphanumeric strings, default domain values,
 * and specifying default lengths for strings and email usernames.
 * </p>
 */
public class Constants {

    /**
     * A string containing all alphanumeric characters and a hyphen.
     * <p>
     * This constant is used for generating random alphanumeric strings, including both uppercase and lowercase letters,
     * digits, and the hyphen character.
     * </p>
     */
    public static final String ALPHA_NUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";

    /**
     * The default domain used for generating email addresses.
     * <p>
     * This constant provides a default domain suffix to be appended to generated email usernames.
     * </p>
     */
    public static final String DEFAULT_DOMAIN = "@defaultDomain.com";

    /**
     * The default length for generated strings.
     * <p>
     * This constant specifies the default number of characters in generated strings when no specific length is provided.
     * </p>
     */
    public static final int DEFAULT_STRING_LENGTH = 10;

    /**
     * The default length for generated email usernames.
     * <p>
     * This constant specifies the default number of characters for the username part of generated email addresses.
     * </p>
     */
    public static final int DEFAULT_EMAIL_USERNAME_LENGTH = 10;
}
