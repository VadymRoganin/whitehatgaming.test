package com.whitehatgaming.piece;

/**
 * This class represents chess piece type
 */
public enum PieceType {

    ROOK("R"), KNIGHT("N"), BISHOP("B"), KING("K"), QUEEN("Q"), PAWN("P");

    private final String character;

    PieceType(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }
}
