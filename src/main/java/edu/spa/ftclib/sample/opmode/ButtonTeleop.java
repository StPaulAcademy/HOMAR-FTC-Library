package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import edu.spa.ftclib.internal.state.Button;

/**
 * Created by Gabriel on 2018-01-07.
 * Simple Button demo that spins a motor for 1 second when the X button on gamepad 1 is pressed
 * Also useful as a simple motor test
 * Tested and found fully functional by Gabriel on 2018-8-5.
 */

@TeleOp
public class ButtonTeleop extends OpMode {
    private Button button;
    private ElapsedTime timer;
    private boolean spinning = false;
    private DcMotor motor;

    @Override
    public void init() {
        button = new Button();
        timer = new ElapsedTime();
        motor = hardwareMap.get(DcMotor.class, "motor");
    }

    @Override
    public void loop() {
        button.input(gamepad1.x);
        if (button.onPress()) {
            timer.reset();
            spinning = true;
            motor.setPower(1);
        }
        if (spinning && timer.seconds() > 1) {
            spinning = false;
            motor.setPower(0);
        }
    }
}
