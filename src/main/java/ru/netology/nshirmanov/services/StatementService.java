package ru.netology.nshirmanov.services;

import ru.netology.nshirmanov.Operation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class StatementService {
    private final OperationService operationService;

    public StatementService(OperationService operationService) {
        this.operationService = operationService;
    }

    public List<Operation> getStatement(int customerId) {
        return operationService.getOperationsByCustomer(customerId);
    }

    public List<Operation> getStatementByPeriod(int customerId, LocalDate from, LocalDate to) {
        List<Operation> operations = operationService.getOperationsByCustomer(customerId);
        return operations.stream()
                .filter(op -> !op.getDate().isBefore(from) && !op.getDate().isAfter(to))
                .collect(Collectors.toList());
    }

    public void printStatementByPeriod(int customerId, LocalDate from, LocalDate to) {
        List<Operation> operations = getStatementByPeriod(customerId, from, to);
        if (operations.isEmpty()) {
            System.out.println("За данный период операций не обнаружено.");
            return;
        }

        System.out.println("Выписка для клиента с Id: " + customerId);
        System.out.println("От: " + from + " До: " + to);
        System.out.println("----------------------------------------------------------");
        for (Operation operation : operations) {
            operation.print();
        }
        System.out.println("----------------------------------------------------------");
    }

    public void printStatement(int customerId) {
        List<Operation> operations = getStatement(customerId);
        if (operations.isEmpty()) {
            System.out.println("Операции не найдены.");
            return;
        }

        System.out.println("Выписка для клиента с Id: " + customerId);
        System.out.println("----------------------------------------------------------");
        for (Operation operation : operations) {
            operation.print();
        }
        System.out.println("----------------------------------------------------------");
    }
}
