package edu.spa.ftclib.old_controller;

/**
 * Created by Gabriel on 2/25/2017; edited substantially on 2017-12-28.
 */

public class PIDController implements ControlAlgorithm {
    public final double KP, KI, KD;
    private double error = 0;
    private double target = 0;
    private double integral, derivative;
    private long timeAtUpdate;

    public double getIntegral() {
        return integral;
    }

    public double getDerivative() {
        return derivative;
    }

    public PIDController (final double KP, final double KI, final double KD) {
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
        timeAtUpdate = System.nanoTime();
        integral = 0;
    }
    public void resetIntegration() {
        integral = 0;
    }

    private double nanoToUnit(long nano) {  //Used to convert nanoseconds to seconds
        return nano/1E9f;
    }

    @Override
    public void setTarget(double target) {
        this.target = target;
    }

    @Override
    public double getTarget() {
        return target;
    }

    @Override
    public double output() {
        return KP*error+KI*integral+KD*derivative;
    }

    @Override
    public void input(double input) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += error*nanoToUnit(newTime-timeAtUpdate);
        derivative = ((target-input-error)/nanoToUnit(newTime-timeAtUpdate));
        timeAtUpdate = newTime;
    }

    public void inputPD(double input, double inputD) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += error*nanoToUnit(newTime-timeAtUpdate);
        derivative = inputD;
        timeAtUpdate = newTime;
    }

    public void inputPI(double input, double inputI) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += inputI;
        derivative = ((target-input-error)/nanoToUnit(newTime-timeAtUpdate));
        timeAtUpdate = newTime;
    }

    public void inputPID(double input, double inputI, double inputD) {
        error = target-input;
        integral = inputI;
        derivative = inputD;
        timeAtUpdate = System.nanoTime();
    }
}
