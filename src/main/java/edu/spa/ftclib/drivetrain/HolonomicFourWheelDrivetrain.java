package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A holonomic drivetrain with four wheels.
 * MecanumDrivetrain and OmniwheelDrivetrain extend this; we wrote this because most of that code is very similar.
 */

abstract public class HolonomicFourWheelDrivetrain extends Drivetrain implements Holonomic, Rotatable {
    private double rotation = 0;
    private double course = 0;
    private final double[] wheelAngles;

    public HolonomicFourWheelDrivetrain(DcMotor[] motors, double[] wheelAngles) {
        super(motors);
        this.wheelAngles = wheelAngles;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
        updateMotorPowers();
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setCourse(double course) {
        this.course = course;
        updateMotorPowers();
    }

    @Override
    public double getCourse() {
        return course;
    }

    @Override
    public void setVelocity(double velocity) {
        super.setVelocity(velocity);
        updateMotorPowers();
    }

    protected double[] calculateMotorPowers() {
        double[] motorPowers = new double[4];
        for (int i = 0; i < 4; i++) {
            motorPowers[i] = calculateWheelPower(course, getVelocity(), rotation, wheelAngles[i]);
            motors[i].setPower(motorPowers[i]);
        }
        return motorPowers;
    }

    abstract double calculateWheelCoefficient(double course, double wheelAngle);
    private double calculateWheelPower(double course, double velocity, double rotationPower, double wheelAngle) {
        return calculateWheelCoefficient(course, wheelAngle)*velocity+rotationPower;
    }
}
