package edu.spa.ftclib.controller;

/**
 * Created by Gabriel on 2017-12-28.
 */

public abstract class SensorController implements UpdateableController {
    ControlAlgorithm algorithm;
    public SensorController(ControlAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
    protected abstract double getSensorValue();
    public void update() {
        algorithm.input(getSensorValue());
    }
    public double output() {
        return algorithm.output();
    }

    @Override
    public void setTarget(double target) {
        algorithm.setTarget(target);
    }

    @Override
    public double getTarget() {
        return algorithm.getTarget();
    }
}
