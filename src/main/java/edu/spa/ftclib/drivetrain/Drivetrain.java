package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * Basic layout of a robot drivetrain.
 */

abstract public class Drivetrain {
    private double velocity = 0;
    public DcMotor[] motors;
    private double[] motorPowers;
    public Drivetrain(DcMotor[] motors) {
        this.motors = motors;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
        updateMotorPowers();
    }

    protected void updateMotorPowers() {
        motorPowers = calculateMotorPowers();
        for (int i = 0; i < motorPowers.length; i++) {
            motors[i].setPower(motorPowers[i]);
        }
    }

    abstract protected double[] calculateMotorPowers();
}
