package dataGenerator.utils.model;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class PropertySchemaModel {
    private String type;
    private String format;
    private Pattern pattern;
    private Object[] enumValues;  // Considering any[] as a generic type for enum
    private boolean useDefault;
    private Object defaultValue;
    private Integer minimum;
    private Integer maximum;
    private Integer minLength;
    private Integer maxLength;
    private Integer length;
    private List<PropertySchemaModel> items;
    private String countryCode;
    private List<String> required;
    private Map<String, PropertySchemaModel> properties;

    // Constructor
    public PropertySchemaModel(String type, String format, Pattern pattern, Object[] enumValues,
                          boolean useDefault, Object defaultValue, Integer minimum, Integer maximum,
                          Integer minLength, Integer maxLength, Integer length, List<PropertySchemaModel> items,
                          String countryCode, List<String> required, Map<String, PropertySchemaModel> properties) {
        this.type = type;
        this.format = format;
        this.pattern = pattern;
        this.enumValues = enumValues;
        this.useDefault = useDefault;
        this.defaultValue = defaultValue;
        this.minimum = minimum;
        this.maximum = maximum;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.length = length;
        this.items = items;
        this.countryCode = countryCode;
        this.required = required;
        this.properties = properties;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Object[] getEnumValues() {
        return enumValues;
    }

    public void setEnumValues(Object[] enumValues) {
        this.enumValues = enumValues;
    }

    public boolean isUseDefault() {
        return useDefault;
    }

    public void setUseDefault(boolean useDefault) {
        this.useDefault = useDefault;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<PropertySchemaModel> getItems() {
        return items;
    }

    public void setItems(List<PropertySchemaModel> items) {
        this.items = items;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public Map<String, PropertySchemaModel> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, PropertySchemaModel> properties) {
        this.properties = properties;
    }
}
