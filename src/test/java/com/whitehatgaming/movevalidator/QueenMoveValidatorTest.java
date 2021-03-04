package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class QueenMoveValidatorTest {

    @Test
    public void shouldNotMoveAnywhereButDiagonallyAndStraight() {
        Board board = new Board(false);
        int initX = 4;
        int initY = 4;
        board.putPiece(new Piece(PieceType.QUEEN, Color.WHITE, initX, initY));

        for (int x = 1; x < 9; x++) {
            for (int y = 1; y < 9; y++) {
                Move move = new Move(initX, initY, x, y, Color.WHITE);
                MoveValidator moveValidator = new MoveValidatorImpl();
                if (moveValidator.validate(board, move).isValid()) {
                    Assert.assertTrue((Math.abs(initX - x) == Math.abs(initY - y)) || (initX == x || initY == y));
                }
            }
        }
    }

    @Test
    public void shouldMoveDiagonally_1() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);

        Move move = new Move(queen, 6, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_2() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 1, 1);
        board.putPiece(queen);

        Move move = new Move(queen, 6, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_3() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);

        Move move = new Move(queen, 6, 2, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveDiagonally_4() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);

        Move move = new Move(queen, 2, 6, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonally() {
        Board board = new Board(false);
        Piece whiteQueen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        Piece blackQueen = new Piece(PieceType.QUEEN, Color.BLACK, 6, 6);
        board.putPiece(whiteQueen);
        board.putPiece(blackQueen);

        Move move = new Move(whiteQueen, blackQueen, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveDiagonallyThroughObstacle() {
        Board board = new Board(false);
        Piece whiteQueen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        Piece blackQueen = new Piece(PieceType.QUEEN, Color.WHITE, 6, 6);
        board.putPiece(whiteQueen);
        board.putPiece(blackQueen);

        Move move = new Move(whiteQueen, 7, 7, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveForward() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(queen, 4, 8, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveBackward() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(queen, 4, 2, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveLeft() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(queen, 2, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveRight() {
        Board board = new Board(false);
        Piece queen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(queen);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(queen, 6, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldBiteStraight() {
        Board board = new Board(false);
        Piece whiteQueen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        Piece blackQueen = new Piece(PieceType.QUEEN, Color.BLACK, 6, 4);
        board.putPiece(whiteQueen);
        board.putPiece(blackQueen);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(whiteQueen, blackQueen, Color.WHITE)).isValid());
    }

    @Test
    public void shouldNotMoveStraightThroughObstacle() {
        Board board = new Board(false);
        Piece whiteQueen = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        Piece blackQueen = new Piece(PieceType.QUEEN, Color.WHITE, 5, 4);
        board.putPiece(whiteQueen);
        board.putPiece(blackQueen);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, new Move(whiteQueen, 6, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepVerticallyUp() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 5, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepVerticallyDown() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 4, 3, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepHorizontallyRight() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 5, 4, Color.WHITE)).isValid());
    }

    @Test
    public void shouldMoveOneStepHorizontallyLeft() {
        Board board = new Board(false);
        Piece rook = new Piece(PieceType.QUEEN, Color.WHITE, 4, 4);
        board.putPiece(rook);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, new Move(rook, 3, 4, Color.WHITE)).isValid());
    }
}
