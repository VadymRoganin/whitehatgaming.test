package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.movevalidator.MoveValidationResult;

/**
 * Move validation strategy interface
 */
public interface MoveValidationStrategy {

    /**
     * Indicates if move is valid
     * @return true if move is valid, false otherwise
     */
    MoveValidationResult isValid();
}
