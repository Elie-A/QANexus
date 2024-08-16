package dataGenerator.enums;

public enum SupportedDateFormats {
    YYYY_MM_DD("yyyy-MM-dd"),
    YYYY_MM_DD_SLASH("yyyy/MM/dd"),
    YYYY_MMM_DD("yyyy-MMM-dd"),
    YYYY_MMM_DD_SLASH("yyyy/MMM/dd"),
    DD_MM_YYYY("dd-MM-yyyy"),
    DD_MMM_YYYY("dd-MMM-yyyy"),
    DD_MMM_YYYY_SLASH("dd/MMM/yyyy");

    private final String dateFormat;

    SupportedDateFormats(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
