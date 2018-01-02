package edu.spa.ftclib.old_controller;

/**
 * Created by Gabriel on 2/25/2017; edited substantially on 2017-12-28.
 * A PID controller, which allows the robot to move to a specific position and correct itself so that it lands right on (or very close).
 * It can also keep a robot on course if it is moving forward and is bumped.
 * The P stands for proportional, the I stands for integral, and the D stands for derivative, but you don't need to know calculus to use it.
 */

public class PIDController implements ControlAlgorithm {
    /**
     * The gain for the proportional part of the controller.
     */
    public final double KP;

    /**
     * The gain for the integral part of the controller.
     */
    public final double KI;

    /**
     * The gain for the derivative part of the controller.
     */
    public final double KD;

    /** The output from the proportional part of the controller. */
    private double error = 0;

    /**
     * The position the robot is trying to get to.
     */
    private double target = 0;

    /**
     * The ouput from the integral part of the controller.
     */
    private double integral;

    /**
     * The output from the derivative part of the controller.
     */
    private double derivative;

    /**
     * The time, in nanoseconds, of the last time of controller ran through the loop. Used when caluclating integral and derivative.
     */
    private long timeAtUpdate;

    /**
     * Gets the value of the integral part of the controller.
     * @return The output of the integral calculation
     */
    public double getIntegral() {
        return integral;
    }

    /**
     * Gets the value of the derivative part of the controller.
     * @return The output of the derivative calculation
     */
    public double getDerivative() {
        return derivative;
    }

    /**
     * The constructor for the PID Controller.
     * @param KP The gain for the derivative part of the controller
     * @param KI The gain for the integral part of the controller
     * @param KD The gain for the derivative part of the controller
     */
    public PIDController (final double KP, final double KI, final double KD) {
        this.KP = KP;
        this.KI = KI;
        this.KD = KD;
        timeAtUpdate = System.nanoTime();
        integral = 0;
    }

    /**
     * Sets the value of the integral to zero again.
     */
    public void resetIntegration() {
        integral = 0;
    }

    /**
     * Converts nanoseconds to seconds.
     * @param nano The time in nanoseconds
     * @return The time in seconds
     */
    private double nanoToUnit(long nano) {  //Used to convert nanoseconds to seconds
        return nano/1E9;
    }

    /**
     * Recieves a target value from the user.
     * @param target The value the system is trying to reach or maintain
     */
    @Override
    public void setTarget(double target) {
        this.target = target;
    }

    /**
     * Gets the target value the system is currently trying to reach or maintain.
     * @return The current target value
     */
    @Override
    public double getTarget() {
        return target;
    }

    /**
     * After doing a lot of math, the controller uses the inputs to calculate a value which the system is able to understand
     * and use to change an attribute such as velocity or course.
     * @return The calculations of all of the different parts of the controller multiplied by the respective gains
     */
    @Override
    public double output() {
        return KP*error+KI*integral+KD*derivative;
    }

    /**
     * Does all of the fancy math, requiring only an input into the proportional part of the controller.
     * @param input The proportional input into the fancy math, such as the heading from a gyro
     */
    @Override
    public void input(double input) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += error*nanoToUnit(newTime-timeAtUpdate);
        derivative = ((target-input-error)/nanoToUnit(newTime-timeAtUpdate));
        timeAtUpdate = newTime;
    }

    /**
     * Does all of the fancy math, requiring only an input into the proportional and derivative parts of the controller.
     * @param input The proportional input, such as the heading from a gyro
     * @param inputD The derivative input, such as the angular velocity
     */
    public void inputPD(double input, double inputD) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += error*nanoToUnit(newTime-timeAtUpdate);
        derivative = inputD;
        timeAtUpdate = newTime;
    }

    /**
     * Does all of the fancy math, requiring an input into the proportional and integral parts of the controller.
     * @param input The proportional input, such as the heading from a gyro
     * @param inputI The integral input, such as a position if you were trying to maintain a linear velocity
     */
    public void inputPI(double input, double inputI) {
        long newTime = System.nanoTime();
        error = target-input;
        integral += inputI;
        derivative = ((target-input-error)/nanoToUnit(newTime-timeAtUpdate));
        timeAtUpdate = newTime;
    }

    /**
     * Does all of the fancy math, requiring an input into all three parts of the controller.
     * @param input The proportional input, such as the heading from a gyro
     * @param inputI The integral input, such as a position if you were trying to maintain a linear velocity
     * @param inputD The derivative input, such as the angular velocity
     */
    public void inputPID(double input, double inputI, double inputD) {
        error = target-input;
        integral = inputI;
        derivative = inputD;
        timeAtUpdate = System.nanoTime();
    }
}
