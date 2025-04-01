package com.codecrafts.digitalrain;

import java.util.Random;

public class TerminalDigitalRain {

    // ANSI escape codes for colors and screen handling
    private static final String ANSI_CLEAR = "\u001B[2J";
    private static final String ANSI_HOME = "\u001B[H";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '[', ']', '{', '}', '<', '>', '?', '%', '$',
            '#', '/', '\\', '+', '-', '*', '@', '&', '!', '^'
    };

    // Terminal size – adjust these to match your preferred width/height
    private static final int WIDTH = 80; // columns
    private static final int HEIGHT = 25; // rows

    // Probability of a "space" instead of a character for each position
    private static final double SPACE_PROBABILITY = 0.02;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        int[] dropPositions = new int[WIDTH];
        for (int i = 0; i < WIDTH; i++) {
            dropPositions[i] = random.nextInt(HEIGHT);
        }

        while (true) {
            System.out.print(ANSI_CLEAR + ANSI_HOME + ANSI_GREEN);

            for (int row = 0; row < HEIGHT; row++) {
                StringBuilder lineBuilder = new StringBuilder();

                for (int col = 0; col < WIDTH; col++) {
                    if (row == dropPositions[col]) {
                        if (random.nextDouble() < SPACE_PROBABILITY) {
                            lineBuilder.append(' ');
                        } else {
                            char c = CHARS[random.nextInt(CHARS.length)];
                            lineBuilder.append(c);
                        }
                    } else {
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

            for (int i = 0; i < WIDTH; i++) {
                if (random.nextDouble() < 0.95) {
                    dropPositions[i] = (dropPositions[i] + 1) % HEIGHT;
                } else {
                    dropPositions[i] = random.nextInt(HEIGHT);
                }
            }

            Thread.sleep(100);
        }
    }
}