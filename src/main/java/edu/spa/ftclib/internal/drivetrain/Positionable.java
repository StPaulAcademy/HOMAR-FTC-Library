package edu.spa.ftclib.internal.drivetrain;

/**
 * Created by Gabriel on 2017-12-27.
 * A drivetrain that can move to a specific position (presumably using encoders or some other sensor).
 */

public interface Positionable {
    /**
     *
     * @param position the position that you want the robot to move to
     */
    void setTargetPosition(double position);

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

    /**
     * Recalculate motor powers to maintain or move towards the target position
     */
    void updatePosition();

    /**
     * Use this as a loop condition (with {@link #updatePosition in the loop body) if you want to move to a specific position and then move on to other code.
     * @return Whether or not the drivetrain is still moving towards the target position
     */
    boolean isPositioning();

    /**
     * If there are any settings (motor {@link com.qualcomm.robotcore.hardware.DcMotor.RunMode RunModes}, etc.) you changed to position, change them back in this method.
     */
    void finishPositioning();

    void position();

    /**
     * All (digital) positioning systems have a minimum unit of position (e.g. an encoder tick); this is what the position should be measured in. (The reason it is a double instead of an int is to eliminate unnecessary rounding error in special cases like omniwheels, where moving x ticks in a certain direction often has the motors actually spinning a different number of ticks.) Many positioning systems also have another natural unit of measurement (e.g. one rotation for an encoder); this method should return the number of ticks in this unit. If there is no natural unit besides the tick (e.g. for ultrasonic sensors), a standard unit such as a centimeter may be used.
     * @return the number of indivisible ticks per natural/standard unit of distance
     */
    double getTicksPerUnit();
}
