package qaNexusDataGenerator.enums;

/**
 * Enum representing the abbreviations of the twelve months of the year.
 * Each enum constant corresponds to a month abbreviation commonly used in date formatting.
 */
public enum MonthsAbbreviationsEnums {
    /**
     * January abbreviation.
     */
    JAN("Jan"),

    /**
     * February abbreviation.
     */
    FEB("Feb"),

    /**
     * March abbreviation.
     */
    MAR("Mar"),

    /**
     * April abbreviation.
     */
    APR("Apr"),

    /**
     * May abbreviation.
     */
    MAY("May"),

    /**
     * June abbreviation.
     */
    JUN("Jun"),

    /**
     * July abbreviation.
     */
    JUL("Jul"),

    /**
     * August abbreviation.
     */
    AUG("Aug"),

    /**
     * September abbreviation.
     */
    SEP("Sep"),

    /**
     * October abbreviation.
     */
    OCT("Oct"),

    /**
     * November abbreviation.
     */
    NOV("Nov"),

    /**
     * December abbreviation.
     */
    DEC("Dec");

    private final String abbreviation;

    /**
     * Constructs an instance of {@code MonthsAbbreviationsEnums} with the specified abbreviation.
     *
     * @param abbreviation the abbreviation of the month
     */
    MonthsAbbreviationsEnums(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    /**
     * Returns the abbreviation of the month.
     *
     * @return the abbreviation of the month
     */
    public String getAbbreviation() {
        return abbreviation;
    }
}
