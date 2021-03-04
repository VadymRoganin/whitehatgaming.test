package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

public class BishopMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public BishopMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {
        return Math.abs(fromX - toX) == Math.abs(fromY - toY);
    }

    @Override
    protected boolean isPathClear() {
        return checkDiagonalPath();
    }
}
