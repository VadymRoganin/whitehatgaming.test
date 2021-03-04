package com.whitehatgaming.movevalidator;

import com.whitehatgaming.exception.ChessException;
import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.movevalidator.strategy.*;
import com.whitehatgaming.piece.Piece;

import java.util.Objects;

import static com.whitehatgaming.board.Board.CHESS_BOARD_SIZE;

public class MoveValidatorImpl implements MoveValidator {

    @Override
    public MoveValidationResult validate(Board board, Move move) {

        Objects.requireNonNull(board);
        Objects.requireNonNull(move);

        if (boardSizeLimitsExceeded(move)) {
            return new MoveValidationResult(false, "Validation failed: move is out of board limits!");
        }

        int fromX = move.getFromX();
        int fromY = move.getFromY();
        int toX = move.getToX();
        int toY = move.getToY();

        Piece piece = board.getPiece(fromX, fromY);

        if (piece == null) {
            return new MoveValidationResult(false, "Invalid move detected: no start piece");
        }

        if (piece.getColor() != move.getColor()) {
            return new MoveValidationResult(false, "Invalid move detected: invalid color");
        }

        if (fromX == toX && fromY == toY) {
            return new MoveValidationResult(false, "Invalid move detected: no coordinates change");
        }

        MoveValidationStrategy strategy;

        switch (piece.getPieceType()) {
            case ROOK -> {
                strategy = new RookMoveValidationStrategy(board, move);
            }
            case KNIGHT -> {
                strategy = new KnightMoveValidationStrategy(board, move);
            }
            case BISHOP -> {
                strategy = new BishopMoveValidationStrategy(board, move);
            }
            case KING -> {
                strategy = new KingMoveValidationStrategy(board, move);
            }
            case QUEEN -> {
                strategy = new QueenMoveValidationStrategy(board, move);
            }
            case PAWN -> {
                strategy = new PawnMoveValidationStrategy(board, move);
            }
            // Should never happen
            default -> throw new ChessException("Invalid validation strategy");
        }
        return strategy.isValid();
    }

    boolean boardSizeLimitsExceeded(Move move) {
        int fromX = move.getFromX();
        int fromY = move.getFromY();
        int toX = move.getToX();
        int toY = move.getToY();

        if (fromX < 1 || fromX > CHESS_BOARD_SIZE) {
            return true;
        }

        if (fromY < 1 || fromY > CHESS_BOARD_SIZE) {
            return true;
        }

        if (toX < 1 || toX > CHESS_BOARD_SIZE) {
            return true;
        }

        if (toY < 1 || toY > CHESS_BOARD_SIZE) {
            return true;
        }

        return false;
    }
}
