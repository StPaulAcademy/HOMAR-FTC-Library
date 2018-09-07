package edu.spa.ftclib.sample.opmode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import edu.spa.ftclib.internal.controller.ErrorTimeThresholdFinishingAlgorithm;
import edu.spa.ftclib.internal.controller.FinishableIntegratedController;
import edu.spa.ftclib.internal.controller.PIDController;
import edu.spa.ftclib.internal.drivetrain.HeadingableTankDrivetrain;
import edu.spa.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Gabriel on 2018-06-12.
 * UNTESTED.
 */

@Disabled
@Autonomous (name = "Headingable Tank Square Autonomous", group = "sample")

public class HeadingableTankSquareAutonomous extends LinearOpMode {
    HeadingableTankDrivetrain drivetrain;
    public BNO055IMUImpl imu;

    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor left = hardwareMap.get(DcMotor.class, "driveLeft");
        DcMotor right = hardwareMap.get(DcMotor.class, "driveRight");
        right.setDirection(DcMotor.Direction.REVERSE);

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

        PIDController pid = new PIDController(1.5, 0.05, 0);
        pid.setMaxErrorForIntegral(0.002);

        FinishableIntegratedController controller = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), pid, new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));
        drivetrain = new HeadingableTankDrivetrain(left, right, controller);

        double driveDistance = drivetrain.getTicksPerUnit()*2;

        waitForStart();
        telemetry.addData("status", "rotating1");
        telemetry.update();
        drivetrain.setTargetHeading(-Math.PI/2);
        drivetrain.rotate();
        telemetry.addData("status", "moving1");
        telemetry.update();
        drivetrain.setVelocity(0.5);
        drivetrain.setTargetPosition(driveDistance);
        drivetrain.position();
        drivetrain.setVelocity(0);
        telemetry.addData("status", "2");
        telemetry.update();

        drivetrain.setTargetHeading(-Math.PI);
        drivetrain.rotate();
        drivetrain.setVelocity(0.5);
        drivetrain.setTargetPosition(driveDistance);
        drivetrain.position();
        drivetrain.setVelocity(0);

        drivetrain.setTargetHeading(-Math.PI/2);
        drivetrain.rotate();
        drivetrain.setVelocity(0.5);
        drivetrain.setTargetPosition(-driveDistance);
        drivetrain.position();
        drivetrain.setVelocity(0);

        drivetrain.setTargetHeading(-Math.PI);
        drivetrain.rotate();
        drivetrain.setVelocity(0.5);
        drivetrain.setTargetPosition(-driveDistance);
        drivetrain.position();
        drivetrain.setVelocity(0);
    }
}
