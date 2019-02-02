package edu.spa.ftclib.internal.controller;

//Created by Gabriel on 2018-01-02.

/**
 * Outputs a value for the robot to use to control some component of the robot
 */

public interface Controller {
    /**
     * Returns the output value to send to the thing you wish to control
     * @return The output value of the control algorithm
     */
    double output();
}
