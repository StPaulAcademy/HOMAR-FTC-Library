package edu.spa.ftclib.internal.controller;

//Created by Gabriel on 2018-01-02.

/**
 * An algorithm you import a target value into, and then the robot using a variety of mathematical techniques determines an
 * output value, which is used to control the robot and keep it as close to the target value as possible.
 */

public abstract class ControlAlgorithm implements Controller, TargetableAlgorithm {
    /**
     * The value the robot is trying to hold
     */
    private double target;

    /**
     * Set the value the robot should hold
     * @param target The value you want the robot to hold
     */
    public void setTarget(double target) {
        this.target = target;
    }

    /**
     * Returns the value the robot is currently trying to hold
     * @return The current value the robot is trying to hold
     */
    public double getTarget() {
        return target;
    }
}
