package com.codecrafts.digitalrain;

import java.util.Random;

public class RenderingEngine {

    private final ScreenConfig config;
    private final TerminalManager terminal;
    private final CharacterGenerator charGenerator;
    private final DropPositioner dropPositioner;
    private final Random random;

    // Controls how fast the rain updates (in ms)
    private final long frameDelay;

    public RenderingEngine(ScreenConfig config, long frameDelay) {
        this.config = config;
        this.frameDelay = frameDelay;

        // Could be injected or created here
        this.random = new Random();
        this.terminal = new TerminalManager();
        this.charGenerator = new CharacterGenerator(config, random);
        this.dropPositioner = new DropPositioner(config, random);
    }

    public void start() throws InterruptedException {
        while (true) {
            terminal.prepareScreen();
            renderFrame();
            dropPositioner.updateDrops();
            Thread.sleep(frameDelay);
        }
    }

    /**
     * Renders a single frame of the digital rain.
     */
    private void renderFrame() {
        int width = config.getWidth();
        int height = config.getHeight();

        for (int row = 0; row < height; row++) {
            StringBuilder lineBuilder = new StringBuilder();

            for (int col = 0; col < width; col++) {
                int dropRow = dropPositioner.getDropRowForColumn(col);

                if (row == dropRow) {
                    lineBuilder.append(charGenerator.getPrimaryDropChar());
                } else {
                    lineBuilder.append(charGenerator.getFaintChar());
                }
            }

            // Print this row
            System.out.println(lineBuilder.toString());
        }
    }
}
