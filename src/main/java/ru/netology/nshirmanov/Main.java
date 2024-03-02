package ru.netology.nshirmanov;

import ru.netology.nshirmanov.utils.DateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Operation> operations = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> statement = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello world!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие: ");
            System.out.println("1. Добавить клиента");
            System.out.println("2. Добавить операцию");
            System.out.println("3. Вывести операции клиента");
            System.out.println("4. Выйти");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> createCustomer(scanner);
                case 2 -> createOperation(scanner);
                case 3 -> getCustomerOperations(scanner).forEach(Operation::print);
                case 4 -> System.exit(0);
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    public static void createCustomer(Scanner scanner) {
        System.out.println("Введите имя клиента:");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Введите email клиента:");
        String email = scanner.nextLine();

        Customer customer = new Customer(customers.size(), name, email);
        customers.add(customer);
        statement.add(new ArrayList<>());
        System.out.println("Клиент успешно добавлен с ID: " + customer.getClientId());
    }

    public static void createOperation(Scanner scanner) {
        System.out.println("Введите ID клиента:");
        int customerId = scanner.nextInt();
        if (customerId >= customers.size()) {
            System.out.println("Клиента с таким ID не существует. Хотите добавить нового клиента? (да/нет)");
            String response = scanner.next();
            if ("да".equalsIgnoreCase(response)) {
                createCustomer(scanner);
            }
            return;
        }
        System.out.println("Выберите тип операции (1 - обычная, 2 - cashback, 3 - займ):");
        int operationType = scanner.nextInt();
        System.out.println("Введите сумму транзакции:");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Введите описание транзакции:");
        String description = scanner.nextLine();
        System.out.println("Введите дату транзакции (гггг-мм-дд):");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        switch (operationType) {
            case 1 -> operations.add(new Operation(operations.size(), amount, description, date));
            case 2 -> {
                System.out.println("Введите сумму кэшбэка:");
                int cashbackAmount = scanner.nextInt();
                operations.add(new CashbackOperation(operations.size(), amount, description, date, cashbackAmount));
            }
            case 3 -> {
                System.out.println("Введите ID кредита:");
                int loanId = scanner.nextInt();
                operations.add(new LoanOperation(operations.size(), amount, description, date, loanId));
            }
            default -> throw new IllegalStateException("Неожиданное значение: " + operationType);
        }
        statement.get(customerId).add(operations.size() - 1);
        System.out.println("Операция успешно добавлена клиенту с ID: " + customerId);
    }

    private static List<Operation> getCustomerOperations(Scanner scanner) {
        System.out.println("Введите ID клиента, чтобы посмотреть его операции:");
        int customerId = scanner.nextInt();
        if (customerId >= customers.size()) {
            System.out.println("Клиента с таким ID не существует.");
            return Collections.emptyList();
        }

        ArrayList<Integer> customerOperations = statement.get(customerId);
        if (customerOperations.isEmpty()) {
            System.out.println("У клиента нет операций.");
            return Collections.emptyList();
        } else {
            System.out.println("Выбрать транзакции за диапазон дат? (да)");
            scanner.nextLine();
            if ("да".equals(scanner.nextLine())) {
                System.out.println("Введите начальную дату диапазона (гггг-мм-дд):");
                LocalDate startDate = LocalDate.parse(scanner.nextLine());
                System.out.println("Введите конечную дату диапазона (гггг-мм-дд):");
                LocalDate endDate = LocalDate.parse(scanner.nextLine());
                return findTransactionsByDateRange(operations.stream()
                        .filter(operation -> customerOperations.contains(operation.getId()))
                        .toList(), startDate, endDate);
            } else {
                return operations.stream()
                        .filter(operation -> customerOperations.contains(operation.getId()))
                        .toList();
            }
        }
    }

    public static List<Operation> findTransactionsByDateRange(List<Operation> operations, LocalDate startDate,
                                                              LocalDate endDate) {
        return operations.stream().filter(operation -> DateUtils.localDateIsAfterOrEqual(operation.getDate(), startDate)
                        && DateUtils.localDateIsBeforeOrEqual(operation.getDate(), endDate))
                .toList();
    }
}
