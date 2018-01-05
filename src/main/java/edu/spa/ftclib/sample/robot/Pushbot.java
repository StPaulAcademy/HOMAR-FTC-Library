package edu.spa.ftclib.sample.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import edu.spa.ftclib.internal.Robot;
import edu.spa.ftclib.internal.activator.ServoActivator;
import edu.spa.ftclib.internal.drivetrain.TankDrivetrain;

/**
 * Created by Michaela on 2018-01-03.
 * Very similar to the HardwarePushbot code provided by FIRST, but implementing our library.
 */

public class Pushbot extends Robot {
    public DcMotor leftDrive;
    public DcMotor rightDrive;
    public DcMotor leftArm;
    public Servo leftClaw;
    public Servo rightClaw;

    public ServoActivator leftClawActivator;
    public ServoActivator rightClawActivator;
    public TankDrivetrain drivetrain;

    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;

    Pushbot(Telemetry telemetry, HardwareMap hardwareMap) {
        super(telemetry, hardwareMap);

        leftDrive  = hardwareMap.get(DcMotor.class,"left_drive");
        rightDrive = hardwareMap.get(DcMotor.class,"right_drive");
        leftArm    = hardwareMap.get(DcMotor.class,"left_arm");
        leftClaw   = hardwareMap.get(Servo.class, "left_hand");
        rightClaw  = hardwareMap.get(Servo.class, "right_hand");

        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        leftArm.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        drivetrain = new TankDrivetrain(leftDrive, rightDrive);

        leftClawActivator = new ServoActivator(leftClaw, 0.1, 0.5);
        rightClawActivator = new ServoActivator(rightClaw, 0.9, 0.5);

        leftClawActivator.deactivate();
        rightClawActivator.deactivate();
    }
}
