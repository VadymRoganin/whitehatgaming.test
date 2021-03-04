package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

public class KingMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public KingMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {
        return Math.abs(fromX - toX) < 2 && Math.abs(fromY - toY) < 2;
    }

    @Override
    public boolean isPathClear() {
        // always true for king
        return true;
    }
}

