package ru.netology.nshirmanov;

import lombok.AllArgsConstructor;
import ru.netology.nshirmanov.exceptions.OperationRuntimeException;
import ru.netology.nshirmanov.services.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@AllArgsConstructor
public class BankingApplication {

    private final lOService ioService;
    private final CustomerService customerService;
    private final OperationService operationService;
    private final StatementService statementService;

    public void run() {
        while (true) {
            ioService.printMessage("\nВыберите действие: ");
            ioService.printMessage("1. Добавить клиента");
            ioService.printMessage("2. Добавить операцию");
            ioService.printMessage("3. Вывести операции клиента");
            ioService.printMessage("4. Сериализация данных");
            ioService.printMessage("5. Десериализация данных");
            ioService.printMessage("6. Выйти");

            int choice = ioService.readInt();
            try {
                switch (choice) {
                    case 1 -> createCustomer();
                    case 2 -> createOperation();
                    case 3 -> getCustomerOperations();
                    case 4 -> {
                        ioService.printMessage("Сериализация (клиенты/операции):");
                        switch (ioService.readLine()) {
                            case "клиенты" -> {
                                ioService.printMessage("Введите путь до файла: :");
                                String path = ioService.readLine();
                                customerService.serialize(path);
                            }
                            case "операции" -> {
                                ioService.printMessage("Введите путь до файла: :");
                                String path = ioService.readLine();
                                operationService.serialize(path);
                            }
                            default -> ioService.printMessage("Некорректный выбор. Попробуйте снова.");
                        }
                    }
                    case 5 -> {
                        ioService.printMessage("Десериализация (клиенты/операции):");
                        switch (ioService.readLine()) {
                            case "клиенты" -> {
                                ioService.printMessage("Введите путь до файла: :");
                                String path = ioService.readLine();
                                customerService.deserialize(path);
                            }
                            case "операции" -> {
                                ioService.printMessage("Введите путь до файла: :");
                                String path = ioService.readLine();
                                operationService.deserialize(path);
                            }
                            default -> ioService.printMessage("Некорректный выбор. Попробуйте снова.");
                        }
                    }
                    case 6 -> {
                        ioService.close();
                        System.exit(0);
                    }
                    default -> ioService.printMessage("Некорректный выбор. Попробуйте снова.");
                }
            } catch (Exception e) {
                ioService.printErrorMessage("Ошибка: " + e.getMessage());
            }
        }
    }

    private Customer createCustomer() {
        ioService.printMessage("Введите имя клиента:");
        String name = ioService.readLine();
        ioService.printMessage("Введите email клиента:");
        String email = ioService.readLine();

        Customer customer = customerService.createCustomer(name, email);
        ioService.printMessage("Клиент успешно добавлен с ID: " + customer.getClientId());
        return customer;
    }

    private void createOperation() {
        ioService.printMessage("Введите ID клиента:");
        int customerId = ioService.readInt();

        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            customer = createCustomer();
        }

        ioService.printMessage("Выберите тип операции (1 - обычная, 2 - cashback, 3 - займ):");
        int operationType = ioService.readInt();
        ioService.printMessage("Введите сумму транзакции:");
        double amount = ioService.readDouble();
        ioService.printMessage("Введите описание транзакции:");
        String description = ioService.readLine();
        ioService.printMessage("Введите дату транзакции (гггг-мм-дд):");
        LocalDate date = LocalDate.parse(ioService.readLine(), DateTimeFormatter.ISO_LOCAL_DATE);

        int additionalData = 0;
        switch (operationType) {
            case 2 -> {
                ioService.printMessage("Введите сумму кэшбэка:");
                additionalData = ioService.readInt();
            }
            case 3 -> {
                ioService.printMessage("Введите ID кредита:");
                additionalData = ioService.readInt();
            }
        }

        Operation operation = operationService.createOperation(customerId, amount, description,
                date, operationType, additionalData);
        ioService.printMessage("Операция успешно добавлена с ID: " + operation.getId());
    }

    private void getCustomerOperations() {
        ioService.printMessage("Введите ID клиента, чтобы посмотреть его операции:");
        int customerId = ioService.readInt();

        if (ioService.confirm("Выбрать транзакции за диапазон дат? ")) {
            try {
                ioService.printMessage("Введите начальную дату диапазона (гггг-мм-дд):");
                LocalDate startDate = LocalDate.parse(ioService.readLine());
                ioService.printMessage("Введите конечную дату диапазона (гггг-мм-дд):");
                LocalDate endDate = LocalDate.parse(ioService.readLine());
                statementService.printStatementByPeriod(customerId, startDate, endDate);
            } catch (DateTimeParseException e) {
                throw new OperationRuntimeException("Некорретный формат даты");
            }
        } else {
            statementService.printStatement(customerId);
        }
    }
}
