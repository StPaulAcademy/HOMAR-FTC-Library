package edu.spa.ftclib.controller;

import edu.spa.ftclib.sensor.Sensor;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class FinishableIntegratedController extends IntegratedController {
    FinishingAlgorithm finisher;
    FinishableIntegratedController(Sensor sensor, ControlAlgorithm algorithm, FinishingAlgorithm finisher) {
        super(sensor, algorithm);
        this.finisher = finisher;
    }

    @Override
    public void update() {
        double input = sensor.getValue();
        algorithm.input(input);
        finisher.input(input);
    }

    public boolean finished() {
        return finisher.finished();
    }
}
