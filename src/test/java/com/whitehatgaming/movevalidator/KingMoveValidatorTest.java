package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class KingMoveValidatorTest {

    @Test
    public void shouldNotMoveAnywhereButCorrect() {
        Board board = new Board(false);
        int initX = 4;
        int initY = 4;
        board.putPiece(new Piece(PieceType.KING, Color.WHITE, initX, initY));

        int validMoveCount = 0;

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Move move = new Move(initX, initY, x, y, Color.WHITE);
                MoveValidator moveValidator = new MoveValidatorImpl();
                if (moveValidator.validate(board, move).isValid()) {
                    Assert.assertTrue(Math.abs(initX - x) < 2 && Math.abs(initY - y) < 2);
                    validMoveCount++;
                }
            }
        }
        Assert.assertEquals(8, validMoveCount);
    }

    @Test
    public void shouldBite() {
        Board board = new Board(false);
        Piece whiteKing = new Piece(PieceType.KING, Color.WHITE, 4, 4);
        Piece blackKing = new Piece(PieceType.KING, Color.BLACK, 4, 5);
        board.putPiece(whiteKing);
        board.putPiece(blackKing);

        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(whiteKing, blackKing, Color.WHITE)).isValid());
    }
}
