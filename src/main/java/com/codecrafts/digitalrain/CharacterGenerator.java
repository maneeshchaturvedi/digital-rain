package com.codecrafts.digitalrain;

import java.util.Random;

public class CharacterGenerator {

    private static final char[] CHARS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '[', ']', '{', '}', '<', '>', '?', '%', '$',
            '#', '/', '\\', '+', '-', '*', '@', '&', '!', '^'
    };

    private final ScreenConfig config;
    private final Random random;

    public CharacterGenerator(ScreenConfig config, Random random) {
        this.config = config;
        this.random = random;
    }

    /**
     * Returns the "main drop" character (or space) with a certain probability
     * for a direct drop position.
     */
    public char getPrimaryDropChar() {
        if (random.nextDouble() < config.getSpaceProbability()) {
            return ' ';
        }
        return CHARS[random.nextInt(CHARS.length)];
    }

    /**
     * Returns a faint background character (or space) with some probability.
     */
    public char getFaintChar() {
        if (random.nextDouble() < config.getFaintCharProbability()) {
            return CHARS[random.nextInt(CHARS.length)];
        }
        return ' ';
    }
}