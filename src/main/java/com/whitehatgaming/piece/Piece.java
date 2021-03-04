package com.whitehatgaming.piece;

import com.whitehatgaming.exception.MoveException;

import java.util.Objects;

import static com.whitehatgaming.board.Board.CHESS_BOARD_SIZE;

/**
 * This class represents chess piece
 */
public class Piece {

    private final PieceType pieceType;

    private final Color color;

    private int x;

    private int y;

    public Piece(PieceType pieceType, Color color, int x, int y) {
        this.pieceType = pieceType;
        this.color = color;
        setX(x);
        setY(y);
    }

    /**
     * Copy constructor
     * @param piece piece to copy from
     */
    public Piece(Piece piece) {
        this.pieceType = piece.getPieceType();
        this.color = piece.getColor();
        setX(piece.getX());
        setY(piece.getY());
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        validateCoordinate(x);
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        validateCoordinate(y);
        this.y = y;
    }

    private void validateCoordinate(int i) {
        if (i < 1 || i > CHESS_BOARD_SIZE) {
            throw new MoveException("Invalid coordinate - out of chess board!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return x == piece.x && y == piece.y && pieceType == piece.pieceType && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color, x, y);
    }
}
