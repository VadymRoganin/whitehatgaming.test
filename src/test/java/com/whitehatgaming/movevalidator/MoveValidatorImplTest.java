package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class MoveValidatorImplTest {

    private Board createFullBoard() {
        Board board = new Board(false);

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                board.putPiece(new Piece(PieceType.PAWN, Color.WHITE, x, y));
            }
        }
       return board;
    }

    @Test
    public void shouldFailValidationWhenNoMove() {
        MoveValidator moveValidator = new MoveValidatorImpl();
        boolean result = moveValidator.validate(createFullBoard(), new Move(1, 1, 1, 1, Color.WHITE)).isValid();
        Assert.assertFalse(result);
    }

    @Test
    public void shouldFailValidationOnWrongColor() {
        MoveValidator moveValidator = new MoveValidatorImpl();
        Board board = new Board(true);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 2);
        board.putPiece(pawn);
        boolean result = moveValidator.validate(board, new Move(pawn, 2, 3, Color.BLACK)).isValid();
        Assert.assertFalse(result);
    }

    @Test
    public void shouldFailValidationOnNoStartPiece() {
        MoveValidator moveValidator = new MoveValidatorImpl();
        boolean result = moveValidator
                .validate(new Board(false), new Move(1, 1, 1, 1, Color.WHITE)).isValid();
        Assert.assertFalse(result);
    }

    @Test
    public void shouldFailValidationOnSameColorTarget() {
        MoveValidator moveValidator = new MoveValidatorImpl();
        Board board = new Board(true);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 2);
        board.putPiece(pawn);
        board.putPiece(new Piece(PieceType.PAWN, Color.WHITE, 2, 3));
        boolean result = moveValidator.validate(board, new Move(pawn, 2, 3, Color.WHITE)).isValid();
        Assert.assertFalse(result);
    }
}
