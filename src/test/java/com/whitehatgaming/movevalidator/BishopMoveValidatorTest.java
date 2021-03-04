package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class BishopMoveValidatorTest {

    @Test
    public void shouldNotMoveAnywhereButDiagonally() {
        Board board = new Board(false);
        int initX = 4;
        int initY = 4;
        board.putPiece(new Piece(PieceType.BISHOP, Color.WHITE, initX, initY));

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Move move = new Move(initX, initY, x, y, Color.WHITE);
                MoveValidator moveValidator = new MoveValidatorImpl();
                if (moveValidator.validate(board, move).isValid()) {
                    Assert.assertEquals(Math.abs(initX - x), Math.abs(initY - y));
                }
            }
        }
    }

    @Test
    public void shouldMoveDiagonally_1() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 6, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_2() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 6, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonallyOneSquareRightUp() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 5, 5, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonallyOneSquareRightDown() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 5, 3, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonallyOneSquareLeftUp() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 3, 5, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonallyOneSquareLeftDown() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 3, 3, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_3() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 6, 2, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_4() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        board.putPiece(bishop);

        Move move = new Move(bishop, 2, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonally() {
        Board board = new Board(false);
        Piece whiteBishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        Piece blackBishop = new Piece(PieceType.BISHOP, Color.BLACK, 6, 6);
        board.putPiece(whiteBishop);
        board.putPiece(blackBishop);

        Move move = new Move(whiteBishop, blackBishop, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveThroughObstacle() {
        Board board = new Board(false);
        Piece whiteBishop = new Piece(PieceType.BISHOP, Color.WHITE, 4, 4);
        Piece blackBishop = new Piece(PieceType.BISHOP, Color.BLACK, 6, 6);
        board.putPiece(whiteBishop);
        board.putPiece(blackBishop);

        Move move = new Move(whiteBishop, 7, 7, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }
}
