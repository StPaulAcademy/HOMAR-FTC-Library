package edu.spa.ftclib.internal.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

/**
 * Created by Gabriel on 2017-12-27.
 * A {@link Holonomic holonomic} drivetrain with four wheels.
 * {@link MecanumDrivetrain} and {@link OmniwheelDrivetrain} extend this; we wrote this because most of that code is very similar.
 */

abstract public class HolonomicFourWheelDrivetrain extends Drivetrain implements Holonomic, Rotatable, Positionable {
    /**
     * Rotation velocity (the amount of power that should be added to each of the motors to make the drivetrain rotate)
     */
    private double rotation = 0;
    /**
     * The direction of travel
     */
    private double course = 0;
    /**
     * How far we're trying to go (when driving by position)
     */
    private double targetPosition = 0;

    /**
     * Stores how far each wheel has to go to get the drivetrain to a specific position
     * @see #setTargetPosition
     * @see #getCurrentPosition
     */
    private double[] wheelTargetPositions = new double[4];
    /**
     * The {@link RunMode RunModes} of each of the motors (used after changing the RunModes to move to a position)
     */
    private RunMode[] runModes = new RunMode[4];
    /**
     * A list of angles related to wheel and drivetrain geometry that must be defined by subclasses
     */
    private final double[] wheelAngles;

    /**
     *
     * @param motors The array of motors included in the drivetrain
     * @param wheelAngles A list of four angles corresponding to each wheel related to wheel and drivetrain geometry; these should be defined by the subclass and are passed to {@link #calculateWheelCoefficient} (which also must be defined by the subclass)
     */
    public HolonomicFourWheelDrivetrain(DcMotor[] motors, double[] wheelAngles) {
        super(motors);
        this.wheelAngles = wheelAngles;
    }

    /**
     * Sets the drivetrain's angular (rotational) velocity.
     * @param rotation The velocity that you want to rotate the robot at, between -1 (full power clockwise) and 1 (full power counterclockwise) — 0 for no rotation
     */
    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
        updateMotorPowers();

    }

    /**
     * Gets the drivetrain's target angular (rotational) velocity.
     * @return the rotation velocity the robot was given, between -1 (full power clockwise) and 1 (full power counterclockwise) — 0 for no rotation
     */
    @Override
    public double getRotation() {
        return rotation;
    }

    /**
     * Sets the direction you want the robot to move along.
     * @param course The course in radians, where 0 is forwards and {@link Math#PI}/2 is directly to the left.
     */
    @Override
    public void setCourse(double course) {
        this.course = course;
        updateMotorPowers(); //sends power to the motors based on the course given
    }

    /**
     * Gets the direction the robot is supposed to be moving along.
     * @return The course in radians, where 0 is forwards and {@link Math#PI}/2 is directly to the left.
     */
    @Override
    public double getCourse() {
        return course;
    }

    /**
     * Re-calculate the powers for each of the motors (called after a velocity, rotation, or course change).
     * @return an array of motor powers
     */
    protected double[] calculateMotorPowers() {
        double[] motorPowers = new double[4]; //creates a array to put the motor powers into
        for (int i = 0; i < 4; i++) {
            motorPowers[i] = calculateWheelPower(course, getVelocity(), rotation, wheelAngles[i]);
            motors[i].setPower(motorPowers[i]);
        }
        return motorPowers;
    }

    /**
     *
     * @param course the angle that you want the robot to move along
     * @param wheelAngle the array of angles that the actual moving parts of the wheels are at
     * @return a number between zero and one, which says what percentage of the speed a wheel should move at. Is then multiplied by the velocity
     */
    abstract double calculateWheelCoefficient(double course, double wheelAngle);

    /**
     * Takes a wheel coefficient from the subclass and calculates a wheel power from it.
     * @param course the angle that you want the robot to more along
     * @param velocity the velocity you want the robot to move with
     * @param rotationPower the velocity that you want to rotate the robot by.
     *                 Counterclockwise is positive and clockwise is negative.
     *                 Zero is if you don't want to rotate the robot.
     * @param wheelAngle the angle of the actual moving part of the wheel
     * @return the power the motor is supposed to move with, which is then sent to the motor
     */
    private double calculateWheelPower(double course, double velocity, double rotationPower, double wheelAngle) {
        return calculateWheelCoefficient(course, wheelAngle)*velocity+rotationPower;
    }

    /**
     * @param targetPosition the position that you want the robot to move to
     */
    @Override
    public void setTargetPosition(double targetPosition) {
        for (int i = 0; i < runModes.length; i++) {
            runModes[i] = motors[i].getMode();  //Save the RunModes so we can restore them later
        }
        this.targetPosition = targetPosition;
        for (DcMotor motor : motors) motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        for (DcMotor motor : motors) motor.setMode(RunMode.RUN_TO_POSITION);
        for (int i = 0; i < motors.length; i++) {   //Calculate how far each wheel has to go to get the drivetrain to a specific position
            wheelTargetPositions[i] = targetPosition*calculateWheelCoefficient(course, wheelAngles[i]);
            motors[i].setTargetPosition((int)(wheelTargetPositions[i]+0.5));    //Round to the nearest int because setTargetPosition only accepts ints
        }
        updateMotorPowers();
    }

    /**
     * @return the current position of the robot
     */
    @Override
    public double getCurrentPosition() {    //We calculate the current position from the average of how far each motor has gone
        double amountDone = 0;  //Sum of the fractions of the necessary distance each of the four motors has gone
        for (int i = 0; i < 4; i++) {
            amountDone += motors[i].getCurrentPosition()/wheelTargetPositions[i];
        }
        return amountDone/4*targetPosition; //The current position is the average fraction of the necessary distance across all four motors times the targetPosition
    }

    /**
     * @return the position the robot is supposed to move to (the Target Position)
     */
    @Override
    public double getTargetPosition() {
        return targetPosition;
    }

    /**
     * Empty — we use the built-in, automatic PID controller to move the motors, so there's nothing to update here.
     */
    @Override
    public void updatePosition() {
    }

    /**
     * Use this as a loop condition (with {@link #updatePosition in the loop body) if you want to move to a specific position and then move on to other code.
     * If the drivetrain never seems to stop positioning, use {@link com.qualcomm.robotcore.hardware.DcMotorEx#setTargetPositionTolerance} on each of your motors to make them less perfectionistic.
     * @return Whether or not the drivetrain is still moving towards the target position
     */
    @Override
    public boolean isPositioning() {
        for (DcMotor motor : motors) {
            if (motor.isBusy()) return true;    //If any of the motors is still busy, we are still positioning
        }
        return false;
    }

    /**
     * Resets encoders and changes the {@link RunMode RunModes} to what they were before
     */
    @Override
    public void finishPositioning() {
        for (DcMotor motor : motors) motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        for (int i = 0; i < motors.length; i++) motors[i].setMode(runModes[i]);
    }

    @Override
    public void position() {
        while (isPositioning()) updatePosition();
        finishPositioning();
    }

    /**
     * Depending on the encoder type, the number of ticks per rotation can vary. This method uses motor configuration data to calculate that number.
     * @return the number of encoder ticks per rotation
     */
    @Override
    public double getTicksPerUnit() {  //The motors better have the same number of ticks per rotation (perhaps a future version can make this unnecessary), but get the average anyway
        double ticksPerUnit = 0;
        for (DcMotor motor : motors) ticksPerUnit += motor.getMotorType().getTicksPerRev();
        ticksPerUnit /= motors.length;
        return ticksPerUnit;
    }
}
