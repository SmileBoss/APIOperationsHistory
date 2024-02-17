package ru.netology.nshirmanov;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        long transactionID;
        String description;
        double amount;
        LocalDate date;

        System.out.println("Введите ID транзакции:");
        transactionID = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Введите описание транзакции:");
        description = scanner.nextLine();

        System.out.println("Введите сумму транзакции:");
        amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Введите дату транзакции (гггг-мм-дд):");
        String dateString = scanner.nextLine();
        date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        scanner.close();

        System.out.println("Транзакция: " + transactionID +
                ", Описание: " + description +
                ", Сумма: " + amount +
                ", Дата: " + date);

    }
}
