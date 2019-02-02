package edu.spa.ftclib.internal.controller;

//Created by Gabriel on 2018-01-02.

/**
 * A controller algorithm, where the derivative aspect of the controller is provided by the robot,
 * instead of the program calculating it itself.
 */

public interface DerivativeAlgorithm extends Targetable {
    /**
     * Provides the algorithm with the derivative from the sensor
     * @param derivative The value the sensor provides for the derivative
     */
    void setDerivative(double derivative);
}
