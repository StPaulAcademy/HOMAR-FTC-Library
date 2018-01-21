package edu.spa.ftclib.util;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.internal.drivetrain.Headingable;
import edu.spa.ftclib.internal.state.Button;
import edu.spa.ftclib.internal.state.ToggleBoolean;
import edu.spa.ftclib.internal.state.ToggleInt;

/**
 * Created by Gabriel on 2018-01-07.
 * Allows you to adjust the constants of a PID controller that is controlling a robot in real time. This helps a lot with tuning.
 * <p />
 * Controls:
 * <ul>
 *     <li>D-pad up: Increase currently selected constant</li>
 *     <li>D-pad down: Decrease currently selected constant</li>
 *     <li>D-pad left: Increase step</li>
 *     <li>D-pad right: Decrease step</li>
 *     <li>X button: Change selected constant</li>
 *     <li>Y button: Reset integral</li>
 *     <li>A button: Toggle pause</li>
 *     <li>Right bumper: Pause automatic heading correction and rotate clockwise</li>
 *     <li>Left bumper: Pause automatic heading correction and rotate counterclockwise</li>
 *     <li>Both bumpers: Pause automatic heading correction and stop rotating</li>
 * </ul>
 */

public class PIDTuner {
    static final private double ROTATION_POWER = 0.5;
    private Headingable drivetrain;
    private PIDController controller;
    private Gamepad gamepad;
    private Telemetry telemetry;
    private double stepP = 1, stepI = 0.01, stepD = 0.01;
    private Button up = new Button();
    private Button down = new Button();
    private Button left = new Button();
    private Button right = new Button();
    private Button y = new Button();
    private ToggleBoolean a = new ToggleBoolean();
    private ToggleInt selected = new ToggleInt(3);

    public PIDTuner(Headingable drivetrain, PIDController controller, Gamepad gamepad, Telemetry telemetry) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        this.gamepad = gamepad;
        this.telemetry = telemetry;
        drivetrain.setTargetHeading(0);
    }

    public void update() {
        up.input(gamepad.dpad_up);
        down.input(gamepad.dpad_down);
        left.input(gamepad.dpad_left);
        right.input(gamepad.dpad_right);
        y.input(gamepad.y);
        selected.input(gamepad.x);
        a.input(gamepad.a);

        if (up.onPress()) {
            switch (selected.output()) {
                case 0:
                    controller.setKP(controller.getKP()+stepP);
                    break;
                case 1:
                    controller.setKI(controller.getKI()+stepI);
                    break;
                case 2:
                    controller.setKD(controller.getKD()+stepD);
                    break;
            }
        }
        if (down.onPress()) {
            switch (selected.output()) {
                case 0:
                    controller.setKP(controller.getKP()-stepP);
                    break;
                case 1:
                    controller.setKI(controller.getKI()-stepI);
                    break;
                case 2:
                    controller.setKD(controller.getKD()-stepD);
                    break;
            }
        }
        if (left.onPress()) {
            switch (selected.output()) {
                case 0:
                    stepP *= 10;
                    break;
                case 1:
                    stepI *= 10;
                    break;
                case 2:
                    stepD *= 10;
                    break;
            }
        }
        if (right.onPress()) {
            switch (selected.output()) {
                case 0:
                    stepP /= 10;
                    break;
                case 1:
                    stepI /= 10;
                    break;
                case 2:
                    stepD /= 10;
                    break;
            }
        }

        if (y.onPress()) controller.resetIntegration();

        if (a.output()) {
            telemetry.addData("Status", "Paused");
            drivetrain.setRotation(0);
        }
        else {
            telemetry.addData("Status", "Running");
            if (gamepad.left_bumper && gamepad.right_bumper) drivetrain.setRotation(0);
            else if (gamepad.left_bumper) drivetrain.setRotation(ROTATION_POWER);
            else if (gamepad.right_bumper) drivetrain.setRotation(-ROTATION_POWER);
            else drivetrain.updateHeading();
        }

        telemetry.addData("Controls", "X to select, Y to reset, A to pause");
        String[] KCaptions = new String[] {"KP", "KI", "KD"};
        KCaptions[selected.output()] = "->"+KCaptions[selected.output()];
        String[] stepCaptions = new String[] {"stepP", "stepI", "stepD"};
        stepCaptions[selected.output()] = "->"+stepCaptions[selected.output()];
        telemetry.addData(KCaptions[0], controller.getKP());
        telemetry.addData(KCaptions[1], controller.getKI());
        telemetry.addData(KCaptions[2], controller.getKD());
        telemetry.addData(stepCaptions[0], stepP);
        telemetry.addData(stepCaptions[1], stepI);
        telemetry.addData(stepCaptions[2], stepD);
        telemetry.addData("Heading*", drivetrain.getCurrentHeading()*1000);
        telemetry.addData("Error*", controller.getError()*1000);
        telemetry.addData("Integral", controller.getIntegral());
        telemetry.addData("Derivative*", controller.getDerivative()*1000);
        telemetry.update();
    }
}
