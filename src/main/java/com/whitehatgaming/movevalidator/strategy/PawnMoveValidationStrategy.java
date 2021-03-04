package com.whitehatgaming.movevalidator.strategy;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;

public class PawnMoveValidationStrategy extends AbstractMoveValidationStrategy implements MoveValidationStrategy {

    public PawnMoveValidationStrategy(Board board, Move move) {
        super(board, move);
    }

    @Override
    public boolean isMoveCorrect() {

        Piece target = board.getPiece(toX, toY);

        if (target == null) {
            // validate non attacking pawn
            // should move vertically only
            if (fromX != toX || fromY == toY) {
                // should move vertically only
                return false;
            }

            if (color == Color.WHITE) {
               return whiteMovesForward();
            } else {
              return blackMovesForward();
            }

        } else {
            if (color == Color.WHITE) {
              return whiteBitesForward();
            } else {
              return blackBitesForward();
            }
        }
    }

    private boolean whiteMovesForward() {
        if (fromY >= toY) {
            // should move forward
            return false;
        }
        if (fromY == 2 && toY - fromY > 2) {
            // if never moved and moves for > 2 steps
            return false;
        }
        if (fromY != 2 && toY - fromY > 1) {
            // if already moved and moves for > 1 step
            return false;
        }
        return true;
    }

    private boolean blackMovesForward() {
        if (toY >= fromY)
            return false;
        if (fromY == 7 && fromY - toY > 2) {
            // if never moved and moves for > 2 steps
            return false;
        }
        if (fromY != 7 && fromY - toY > 1) {
            // if already moved and moves for > 1 step
            return false;
        }
        return true;
    }

    private boolean blackBitesForward() {
        if (fromY <= toY) {
            // should move forward
            return false;
        }
        if (Math.abs(fromY - toY) != 1 || Math.abs(fromX - toX) != 1) {
            // should bite diagonally only
            return false;
        }
        return true;
    }

    private boolean whiteBitesForward() {
        if (toY <= fromY) {
            // should bite forward
            return false;
        }
        if (Math.abs(fromY - toY) != 1 || Math.abs(fromX - toX) != 1) {
            // should bite diagonally only
            return false;
        }
        return true;
    }

    @Override
    protected boolean isPathClear() {
        if (color == Color.WHITE) {
            // there's obstacle on 3rd line
            return fromY != 2 || toY != 4 || board.getPiece(toX, 3) == null;
        } else {
            // there's obstacle on 3rd line
            return fromY != 7 || toY != 5 || board.getPiece(toX, 6) == null;
        }
    }

    @Override
    public boolean isTargetSquareAvailable() {
        if (Math.abs(fromY - toY) == 1
                && Math.abs(fromX - toX) == 1
                && target !=null
                && target.getColor() != this.color) {
            return true;
        }
        return target == null;
    }
}
