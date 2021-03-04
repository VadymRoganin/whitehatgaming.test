package com.whitehatgaming.engine;

import com.whitehatgaming.UserInput;
import com.whitehatgaming.checkdetector.CheckDetector;
import com.whitehatgaming.exception.ChessException;
import com.whitehatgaming.exception.MoveException;
import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;
import com.whitehatgaming.movevalidator.MoveValidationResult;
import com.whitehatgaming.movevalidator.MoveValidator;
import com.whitehatgaming.piece.Color;
import com.whitehatgaming.renderer.Renderer;

import java.io.IOException;

/**
 * Game engine class.
 * Handles game loop/orchestration
 */
public class GameEngine {

    private final UserInput userInput;

    private final MoveValidator moveValidator;

    private final CheckDetector checkDetector;

    private final Renderer renderer;

    /**
     *
     * @param userInput user input
     * @param moveValidator - move validator
     * @param checkDetector - check validator
     * @param renderer - board/move renderer
     */
    public GameEngine(UserInput userInput, MoveValidator moveValidator, CheckDetector checkDetector, Renderer renderer) {
        this.userInput = userInput;
        this.moveValidator = moveValidator;
        this.checkDetector = checkDetector;
        this.renderer = renderer;
    }

    /**
     * Starts game loop
     */
    public void startGameLoop() {
        Board board = new Board(true);
        Color color = Color.WHITE;
        Move move;
        while ((move = getMoveFromInput(color)) != null) {
            handleMove(move, board);
            color = color == Color.WHITE ? Color.BLACK : Color.WHITE;
        }
    }

    private Move getMoveFromInput(Color currentColor) {
        try {
            int[] arr = userInput.nextMove();
            return arr != null ? Move.fromIntArray(arr, currentColor) : null;
        } catch (IOException e) {
            throw new ChessException("Error extracting move from input", e);
        }
    }

    /**
     * Handles move
     * @param move move to handle
     * @param board the board
     */
    private void handleMove(Move move, Board board) {
        if (checkDetector.checkToActivePlayer(board, move)) {
            // there's a check to active player after the move
            throw new MoveException("Invalid move detected: player cannot end move in check");
        }
        MoveValidationResult validationResult = moveValidator.validate(board, move);

        if (!validationResult.isValid()) {
            throw new MoveException(validationResult.getReason());
        }

        renderer.render(move);

        if (checkDetector.checkToOpponent(board, move)) {
            // there's a check to opponent
            System.out.println("Player in check!");
        }

        board.move(move);
        renderer.render(board);
    }
}
