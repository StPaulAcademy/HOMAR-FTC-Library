package edu.spa.ftclib.internal.activator;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

//Created by Gabriel on 2017-11-02; edited substantially on 2017-12-28.

/**
 * Use this if you have a motor that you normally only turn on and off (e.g. part of an intake mechanism).
 * It will store the power you want to send to the motor when it is on, and then you can call setActivated(true) and setActivated(false) to automatically activate and deactivate the motor.
 * The motor is public, so if you ever need to set it to run at a different power or change a setting you can do that.
 * Continuous rotation servos count as motors (DcMotorSimple is implemented by both DcMotor and CRServo).
 */

public class MotorActivator implements Activable {
    /**
     * The current state of the motor. Is it supposed to be currently activated?
     */
    private boolean activated = false;

    /**
     * The motor that is being activated. It includes normal DC motors and continuous rotation servos.
     */
    public DcMotorSimple motor;

    /**
     * The power the motor will run with when activated
     */
    private double activatedPower;

    /**
     * The constuctor for if you don't provide an activated power.
     * Sets the activated power to 1.
     * @param motor The motor that is being activated
     */
    public MotorActivator(DcMotorSimple motor) {
        this(motor, 1);
    }

    /**
     * The constructor for it an activated power is provided
     * @param motor The motor being activated
     * @param activatedPower The power the motor should run with when activated
     */

    public MotorActivator(DcMotorSimple motor, double activatedPower) {
        this.motor = motor;
        this.activatedPower = activatedPower;
    }

    /**
     * Sends the activated motor power to the motor if the motor is activated and turns the motor off if it is deactivated.
     * @param activated Whether or not the thing being activated should be activated or not
     */
    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
        if (activated) motor.setPower(activatedPower);
        else motor.setPower(0);
    }

    /**
     * Says if the motor is currently activated or not.
     * @return If the motor is currently activated
     */
    @Override
    public boolean getActivated() {
        return activated;
    }

    /**
     * Sets the motor to its activated state
     */
    @Override
    public void activate() {
        setActivated(true);
    }


    /**
     * Sets the motor to its activated state
     */
    @Override
    public void deactivate() {
        setActivated(false);
    }

    /**
     * Returns the activated power of the motor.
     * @return The power the motor moves with when activated
     */
    public double getActivatedPower() {
        return activatedPower;
    }

    /**
     * Updates the activatedPower to a new value.
     * @param activatedPower The new activated power that is wanted
     */
    public void setActivatedPower(double activatedPower) {
        this.activatedPower = activatedPower;
        if (activated) motor.setPower(activatedPower);  //Update motor power if necessary
    }
}
