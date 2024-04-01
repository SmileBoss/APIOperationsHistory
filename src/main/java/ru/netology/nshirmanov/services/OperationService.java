package ru.netology.nshirmanov.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.nshirmanov.dto.OperationCreateDto;
import ru.netology.nshirmanov.dto.OperationDto;
import ru.netology.nshirmanov.entities.CashbackOperation;
import ru.netology.nshirmanov.entities.Customer;
import ru.netology.nshirmanov.entities.LoanOperation;
import ru.netology.nshirmanov.entities.Operation;
import ru.netology.nshirmanov.enums.OperationType;
import ru.netology.nshirmanov.exceptions.OperationRuntimeException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperationService {

    private final StorageService<Operation> operationStorage;
    private final CustomerService customerService;

    public Operation create(OperationCreateDto dto, OperationType type) {
        Customer customer = customerService.getCustomer(dto.getCustomerId());
        if (customer == null) {
            throw new OperationRuntimeException("Customer with ID: {0} not found.", dto.getCustomerId());
        }

        Operation operation;
        switch (type) {
            case BASE -> operation = new Operation(operationStorage.size(), dto.getAmount(),
                    customer.getClientId(), dto.getDescription(), dto.getDate());

            case CASHBACK -> operation = new CashbackOperation(operationStorage.size(), dto.getAmount(),
                    customer.getClientId(), dto.getDescription(), dto.getDate(), dto.getAdditionalData());
            case LOAN -> operation = new LoanOperation(operationStorage.size(), dto.getAmount(),
                    customer.getClientId(), dto.getDescription(), dto.getDate(), dto.getAdditionalData());
            default -> throw new IllegalStateException("Invalid operation type: " + type);
        }
        AsyncInputOperationService.queue.add(operation);
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

    public List<Operation> getOperationsByCustomerAndPeriod(int customerId,
                                                            LocalDate dateFrom,
                                                            LocalDate dateTo) {
        return getOperationsByCustomer(customerId).stream()
                .filter(op -> !op.getDate().isBefore(dateFrom) && !op.getDate().isAfter(dateTo))
                .toList();
    }

    public Operation getOperation(int operationId) {
        return operationStorage.getItem(operationId);
    }

    public void deleteOperation(int operationId) {
        operationStorage.removeItem(operationId);
    }

    public Operation updateOperation(OperationDto dto) {
        return operationStorage.getItem(dto.getId());
    }

    public void deleteOperationsByCustomer(int customerId) {
        getOperationsByCustomer(customerId)
                .forEach(operation -> deleteOperation(operation.getId()));
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
