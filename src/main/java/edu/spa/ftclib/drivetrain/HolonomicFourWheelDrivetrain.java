package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A holonomic drivetrain with four wheels.
 * MecanumDrivetrain and OmniwheelDrivetrain extend this; we wrote this because most of that code is very similar.
 */

abstract public class HolonomicFourWheelDrivetrain extends Drivetrain implements Holonomic, Rotatable {
    private double rotation = 0; //create the rotation variable
    private double course = 0; //create the course variable
    private final double[] wheelAngles; //create the wheelAngles array

    /**
     *
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     * @param wheelAngles the array of angles that the actual moving parts of the wheels are at
     */
    public HolonomicFourWheelDrivetrain(DcMotor[] motors, double[] wheelAngles) {
        super(motors);
        this.wheelAngles = wheelAngles;
    }


    /**
     * @param rotation the velocity that you want to rotate the robot by.
     *                 Counterclockwise is positive and clockwise is negative.
     *                 Zero is if you don't want to rotate the robot.
     */
    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
        updateMotorPowers();

    }

    /**
     *
     * @return the rotation velocity the robot was given.
     */
    @Override
    public double getRotation() {
        return rotation;
    }

    /**
     *
     * @param course the angle that you want the robot to drive towards.
     *
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
     *
     * @param velocity the velocity that you want the robot to move with
     */
    @Override
    public void setVelocity(double velocity) {
        super.setVelocity(velocity);
        updateMotorPowers(); //sends power to the motors based on the velocity given
    }

    /**
     *
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
     * @return the power that the wheels should each move at, in an array
     */
    abstract double calculateWheelCoefficient(double course, double wheelAngle);
    private double calculateWheelPower(double course, double velocity, double rotationPower, double wheelAngle) {
        return calculateWheelCoefficient(course, wheelAngle)*velocity+rotationPower;
    }
}
