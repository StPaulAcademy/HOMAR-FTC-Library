package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import edu.spa.ftclib.internal.activator.ServoActivator;

/**
 * Created by Michaela on 1/5/2018.
 * An example of the ServoActivator, which can be used to streamline the common case of only ever setting a servo to two positions ("activated" and "deactivated").
 * Note the similarity to MotorActivatorTeleop.
 * Tested and found fully functional by Gabriel on 2018-8-5.
 */

@Disabled
@TeleOp (name = "Servo Activator Tele-op", group = "sample")

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
