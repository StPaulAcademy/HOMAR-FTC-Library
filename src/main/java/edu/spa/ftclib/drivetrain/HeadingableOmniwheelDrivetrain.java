package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.old_controller.HeadingController;

/**
 * Created by Gabriel on 2017-12-29.
 */

public class HeadingableOmniwheelDrivetrain extends OmniwheelDrivetrain implements Headingable {
    private HeadingController controller;
    private long lastOutOfRange;
    private double targetHeading = 0;
    /**
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public HeadingableOmniwheelDrivetrain(DcMotor[] motors, HeadingController controller, double headingTolerance, double headingDuration) {
        super(motors);
        this.controller = controller;
    }

    /**
     * Set the target heading of the drivetrain.
     *
     * @param targetHeading the angle that you want the drivetrain to move towards
     */
    @Override
    public void setTargetHeading(double targetHeading) {
        this.targetHeading = targetHeading;
    }

    /**
     * Get the current heading of the drivetrain (presumably a value from a sensor and not necessarily the drivetrain's target heading).
     *
     * @return the angle the drivetrain is currently facing
     */
    @Override
    public double getCurrentHeading() {
        return controller.getSensorValue();
    }

    /**
     * Get the target heading of the drivetrain (not necessarily the actual, current heading of the drivetrain), passed in using {@link #setTargetHeading}.
     *
     * @return the heading that the robot is currently trying to get to or move along.
     */
    @Override
    public double getTargetHeading() {
        return targetHeading;
    }

    /**
     * Recalculate motor powers to maintain or move towards the target heading
     */
    @Override
    public void updateHeading() {
        controller.update();
        setRotation(controller.output());
    }

    /**
     * Use this as a loop condition (with {@link #updateHeading in the loop body) if you want to turn to a specific heading and then move on to other code.
     *
     * @return Whether or not the drivetrain is still rotating towards the target heading
     */
    @Override
    public boolean isRotating() {
        return !controller.finished();
    }

    /**
     * Empty — we don't change any setting that need to be changed back
     */
    @Override
    public void finishRotating() {
    }
}
