package ru.sberbank.edu;

public class GCD implements CommonDivisor {
    /**
     * Method calculated divisor
     */
    private int calcDivisor(int firstNumber, int secondNumber) {
        while (secondNumber != 0) {
            int divisor = firstNumber % secondNumber;
            firstNumber = secondNumber;
            secondNumber = divisor;
        }
        return firstNumber;
    }

    /**
     * Method return divisor
     */
    @Override
    public int getDivisor(int firstNumber, int secondNumber) {
        return calcDivisor(firstNumber, secondNumber);
    }
}
