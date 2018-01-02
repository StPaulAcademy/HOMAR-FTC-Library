package edu.spa.ftclib.old_controller;

/**
 * Created by Gabriel on 2017-12-29.
 * The general controller for when there is a sensor that can determine and heading and it is being used in conjunction with an PID controller.
 * If a modern robotics sensor is being used, use {@link GyroSensorController} and if the REV BNO055 is being used, use {@link IntegratingGyroscopeController}
 */

abstract public class HeadingController extends SensorController implements FinishableController {
    /**
     * How close the robot must be to the target heading to be considered "good enough."
     */
    private static final double DEFAULT_HEADING_TOLERANCE = Math.PI/50;
    /**
     * How long (in nanoseconds) the measured heading must be within {@link #DEFAULT_HEADING_TOLERANCE} of the target heading before {@link #finished} is true
     */
    private static final long DEFAULT_HEADING_DURATION = (long)1E9;

    /**
     * A custom headingTolerance, if the {@link #DEFAULT_HEADING_TOLERANCE} won't work.
     */
    private double headingTolerance;

    /**
     * A custom headingDuration, if the {@link #DEFAULT_HEADING_DURATION} won't work.
     */
    private double headingDuration;

    /**
     * The last time the heading was not within the heading tolerance of the target.
     */
    private long lastOutOfRange;
    /**
     * Sets the algorithm being used to be the algorithm specified by the user.
     *
     * @param algorithm Which control algorithm should be used
     */
    public HeadingController(ControlAlgorithm algorithm) {
        this(algorithm, DEFAULT_HEADING_TOLERANCE, DEFAULT_HEADING_DURATION);
    }

    /**
     * Sets the algorithm being used to be the algorithm specified by the user, with a custom heading tolerance and heading duration.
     * @param algorithm Which algorithm should the used
     * @param headingTolerance The custom headingTolerance, how close the robot has to get
     * @param headingDuration The custom headingDuration, how long the robot has to stay within the tolerance
     */

    public HeadingController(ControlAlgorithm algorithm, double headingTolerance, double headingDuration) {
        super(algorithm);
        this.headingTolerance = headingTolerance;
        this.headingDuration = headingDuration;
    }

    /**
     * Updated the controller algorithm, and if the heading is outside of the tolerance, it sets the lastOutOfRange to the current time.
     */
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
