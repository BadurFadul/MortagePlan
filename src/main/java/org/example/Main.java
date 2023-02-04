package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        String fileName = "prospects.txt";
        String line;
        String[] data;
        DecimalFormat df = new DecimalFormat("#.##");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();  // skip the first line
            int prospect = 1;
            System.out.println("****************************************************************************************************");
            while ((line = br.readLine()) != null) {
                data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String customerName = data[0];
                if (customerName.startsWith("\"")) {
                    customerName = customerName.substring(1, customerName.length() - 1);
                }
                double loanAmount = Double.parseDouble(data[1]);
                double interestRate = Double.parseDouble(data[2]);
                int loanTerm = Integer.parseInt(data[3]);

                double interestOnAMonthlyBasis = interestRate / 12;
                int numberOfPayments = loanTerm * 12;
                double base = interestOnAMonthlyBasis + 1;
                double poweredBase = 1;
                for (int i = 0; i < numberOfPayments; i++) {
                    poweredBase *= base;
                }
                double monthlyPayment = loanAmount * interestOnAMonthlyBasis * poweredBase / (poweredBase - 1);

                System.out.println("Prospect " + prospect + ": " + customerName + " wants to borrow " + df.format(loanAmount) + " € for a period of " + loanTerm + " years and pay " + df.format(monthlyPayment) + " € each month");
                System.out.println("****************************************************************************************************");
                prospect++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
    }
}