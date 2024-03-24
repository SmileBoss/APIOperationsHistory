package ru.netology.nshirmanov.services;

import ru.netology.nshirmanov.CashbackOperation;
import ru.netology.nshirmanov.Customer;
import ru.netology.nshirmanov.LoanOperation;
import ru.netology.nshirmanov.Operation;
import ru.netology.nshirmanov.exceptions.OperationRuntimeException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OperationService {
    private final StorageService<Operation> operationStorage;
    private final CustomerService customerService;

    public OperationService(StorageService<Operation> operationStorage, CustomerService customerService) {
        this.operationStorage = operationStorage;
        this.customerService = customerService;
    }

    public Operation createOperation(int customerId,
                                     double amount,
                                     String description,
                                     LocalDate date,
                                     int operationType,
                                     Integer additionalData) {
        Customer customer = customerService.getCustomer(customerId);
        if (customer == null) {
            throw new OperationRuntimeException("Customer with ID: {0} not found.", customerId);
        }

        Operation operation;
        switch (operationType) {
            case 1 -> operation = new Operation(operationStorage.size(), amount, customerId, description, date);
            case 2 -> operation = new CashbackOperation(operationStorage.size(), amount, customerId, description, date, additionalData);
            case 3 -> operation = new LoanOperation(operationStorage.size(), amount, customerId, description, date, additionalData);
            default -> throw new IllegalArgumentException("Invalid operation type");
        }
        operationStorage.addItem(operation);

        return operation;
    }

    public void addOperation(Operation operation) {
        operationStorage.addItem(operation);
    }

    public List<Operation> getOperationsByCustomer(int customerId) {
        return operationStorage.getAllItems().stream()
                .filter(operation -> operation.getCustomerId() == customerId)
                .collect(Collectors.toList());
    }

    public Operation getOperation(int operationId) {
        return operationStorage.getItem(operationId);
    }

    public List<Operation> getAllOperations() {
        return operationStorage.getAllItems();
    }

    public void serialize(String path) throws IOException {
        operationStorage.serialize(path);
    }

    public void deserialize(String path) throws IOException, ClassNotFoundException {
        operationStorage.deserialize(path);
    }
}
