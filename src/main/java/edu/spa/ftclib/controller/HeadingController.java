package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-29.
 */

abstract public class HeadingController extends SensorController implements FinishableController {
    private static final double DEFAULT_HEADING_TOLERANCE = Math.PI/50;
    /**
     * How long (in nanoseconds) the measured heading must be within {@link #DEFAULT_HEADING_TOLERANCE} of the target heading before {@link #finished} is true
     */
    private static final long DEFAULT_HEADING_DURATION = (long)1E9;
    private double headingTolerance;
    private double headingDuration;
    private long lastOutOfRange;
    /**
     * Sets the algorithm being used to be the algorithm specified by the user.
     *
     * @param algorithm Which control algorithm should be used
     */
    public HeadingController(ControlAlgorithm algorithm) {
        this(algorithm, DEFAULT_HEADING_TOLERANCE, DEFAULT_HEADING_DURATION);
    }

    public HeadingController(ControlAlgorithm algorithm, double headingTolerance, double headingDuration) {
        super(algorithm);
        this.headingTolerance = headingTolerance;
        this.headingDuration = headingDuration;
    }

    @Override
    public void update() {
        super.update();
        if (Math.abs(getTarget()-getSensorValue()) > headingTolerance) lastOutOfRange = System.nanoTime();
    }

    @Override
    public boolean finished() {
        return (System.nanoTime()-lastOutOfRange) > headingDuration;
    }
}
