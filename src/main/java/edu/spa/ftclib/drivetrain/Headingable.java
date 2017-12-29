package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain whose heading it is possible to set (presumably using a gyro or some other sensor).
 * We couldn't think of a better name for this interface.
 * Extends {@link Rotatable}, because all drivetrains with settable headings must be rotatable.
 */

public interface Headingable extends Rotatable {
    /**
     * Set the target heading of the drivetrain.
     * @param heading the angle that you want the drivetrain to move towards
     */
    void setHeading(double heading);

    /**
     * Get the current heading of the drivetrain (presumably a value from a sensor and not necessarily the drivetrain's target heading).
     * @return the angle the drivetrain is currently facing
     */
    double getCurrentHeading();

    /**
     * Get the target heading of the drivetrain (not necessarily the actual, current heading of the drivetrain), passed in using {@link #setHeading}.
     * @return the heading that the robot is currently trying to get to or move along.
     */
    double getTargetHeading();
}
