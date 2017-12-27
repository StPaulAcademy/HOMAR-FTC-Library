package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * Basic layout of a robot drivetrain.
 */

abstract public class Drivetrain {
    private double velocity = 0; //creates the velocity variable
    public DcMotor[] motors; //creates the motor array
    private double[] motorPowers; //creates the motorPowers array

    /**
     *
     * @param motors
     */
    public Drivetrain(DcMotor[] motors) {
        this.motors = motors;
    }

    /**
     *
     * @return
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     *
     * @param velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        updateMotorPowers();
    }

    /**
     *
     */
    protected void updateMotorPowers() {
        motorPowers = calculateMotorPowers();
        for (int i = 0; i < motorPowers.length; i++) {
            motors[i].setPower(motorPowers[i]);
        }
    }

    /**
     *
     * @return
     */
    abstract protected double[] calculateMotorPowers();
}
