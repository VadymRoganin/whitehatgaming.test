package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class KnightMoveValidatorTest {

    @Test
    public void shouldNotMoveAnywhereButCorrect() {
        Board board = new Board(false);
        int initX = 4;
        int initY = 4;
        board.putPiece(new Piece(PieceType.KNIGHT, Color.WHITE, initX, initY));

        int validMoveCount = 0;

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Move move = new Move(initX, initY, x, y, Color.WHITE);
                MoveValidator moveValidator = new MoveValidatorImpl();
                if (moveValidator.validate(board, move).isValid()) {
                    Assert.assertTrue((Math.abs(initX - x) == 2 && Math.abs(initY - y) == 1)
                            || (Math.abs(initX - x) == 1 && Math.abs(initY - y) == 2));
                    validMoveCount++;
                }
            }
        }
        Assert.assertEquals(8, validMoveCount);
    }

    @Test
    public void shouldMoveOverObstacles() {
        Board board = new Board(false);
        Piece whiteKnight = new Piece(PieceType.KNIGHT, Color.WHITE, 4, 4);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 5, 5);
        board.putPiece(whiteKnight);
        board.putPiece(blackPawn);

        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(whiteKnight, 5, 6, Color.WHITE)).isValid());
    }

    @Test
    public void shouldBiteOverObstacles() {
        Board board = new Board(false);
        Piece whiteKnight = new Piece(PieceType.KNIGHT, Color.WHITE, 4, 4);
        Piece blackPawn1 = new Piece(PieceType.PAWN, Color.BLACK, 5, 5);
        Piece blackPawn2 = new Piece(PieceType.PAWN, Color.BLACK, 5, 6);
        board.putPiece(whiteKnight);
        board.putPiece(blackPawn1);
        board.putPiece(blackPawn2);

        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(whiteKnight, blackPawn2, Color.WHITE)).isValid());
    }
}
