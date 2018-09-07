package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import edu.spa.ftclib.internal.state.ToggleDouble;
import edu.spa.ftclib.sample.robot.BNO055HolonomicBot;

/**
 * Created by Michaela on 1/7/2018.
 * A basic tele-op using the {@link BNO055HolonomicBot}. Moves the robot based on the gamepad and toggles the servo through various positions when "x" is pressed.
 */

@Disabled
@TeleOp(name = "BNO055 Holonomic Bot Tele-op", group = "sample")

public class BNO055HolonomicBotTeleop extends OpMode {
    private BNO055HolonomicBot robot;
    private ToggleDouble servoToggle;
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        robot = new BNO055HolonomicBot(telemetry, hardwareMap);
        servoToggle = new ToggleDouble(new double[]{0, 0.25, 0.5, 0.75, 1});
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        double course = Math.atan2(-gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.PI/2;
        double velocity = Math.hypot(gamepad1.right_stick_x, gamepad1.right_stick_y);
        double rotation = -gamepad1.left_stick_x;

        robot.drivetrain.setCourse(course);
        robot.drivetrain.setVelocity(velocity);
        robot.drivetrain.setRotation(rotation);

        servoToggle.input(gamepad1.x);
        //robot.servo.setPosition(servoToggle.output());
    }
}
