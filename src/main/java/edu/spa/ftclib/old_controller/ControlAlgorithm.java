package edu.spa.ftclib.old_controller;

/**
 * Created by Gabriel on 2017-12-28.
 * The fancy math that gives the system a usable output.
 * @see PIDController
 */

public interface ControlAlgorithm extends Controller {
    /**
     * Give the control algorithm an input, which allows it to do fancy math and give an output.
     * @param input The input into the fancy math, such as the heading from a gyro.
     */
    void input(double input);
}
