package org.example;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testMonthlyPaymentCalculation() {
        double loanAmount = 1000.0;
        double interestRate = 0.05;
        int loanTerm = 2;

        double expectedMonthlyPayment = 43.8713;
        double monthlyPayment = calculateMonthlyPayment(loanAmount, interestRate, loanTerm);
        assertEquals(expectedMonthlyPayment, monthlyPayment, 0.01);
    }

    private double calculateMonthlyPayment(double loanAmount, double interestRate, int loanTerm) {
        double interestOnAMonthlyBasis = interestRate / 12;
        int numberOfPayments = loanTerm * 12;
        double base = interestOnAMonthlyBasis + 1;
        double poweredBase = 1;
        for (int i = 0; i < numberOfPayments; i++) {
            poweredBase *= base;
        }
        return loanAmount * interestOnAMonthlyBasis * poweredBase / (poweredBase - 1);
    }
}