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
import edu.spa.ftclib.internal.drivetrain.HeadingableMecanumDrivetrain;
import edu.spa.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Michaela on 1/3/2018.
 * Demonstrates the use of a HeadingableMecanumDrivetrain to autonomously rotate a robot to specific headings and hold those headings.
 * Tested and found fully functional by Gabriel on 2018-8-4.
 */

@Disabled
@Autonomous(name = "Headingable Mecanum Rotation Autonomous", group = "sample")

public class HeadingableMecanumRotationAutonomous extends LinearOpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public HeadingableMecanumDrivetrain drivetrain;

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
        frontLeft = hardwareMap.get(DcMotor.class, "driveFrontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "driveFrontRight");
        backLeft = hardwareMap.get(DcMotor.class, "driveBackLeft");
        backRight = hardwareMap.get(DcMotor.class, "driveBackRight");

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

        controller = new FinishableIntegratedController(new IntegratingGyroscopeSensor(imu), pid, new ErrorTimeThresholdFinishingAlgorithm(Math.PI/50, 1));
        drivetrain = new HeadingableMecanumDrivetrain(new DcMotor[]{frontLeft,frontRight, backLeft, backRight}, controller);
        for (DcMotor motor : drivetrain.motors) motor.setDirection(DcMotor.Direction.REVERSE);  //Depending on the design of the robot, you may need to comment this line out.
        for (DcMotor motor : drivetrain.motors) motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        drivetrain.setTargetHeading(Math.PI/2);
        while (drivetrain.isRotating()) {
            drivetrain.updateHeading();
            doTelemetry();
        }
        //drivetrain.rotate();
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

    void doTelemetry() {
        PIDController pid = (PIDController) drivetrain.controller.algorithm;
        telemetry.addData("heading, target", drivetrain.controller.getSensorValue()+","+pid.getTarget());
        telemetry.addData("KP", pid.getKP());
        telemetry.addData("KI", pid.getKI());
        telemetry.addData("KD", pid.getKD());
        telemetry.addData("error", pid.getError());
        telemetry.addData("integral", pid.getIntegral());
        telemetry.addData("derivative", pid.getDerivative());
        telemetry.update();
    }
}
