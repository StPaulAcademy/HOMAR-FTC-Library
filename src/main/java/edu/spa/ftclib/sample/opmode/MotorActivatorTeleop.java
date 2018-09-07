package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

import edu.spa.ftclib.internal.activator.MotorActivator;

/**
 * Created by Michaela on 1/7/2018.
 * An example of using the MotorActivator, which can be used for DcMotors or (perhaps more usefully) CRServos. This runs a CR Servo forward when activated and not at all when deactivated.
 * Note the similarity to ServoActivatorTeleop.
 * Tested and found fully functional by Gabriel on 2018-8-5.
 */

@Disabled
@TeleOp(name = "Motor Activator", group = "sample")

public class MotorActivatorTeleop extends OpMode {
    private CRServo servo;
    private MotorActivator motorActivator;
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        servo = hardwareMap.get(CRServo.class, "CRServo");
        motorActivator = new MotorActivator(servo, 1);
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        if (gamepad1.x) {
            motorActivator.activate();
        }
        if (gamepad1.y) {
            motorActivator.deactivate();
        }
    }
}
