package qaNexusDataGenerator.utils;

import qaNexusDataGenerator.constants.Constants;
import qaNexusDataGenerator.enums.CountryCodePatternEnums;
import qaNexusDataGenerator.enums.MonthsAbbreviationsEnums;
import qaNexusDataGenerator.enums.SupportedDateFormats;
import models.ComplexNumber;

import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Pattern;

/**
 * A utility class for generating various types of random data, including strings, numbers, and dates.
 * This class uses the {@link SecureRandom} class to generate secure random values for its methods.
 * This class provides methods to generate:
 * <ul>
 *     <li>Random strings</li>
 *     <li>Emails</li>
 *     <li>Phone numbers</li>
 *     <li>Dates</li>
 *     <li>UUIDs</li>
 *     <li>Social Security Numbers (SSNs)</li>
 *     <li>Passport numbers</li>
 *     <li>Credit card numbers</li>
 *     <li>Bank account numbers</li>
 *     <li>IBANs</li>
 *     <li>Boolean values</li>
 *     <li>Binary data</li>
 *     <li>Timestamps</li>
 *     <li>IP addresses</li>
 *     <li>MAC addresses</li>
 *     <li>Hex colors</li>
 *     <li>Various numeric types (int, float, double, long, byte, short, char)</li>
 *     <li>Random sequences and distributions</li>
 *     <li>Complex numbers</li>
 *</ul>
 */
public class DataGenerator {

    /**
     * Generates a random string with the default length specified in {@link Constants#DEFAULT_STRING_LENGTH}.
     *
     * @return A randomly generated string.
     */
    public static String generateString() {
        return generateString(Constants.DEFAULT_STRING_LENGTH);
    }

    /**
     * Generates a random alphanumeric string with the specified length.
     *
     * @param length The length of the generated string.
     * @return A randomly generated alphanumeric string.
     */
    public static String generateString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder generatedValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(Constants.ALPHA_NUM.length());
            generatedValue.append(Constants.ALPHA_NUM.charAt(index));
        }

        return generatedValue.toString();
    }

    /**
     * Generates a random email address with the specified username length and given domain.
     *
     * @param domain The domain to use for the email address.
     * @param usernameLength The length of the username part of the email address.
     * @return A randomly generated email address.
     */
    public static String generateEmail(String domain, int usernameLength) {
        return generateString(usernameLength) + domain;
    }

    /**
     * Generates a random email address with the specified username length and default domain.
     *
     * @param usernameLength The length of the username part of the email address.
     * @return A randomly generated email address with the default domain.
     */
    public static String generateEmail(int usernameLength) {
        return generateString(usernameLength) + Constants.DEFAULT_DOMAIN;
    }

    /**
     * Generates a random email address with the default username length and default domain.
     *
     * @return A randomly generated email address with the default username length and domain.
     */
    public static String generateEmail() {
        return generateString(Constants.DEFAULT_EMAIL_USERNAME_LENGTH) + Constants.DEFAULT_DOMAIN;
    }

    /**
     * Generates a random phone number based on the specified country code.
     *
     * @param countryCode The country code to determine the phone number format.
     * @return A randomly generated phone number in the format corresponding to the given country code.
     * @throws IllegalArgumentException if the country code is invalid.
     */
    public static String generatePhoneNumber(String countryCode) {
        CountryCodePatternEnums countryEnum = CountryCodePatternEnums.valueOf(countryCode);

        if (countryEnum == null) {
            throw new IllegalArgumentException("Invalid country code: " + countryCode);
        } else {
            String phoneRegex = countryEnum.getRegexPattern();
            Pattern pattern = Pattern.compile(phoneRegex);
            String phoneNumber;
            do {
                phoneNumber = generateRandomPhoneNumber(pattern.pattern());
            } while (!pattern.matcher(phoneNumber).matches());
            return phoneNumber;
        }
    }

    /**
     * Generates a random phone number with the default country code ("US").
     *
     * @return A randomly generated phone number in the format for the US.
     */
    public static String generatePhoneNumber() {
        CountryCodePatternEnums countryEnum = CountryCodePatternEnums.valueOf("US");

        String phoneRegex = countryEnum.getRegexPattern();
        Pattern pattern = Pattern.compile(phoneRegex);
        String phoneNumber;
        do {
            phoneNumber = generateRandomPhoneNumber(pattern.pattern());
        } while (!pattern.matcher(phoneNumber).matches());
        return phoneNumber;
    }

    /**
     * Generates a random date based on the specified format.
     *
     * @param format The format to use for the date generation.
     * @return A randomly generated date in the specified format.
     */
    public static String generateDate(SupportedDateFormats format) {
        int year = -1;
        String month = "";
        String day = "";

        SecureRandom secureRandom = new SecureRandom();

        if (format == null) {
            Calendar currentDate = Calendar.getInstance();
            year = currentDate.get(Calendar.YEAR);
            month = String.format("%02d", currentDate.get(Calendar.MONTH) + 1);
            day = String.format("%02d", currentDate.get(Calendar.DAY_OF_MONTH));
            return String.format("%d-%s-%s", year, month, day);
        }

        String formatString = format.getDateFormat();

        if (formatString.contains("yyyy")) {
            year = 1900 + secureRandom.nextInt(100); // Random year between 1900 and 1999
        }

        if (formatString.contains("MM")) {
            int monthNum = 1 + secureRandom.nextInt(12); // Random month between 1 and 12
            month = String.format("%02d", monthNum);
        }
        if (formatString.contains("MMM")) {
            int randomIndex = secureRandom.nextInt(MonthsAbbreviationsEnums.values().length);
            month = MonthsAbbreviationsEnums.values()[randomIndex].getAbbreviation();
        }

        if (formatString.contains("dd")) {
            int maxDays;
            if (year != -1 && !month.isEmpty() && isNumeric(month)) {
                maxDays = getMaxDays(Integer.parseInt(month), year);
            } else {
                maxDays = 31;
            }
            day = String.format("%02d", 1 + secureRandom.nextInt(maxDays)); // Random day
        }

        // Replace the format string with the actual date components
        return formatString
                .replace("yyyy", String.valueOf(year))
                .replace("yy", String.valueOf(year).substring(2))
                .replace("MM", isNumeric(month) ? String.format("%02d", Integer.parseInt(month)) : month)
                .replace("MMM", month)
                .replace("dd", day);
    }

    /**
     * Generates a random date in the format "yyyy-MM-dd".
     *
     * @return A randomly generated date in the format "yyyy-MM-dd".
     */
    public static String generateDate() {
        int year = -1;
        String month = "";
        String day = "";

        SecureRandom secureRandom = new SecureRandom();

        // Default date format string: "yyyy-MM-dd"
        String formatString = "yyyy-MM-dd";

        // Generate year (random between 1900 and 1999)
        if (formatString.contains("yyyy")) {
            year = 1900 + secureRandom.nextInt(100); // Random year between 1900 and 1999
        }

        // Generate month (random between 01 and 12)
        if (formatString.contains("MM")) {
            int monthNum = 1 + secureRandom.nextInt(12); // Random month between 1 and 12
            month = String.format("%02d", monthNum);
        }

        // Generate day (random, considering the number of days in the month and year)
        if (formatString.contains("dd")) {
            int maxDays;
            if (year != -1 && !month.isEmpty() && isNumeric(month)) {
                maxDays = getMaxDays(Integer.parseInt(month), year);
            } else {
                maxDays = 31;
            }
            day = String.format("%02d", 1 + secureRandom.nextInt(maxDays)); // Random day
        }

        // Replace the format string with the actual date components
        return formatString
                .replace("yyyy", String.valueOf(year))
                .replace("MM", isNumeric(month) ? String.format("%02d", Integer.parseInt(month)) : month)
                .replace("dd", day);
    }

    /**
     * Generates a random UUID.
     *
     * @return A randomly generated UUID.
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a random Social Security Number (SSN) in the format "XXX-XX-XXXX".
     *
     * @return A randomly generated SSN.
     */
    public static String generateSSN() {
        SecureRandom secureRandom = new SecureRandom();
        return String.format("%03d-%02d-%04d",
                secureRandom.nextInt(1000),
                secureRandom.nextInt(100),
                secureRandom.nextInt(10000));
    }

    /**
     * Generates a random passport number consisting of 9 digits.
     *
     * @return A randomly generated passport number.
     */
    public static String generatePassportNumber() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder passportNumber = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            passportNumber.append(secureRandom.nextInt(10));
        }
        return passportNumber.toString();
    }

    /**
     * Generates a random credit card number, including a Luhn checksum digit.
     *
     * @return A randomly generated credit card number.
     */
    public static String generateCreditCardNumber() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder ccNumber = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            ccNumber.append(secureRandom.nextInt(10));
        }
        ccNumber.append(calculateLuhnChecksum(ccNumber.toString()));
        return ccNumber.toString();
    }

    /**
     * Calculates the Luhn checksum digit for a given number string.
     *
     * @param number The number string to calculate the checksum for.
     * @return The Luhn checksum digit.
     */
    private static int calculateLuhnChecksum(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (10 - (sum % 10)) % 10;
    }

    /**
     * Generates a random bank account number consisting of 12 digits.
     *
     * @return A randomly generated bank account number.
     */
    public static String generateBankAccountNumber() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(secureRandom.nextInt(10));
        }
        return accountNumber.toString();
    }

    /**
     * Generates a random IBAN (International Bank Account Number) with the default country code "DE".
     *
     * @return A randomly generated IBAN.
     */
    public static String generateIBAN() {
        SecureRandom secureRandom = new SecureRandom();
        String countryCode = "DE";
        StringBuilder iban = new StringBuilder(countryCode);
        for (int i = 0; i < 20; i++) {
            iban.append(secureRandom.nextInt(10));
        }
        return iban.toString();
    }

    /**
     * Generates a random boolean value.
     *
     * @return A randomly generated boolean value.
     */
    public static boolean generateBoolean() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextBoolean();
    }

    /**
     * Generates random binary data of the specified length.
     *
     * @param length The length of the binary data.
     * @return A randomly generated byte array of the specified length.
     */
    public static byte[] generateBinaryData(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] data = new byte[length];
        secureRandom.nextBytes(data);
        return data;
    }

    /**
     * Generates a random timestamp.
     *
     * @return A randomly generated timestamp in ISO 8601 format.
     */
    public static String generateTimestamp() {
        SecureRandom secureRandom = new SecureRandom();
        long currentTimeMillis = System.currentTimeMillis();
        long randomMillis = secureRandom.nextInt(1000000000);
        return new java.sql.Timestamp(currentTimeMillis - randomMillis).toString();
    }

    /**
     * Generates a random Unix timestamp.
     *
     * @return A randomly generated Unix timestamp.
     */
    public static long generateUnixTimestamp() {
        SecureRandom secureRandom = new SecureRandom();
        return System.currentTimeMillis() / 1000L - secureRandom.nextInt(1000000000);
    }

    /**
     * Generates a random time in the format "HH:mm:ss".
     *
     * @return A randomly generated time.
     */
    public static String generateTime() {
        SecureRandom secureRandom = new SecureRandom();
        int hours = secureRandom.nextInt(24);
        int minutes = secureRandom.nextInt(60);
        int seconds = secureRandom.nextInt(60);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Generates a random IP address in the format "X.X.X.X".
     *
     * @return A randomly generated IP address.
     */
    public static String generateIPAddress() {
        SecureRandom secureRandom = new SecureRandom();
        return String.format("%d.%d.%d.%d", secureRandom.nextInt(256), secureRandom.nextInt(256), secureRandom.nextInt(256), secureRandom.nextInt(256));
    }

    /**
     * Generates a random MAC address in the format "XX:XX:XX:XX:XX:XX".
     *
     * @return A randomly generated MAC address.
     */
    public static String generateMACAddress() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder mac = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            mac.append(String.format("%02X", secureRandom.nextInt(256)));
            if (i < 5) mac.append(":");
        }
        return mac.toString();
    }

    /**
     * Generates a random hex color code in the format "#RRGGBB".
     *
     * @return A randomly generated hex color code.
     */
    public static String generateHexColor() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder color = new StringBuilder("#");
        for (int i = 0; i < 6; i++) {
            color.append(Integer.toHexString(secureRandom.nextInt(16)));
        }
        return color.toString();
    }

    /**
     * Generates a random integer between the specified minimum and maximum values (inclusive).
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated integer between min and max (inclusive).
     */
    public static int generateInt(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random float between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated float between min and max.
     */
    public static float generateFloat(float min, float max) {
        SecureRandom secureRandom = new SecureRandom();
        return min + secureRandom.nextFloat() * (max - min);
    }

    /**
     * Generates a random double between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated double between min and max.
     */
    public static double generateDouble(double min, double max) {
        SecureRandom secureRandom = new SecureRandom();
        return min + secureRandom.nextDouble() * (max - min);
    }

    /**
     * Generates a random long between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated long between min and max.
     */
    public static long generateLong(long min, long max) {
        SecureRandom secureRandom = new SecureRandom();
        return min + (long) (secureRandom.nextDouble() * (max - min));
    }

    /**
     * Generates a random byte.
     *
     * @return A randomly generated byte.
     */
    public static byte generateByte() {
        SecureRandom secureRandom = new SecureRandom();
        return (byte) secureRandom.nextInt(256);
    }

    /**
     * Generates a random byte array of the specified length.
     *
     * @param length The length of the byte array.
     * @return A randomly generated byte array.
     */
    public static byte[] generateByteArray(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] byteArray = new byte[length];
        secureRandom.nextBytes(byteArray);
        return byteArray;
    }

    /**
     * Generates a random short between the specified minimum and maximum values (inclusive).
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated short between min and max (inclusive).
     */
    public static short generateShort(short min, short max) {
        SecureRandom secureRandom = new SecureRandom();
        return (short) (min + secureRandom.nextInt((max - min) + 1));
    }

    /**
     * Generates a random char between the specified minimum and maximum values (inclusive).
     *
     * @param min The minimum char value.
     * @param max The maximum char value.
     * @return A randomly generated char between min and max (inclusive).
     */
    public static char generateChar(char min, char max) {
        SecureRandom secureRandom = new SecureRandom();
        return (char) (min + secureRandom.nextInt((max - min) + 1));
    }

    /**
     * Generates a random hex string of the specified length.
     *
     * @param length The length of the hex string.
     * @return A randomly generated hex string.
     */
    public static String generateHex(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder hexString = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            hexString.append(Integer.toHexString(secureRandom.nextInt(16)));
        }
        return hexString.toString();
    }

    /**
     * Generates a random Gaussian distributed value.
     *
     * @param mean The mean of the distribution.
     * @param standardDeviation The standard deviation of the distribution.
     * @return A randomly generated Gaussian distributed value.
     */
    public static double generateGaussian(double mean, double standardDeviation) {
        SecureRandom secureRandom = new SecureRandom();
        return mean + secureRandom.nextGaussian() * standardDeviation;
    }

    /**
     * Generates a random integer based on a custom distribution.
     *
     * @param probabilities An array of probabilities for each integer.
     * @return A randomly generated integer based on the given probabilities.
     */
    public static int generateRandomWithCustomDistribution(double[] probabilities) {
        SecureRandom secureRandom = new SecureRandom();
        double p = secureRandom.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (p <= cumulativeProbability) {
                return i;
            }
        }
        return probabilities.length - 1; // Fallback
    }

    /**
     * Generates a random prime number between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated prime number between min and max.
     */
    public static int generateRandomPrime(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        int num;
        do {
            num = generateInt(min, max);
        } while (!isPrime(num));
        return num;
    }

    /**
     * Generates a random percentage between 0 and 100.
     *
     * @return A randomly generated percentage.
     */
    public static double generateRandomPercentage() {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextDouble() * 100.0;
    }

    /**
     * Generates a random integer from a given set of integers.
     *
     * @param set An array of integers.
     * @return A randomly selected integer from the given set.
     */
    public static int generateRandomFromSet(int[] set) {
        SecureRandom secureRandom = new SecureRandom();
        return set[secureRandom.nextInt(set.length)];
    }

    /**
     * Generates a random even integer between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated even integer between min and max.
     */
    public static int generateRandomEven(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        int num;
        do {
            num = generateInt(min, max);
        } while (num % 2 != 0);
        return num;
    }

    /**
     * Generates a random odd integer between the specified minimum and maximum values.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A randomly generated odd integer between min and max.
     */
    public static int generateRandomOdd(int min, int max) {
        SecureRandom secureRandom = new SecureRandom();
        int num;
        do {
            num = generateInt(min, max);
        } while (num % 2 == 0);
        return num;
    }

    /**
     * Generates a unique random sequence of integers.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @param length The length of the sequence.
     * @return An array containing a unique random sequence of integers.
     */
    public static int[] generateUniqueRandomSequence(int min, int max, int length) {
        if (length > (max - min + 1)) throw new IllegalArgumentException("Sequence length exceeds the range size.");
        SecureRandom secureRandom = new SecureRandom();
        List<Integer> numbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers, secureRandom);
        return numbers.subList(0, length).stream().mapToInt(i -> i).toArray();
    }

    /**
     * Generates a random value based on an exponential distribution.
     *
     * @param lambda The rate parameter of the distribution.
     * @return A randomly generated value based on the exponential distribution.
     */
    public static double generateRandomExponential(double lambda) {
        SecureRandom secureRandom = new SecureRandom();
        return Math.log(1 - secureRandom.nextDouble()) / (-lambda);
    }

    /**
     * Generates a random complex number with real and imaginary parts within specified ranges.
     *
     * @param realMin The minimum value for the real part.
     * @param realMax The maximum value for the real part.
     * @param imaginaryMin The minimum value for the imaginary part.
     * @param imaginaryMax The maximum value for the imaginary part.
     * @return A randomly generated complex number.
     */
    public static ComplexNumber generateRandomComplexNumber(double realMin, double realMax, double imaginaryMin, double imaginaryMax) {
        SecureRandom secureRandom = new SecureRandom();
        double realPart = realMin + (realMax - realMin) * secureRandom.nextDouble();
        double imaginaryPart = imaginaryMin + (imaginaryMax - imaginaryMin) * secureRandom.nextDouble();
        return new ComplexNumber(realPart, imaginaryPart);
    }

    /**
     * Checks if a number is prime.
     *
     * @param number The number to check.
     * @return True if the number is prime, false otherwise.
     */
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

    /**
     * Gets the maximum number of days in a given month and year.
     *
     * @param month The month number (1 = January, 12 = December).
     * @param year The year.
     * @return The maximum number of days in the given month and year.
     */
    private static int getMaxDays(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    /**
     * Checks if a year is a leap year.
     *
     * @param year The year to check.
     * @return True if the year is a leap year, false otherwise.
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Checks if a string is numeric.
     *
     * @param str The string to check.
     * @return True if the string is numeric, false otherwise.
     */
    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Generates a random phone number based on a pattern.
     *
     * @param pattern The pattern to use for generating the phone number.
     * @return A randomly generated phone number.
     */
    private static String generateRandomPhoneNumber(String pattern) {
        StringBuilder phoneNumber = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        boolean isEscaped = false;

        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);

            if (isEscaped) {
                if (ch == 'd') {
                    // Check if the next character is '{' to handle \d{n}
                    if (i + 1 < pattern.length() && pattern.charAt(i + 1) == '{') {
                        // Find the closing '}' and extract the number inside the braces
                        int closingBraceIndex = pattern.indexOf('}', i + 2);
                        if (closingBraceIndex != -1) {
                            String repeatCountStr = pattern.substring(i + 2, closingBraceIndex);
                            int repeatCount = Integer.parseInt(repeatCountStr);
                            for (int j = 0; j < repeatCount; j++) {
                                phoneNumber.append(secureRandom.nextInt(10));
                            }
                            i = closingBraceIndex; // Move the index to the closing '}'
                        }
                    } else {
                        // Generate a single digit for \d
                        phoneNumber.append(secureRandom.nextInt(10));
                    }
                } else {
                    // Append other escaped characters (e.g., '(', ')', '-')
                    phoneNumber.append(ch);
                }
                isEscaped = false;
            } else {
                if (ch == '\\') {
                    // Handle escape character
                    isEscaped = true;
                } else {
                    // Append literal characters
                    phoneNumber.append(ch);
                }
            }
        }

        return phoneNumber.toString();
    }
}