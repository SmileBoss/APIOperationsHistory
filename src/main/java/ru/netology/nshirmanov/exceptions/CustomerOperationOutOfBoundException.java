package ru.netology.nshirmanov.exceptions;

public class CustomerOperationOutOfBoundException extends OperationRuntimeException {
    public static final String MESSAGE = "Исключение при попытке сохранить операцию %s для клиента %s";
    private final Integer customerId;
    private final Integer operationId;

    public CustomerOperationOutOfBoundException(Integer customerId, Integer operationId) {
        super();
        this.customerId = customerId;
        this.operationId = operationId;
    }

    @Override
    public String getMessage() {
        return MESSAGE.formatted(operationId, customerId);
    }


}
