package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

public class QueenMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public QueenMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {
        return (fromX == toX || fromY == toY)
                || (Math.abs(fromX - toX) == Math.abs(fromY - toY));
    }

    @Override
    protected boolean isPathClear() {
        if (fromX == toX || fromY == toY) {
            // horizontal - rook way
            if (fromX == toX) {
                return checkVerticalPath();
            }
            return checkHorizontalPath();
        }
        if (Math.abs(fromX - toX) == Math.abs(fromY - toY)) {
            // diagonal - bishop way
            return checkDiagonalPath();
        }
        return true;
    }
}