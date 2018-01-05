package edu.spa.ftclib.internal.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Gabriel on 2017-12-27.
 * Basic layout of a robot drivetrain. We use this in our {@link MecanumDrivetrain Mecanum}, {@link OmniwheelDrivetrain omniwheel}, and {@link TankDrivetrain tank} drivetrains, and you can implement it if you create other drivetrains.
 */

abstract public class Drivetrain {
    /**
     * All drivetrains should have a velocity.
     */
    private double velocity = 0;
    /**
     * All the drive motors included in the drivetrain.
     */
    public DcMotor[] motors; //creates the motor array
    /**
     * The list of powers each of the corresponding motors in the {@link #motors} array should be set to.
     */
    private double[] motorPowers;

    /**
     * Makes a new drivetrain with a set of motors.
     * @param motors The array of motors included in the drivetrain
     */
    public Drivetrain(DcMotor[] motors) {
        this.motors = motors;
    }

    /**
     * Gets the velocity that the robot has been told to move with.
     * @return The velocity that the robot has been told to move with
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity at which the robot should move.
     * @param velocity The velocity that you want the robot to move with
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        updateMotorPowers(); //sets the motor powers based on the velocity given
    }

    /**
     * Calculates the motor powers and then sends the motor powers to the motors to move the robot.
     */
    protected void updateMotorPowers() {
        motorPowers = calculateMotorPowers();
        for (int i = 0; i < motorPowers.length; i++) {
            motors[i].setPower(motorPowers[i]);
        }
    }

    /**
     *
     * @return The motorPowers array, which will then be sent to the motors to tell them how fast to move
     */
    abstract protected double[] calculateMotorPowers();
}
