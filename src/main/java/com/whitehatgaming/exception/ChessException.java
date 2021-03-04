package com.whitehatgaming.exception;

/**
 * Base game exception
 */
public class ChessException extends RuntimeException {

    public ChessException(String message) {
        super(message);
    }

    public ChessException(String message, Throwable cause) {
        super(message, cause);
    }
}
