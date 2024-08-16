package dataGenerator.enums;

public enum MonthsAbbreviationsEnums {
    JAN("Jan"),
    FEB("Feb"),
    MAR("Mar"),
    APR("Apr"),
    MAY("May"),
    JUN("Jun"),
    JUL("Jul"),
    AUG("Aug"),
    SEP("Sep"),
    OCT("Oct"),
    NOV("Nov"),
    DEC("Dec");

    private final String abbreviation;

    MonthsAbbreviationsEnums(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
