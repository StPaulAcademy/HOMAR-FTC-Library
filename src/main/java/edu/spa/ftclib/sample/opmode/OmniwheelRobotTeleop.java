package edu.spa.ftclib.sample.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.internal.drivetrain.OmniwheelDrivetrain;
import edu.spa.ftclib.internal.state.Button;
import edu.spa.ftclib.internal.state.ToggleBoolean;

/**
 * Created by Michaela on 1/3/2018.
 * Tested and found fully functional by Gabriel on 2018-8-5.
 */

@Disabled
@TeleOp(name = "Omniwheel Robot Tele-op", group = "sample")

public class OmniwheelRobotTeleop extends OpMode {
    public DcMotor drive1;
    public DcMotor drive2;
    public DcMotor drive3;
    public DcMotor drive4;

    public OmniwheelDrivetrain drivetrain;
    private ToggleBoolean toggleDiagonal = new ToggleBoolean(true);    //Represents whether the wheels are mounted diagonally or parallel/perpendicular to "forwards"
    private Button buttonDiagonal = new Button();   //Used to keep track of when toggleDiagonal is changed so we can avoid making a new OmniwheelDrivetrain every loop

    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        drive1 = hardwareMap.get(DcMotor.class, "drive1");
        drive2 = hardwareMap.get(DcMotor.class, "drive2");
        drive3 = hardwareMap.get(DcMotor.class, "drive3");
        drive4 = hardwareMap.get(DcMotor.class, "drive4");

        drivetrain = new OmniwheelDrivetrain(new DcMotor[]{drive1, drive2, drive3, drive4});
        for (DcMotor motor : drivetrain.motors) motor.setDirection(DcMotor.Direction.REVERSE);
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
        toggleDiagonal.input(gamepad1.x);
        buttonDiagonal.input(toggleDiagonal.output());

        if (buttonDiagonal.onPress()) drivetrain = new OmniwheelDrivetrain(new DcMotor[]{drive1, drive2, drive3, drive4});
        if (buttonDiagonal.onRelease()) drivetrain = new OmniwheelDrivetrain(new DcMotor[]{drive1, drive2, drive3, drive4}, Math.PI);
        drivetrain.setCourse(course);
        drivetrain.setVelocity(velocity);
        drivetrain.setRotation(rotation);

        telemetry.addData("diagonal", toggleDiagonal.output());
    }
}

