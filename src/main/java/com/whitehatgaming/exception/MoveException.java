package com.whitehatgaming.exception;

/**
 * Exception related to move validation/creation
 */
public class MoveException extends ChessException {
    public MoveException(String message) {
        super(message);
    }
}
