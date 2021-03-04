package com.whitehatgaming.move;

import com.whitehatgaming.exception.MoveException;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;

import java.util.Objects;
import java.util.stream.IntStream;

import static com.whitehatgaming.board.Board.CHESS_BOARD_SIZE;

/**
 * Class representing chess move
 */
public class Move {

    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;
    private final Color color;

    /**
     * @param fromX initial x
     * @param fromY initial y
     * @param toX target x
     * @param toY target y
     * @param color active color
     */
    public Move(int fromX, int fromY, int toX, int toY, Color color) {
        validateCoordinate(fromX);
        validateCoordinate(fromY);
        validateCoordinate(toX);
        validateCoordinate(toY);
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.color = color;
    }

    /**
     * Convenience constructor
     * @param piece piece which moves
     * @param toX target x
     * @param toY target y
     * @param color active move
     */
    public Move(Piece piece, int toX, int toY, Color color) {
        validateCoordinate(toX);
        validateCoordinate(toY);
        this.fromX = piece.getX();
        this.fromY = piece.getY();
        this.toX = toX;
        this.toY = toY;
        this.color = color;
    }

    /**
     * Convenience constructor
     * @param pieceFrom piece which moves
     * @param pieceTo piece which will be beaten
     * @param color active color
     */
    public Move(Piece pieceFrom, Piece pieceTo, Color color) {
        this.fromX = pieceFrom.getX();
        this.fromY = pieceFrom.getY();
        this.toX = pieceTo.getX();
        this.toY = pieceTo.getY();
        this.color = color;
    }

    private void validateCoordinate(int n) {
        if (n < 1 || n > CHESS_BOARD_SIZE) {
            throw new MoveException("Invalid coordinate - out of chess board!");
        }
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Factory method for creating move from int array
     * @param arr int array denotes move
     * @param color active color
     * @return the move
     */
    public static Move fromIntArray(int[] arr, Color color) {
        if (arr.length != 4) {
            throw new MoveException("Invalid move array!");
        }
        IntStream.range(0, 4).forEach(i -> {
            if (arr[i] < 0 || arr[i] > CHESS_BOARD_SIZE - 1) {
                throw new MoveException("Invalid move array!");
            }
        });
        return new Move(arr[0] + 1, CHESS_BOARD_SIZE - arr[1], arr[2] + 1, CHESS_BOARD_SIZE - arr[3], color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return fromX == move.fromX && fromY == move.fromY && toX == move.toX && toY == move.toY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromX, fromY, toX, toY);
    }

    @Override
    public String toString() {
        return "" + (char)(fromX + 96) + fromY + "-" + (char)(toX + 96) + toY;
    }
}
