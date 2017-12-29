package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A {@link Holonomic holonomic} drivetrain with four wheels.
 * {@link MecanumDrivetrain} and {@link OmniwheelDrivetrain} extend this; we wrote this because most of that code is very similar.
 */

abstract public class HolonomicFourWheelDrivetrain extends Drivetrain implements Holonomic, Rotatable {
    /**
     * Rotation velocity (the amount of power that should be added to each of the motors to make the drivetrain rotate)
     */
    private double rotation = 0;
    /**
     * The direction of travel
     */
    private double course = 0;
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
     *
     * @return the course the robot is supposed to be moving along
     */
    @Override
    public double getCourse() {
        return course;
    }

    /**
     * Gets the direction the robot is supposed to be moving along.
     * @return The course in radians, where 0 is forwards and {@link Math#PI}/2 is directly to the left.
     */
    @Override
    public void setVelocity(double velocity) {
        super.setVelocity(velocity);
        updateMotorPowers(); //sends power to the motors based on the velocity given
    }

    /**
     * Re-calculate the powers for each of the motors (called after a velocity, rotation, or course change).
     * @return an array of motorPowers, which is then sent to the motors to move the robot
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
}
