package edu.spa.ftclib.activator;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Gabriel on 2017-11-02; edited substantially on 2017-12-28.
 * Use this if you have a motor that you normally only turn on and off (e.g. part of an intake mechanism).
 * It will store the power you want to send to the motor when it is on, and then you can call setActivated(true) and setActivated(false) to automatically activate and deactivate the motor.
 * Continuous rotation servos count as motors (DcMotorSimple is implemented by both DcMotor and CRServo).
 */

public class MotorActivator implements Activable {
    private boolean activated = false;
    public DcMotorSimple motor;  //This includes normal DC motors and continuous rotation servos.
    private double activatedPower;

    public MotorActivator(DcMotorSimple motor) {
        this(motor, 1);
    }

    public MotorActivator(DcMotorSimple motor, double activatedPower) {
        this.motor = motor;
        this.activatedPower = activatedPower;
    }

    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
        if (activated) motor.setPower(activatedPower);
        else motor.setPower(0);
    }

    @Override
    public boolean getActivated() {
        return activated;
    }

    public double getActivatedPower() {
        return activatedPower;
    }

    public void setActivatedPower(double activatedPower) {
        this.activatedPower = activatedPower;
        if (activated) motor.setPower(activatedPower);  //Update motor power if necessary
    }
}
