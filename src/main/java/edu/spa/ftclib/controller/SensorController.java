package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-28.
 * An updateable controller which uses a sensor to get its inputs.
 */

public abstract class SensorController implements UpdateableController {
    /**
     * Chooses which control algorithm to use.
     * @see PIDController
     */
    ControlAlgorithm algorithm;

    /**
     * Sets the algorithm being used to be the algorithm specified by the user.
     * @param algorithm Which control algorithm should be used
     */
    public SensorController(ControlAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Gets the value from the sensor to be used as an input.
     * @return The input value from the sensor
     */
    public abstract double getSensorValue();

    /**
     * Gives the sensor value to the algorithm so that the algorithm can calculate a usable value for the system.
     */
    public void update() {
        algorithm.input(getSensorValue());
    }

    /**
     * Returns the value from the algorithm for use by the system.
     * @return The final calculation from the algorithm
     */
    public double output() {
        return algorithm.output();
    }

    /**
     * Recieves a target value from the user.
     * @param target The value the system is trying to reach or maintain
     */
    @Override
    public void setTarget(double target) {
        algorithm.setTarget(target);
    }

    /**
     * Gets the target value the system is currently trying to reach or maintain.
     * @return The current target value
     */
    @Override
    public double getTarget() {
        return algorithm.getTarget();
    }
}
