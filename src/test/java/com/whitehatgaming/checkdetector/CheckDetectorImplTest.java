package com.whitehatgaming.checkdetector;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.movevalidator.MoveValidatorImpl;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class CheckDetectorImplTest {

    @Test
    public void shouldDetectCheckToActivePlayerIfPlayerInCheck() {
        Board board = new Board(false);
        Piece king = new Piece(PieceType.KING, Color.WHITE, 1, 2);
        board.putPiece(king);
        Piece bishop = new Piece(PieceType.BISHOP, Color.WHITE, 1, 3);
        board.putPiece(bishop);
        board.putPiece(new Piece(PieceType.QUEEN, Color.BLACK, 1, 8));

        CheckDetector checkDetector = new CheckDetectorImpl(new MoveValidatorImpl());
        boolean result = checkDetector.checkToActivePlayer(board, new Move(bishop, 2, 4, Color.WHITE));
        Assert.assertTrue(result);
    }

    @Test
    public void shouldNotDetectChecToActivePlayerIfPlayerNotInCheck() {
        Board board = new Board(false);
        Piece king = new Piece(PieceType.KING, Color.WHITE, 1, 1);
        board.putPiece(king);
        board.putPiece(new Piece(PieceType.BISHOP, Color.WHITE, 1, 2));
        board.putPiece(new Piece(PieceType.QUEEN, Color.BLACK, 2, 8));

        CheckDetector checkDetector = new CheckDetectorImpl(new MoveValidatorImpl());
        boolean result = checkDetector.checkToActivePlayer(board, new Move(1, 2, 2, 3, Color.WHITE));
        Assert.assertFalse(result);
    }

    @Test
    public void shouldDetectCheckToOpponentPlayerIfPlayerInCheck() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.ROOK, Color.WHITE, 2, 3);
        board.putPiece(bishop);
        board.putPiece(new Piece(PieceType.KING, Color.BLACK, 1, 8));

        CheckDetector checkDetector = new CheckDetectorImpl(new MoveValidatorImpl());
        boolean result = checkDetector.checkToOpponent(board, new Move(bishop, 1, 3, Color.WHITE));
        Assert.assertTrue(result);
    }

    @Test
    public void shouldNotDetectCheckToActivePlayerIfPlayerNotInCheck() {
        Board board = new Board(false);
        Piece bishop = new Piece(PieceType.ROOK, Color.WHITE, 2, 3);
        board.putPiece(bishop);
        board.putPiece(new Piece(PieceType.KING, Color.BLACK, 1, 8));

        CheckDetector checkDetector = new CheckDetectorImpl(new MoveValidatorImpl());
        boolean result = checkDetector.checkToOpponent(board, new Move(bishop, 3, 3, Color.WHITE));
        Assert.assertFalse(result);
    }
}
