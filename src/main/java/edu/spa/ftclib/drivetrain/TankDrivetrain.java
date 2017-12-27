package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A simple drivetrain with two drive wheels.
 */

public class TankDrivetrain extends Drivetrain implements Rotatable {
    private double rotation = 0;

    public TankDrivetrain(DcMotor[] motors) {
        super(motors);
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void setVelocity(double velocity) {
        super.setVelocity(velocity);

    }

    protected double[] calculateMotorPowers() {
        double[] motorPowers = new double[2];
        motorPowers[0] = getVelocity()-rotation;
        motorPowers[1] = getVelocity()+rotation;
        return motorPowers;
    }
}
