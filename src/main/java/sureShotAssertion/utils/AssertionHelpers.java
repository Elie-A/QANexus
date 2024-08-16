package sureShotAssertion.utils;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * Utility class providing static assertion methods for verifying conditions in tests.
 * These assertions can be used to validate various conditions such as object types,
 * collection contents, string properties, numerical ranges, and more.
 * <p>
 * This class contains methods to check whether conditions are true, objects are of certain types,
 * and other common assertions used in testing scenarios.
 * </p>
 */
public class AssertionHelpers {

    /**
     * Asserts that the given object is an instance of {@link Number}.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not an instance of {@link Number}.
     */
    public static void assertIsNumber(Object obj, String message) {
        if (!(obj instanceof Number)) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that the given object is not an instance of {@link Number}.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is an instance of {@link Number}.
     */
    public static void assertIsNotNumber(Object obj, String message) {
        if (obj instanceof Number) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that two objects are equal.
     *
     * @param expected The expected object.
     * @param actual The actual object.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the objects are not equal.
     */
    public static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null && actual == null) return;
        if (!expected.equals(actual)) {
            throw new AssertionException(message + " Expected: " + expected + ", but was: " + actual);
        }
    }

    /**
     * Asserts that two objects are deeply equal. This is a strict equality check that relies on the
     * {@link Object#equals(Object)} method.
     *
     * @param expected The expected object.
     * @param actual The actual object.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the objects are not deeply equal.
     */
    public static void assertDeepEquals(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionException(message + " Expected: " + expected + ", but was: " + actual);
        }
    }

    /**
     * Asserts that a condition is true.
     *
     * @param condition The condition to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the condition is false.
     */
    public static void assertIsTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that a condition is false.
     *
     * @param condition The condition to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the condition is true.
     */
    public static void assertIsFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that executing the given runnable throws an exception.
     *
     * @param runnable The runnable to execute.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If no exception is thrown.
     */
    public static void assertThrows(Runnable runnable, String message) {
        try {
            runnable.run();
        } catch (Throwable e) {
            return;
        }
        throw new AssertionException(message);
    }

    /**
     * Asserts that the given object is an instance of the specified class.
     *
     * @param expectedType The expected class type.
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not an instance of the expected class.
     */
    public static void assertIsTypeOf(Class<?> expectedType, Object obj, String message) {
        if (!expectedType.isInstance(obj)) {
            throw new AssertionException(message + " Expected type: " + expectedType.getName() + ", but was: " + obj.getClass().getName());
        }
    }

    /**
     * Asserts that a number is within the specified range (exclusive).
     *
     * @param value The value to check.
     * @param min The minimum value (exclusive).
     * @param max The maximum value (exclusive).
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not within the specified range.
     */
    public static void assertInRange(Number value, Number min, Number max, String message) {
        if (value.doubleValue() <= min.doubleValue() || value.doubleValue() >= max.doubleValue()) {
            throw new AssertionException(message + " Expected: " + min + " < " + value + " < " + max);
        }
    }

    /**
     * Asserts that a number is within the specified range (inclusive).
     *
     * @param value The value to check.
     * @param min The minimum value (inclusive).
     * @param max The maximum value (inclusive).
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not within the specified range.
     */
    public static void assertInRangeIncluded(Number value, Number min, Number max, String message) {
        if (value.doubleValue() < min.doubleValue() || value.doubleValue() > max.doubleValue()) {
            throw new AssertionException(message + " Expected: " + min + " <= " + value + " <= " + max);
        }
    }

    /**
     * Asserts that a collection contains the specified element.
     *
     * @param collection The collection to check.
     * @param element The element to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collection does not contain the element.
     */
    public static void assertCollectionContains(Collection<?> collection, Object element, String message) {
        if (!collection.contains(element)) {
            throw new AssertionException(message + " Collection does not contain: " + element);
        }
    }

    /**
     * Asserts that the first collection is a subset of the second collection.
     *
     * @param subset The subset collection.
     * @param superset The superset collection.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the subset is not contained within the superset.
     */
    public static void assertSubsetOf(Collection<?> subset, Collection<?> superset, String message) {
        if (!superset.containsAll(subset)) {
            throw new AssertionException(message + " Expected subset, but was not found");
        }
    }

    /**
     * Asserts that two collections are disjoint (do not share any common elements).
     *
     * @param collection1 The first collection.
     * @param collection2 The second collection.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collections are not disjoint.
     */
    public static void assertDisjoint(Collection<?> collection1, Collection<?> collection2, String message) {
        for (Object item : collection1) {
            if (collection2.contains(item)) {
                throw new AssertionException(message + " Collections are not disjoint; common element: " + item);
            }
        }
    }

    /**
     * Asserts that an object is either null or undefined (not set).
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not null.
     */
    public static void assertIsNullOrUndefined(Object obj, String message) {
        if (obj != null) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that an object is null.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not null.
     */
    public static void assertIsNull(Object obj, String message) {
        if (obj != null) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that an object is not null.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is null.
     */
    public static void assertIsNotNullOrUndefined(Object obj, String message) {
        if (obj == null) {
            throw new AssertionException(message);
        }
    }

    /**
     * Asserts that an object has a specified property.
     * For objects of type {@link Map}, it checks the presence of the key.
     * For other objects, it checks if the object has a field with the given name.
     *
     * @param obj The object to check.
     * @param propertyName The name of the property or key.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object does not have the specified property.
     */
    public static void assertObjectHasProperty(Object obj, String propertyName, String message) {
        if (obj instanceof Map) {
            if (!((Map<?, ?>) obj).containsKey(propertyName)) {
                throw new AssertionException(message + " Object does not have property: " + propertyName);
            }
        } else {
            try {
                obj.getClass().getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                throw new AssertionException(message + " Object does not have property: " + propertyName);
            }
        }
    }

    /**
     * Asserts that an object has a specified property with a given value.
     * For objects of type {@link Map}, it checks the value associated with the key.
     * For other objects, it uses reflection to access the field value.
     *
     * @param obj The object to check.
     * @param propertyName The name of the property or key.
     * @param expectedValue The expected value of the property.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the property value does not match the expected value.
     */
    public static void assertHasPropertyValue(Object obj, String propertyName, Object expectedValue, String message) {
        Object value = null;
        if (obj instanceof Map) {
            value = ((Map<?, ?>) obj).get(propertyName);
        } else {
            try {
                value = obj.getClass().getDeclaredField(propertyName).get(obj);
            } catch (Exception e) {
                throw new AssertionException(message + " Failed to access property: " + propertyName);
            }
        }
        if (!expectedValue.equals(value)) {
            throw new AssertionException(message + " Expected value: " + expectedValue + ", but was: " + value);
        }
    }

    /**
     * Asserts that an object (map, collection, or string) is empty.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not empty.
     */
    public static void assertEmptyObject(Object obj, String message) {
        if (obj instanceof Map && !((Map<?, ?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected empty map, but was not.");
        } else if (obj instanceof Collection && !((Collection<?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected empty collection, but was not.");
        } else if (obj instanceof String && !((String) obj).isEmpty()) {
            throw new AssertionException(message + " Expected empty string, but was not.");
        }
    }

    /**
     * Asserts that a number is greater than a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not greater than the reference.
     */
    public static void assertGreaterThan(Number value, Number reference, String message) {
        if (value.doubleValue() <= reference.doubleValue()) {
            throw new AssertionException(message + " Expected: " + value + " > " + reference);
        }
    }

    /**
     * Asserts that a number is greater than or equal to a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not greater than or equal to the reference.
     */
    public static void assertGreaterThanOrEqual(Number value, Number reference, String message) {
        if (value.doubleValue() < reference.doubleValue()) {
            throw new AssertionException(message + " Expected: " + value + " >= " + reference);
        }
    }

    /**
     * Asserts that a number is less than a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not less than the reference.
     */
    public static void assertLessThan(Number value, Number reference, String message) {
        if (value.doubleValue() >= reference.doubleValue()) {
            throw new AssertionException(message + " Expected: " + value + " < " + reference);
        }
    }

    /**
     * Asserts that a number is less than or equal to a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not less than or equal to the reference.
     */
    public static void assertLessThanOrEqual(Number value, Number reference, String message) {
        if (value.doubleValue() > reference.doubleValue()) {
            throw new AssertionException(message + " Expected: " + value + " <= " + reference);
        }
    }

    /**
     * Asserts that an object (map) has all specified keys.
     *
     * @param obj The map to check.
     * @param keys The keys to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the map is missing any of the specified keys.
     */
    public static void assertObjectHasKeys(Map<?, ?> obj, Collection<?> keys, String message) {
        for (Object key : keys) {
            if (!obj.containsKey(key)) {
                throw new AssertionException(message + " Object is missing key: " + key);
            }
        }
    }

    /**
     * Asserts that a collection is empty.
     *
     * @param collection The collection to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collection is not empty.
     */
    public static void assertIsCollectionEmpty(Collection<?> collection, String message) {
        if (!collection.isEmpty()) {
            throw new AssertionException(message + " Expected empty collection, but was not.");
        }
    }

    /**
     * Asserts that a collection is not empty.
     *
     * @param collection The collection to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collection is empty.
     */
    public static void assertCollectionIsNotEmpty(Collection<?> collection, String message) {
        if (collection.isEmpty()) {
            throw new AssertionException(message + " Expected non-empty collection, but was empty.");
        }
    }

    /**
     * Asserts that a collection has a specified length.
     *
     * @param collection The collection to check.
     * @param expectedLength The expected length of the collection.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collection length does not match the expected length.
     */
    public static void assertCollectionLength(Collection<?> collection, int expectedLength, String message) {
        if (collection.size() != expectedLength) {
            throw new AssertionException(message + " Expected length: " + expectedLength + ", but was: " + collection.size());
        }
    }

    /**
     * Asserts that a string has a specified length.
     *
     * @param str The string to check.
     * @param expectedLength The expected length of the string.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string length does not match the expected length.
     */
    public static void assertStringLength(String str, int expectedLength, String message) {
        if (str.length() != expectedLength) {
            throw new AssertionException(message + " Expected length: " + expectedLength + ", but was: " + str.length());
        }
    }

    /**
     * Asserts that a string contains a specified substring.
     *
     * @param str The string to check.
     * @param substring The substring to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string does not contain the substring.
     */
    public static void assertStringContains(String str, String substring, String message) {
        if (!str.contains(substring)) {
            throw new AssertionException(message + " String does not contain: " + substring);
        }
    }

    /**
     * Asserts that a string starts with a specified prefix.
     *
     * @param str The string to check.
     * @param prefix The prefix to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string does not start with the prefix.
     */
    public static void assertStringStartsWith(String str, String prefix, String message) {
        if (!str.startsWith(prefix)) {
            throw new AssertionException(message + " String does not start with: " + prefix);
        }
    }

    /**
     * Asserts that a string ends with a specified suffix.
     *
     * @param str The string to check.
     * @param suffix The suffix to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string does not end with the suffix.
     */
    public static void assertStringEndsWith(String str, String suffix, String message) {
        if (!str.endsWith(suffix)) {
            throw new AssertionException(message + " String does not end with: " + suffix);
        }
    }

    /**
     * Asserts that a string matches a specified regular expression pattern.
     *
     * @param str The string to check.
     * @param regex The regular expression pattern.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string does not match the pattern.
     */
    public static void assertStringMatchesRegex(String str, String regex, String message) {
        if (!Pattern.matches(regex, str)) {
            throw new AssertionException(message + " String does not match pattern: " + regex);
        }
    }

    /**
     * Asserts that a string does not match a specified regular expression pattern.
     *
     * @param str The string to check.
     * @param regex The regular expression pattern.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string matches the pattern.
     */
    public static void assertStringNotMatchesRegex(String str, String regex, String message) {
        if (Pattern.matches(regex, str)) {
            throw new AssertionException(message + " String matches pattern: " + regex);
        }
    }

    /**
     * Asserts that an object is an instance of a specified class.
     *
     * @param expectedClass The expected class.
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not an instance of the expected class.
     */
    public static void assertInstanceOf(Class<?> expectedClass, Object obj, String message) {
        if (!expectedClass.isInstance(obj)) {
            throw new AssertionException(message + " Object is not an instance of: " + expectedClass.getName());
        }
    }

    /**
     * Asserts that an object is an instance of {@link java.util.Date}.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not a Date.
     */
    public static void assertDate(Object obj, String message) {
        if (!(obj instanceof java.util.Date)) {
            throw new AssertionException(message + " Object is not a Date");
        }
    }

    /**
     * Asserts that a string represents a date in a specified format.
     *
     * @param date The date string to check.
     * @param format The date format.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the date string does not match the format.
     */
    public static void assertDateFormat(String date, String format, String message) {
        try {
            new java.text.SimpleDateFormat(format).parse(date);
        } catch (Exception e) {
            throw new AssertionException(message + " Date does not match format: " + format);
        }
    }

    /**
     * Asserts that an object is a function (i.e., an instance of {@link Runnable}).
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not a function.
     */
    public static void assertIsFunction(Object obj, String message) {
        if (!(obj instanceof Runnable)) {
            throw new AssertionException(message + " Object is not a function");
        }
    }

    /**
     * Asserts that a collection does not deeply include a specified element.
     *
     * @param collection The collection to check.
     * @param element The element to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collection deeply includes the element.
     */
    public static void assertNotDeepInclude(Collection<?> collection, Object element, String message) {
        if (collection.contains(element)) {
            throw new AssertionException(message + " Collection deeply includes: " + element);
        }
    }

    /**
     * Asserts that a collection contains an element within a nested collection.
     *
     * @param collection The collection to check.
     * @param nestedElement The nested element to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the nested element is not found.
     */
    public static void assertNestedInclude(Collection<?> collection, Object nestedElement, String message) {
        for (Object element : collection) {
            if (element instanceof Collection && ((Collection<?>) element).contains(nestedElement)) {
                return;
            }
        }
        throw new AssertionException(message + " Collection does not include nested element: " + nestedElement);
    }

    /**
     * Asserts that a collection does not contain an element within a nested collection.
     *
     * @param collection The collection to check.
     * @param nestedElement The nested element to check for.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the nested element is found.
     */
    public static void assertNotNestedInclude(Collection<?> collection, Object nestedElement, String message) {
        for (Object element : collection) {
            if (element instanceof Collection && ((Collection<?>) element).contains(nestedElement)) {
                throw new AssertionException(message + " Collection includes nested element: " + nestedElement);
            }
        }
    }

    /**
     * Asserts that a number is close to a specified value within a given delta.
     *
     * @param actual The actual value.
     * @param expected The expected value.
     * @param delta The allowed difference.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not within the allowed delta.
     */
    public static void assertCloseTo(Number actual, Number expected, Number delta, String message) {
        if (Math.abs(actual.doubleValue() - expected.doubleValue()) > delta.doubleValue()) {
            throw new AssertionException(message + " Expected: " + actual + " to be close to: " + expected + " within: " + delta);
        }
    }

    /**
     * Asserts that two collections have the same members.
     *
     * @param collection1 The first collection.
     * @param collection2 The second collection.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collections do not have the same members.
     */
    public static void assertCollectionsSameMembers(Collection<?> collection1, Collection<?> collection2, String message) {
        if (!collection1.containsAll(collection2) || !collection2.containsAll(collection1)) {
            throw new AssertionException(message + " Collections do not have the same members");
        }
    }

    /**
     * Asserts that two collections do not have the same members.
     *
     * @param collection1 The first collection.
     * @param collection2 The second collection.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the collections have the same members.
     */
    public static void assertCollectionNotSameMembers(Collection<?> collection1, Collection<?> collection2, String message) {
        if (collection1.containsAll(collection2) && collection2.containsAll(collection1)) {
            throw new AssertionException(message + " Collections have the same members, but they should not");
        }
    }

    /**
     * Asserts that a number is an increment of a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not an increment of the reference.
     */
    public static void assertIsIncrementOf(Number value, Number reference, String message) {
        if (value.doubleValue() != reference.doubleValue() + 1) {
            throw new AssertionException(message + " Expected: " + value + " to be increment of: " + reference);
        }
    }

    /**
     * Asserts that a number is not an increment of a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is an increment of the reference.
     */
    public static void assertNotIncrementOf(Number value, Number reference, String message) {
        if (value.doubleValue() == reference.doubleValue() + 1) {
            throw new AssertionException(message + " Expected: " + value + " not to be increment of: " + reference);
        }
    }

    /**
     * Asserts that a number is a decrement of a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not a decrement of the reference.
     */
    public static void assertIsDecrementOf(Number value, Number reference, String message) {
        if (value.doubleValue() != reference.doubleValue() - 1) {
            throw new AssertionException(message + " Expected: " + value + " to be decrement of: " + reference);
        }
    }

    /**
     * Asserts that a number is not a decrement of a specified reference value.
     *
     * @param value The value to check.
     * @param reference The reference value.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is a decrement of the reference.
     */
    public static void assertNotDecrementOf(Number value, Number reference, String message) {
        if (value.doubleValue() == reference.doubleValue() - 1) {
            throw new AssertionException(message + " Expected: " + value + " not to be decrement of: " + reference);
        }
    }

    /**
     * Asserts that a number is zero.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not zero.
     */
    public static void assertZero(Number value, String message) {
        if (value.doubleValue() != 0) {
            throw new AssertionException(message + " Expected: " + value + " to be zero");
        }
    }

    /**
     * Asserts that a number is not zero.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is zero.
     */
    public static void assertNotZero(Number value, String message) {
        if (value.doubleValue() == 0) {
            throw new AssertionException(message + " Expected: " + value + " not to be zero");
        }
    }

    /**
     * Asserts that a number is positive.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not positive.
     */
    public static void assertPositive(Number value, String message) {
        if (value.doubleValue() <= 0) {
            throw new AssertionException(message + " Expected: " + value + " to be positive");
        }
    }

    /**
     * Asserts that a number is negative.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not negative.
     */
    public static void assertNegative(Number value, String message) {
        if (value.doubleValue() >= 0) {
            throw new AssertionException(message + " Expected: " + value + " to be negative");
        }
    }

    /**
     * Asserts that a number is odd.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not odd.
     */
    public static void assertOdd(Number value, String message) {
        if (value.intValue() % 2 != 1) {
            throw new AssertionException(message + " Expected: " + value + " to be odd");
        }
    }

    /**
     * Asserts that a number is even.
     *
     * @param value The value to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the value is not even.
     */
    public static void assertEven(Number value, String message) {
        if (value.intValue() % 2 != 0) {
            throw new AssertionException(message + " Expected: " + value + " to be even");
        }
    }

    /**
     * Asserts that a string represents a valid URL.
     *
     * @param str The string to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string is not a valid URL.
     */
    public static void assertValidUrl(String str, String message) {
        try {
            new java.net.URL(str);
        } catch (Exception e) {
            throw new AssertionException(message + " String is not a valid URL");
        }
    }

    /**
     * Asserts that a string represents a valid email address.
     *
     * @param email The email address to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the email address is not valid.
     */
    public static void assertValidEmail(String email, String message) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!Pattern.matches(regex, email)) {
            throw new AssertionException(message + " Email address is not valid");
        }
    }

    /**
     * Asserts that the provided object is an array.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not an array.
     */
    public static void assertIsArray(Object obj, String message) {
        if (!obj.getClass().isArray()) {
            throw new AssertionException(message + " Object is not an array");
        }
    }

    /**
     * Asserts that the provided object is not an array.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is an array.
     */
    public static void assertIsNotArray(Object obj, String message) {
        if (obj.getClass().isArray()) {
            throw new AssertionException(message + " Object is an array, but should not be");
        }
    }

    /**
     * Asserts that the provided array has the specified length.
     *
     * @param array The array to check.
     * @param expectedLength The expected length of the array.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the array's length does not match the expected length.
     */
    public static void assertArrayLength(Object[] array, int expectedLength, String message) {
        if (array.length != expectedLength) {
            throw new AssertionException(message + " Expected array length: " + expectedLength + ", but was: " + array.length);
        }
    }

    /**
     * Asserts that the provided object is empty. The object must be either a {@link Map} or a {@link Collection}.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is not empty.
     */
    public static void assertObjectIsEmpty(Object obj, String message) {
        if (obj instanceof Map && !((Map<?, ?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected empty map, but was not.");
        } else if (obj instanceof Collection && !((Collection<?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected empty collection, but was not.");
        }
    }

    /**
     * Asserts that the provided object is not empty. The object must be either a {@link Map} or a {@link Collection}.
     *
     * @param obj The object to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the object is empty.
     */
    public static void assertObjectIsNotEmpty(Object obj, String message) {
        if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected non-empty map, but was empty.");
        } else if (obj instanceof Collection && ((Collection<?>) obj).isEmpty()) {
            throw new AssertionException(message + " Expected non-empty collection, but was empty.");
        }
    }

    /**
     * Asserts that the provided map contains the specified value.
     *
     * @param obj The map to check.
     * @param value The value to look for in the map.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the map does not contain the specified value.
     */
    public static void assertObjectIncludes(Map<?, ?> obj, Object value, String message) {
        if (!obj.containsValue(value)) {
            throw new AssertionException(message + " Object does not include value: " + value);
        }
    }

    /**
     * Asserts that the provided string is empty.
     *
     * @param str The string to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string is not empty.
     */
    public static void assertStringIsEmpty(String str, String message) {
        if (!str.isEmpty()) {
            throw new AssertionException(message + " Expected empty string, but was not.");
        }
    }

    /**
     * Asserts that the provided string is not empty.
     *
     * @param str The string to check.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string is empty.
     */
    public static void assertStringIsNotEmpty(String str, String message) {
        if (str.isEmpty()) {
            throw new AssertionException(message + " Expected non-empty string, but was empty.");
        }
    }

    /**
     * Asserts that the provided string matches the specified regular expression pattern.
     *
     * @param str The string to check.
     * @param pattern The regular expression pattern to match against.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the string does not match the pattern.
     */
    public static void assertStringMatchesPattern(String str, Pattern pattern, String message) {
        if (!pattern.matcher(str).matches()) {
            throw new AssertionException(message + " String does not match pattern: " + pattern.pattern());
        }
    }

    /**
     * Asserts that the provided function (Runnable) throws an expected exception when executed.
     *
     * @param func The function to execute.
     * @param expectedException The class of the expected exception.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the function does not throw the expected exception.
     */
    public static void assertFunctionThrows(Runnable func, Class<? extends Throwable> expectedException, String message) {
        try {
            func.run();
        } catch (Throwable e) {
            if (expectedException.isInstance(e)) {
                return; // Expected exception
            } else {
                throw new AssertionException(message + " Expected exception: " + expectedException.getName() + ", but was: " + e.getClass().getName());
            }
        }
        throw new AssertionException(message + " Expected exception, but none was thrown.");
    }

    /**
     * Asserts that the provided function (Runnable) does not throw any exception when executed.
     *
     * @param func The function to execute.
     * @param message The message to include in the exception if the assertion fails.
     * @throws AssertionException If the function throws an exception.
     */
    public static void assertFunctionDoesNotThrow(Runnable func, String message) {
        try {
            func.run();
        } catch (Throwable e) {
            throw new AssertionException(message + " Expected no exception, but caught: " + e.getClass().getName());
        }
    }

    /**
     * Asserts that the provided function (Supplier) returns the expected value.
     *
     * @param expectedValue The expected return value.
     * @param func The function to execute and get the return value from.
     * @param message The message to include in the exception if the assertion fails.
     * @param <T> The type of the value returned by the function.
     * @throws AssertionException If the function's return value does not match the expected value.
     */
    public static <T> void assertFunctionReturns(T expectedValue, Supplier<T> func, String message) {
        T result = func.get();
        if (!expectedValue.equals(result)) {
            throw new AssertionException(message + " Expected return: " + expectedValue + ", but was: " + result);
        }
    }
}
