package ru.netology.nshirmanov.exceptions;

import java.text.MessageFormat;

public class OperationRuntimeException extends RuntimeException {

    public OperationRuntimeException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    public OperationRuntimeException() {
        super();
    }
}
