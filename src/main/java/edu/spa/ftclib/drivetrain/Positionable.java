package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move to a specific position (presumably using encoders or some other sensor).
 */

public interface Positionable {
    /**
     *
     * @param position the position that you want the robot to move to
     */
    void setPosition(double position);

    /**
     *
     * @return the current position of the robot
     */
    double getCurrentPosition();

    /**
     *
     * @return the position the robot is supposed to move to (the Target Position)
     */
    double getTargetPosition();
}
