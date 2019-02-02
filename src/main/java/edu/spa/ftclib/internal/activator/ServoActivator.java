package edu.spa.ftclib.internal.activator;

import com.qualcomm.robotcore.hardware.Servo;

//Created by Gabriel on 2017-11-02; edited substantially on 2017-12-28.

/**
 * Use this if you have a servo that you normally only set to two positions (e.g. an arm that holds something in place).
 * It will store the positions, and then you can call setActivated(true) and setActivated(false) to automatically activate and deactivate the servo.
 * The servo is public, so if you ever need to move it to a different position or change a setting you can do that.
 */

public class ServoActivator implements Activable {
    /**
     * The current state of the servo. Is it supposed to be currently activated?
     */
    private boolean activated = false;

    /**
     * The servo that is being activated.
     */
    public Servo servo;

    /**
     * The activated position of the servo.
     */
    private double activatedPosition;

    /**
     * The deactivated position of the servo.
     */
    private double deactivatedPosition;

    /**
     * The constructor to create the servo activator.
     * @param servo The servo being activated and deactivated
     * @param activatedPosition The position of the servo when activated
     * @param deactivatedPosition The position of the servo when deactivated
     */
    public ServoActivator(Servo servo, double activatedPosition, double deactivatedPosition) {
        this.servo = servo;
        this.activatedPosition = activatedPosition;
        this.deactivatedPosition = deactivatedPosition;
    }

    /**
     * Activate or deactivate the servo.
     * @param activated Whether or not the thing being activated should be activated or not
     */
    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
        if (activated) servo.setPosition(activatedPosition);
        else servo.setPosition(deactivatedPosition);
    }

    /**
     * Says if the servo is currently activated or not.
     * @return If the servo is currently activated
     */
    @Override
    public boolean getActivated() {
        return activated;
    }

    /**
     * Sets the servo to its activated state
     */
    @Override
    public void activate() {
        setActivated(true);
    }

    /**
     * Sets the servo to its deactivated state
     */
    @Override
    public void deactivate() {
        setActivated(false);
    }

    /**
     * Gets the activated servo position.
     * @return The servo position when activated
     */
    public double getActivatedPosition() {
        return activatedPosition;
    }

    /**
     * Sets a new activated servo position.
     * @param activatedPosition The new position of the servo when activated
     */
    public void setActivatedPosition(double activatedPosition) {
        this.activatedPosition = activatedPosition;
        if (activated)  servo.setPosition(activatedPosition);   //Update servo position if necessary
    }

    /**
     * Gets the deactivated servo position.
     * @return The servo position when deactivated
     */
    public double getDeactivatedPosition() {
        return deactivatedPosition;
    }

    /**
     * Sets a new deactivated servo position.
     * @param deactivatedPosition The new position of the servo when deactivated
     */
    public void setDeactivatedPosition(double deactivatedPosition) {
        this.deactivatedPosition = deactivatedPosition;
        if (!activated) servo.setPosition(deactivatedPosition); //Update servo position if necessary
    }
}
