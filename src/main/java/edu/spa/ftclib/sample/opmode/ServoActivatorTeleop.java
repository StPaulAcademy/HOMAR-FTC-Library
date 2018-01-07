package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

import edu.spa.ftclib.internal.activator.ServoActivator;

/**
 * Created by Michaela on 1/5/2018.
 */

public class ServoActivatorTeleop extends OpMode {
    public Servo servo;
    public ServoActivator servoActivator;

    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        servoActivator = new ServoActivator(servo, 1, 0);
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        if (gamepad1.x){
            servoActivator.activate();
        }
        if (gamepad1.y){
            servoActivator.deactivate();
        }
    }
}
