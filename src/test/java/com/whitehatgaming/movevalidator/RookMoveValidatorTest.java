package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class RookMoveValidatorTest {

    @Test
    public void shouldNotMoveAnywhereButStraight() {
        Board board = new Board(false);
        int initX = 4;
        int initY = 4;
        board.putPiece(new Piece(PieceType.ROOK, Color.WHITE, initX, initY));

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Move move = new Move(initX, initY, x, y, Color.WHITE);
                MoveValidator moveValidator = new MoveValidatorImpl();
                if (moveValidator.validate(board, move).isValid()) {
                    Assert.assertTrue(initX == x || initY == y);
                }
            }
        }
    }

    @Test
    public void shouldMoveForward() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 8, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepVerticallyUp() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 5, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepVerticallyDown() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 3, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepHorizontallyRight() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 5, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepHorizontallyLeft() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 3, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveBackward() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 2, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveLeft() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 2, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveRight() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 6, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldBite() {
        Board board = new Board(false);
        Piece whiteRook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        Piece blackRook = new Piece(PieceType.ROOK, Color.BLACK, 6, 4);
        board.putPiece(whiteRook);
        board.putPiece(blackRook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(whiteRook, blackRook, Color.WHITE)).isValid());
    }

    @Test
    public void shouldNotMoveThroughObstacle() {
        Board board = new Board(false);
        Piece whiteRook = new Piece(PieceType.ROOK, Color.WHITE, 4, 4);
        Piece blackRook = new Piece(PieceType.ROOK, Color.WHITE, 5, 4);
        board.putPiece(whiteRook);
        board.putPiece(blackRook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, new Move(whiteRook, 6, 4, Color.WHITE)).isValid());
    }
}
