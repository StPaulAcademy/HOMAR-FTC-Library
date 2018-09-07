package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import edu.spa.ftclib.internal.state.ToggleBoolean;
import edu.spa.ftclib.internal.state.ToggleDouble;
import edu.spa.ftclib.internal.state.ToggleInt;

/**
 * Created by Michaela on 1/7/2018.
 * Demonstrates for the various toggle codes can be used to control the robot.
 * If you're using the drive wheels, best to run this one when those wheels aren't touching the ground.
 * Tested and found fully functional by Gabriel on 2018-8-5.
 */

@Disabled
@TeleOp(name = "Toggle Tele-op", group = "sample")

public class ToggleTeleop extends OpMode {
    private Servo servo;
    private DcMotor motor;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;

    private ToggleBoolean toggleBoolean;
    private ToggleInt toggleInt;
    private ToggleDouble toggleDouble;
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
        motor = hardwareMap.get(DcMotor.class, "motor");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        toggleBoolean = new ToggleBoolean();
        toggleInt = new ToggleInt(4);
        toggleDouble = new ToggleDouble(new double[] {
                0, 0.25, 0.5, 0.75, 1
        });
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        toggleBoolean.input(gamepad1.a);
        toggleInt.input(gamepad1.b);
        toggleDouble.input(gamepad1.x);

        if (toggleBoolean.output()) {   //Possibly change to an LED (or use a MotorActivator)
            motor.setPower(1);
        } else {
            motor.setPower(0);
        }

        switch (toggleInt.output()) {
            case 0:
                frontLeft.setPower(1);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(0);
                break;

            case 1:
                frontLeft.setPower(0);
                frontRight.setPower(1);
                backLeft.setPower(0);
                backRight.setPower(0);
                break;

            case 2:
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(1);
                backRight.setPower(0);
                break;

            case 3:
                frontLeft.setPower(0);
                frontRight.setPower(0);
                backLeft.setPower(0);
                backRight.setPower(1);
        }

        servo.setPosition(toggleDouble.output());
    }
}
