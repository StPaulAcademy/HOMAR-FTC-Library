package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move in any (horizontal) direction. Does not specify rotation.
 */

public interface Holonomic {
    /**
     *
     * @param course the direction you want the robot to move along in radians
     */
    void setCourse(double course);

    /**
     *
     * @return the direction the robot is supposed to be moving along in radians
     */
    double getCourse();
}
