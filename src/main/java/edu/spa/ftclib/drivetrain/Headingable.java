package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain whose heading it is possible to set (presumably using a gyro or some other sensor).
 * We couldn't think of a better name.
 * All drivetrains with settable headings must be rotatable.
 */

public interface Headingable extends Rotatable {
    void setHeading(double heading);
    double getCurrentHeading();
    double getTargetHeading();
}
