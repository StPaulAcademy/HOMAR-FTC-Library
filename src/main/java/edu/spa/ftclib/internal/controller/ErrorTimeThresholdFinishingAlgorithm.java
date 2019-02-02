package edu.spa.ftclib.internal.controller;

//import edu.spa.ftclib.internal.Global;    //For testing

//Created by Gabriel on 2017-12-29.

/**
 * Uses error tolerance and time threshold to determine when to stop running the controller and count it as "finished."
 * The robot must stay within the error tolerance continuously for the time given in the time threshold to finish.
 */

public class ErrorTimeThresholdFinishingAlgorithm extends FinishingAlgorithm {

    /**
     * The difference between the target value and the current value you are willing to live with
     */
    private double errorTolerance;

    /**
     *
     */
    private long timeThreshold;

    /**
     * The last time the heading was not within the heading tolerance of the target.
     */
    private long lastOutOfRange;
    private boolean withinRange = false;

    private double input;

    /**
     * Sets the algorithm being used to be the algorithm specified by the user, with a custom heading tolerance and heading duration.
     * @param errorTolerance The custom errorTolerance, how close the robot has to get
     * @param timeThreshold The custom timeThreshold, how long (in seconds) the robot has to stay within the tolerance
     */

    public ErrorTimeThresholdFinishingAlgorithm(double errorTolerance, double timeThreshold) {
        this.errorTolerance = errorTolerance;
        this.timeThreshold = (long)(timeThreshold*1e9); //Convert to nanoseconds
    }

    /**
     * It makes another algorithm by copying the original one
     * @param original The algorithm you wish to copy from
     * @param copyState Whether you want the various states to be copied as well. FALSE for only copying the settings, TRUE for also copying the current status of the controller
     */
    public ErrorTimeThresholdFinishingAlgorithm(ErrorTimeThresholdFinishingAlgorithm original, boolean copyState) {
        this(original.getErrorTolerance(), original.getTimeThreshold());
        if (copyState) {
            input = original.getInput();
            lastOutOfRange = original.getLastOutOfRange();
            withinRange = original.isWithinRange();
        }
    }

    /**
     * Makes a copy of the algorithm, but only copies the settings
     * @param original The algorithm you wish to copy
     */
    public ErrorTimeThresholdFinishingAlgorithm(ErrorTimeThresholdFinishingAlgorithm original) {
        this(original, false);
    }


    /**
     * Determines that the controller is finished
     * @return If the algorithm is finished or not
     */
    @Override
    public boolean finished() {
        /*Global.telemetry.addData("finishing", System.nanoTime()-lastOutOfRange);  //For testing
        //Global.telemetry.addData("threshold", timeThreshold);
        Global.telemetry.addData("withinRange", withinRange);*/
        return ((System.nanoTime()-lastOutOfRange) > timeThreshold) && withinRange;
    }

    /**
     * Updated the controller algorithm, and if the heading is outside of the tolerance, it sets the lastOutOfRange to the current time.
     * @param input The current value of the sensor
     */
    @Override
    public void input(double input) {
        this.input = input;
        /*Global.telemetry.addData("error", getTarget()-input); //For testing
        Global.telemetry.addData("input", input);
        Global.telemetry.addData("target", getTarget());
        Global.telemetry.addData("tolerance", errorTolerance);*/
        if (Math.abs(getTarget()-input) > errorTolerance) {
            lastOutOfRange = System.nanoTime();
            withinRange = false;
        }
        else withinRange = true;
    }

    /**
     * Returns the error tolerance
     * @return The current error tolerance
     */
    public double getErrorTolerance() {
        return errorTolerance;
    }

    /**
     * Set the error tolerance
     * @param errorTolerance Difference between target value and actual value that you are willing to live with
     */
    public void setErrorTolerance(double errorTolerance) {
        this.errorTolerance = errorTolerance;
    }

    /**
     * Returns the time threshold
     * @return The current time threshold
     */
    public long getTimeThreshold() {
        return timeThreshold;
    }

    /**
     * Sets the time threshold
     * @param timeThreshold The amount of time the robot must stay within the error tolerance to be considered finished
     */
    public void setTimeThreshold(long timeThreshold) {
        this.timeThreshold = timeThreshold;
    }

    /**
     * Returns the last time the robot was outside of the error tolerance
     * @return The last time the robot was outside of the error tolerance
     */
    public long getLastOutOfRange() {
        return lastOutOfRange;
    }

    /**
     * Returns whether of not the robot is currently within the error threshold
     * @return Whether or not the robot is currently within the error threshold
     */
    public boolean isWithinRange() {
        return withinRange;
    }

    /**
     * Returns the current sensor value
     * @return The current sensor value
     */
    public double getInput() {
        return input;
    }
}
