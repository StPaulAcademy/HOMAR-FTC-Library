package edu.spa.ftclib.sample.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

@Autonomous

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
    public void runOpMode() throws InterruptedException {   //Notice that this is almost the exact same code as in HeadingableOmniwheelRotationAutonomous.
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        imu = hardwareMap.get(BNO055IMUImpl.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //Add calibration file?
        parameters.loggingEnabled = true;   //For debugging
        parameters.loggingTag = "IMU";      //For debugging
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();  //Figure out why the naive one doesn't have a public constructor
        imu.initialize(parameters);
        while (!imu.isGyroCalibrated());

        controller = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), new PIDController(1,1,1), new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));
        drivetrain = new HeadingableOmniwheelDrivetrain(new DcMotor[]{frontLeft,frontRight, backLeft, backRight}, controller);

        waitForStart();

        drivetrain.setTargetHeading(Math.PI/2);
        drivetrain.rotate();
        sleep(1000);

        drivetrain.setTargetHeading(-Math.PI/2);
        while (drivetrain.isRotating()) {
            drivetrain.updateHeading();
            telemetry.addData("Heading", drivetrain.getCurrentHeading());
            telemetry.update();
        }
        sleep(1000);

        drivetrain.setTargetHeading(0);
        while (opModeIsActive()) drivetrain.updateHeading();
    }
}
