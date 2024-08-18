package qaNexusDataGenerator.enums;

/**
 * Enum representing the supported date formats.
 * Each enum constant corresponds to a specific date format pattern used for formatting and parsing dates.
 */
public enum SupportedDateFormats {
    /**
     * Date format pattern for "year-month-day" with hyphens.
     * Example: 2024-08-18
     */
    YYYY_MM_DD("yyyy-MM-dd"),

    /**
     * Date format pattern for "year/month/day" with slashes.
     * Example: 2024/08/18
     */
    YYYY_MM_DD_SLASH("yyyy/MM/dd"),

    /**
     * Date format pattern for "year-month-abbreviated-month-day" with hyphens.
     * Example: 2024-Aug-18
     */
    YYYY_MMM_DD("yyyy-MMM-dd"),

    /**
     * Date format pattern for "year/abbreviated-month/day" with slashes.
     * Example: 2024/Aug/18
     */
    YYYY_MMM_DD_SLASH("yyyy/MMM/dd"),

    /**
     * Date format pattern for "day-month-year" with hyphens.
     * Example: 18-08-2024
     */
    DD_MM_YYYY("dd-MM-yyyy"),

    /**
     * Date format pattern for "day-abbreviated-month-year" with hyphens.
     * Example: 18-Aug-2024
     */
    DD_MMM_YYYY("dd-MMM-yyyy"),

    /**
     * Date format pattern for "day/abbreviated-month/year" with slashes.
     * Example: 18/Aug/2024
     */
    DD_MMM_YYYY_SLASH("dd/MMM/yyyy");

    private final String dateFormat;

    /**
     * Constructs an instance of {@code SupportedDateFormats} with the specified date format pattern.
     *
     * @param dateFormat the date format pattern
     */
    SupportedDateFormats(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Returns the date format pattern associated with this enum constant.
     *
     * @return the date format pattern
     */
    public String getDateFormat() {
        return dateFormat;
    }
}
