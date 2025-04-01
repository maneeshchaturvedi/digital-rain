package com.codecrafts.digitalrain;

import java.util.Random;

public class DropPositioner {
    private final int[] dropPositions;
    private final ScreenConfig config;
    private final Random random;

    public DropPositioner(ScreenConfig config, Random random) {
        this.config = config;
        this.random = random;
        this.dropPositions = new int[config.getWidth()];

        // Initialize each column’s drop to a random row
        initializeDrops();
    }

    private void initializeDrops() {
        for (int i = 0; i < dropPositions.length; i++) {
            dropPositions[i] = random.nextInt(config.getHeight());
        }
    }

    /**
     * Returns the current drop row for a given column index.
     */
    public int getDropRowForColumn(int col) {
        return dropPositions[col];
    }

    /**
     * Moves drop positions downward (with occasional random reset).
     */
    public void updateDrops() {
        for (int i = 0; i < dropPositions.length; i++) {
            if (random.nextDouble() < 0.95) {
                // Move down one row, wrap around
                dropPositions[i] = (dropPositions[i] + 1) % config.getHeight();
            } else {
                // Occasionally reset to a random position for variety
                dropPositions[i] = random.nextInt(config.getHeight());
            }
        }
    }
}
