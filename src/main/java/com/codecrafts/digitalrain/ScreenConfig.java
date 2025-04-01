package com.codecrafts.digitalrain;

public class ScreenConfig {
    private final int width;
    private final int height;
    private final double spaceProbability;
    private final double faintCharProbability;

    public ScreenConfig(int width, int height, double spaceProbability, double faintCharProbability) {
        this.width = width;
        this.height = height;
        this.spaceProbability = spaceProbability;
        this.faintCharProbability = faintCharProbability;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getSpaceProbability() {
        return spaceProbability;
    }

    public double getFaintCharProbability() {
        return faintCharProbability;
    }
}