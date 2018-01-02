package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-29.
 */

public class ErrorTimeThresholdFinishingAlgorithm extends FinishingAlgorithm {
    /**
     *
     */
    private double errorTolerance;

    /**
     *
     */
    private double timeThreshold;

    /**
     * The last time the heading was not within the heading tolerance of the target.
     */
    private long lastOutOfRange;

    private double input;

    /**
     * Sets the algorithm being used to be the algorithm specified by the user, with a custom heading tolerance and heading duration.
     * @param errorTolerance The custom errorTolerance, how close the robot has to get
     * @param timeThreshold The custom timeThreshold, how long the robot has to stay within the tolerance
     */

    public ErrorTimeThresholdFinishingAlgorithm(double errorTolerance, double timeThreshold) {
        this.errorTolerance = errorTolerance;
        this.timeThreshold = timeThreshold;
    }

    /**
     * Updated the controller algorithm, and if the heading is outside of the tolerance, it sets the lastOutOfRange to the current time.
     */

    @Override
    public boolean finished() {
        return (System.nanoTime()-lastOutOfRange) > timeThreshold;
    }

    @Override
    public void input(double input) {
        this.input = input;
        if (Math.abs(getTarget()-input) > errorTolerance) lastOutOfRange = System.nanoTime();
    }
}
