package edu.spa.ftclib.internal.controller;

//import edu.spa.ftclib.internal.Global;    //For testing

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

    public ErrorTimeThresholdFinishingAlgorithm(ErrorTimeThresholdFinishingAlgorithm original, boolean copyState) {
        this(original.getErrorTolerance(), original.getTimeThreshold());
        if (copyState) {
            input = original.getInput();
            lastOutOfRange = original.getLastOutOfRange();
            withinRange = original.isWithinRange();
        }
    }

    public ErrorTimeThresholdFinishingAlgorithm(ErrorTimeThresholdFinishingAlgorithm original) {
        this(original, false);
    }

    /**
     * Updated the controller algorithm, and if the heading is outside of the tolerance, it sets the lastOutOfRange to the current time.
     */

    @Override
    public boolean finished() {
        /*Global.telemetry.addData("finishing", System.nanoTime()-lastOutOfRange);  //For testing
        //Global.telemetry.addData("threshold", timeThreshold);
        Global.telemetry.addData("withinRange", withinRange);*/
        return ((System.nanoTime()-lastOutOfRange) > timeThreshold) && withinRange;
    }

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

    public double getErrorTolerance() {
        return errorTolerance;
    }

    public void setErrorTolerance(double errorTolerance) {
        this.errorTolerance = errorTolerance;
    }

    public long getTimeThreshold() {
        return timeThreshold;
    }

    public void setTimeThreshold(long timeThreshold) {
        this.timeThreshold = timeThreshold;
    }

    public long getLastOutOfRange() {
        return lastOutOfRange;
    }

    public boolean isWithinRange() {
        return withinRange;
    }

    public double getInput() {
        return input;
    }
}
