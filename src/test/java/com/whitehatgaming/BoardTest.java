package com.whitehatgaming;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;
import org.junit.Assert;
import org.junit.Test;

public class BoardTest {

    @Test
    public void shouldMove() {
        Board board = new Board(true);
        int initialX = 4;
        int initialY = 2;
        int resultingX = 4;
        int resultingY = 4;
        Piece pawn = board.getPiece(initialX, initialY);
        board.move(new Move(initialX, initialY, resultingX, resultingY, Color.WHITE));
        Assert.assertNull(board.getPiece(initialX, initialY));
        Piece resultingPiece = board.getPiece(resultingX, resultingY);
        Assert.assertNotNull(resultingPiece);
        Assert.assertEquals(pawn, resultingPiece);
        Assert.assertEquals(resultingX, resultingPiece.getX());
        Assert.assertEquals(resultingY, resultingPiece.getY());
    }

    @Test
    public void shouldBite() {
        Board board = new Board(true);
        int initialX = 4;
        int initialY = 2;
        int resultingX = 4;
        int resultingY = 4;
        Piece pawn = board.getPiece(initialX, initialY);
        board.putPiece(new Piece(PieceType.PAWN, Color.BLACK, resultingX, resultingY));
        board.move(new Move(initialX, initialY, resultingX, resultingY, Color.WHITE));
        Assert.assertNull(board.getPiece(initialX, initialY));
        Piece resultingPiece = board.getPiece(resultingX, resultingY);
        Assert.assertNotNull(resultingPiece);
        Assert.assertEquals(pawn, resultingPiece);
        Assert.assertEquals(resultingX, resultingPiece.getX());
        Assert.assertEquals(resultingY, resultingPiece.getY());
    }

    @Test
    public void shouldGetPiece() {
        Board board = new Board(true);
        Piece resultingPiece = board.getPiece(1, 2);

        Assert.assertEquals(PieceType.PAWN, resultingPiece.getPieceType());
        Assert.assertEquals(Color.WHITE, resultingPiece.getColor());
        Assert.assertEquals(1, resultingPiece.getX());
        Assert.assertEquals(2, resultingPiece.getY());
    }


    @Test
    public void shouldAddPiece() {
        Board board = new Board(false);

        Piece pawn = new Piece(PieceType.PAWN, Color.WHITE, 1, 2);
        board.putPiece(pawn);

        Piece resultingPiece = board.getPiece(1, 2);

        Assert.assertEquals(PieceType.PAWN, resultingPiece.getPieceType());
        Assert.assertEquals(Color.WHITE, resultingPiece.getColor());
        Assert.assertEquals(1, resultingPiece.getX());
        Assert.assertEquals(2, resultingPiece.getY());
    }

    @Test
    public void shouldGetWhiteKing() {
        Board board = new Board(true);
        Piece king = board.getKing(Color.WHITE);

        Assert.assertNotNull(king);
        Assert.assertEquals(PieceType.KING, king.getPieceType());
    }

    @Test
    public void shouldGetBlackKing() {
        Board board = new Board(true);
        Piece king = board.getKing(Color.BLACK);

        Assert.assertNotNull(king);
        Assert.assertEquals(PieceType.KING, king.getPieceType());
    }

    @Test
    public void shouldCreateCopyViaCopyConstructor() {
        Board board = new Board(true);
        Board copyBoard = new Board(board);

        Assert.assertEquals(board, copyBoard);
    }
}
