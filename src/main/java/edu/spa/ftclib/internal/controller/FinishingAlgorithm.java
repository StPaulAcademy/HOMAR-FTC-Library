package edu.spa.ftclib.internal.controller;

//Created by Gabriel on 2018-01-02.

/**
 * Any algorithm that has a target it is trying to reach and will then count as finished.
 */

public abstract class FinishingAlgorithm implements Finishable, TargetableAlgorithm {
    /**
     * The value the robot is trying to reach.
     */
    private double target;

    /**
     * Sets the target the robot is trying to reach
     * @param target The value the robot is trying to reach
     */
    public void setTarget(double target) {
        this.target = target;
    }

    /**
     * Returns the value the robot is trying to reach
     * @return The value the robot is trying to reach
     */
    public double getTarget() {
        return target;
    }
}
