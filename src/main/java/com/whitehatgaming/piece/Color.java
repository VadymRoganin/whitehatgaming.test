package com.whitehatgaming.piece;

/**
 * Piece color - white or black
 */
public enum Color {
    WHITE, BLACK;

    /**
     * Gets opposite color
     * @return the opposite color
     */
    public Color getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
