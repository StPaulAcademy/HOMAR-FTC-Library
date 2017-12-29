package edu.spa.ftclib.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.controller.SensorController;

/**
 * Created by Gabriel on 2017-12-29.
 */

public class HeadingableOmniwheelDrivetrain extends OmniwheelDrivetrain implements Headingable {
    private static final double DEFAULT_HEADING_TOLERANCE = Math.PI/50;
    /**
     * How long (in nanoseconds) the measured heading must be within {@link #DEFAULT_HEADING_TOLERANCE} of the target heading before {@link #isRotating} is false
     */
    private static final long DEFAULT_HEADING_DURATION = (long)1E9;
    private SensorController controller;    //TODO: maybe make this an UpdateableController?
    private double targetHeading = 0;
    private double headingTolerance;
    private double headingDuration;
    private long lastOutOfRange;

    /**
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public HeadingableOmniwheelDrivetrain(DcMotor[] motors, SensorController controller) {
        this(motors, controller, DEFAULT_HEADING_TOLERANCE, DEFAULT_HEADING_DURATION);
    }

    /**
     * @param motors the array of motors that you give the constructor so that it can find the hardware
     */
    public HeadingableOmniwheelDrivetrain(DcMotor[] motors, SensorController controller, double headingTolerance, double headingDuration) {
        super(motors);
        this.controller = controller;
        this.headingTolerance = headingTolerance;
        this.headingDuration = headingDuration;
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
        if (Math.abs(targetHeading-getCurrentHeading()) > headingTolerance) lastOutOfRange = System.nanoTime();
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
        return (System.nanoTime()-lastOutOfRange) > headingDuration;    //If the last time the measured heading was more than headingTolerance away from the target heading was more than headingDuration nanoseconds ago, the rotation is done
    }

    /**
     * Empty — we don't change any setting that need to be changed back
     */
    @Override
    public void finishRotating() {
    }
}
