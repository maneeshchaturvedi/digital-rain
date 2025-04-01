package com.codecrafts.digitalrain;

public class Main {
    public static void main(String[] args) {
        // Create the configuration
        ScreenConfig config = new ScreenConfig(
                80, // width
                25, // height
                0.02, // probability of space in primary drop
                0.1 // probability of faint character in background
        );

        // Create the engine and start
        RenderingEngine engine = new RenderingEngine(config, 100 /* ms delay */);
        try {
            engine.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}