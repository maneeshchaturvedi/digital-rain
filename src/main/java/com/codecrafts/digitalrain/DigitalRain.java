package com.codecrafts.digitalrain;

import java.util.Random;

public class DigitalRain {

    // ANSI constants
    private static final String ANSI_CLEAR = "\u001B[2J";
    private static final String ANSI_HOME = "\u001B[H";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Character set
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '[', ']', '{', '}', '<', '>', '?', '%', '$',
            '#', '/', '\\', '+', '-', '*', '@', '&', '!', '^'
    };

    // Configuration
    private static final int WIDTH = 80;
    private static final int HEIGHT = 25;
    private static final double SPACE_PROBABILITY = 0.02;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Initialize drop positions
        int[] dropPositions = createInitialDropPositions(random);

        while (true) {
            clearScreenAndSetColor();

            // Print each row
            for (int row = 0; row < HEIGHT; row++) {
                String line = buildRowString(row, dropPositions, random);
                System.out.println(line);
            }

            // Update drops
            updateDropPositions(dropPositions, random);

            Thread.sleep(100); // Delay so it’s not too fast
        }
    }

    /**
     * Initializes the drop positions for each column to a random row.
     */
    private static int[] createInitialDropPositions(Random random) {
        int[] dropPositions = new int[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            dropPositions[i] = random.nextInt(HEIGHT);
        }
        return dropPositions;
    }

    /**
     * Clears the screen, moves the cursor to the top-left,
     * and sets the text color to green.
     */
    private static void clearScreenAndSetColor() {
        System.out.print(ANSI_CLEAR + ANSI_HOME + ANSI_GREEN);
    }

    /**
     * Builds a single row of the rain effect by iterating through each column.
     */
    private static String buildRowString(int row, int[] dropPositions, Random random) {
        StringBuilder lineBuilder = new StringBuilder();
        for (int col = 0; col < WIDTH; col++) {
            lineBuilder.append(getCharacterForPosition(row, col, dropPositions, random));
        }
        return lineBuilder.toString();
    }

    /**
     * Determines which character should be displayed at the given (row, col).
     */
    private static char getCharacterForPosition(int row, int col, int[] dropPositions, Random random) {
        // If current row matches the drop position, choose a random char or space
        if (row == dropPositions[col]) {
            if (random.nextDouble() < SPACE_PROBABILITY) {
                return ' ';
            } else {
                return CHARS[random.nextInt(CHARS.length)];
            }
        } else {
            // Show either space or random chance of a faint char for background
            if (random.nextDouble() < 0.1) {
                return CHARS[random.nextInt(CHARS.length)];
            } else {
                return ' ';
            }
        }
    }

    /**
     * Updates the drop positions for each column, moving them downward or resetting
     * occasionally.
     */
    private static void updateDropPositions(int[] dropPositions, Random random) {
        for (int i = 0; i < WIDTH; i++) {
            if (random.nextDouble() < 0.95) {
                dropPositions[i] = (dropPositions[i] + 1) % HEIGHT;
            } else {
                dropPositions[i] = random.nextInt(HEIGHT);
            }
        }
    }
}