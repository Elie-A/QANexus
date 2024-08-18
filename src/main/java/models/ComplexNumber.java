package models;

/**
 * Represents a complex number with a real and imaginary part.
 * <p>
 * A complex number is a number of the form <code>a + bi</code>, where <code>a</code> is the real part
 * and <code>b</code> is the imaginary part.
 * </p>
 */
public class ComplexNumber {
    private final double real;
    private final double imaginary;

    /**
     * Constructs a {@code ComplexNumber} with the specified real and imaginary parts.
     *
     * @param real the real part of the complex number
     * @param imaginary the imaginary part of the complex number
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Returns the real part of this complex number.
     *
     * @return the real part of this complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Returns the imaginary part of this complex number.
     *
     * @return the imaginary part of this complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Returns a string representation of this complex number in the form
     * <code>a + bi</code>, where <code>a</code> is the real part and <code>b</code> is the imaginary part,
     * formatted to two decimal places.
     *
     * @return a string representation of this complex number
     */
    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", real, imaginary);
    }
}