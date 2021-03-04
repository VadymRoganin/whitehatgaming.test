package com.whitehatgaming.board;

import com.whitehatgaming.exception.ChessException;
import com.whitehatgaming.exception.MoveException;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;
import com.whitehatgaming.piece.PieceType;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * This class represents chess board
 */
public class Board {

    private final Piece[][] array = new Piece[CHESS_BOARD_SIZE][CHESS_BOARD_SIZE];

    public static final int CHESS_BOARD_SIZE = 8;

    /**
     * Creates new board
     * @param populateNewBoard populate new chess board or not
     */
    public Board(boolean populateNewBoard) {
        if (populateNewBoard) {
            populateNewBoard();
        }
    }

    /**
     * Copy constructor. Creates board copy
     * @param board board to copy from
     */
    public Board(Board board) {
        for (int x = 1; x <= CHESS_BOARD_SIZE; x++) {
            for (int y = 1; y <= CHESS_BOARD_SIZE; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece != null) {
                    putPiece(new Piece(piece));
                }
            }
        }
    }

    private void populateNewBoard() {
        putPiece(new Piece(PieceType.ROOK, Color.WHITE, 1, 1));
        putPiece(new Piece(PieceType.KNIGHT, Color.WHITE,2, 1));
        putPiece(new Piece(PieceType.BISHOP, Color.WHITE, 3, 1));
        putPiece(new Piece(PieceType.QUEEN, Color.WHITE, 4, 1));
        putPiece(new Piece(PieceType.KING, Color.WHITE, 5, 1));
        putPiece(new Piece(PieceType.BISHOP, Color.WHITE, 6, 1));
        putPiece(new Piece(PieceType.KNIGHT, Color.WHITE, 7, 1));
        putPiece(new Piece(PieceType.ROOK, Color.WHITE, 8, 1));

        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 1, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE,2, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 3, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 4, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 5, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 6, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 7, 2));
        putPiece(new Piece(PieceType.PAWN, Color.WHITE, 8, 2));

        putPiece(new Piece(PieceType.ROOK, Color.BLACK, 1, 8));
        putPiece(new Piece(PieceType.KNIGHT, Color.BLACK,2, 8));
        putPiece(new Piece(PieceType.BISHOP, Color.BLACK, 3, 8));
        putPiece(new Piece(PieceType.QUEEN, Color.BLACK, 4, 8));
        putPiece(new Piece(PieceType.KING, Color.BLACK, 5, 8));
        putPiece(new Piece(PieceType.BISHOP, Color.BLACK, 6, 8));
        putPiece(new Piece(PieceType.KNIGHT, Color.BLACK, 7, 8));
        putPiece(new Piece(PieceType.ROOK, Color.BLACK, 8, 8));

        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 1, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK,2, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 3, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 4, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 5, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 6, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 7, 7));
        putPiece(new Piece(PieceType.PAWN, Color.BLACK, 8, 7));
    }

    /**
     * Gets piece
     * @param x x coordinate
     * @param y y coordinate
     * @return the piece or null if no piece found
     */
    public Piece getPiece(int x, int y) {
        return array[y - 1][x - 1];
    }

    /**
     * Puts piece
     * @param piece piece to put
     */
    public void putPiece(Piece piece) {
        Objects.requireNonNull(piece);
        array[piece.getY() - 1][piece.getX() - 1] = piece;
    }

    /**
     * Moves the piece
     * @param move move
     */
    public void move(Move move) {
        Objects.requireNonNull(move);
        Piece piece = getPiece(move.getFromX(), move.getFromY());
        if (piece == null) {
            throw new MoveException("Invalid move: no source piece!");
        }
            removePiece(piece);
            piece.setX(move.getToX());
            piece.setY(move.getToY());
            putPiece(piece);
    }

    private void removePiece(Piece piece) {
        array[piece.getY() - 1][piece.getX() - 1] = null;
    }

    /**
     * Gets a king of requested color
     * @param color king's color
     * @return the king
     */
    public Piece getKing(Color color) {
        Objects.requireNonNull(color);
        for (int x = 1; x <= CHESS_BOARD_SIZE; x++) {
            for (int y = 1; y <= CHESS_BOARD_SIZE; y++) {
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getPieceType() == PieceType.KING && piece.getColor() == color) {
                    return piece;
                }
            }
        }
        throw new ChessException("Fatal exception: corrupted board - no king found!");
    }

    /**
     * Returns all board pieces as a stream
     * @return stream of all board pieces
     */
    public Stream<Piece> stream() {
        return Arrays.stream(array).flatMap(Arrays::stream);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(array, board.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = CHESS_BOARD_SIZE; y >= 1; y--) {
            sb.append("\n");
            sb.append("-----------------------------------\n");
            for (int x = 1; x <= CHESS_BOARD_SIZE; x++) {
                if (x == 1) {
                    sb.append(y).append(" ");
                }
                Piece piece = this.getPiece(x, y);
                sb.append("| ");
                if (piece == null) {
                    sb.append(" ");
                } else {
                    String charNameWhite = piece.getPieceType().getCharacter().toUpperCase();
                    String charNameBlack = piece.getPieceType().getCharacter().toLowerCase();
                    sb.append(piece.getColor() == Color.WHITE ? charNameWhite : charNameBlack);
                }
                sb.append(" ");
                if (x == CHESS_BOARD_SIZE) sb.append("|");
            }
        }
        sb.append("\n");
        sb.append("-----------------------------------\n");
        sb.append("  | A | B | C | D | E | F | G | H |\n");
        sb.append("\n");
        return sb.toString();
    }
}
