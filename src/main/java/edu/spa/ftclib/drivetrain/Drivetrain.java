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
     * @param motors the array of motors on the robot
     */
    public Drivetrain(DcMotor[] motors) {
        this.motors = motors;
    }

    /**
     *
     * @return the velocity that the robot has been told to move with
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     *
     * @param velocity the velocity that you want the robot to move with
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
        updateMotorPowers(); //sets the motor powers based on the velocity given
    }

    /**
     * Calculates the motor powers and then sends the motor powers to the motors to move the robot
     */
    protected void updateMotorPowers() {
        motorPowers = calculateMotorPowers();
        for (int i = 0; i < motorPowers.length; i++) {
            motors[i].setPower(motorPowers[i]);
        }
    }

    /**
     *
     * @return the motorPowers array, which will then be sent to the motors to tell them how fast to move
     */
    abstract protected double[] calculateMotorPowers();
}
