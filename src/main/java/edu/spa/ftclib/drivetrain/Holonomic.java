package edu.spa.ftclib.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move in any (horizontal) direction. Does not specify rotation.
 */

public interface Holonomic {
    void setCourse(double course);
    double getCourse();
}
