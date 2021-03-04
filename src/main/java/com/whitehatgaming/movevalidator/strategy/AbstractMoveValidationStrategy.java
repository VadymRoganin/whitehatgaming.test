package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.movevalidator.MoveValidationResult;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;

/**
 * Move validation base class
 */
public abstract class AbstractMoveValidationStrategy implements MoveValidationStrategy {

    protected final Board board;
    protected final int fromX;
    protected final int toX;
    protected final int fromY;
    protected final int toY;
    protected final Piece target;
    protected final Color color;

    public AbstractMoveValidationStrategy(Board board, Move move) {
        this.board = board;
        this.fromX = move.getFromX();
        this.toX = move.getToX();
        this.fromY = move.getFromY();
        this.toY = move.getToY();
        this.target = board.getPiece(toX, toY);
        this.color = move.getColor();
    }

    /**
     * Indicates if path has correct shape
     * @return true if path has correct shape
     */
    protected abstract boolean isMoveCorrect();

    /**
     * Indicates if path (not counting target square) has no obstacles
     * @return true if path (not counting target square) has no obstacles
     */
    protected abstract boolean isPathClear();

    /**
     * Indicates if target square is empty or beatable
     * @return true if target square is empty or beatable, false otherwise
     */
    public boolean isTargetSquareAvailable() {
        return target == null || target.getColor() != this.color;
    }

    @Override
    public MoveValidationResult isValid() {
        if (!isTargetSquareAvailable()) {
            return new MoveValidationResult(false, "Invalid move detected: target square not available");
        }
        if (!isMoveCorrect()) {
            return new MoveValidationResult(false, "Invalid move detected: incorrect move according to chess rules");
        }
        if (!isPathClear()) {
            return new MoveValidationResult(false, "Invalid move detected: there are obstacles on the way");
        }
        return new MoveValidationResult(true, null);
    }

    protected boolean checkVerticalPath() {
        if (Math.abs(fromY - toY) == 1) {
            return true;
        }
        int start = Math.min(fromY, toY) + 1;
        int end = Math.max(fromY, toY) - 1;
        for (int y = start; y <= end; y++) {
            if (board.getPiece(fromX, y) != null) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkHorizontalPath() {
        if (Math.abs(fromX - toX) == 1) {
            return true;
        }
        int start = Math.min(fromX, toX) + 1;
        int end = Math.max(fromX, toX) - 1;
        for (int x = start; x <= end; x++) {
            if (board.getPiece(x, fromY) != null) {
                return false;
            }
        }
        return true;
    }

    protected boolean checkDiagonalPath() {
        int xStep= fromX < toX ? 1 : -1;
        int yStep = fromY < toY ? 1 : -1;
        for (int x = fromX + xStep, y = fromY + yStep;
             (xStep > 0 ? x <= toX - xStep : x >= toX - xStep) && (yStep > 0 ? y <= toY - yStep : y >= toY - yStep);
             x += xStep, y += yStep){
            if (board.getPiece(x, y) != null) {
                return false;
            }
        }
        return true;
    }
}