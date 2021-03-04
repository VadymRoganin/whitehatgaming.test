package com.whitehatgaming.renderer;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;

import static com.whitehatgaming.board.Board.CHESS_BOARD_SIZE;

public class ConsoleRenderer implements Renderer {

    @Override
    public void render(Board board) {
        for (int y = CHESS_BOARD_SIZE; y >= 1; y--) {
            System.out.println("");
            System.out.println("-----------------------------------");
            for (int x = 1; x <= CHESS_BOARD_SIZE; x++) {
                if (x == 1) {
                    System.out.print(y + " ");
                }
                Piece piece = board.getPiece(x, y);
                System.out.print("| ");
                if (piece == null) {
                    System.out.print(" ");
                } else {
                    String charNameWhite = piece.getPieceType().getCharacter().toUpperCase();
                    String charNameBlack = piece.getPieceType().getCharacter().toLowerCase();
                    System.out.print(piece.getColor() == Color.WHITE ? charNameWhite : charNameBlack);
                }
                System.out.print(" ");
                if (x == CHESS_BOARD_SIZE) {
                    System.out.print("|");
                }
            }
        }
        System.out.println("");
        System.out.println("-----------------------------------");
        System.out.println("  | A | B | C | D | E | F | G | H |");
        System.out.println("");
    }

    @Override
    public void render(Move move) {
        String toPrint = "" + (char)(move.getFromX() + 96) + move.getFromY() + "-" + (char)(move.getToX() + 96) + move.getToY();
        System.out.println(toPrint);
    }
}
