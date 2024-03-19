package ru.netology.nshirmanov.exceptions;

import java.text.MessageFormat;

public class OperationRuntimeException extends RuntimeException {

    public OperationRuntimeException(String message, Throwable cause, Object... args) {
        super("[" + MessageFormat.format(message, args) + "] ", cause);
    }

    public OperationRuntimeException(String message, Object... args) {
        this(message, (Throwable) null, args);
    }

    public OperationRuntimeException() {
        super();
    }
}
