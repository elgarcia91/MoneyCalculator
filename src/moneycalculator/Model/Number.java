package moneycalculator.Model;

public class Number {

    private long numerator;
    private long denominator;

    public Number(long numerator, long denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.reduce();
    }

    public Number(long number) {
        this.numerator = number;
        this.denominator = 1;
    }

    public Number(double number) {
        this.numerator = (long) number;
        this.denominator = 1;
        while (numerator != denominator * number) {
            denominator = denominator * 10;
            numerator = (long) (number * denominator);
        }
        this.reduce();
    }

    public long getNumerator() {
        return numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    private void reduce() {
        int prime = 2;
        while (this.numerator / 2 > prime && this.denominator / 2 > prime) {
            while (isDivisible(prime)) {
                this.numerator = this.numerator / prime;
                this.denominator = this.denominator / prime;
            }
            prime = getNextPrime(prime);
        }
    }

    private int getNextPrime(int prime) {
        while (true) {
            prime++;
            if (isPrime(prime)) {
                return prime;
            }
        }
    }

    private boolean isPrime(int prime) {
        for (int i = 2; i < prime - 1; i++) {
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isDivisible(int prime) {
        return ((this.numerator % prime == 0) && (this.denominator % prime == 0));
    }

    public Number add(Number number) {
        long addNumerator;
        long addDenominator;

        addDenominator = this.denominator * number.denominator;
        addNumerator = ((addDenominator / this.denominator) * this.numerator)
                + ((addDenominator / number.denominator) * number.numerator);
        return new Number(addNumerator, addDenominator);
    }

    public Number substract(Number number) {
        long subsNumerator;
        long subsDenominator;

        subsDenominator = this.denominator * number.denominator;
        subsNumerator = ((subsDenominator / this.denominator) * this.numerator)
                + ((subsDenominator / number.denominator) * number.numerator);
        return new Number(subsNumerator, subsDenominator);
    }

    public Number multiply(Number number) {
        long multNumerator;
        long multDenominator;

        multNumerator = this.numerator * number.numerator;
        multDenominator = this.denominator * number.denominator;
        return new Number(multNumerator, multDenominator);
    }

    public Number divide(Number number) {
        long divNumerator;
        long divDenominator;

        divNumerator = this.numerator * number.denominator;
        divDenominator = this.denominator * number.numerator;
        return new Number(divNumerator, divDenominator);
    }

    @Override
    public String toString() {
        return String.valueOf((double)numerator / (double) denominator);
    }
}