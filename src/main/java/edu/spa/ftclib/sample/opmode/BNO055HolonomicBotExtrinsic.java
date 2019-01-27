package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.spa.ftclib.internal.state.Button;
import edu.spa.ftclib.sample.robot.BNO055HolonomicBot;

/**
 * Created by Gabriel on 2018-05-21.
 * A simple demo of extrinsic course control.
 * Tested and found fully functional by Gabriel on 2018-8-4.
 * Updated for extrinsic offset and successfully re-tested by Gabriel on 2019-1-25
 */

//@Disabled
@TeleOp(name = "BNO055 Holonomic Bot Extrinsic", group = "sample")

public class BNO055HolonomicBotExtrinsic extends OpMode {
    private static final double HEADING_COEFF = 0.005;  //Radians per millisecond
    private BNO055HolonomicBot robot;
    private Button buttonLeft, buttonRight, buttonY;
    private double desiredHeading = 0;
    private ElapsedTime timer;
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        robot = new BNO055HolonomicBot(telemetry, hardwareMap);
        robot.drivetrain.setExtrinsic(true);

        timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        buttonLeft = new Button();
        buttonRight = new Button();
        buttonY = new Button();
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
        desiredHeading += -gamepad1.left_stick_x*timer.time()*HEADING_COEFF;
        timer.reset();
        buttonLeft.input(gamepad1.left_bumper);
        buttonRight.input(gamepad1.right_bumper);
        if (buttonLeft.onPress()) desiredHeading += -Math.PI/4;
        if (buttonRight.onPress()) desiredHeading += Math.PI/4;
        buttonY.input(gamepad1.y);
        if (buttonY.onPress()) robot.drivetrain.setExtrinsicOffset(desiredHeading);

        robot.drivetrain.setCourse(course);
        robot.drivetrain.setVelocity(velocity);
        robot.drivetrain.setTargetHeading(desiredHeading);
        robot.drivetrain.updateHeading();

        telemetry.addData("Desired heading", desiredHeading);
        telemetry.addData("Extrinsic offset", robot.drivetrain.getExtrinsicOffset());
        telemetry.update();
    }
}
