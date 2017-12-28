package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain whose heading it is possible to set (presumably using a gyro or some other sensor).
 * We couldn't think of a better name.
 * All drivetrains with settable headings must be rotatable.
 */

public interface Headingable extends Rotatable {
    /**
     *
     * @param heading the angle that you want the robot to move towards
     */
    void setHeading(double heading);

    /**
     *
     * @return the angle the robot is currently facing
     */
    double getCurrentHeading();

    /**
     *
     * @return the heading that the robot is currently trying to get to or move along. What was passed in using @see setHeading
     */
    double getTargetHeading();
}
