package edu.spa.ftclib.internal.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can rotate (practically any drivetrain).
 */

public interface Rotatable {
    /**
     *
     * @param rotation the velocity that you want to rotate the robot by.
     *                 Counterclockwise is positive and clockwise is negative.
     *                 Zero is if you don't want to rotate the robot.
     */
    void setRotation(double rotation);

    /**
     *
     * @return the rotation velocity the robot was given.
     */
    double getRotation();
}
