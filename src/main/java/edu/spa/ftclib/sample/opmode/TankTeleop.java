package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.internal.drivetrain.TankDrivetrain;
import edu.spa.ftclib.internal.state.ToggleBoolean;

/**
 * Created by Gabriel on 2018-06-10.
 * Tested and found fully functional by Gabriel on 2018-9-5.
 * Simple demo of the TankDrivetrain
 * Controls:
 * <ul>
 *     <li>X to toggle drive mode</li>
 *     <li>Drive mode 0:
 *         <ul>
 *             <li>Left or right stick Y (whichever is more extreme): forwards/backwards velocity</li>
 *             <li>Left or right stick X (whichever is more extreme): rotation</li>
 *         </ul>
 *     </li>
 *     <li>Drive mode 1:
 *         <ul>
 *             <li>Left stick Y: left drive motor</li>
 *             <li>Right stick Y: right drive motor</li>
 *         </ul>
 *     </li>
 * </ul>
 */

@Disabled
@TeleOp (name = "Tank Teleop", group = "sample")

public class TankTeleop extends OpMode {
    private TankDrivetrain drivetrain;
    private ToggleBoolean driveMode;

    @Override
    public void init() {
        DcMotor left = hardwareMap.get(DcMotor.class, "driveLeft");
        DcMotor right = hardwareMap.get(DcMotor.class, "driveRight");
        right.setDirection(DcMotor.Direction.REVERSE);
        drivetrain = new TankDrivetrain(left, right);
        driveMode = new ToggleBoolean();
    }

    @Override
    public void loop() {
        driveMode.input(gamepad1.x);
        if (driveMode.output()) {
            drivetrain.setVelocity(absMax(-gamepad1.left_stick_y, -gamepad1.right_stick_y));
            drivetrain.setRotation(absMax(gamepad1.left_stick_x, gamepad1.right_stick_x));
        } else {
            drivetrain.left.setPower(-gamepad1.left_stick_y);
            drivetrain.right.setPower(-gamepad1.right_stick_y);
        }
    }

    private double absMax(double a, double b) { //Returns the argument whose absolute value is greater (similar to Math.max() but compares absolute values)
        return (Math.abs(a) > Math.abs(b)) ? a : b;
    }
}
