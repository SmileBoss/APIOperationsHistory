package ru.netology.nshirmanov;

import ru.netology.nshirmanov.services.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        lOService ioService = new lOService();
        CustomerService customerService = new CustomerService(new StorageService<>());
        customerService.createCustomer("admin", "admin_email");
        OperationService operationService = new OperationService(new StorageService<>(), customerService);
        StatementService statementService = new StatementService(operationService);

//        BankingApplication app = new BankingApplication(ioService, customerService, operationService, statementService);
//        app.run();

        AsyncInputOperationService asyncInputOperationService = new AsyncInputOperationService(operationService, ioService);
        asyncInputOperationService.startAsyncOperationProcessing();

        asyncInputOperationService.offerOperation(new CashbackOperation(1, 200d, 0, "test", LocalDate.now(), 20));
        asyncInputOperationService.offerOperation(new Operation(2, 12345d, 0, "test_1", LocalDate.now()));
        asyncInputOperationService.offerOperation(new LoanOperation(3, 132143d, 0, "test_2", LocalDate.now(), 15));
    }

}
