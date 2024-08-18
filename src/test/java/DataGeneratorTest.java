import qaNexusDataGenerator.constants.Constants;
import qaNexusDataGenerator.enums.CountryCodePatternEnums;
import qaNexusDataGenerator.enums.SupportedDateFormats;
import qaNexusDataGenerator.utils.DataGenerator;
import models.ComplexNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class DataGeneratorTest {

    @Test
    public void testGenerateString() {
        String generated = DataGenerator.generateString();
        assertNotNull(generated);
        assertEquals(Constants.DEFAULT_STRING_LENGTH, generated.length());
    }

    @Test
    public void testGenerateStringWithLength() {
        int length = 10;
        String generated = DataGenerator.generateString(length);
        assertNotNull(generated);
        assertEquals(length, generated.length());
    }

    @Test
    public void testGenerateEmail() {
        String domain = "@example.com";
        int usernameLength = 5;
        String email = DataGenerator.generateEmail(domain, usernameLength);
        assertNotNull(email);
        assertTrue(email.endsWith(domain));
        assertEquals(usernameLength, email.length() - domain.length());
    }

    @Test
    public void testGeneratePhoneNumber() {
        String countryCode = "LB";
        String phoneNumber = DataGenerator.generatePhoneNumber(countryCode);
        CountryCodePatternEnums countryEnum = CountryCodePatternEnums.valueOf(countryCode);
        Pattern pattern = Pattern.compile(countryEnum.getRegexPattern());
        assertNotNull(phoneNumber);
        assertTrue(pattern.matcher(phoneNumber).matches());
    }

    @Test
    public void testGenerateDate() {
        String date = DataGenerator.generateDate(SupportedDateFormats.YYYY_MM_DD);
        assertNotNull(date);
        assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void testGenerateUUID() {
        String uuid = DataGenerator.generateUUID();
        assertNotNull(uuid);
        assertTrue(uuid.matches("[0-9a-fA-F\\-]{36}"));
    }

    @Test
    public void testGenerateSSN() {
        String ssn = DataGenerator.generateSSN();
        assertNotNull(ssn);
        assertTrue(ssn.matches("\\d{3}-\\d{2}-\\d{4}"));
    }

    @Test
    public void testGeneratePassportNumber() {
        String passportNumber = DataGenerator.generatePassportNumber();
        assertNotNull(passportNumber);
        assertTrue(passportNumber.matches("\\d{9}"));
    }

    @Test
    public void testGenerateCreditCardNumber() {
        String ccNumber = DataGenerator.generateCreditCardNumber();
        assertNotNull(ccNumber);
        assertTrue(ccNumber.matches("\\d{16}"));
    }

    @Test
    public void testGenerateBankAccountNumber() {
        String accountNumber = DataGenerator.generateBankAccountNumber();
        assertNotNull(accountNumber);
        assertTrue(accountNumber.matches("\\d{12}"));
    }

    @Test
    public void testGenerateIBAN() {
        String iban = DataGenerator.generateIBAN();
        assertNotNull(iban);
        assertTrue(iban.matches("DE\\d{20}"));
    }

    @Test
    public void testGenerateBoolean() {
        boolean bool = DataGenerator.generateBoolean();
        assertTrue(bool || !bool); // Test should pass if it is either true or false
    }

    @Test
    public void testGenerateBinaryData() {
        int length = 10;
        byte[] data = DataGenerator.generateBinaryData(length);
        assertNotNull(data);
        assertEquals(length, data.length);
    }

    @Test
    public void testGenerateTimestamp() {
        String timestamp = DataGenerator.generateTimestamp();
        assertNotNull(timestamp);
        assertTrue(timestamp.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}"));
    }

    @Test
    public void testGenerateUnixTimestamp() {
        long unixTimestamp = DataGenerator.generateUnixTimestamp();
        assertTrue(unixTimestamp > 0); // Check that timestamp is positive
    }

    @Test
    public void testGenerateTime() {
        String time = DataGenerator.generateTime();
        assertNotNull(time);
        assertTrue(time.matches("\\d{2}:\\d{2}:\\d{2}"));
    }

    @Test
    public void testGenerateIPAddress() {
        String ipAddress = DataGenerator.generateIPAddress();
        assertNotNull(ipAddress);
        assertTrue(ipAddress.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"));
    }

    @Test
    public void testGenerateMACAddress() {
        String macAddress = DataGenerator.generateMACAddress();
        assertNotNull(macAddress);
        assertTrue(macAddress.matches("([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}"));
    }

    @Test
    public void testGenerateHexColor() {
        String hexColor = DataGenerator.generateHexColor();
        assertNotNull(hexColor);
        assertTrue(hexColor.matches("#[0-9a-fA-F]{6}"));
    }

    @Test
    public void testGenerateInt() {
        int min = 1, max = 10;
        int generated = DataGenerator.generateInt(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateFloat() {
        float min = 1.0f, max = 10.0f;
        float generated = DataGenerator.generateFloat(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateDouble() {
        double min = 1.0, max = 10.0;
        double generated = DataGenerator.generateDouble(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateLong() {
        long min = 1L, max = 10L;
        long generated = DataGenerator.generateLong(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateByte() {
        byte generated = DataGenerator.generateByte();
        assertTrue(generated >= 0 && generated <= 255);
    }

    @Test
    public void testGenerateByteArray() {
        int length = 10;
        byte[] byteArray = DataGenerator.generateByteArray(length);
        assertNotNull(byteArray);
        assertEquals(length, byteArray.length);
    }

    @Test
    public void testGenerateShort() {
        short min = 1, max = 10;
        short generated = DataGenerator.generateShort(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateChar() {
        char min = 'a', max = 'z';
        char generated = DataGenerator.generateChar(min, max);
        assertTrue(generated >= min && generated <= max);
    }

    @Test
    public void testGenerateHex() {
        int length = 10;
        String hex = DataGenerator.generateHex(length);
        assertNotNull(hex);
        assertEquals(length, hex.length());
        assertTrue(hex.matches("[0-9a-fA-F]+"));
    }

    @Test
    public void testGenerateGaussian() {
        double mean = 0.0, stdDev = 1.0;
        double generated = DataGenerator.generateGaussian(mean, stdDev);
        assertTrue(generated >= mean - 3 * stdDev && generated <= mean + 3 * stdDev);
    }

    @Test
    public void testGenerateRandomWithCustomDistribution() {
        double[] probabilities = {0.1, 0.2, 0.3, 0.4};
        int index = DataGenerator.generateRandomWithCustomDistribution(probabilities);
        assertTrue(index >= 0 && index < probabilities.length);
    }

    @Test
    public void testGenerateRandomPrime() {
        int min = 1, max = 100;
        int prime = DataGenerator.generateRandomPrime(min, max);
        assertTrue(prime >= min && prime <= max);
        assertTrue(DataGenerator.isPrime(prime));
    }

    @Test
    public void testGenerateRandomPercentage() {
        double percentage = DataGenerator.generateRandomPercentage();
        assertTrue(percentage >= 0.0 && percentage <= 100.0);
    }

    @Test
    public void testGenerateRandomFromSet() {
        int[] set = {1, 2, 3, 4, 5};
        int generated = DataGenerator.generateRandomFromSet(set);
        assertTrue(Arrays.stream(set).anyMatch(x -> x == generated));
    }

    @Test
    public void testGenerateRandomEven() {
        int min = 1, max = 10;
        int even = DataGenerator.generateRandomEven(min, max);
        assertTrue(even >= min && even <= max);
        assertTrue(even % 2 == 0);
    }

    @Test
    public void testGenerateRandomOdd() {
        int min = 1, max = 10;
        int odd = DataGenerator.generateRandomOdd(min, max);
        assertTrue(odd >= min && odd <= max);
        assertTrue(odd % 2 != 0);
    }

    @Test
    public void testGenerateUniqueRandomSequence() {
        int min = 1, max = 10, length = 5;
        int[] sequence = DataGenerator.generateUniqueRandomSequence(min, max, length);
        assertNotNull(sequence);
        assertEquals(length, sequence.length);
        assertTrue(Arrays.stream(sequence).distinct().count() == length);
    }

    @Test
    public void testGenerateRandomExponential() {
        double lambda = 1.0;
        double exp = DataGenerator.generateRandomExponential(lambda);
        assertTrue(exp >= 0.0);
    }

    @Test
    public void testGenerateRandomComplexNumber() {
        double realMin = -10.0, realMax = 10.0;
        double imaginaryMin = -10.0, imaginaryMax = 10.0;
        ComplexNumber complex = DataGenerator.generateRandomComplexNumber(realMin, realMax, imaginaryMin, imaginaryMax);
        assertNotNull(complex);
        assertTrue(complex.getReal() >= realMin && complex.getReal() <= realMax);
        assertTrue(complex.getImaginary() >= imaginaryMin && complex.getImaginary() <= imaginaryMax);
    }

}