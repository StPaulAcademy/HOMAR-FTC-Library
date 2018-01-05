package edu.spa.ftclib.sample.opmode;

import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.internal.controller.ErrorTimeThresholdFinishingAlgorithm;
import edu.spa.ftclib.internal.controller.FinishableIntegratedController;
import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.internal.drivetrain.HeadingableOmniwheelDrivetrain;
import edu.spa.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Michaela on 1/3/2018.
 */

public class HeadingableOmniwheelRotationAutonomous extends LinearOpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public HeadingableOmniwheelDrivetrain drivetrain;

    public FinishableIntegratedController controller;

    public BNO055IMUImpl imu;

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        controller = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), new PIDController(1,1,1), new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));
        drivetrain = new HeadingableOmniwheelDrivetrain(new DcMotor[]{frontLeft,frontRight, backLeft, backRight}, controller);

        waitForStart();

        drivetrain.setTargetHeading(Math.PI/2);
        drivetrain.rotate();
        sleep(1000);

        drivetrain.setTargetHeading(-Math.PI/2);
        drivetrain.rotate();
        sleep(1000);

        drivetrain.setTargetHeading(0);
        drivetrain.rotate();
        sleep(5000);
    }
}
