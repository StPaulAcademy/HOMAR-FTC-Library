package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * A simple drivetrain with two drive wheels.
 */

public class TankDrivetrain extends Drivetrain implements Rotatable {
    private double rotation = 0;

    /**
     * The constructor for the class.
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public TankDrivetrain(DcMotor[] motors) {
        super(motors);
    }

    /**
     *
     * @param rotation the velocity that you want to rotate the robot by.
     *                 Counterclockwise is positive and clockwise is negative.
     *                 Zero is if you don't want to rotate the robot.
     */
    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
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
     * @param velocity the velocity at which the robot is supposed to move forward or backwards
     */
    @Override
    public void setVelocity(double velocity) {
        super.setVelocity(velocity);

    }

    /**
     * Calculates the motor powers. It combines the velocity you gave it and the rotation that you gave it
     * @return the motorPowers array, which will then be sent to the motors to tell them how fast to move
     */
    protected double[] calculateMotorPowers() {
        double[] motorPowers = new double[2]; //create a new array to hold the motor powers
        motorPowers[0] = getVelocity()-rotation; //calculate the motor power for the left wheel
        motorPowers[1] = getVelocity()+rotation; //calculate the motor power for the right wheel
        return motorPowers;
    }
}
