package ru.netology.nshirmanov;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        Long[] transactionIDs = new Long[5];
        String[] descriptions = new String[5];
        Double[] amounts = new Double[5];
        LocalDate[] dates = new LocalDate[5];
        String[] transactionTypes = new String[5];

        int transactionCount = 0;

        while (true) {
            if (transactionCount >= 5) {
                break;
            }

            System.out.println("Введите ID транзакции:");
            transactionIDs[transactionCount] = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Введите описание транзакции:");
            descriptions[transactionCount] = scanner.nextLine();

            System.out.println("Введите сумму транзакции:");
            amounts[transactionCount] = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Введите дату транзакции (гггг-мм-дд):");
            String dateString = scanner.nextLine();
            dates[transactionCount] = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

            System.out.println("Введите тип транзакции:");
            transactionTypes[transactionCount] = scanner.nextLine();

            transactionCount++;
        }

        scanner.close();

        for (int i = 0; i < 5; i++) {
            System.out.println("Транзакция #" + (i + 1) + ":");
            System.out.println("ID транзакции: " + transactionIDs[i] +
                    ", Описание: " + descriptions[i] +
                    ", Сумма: " + amounts[i] +
                    ", Дата: " + dates[i] +
                    ", Тип транзакции: " + transactionTypes[i]);
        }

    }
}
