package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

/**
 * Move validator
 * @see com.whitehatgaming.move.Move
 */
public interface MoveValidator {
    /**
     * Validates move
     * @param board chess board
     * @param move a move
     */
    MoveValidationResult validate(Board board, Move move);
}
