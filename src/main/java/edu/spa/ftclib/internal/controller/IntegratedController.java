package edu.spa.ftclib.internal.controller;

import edu.spa.ftclib.internal.sensor.DerivativeSensor;
import edu.spa.ftclib.internal.sensor.Sensor;

//Created by Gabriel on 2018-01-02.

/**
 *
 */

public class IntegratedController implements Controller, Targetable {
    /**
     * The sensor providing the values for the robot
     */
    public Sensor sensor;
    /**
     * The algorithm doing the math to control the robot
     */
    public ControlAlgorithm algorithm;
    /**
     * The value the robot is trying to reach
     */
    private double target;

    /**
     * Sets up the controller
     * @param sensor The sensor providing the values from the robot
     * @param algorithm The algorithm being used to do the math
     */
    public IntegratedController(Sensor sensor, ControlAlgorithm algorithm) {
        this.sensor = sensor;
        this.algorithm = algorithm;
    }

    public void setTarget(double target) {
        this.target = target;
        algorithm.setTarget(target);
        update();
    }

    public double getTarget() {
        return target;
    }

    public double update() {
        if (sensor instanceof DerivativeSensor && algorithm instanceof DerivativeAlgorithm) ((DerivativeAlgorithm) algorithm).setDerivative(((DerivativeSensor) sensor).getDerivative());   //If both the sensor and the algorithm can handle derivatives, pass it along.
        double sensorValue = sensor.getValue();
        algorithm.input(sensorValue);
        return sensorValue;
    }

    @Override
    public double output() {
        return algorithm.output();
    }

    public double getSensorValue() {
        return sensor.getValue();
    }
}
