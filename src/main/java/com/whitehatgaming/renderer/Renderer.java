package com.whitehatgaming.renderer;

import com.whitehatgaming.board.Board;
import com.whitehatgaming.move.Move;

/**
 * Board/Move renderer
 */
public interface Renderer {

    /**
     * Renders board
     * @param board board to render
     */
    void render(Board board);

    /**
     * Renders move
     * @param move board to render
     */
    void render(Move move);
}
