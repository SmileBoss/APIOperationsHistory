package ru.netology.nshirmanov;

import ru.netology.nshirmanov.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.stream.IntStream;

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

        IntStream.range(0, transactionCount).forEach(i -> {
            System.out.println("Транзакция #" + (i + 1) + ":");
            print(dates[i], transactionIDs[i], amounts[i], descriptions[i], transactionTypes[i]);
        });

        System.out.println("Введите начальную дату диапазона (гггг-мм-дд):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Введите конечную дату диапазона (гггг-мм-дд):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        scanner.close();

        findTransactionsByDateRange(dates, transactionIDs, amounts, descriptions, transactionTypes, startDate, endDate);
    }

    public static void print(LocalDate date, Long transactionID,
                             Double amount, String description,
                             String transactionType) {
        System.out.println("ID транзакции: " + transactionID +
                ", Описание: " + description +
                ", Сумма: " + amount +
                ", Дата: " + date +
                ", Тип транзакции: " + transactionType);
    }

    public static void findTransactionsByDateRange(LocalDate[] dates, Long[] transactionIDs,
                                                   Double[] amounts, String[] descriptions,
                                                   String[] transactionTypes, LocalDate startDate,
                                                   LocalDate endDate) {
        IntStream.range(0, dates.length).filter(i -> DateUtils.localDateIsAfterOrEqual(dates[i], startDate) &&
                DateUtils.localDateIsBeforeOrEqual(dates[i], endDate)).forEachOrdered(i ->
                print(dates[i], transactionIDs[i], amounts[i], descriptions[i], transactionTypes[i]));
    }
}
