package ru.netology.nshirmanov.services;

import lombok.Data;

import java.util.Scanner;

@Data
public class lOService {

    private final Scanner scanner;

    public lOService() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int readInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public double readDouble() {
        double number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.err.println(message);
    }

    public void printInstructions(String[] instructions) {
        for (String instruction : instructions) {
            printMessage(instruction);
        }
    }

    public boolean confirm(String prompt) {
        printMessage(prompt + " (да/нет)");
        String response = readLine();
        return "да".equalsIgnoreCase(response);
    }

    public void close() {
        scanner.close();
    }

}
