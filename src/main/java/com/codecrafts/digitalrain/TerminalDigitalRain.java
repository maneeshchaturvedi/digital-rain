package com.codecrafts.digitalrain;

import java.util.Random;

public class TerminalDigitalRain {

    // ANSI escape codes for colors and screen handling
    // \u001B[2J clears the screen
    // \u001B[H moves the cursor to the top-left
    private static final String ANSI_CLEAR = "\u001B[2J";
    private static final String ANSI_HOME = "\u001B[H";

    // For color, using bright green for main characters
    private static final String ANSI_GREEN = "\u001B[32m";
    // You could experiment with a "bright" version:
    // private static final String ANSI_GREEN_BRIGHT = "\u001B[92m";
    private static final String ANSI_RESET = "\u001B[0m";

    // Characters you can use in the "rain"
    // (feel free to customize)
    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '[', ']', '{', '}', '<', '>', '?', '%', '$',
            '#', '/', '\\', '+', '-', '*', '@', '&', '!', '^'
    };

    // Terminal size – adjust these to match your preferred width/height
    // or detect them dynamically if desired.
    private static final int WIDTH = 80; // columns
    private static final int HEIGHT = 25; // rows

    // Probability of a "space" instead of a character for each position
    private static final double SPACE_PROBABILITY = 0.02;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        // Each column has a "drop position" that slowly moves downward
        // to create a sense of drifting columns.
        int[] dropPositions = new int[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            dropPositions[i] = random.nextInt(HEIGHT);
        }

        while (true) {
            // Clear screen and move cursor to top-left
            System.out.print(ANSI_CLEAR + ANSI_HOME + ANSI_GREEN);

            // Generate and print the matrix-like data
            // row by row, top to bottom
            for (int row = 0; row < HEIGHT; row++) {
                StringBuilder lineBuilder = new StringBuilder();

                for (int col = 0; col < WIDTH; col++) {
                    // If current row matches the drop position, choose a random char
                    if (row == dropPositions[col]) {
                        // Sometimes insert a blank space to add "gaps"
                        if (random.nextDouble() < SPACE_PROBABILITY) {
                            lineBuilder.append(' ');
                        } else {
                            char c = CHARS[random.nextInt(CHARS.length)];
                            lineBuilder.append(c);
                        }
                    } else {
                        // Show either space or random chance of a faint char
                        // to produce light background noise
                        if (random.nextDouble() < 0.1) {
                            char c = CHARS[random.nextInt(CHARS.length)];
                            lineBuilder.append(c);
                        } else {
                            lineBuilder.append(' ');
                        }
                    }
                }

                System.out.println(lineBuilder.toString());
            }

            // Update drop positions for each column
            for (int i = 0; i < WIDTH; i++) {
                // Move the drop down one row (wrap around if going off the bottom)
                if (random.nextDouble() < 0.95) {
                    dropPositions[i] = (dropPositions[i] + 1) % HEIGHT;
                } else {
                    // Occasionally reset a column to a random row
                    // to vary the drop start location
                    dropPositions[i] = random.nextInt(HEIGHT);
                }
            }

            // Small delay so it’s not too fast
            Thread.sleep(100);
        }
    }
}