
import org.junit.jupiter.api.Test;
import qaNexusAssertion.utils.AssertionException;
import qaNexusAssertion.utils.AssertionHelpers;

import java.util.*;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.*;

public class AssertionHelpersTest {

    @Test
    void assertIsNumberTest() {
        AssertionHelpers.assertIsNumber(5, "Should be a number");
        AssertionHelpers.assertIsNumber(3.14, "Should be a number");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNumber("string", "Should not be a number"));
    }

    @Test
    void assertIsNotNumberTest() {
        AssertionHelpers.assertIsNotNumber("string", "Should not be a number");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNotNumber(5, "Should be a number"));
    }

    @Test
    void assertEqualsTest() {
        AssertionHelpers.assertEquals("test", "test", "Values should be equal");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertEquals("test", "different", "Values should be equal"));
    }

    @Test
    void assertDeepEqualsTest() {
        AssertionHelpers.assertDeepEquals("test", "test", "Values should be deeply equal");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertDeepEquals("test", "different", "Values should be deeply equal"));
    }

    @Test
    void assertIsTrueTest() {
        AssertionHelpers.assertIsTrue(true, "Condition should be true");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsTrue(false, "Condition should be true"));
    }

    @Test
    void assertIsFalseTest() {
        AssertionHelpers.assertIsFalse(false, "Condition should be false");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsFalse(true, "Condition should be false"));
    }

    @Test
    void assertThrowsTest() {
        AssertionHelpers.assertThrows(() -> {
            throw new RuntimeException("Error");
        }, "Should throw an exception");

        assertThrows(AssertionException.class, () -> AssertionHelpers.assertThrows(() -> {
            // No exception thrown
        }, "Should throw an exception"));
    }

    @Test
    void assertIsTypeOfTest() {
        AssertionHelpers.assertIsTypeOf(String.class, "test", "Object should be of type String");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsTypeOf(Integer.class, "test", "Object should be of type Integer"));
    }

    @Test
    void assertInRangeTest() {
        AssertionHelpers.assertInRange(5, 1, 10, "Value should be in range");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertInRange(15, 1, 10, "Value should be in range"));
    }

    @Test
    void assertInRangeIncludedTest() {
        AssertionHelpers.assertInRangeIncluded(5, 5, 10, "Value should be in range [inclusive]");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertInRangeIncluded(15, 5, 10, "Value should be in range [inclusive]"));
    }

    @Test
    void assertCollectionContainsTest() {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        AssertionHelpers.assertCollectionContains(collection, "b", "Collection should contain element");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCollectionContains(collection, "d", "Collection should contain element"));
    }

    @Test
    void assertSubsetOfTest() {
        Collection<String> subset = Arrays.asList("a");
        Collection<String> superset = Arrays.asList("a", "b", "c");
        AssertionHelpers.assertSubsetOf(subset, superset, "Subset should be contained in superset");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertSubsetOf(Arrays.asList("d"), superset, "Subset should be contained in superset"));
    }

    @Test
    void assertDisjointTest() {
        Collection<String> collection1 = Arrays.asList("a", "b");
        Collection<String> collection2 = Arrays.asList("c", "d");
        AssertionHelpers.assertDisjoint(collection1, collection2, "Collections should be disjoint");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertDisjoint(Arrays.asList("a"), Arrays.asList("a", "b"), "Collections should be disjoint"));
    }

    @Test
    void assertIsNullTest() {
        AssertionHelpers.assertIsNull(null, "Should be null");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNull("value", "Should be null"));
    }

    @Test
    void assertIsNotNullTest() {
        AssertionHelpers.assertIsNotNullOrUndefined("value", "Should not be null");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNotNullOrUndefined(null, "Should not be null"));
    }

    @Test
    void assertObjectHasPropertyTest() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        AssertionHelpers.assertObjectHasProperty(map, "key", "Map should have property");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectHasProperty(map, "missingKey", "Map should have property"));

        TestClass testClass = new TestClass();
        AssertionHelpers.assertObjectHasProperty(testClass, "field", "Class should have property");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectHasProperty(testClass, "missingField", "Class should have property"));
    }

    @Test
    void assertHasPropertyValueTest() throws NoSuchFieldException, IllegalAccessException {
        TestClass testClass = new TestClass();

        // Valid case
        AssertionHelpers.assertHasPropertyValue(testClass, "field", "value", "Property value should match");

        // Invalid case
        assertThrows(AssertionException.class,
                () -> AssertionHelpers.assertHasPropertyValue(testClass, "field", "wrongValue", "Property value should match"));
    }

    @Test
    void assertEmptyObjectTest() {
        AssertionHelpers.assertEmptyObject(new HashMap<>(), "Map should be empty");
        AssertionHelpers.assertEmptyObject(new ArrayList<>(), "Collection should be empty");
        AssertionHelpers.assertEmptyObject("", "String should be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertEmptyObject(Collections.singletonMap("key", "value"), "Map should be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertEmptyObject(Collections.singletonList("item"), "Collection should be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertEmptyObject("nonEmpty", "String should be empty"));
    }

    @Test
    void assertGreaterThanTest() {
        AssertionHelpers.assertGreaterThan(10, 5, "Value should be greater than reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertGreaterThan(5, 10, "Value should be greater than reference"));
    }

    @Test
    void assertGreaterThanOrEqualTest() {
        AssertionHelpers.assertGreaterThanOrEqual(10, 10, "Value should be greater than or equal to reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertGreaterThanOrEqual(5, 10, "Value should be greater than or equal to reference"));
    }

    @Test
    void assertLessThanTest() {
        AssertionHelpers.assertLessThan(5, 10, "Value should be less than reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertLessThan(10, 5, "Value should be less than reference"));
    }

    @Test
    void assertLessThanOrEqualTest() {
        AssertionHelpers.assertLessThanOrEqual(5, 10, "Value should be less than or equal to reference");
        AssertionHelpers.assertLessThanOrEqual(10, 10, "Value should be less than or equal to reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertLessThanOrEqual(15, 10, "Value should be less than or equal to reference"));
    }

    @Test
    void assertObjectHasKeysTest() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        AssertionHelpers.assertObjectHasKeys(map, Arrays.asList("key1", "key2"), "Map should have keys");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectHasKeys(map, Arrays.asList("key1", "missingKey"), "Map should have keys"));
    }

    @Test
    void assertIsCollectionEmptyTest() {
        AssertionHelpers.assertIsCollectionEmpty(new ArrayList<>(), "Collection should be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsCollectionEmpty(Arrays.asList("item"), "Collection should be empty"));
    }

    @Test
    void assertCollectionIsNotEmptyTest() {
        AssertionHelpers.assertCollectionIsNotEmpty(Arrays.asList("item"), "Collection should not be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCollectionIsNotEmpty(new ArrayList<>(), "Collection should not be empty"));
    }

    @Test
    void assertCollectionLengthTest() {
        AssertionHelpers.assertCollectionLength(Arrays.asList("a", "b", "c"), 3, "Collection length should match");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCollectionLength(Arrays.asList("a", "b"), 3, "Collection length should match"));
    }

    @Test
    void assertStringLengthTest() {
        AssertionHelpers.assertStringLength("test", 4, "String length should match");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringLength("test", 5, "String length should match"));
    }

    @Test
    void assertStringContainsTest() {
        AssertionHelpers.assertStringContains("hello world", "world", "String should contain substring");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringContains("hello world", "foo", "String should contain substring"));
    }

    @Test
    void assertStringStartsWithTest() {
        AssertionHelpers.assertStringStartsWith("hello world", "hello", "String should start with prefix");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringStartsWith("hello world", "world", "String should start with prefix"));
    }

    @Test
    void assertStringEndsWithTest() {
        AssertionHelpers.assertStringEndsWith("hello world", "world", "String should end with suffix");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringEndsWith("hello world", "hello", "String should end with suffix"));
    }

    @Test
    void assertStringMatchesRegexTest() {
        AssertionHelpers.assertStringMatchesRegex("12345", "\\d+", "String should match regex");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringMatchesRegex("abc", "\\d+", "String should match regex"));
    }

    @Test
    void assertStringNotMatchesRegexTest() {
        AssertionHelpers.assertStringNotMatchesRegex("abc", "\\d+", "String should not match regex");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringNotMatchesRegex("12345", "\\d+", "String should not match regex"));
    }

    @Test
    void assertInstanceOfTest() {
        AssertionHelpers.assertInstanceOf(String.class, "test", "Object should be instance of String");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertInstanceOf(Integer.class, "test", "Object should be instance of Integer"));
    }

    @Test
    void assertDateTest() {
        AssertionHelpers.assertDate(new Date(), "Object should be a Date");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertDate("not a date", "Object should be a Date"));
    }

    @Test
    void assertDateFormatTest() {
        AssertionHelpers.assertDateFormat("2024-08-16", "yyyy-MM-dd", "Date should match format");
        assertThrows(AssertionException.class,
                () -> AssertionHelpers.assertDateFormat("2024-16-08", "yyyy-MM-dd", "Date should match format"));
    }

    @Test
    void assertIsFunctionTest() {
        // Valid: lambda expression is used where Runnable (a functional interface) is expected
        AssertionHelpers.assertIsFunction((Runnable) () -> {}, "Object should be a function");

        // Invalid: String is not a functional interface
        assertThrows(AssertionException.class,
                () -> AssertionHelpers.assertIsFunction("not a function", "Object should be a function"));
    }

    @Test
    void assertNotDeepIncludeTest() {
        Collection<String> collection = Arrays.asList("a", "b", "c");
        AssertionHelpers.assertNotDeepInclude(collection, "d", "Collection should not deeply include element");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertNotDeepInclude(collection, "b", "Collection should not deeply include element"));
    }

    @Test
    void assertNestedIncludeTest() {
        Collection<Collection<String>> collection = Arrays.asList(Arrays.asList("a"), Arrays.asList("b", "c"));
        AssertionHelpers.assertNestedInclude(collection, "b", "Collection should include nested element");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertNestedInclude(collection, "d", "Collection should include nested element"));
    }

    @Test
    void assertNotNestedIncludeTest() {
        Collection<Collection<String>> collection = Arrays.asList(Arrays.asList("a"), Arrays.asList("b", "c"));
        AssertionHelpers.assertNotNestedInclude(collection, "d", "Collection should not include nested element");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertNotNestedInclude(collection, "b", "Collection should not include nested element"));
    }

    @Test
    void assertCloseToTest() {
        AssertionHelpers.assertCloseTo(5.0, 5.0, 0.1, "Values should be close");

        // This will throw an AssertionException because the delta is too small
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCloseTo(5.0, 5.1, 0.01, "Values should be close"));
    }

    @Test
    void assertCollectionsSameMembersTest() {
        Collection<String> collection1 = Arrays.asList("a", "b", "c");
        Collection<String> collection2 = Arrays.asList("c", "b", "a");
        AssertionHelpers.assertCollectionsSameMembers(collection1, collection2, "Collections should have same members");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCollectionsSameMembers(collection1, Arrays.asList("a", "b"), "Collections should have same members"));
    }

    @Test
    void assertCollectionNotSameMembersTest() {
        Collection<String> collection1 = Arrays.asList("a", "b", "c");
        Collection<String> collection2 = Arrays.asList("a", "b");
        AssertionHelpers.assertCollectionNotSameMembers(collection1, collection2, "Collections should not have same members");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertCollectionNotSameMembers(collection1, Arrays.asList("a", "b", "c"), "Collections should not have same members"));
    }

    @Test
    void assertIsIncrementOfTest() {
        AssertionHelpers.assertIsIncrementOf(2, 1, "Value should be increment of reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsIncrementOf(3, 1, "Value should be increment of reference"));
    }

    @Test
    void assertNotIncrementOfTest() {
        AssertionHelpers.assertNotIncrementOf(3, 1, "Value should not be increment of reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertNotIncrementOf(2, 1, "Value should not be increment of reference"));
    }

    @Test
    void assertIsDecrementOfTest() {
        AssertionHelpers.assertIsDecrementOf(1, 2, "Value should be decrement of reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsDecrementOf(3, 2, "Value should be decrement of reference"));
    }

    @Test
    void assertIsNotDecrementOfTest() {
        AssertionHelpers.assertIsNotDecrementOf(3, 2, "Value should not be decrement of reference");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNotDecrementOf(1, 2, "Value should not be decrement of reference"));
    }

    @Test
    void assertIsArrayTest() {
        AssertionHelpers.assertIsArray(new String[]{}, "Object should be an array");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsArray("not an array", "Object should be an array"));
    }

    @Test
    void assertIsNotArrayTest() {
        AssertionHelpers.assertIsNotArray("not an array", "Object should not be an array");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertIsNotArray(new String[]{}, "Object should not be an array"));
    }

    @Test
    void assertArrayLengthTest() {
        AssertionHelpers.assertArrayLength(new Integer[]{1, 2, 3}, 3, "Array length should match");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertArrayLength(new Integer[]{1, 2}, 3, "Array length should match"));
    }

    @Test
    void assertObjectIsEmptyTest() {
        AssertionHelpers.assertObjectIsEmpty(new HashMap<>(), "Object should be empty");
        AssertionHelpers.assertObjectIsEmpty(new ArrayList<>(), "Object should be empty");
        AssertionHelpers.assertObjectIsEmpty("", "Object should be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsEmpty(Collections.singletonMap("key", "value"), "Object should be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsEmpty(Collections.singletonList("item"), "Object should be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsEmpty("nonEmpty", "Object should be empty"));
    }

    @Test
    void assertObjectIsNotEmptyTest() {
        AssertionHelpers.assertObjectIsNotEmpty(Collections.singletonMap("key", "value"), "Object should not be empty");
        AssertionHelpers.assertObjectIsNotEmpty(Collections.singletonList("item"), "Object should not be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsNotEmpty(new HashMap<>(), "Object should not be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsNotEmpty(new ArrayList<>(), "Object should not be empty"));
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIsNotEmpty("", "Object should not be empty"));
    }

    @Test
    void assertObjectIncludesTest() {
        AssertionHelpers.assertObjectIncludes(Collections.singletonMap("key", "value"), "value", "Object should include value");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertObjectIncludes(Collections.singletonMap("key", "other"), "value", "Object should include value"));
    }

    @Test
    void assertStringIsEmptyTest() {
        AssertionHelpers.assertStringIsEmpty("", "String should be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringIsEmpty("not empty", "String should be empty"));
    }

    @Test
    void assertStringIsNotEmptyTest() {
        AssertionHelpers.assertStringIsNotEmpty("not empty", "String should not be empty");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringIsNotEmpty("", "String should not be empty"));
    }

    @Test
    void assertStringMatchesPatternTest() {
        AssertionHelpers.assertStringMatchesPattern("abc123", Pattern.compile("\\w+"), "String should match pattern");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertStringMatchesPattern("abc123", Pattern.compile("\\d+"), "String should match pattern"));
    }

    @Test
    void assertFunctionThrowsTest() {
        AssertionHelpers.assertFunctionThrows(() -> { throw new IllegalArgumentException(); }, IllegalArgumentException.class, "Function should throw exception");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertFunctionThrows(() -> { throw new IllegalStateException(); }, IllegalArgumentException.class, "Function should throw exception"));
    }

    @Test
    void assertFunctionDoesNotThrowTest() {
        AssertionHelpers.assertFunctionDoesNotThrow(() -> {}, "Function should not throw exception");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertFunctionDoesNotThrow(() -> { throw new IllegalArgumentException(); }, "Function should not throw exception"));
    }

    @Test
    void assertFunctionReturnsTest() {
        AssertionHelpers.assertFunctionReturns("expected", () -> "expected", "Function should return expected value");
        assertThrows(AssertionException.class, () -> AssertionHelpers.assertFunctionReturns("expected", () -> "actual", "Function should return expected value"));
    }
}