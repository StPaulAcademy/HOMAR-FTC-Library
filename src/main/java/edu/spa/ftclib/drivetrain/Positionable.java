package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move to a specific position (presumably using encoders or some other sensor).
 */

public interface Positionable {
    void setPosition(double position);
    double getCurrentPosition();
    double getTargetPosition();
}
