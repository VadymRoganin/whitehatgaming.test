package com.whitehatgaming.movevalidator;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class PawnMoveValidatorTest {

    // white moves
    @Test
    public void shouldMoveTwoSquaresAtFirstMove_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(new Piece(PieceType.PAWN, Color.WHITE, 1, 2));

        Move move = new Move(pawn, 1, 4, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveTwoSquaresAtNonFirstMove_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 3);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 5, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveOneSquare_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 3, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveMoreThanTwoSquaresFromFirstLine_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 5, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveMoreThanOneSquare_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 3);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 5, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveBackwards_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 1, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveDiagonally_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);

        Move move = new Move(pawn, 2, 3, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveAside_whiteMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);

        Move move = new Move(pawn, 2, 2, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonallyRight_whiteMoves() {
        Board board = new Board(false);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 2, 3);
        board.putPiece(whitePawn);
        board.putPiece(blackPawn);

        Move move = new Move(whitePawn, blackPawn, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonallyLeft_whiteMoves() {
        Board board = new Board(false);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 2);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 3);
        board.putPiece(whitePawn);
        board.putPiece(blackPawn);

        Move move = new Move(whitePawn, blackPawn, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotBiteDiagonallyBackwards_whiteMoves() {
        Board board = new Board(false);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 2);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 1);
        board.putPiece(whitePawn);
        board.putPiece(blackPawn);

        Move move = new Move(whitePawn, blackPawn, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotBiteForward_whiteMoves() {
        Board board = new Board(false);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 3);
        board.putPiece(whitePawn);
        board.putPiece(blackPawn);

        Move move = new Move(whitePawn, blackPawn, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveTwoSquaresThroughObstacle_whiteMoves() {
        Board board = new Board(false);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 3);
        board.putPiece(whitePawn);
        board.putPiece(blackPawn);

        Move move = new Move(whitePawn, 1, 4, Color.WHITE);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

// black moves
    @Test
    public void shouldMoveTwoSquaresAtFirstMove_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 5, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveTwoSquaresAtNonFirstMove_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 4, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldMoveOneSquare_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 6, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveMoreThanTwoSquaresFromFirstLine_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 4, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveMoreThanOneSquare_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 6);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 4, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveBackwards_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 1, 8, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveDiagonally_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 2, 6, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveAside_blackMoves() {
        Board board = new Board(false);
        Piece pawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        board.putPiece(pawn);

        Move move = new Move(pawn, 2, 7, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonallyRight_blackMoves() {
        Board board = new Board(false);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 6);
        board.putPiece(blackPawn);
        board.putPiece(whitePawn);

        Move move = new Move(blackPawn, whitePawn, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldBiteDiagonallyLeft_blackMoves() {
        Board board = new Board(false);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 2, 6);
        board.putPiece(blackPawn);
        board.putPiece(whitePawn);

        Move move = new Move(blackPawn, whitePawn, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertTrue(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotBiteDiagonallyBackwards_blackMoves() {
        Board board = new Board(false);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 2, 7);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 8);
        board.putPiece(blackPawn);
        board.putPiece(whitePawn);

        Move move = new Move(blackPawn, whitePawn, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotBiteForward_blackMoves() {
        Board board = new Board(false);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 6);
        board.putPiece(blackPawn);
        board.putPiece(whitePawn);

        Move move = new Move(blackPawn, whitePawn, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

    @Test
    public void shouldNotMoveTwoSquaresThroughObstacle_blackMoves() {
        Board board = new Board(false);
        Piece blackPawn = new Piece(PieceType.PAWN, Color.BLACK, 1, 7);
        Piece whitePawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 6);
        board.putPiece(blackPawn);
        board.putPiece(whitePawn);


        Move move = new Move(blackPawn, 1, 5, Color.BLACK);
        MoveValidator moveValidator = new MoveValidatorImpl();
        Assert.assertFalse(moveValidator.validate(board, move).isValid());
    }

}
