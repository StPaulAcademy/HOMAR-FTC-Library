package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.old_controller.HeadingController;

/**
 * Created by Michaela on 2018-1-2.
 * A Tank Drivetrain with some sort of heading sensor, such as a gyro. Uses the sensor and a controller specified by the user to control the system.
 */

public class HeadingableTankDrivetrain extends TankDrivetrain implements Headingable {
    /**
     * The controller being used.
     * @see edu.spa.ftclib.old_controller.PIDController
     * @see edu.spa.ftclib.old_controller.PIController
     * @see edu.spa.ftclib.old_controller.Controller
     */
    private HeadingController controller;

    /**
     * The last time the heading was not within the heading tolerance.
     * @see HeadingController #DEFAULT_HEADING_TOLERANCE
     */
    private long lastOutOfRange;

    /**
     * The target heading
     */
    private double targetHeading = 0;
    /**
     * The constructor for the drivetrain.
     * @param motors The array of motors that you give the constructor so that it can find the hardware
     * @param controller Which controller you want the system to use.
     *                   @see edu.spa.ftclib.old_controller.PIDController
     */
    public HeadingableTankDrivetrain(DcMotor[] motors, HeadingController controller) {
        super(motors);
        this.controller = controller;
    }

    /**
     * Set the target heading of the drivetrain.
     *
     * @param targetHeading The angle that you want the drivetrain to move towards
     */
    @Override
    public void setTargetHeading(double targetHeading) {
        this.targetHeading = targetHeading;
    }

    /**
     * Get the current heading of the drivetrain (presumably a value from a sensor and not necessarily the drivetrain's target heading).
     *
     * @return The angle the drivetrain is currently facing
     */
    @Override
    public double getCurrentHeading() {
        return controller.getSensorValue();
    }

    /**
     * Get the target heading of the drivetrain (not necessarily the actual, current heading of the drivetrain), passed in using {@link #setTargetHeading}.
     *
     * @return The heading that the robot is currently trying to get to or maintain
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
     * Empty — we don't change any setting that needs to be changed back
     */
    @Override
    public void finishRotating() {
    }
}
