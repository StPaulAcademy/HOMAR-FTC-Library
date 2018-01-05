package edu.spa.ftclib.internal.controller;

/**
 * Created by Gabriel on 2018-01-02.
 */

public abstract class FinishingAlgorithm implements Finishable, TargetableAlgorithm {
    private double target;
    public void setTarget(double target) {
        this.target = target;
    }

    public double getTarget() {
        return target;
    }
}
