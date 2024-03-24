package ru.netology.nshirmanov;

import ru.netology.nshirmanov.services.*;

public class Main {

    public static void main(String[] args) {
        lOService ioService = new lOService();
        CustomerService customerService = new CustomerService(new StorageService<>());
        OperationService operationService = new OperationService(new StorageService<>(), customerService);
        StatementService statementService = new StatementService(operationService);

        BankingApplication app = new BankingApplication(ioService, customerService, operationService, statementService);
        app.run();
    }

}
