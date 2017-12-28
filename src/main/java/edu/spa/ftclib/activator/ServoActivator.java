package edu.spa.ftclib.activator;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Gabriel on 2017-11-02; edited substantially on 2017-12-28.
 * Use this if you have a servo that you normally only set to two positions (e.g. an arm that holds something in place).
 * It will store the positions, and then you can call setActivated(true) and setActivated(false) to automatically activate and deactivate the servo.
 * The servo is public, so if you ever need to move it to a different position or change a setting you can do that.
 */

public class ServoActivator implements Activable {
    private boolean activated = false;
    public Servo servo;
    private double activatedPosition, deactivatedPosition;

    public ServoActivator(Servo servo, double activatedPosition, double deactivatedPosition) {
        this.servo = servo;
        this.activatedPosition = activatedPosition;
        this.deactivatedPosition = deactivatedPosition;
    }

    @Override
    public void setActivated(boolean activated) {
        this.activated = activated;
        if (activated) servo.setPosition(activatedPosition);
        else servo.setPosition(deactivatedPosition);
    }

    @Override
    public boolean getActivated() {
        return activated;
    }

    public double getActivatedPosition() {
        return activatedPosition;
    }

    public void setActivatedPosition(double activatedPosition) {
        this.activatedPosition = activatedPosition;
        if (activated)  servo.setPosition(activatedPosition);   //Update motor power if necessary
    }

    public double getDeactivatedPosition() {
        return deactivatedPosition;
    }

    public void setDeactivatedPosition(double deactivatedPosition) {
        this.deactivatedPosition = deactivatedPosition;
        if (!activated) servo.setPosition(deactivatedPosition); //Update motor power if necessary
    }
}
