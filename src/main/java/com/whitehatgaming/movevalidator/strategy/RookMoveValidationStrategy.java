package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

public class RookMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public RookMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {
        return fromX == toX || fromY == toY;
    }

    @Override
    public boolean isPathClear() {
        if (fromX == toX) {
            return checkVerticalPath();
        }
        return checkHorizontalPath();
    }
}