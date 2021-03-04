package com.whitehatgaming.checkdetector;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

/**
 * Check detector interface
 */
public interface CheckDetector {

    /**
     * Detects if there's check to active player upon active player's move
     * @param board chess board
     * @param move move to check
     * @return true if current move will result in a check to current player, false otherwise
     */
    boolean checkToActivePlayer(Board board, Move move);

    /**
     * Detects if there's check to opponent player upon active player's move
     * @param board chess board
     * @param move move to check
     * @return true if current move will result in a check to opponent player, false otherwise
     */
    boolean checkToOpponent(Board board, Move move);
}
