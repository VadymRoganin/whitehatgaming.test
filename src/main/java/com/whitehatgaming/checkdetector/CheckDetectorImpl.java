package com.whitehatgaming.checkdetector;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.movevalidator.MoveValidator;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.piece.Piece;

/**
 * {@link com.whitehatgaming.checkdetector.CheckDetector} implementation
 *
 */
public class CheckDetectorImpl implements CheckDetector {

    private final MoveValidator moveValidator;

    public CheckDetectorImpl(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    /**
     * Detects if there's check to active player after move
     * @param board chess board
     * @param move move to detect
     * @return true if there's check to active player after move, false otherwise
     */
    @Override
    public boolean checkToActivePlayer(Board board, Move move) {
        Board boardCopy = new Board(board);
        Piece king = boardCopy.getKing(move.getColor());
        boardCopy.move(move);

        return detectCheck(boardCopy, king);
    }

    /**
     * Detects if there's check to opponent player after move
     * @param board chess board
     * @param move move to detect
     * @return true if there's check to opponent player after move, false otherwise
     */
    @Override
    public boolean checkToOpponent(Board board, Move move) {
        Board boardCopy = new Board(board);
        Piece king = boardCopy.getKing(move.getColor().getOpposite());
        boardCopy.move(move);

        return detectCheck(boardCopy, king);
    }

    /**
     * Detects if there's a check to king
     * @param board board
     * @param king king
     * @return detection result
     */
    private boolean detectCheck(Board board, Piece king) {
        Color enemyColor = king.getColor().getOpposite();
        return board.stream()
                .anyMatch(piece -> piece != null
                        && piece.getColor() == enemyColor
                        && moveValidator.validate(board, new Move(piece, king, enemyColor)).isValid());
    }
}
