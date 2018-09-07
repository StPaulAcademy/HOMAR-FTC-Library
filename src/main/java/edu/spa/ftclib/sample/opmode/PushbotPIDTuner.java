package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.sample.robot.HeadingablePushbot;
import edu.spa.ftclib.util.PIDTuner;

/**
 * Created by Gabriel on 2018-01-07.
 */

@Disabled
@TeleOp(name = "Pushbot PID Tuner", group = "sample")

public class PushbotPIDTuner extends OpMode {
    private PIDTuner tuner;
    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        HeadingablePushbot robot = new HeadingablePushbot(telemetry, hardwareMap);
        tuner = new PIDTuner(robot.drivetrain, (PIDController) robot.controller.algorithm, gamepad1, telemetry);
    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        tuner.update();
    }
}
