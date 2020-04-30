package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * This class runs the current animation.
 *
 * @author Yana Molodetsky
 *
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor.
     */
    public AnimationRunner() {
        this.framesPerSecond = 60;
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
    }

    /**
     * The method runs the animation on the screen.
     *
     * @param animation The animation that the method runs.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            // Drawing the animation.
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

        }
    }

    /**
     * Getter for the GUI.
     *
     * @return the GUI.
     */
    public GUI getGUI() {
        return this.gui;
    }
}
