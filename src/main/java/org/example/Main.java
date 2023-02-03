package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "prospects.txt";
        String line;
        String[] data;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            reader.readLine();  // skip the first line
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                String customername = data[0];
                double loanAmount = Double.parseDouble(data[1]);
                double interestRate = Double.parseDouble(data[2]);
                int loanTerm = Integer.parseInt(data[3]);

                System.out.println(customername + " "+ loanAmount + " " + interestRate + " " + loanTerm);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file");
            e.printStackTrace();
        }
    }
}