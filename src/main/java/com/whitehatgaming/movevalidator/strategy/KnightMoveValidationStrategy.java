package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

public class KnightMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public KnightMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {
        return (Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY) == 1)
                || (Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 2);
    }

    @Override
    protected boolean isPathClear() {
        // always true for knight
        return true;
    }
}