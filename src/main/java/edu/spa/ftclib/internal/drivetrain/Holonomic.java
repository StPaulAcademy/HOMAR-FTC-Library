package edu.spa.ftclib.internal.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move in any (horizontal) direction. Does not specify rotation.
 * @see HolonomicFourWheelDrivetrain
 * @see MecanumDrivetrain
 * @see OmniwheelDrivetrain
 */

public interface Holonomic {
    /**
     * Sets the direction you want the robot to move along.
     * @param course The course in radians, where 0 is forwards and {@link Math#PI}/2 is directly to the left.
     */
    void setCourse(double course);

    /**
     * Gets the direction the robot is supposed to be moving along.
     * @return The course in radians, where 0 is forwards and {@link Math#PI}/2 is directly to the left.
     */
    double getCourse();
}
