package com.codecrafts.digitalrain;

public class TerminalManager {

    // ANSI escape sequences for controlling the terminal
    private static final String ANSI_CLEAR = "\u001B[2J";
    private static final String ANSI_HOME = "\u001B[H";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * Clears the screen, moves the cursor to the top-left,
     * and sets the console color (to green by default).
     */
    public void prepareScreen() {
        System.out.print(ANSI_CLEAR + ANSI_HOME + ANSI_GREEN);
    }

    /**
     * Resets the text color/style. Could be used if you
     * print anything after the rain and want to go back to normal.
     */
    public void resetColor() {
        System.out.print(ANSI_RESET);
    }
}
