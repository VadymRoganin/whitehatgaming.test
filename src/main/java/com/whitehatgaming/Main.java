package com.whitehatgaming;

import com.whitehatgaming.checkdetector.CheckDetectorImpl;
import com.whitehatgaming.engine.GameEngine;
import com.whitehatgaming.exception.ChessException;
import com.whitehatgaming.movevalidator.MoveValidatorImpl;
import com.whitehatgaming.renderer.ConsoleRenderer;

import java.io.FileNotFoundException;

/**
 * Bootstrap class
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("""
                ╔═╗┬ ┬┌─┐┌─┐┌─┐
                ║  ├─┤├┤ └─┐└─┐
                ╚═╝┴ ┴└─┘└─┘└─┘
                © Vadim Roganin 2021
                """);
        if (args.length != 1) {
            throw new ChessException("There should be one and only one program argument: path to moves file");
        }
        try {
            new GameEngine(new UserInputFile(args[0]),
                    new MoveValidatorImpl(),
                    new CheckDetectorImpl(new MoveValidatorImpl()),
                    new ConsoleRenderer()).startGameLoop();
        } catch (FileNotFoundException e) {
            throw new ChessException("Cannot open moves file", e);
        }
    }
}
