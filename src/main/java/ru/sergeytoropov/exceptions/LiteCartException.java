package ru.sergeytoropov.exceptions;

/**
 * @author sergeytoropov
 * @since 20.06.17
 */
public class LiteCartException extends RuntimeException {
    public LiteCartException() {
        super();
    }

    public LiteCartException(String message) {
        super(message);
    }

    public LiteCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public LiteCartException(Throwable cause) {
        super(cause);
    }
}
